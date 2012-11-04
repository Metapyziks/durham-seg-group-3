using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TestServer.Responses
{
    [Serializable]
    class EchoResponse
    {
        [Serialize( "msg" )]
        public readonly String[] Message;

        public EchoResponse( String message )
        {
            Message = message.Split();
        }
    }
}
