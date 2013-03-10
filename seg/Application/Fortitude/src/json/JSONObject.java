package json;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

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
		throws FileNotFoundException, JSONParserException, IOException
	{
		return parseStream( new FileInputStream( path ) );
	}

	public static JSONObject parseStream( InputStream stream )
		throws JSONParserException, IOException
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
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for(String key : this._children.keySet())
		{
			JSONValue val = this.get(key);
			sb.append('"');
			sb.append(key);
			sb.append("\" : ");
			sb.append(val.toString());
			sb.append(",\n");
		}
		sb.append("}");
		return sb.toString();
	}
	
	public static void main(String args[])
	{
		try
		{
		URL urler = new URL("http://maps.googleapis.com/maps/api/directions/json?origin=54.77853412,-1.57010289&destination=54.773628,-1.588106&sensor=true");
		URLConnection connection = urler.openConnection();
		connection.setConnectTimeout(15000);
		JSONObject x = JSONObject.parseStream(connection.getInputStream());
		System.out.println(x.toString());
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}
}
