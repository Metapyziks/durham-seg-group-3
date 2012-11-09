using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Mail;
using System.Text;
using System.Threading.Tasks;

using Nini.Ini;

namespace TestServer
{
    static class EmailManager
    {
        private static SmtpClient stClient;
        private static String stAdminEmail;

        public static bool IsAvaliable
        {
            get { return stClient != null; }
        }

        public static void CreateClient( IniSection settings )
        {
            CreateClient( settings.GetValue( "server" ),
                Int32.Parse( settings.GetValue( "port" ) ),
                settings.GetValue( "address" ),
                settings.GetValue( "password" ) );
        }

        public static void CreateClient( String smtpAddress, int port, String email, String password )
        {
            stAdminEmail = email;

            String username = email.Substring( 0, email.IndexOf( '@' ) );

            stClient = new SmtpClient( smtpAddress, port )
            {
                Credentials = new NetworkCredential( username, password ),
                EnableSsl = true
            };
        }

        public static void Send( String to, String subject, String message )
        {
            stClient.Send( stAdminEmail, to, subject, message );
        }
    }
}
