﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace TestServer
{
    enum Rank : byte
    {
        Unverified = 0,
        Verified = 1,
        Admin = 3,
        Owner = 7
    }

    [Serializable]
    [DatabaseEntity( "Account", Account.Fields.AccountID,
        Account.Fields.AccountID,
        Account.Fields.Username,
        Account.Fields.PasswordHash,
        Account.Fields.Email,
        Account.Fields.RegistrationDate,
        Account.Fields.Rank
    )]
    class Account : IDatabaseEntity
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

        public struct Fields
        {
            public const String AccountID = "AccountID";
            public const String Username = "Username";
            public const String PasswordHash = "PasswordHash";
            public const String Email = "Email";
            public const String RegistrationDate = "RegistrationDate";
            public const String Rank = "Rank";
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
            if ( hash.Length != 32 )
                return false;

            for ( int i = 0; i < 32; ++i )
            {
                if ( char.IsNumber( hash[ i ] ) )
                    continue;

                if ( (int) hash[ i ] < 0x61 || (int) hash[ i ] >= 0x67 )
                    return false;
            }

            return true;
        }

        [Serialize( "accountid" )]
        public int AccountID;
        [Serialize( "uname" )]
        public String Username;
        public char[] PasswordHash;
        public String Email;
        [Serialize( "regdate" )]
        public DateTime RegistrationDate;
        [Serialize( "rank" )]
        public Rank Rank;

        public bool IsVerified
        {
            get { return Rank.HasFlag( Rank.Verified ); }
        }

        public bool IsAdmin
        {
            get { return Rank.HasFlag( Rank.Admin ); }
        }

        public bool IsOwner
        {
            get { return Rank.HasFlag( Rank.Owner ); }
        }

        public string GetField( string fieldName )
        {
            switch ( fieldName )
            {
                case Fields.AccountID:
                    return AccountID.ToString();
                case Fields.Username:
                    return Username;
                case Fields.PasswordHash:
                    return String.Join( "", PasswordHash );
                case Fields.Email:
                    return Email;
                case Fields.RegistrationDate:
                    return RegistrationDate.ToString();
                case Fields.Rank:
                    return ( (byte) Rank ).ToString();
                default:
                    return "";
            }
        }

        public void SetField( string fieldName, object value )
        {
            switch ( fieldName )
            {
                case Fields.AccountID:
                    AccountID = Convert.ToInt32( value ); break;
                case Fields.Username:
                    Username = Convert.ToString( value ); break;
                case Fields.PasswordHash:
                    PasswordHash = Convert.ToString( value ).ToCharArray(); break;
                case Fields.Email:
                    Email = Convert.ToString( value ); break;
                case Fields.RegistrationDate:
                    RegistrationDate = Convert.ToDateTime( value ); break;
                case Fields.Rank:
                    Rank = (Rank) Convert.ToByte( value ); break;
            }
        }
    }
}
