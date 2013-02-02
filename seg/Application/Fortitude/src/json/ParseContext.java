package json;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.StringBuilder;
import java.io.BufferedReader;
import java.io.IOException;

class ParseContext
{
	private final char[] _chars;

	final int length;

	int offset;

	ParseContext( String string )
	{
		length = string.length();
		_chars = new char[length];
		string.getChars( 0, length, _chars, 0 );
	}

	ParseContext( InputStream stream ) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(stream));
		StringBuilder sb = new StringBuilder();
		
		String string = br.readLine();
		
		while(string != null)
		{
			sb.append(string);
			string = br.readLine();
		}
		
		string = sb.toString();

		length = string.length();
		_chars = new char[length];
		string.getChars( 0, length, _chars, 0 );
	}

	char peek()
	{
		if( offset < length )
			return _chars[offset];

		return '\0';
	}

	char prev()
	{
		if( offset > 0 )
			return _chars[--offset];

		return '\0';
	}

	char next()
	{
		if( offset < length )
			return _chars[offset++];

		return '\0';
	}

	boolean hasNext()
	{
		return offset < length;
	}

	int getLineNumber()
	{
		int num = 0;
		for( int i = 0; i < offset; ++i )
			if( _chars[i] == '\n' )
				++num;

		return num + 1;
	}

	int getColumnNumber()
	{
		int num = 0;
		for( int i = 0; i < offset; ++i )
		{
			switch( _chars[i] )
			{
				case '\r':
				case '\0':
					break;
				case '\t':
					num += 4;
					break;
				case '\n':
					num = 0;
					break;
				default:
					++num;
					break;
			}
		}
		return num + 1;
	}

	void error( String message )
		throws JSONParserException
	{
		throw new JSONParserException( this, message );
	}

	void skipWhitespace()
	{
		while( hasNext() && isNextWhitespace() )
			next();
	}

	boolean isNext( String str )
	{
		if( length - str.length() < 0 )
			return false;

		for( int i = 0; i < str.length(); ++i )
			if( str.charAt( i ) != _chars[offset + i] )
				return false;

		return true;
	}

	boolean isNextWhitespace()
	{
		return Character.isWhitespace( peek() );
	}

	boolean isNextObjectStart()
	{
		skipWhitespace();
		return peek() == '{';
	}

	boolean isNextObjectEnd()
	{
		skipWhitespace();
		return peek() == '}';
	}

	boolean isNextArrayStart()
	{
		skipWhitespace();
		return peek() == '[';
	}

	boolean isNextArrayEnd()
	{
		skipWhitespace();
		return peek() == ']';
	}

	boolean isNextColon()
	{
		skipWhitespace();
		return peek() == ':';
	}

	boolean isNextDelimiter()
	{
		skipWhitespace();
		return peek() == ',';
	}

	boolean isNextString()
	{
		skipWhitespace();
		return peek() == '"';
	}

	char nextUnicodeLiteral() 
	    throws JSONParserException
	{
		if( offset + 4 <= length )
		{
			char[] hexChars = new char[] { next(), next(), next(), next() };
			String hex = new String( hexChars );

			try
			{
				return (char) Short.parseShort( hex, 16 );
			}
			catch( NumberFormatException e )
			{
				error( "Invalid unicode literal (" + hex + ")" );
			}
		}
		else
		{
			error( "Incomplete unicode literal" );
		}
		return '\0';
	}

	char nextStringChar()
		throws JSONParserException
	{
		if( peek() == '\\' )
		{
			next();
			switch( next() )
			{
				case 'r':
					return '\r';
				case 'n':
					return '\n';
				case 't':
					return '\t';
				case '"':
					return '"';
				case 'u':
					return nextUnicodeLiteral();
				case '\\':
					return '\\';
				default:
					error( "Invalid escaped character (\\" + prev() + ")" );
			}
		}

		if( peek() == '"' )
			return '\0';

		return next();
	}

	String nextString()
		throws JSONParserException
	{
		if( !isNextString() )
			return null;

		StringBuilder str = new StringBuilder();

		next(); // read past '"'

		char nxt;
		while( ( nxt = nextStringChar() ) != '\0' )
			str.append( nxt );

		next(); // read past '"'

		return str.toString();
	}

	boolean isNextNumber()
	{
		skipWhitespace();
		return peek() == '-' || Character.isDigit( peek() );
	}

	double nextNumber()
	{
		if( !isNextNumber() )
			return Double.NaN;

		StringBuilder str = new StringBuilder();

		if ( hasNext() && peek() == '-' )
			str.append( next() );

		boolean dp = false;
		while( hasNext() )
		{
			char nxt = peek();
			if( Character.isDigit( nxt ) )
				str.append( next() );
			else if( nxt == '.' && !dp )
			{
				str.append( next() );
				dp = true;
			}
			else
				break;
		}

		return Double.parseDouble( str.toString() );
	}

	boolean isNextBoolean()
	{
		skipWhitespace();
		return isNext( "true" ) || isNext( "false" );
	}

	boolean nextBoolean()
	{
		if ( !isNextBoolean() )
			return false;

		if( peek() == 'f' )
		{
			offset += 5;
			return false;
		}
		else
		{
			offset += 4;
			return true;
		}
	}

	JSONValue nextValue()
		throws JSONParserException
	{
		if( isNextNumber() )
			return new JSONNumber( this );

		if( isNextString() )
			return new JSONString( this );

		if( isNextObjectStart() )
			return new JSONObject( this );

		if( isNextArrayStart() )
			return new JSONArray( this );

		if( isNextBoolean() )
			return new JSONBoolean( this );

		return null;
	}
}
