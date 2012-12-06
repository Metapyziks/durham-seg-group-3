package org.json;
import java.io.InputStream;

public class JSONString extends JSONValue
{
	public final String value;

	public JSONString( InputStream stream )
	{
		value = str;
	}

	@Override
	public String asString()
	{
		return value;
	}
}