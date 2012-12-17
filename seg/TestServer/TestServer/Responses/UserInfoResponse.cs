using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using TestServer.Entities;

namespace TestServer.Responses
{
    [Serializable]
    class UserInfoResponse : Response
    {
        [Serialize( "users" )]
        public readonly IEnumerable<Account> Users;

        public UserInfoResponse( IEnumerable<Account> users )
            : base( true )
        {
            Users = users;
        }
    }
}
