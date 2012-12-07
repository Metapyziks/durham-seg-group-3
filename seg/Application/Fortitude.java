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
	ServerRequests.echoMessage("Hello There I Am Fortitude");
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
