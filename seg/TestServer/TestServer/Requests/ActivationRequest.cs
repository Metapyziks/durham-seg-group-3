using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TestServer.Requests
{
    [RequestTypeName( "activate" )]
    class ActivationRequest : RequestType
    {
        public override Object Respond( NameValueCollection args )
        {
            String email = args[ "email" ];
            String code = args[ "code" ];

            if ( email == null || email.Length == 0 )
                return new Responses.ErrorResponse( "no email address given" );

            if ( !Account.IsEmailValid( email ) )
                return new Responses.ErrorResponse( "invalid email address" );

            if(  !Account.IsPasswordHashValid( code ) )
                return new Responses.ErrorResponse( "invalid activation code" );

            Account[] accounts = DatabaseManager.Select<Account>( null,
                String.Format( "Email = '{0}'", email ) );

            if ( accounts.Length == 0 )
                return new Responses.ErrorResponse( "email address not recognised" );

            if ( accounts[ 0 ].IsVerified )
                return new Responses.ErrorResponse( "account already activated" );

            VerifyRequest[] requests = DatabaseManager.Select<VerifyRequest>( null,
                String.Format( "AccountID = '{0}'", accounts[ 0 ].AccountID.ToString() ) );

            if ( requests.Length == 0 || String.Join( "", requests.Last().ValidationCode ) != code )
                return new Responses.ErrorResponse( "incorrect activation code" );

            DatabaseManager.Delete<VerifyRequest>( requests );

            accounts[ 0 ].Rank = Rank.Verified;
            DatabaseManager.Update( accounts[ 0 ] );

            return new Responses.AcknowledgeResponse( true );
        }
    }
}
