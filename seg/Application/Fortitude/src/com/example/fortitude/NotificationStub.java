package com.example.fortitude;

public class NotificationStub extends Reportable
{
	private int notificationId;
	private int accountId;
	private long timeStamp;
	private String type;
	private boolean read;
	private String person;
	private String context;
	
	public NotificationStub(int notificationId, int accountId, long timeStamp, String type, String read, String person, String context)
	{
		super(notificationId);
		this.accountId = accountId;
		this.timeStamp = timeStamp;
		this.type = type;
		this.read = read.equals("Read");
		this.person = person;
		this.context = context;
	}
	
	public String getContext()
	{
		return context;
	}
	
	public String getPerson()
	{
		return person;
	}
	
	public void setRead()
	{
		read = true;
	}
	
	public boolean getRead()
	{
		return read;
	}
	
	public int getNotificationId()
	{
		return super.getIdentifier();
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
