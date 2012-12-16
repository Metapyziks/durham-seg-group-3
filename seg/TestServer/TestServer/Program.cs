using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

using Nini.Ini;

namespace TestServer
{
    class Program
    {
        public static int LocalPort = 80;
        public static String ServerAddress = null;

        static bool stActive;

        static void Main( String[] args )
        {
            stActive = true;

            if ( !File.Exists( "config.ini" ) )
                Console.WriteLine( "WARNING: config.ini not found, some features may not function" );
            else
            {
                IniDocument ini = new IniDocument( "config.ini" );
                IniSection general = ini.Sections["general"];
                ServerAddress = general.GetValue( "address" );
                int.TryParse( general.GetValue( "localport" ), out LocalPort );

                EmailManager.CreateClient( ini.Sections["smtp"] );

                ContentManager.Initialize( ini.Sections["webserver"] );
            }

            DatabaseManager.Connect();

            Thread clientThread = new Thread( async () =>
            {
                HttpListener listener = new HttpListener();
                listener.Prefixes.Add( "http://+:" + LocalPort + "/" );
                listener.Start();

                Console.WriteLine( "Http server started and ready for requests" );

                while ( stActive )
                {
                    Task<HttpListenerContext> ctxTask = listener.GetContextAsync();

                    while ( !ctxTask.IsCompleted && stActive ) ;

                    if ( !stActive )
                        break;

                    ProcessRequest( await ctxTask );
                }
            } );

            clientThread.Start();

            while ( stActive )
            {
                String[] line = Console.ReadLine().Split( new char[] { ' ', '\t' }, StringSplitOptions.RemoveEmptyEntries );
                if ( line.Length > 0 )
                    ProcessCommand( line[0].ToLower(), line.Where( ( x, i ) => i > 0 ).ToArray() );
            }

            clientThread.Abort();

            while ( clientThread.IsAlive )
                Thread.Sleep( 10 );

            DatabaseManager.Disconnect();
        }

        static void ProcessCommand( String command, String[] args )
        {
            switch ( command )
            {
                case "stop":
                    stActive = false;
                    break;
            }
        }

        static void ProcessRequest( HttpListenerContext context )
        {
            Console.WriteLine( "Request from " + context.Request.RemoteEndPoint.Address.MapToIPv4().ToString() + " : " + context.Request.RawUrl );

#if DEBUG
#else
            try
            {
#endif
                if ( context.Request.HttpMethod == "GET" )
                {
                    String url = context.Request.RawUrl;
                    int pathEnd = url.IndexOf( '?' );
                    if ( pathEnd == -1 )
                        pathEnd = url.Length;

                    String requestTypeString = url.Substring( 1, pathEnd - 1 );

                    if ( requestTypeString.StartsWith( "api/" ) )
                    {
                        requestTypeString = requestTypeString.Substring( 4 );
                        RequestType requestType = RequestType.Get( requestTypeString );
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
                    else
                    {
                        ContentManager.ServeRequest( context.Request.RawUrl, context.Response.OutputStream );
                    }
                }
#if DEBUG
#else
            }
            catch ( Exception e )
            {
                Console.WriteLine( e.GetType().Name + " thrown" );
            }
            finally
            {
#endif
                context.Response.OutputStream.Close();
#if DEBUG
#else
            }
#endif
        }
    }
}
