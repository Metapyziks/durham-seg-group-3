package org.json;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

public class JSONObject extends JSONValue
{
	public static JSONObject parseString( String string )
		throws JSONParserException
	{
		ParseContext ctx = new ParseContext( string );

		if( ctx.isNextObjectStart() )
			return new JSONObject( ctx );

		return null;
	}

	public static JSONObject parseFile( String path )
		throws FileNotFoundException, JSONParserException
	{
		return parseStream( new FileInputStream( path ) );
	}

	public static JSONObject parseStream( InputStream stream )
		throws JSONParserException
	{
		ParseContext ctx = new ParseContext( stream );

		if( ctx.isNextObjectStart() )
			return new JSONObject( ctx );

		return null;
	}

	private HashMap<String, JSONValue> _children;

	JSONObject( ParseContext ctx )
		throws JSONParserException
	{
		_children = new HashMap<String, JSONValue>();

		ctx.skipWhitespace();
		ctx.next(); // read past '{'
		while( !ctx.isNextObjectEnd() )
		{
			if( _children.size() > 0 )
			{
				if( !ctx.isNextDelimiter() )
					ctx.error( "Expected a ','" );

				ctx.next(); // read past ','
			}

			String ident;
			if( ( ident = ctx.nextString() ) == null )
				ctx.error( "Expected an identifier" );
			
			if( !ctx.isNextColon() )
				ctx.error( "Expected a ':'" );

			ctx.next(); // read past ':'

			JSONValue val = ctx.nextValue();

			if( val == null )
				ctx.error( "Expected a value" );

			_children.put( ident, val );
		}
		ctx.next(); // read past '}'
	}

	@Override
	public JSONValue get( String identifier )
	{
		if( _children.containsKey( identifier ) )
			return _children.get( identifier );

		return null;
	}

	@Override
	public String toString()
	{
		return "{}"; // TODO: make this good
	}
}
