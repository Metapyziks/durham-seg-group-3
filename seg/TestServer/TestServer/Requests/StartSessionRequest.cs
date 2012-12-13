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
        public override Responses.Response Respond( NameValueCollection args )
        {
            Account account;
            Responses.ErrorResponse error;

            if ( CheckAuth( args, out account, out error, false ) )
            {
                AuthSession sess = AuthSession.Create( account );
                return new Responses.SessionInfoResponse(
                    new String( sess.SessionCode ) );
            }

            return error;
        }
    }
}
