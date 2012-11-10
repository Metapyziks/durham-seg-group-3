using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using TestServer.Entities;

namespace TestServer
{
    class VerifyRequest
    {
        public const double ExpirationTime = 60.0 * 60.0 * 24.0;

        private static Random stRand = new Random();
        private static List<VerifyRequest> stList = new List<VerifyRequest>();

        public static char[] GenerateValidationCode()
        {
            char[] str = new char[ 32 ];
            for ( int i = 0; i < 32; ++i )
                str[ i ] = stRand.Next( 16 ).ToString( "X" ).ToLower()[ 0 ];

            return str;
        }

        public static VerifyRequest Create( Account account )
        {
            if ( account.IsVerified )
                return null;

            VerifyRequest request = new VerifyRequest()
            {
                AccountID = account.AccountID,
                ValidationCode = GenerateValidationCode(),
                SentDate = DateTime.Now
            };

            Remove( account );
            stList.Add( request );

            return request;
        }

        public static VerifyRequest Get( Account account )
        {
            return stList.Find( x => x.AccountID == account.AccountID );
        }

        public static void Remove( Account account )
        {
            Remove( account.AccountID );
        }

        public static void Remove( int accountID )
        {
            for ( int i = stList.Count - 1; i >= 0; --i )
                if ( stList[ i ].AccountID == accountID || stList[ i ].IsExpired )
                    stList.RemoveAt( i );
        }

        public int AccountID { get; private set; }
        public char[] ValidationCode { get; private set; }
        public DateTime SentDate { get; private set; }

        public bool IsExpired
        {
            get { return ( SentDate - DateTime.Now ).TotalSeconds >= ExpirationTime; }
        }

        public void Remove()
        {
            Remove( AccountID );
        }

        public void SendEmail( Account account )
        {
            EmailManager.Send( account.Email, "TestServer account activation", String.Format(
@"Hey {0},

To finish the registration process just click this link: {1}api/activate?email={2}&code={3}

Have fun!", account.Username, Program.ServerAddress, account.Email, String.Join( "", ValidationCode ) ) );
        }
    }
}
