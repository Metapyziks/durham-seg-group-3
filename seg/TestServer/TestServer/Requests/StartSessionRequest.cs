using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using TestServer.Entities;

namespace TestServer.Requests
{
    [RequestTypeName( "session" )]
    class StartSessionRequest : RequestType
    {
        public override Object Respond( NameValueCollection args )
        {
            Account account;
            Object error;

            if ( IsAuthorised( args, out account, out error, false ) )
            {
                AuthSession sess = AuthSession.Create( account );
                return new KeyValuePair<String, Object>( "code", new String( sess.SessionCode ) );
            }

            return error;
        }
    }
}
