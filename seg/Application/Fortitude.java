//Main class that runs the application
public class Fortitude
{
    ////////
    //
    //constructor
    //
    ////////
    public Fortitude()
    {

    }

    ////////
    //
    //runApplication
    //
    //method that runs the application
    //
    ////////
    public void runApplication()
    {
        Settings.readSettings();
	System.out.println(ServerRequests.echoMessage("Hello There I Am Fortitude"));
	System.out.println("New User Created: " + Login.createUser("asdkgf", "oinkypig123", "asdg@cat.co.uk"));
	try
	{
	    Thread.sleep(1000);
	}
	catch(Exception e)
	{
            System.out.println("error");
            return;
	}
	System.out.println("Log in hash: " + Login.logIn("MissPiggy", "oinkypig123"));
	User theUser = Login.getUser("james_camden");
	System.out.println("User is verified: " + theUser.isVerified());
    }

    ////////
    //
    //main
    //
    //main method to run the application
    //
    ////////
    public static void main(String args[])
    {
        Fortitude fortitude = new Fortitude();
	fortitude.runApplication();
    }
}
