using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TestServer.Responses
{
    [Serializable]
    class SessionInfoResponse : Response
    {
        [Serialize( "code" )]
        public readonly String Code;

        public SessionInfoResponse( String code )
            : base( true )
        {
            Code = code;
        }
    }
}
