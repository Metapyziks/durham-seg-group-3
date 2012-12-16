using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Nini.Ini;

namespace TestServer
{
    static class ContentManager
    {
        private class ScriptedPage
        {
            public readonly String Content;

            public ScriptedPage( String content )
            {
                Content = content;
            }
        }

        private static String _sContentDirectory;
        private static List<String> _sAllowedExtensions;

        private static Dictionary<String, ScriptedPage> _sPages;
        private static Dictionary<String, byte[]> _sContent;

        public static void Initialize( IniSection ini )
        {
            _sPages = new Dictionary<string, ScriptedPage>();
            _sContent = new Dictionary<string, byte[]>();

            _sContentDirectory = ini.GetValue( "pagesdir" ) ?? "Content";
            _sAllowedExtensions = ( ini.GetValue( "allowedext" ) ?? "" ).Split( ',' ).ToList();

            for ( int i = 0; i < _sAllowedExtensions.Count; ++i )
                _sAllowedExtensions[i] = _sAllowedExtensions[i].Trim().ToLower();

            Console.WriteLine( "Initializing content..." );

            InitializeDirectory( _sContentDirectory );
        }

        private static void InitializeDirectory( string dir, int depth = 0 )
        {
            Console.ForegroundColor = ConsoleColor.Gray;
            Console.WriteLine( "- {0}", dir );

            foreach ( String file in Directory.EnumerateFiles( dir ) )
            {
                String ext = Path.GetExtension( file ).ToLower();
                if ( ext == ".html" )
                    InitializePage( file, depth + 1 );
                else if ( _sAllowedExtensions.Contains( ext ) )
                    InitializeContent( file, depth + 1 );
            }

            foreach ( String subDir in Directory.EnumerateDirectories( dir ) )
                InitializeDirectory( subDir, depth + 1 );

            Console.ForegroundColor = ConsoleColor.Gray;
        }

        private static String FormatPath( String path )
        {
            return path.Substring( _sContentDirectory.Length + 1 ).Replace( '\\', '/' );
        }

        private static void InitializePage( String path, int depth = 0 )
        {
            String fileName = Path.GetFileName( path );

            Console.ForegroundColor = ConsoleColor.Cyan;
            Console.WriteLine( fileName.PadLeft( fileName.Length + depth * 2 ) );

            _sPages.Add( FormatPath( path ), new ScriptedPage( File.ReadAllText( path ) ) );
        }

        private static void InitializeContent( String path, int depth = 0 )
        {
            String fileName = Path.GetFileName( path );

            Console.ForegroundColor = ConsoleColor.Yellow;
            Console.WriteLine( fileName.PadLeft( fileName.Length + depth * 2 ) );

            _sContent.Add( FormatPath( path ), File.ReadAllBytes( path ) );
        }

        public static void ServeRequest( String request, Stream stream )
        {
            String path = request.TrimStart( '/' );

            if ( path.EndsWith( ".html" ) )
            {
                if ( _sPages.ContainsKey( path ) )
                {
                    StreamWriter writer = new StreamWriter( stream );
                    writer.WriteLine( _sPages[path].Content );
                    writer.Flush();
                }

                return;
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
