package com.example.fortitude;

public class NotificationStub 
{
	private int notificationId;
	private int accountId;
	private long timeStamp;
	private String type;
	
	public NotificationStub(int notificationId, int accountId, long timeStamp, String type)
	{
		this.notificationId = notificationId;
		this.accountId = accountId;
		this.timeStamp = timeStamp;
		this.type = type;
	}
	
	public int getNotificationId()
	{
		return notificationId;
	}
	
	public int getAccountId()
	{
		return accountId;
	}
	
	public long getTimeStamp()
	{
		return timeStamp;
	}
	
	public String getType()
	{
		return type;
	}
}
