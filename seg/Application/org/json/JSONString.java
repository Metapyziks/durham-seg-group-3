package org.json;

public class JSONString extends JSONValue
{
	public final String value;

	JSONString( ParseContext ctx )
		throws JSONParserException
	{
		value = ctx.nextString();
	}

	@Override
	public String asString()
	{
		return value;
	}

	@Override
	public String toString()
	{
		return "\"" + value + "\"";
	}
}
