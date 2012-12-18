using System;

namespace TestServer.Entities
{
    [Serializable, DatabaseEntity]
    public class Player
    {
        public struct Fields
        {
            public const String AccountID = "AccountID";
            public const String Balance = "Balance";
        }

        public static Player GetPlayer( Account acc )
        {
            return GetPlayer( acc.AccountID );
        }

        public static Player GetPlayer( int accountID )
        {
            Player[] plys = DatabaseManager.Select<Player>( x => x.AccountID == accountID );

            if ( plys.Length == 0 )
            {
                Player ply = new Player()
                {
                    AccountID = accountID,
                    Balance = 0
                };

                DatabaseManager.Insert( ply );

                return ply;
            }

            return plys[0];
        }

        [Serialize( "accountid" )]
        [PrimaryKey]
        public int AccountID { get; set; }

        [Serialize( "balance" )]
        [NotNull]
        public int Balance { get; set; }
    }
}
