using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Linq;
using System.Text;

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

            Account account = DatabaseManager.Get<Account>( x => x.Email == email );

            if ( account == null )
                return new Responses.ErrorResponse( "email address not recognised" );

            if ( account.IsVerified )
                return new Responses.ErrorResponse( "account already activated" );

            VerificationCode request = VerificationCode.Get( account );

            if ( request == null || !code.EqualsCharArray( request.Code ) )
                return new Responses.ErrorResponse( "incorrect activation code" );

            request.Remove();

            account.Rank = Rank.Verified;
            DatabaseManager.Update( account );

            return new Responses.Response( true );
        }
    }
}
