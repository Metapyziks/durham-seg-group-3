import java.io.FileNotFoundException;

import org.json.JSONObject;
import org.json.JSONParserException;

public class Program
{
	public static void main( String[] args )
	{
		try
		{
			JSONObject obj = JSONObject.parseFile( "test.json" );

			String lawdy = obj.get( "nesting" ).get( "inception" )
				.get( "oh lawdy" ).asString();

			System.out.println( "indeed = " + lawdy );

			String arrays = obj.get( "arrays" ).get( 2 ).get( 2 ).asString();

			System.out.println( "arrays = " + arrays );

			int integer = obj.get( "numbers?" ).asInteger();
			double doub = obj.get( "decimals" ).asDouble();

			System.out.println( "numbers = " + integer + ", " + doub );
		}
		catch( FileNotFoundException e )
		{
			System.out.println( "File not found" );
		}
		catch( JSONParserException e )
		{
			System.out.println( "JSON Parser Exception:\n" + e.getMessage() );
		}
	}
}
