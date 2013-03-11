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
	
	public static synchronized void setAsRead(int id)
	{
		for(NotificationStub stub : stubs)
		{
			if(stub.getNotificationId() == id)
			{
				stub.setRead();
				break;
			}
		}
	}
	
	public static synchronized void addStub(NotificationStub x)
	{
		for(int i = 0; i < stubs.size(); i++)
		{
			NotificationStub item = stubs.get(i);
			if(item.getNotificationId() < x.getNotificationId())
			{
				stubs.add(i, x);
				return;
			}
			else if(item.getNotificationId() == x.getNotificationId())
			{
				return;
			}
		}
		stubs.add(x);
	}
	
	public static int getSize()
	{
		return stubs.size();
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
	
	public static synchronized ArrayList<NotificationStub> getMessages()
	{
		ArrayList<NotificationStub> stubsToReturn = new ArrayList<NotificationStub>();
		
		for(NotificationStub stub : stubs)
		{
			if(stub.getType().equals("Message"))
			{
				stubsToReturn.add(stub);
			}
		}
		
		return stubsToReturn;
	}
	
	public static synchronized boolean isUnread()
	{
		for(NotificationStub stub : stubs)
		{
			if(!stub.getRead())
			{
				return true;
			}
		}
		return false;
	}
}
