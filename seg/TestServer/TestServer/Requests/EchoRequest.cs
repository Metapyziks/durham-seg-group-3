using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TestServer.Requests
{
    [RequestTypeName( "echo" )]
    class EchoRequest : RequestType
    {
        public override Responses.Response Respond( NameValueCollection args )
        {
            if ( args[ "msg" ] != null )
                return new Responses.EchoResponse( args[ "msg" ] );

            return new Responses.EchoResponse( "null" );
        }
    }
}
