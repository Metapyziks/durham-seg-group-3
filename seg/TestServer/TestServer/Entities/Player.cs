using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TestServer.Entities
{
    [Serializable]
    [DatabaseEntity( "Player", Player.Fields.AccountID, false,
        Player.Fields.AccountID,
        Player.Fields.Balance)]
    class Player : IDatabaseEntity
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
            Player[] plys = DatabaseManager.Select<Player>( null,
                String.Format( "{0} = '{1}'", Fields.AccountID, accountID ) );

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
        public int AccountID { get; set; }
        [Serialize( "balance" )]
        public int Balance { get; set; }

        public string GetField( string fieldName )
        {
            switch ( fieldName )
            {
                case Fields.AccountID:
                    return AccountID.ToString();
                case Fields.Balance:
                    return Balance.ToString();
                default:
                    return "";
            }
        }

        public void SetField( string fieldName, object value )
        {
            switch ( fieldName )
            {
                case Fields.AccountID:
                    AccountID = Convert.ToInt32( value ); break;
                case Fields.Balance:
                    Balance = Convert.ToInt32( value ); break;
            }
        }
    }
}
