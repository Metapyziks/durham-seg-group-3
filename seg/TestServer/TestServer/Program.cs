﻿using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading;

using Nini.Ini;

using TestServer.Entities;
using TestServer.Requests;

namespace TestServer
{
    class Program
    {
        public static int LocalPort = 80;
        public static String ServerAddress = null;

        static bool stActive;

        static void Main( String[] args )
        {
            Console.Title = "Fortitude Server Prototype";

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

            Thread clientThread = new Thread( () =>
            {
                HttpListener listener = new HttpListener();
                listener.Prefixes.Add( "http://+:" + LocalPort + "/" );
                listener.Start();

                Console.WriteLine( "Http server started and ready for requests" );

                while ( stActive )
                    ProcessRequest( listener.GetContext() );

                listener.Stop();
            } );

            clientThread.Start();

            while ( stActive )
            {
                String[] line = Console.ReadLine().Split( new char[] { ' ', '\t' }, StringSplitOptions.RemoveEmptyEntries );
                if ( line.Length > 0 )
                {
#if DEBUG
#else
                    try
                    {
#endif
                        ProcessCommand( line[0].ToLower(), line.Where( ( x, i ) => i > 0 ).ToArray() );
#if DEBUG
#else
                    }
                    catch ( Exception e )
                    {
                        Console.ForegroundColor = ConsoleColor.Red;
                        Console.WriteLine( e.GetType().Name + " thrown: " + e.Message );
                        Console.ForegroundColor = ConsoleColor.Gray;
                    }
#endif
                }
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
                case "activate":
                    if ( args.Length == 0 )
                        throw new ArgumentException( "Expected a user name" );

                    String username = args[0];

                    Account[] accounts = DatabaseManager.Select<Account>( null,
                        String.Format( "Username = '{0}'", username ) );

                    if ( accounts.Length == 0 )
                        throw new Exception( "username not recognised" );

                    if ( accounts[ 0 ].IsVerified )
                        throw new Exception( "account already activated" );

                    VerificationCode request = VerificationCode.Get( accounts[ 0 ] );
                    
                    if( request != null )
                        request.Remove();

                    accounts[ 0 ].Rank = Rank.Verified;
                    DatabaseManager.Update( accounts[ 0 ] );
                    break;
            }
        }

        static void ProcessRequest( HttpListenerContext context )
        {
            Console.WriteLine( "Request from " + context.Request.RemoteEndPoint.ToString() + " : " + context.Request.RawUrl );

#if DEBUG
#else
            try
            {
#endif
                if ( context.Request.HttpMethod == "GET" )
                {
                    if ( context.Request.RawUrl.StartsWith( "/api/" ) )
                        APIManager.ServeRequest( context );
                    else
                        ContentManager.ServeRequest( context );
                }
#if DEBUG
#else
            }
            catch ( Exception e )
            {
                Console.ForegroundColor = ConsoleColor.Red;
                Console.WriteLine( e.GetType().Name + " thrown: " + e.Message );
                Console.ForegroundColor = ConsoleColor.Gray;
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
