import java.util.Properties;
import java.io.FileInputStream;

public class Settings
{
    private static final String settingsFile = "Settings.ini";
    private static final Messenger messenger = new Messenger();
    private Properties settings;

    ///////
    //
    //Constructor
    //
    //Initiallises vairables and reads in all settings from the settings
    //ini file.
    //
    ////////
    public Settings()
    {
	settings = new Properties();
        readSettings();
    }

    ////////
    //
    //readSettings
    //
    //Reads in the settings file that is an INI file.
    //
    ////////
    private void readSettings()
    {
	try
	{
	    settings.load(new FileInputStream(settingsFile));
	}
	catch(Exception e)
	{
            messenger.printError("Error When Reading Settings ini File: " + e.toString());
	}
    }

    ////////
    //
    //updateSettings
    //
    //Pretty irrelevant method, but the method name makes more sense when called
    //after the class has been initialised.
    //
    ////////
    public void updateSettings()
    {
        readSettings();
    }

    ////////
    //
    //getSettings
    //
    //returns all of the settings that were last read from the ini file.
    //
    ////////
    public Properties getAllSettings()
    {
        return settings;
    }

    ////////
    //
    //getServerIP
    //
    //returns the Server IP that setting that was last read from the ini file.
    //
    ////////
    public String getServerIP()
    {
        return settings.getProperty("ServerIP");
    }

    ////////
    //
    //main
    //
    //Main method for testing this class.
    //
    ////////
    public static void main(String args[])
    {
        Settings s = new Settings();
	System.out.println(s.getServerIP());
    }
}
