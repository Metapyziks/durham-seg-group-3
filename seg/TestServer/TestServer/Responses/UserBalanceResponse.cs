using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using TestServer.Entities;

namespace TestServer.Responses
{
    [Serializable]
    class UserBalanceResponse : Response
    {
        [Serialize( "balance" )]
        public readonly int Balance;

        public UserBalanceResponse( Player ply )
            : base( true )
        {
            Balance = ply.Balance;
        }
    }
}
