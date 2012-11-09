using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TestServer.Requests
{
    [RequestTypeName( "register" )]
    class RegisterRequest : RequestType
    {
        public override Object Respond( NameValueCollection args )
        {
            if ( args[ "uname" ] == null )
                return new Responses.ErrorResponse( "no username given" );

            if ( args[ "phash" ] == null )
                return new Responses.ErrorResponse( "no password hash given" );

            if ( !Account.IsPasswordHashValid( args[ "phash" ] ) )
                return new Responses.ErrorResponse( "invalid password hash" );

            if ( args[ "email" ] == null )
                return new Responses.ErrorResponse( "no email address given" );

            Account[] clashes = DatabaseManager.Select<Account>( null,
                String.Format( "Username = '{0}' OR Email ='{1}'",
                    args[ "uname" ], args[ "email" ] ) );

            if ( clashes.Length > 0 )
            {
                if ( clashes[ 0 ].Username == args[ "uname" ] )
                    return new Responses.ErrorResponse( "username already taken" );
                else
                    return new Responses.ErrorResponse( "email already in use" );
            }

            Account account = new Account()
            {
                Username = args[ "uname" ],
                PasswordHash = args[ "phash" ].ToCharArray(),
                Email = args[ "email" ],
                Rank = Rank.Unverified,
                RegistrationDate = DateTime.Now
            };

            DatabaseManager.Insert( account );

            return new Responses.AcknowledgeResponse( true );
        }
    }
}
