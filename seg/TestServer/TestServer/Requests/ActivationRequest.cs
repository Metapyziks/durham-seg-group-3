using System;
using System.Collections.Specialized;

using TestServer.Entities;

namespace TestServer.Requests
{
    [RequestTypeName( "activate" )]
    class ActivationRequest : Request
    {
        public override Responses.Response Respond( NameValueCollection args )
        {
            String email = args[ "email" ];
            String code = args[ "code" ];

            if ( email == null || email.Length == 0 )
                return new Responses.ErrorResponse( "no email address given" );

            if ( !Account.IsEmailValid( email ) )
                return new Responses.ErrorResponse( "invalid email address" );

            if(  !Account.IsPasswordHashValid( code ) )
                return new Responses.ErrorResponse( "invalid activation code" );

            Account[] accounts = DatabaseManager.Select<Account>( x => x.Email == email );

            if ( accounts.Length == 0 )
                return new Responses.ErrorResponse( "email address not recognised" );

            if ( accounts[ 0 ].IsVerified )
                return new Responses.ErrorResponse( "account already activated" );

            VerificationCode request = VerificationCode.Get( accounts[ 0 ] );

            if ( request == null || !code.EqualsCharArray( request.Code ) )
                return new Responses.ErrorResponse( "incorrect activation code" );

            request.Remove();

            accounts[ 0 ].Rank = Rank.Verified;
            DatabaseManager.Update( accounts[ 0 ] );

            return new Responses.Response( true );
        }
    }
}
