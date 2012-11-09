using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;

namespace TestServer
{
    static class Tools
    {
        public static readonly DateTime UnixEpoch =
            new DateTime( 1970, 1, 1, 0, 0, 0, DateTimeKind.Utc );

        public static bool IsDefined<T>( this MemberInfo minfo, bool inherit = false )
            where T : Attribute
        {
            return minfo.IsDefined( typeof( T ), inherit );
        }

        public static T GetCustomAttribute<T>( this MemberInfo minfo, bool inherit = false )
            where T : Attribute
        {
            T[] attribs = (T[]) minfo.GetCustomAttributes( typeof( T ), inherit );

            if ( attribs.Length == 0 )
                return null;

            return attribs[ 0 ];
        }

        public static bool IsNumerical( this Object obj )
        {
            return ( obj is Byte || obj is SByte ||
                obj is UInt16 || obj is Int16 ||
                obj is UInt32 || obj is Int32 ||
                obj is UInt64 || obj is Int64 ||
                obj is Single || obj is Double || obj is Decimal );
        }
    }
}
