using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TestServer.Responses
{
    [Serializable]
    class ErrorResponse : Response
    {
        [Serialize( "error" )]
        public readonly String Message;

        public ErrorResponse( String message )
            : base( false )
        {
            Message = message;
        }

        public ErrorResponse( String format, params Object[] args )
            : base( false )
        {
            Message = String.Format( format, args );
        }
    }
}
