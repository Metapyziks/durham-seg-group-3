package org.json;

public class JSONParser
{
	public static JSONObject parseString( String string )
	{
		return parseStream( new ByteArrayInputStream( myString.getBytes() ) );
	}

	public static JSONObject parseFile( String path )
	{
		return parseStream( new FileInputStream( path ) );
	}

	public static JSONObject parseStream( InputStream stream )
	{
		
	}
}
