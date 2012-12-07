import java.net.URL;
import java.net.URLConnection;
import java.lang.StringBuilder;
import java.net.URLEncoder;
import java.net.ConnectException;
import org.json.*;
import java.util.ArrayList;

//A class that handles all requests to the server through static methods.
public class ServerRequests
{
    ////////
    //
    //Constructor
    //
    ////////
    public ServerRequests()
    {

    }

    ////////
    //
    //getUserInfo
    //
    //used to retrieve the user info of one or more users, returns an arraylist
    //of the User class with the fields filled from the returned JSON.
    //The "usernames" parameter is a list of usernames separated by commas.
    //
    ////////
    public static ArrayList<User> getUserInfo(String usernames)
    {
        try
	{
            String ServerIP = Settings.getServerIP();
	    if(ServerIP == null)
	    {
		Messenger.printMessage("Unable To Retrieve Setting 'getServerIP'");
                return null;
	    }
            JSONObject response = makeGetRequest("http://" + ServerIP + "/api/userinfo?uname=" + usernames);
	    if(response != null)
	    {
                if(response.get("users") != null)
		{
	            ArrayList<User> users = new ArrayList<User>();
                    for(int i = 0; i < response.get("users").length(); i++)
		    {
			String accountid = Integer.toString(response.get("users").get(i).get("accountid").asInteger());
			String uname = response.get("users").get(i).get("uname").asString();
			String joindate = Integer.toString(response.get("users").get(i).get("joindate").asInteger());
			String rank = response.get("users").get(i).get("rank").asString();
                        User u = new User(accountid, uname, joindate, rank);
			users.add(u);
		    }
		    return users;
		}
		else
		{
                    if(response.get("error") != null)
		    {
                        Messenger.printMessage("Unable To Get User Info: " + response.get("error"));
			return null;
		    }
		    else
		    {
                         Messenger.printMessage("Unable To Get User Info: No response from server...");
			 return null;
		    }
		}
	    }
	    else
	    {
                return null;
	    }
	}
	catch(Exception e)
	{
	    Messenger.printMessage("Unable To Get User Info: " + e);
            return null;
	}
    }

    ////////
    //
    //registerUser
    //
    //Registers a new account, if it is successful then an activation email
    //is sent by the server. This method will return true if it successfully
    //registered and false if it did not.
    //
    ////////
    public static boolean registerUser(String uname, String phash, String email)
    {
        try
	{
            String ServerIP = Settings.getServerIP();
	    if(ServerIP == null)
	    {
                Messenger.printMessage("Unable To Retrieve Setting 'getServerIP'");
		return false;
	    }
	    JSONObject response = makeGetRequest("http://" + ServerIP + "/api/register?uname=" + uname + "&phash=" + phash + "&email=" + email);
	    if(response != null)
	    {
                if(response.get("success") != null)
		{
                    return response.get("success").asBoolean();
		}
		else
		{
                    if(response.get("error") != null)
		    {
                        Messenger.printMessage("Unable To Register User: " + response.get("error"));
			return false;
		    }
		    else
		    {
                         Messenger.printMessage("Unable To Register User: No response from server...");
			 return false;
		    }
		}
	    }
	    return false;
	}
	catch(Exception e)
	{
            Messenger.printMessage("Unable To Register User: " + e);
	    return false;
        }
    }

    ////////
    //
    //echoMessage
    //
    //Really a test method, makes a request and returns the message that was
    //sent back, which is the same as the message that was sent.
    //
    ////////
    public static String echoMessage(String message)
    {
	try
	{
            String ServerIP = Settings.getServerIP();
       	    if(ServerIP == null)
	    {
                Messenger.printMessage("Unable To Retrieve Setting 'getServerIP'");
	        return null;
	    }
            JSONObject response = makeGetRequest("http://" + ServerIP + "/api/echo?msg=" + URLEncoder.encode(message,"ISO-8859-1"));
	    if(response != null)
	    {
		if(response.get("msg") != null)
		{
                    return processJSONStringArray(response.get("msg"));
		}
		else
		{
                    if(response.get("error") != null)
		    {
                        Messenger.printMessage("Unable To Echo Message: " + response.get("error"));
			return null;
		    }
		    else
		    {
                        Messenger.printMessage("Unable To Echo Message: No response from server...");
			return null;
		    }
		}
	    }
	    return null;
	}
	catch(Exception e)
	{
            Messenger.printMessage("Unable To Echo Message: " + e);
	    return null;
	}
    }

    ////////
    //
    //processJSONStringArray
    //
    //Takes a JSONArray made of strings returned by the server and returns 
    //the string inside.
    //
    ////////
    private static String processJSONStringArray(JSONValue x)
    {
        StringBuilder sb = new StringBuilder();
	String nextWord = "";
        for(int i = 0; i < x.length(); i++)
	{
	    if(i != x.length() - 1)
	    {
                nextWord = x.get(i).asString();
	        nextWord = nextWord.substring(0, nextWord.length());
	        sb.append(nextWord + " ");
	    }
	    else
	    {
                nextWord = x.get(x.length() - 1).asString();
                nextWord = nextWord.substring(0, nextWord.length());
		sb.append(nextWord);
	    }
	}
	return sb.toString();
    }

    ////////
    //
    //makeGetRequest
    //
    //Generic method to make a get request to the server and return the 
    //response.
    //
    ////////
    public static JSONObject makeGetRequest(String request)
    {
	StringBuilder sb = new StringBuilder();
        try
	{
            String serverIP = Settings.getServerIP();
	    URL url = new URL(request);
	    URLConnection connection = url.openConnection();
	    try
	    {
                return JSONObject.parseStream(connection.getInputStream());
	    }
	    catch(JSONParserException e)
	    {
                Messenger.printMessage("Error Making Request: " + e);
                return null;
	    }
	}
	catch(ConnectException e)
	{
            Messenger.printMessage("Unable To Contact Server!");
	    return null;
	}
	catch(Exception e)
	{
            Messenger.printMessage("'makeGetrequest' Unable To Make Request: " + e);
	    return null;
	}
    }
}
