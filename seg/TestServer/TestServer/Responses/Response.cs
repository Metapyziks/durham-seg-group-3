using System;

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
