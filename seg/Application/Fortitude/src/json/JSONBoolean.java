package json;

public class JSONBoolean extends JSONValue
{
	private boolean _value;

	JSONBoolean( ParseContext ctx )
		throws JSONParserException
	{
		_value = ctx.nextBoolean();
	}

	@Override
	public String asString()
	{
		return _value ? "true" : "false";
	}

	@Override
	public int asInteger()
	{
		return _value ? 1 : 0;
	}

	@Override
	public double asDouble()
	{
		return _value ? 1.0 : 0.0;
	}

	@Override
	public boolean asBoolean()
	{
		return _value;
	}

	@Override
	public String toString()
	{
		return asString();
	}
}
