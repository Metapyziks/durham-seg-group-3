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
    }
}
