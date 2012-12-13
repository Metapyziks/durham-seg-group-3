using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using TestServer.Entities;

namespace TestServer.Responses
{
    [Serializable]
    class UserInfoResponse : AcknowledgeResponse
    {
        [Serialize( "users" )]
        public readonly Account[] Users;

        public UserInfoResponse( Account[] users )
            : base( true )
        {
            Users = users;
        }
    }
}
