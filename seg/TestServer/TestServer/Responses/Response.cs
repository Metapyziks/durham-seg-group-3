using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TestServer.Responses
{
    [Serializable]
    class Response
    {
        [Serialize( "success" )]
        public readonly bool Success;

        public Response( bool success )
        {
            Success = success;
        }
    }
}
