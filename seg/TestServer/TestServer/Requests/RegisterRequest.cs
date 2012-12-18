using System;
using System.Collections.Generic;
using System.Collections.Specialized;

using TestServer.Entities;

namespace TestServer.Requests
{
    [RequestTypeName( "register" )]
    class RegisterRequest : Request
    {
        public override Responses.Response Respond( NameValueCollection args )
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

            List<Account> clashes = DatabaseManager.Select<Account>( x => x.Username == uname || x.Email == email );

            if ( clashes.Count > 0 )
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

            account = DatabaseManager.Select<Account>( x => x.Email == email )[ 0 ];
            VerificationCode.Create( account ).SendEmail( account );

            return new Responses.Response( true );
        }
    }
}
