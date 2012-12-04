public class Messenger
{
    ////////
    //
    //Constructor
    //
    //
    //
    ////////
    public Messenger()
    {

    }

    ////////
    //
    //printError
    //
    //This method implements a method for dealing with errors from all
    //classes, for example displaying a GUI message box containing the error,
    //and then closing the application in a suitable manner.
    //
    ////////
    public void printError(String message)
    {
	printMessage(message);
	System.exit(1);
    }

    ////////
    //
    //printMessage
    //
    //This method implements a method for outputting messages from all classes
    //in the same way. This may be a GUI message box or something similar.
    //
    ////////
    public void printMessage(String message)
    {
        System.out.println(message);
    }
}
