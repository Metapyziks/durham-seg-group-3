using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TestServer.Responses
{
    [Serializable]
    class ErrorResponse
    {
        [Serialize( "error" )]
        public readonly String Message;

        public ErrorResponse( String message )
        {
            Message = message;
        }
    }
}
