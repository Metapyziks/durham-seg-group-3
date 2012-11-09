using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TestServer.Responses
{
    [Serializable]
    class UserInfoResponse
    {
        [Serialize( "users" )]
        public readonly Account[] Users;

        public UserInfoResponse( Account[] users )
        {
            Users = users;
        }
    }
}
