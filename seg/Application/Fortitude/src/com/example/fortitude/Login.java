package com.example.fortitude;

import java.security.MessageDigest;
import java.math.BigInteger;
import java.util.ArrayList;

//class to contain static methods for all actions concerning logins and
//user account creation
public class Login
{
	////////
	//
	//Constructor
	//
	////////
	public Login()
	{

	}

	////////
	//
	//getUser
	//
	//retrieves an instance of the User class containing information on the
	//username specified. If the user does not exist or there is an error
	//it returns null;
	//
	////////
	public static User getUser(String uname)
	{
		//ArrayList<User> users = ServerRequests.getUserInfo(uname); 
		//if(users == null)
		//{
		//	return null;
		//}
		//if(users.size() != 1)
		//{
		//	return null;
		//}
		//return users.get(0);
		return null;
	}

	////////
	//
	//initialLogIn
	//
	//method to log in and retrieve a session ID
	//
	////////
	public static void initialLogIn(String uname, String password)
	{
		password = hashPassword(password);
		ServerRequests.createSession(uname, password, true);
	}  
	
	////////
	//
	//logIn
	//
	//method to log in and retrieve a session ID
	//
	////////
	public static void logIn(String uname, String password)
	{
		password = hashPassword(password);
		ServerRequests.createSession(uname, password, false);
	}  

	////////
	//
	//createUser
	//
	//method to create a user account on the server. takes a username, password
	//and email address and hashes the password with a MD5 hash. This method
	//assumes that the username, password and email have been validated.
	//
	////////
	public static void createUser(String uname, String password, String email)
	{
		password = hashPassword(password);
		ServerRequests.registerUser(uname, password, email);
	}

	////////
	//
	//hashPassword
	//
	//hashes and returns a given password.
	//
	////////
	private static String hashPassword(String password)
	{
		try
		{
			password = "7�`j8,#@ytIsQ9$Od" + password + "%fI4\"0lPQz^~U&An4:9�k";
			MessageDigest m = MessageDigest.getInstance("MD5");
		        byte[] digest = m.digest(password.getBytes("UTF-8"));
		        StringBuffer builder = new StringBuffer();
		        for(int i = 0; i < digest.length; ++i) {
		            builder.append(Integer.toHexString((digest[i] & 0xFF) | 0x100).substring(1, 3));
		        }
        		return builder.toString();
		}
		catch(Exception e)
		{
			System.out.println("Error When Hashing Password: " + e);
			return null;
		}
	}
}

