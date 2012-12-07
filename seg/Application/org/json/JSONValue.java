package org.json;

public abstract class JSONValue
{
	public JSONValue get( String identifier )
	{
		return null;
	}
	public JSONValue get( int index )
	{
		return null;
	}

	public String asString()
	{
		return null;
	}

	public int asInteger()
	{
		return 0;
	}

	public double asDouble()
	{
		return Double.NaN;
	}

	public JSONValue[] asArray()
	{
		return null;
	}

	public int length()
	{
		return -1;
	}
}
