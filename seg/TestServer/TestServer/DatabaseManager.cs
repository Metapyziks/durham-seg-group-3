using System;
using System.IO;
using System.Collections.Generic;
using System.Data;
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
    using DBDataReader = System.Data.SqlServerCe.SqlCeDataReader;
    using DBEngine = System.Data.SqlServerCe.SqlCeEngine;
#endif

    public interface IDatabaseEntity
    {
        String GetField( String fieldName );
        void SetField( String fieldName, Object value );
    }

    [AttributeUsage( AttributeTargets.Class )]
    public class DatabaseEntityAttribute : Attribute
    {
        public readonly String EntityName;
        public readonly bool AutoAssignKey;
        public readonly String PrimaryKey;
        public readonly String[] FieldNames;

        public DatabaseEntityAttribute( String entityName, String publicKey, bool autoAssignKey,
            params String[] fieldNames )
        {
            EntityName = entityName;
            PrimaryKey = publicKey;
            AutoAssignKey = autoAssignKey;
            FieldNames = fieldNames;
        }
    }

    public static class DatabaseManager
    {
        public static CultureInfo CultureInfo = new CultureInfo( "en-US" );

        private static readonly String _sFileName = "Database.db";
        private static DBConnection _sConnection;

        public static void Connect( String connStrFormat, params String[] args )
        {
            String connectionString = String.Format( connStrFormat, args );
            _sConnection = new DBConnection( connectionString );
            _sConnection.Open();
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
#if LINUX
#else
            DBEngine engine = new DBEngine( String.Format( connStrFormat, args ) );
            engine.CreateDatabase();
            engine.Dispose();
#endif
            Connect( connStrFormat, args );
#if LINUX
            String ddl = @"CREATE TABLE Account
            (
                AccountID INTEGER NOT NULL UNIQUE PRIMARY KEY AUTOINCREMENT,
                Username NVARCHAR(32) NOT NULL UNIQUE,
                PasswordHash NCHAR(32) NOT NULL,
                Email NVARCHAR(64) NOT NULL UNIQUE,
                RegistrationDate INTEGER(8) NOT NULL,
                RANK INTEGER(1) NOT NULL DEFAULT 0
            )";
#else
            String ddl = @"CREATE TABLE Account
            (
                AccountID INTEGER IDENTITY(1,1) PRIMARY KEY,
                Username NVARCHAR(32) NOT NULL UNIQUE,
                PasswordHash NCHAR(32) NOT NULL,
                Email NVARCHAR(64) NOT NULL UNIQUE,
                RegistrationDate BIGINT NOT NULL,
                RANK TINYINT NOT NULL DEFAULT 0
            )";
#endif
            DBCommand cmd = new DBCommand( ddl, _sConnection );
            cmd.ExecuteNonQuery();
        }

        public static T[] Select<T>( String[] fields = null, String condition = null )
            where T : IDatabaseEntity, new()
        {
            Type t = typeof( T );

            if ( !t.IsDefined( typeof( DatabaseEntityAttribute ), true ) )
                throw new Exception( t.FullName + " is not a valid database entity type" );

            DatabaseEntityAttribute entAttrib = t.GetCustomAttribute<DatabaseEntityAttribute>( true );
            String entityName = entAttrib.EntityName;

            String fieldString = fields == null ? "*" : String.Join( ", ", fields );

            String query;
            if( condition != null )
                query = String.Format( "SELECT {0} FROM {1} WHERE {2}", fieldString, entityName, condition );
            else
                query = String.Format( "SELECT {0} FROM {1}", fieldString, entityName );

            DBCommand cmd = new DBCommand( query, _sConnection );
            DBDataReader reader = cmd.ExecuteReader();

            List<T> entities = new List<T>();

            while ( reader.Read() )
            {
                T entity = new T();

                foreach ( String field in entAttrib.FieldNames )
                    entity.SetField( field, reader[ field ] );

                entities.Add( entity );
            }

            reader.Close();

            return entities.ToArray();
        }

        public static int Insert<T>( T entity )
            where T : IDatabaseEntity
        {
            Type t = typeof( T );

            if ( !t.IsDefined( typeof( DatabaseEntityAttribute ), true ) )
                throw new Exception( t.FullName + " is not a valid database entity type" );

            DatabaseEntityAttribute entAttrib = t.GetCustomAttribute<DatabaseEntityAttribute>( true );
            String entityName = entAttrib.EntityName;

            Dictionary<String, String> fieldDict = new Dictionary<String, String>();
            foreach ( String field in entAttrib.FieldNames )
            {
                if( entAttrib.AutoAssignKey && field == entAttrib.PrimaryKey )
                    continue;

                fieldDict.Add( field, entity.GetField( field ) );
            }

            String query = String.Format( "INSERT INTO {0} ({1}) VALUES ('{2}')", entityName,
                String.Join( ", ", fieldDict.Keys.ToArray() ), String.Join( "', '", fieldDict.Values.ToArray() ) );

            DBCommand cmd = new DBCommand( query, _sConnection );
            return cmd.ExecuteNonQuery();
        }

        public static int Update<T>( T entity )
            where T : IDatabaseEntity
        {
            Type t = typeof( T );

            if ( !t.IsDefined( typeof( DatabaseEntityAttribute ), true ) )
                throw new Exception( t.FullName + " is not a valid database entity type" );

            DatabaseEntityAttribute entAttrib = t.GetCustomAttribute<DatabaseEntityAttribute>( true );
            String entityName = entAttrib.EntityName;

            List<String> fieldSets = new List<string>();
            foreach ( String field in entAttrib.FieldNames )
            {
                if ( field == entAttrib.PrimaryKey )
                    continue;

                fieldSets.Add( String.Format( "{0}='{1}'", field, entity.GetField( field ) ) );
            }

            String query = String.Format( "UPDATE {0} SET {1} WHERE {2}='{3}'", entityName,
                String.Join( ", ", fieldSets.ToArray() ), entAttrib.PrimaryKey,
                entity.GetField( entAttrib.PrimaryKey ) );

            DBCommand cmd = new DBCommand( query, _sConnection );
            return cmd.ExecuteNonQuery();
        }

        public static int Delete<T>( T entity )
            where T : IDatabaseEntity
        {
            Type t = typeof( T );

            if ( !t.IsDefined( typeof( DatabaseEntityAttribute ), true ) )
                throw new Exception( t.FullName + " is not a valid database entity type" );

            DatabaseEntityAttribute entAttrib = t.GetCustomAttribute<DatabaseEntityAttribute>( true );

            return Delete<T>( String.Format( "{0}='{1}'", entAttrib.PrimaryKey,
                entity.GetField( entAttrib.PrimaryKey ) ) );
        }

        public static int Delete<T>( IEnumerable<T> entities )
            where T : IDatabaseEntity
        {
            Type t = typeof( T );

            if ( !t.IsDefined( typeof( DatabaseEntityAttribute ), true ) )
                throw new Exception( t.FullName + " is not a valid database entity type" );

            DatabaseEntityAttribute entAttrib = t.GetCustomAttribute<DatabaseEntityAttribute>( true );

            StringBuilder condBuilder = new StringBuilder();
            bool first = true;
            foreach ( T entity in entities )
            {
                if ( !first )
                    condBuilder.Append( " OR " );
                else
                    first = false;
                
                condBuilder.AppendFormat( "{0}='{1}'", entAttrib.PrimaryKey, entity.GetField( entAttrib.PrimaryKey ) );
            }

            return Delete<T>( condBuilder.ToString() );
        }

        public static int Delete<T>( String condition )
            where T : IDatabaseEntity
        {
            Type t = typeof( T );

            if ( !t.IsDefined( typeof( DatabaseEntityAttribute ), true ) )
                throw new Exception( t.FullName + " is not a valid database entity type" );

            DatabaseEntityAttribute entAttrib = t.GetCustomAttribute<DatabaseEntityAttribute>( true );
            String entityName = entAttrib.EntityName;

            String query = String.Format( "DELETE FROM {0} WHERE {1}", entityName, condition );

            DBCommand cmd = new DBCommand( query, _sConnection );
            return cmd.ExecuteNonQuery();
        }

        public static void Disconnect()
        {
            _sConnection.Close();
        }
    }
}
