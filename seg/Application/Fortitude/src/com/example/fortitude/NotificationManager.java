package com.example.fortitude;

import java.util.ArrayList;

public class NotificationManager 
{
	private static ArrayList<NotificationStub> stubs;
	
	////////
	//
	//static constructor
	//
	////////
	static
	{
		stubs = new ArrayList<NotificationStub>();
	}
	
	////////
	//
	//getMessageStub
	//
	//returns message stub at index provided in array list. Will return null if doesn't exist
	//
	////////
	public static NotificationStub getMessageStub(int index)
	{
		if(index >= stubs.size())
		{
			return null;
		}
		return stubs.get(index);
	}
	
	public static void addStub(NotificationStub x)
	{
		stubs.add(x);
	}
	
	////////
	//
	//clearStubs
	//
	//removes all message stubs
	//
	////////
	public static void clearStubs()
	{
		stubs.clear();
	}
}
