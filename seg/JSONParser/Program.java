import java.io.FileNotFoundException;

import org.json.JSONObject;

public class Program
{
	public static void main( String[] args )
	{
		try
		{
			JSONObject obj = JSONObject.parseFile( "test.json" );
		}
		catch( FileNotFoundException e )
		{
			System.out.println( "File not found" );
		}
	}
}