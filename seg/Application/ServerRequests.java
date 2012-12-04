import java.net.URL;
import java.net.URLConnection;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.lang.StringBuilder;
import java.net.URLEncoder;
import java.net.ConnectException;

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
                Messenger.printMessage("Unable to retrieve setting 'getServerIP'");
	        return null;
	    }
            String response = makeGetRequest("http://" + ServerIP + "/api/echo?msg=" + URLEncoder.encode(message,"ISO-8859-1"));
	    if(response != null)
	    {
	        System.out.println(response);
	    }
	    return response;
	}
	catch(Exception e)
	{
            Messenger.printMessage("Unable to echo Message");
	    return null;
	}
    }

    ////////
    //
    //makeGetRequest
    //
    //Generic method to make a get request to the server and return the 
    //response.
    //
    ////////
    public static String makeGetRequest(String request)
    {
	StringBuilder sb = new StringBuilder();
        try
	{
            String serverIP = Settings.getServerIP();
	    URL url = new URL(request);
	    URLConnection connection = url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	    String line;
	    while((line = br.readLine()) != null)
	    {
                sb.append(line);
	    }
	}
	catch(ConnectException e)
	{
            Messenger.printMessage("Unable To Contact Server!");
	    return null;
	}
	catch(Exception e)
	{
            Messenger.printMessage("'makeGetrequest' Unable to make request: " + e);
	    return null;
	}
	return sb.toString();
    }
}
