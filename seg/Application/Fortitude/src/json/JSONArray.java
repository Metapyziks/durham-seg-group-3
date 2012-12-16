package json;

import java.util.ArrayList;

public class JSONArray extends JSONValue
{
	private JSONValue[] _values;

	JSONArray( ParseContext ctx )
		throws JSONParserException
	{
		ArrayList<JSONValue> vals = new ArrayList<JSONValue>();

		ctx.skipWhitespace();
		ctx.next(); // read past '['
		while( !ctx.isNextArrayEnd() )
		{
			if( vals.size() > 0 )
			{
				if( !ctx.isNextDelimiter() )
					ctx.error( "Expected a ','" );

				ctx.next(); // read past ','
			}

			JSONValue val = ctx.nextValue();

			if( val == null )
				ctx.error( "Expected a value" );

			vals.add( val );
		}
		ctx.next(); // read past ']'

		_values = new JSONValue[vals.size()];
		vals.toArray( _values );
	}

	public JSONValue get( int index )
	{
		return _values[ index ];
	}

	@Override
	public JSONValue[] asArray()
	{
		return _values;
	}

	@Override
	public int length()
	{
		return _values.length;
	}

	@Override
	public String toString()
	{
		return "[]"; // TODO: make this good
	}
}