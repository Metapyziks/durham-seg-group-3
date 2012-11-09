
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.SqlServerCe;
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
        public readonly String PublicKey;
        public readonly String[] FieldNames;

        public DatabaseEntityAttribute( String entityName, String publicKey,
            params String[] fieldNames )
        {
            EntityName = entityName;
            PublicKey = publicKey;
            FieldNames = fieldNames;
        }
    }

    static class DatabaseManager
    {
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
                if( field == entAttrib.PublicKey )
                    continue;

                fieldDict.Add( field, entity.GetField( field ) );
            }

            String query = String.Format( "INSERT INTO {0} ({1}) VALUES ('{2}')", entityName,
                String.Join( ", ", fieldDict.Keys ), String.Join( "', '", fieldDict.Values ) );

            SqlCeCommand cmd = new SqlCeCommand( query, stConnection );
            return cmd.ExecuteNonQuery();
        }

        public static void Disconnect()
        {
            stConnection.Close();
        }
    }
}
