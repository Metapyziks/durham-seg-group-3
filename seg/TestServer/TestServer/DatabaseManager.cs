
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.SqlServerCe;
using System.Globalization;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;

namespace TestServer
{
    interface IDatabaseEntity
    {
        String GetField( String fieldName );
        void SetField( String fieldName, Object value );
    }

    [AttributeUsage( AttributeTargets.Class )]
    class DatabaseEntityAttribute : Attribute
    {
        public readonly String EntityName;
        public readonly String PrimaryKey;
        public readonly String[] FieldNames;

        public DatabaseEntityAttribute( String entityName, String publicKey,
            params String[] fieldNames )
        {
            EntityName = entityName;
            PrimaryKey = publicKey;
            FieldNames = fieldNames;
        }
    }

    static class DatabaseManager
    {
        public static CultureInfo CultureInfo = new CultureInfo( "en-US" );

        private static SqlCeConnection stConnection;

        public static void Connect()
        {
            stConnection = new SqlCeConnection( "Data Source=Database.sdf" );
            stConnection.Open();
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

            SqlCeCommand cmd = new SqlCeCommand( query, stConnection );
            SqlCeDataReader reader = cmd.ExecuteReader();

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
                if( field == entAttrib.PrimaryKey )
                    continue;

                fieldDict.Add( field, entity.GetField( field ) );
            }

            String query = String.Format( "INSERT INTO {0} ({1}) VALUES ('{2}')", entityName,
                String.Join( ", ", fieldDict.Keys ), String.Join( "', '", fieldDict.Values ) );

            SqlCeCommand cmd = new SqlCeCommand( query, stConnection );
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
                String.Join( ", ", fieldSets ), entAttrib.PrimaryKey, entity.GetField( entAttrib.PrimaryKey ) );

            SqlCeCommand cmd = new SqlCeCommand( query, stConnection );
            return cmd.ExecuteNonQuery();
        }

        public static int Delete<T>( T entity )
            where T : IDatabaseEntity
        {
            Type t = typeof( T );

            if ( !t.IsDefined( typeof( DatabaseEntityAttribute ), true ) )
                throw new Exception( t.FullName + " is not a valid database entity type" );

            DatabaseEntityAttribute entAttrib = t.GetCustomAttribute<DatabaseEntityAttribute>( true );

            return Delete<T>( String.Format( "{0} = '{1}'", entAttrib.PrimaryKey,
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

            SqlCeCommand cmd = new SqlCeCommand( query, stConnection );
            return cmd.ExecuteNonQuery();
        }

        public static void Disconnect()
        {
            stConnection.Close();
        }
    }
}
