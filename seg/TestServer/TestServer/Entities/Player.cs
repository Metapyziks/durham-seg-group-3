using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using SQLite;

namespace TestServer.Entities
{
    [Serializable]
    public class Player
    {
        public static Player GetPlayer( Account acc )
        {
            return GetPlayer( acc.AccountID );
        }

        public static Player GetPlayer( int accountID )
        {
            Player ply = DatabaseManager.Get<Player>( accountID );

            if ( ply == null )
            {
                ply = new Player()
                {
                    AccountID = accountID,
                    Balance = 0
                };

                DatabaseManager.Insert( ply );
            }

            return ply;
        }

        [Serialize( "accountid" )]
        [PrimaryKey]
        public int AccountID { get; set; }
        [Serialize( "balance" )]
        public int Balance { get; set; }
    }
}
