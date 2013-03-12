package com.example.fortitude;

import java.util.ArrayList;

public class BlockedManager 
{
	public static ArrayList<String> blocked;
	
	static
	{
		blocked = new ArrayList<String>();
	}
	
	public static String getBlocked(int index)
	{
		if(index >= blocked.size())
		{
			return null;
		}
		return blocked.get(index);
	}
	
	public static synchronized void clearBlocked()
	{
		blocked.clear();
	}
	
	public static synchronized void addBlocked(String user)
	{
		blocked.add(user);
	}
	
	public static boolean isBlocked(String uname)
	{
		for(String name : blocked)
		{
			if(name.equals(uname))
			{
				return true;
			}
		}
		return false;
	}
	
	public static int getSize()
	{
		return blocked.size();
	}
	
	public static synchronized void removeBlocked(String user)
	{
		blocked.remove(user);
	}
}
