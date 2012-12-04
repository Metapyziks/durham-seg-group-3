import java.util.Properties;
import java.io.FileInputStream;

//Static class to allow all classes in the application
//to access settings from the config ini file.
public class Settings
{
    private static final String settingsFile = "Settings.ini";
    private static Properties settings = new Properties();

    ///////
    //
    //Constructor
    //
    ////////
    public Settings()
    {

    }

    ////////
    //
    //readSettings
    //
    //Reads in the settings file that is an INI file.
    //
    ////////
    public static void readSettings()
    {
	try
	{
	    settings.load(new FileInputStream(settingsFile));
	}
	catch(Exception e)
	{
            Messenger.printError("Error When Reading Settings ini File: " + e.toString());
	}
    }

    ////////
    //
    //getAllSettings
    //
    //returns all of the settings that were last read from the ini file.
    //
    ////////
    public static Properties getAllSettings()
    {
        return settings;
    }

    ////////
    //
    //getSetting
    //
    //Can be used to access any setting value from the settings file,
    //but it is better to use the individual accessors should the
    //name of the key change.
    //
    ////////
    public static String getSetting(String settingName)
    {
        if(settings.containsKey(settingName))
	{
            return settings.getProperty(settingName);
	}
	else
	{
            return null;
	}
    }

    ////////
    //
    //getServerIP
    //
    //returns the Server IP that setting that was last read from the ini file.
    //
    ////////
    public static String getServerIP()
    {
	if(settings.containsKey("ServerIP"))
	{
            return settings.getProperty("ServerIP");
	}
	else
	{
            return null;
	}
    }
}
