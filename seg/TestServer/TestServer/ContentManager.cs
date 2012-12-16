using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using Nini.Ini;

namespace TestServer
{
    static class ContentManager
    {
        private class ScriptedPage
        {
            public String Content { get; private set; }

            public ScriptedPage( String content )
            {
                Update( content );
            }

            public void Update( String content )
            {
                Content = content;
            }
        }

        private static String _sContentDirectory;
        private static List<String> _sAllowedExtensions;

        private static FileSystemWatcher _watcher;

        private static Dictionary<String, ScriptedPage> _sPages;
        private static Dictionary<String, byte[]> _sContent;

        public static void Initialize( IniSection ini )
        {
            _sPages = new Dictionary<string, ScriptedPage>();
            _sContent = new Dictionary<string, byte[]>();


            _sContentDirectory = Path.GetFullPath( ini.GetValue( "pagesdir" ) ?? "Content" );
            _sAllowedExtensions = ( ini.GetValue( "allowedext" ) ?? "" ).Split( ',' ).ToList();

            for ( int i = 0; i < _sAllowedExtensions.Count; ++i )
                _sAllowedExtensions[i] = _sAllowedExtensions[i].Trim().ToLower();

            Console.WriteLine( "Initializing content..." );

            InitializeDirectory( _sContentDirectory );

            _watcher = new FileSystemWatcher( _sContentDirectory );
            _watcher.Created += ( sender, e ) =>
            {
                Console.WriteLine( "Content created" );
                UpdateFile( e.FullPath );
            };
            _watcher.Changed += ( sender, e ) =>
            {
                Console.WriteLine( "Content updated" );
                UpdateFile( e.FullPath );
            };
            _watcher.Renamed += ( sender, e ) =>
            {
                Console.WriteLine( "Content renamed" );
                UpdateFile( e.OldFullPath );
                UpdateFile( e.FullPath );
            };
            _watcher.Deleted += ( sender, e ) =>
            {
                Console.WriteLine( "Content removed" );
                UpdateFile( e.FullPath );
            };
            _watcher.NotifyFilter = NotifyFilters.LastWrite | NotifyFilters.FileName | NotifyFilters.DirectoryName;
            _watcher.IncludeSubdirectories = true;
            _watcher.EnableRaisingEvents = true;
        }

        private static void InitializeDirectory( string dir, int depth = 0 )
        {
            String dirName = "Content" + dir.Substring( _sContentDirectory.Length );
            Console.WriteLine( dirName );

            foreach ( String file in Directory.EnumerateFiles( dir ) )
                UpdateFile( file, depth + 1 );

            foreach ( String subDir in Directory.EnumerateDirectories( dir ) )
                InitializeDirectory( subDir, depth + 1 );
        }

        private static String FormatPath( String path )
        {
            return path.Substring( _sContentDirectory.Length ).Replace( '\\', '/' );
        }

        private static void UpdateFile( String path, int depth = 0 )
        {
            String ext = Path.GetExtension( path ).ToLower();
            if ( ext == ".html" )
                UpdatePage( path, depth );
            else if ( _sAllowedExtensions.Contains( ext ) )
                UpdateContent( path, depth );
        }

        private static void UpdatePage( String path, int depth = 0 )
        {
            String formatted = FormatPath( path );

            if ( !_sPages.ContainsKey( formatted ) )
            {
                _sPages.Add( formatted, new ScriptedPage( File.ReadAllText( path ) ) );
                Console.Write( "+ ".PadLeft( 2 + depth * 2 ) );
            }
            else
            {
                if ( File.Exists( path ) )
                {
                    Console.Write( "= ".PadLeft( 2 + depth * 2 ) );
                    DateTime start = DateTime.Now;
                    while ( ( DateTime.Now - start ).TotalSeconds < 1.0 )
                    {
                        try
                        {
                            _sPages[formatted].Update( File.ReadAllText( path ) );
                            break;
                        }
                        catch ( IOException e )
                        {
                            Thread.Sleep( 10 );
                        }
                    }
                }
                else
                {
                    Console.Write( "- ".PadLeft( 2 + depth * 2 ) );
                    _sPages.Remove( formatted );
                }
            }

            Console.ForegroundColor = ConsoleColor.Cyan;
            Console.WriteLine( formatted );
            Console.ForegroundColor = ConsoleColor.Gray;
        }

        private static void UpdateContent( String path, int depth = 0 )
        {
            String formatted = FormatPath( path );

            if ( !_sContent.ContainsKey( formatted ) )
            {
                _sContent.Add( formatted, File.ReadAllBytes( path ) );
                Console.Write( "+ ".PadLeft( 2 + depth * 2 ) );
            }
            else
            {
                if ( File.Exists( path ) )
                {
                    Console.Write( "= ".PadLeft( 2 + depth * 2 ) );
                    DateTime start = DateTime.Now;
                    while ( ( DateTime.Now - start ).TotalSeconds < 1.0 )
                    {
                        try
                        {
                            _sContent[formatted] = File.ReadAllBytes( path );
                            break;
                        }
                        catch ( IOException e )
                        {
                            Thread.Sleep( 10 );
                        }
                    }
                }
                else
                {
                    Console.Write( "- ".PadLeft( 2 + depth * 2 ) );
                    _sContent.Remove( formatted );
                }
            }

            Console.ForegroundColor = ConsoleColor.Yellow;
            Console.WriteLine( formatted );
            Console.ForegroundColor = ConsoleColor.Gray;
        }

        public static void ServeRequest( String request, Stream stream )
        {
            String path = request;
            int pathEnd = path.IndexOf( '?' );
            if ( pathEnd == -1 )
                pathEnd = path.Length;
            path = path.Substring( 0, pathEnd );

            if ( path.Length == 0 )
                path = "index.html";

            if ( !path.Contains( '.' ) )
                path += ".html";

            if ( path.EndsWith( ".html" ) )
            {
                if ( _sPages.ContainsKey( path ) )
                {
                    StreamWriter writer = new StreamWriter( stream );
                    writer.WriteLine( _sPages[path].Content );
                    writer.Flush();

                    return;
                }
            }

            if ( _sContent.ContainsKey( path ) )
            {
                byte[] content = _sContent[path];
                stream.Write( content, 0, content.Length );
                return;
            }
            
            if ( request != "404.html" )
                ServeRequest( "404.html", stream );
        }
    }
}
