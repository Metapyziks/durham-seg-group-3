using System;
using System.Collections.Generic;
using System.Globalization;
using System.IO;
using System.Linq.Expressions;

using SQLite;

namespace TestServer
{
    public static class DatabaseManager
    {
        public static CultureInfo CultureInfo = new CultureInfo( "en-US" );

        private static readonly String _sFileName = "Database.db";

        private static SQLiteConnection _sConnection;

        public static void Connect()
        {
            bool newDB = !File.Exists( _sFileName );
            _sConnection = new SQLiteConnection( _sFileName );

            if ( newDB )
            {
                _sConnection.CreateTable<Entities.Account>();
            }
        }

        public static T Get<T>( object pk )
            where T : new()
        {
            return _sConnection.Get<T>( pk );
        }

        public static T Get<T>( Expression<Func<T, bool>> predicate )
            where T : new()
        {
            return _sConnection.Get<T>( predicate );
        }

        public static List<T> Query<T>( String query, params object[] args )
            where T : new()
        {
            return _sConnection.Query<T>( query, args );
        }

        public static List<T> SelectAll<T>()
            where T : new()
        {
            return Query<T>( "select * from " + typeof(T).Name );
        }

        public static int Insert( object obj )
        {
            return _sConnection.Insert( obj );
        }

        public static int Update( object obj )
        {
            return _sConnection.Update( obj );
        }

        public static void Disconnect()
        {
            _sConnection.Close();
        }
    }
}
