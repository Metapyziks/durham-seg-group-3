using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TestServer.Responses
{
    [Serializable]
    class EchoResponse : Response
    {
        [Serialize( "msg" )]
        public readonly String[] Message;

        public EchoResponse( String message )
            : base( true )
        {
            Message = message.Split();
        }
    }
}
