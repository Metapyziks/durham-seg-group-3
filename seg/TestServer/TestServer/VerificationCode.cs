using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using TestServer.Entities;

namespace TestServer
{
    class VerificationCode
    {
        public const double ExpirationTime = 60.0 * 60.0 * 24.0;

        private static Random stRand = new Random();
        private static Dictionary<Int32, VerificationCode> stCodes = new Dictionary<int, VerificationCode>();

        public static char[] GenerateCode()
        {
            char[] str = new char[ 32 ];
            for ( int i = 0; i < 32; ++i )
                str[ i ] = stRand.Next( 16 ).ToString( "X" ).ToLower()[ 0 ];

            return str;
        }

        public static VerificationCode Create( Account account )
        {
            if ( account.IsVerified )
                return null;

            VerificationCode code = new VerificationCode()
            {
                AccountID = account.AccountID,
                Code = GenerateCode(),
                SentDate = DateTime.Now
            };

            if ( stCodes.ContainsKey( account.AccountID ) )
                stCodes[ account.AccountID ] = code;
            else
                stCodes.Add( account.AccountID, code );

            return code;
        }

        public static VerificationCode Get( Account account )
        {
            return stCodes.ContainsKey( account.AccountID ) ?
                stCodes[ account.AccountID ] : null;
        }

        public static void Remove( Account account )
        {
            Remove( account.AccountID );
        }

        public static void Remove( int accountID )
        {
            if ( stCodes.ContainsKey( accountID ) )
                stCodes.Remove( accountID );
        }

        public int AccountID { get; private set; }
        public char[] Code { get; private set; }
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

Have fun!", account.Username, Program.ServerAddress, account.Email, String.Join( "", Code ) ) );
        }
    }
}
