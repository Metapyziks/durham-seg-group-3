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
            String uname = args[ "uname" ];
            String phash = args[ "phash" ].ToLower();
            String email = args[ "email" ].ToLower();

            if ( uname == null || uname.Length == 0 )
                return new Responses.ErrorResponse( "no username given" );

            if ( !Account.IsUsernameValid( uname ) )
                return new Responses.ErrorResponse( "invalid username" );

            if ( phash == null || phash.Length == 0 )
                return new Responses.ErrorResponse( "no password hash given" );

            if ( !Account.IsPasswordHashValid( phash ) )
                return new Responses.ErrorResponse( "invalid password hash" );

            if ( email == null || email.Length == 0 )
                return new Responses.ErrorResponse( "no email address given" );

            if ( !Account.IsEmailValid( email ) )
                return new Responses.ErrorResponse( "invalid email address" );

            Account[] clashes = DatabaseManager.Select<Account>( null,
                String.Format( "Username = '{0}' OR Email ='{1}'", uname, email ) );

            if ( clashes.Length > 0 )
            {
                if ( clashes[ 0 ].Username == uname )
                    return new Responses.ErrorResponse( "username already in use" );
                else
                    return new Responses.ErrorResponse( "email already in use" );
            }

            Account account = new Account()
            {
                Username = uname,
                PasswordHash = phash.ToCharArray(),
                Email = email,
                Rank = Rank.Unverified,
                RegistrationDate = DateTime.Now
            };

            DatabaseManager.Insert( account );

            account = DatabaseManager.Select<Account>( null, "Email='" + account.Email + "'" )[ 0 ];
            VerifyRequest verify = VerifyRequest.Create( account );
            DatabaseManager.Insert( verify );

            verify.SendEmail( account );

            return new Responses.AcknowledgeResponse( true );
        }
    }
}
