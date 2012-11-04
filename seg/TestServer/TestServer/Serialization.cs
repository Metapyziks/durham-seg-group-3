using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Reflection;

namespace TestServer
{
    class SerializeAttribute : Attribute
    {
        public readonly String KeyName;

        public SerializeAttribute( String keyName )
        {
            KeyName = keyName;
        }
    }

    public static class JSONSerializer
    {
        public static String Serialize( Object obj )
        {
            StringBuilder builder = new StringBuilder();
            Serialize( obj, builder );
            return builder.ToString();
        }

        public static void Serialize( Object obj, StringBuilder builder )
        {
            if ( obj == null )
                builder.Append( "null" );

            if ( obj is string )
            {
                builder.AppendFormat( "\"{0}\"", ( (string) obj ).Replace( "\"", "\\\"" ) );
                return;
            }

            if ( obj is Array )
            {
                builder.Append( "[" );
                bool first = true;
                foreach ( Object o in (Array) obj )
                {
                    if ( !first )
                        builder.Append( "," );
                    else
                        first = false;

                    Serialize( o, builder );
                }
                builder.Append( "]" );
                return;
            }

            Type type = obj.GetType();
            if ( type.IsDefined( typeof( SerializableAttribute ) ) )
            {
                builder.Append( "{" );
                bool first = true;
                foreach ( FieldInfo field in type.GetFields() )
                {
                    if ( field.IsDefined( typeof( SerializeAttribute ), true ) )
                    {
                        if ( !first )
                            builder.Append( "," );
                        else
                            first = false;

                        SerializeAttribute attrib = field.GetCustomAttribute<SerializeAttribute>( true );
                        builder.AppendFormat( "\"{0}\":", attrib.KeyName );
                        Serialize( field.GetValue( obj ), builder );
                    }
                }
                builder.Append( "}" );
            }
            else
            {
                builder.Append( obj.ToString() );
            }
        }
    }
}
