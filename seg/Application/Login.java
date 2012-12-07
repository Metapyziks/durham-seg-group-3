import java.security.MessageDigest;
import java.math.BigInteger;

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
    //createUser
    //
    //method to create a user account on the server. takes a username, password
    //and email address and hashes the password with a MD5 hash. This method
    //assumes that the username, password and email have been validated.
    //
    ////////
    public static boolean createUser(String uname, String password, String email)
    {
	try
	{
            MessageDigest m = MessageDigest.getInstance("MD5");
	    m.update(password.getBytes(), 0, password.length());
            password = new BigInteger(1, m.digest()).toString(16);
	    return ServerRequests.registerUser(uname, password, email);
	}
	catch(Exception e)
	{
            Messenger.printMessage("Error When Creating User");
	    return false;
	}
    }
}
