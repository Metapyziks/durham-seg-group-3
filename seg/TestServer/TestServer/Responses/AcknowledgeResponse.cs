using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TestServer.Responses
{
    [Serializable]
    class AcknowledgeResponse
    {
        [Serialize( "success" )]
        public readonly bool Success;

        public AcknowledgeResponse( bool success )
        {
            Success = success;
        }
    }
}
