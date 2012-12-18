using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;

namespace TestServer.Entities
{
    public enum Rank : byte
    {
        Unverified = 0,
        Verified = 1,
        Admin = 3,
        Owner = 7
    }

    [Serializable, DatabaseEntity]
    public class Account
    {
        private static readonly Regex stUsernameRegex;
        private static readonly Regex stEmailRegex;
        private static readonly Regex stPasswordHashRegex;

        static Account()
        {
            stUsernameRegex = new Regex( "^[a-zA-Z0-9_-]([ a-zA-Z0-9_-]{1,31})$" );
            stEmailRegex = new Regex( "^[a-z0-9._%-]+@[a-z0-9.-]+\\.[a-z]{2,4}$" );
            stPasswordHashRegex = new Regex( "^[0-9a-f]{32}$" );
        }

        public static bool IsUsernameValid( String username )
        {
            return stUsernameRegex.IsMatch( username );
        }

        public static bool IsEmailValid( String email )
        {
            if ( email.Length > 64 )
                return false;

            return stEmailRegex.IsMatch( email );
        }

        public static bool IsPasswordHashValid( String hash )
        {
            return stPasswordHashRegex.IsMatch( hash );
        }

        [Serialize( "accountid" )]
        [PrimaryKey, AutoIncrement]
        public int AccountID { get; private set; }

        [Serialize( "uname" )]
        [Capacity( 32 ), Unique]
        public String Username { get; set; }

        [Capacity( 32 ), FixedLength, NotNull]
        public char[] PasswordHash { get; set; }

        [Capacity( 64 ), Unique]
        public String Email { get; set; }

        [Serialize( "joindate" )]
        [NotNull]
        public DateTime RegistrationDate { get; set; }

        [Serialize( "rank" )]
        [NotNull]
        public Rank Rank { get; set; }

        public bool IsVerified
        {
            get { return ( Rank & Rank.Verified ) != 0; }
        }

        public bool IsAdmin
        {
            get { return ( Rank & Rank.Admin ) != 0; }
        }

        public bool IsOwner
        {
            get { return ( Rank & Rank.Owner ) != 0; }
        }
    }
}
