using System;
using System.IO;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Reflection;
using System.Text;

namespace TestServer
{
#if LINUX
    using DBConnection = Mono.Data.Sqlite.SqliteConnection;
    using DBCommand = Mono.Data.Sqlite.SqliteCommand;
    using DBDataReader = Mono.Data.Sqlite.SqliteDataReader;
#else
    using DBConnection = System.Data.SqlServerCe.SqlCeConnection;
    using DBCommand = System.Data.SqlServerCe.SqlCeCommand;
    using DBEngine = System.Data.SqlServerCe.SqlCeEngine;
#endif

    [AttributeUsage( AttributeTargets.Class )]
    public class DatabaseEntityAttribute : Attribute { }

    [AttributeUsage( AttributeTargets.Property )]
    public class ColumnAttribute : Attribute { }

    [AttributeUsage( AttributeTargets.Property )]
    public class NotNullAttribute : ColumnAttribute { }

    [AttributeUsage( AttributeTargets.Property )]
    public class UniqueAttribute : NotNullAttribute { }

    [AttributeUsage( AttributeTargets.Property )]
    public class PrimaryKeyAttribute : UniqueAttribute { }

    [AttributeUsage( AttributeTargets.Property )]
    public class AutoIncrementAttribute : ColumnAttribute { }

    [AttributeUsage( AttributeTargets.Property )]
    public class FixedLengthAttribute : ColumnAttribute { }

    [AttributeUsage( AttributeTargets.Property )]
    public class CapacityAttribute : ColumnAttribute
    {
        public readonly int Value;
        public readonly int Value2;

        public CapacityAttribute( int value, int value2 = 0 )
        {
            Value = value;
            Value2 = value2;
        }
    }

    public class DatabaseColumn
    {
        private readonly PropertyInfo _property;

        public String Name { get { return _property.Name; } }
        public Type Type { get { return _property.PropertyType; } }

        public bool NotNull { get; private set; }
        public bool Unique { get; private set; }
        public bool PrimaryKey { get; private set; }
        public bool AutoIncrement { get; private set; }
        public bool FixedLength { get; private set; }

        public int Capacity { get; private set; }
        public int Capacity2 { get; private set; }

        public DatabaseColumn( PropertyInfo property )
        {
            _property = property;

            NotNull = property.IsDefined<NotNullAttribute>();
            Unique = property.IsDefined<UniqueAttribute>();
            PrimaryKey = property.IsDefined<PrimaryKeyAttribute>();
            AutoIncrement = property.IsDefined<AutoIncrementAttribute>();
            FixedLength = property.IsDefined<FixedLengthAttribute>();

            if ( property.IsDefined<CapacityAttribute>() )
            {
                CapacityAttribute val = property.GetCustomAttribute<CapacityAttribute>();
                Capacity = val.Value;
                Capacity2 = val.Value2;
            }
            else
            {
                Capacity = 0;
                Capacity2 = 0;
            }
        }

        private static String GetSQLTypeName( DatabaseColumn col, Type type )
        {
            if ( type.IsEnum )
                return GetSQLTypeName( col, Enum.GetUnderlyingType( type ) );

            if ( type == typeof( String ) || type == typeof( Char[] ) )
            {
                if ( col.FixedLength )
                    return "NCHAR({0})";

                return "NVARCHAR({0})";
            }

            if ( type == typeof( Int64 ) || type == typeof( DateTime ) )
                return "BIGINT";

            if ( type == typeof( Int32 ) )
                return "INTEGER";

            if ( type == typeof( Int16 ) )
                return "SMALLINT";

            if ( type == typeof( Byte ) )
                return "TINYINT";

            throw new Exception( "Can't find the SQL type of " + type.FullName );
        }

        public String GenerateDefinitionStatement()
        {
            StringBuilder builder = new StringBuilder();

            builder.AppendFormat( "{0} {1} ", Name, String.Format(
                GetSQLTypeName( this, Type ), Capacity, Capacity2 ) );

            if ( PrimaryKey )
                builder.Append( "PRIMARY KEY " );
            else if ( Unique )
                builder.Append( "UNIQUE " );
            else if ( NotNull )
                builder.Append( "NOT NULL " );

            if ( AutoIncrement )
#if LINUX
                builder.Append( "AUTOINCREMENT " );
#else
                builder.Append( "IDENTITY " );
#endif

            return builder.ToString();
        }

        public override string ToString()
        {
            return GenerateDefinitionStatement();
        }
    }

    public class DatabaseTable
    {
        private readonly Type _type;

        public Type Type { get { return _type; } }
        public String Name { get { return _type.Name; } }

        public DatabaseColumn[] Columns { get; private set; }

        public DatabaseTable( Type type )
        {
            _type = type;

            BuildColumns();
        }

        private void BuildColumns()
        {
            int count = _type.GetProperties().Count( x => x.IsDefined<ColumnAttribute>() );
            Columns = new DatabaseColumn[count];

            int i = 0;
            foreach( PropertyInfo property in _type.GetProperties() )
                if ( property.IsDefined<ColumnAttribute>() )
                    Columns[i++] = new DatabaseColumn( property );
        }

        public String GenerateDefinitionStatement()
        {
            StringBuilder builder = new StringBuilder();
            builder.AppendFormat( "CREATE TABLE {0}\n(\n", Name );
            for ( int i = 0; i < Columns.Length; ++i )
                builder.AppendFormat( "\t{0}{1}\n", Columns[i].GenerateDefinitionStatement(),
                    i < Columns.Length - 1 ? "," : "" );
            builder.AppendFormat( ");\n" );
            return builder.ToString();
        }

        public override string ToString()
        {
            return Name;
        }
    }

    public static class DatabaseManager
    {
        public static CultureInfo CultureInfo = new CultureInfo( "en-US" );

        private static readonly String _sFileName = "Database.db";
        private static DBConnection _sConnection;
        private static List<DatabaseTable> _sTables;

        public static void Connect( String connStrFormat, params String[] args )
        {
            String connectionString = String.Format( connStrFormat, args );
            _sConnection = new DBConnection( connectionString );
            _sConnection.Open();

            _sTables = new List<DatabaseTable>();
        }

        public static void ConnectLocal()
        {
            if( !File.Exists( _sFileName ) )
                CreateDatabase( "Data Source={0};", _sFileName );
            else
                Connect( "Data Source={0};", _sFileName );
        }

        private static void CreateDatabase( String connStrFormat, params String[] args )
        {
#if !LINUX
            DBEngine engine = new DBEngine( String.Format( connStrFormat, args ) );
            engine.CreateDatabase();
            engine.Dispose();
#endif
            Connect( connStrFormat, args );

            foreach ( Type type in Assembly.GetExecutingAssembly().GetTypes() )
            {
                if ( type.IsDefined<DatabaseEntityAttribute>() )
                {
                    DatabaseTable table = CreateTable( type );

                    DBCommand cmd = new DBCommand( table.GenerateDefinitionStatement(), _sConnection );
                    cmd.ExecuteNonQuery();
                }
            }
        }

        private static DatabaseTable CreateTable<T>()
            where T : new()
        {
            return CreateTable( typeof( T ) );
        }

        private static DatabaseTable CreateTable( Type type )
        {
            DatabaseTable newTable = new DatabaseTable( type );
            _sTables.Add( newTable );
            return newTable;
        }

        public static T[] Select<T>( String[] fields = null, String condition = null )
            where T : new()
        {
            return new T[0];

            //Type t = typeof( T );

            //if ( !t.IsDefined( typeof( DatabaseEntityAttribute ), true ) )
            //    throw new Exception( t.FullName + " is not a valid database entity type" );

            //DatabaseEntityAttribute entAttrib = t.GetCustomAttribute<DatabaseEntityAttribute>( true );
            //String entityName = entAttrib.EntityName;

            //String fieldString = fields == null ? "*" : String.Join( ", ", fields );

            //String query;
            //if( condition != null )
            //    query = String.Format( "SELECT {0} FROM {1} WHERE {2}", fieldString, entityName, condition );
            //else
            //    query = String.Format( "SELECT {0} FROM {1}", fieldString, entityName );

            //DBCommand cmd = new DBCommand( query, _sConnection );
            //DBDataReader reader = cmd.ExecuteReader();

            //List<T> entities = new List<T>();

            //while ( reader.Read() )
            //{
            //    T entity = new T();

            //    foreach ( String field in entAttrib.FieldNames )
            //        entity.SetField( field, reader[ field ] );

            //    entities.Add( entity );
            //}

            //reader.Close();

            //return entities.ToArray();
        }

        public static int Insert<T>( T entity )
            where T : new()
        {
            return 0;

            //Type t = typeof( T );

            //if ( !t.IsDefined( typeof( DatabaseEntityAttribute ), true ) )
            //    throw new Exception( t.FullName + " is not a valid database entity type" );

            //DatabaseEntityAttribute entAttrib = t.GetCustomAttribute<DatabaseEntityAttribute>( true );
            //String entityName = entAttrib.EntityName;

            //Dictionary<String, String> fieldDict = new Dictionary<String, String>();
            //foreach ( String field in entAttrib.FieldNames )
            //{
            //    if ( entAttrib.AutoAssignKey && field == entAttrib.PrimaryKey )
            //        continue;

            //    fieldDict.Add( field, entity.GetField( field ) );
            //}

            //String query = String.Format( "INSERT INTO {0} ({1}) VALUES ('{2}')", entityName,
            //    String.Join( ", ", fieldDict.Keys.ToArray() ), String.Join( "', '", fieldDict.Values.ToArray() ) );

            //DBCommand cmd = new DBCommand( query, _sConnection );
            //return cmd.ExecuteNonQuery();
        }

        public static int Update<T>( T entity )
            where T : new()
        {
            return 0;

            //Type t = typeof( T );

            //if ( !t.IsDefined( typeof( DatabaseEntityAttribute ), true ) )
            //    throw new Exception( t.FullName + " is not a valid database entity type" );

            //DatabaseEntityAttribute entAttrib = t.GetCustomAttribute<DatabaseEntityAttribute>( true );
            //String entityName = entAttrib.EntityName;

            //List<String> fieldSets = new List<string>();
            //foreach ( String field in entAttrib.FieldNames )
            //{
            //    if ( field == entAttrib.PrimaryKey )
            //        continue;

            //    fieldSets.Add( String.Format( "{0}='{1}'", field, entity.GetField( field ) ) );
            //}

            //String query = String.Format( "UPDATE {0} SET {1} WHERE {2}='{3}'", entityName,
            //    String.Join( ", ", fieldSets.ToArray() ), entAttrib.PrimaryKey,
            //    entity.GetField( entAttrib.PrimaryKey ) );

            //DBCommand cmd = new DBCommand( query, _sConnection );
            //return cmd.ExecuteNonQuery();
        }

        public static int Delete<T>( T entity )
            where T : new()
        {
            return 0;

            //Type t = typeof( T );

            //if ( !t.IsDefined( typeof( DatabaseEntityAttribute ), true ) )
            //    throw new Exception( t.FullName + " is not a valid database entity type" );

            //DatabaseEntityAttribute entAttrib = t.GetCustomAttribute<DatabaseEntityAttribute>( true );

            //return Delete<T>( String.Format( "{0}='{1}'", entAttrib.PrimaryKey,
            //    entity.GetField( entAttrib.PrimaryKey ) ) );
        }

        public static int Delete<T>( IEnumerable<T> entities )
            where T : new()
        {
            return 0;

            //Type t = typeof( T );

            //if ( !t.IsDefined( typeof( DatabaseEntityAttribute ), true ) )
            //    throw new Exception( t.FullName + " is not a valid database entity type" );

            //DatabaseEntityAttribute entAttrib = t.GetCustomAttribute<DatabaseEntityAttribute>( true );

            //StringBuilder condBuilder = new StringBuilder();
            //bool first = true;
            //foreach ( T entity in entities )
            //{
            //    if ( !first )
            //        condBuilder.Append( " OR " );
            //    else
            //        first = false;
                
            //    condBuilder.AppendFormat( "{0}='{1}'", entAttrib.PrimaryKey, entity.GetField( entAttrib.PrimaryKey ) );
            //}

            //return Delete<T>( condBuilder.ToString() );
        }

        public static int Delete<T>( String condition )
            where T : new()
        {
            return 0;

            //Type t = typeof( T );

            //if ( !t.IsDefined( typeof( DatabaseEntityAttribute ), true ) )
            //    throw new Exception( t.FullName + " is not a valid database entity type" );

            //DatabaseEntityAttribute entAttrib = t.GetCustomAttribute<DatabaseEntityAttribute>( true );
            //String entityName = entAttrib.EntityName;

            //String query = String.Format( "DELETE FROM {0} WHERE {1}", entityName, condition );

            //DBCommand cmd = new DBCommand( query, _sConnection );
            //return cmd.ExecuteNonQuery();
        }

        public static void Disconnect()
        {
            _sConnection.Close();
        }
    }
}
