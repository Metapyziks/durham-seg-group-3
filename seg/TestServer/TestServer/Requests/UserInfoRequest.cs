using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Linq;
using System.Text;

using TestServer.Entities;

namespace TestServer.Requests
{
    [RequestTypeName( "userinfo" )]
    class UserInfoRequest : Request
    {
        public override Responses.Response Respond( NameValueCollection args )
        {
            String[] usernames = null;

            if ( args[ "uname" ] != null )
                usernames = new String[] { args[ "uname" ] };
            else if ( args[ "unames" ] != null )
                usernames = args[ "unames" ].Split( new char[] { ',' },
                    StringSplitOptions.RemoveEmptyEntries );

            if ( usernames == null || usernames.Length == 0 )
                return new Responses.ErrorResponse( "no username given" );

            for ( int i = 0; i < usernames.Length; ++i )
            {
                usernames[ i ] = usernames[ i ].Trim();

                if ( !Account.IsUsernameValid( usernames[ i ] ) )
                    return new Responses.ErrorResponse( "invalid username (#{0})", i + 1 );

                usernames[ i ] = String.Format( "Username = '{0}'", usernames[ i ] );
            }

            Account[] users = DatabaseManager.Select<Account>(
                null, String.Join( " OR ", usernames ) );

            return new Responses.UserInfoResponse( users );
        }
    }
}
