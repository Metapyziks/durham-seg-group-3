using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Linq;
using System.Text;

using TestServer.Entities;

namespace TestServer.Requests
{
    [RequestTypeName( "sendverify" )]
    class SendActivationRequest : Request
    {
        public override Responses.Response Respond( NameValueCollection args )
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

            VerificationCode.Create( accounts[ 0 ] ).SendEmail( accounts[ 0 ] );

            return new Responses.Response( true );
        }
    }
}
