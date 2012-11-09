using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Reflection;

namespace TestServer
{
    class RequestTypeNameAttribute : Attribute
    {
        public readonly String Name;

        public RequestTypeNameAttribute( String name )
        {
            Name = name;
        }
    }

    abstract class RequestType
    {
        private static Dictionary<String, RequestType> stRegistered;

        static RequestType()
        {
            stRegistered = new Dictionary<String, RequestType>();

            foreach ( Type type in Assembly.GetExecutingAssembly().GetTypes() )
            {
                if ( type.BaseType == typeof( RequestType ) )
                {
                    ConstructorInfo cons = type.GetConstructor( new Type[ 0 ] );
                    RequestType inst = (RequestType) cons.Invoke( new object[ 0 ] );

                    String name;
                    if ( type.IsDefined( typeof( RequestTypeNameAttribute ) ) )
                        name = type.GetCustomAttribute<RequestTypeNameAttribute>().Name;
                    else
                    {
                        name = type.Name.ToLower();
                        if ( name.EndsWith( "request" ) )
                            name = name.Substring( 0, name.Length - ( "request" ).Length );
                    }

                    stRegistered.Add( name, inst );
                }
            }
        }

        public static void Register( String name, RequestType type )
        {
            stRegistered.Add( name, type );
        }

        public static RequestType Get( String name )
        {
            try
            {
                return stRegistered[ name ];
            }
            catch ( KeyNotFoundException )
            {
                return null;
            }
        }

        public abstract Object Respond( NameValueCollection args );
    }
}
