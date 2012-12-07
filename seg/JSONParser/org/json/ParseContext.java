package org.json;

import java.io.InputStream;
import java.util.Scanner;

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

	ParseContext( InputStream stream )
	{
		Scanner scan = new Scanner( stream );
		scan.useDelimiter( "$" );
		String string = scan.next();

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
				case '\n':
					num = 0;
				default:
					++num;
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

	boolean hasNext()
	{
		return offset < length;
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

	JSONValue nextValue()
		throws JSONParserException
	{
		if( isNextString() )
			return new JSONString( this );

		if( isNextObjectStart() )
			return new JSONObject( this );

		return null;
	}
}
