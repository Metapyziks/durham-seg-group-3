using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TestServer
{
    [DatabaseEntity( "VerifyRequest", VerifyRequest.Fields.VerifyRequestID,
        VerifyRequest.Fields.VerifyRequestID,
        VerifyRequest.Fields.AccountID,
        VerifyRequest.Fields.ValidationCode,
        VerifyRequest.Fields.SentDate
    )]
    class VerifyRequest : IDatabaseEntity
    {
        private static Random stRand = new Random();

        public struct Fields
        {
            public const String VerifyRequestID = "VerifyRequestID";
            public const String AccountID = "AccountID";
            public const String ValidationCode = "ValidationCode";
            public const String SentDate = "SentDate";
        }

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

            return new VerifyRequest()
            {
                AccountID = account.AccountID,
                ValidationCode = GenerateValidationCode(),
                SentDate = DateTime.Now
            };
        }

        public int VerifyRequestID { get; private set; }
        public int AccountID { get; set; }
        public char[] ValidationCode { get; set; }
        public DateTime SentDate { get; set; }

        public string GetField( string fieldName )
        {
            switch ( fieldName )
            {
                case Fields.VerifyRequestID:
                    return VerifyRequestID.ToString();
                case Fields.AccountID:
                    return AccountID.ToString();
                case Fields.ValidationCode:
                    return String.Join( "", ValidationCode );
                case Fields.SentDate:
                    return SentDate.ToString();
                default:
                    return "";
            }
        }

        public void SetField( string fieldName, object value )
        {
            switch ( fieldName )
            {
                case Fields.VerifyRequestID:
                    VerifyRequestID = Convert.ToInt32( value ); break;
                case Fields.AccountID:
                    AccountID = Convert.ToInt32( value ); break;
                case Fields.ValidationCode:
                    ValidationCode = Convert.ToString( value ).ToCharArray(); break;
                case Fields.SentDate:
                    SentDate = Convert.ToDateTime( value ); break;
            }
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
