package org.json;

public class JSONParserException
	extends Exception
{
	private int _line;
	private int _col;

	JSONParserException( ParseContext ctx, String message )
	{
		super( message + "\n at line #" + ctx.getLineNumber()
			+ ", column #" + ctx.getColumnNumber() );

		_line = ctx.getLineNumber();
		_col = ctx.getColumnNumber();
	}

	public int getLine()
	{
		return _line;
	}

	public int getColumn()
	{
		return _col;
	}
}