﻿using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;

using TestServer.Requests;

namespace TestServer
{
    static class APIManager
    {
        public static void ServeRequest( HttpListenerContext context )
        {
            String url = context.Request.RawUrl;
            int pathEnd = url.IndexOf( '?' );
            if ( pathEnd == -1 )
                pathEnd = url.Length;

            String requestTypeString = url.Substring( 1, pathEnd - 1 );

            requestTypeString = requestTypeString.Substring( 4 );
            Request requestType = Request.Get( requestTypeString );

            object response;
            if ( requestType != null )
                response = requestType.Respond( context.Request.QueryString );
            else
                response = new Responses.ErrorResponse( "invalid request type (" + requestTypeString + ")" );

            String obj = JSONSerializer.Serialize( response );
            StreamWriter writer = new StreamWriter( context.Response.OutputStream );
            writer.WriteLine( obj );
            writer.Flush();
        }
    }
}