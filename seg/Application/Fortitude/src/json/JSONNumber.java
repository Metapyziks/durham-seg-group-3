package json;

public class JSONNumber extends JSONValue
{
	private double _value;

	JSONNumber( ParseContext ctx )
		throws JSONParserException
	{
		_value = ctx.nextNumber();
	}

	@Override
	public String asString()
	{
		return Double.toString( _value );
	}

	@Override
	public int asInteger()
	{
		return (int) _value;
	}

	@Override
	public double asDouble()
	{
		return _value;
	}

	@Override
	public String toString()
	{
		return asString();
	}
}
