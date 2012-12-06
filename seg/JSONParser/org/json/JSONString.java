package org.json;

public class JSONString extends JSONValue
{
	public final String value;

	public JSONString( String str )
	{
		value = str;
	}

	@Override
	public String asString()
	{
		return value;
	}
}