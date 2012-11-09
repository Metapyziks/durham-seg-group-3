using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TestServer.Requests
{
    [RequestTypeName( "sendverify" )]
    class SendActivationRequest : RequestType
    {
        public override Object Respond( NameValueCollection args )
        {
            String email = args[ "email" ];

            if ( email == null || email.Length == 0 )
                return new Responses.ErrorResponse( "no email address given" );

            if ( !Account.IsEmailValid( email ) )
                return new Responses.ErrorResponse( "invalid email address" );
            
            Account[] accounts = DatabaseManager.Select<Account>( null,
                String.Format( "Email = '{0}'", email ) );

            if ( accounts.Length == 0 )
                return new Responses.ErrorResponse( "email address not recognised" );

            if ( accounts[ 0 ].IsVerified )
                return new Responses.ErrorResponse( "account already activated" );

            DatabaseManager.Delete<VerifyRequest>( String.Format( "{0}='{1}'",
                VerifyRequest.Fields.AccountID, accounts[ 0 ].AccountID ) );

            VerifyRequest verify = VerifyRequest.Create( accounts[ 0 ] );
            DatabaseManager.Insert( verify );

            verify.SendEmail( accounts[ 0 ] );

            return new Responses.AcknowledgeResponse( true );
        }
    }
}
