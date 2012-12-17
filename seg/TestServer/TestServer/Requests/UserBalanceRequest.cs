﻿using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Linq;
using System.Text;

using TestServer.Entities;

namespace TestServer.Requests
{
    [RequestTypeName( "balance" )]
    class UserBalanceRequest : Request
    {
        public override Responses.Response Respond( NameValueCollection args )
        {
            Account acc;
            Responses.ErrorResponse error;

            if( !this.CheckAuth( args, out acc, out error, true ) )
                return error;

            Player ply = Player.GetPlayer( acc );

            return new Responses.UserBalanceResponse( ply );
        }
    }
}
