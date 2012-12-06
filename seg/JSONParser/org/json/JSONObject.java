import java.util.HashMap;
import java.io.InputStream;

package org.json;

public class JSONObject
{
	private HashMap<String, JSONValue> _children;

	public JSONObject( InputStream stream )
	{
		_children = new HashMap<String, JSONValue>();
	}

	@Override
	public JSONValue get( String identifier )
	{
		if( _children.containsKey( identifier ) )
			return _children.get( identifier );

		return null;
	}
}