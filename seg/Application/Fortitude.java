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
	System.out.println(Login.createUser("Kris", "Kris", "k.a.s.ayre@durham.ac.uk"));

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
