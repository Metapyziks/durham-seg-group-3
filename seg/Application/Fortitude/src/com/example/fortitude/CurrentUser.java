package com.example.fortitude;

public class CurrentUser extends User
{
	private static CurrentUser me = null;
	private String sessionID;
	private String phash;
	private int balance;
	private String numberOfCaches;
	private int totalGarrison;
	
	public CurrentUser(String accountid, String username, String joindate, String rank, String sessionID, String phash, int balance, String numberOfCaches, int totalGarrison, String caches)
	{
		super(accountid, username, joindate, rank, caches);
		this.sessionID = sessionID;
		this.phash = phash;
		this.balance = balance;
		this.numberOfCaches = truncateBalance(numberOfCaches);
		setGarrison(totalGarrison); 
		me = this;
	}
	
	public void setSessionID(String x)
	{
		sessionID = x;
	}
	
	public void setBalance(int x)
	{
		balance = x;;
	}
	
	public void setNumberOfCaches(String x)
	{
		numberOfCaches = truncateBalance(x);
	}
	
	public int getGarrison()
	{
		return totalGarrison;
	}
	
	public void addToGarrison(int x)
	{
		totalGarrison += x;
	}
	
	public void setGarrison(int x)
	{
		totalGarrison = x;
	}
		
	public int getTotalBalance()
	{
		return totalGarrison + balance;
	}
	
	public String getNumberOfCaches()
	{
		return numberOfCaches;
	}
	
	public int getIntNumberOfCaches()
	{
		return Integer.parseInt(numberOfCaches);
	}
	
	private String truncateBalance(String x)
	{
		return Integer.toString((int)(Double.parseDouble(x)));
	}
	
	public int getBalance()
	{
		return balance;
	}
	
	public String getPhash()
	{
		return phash;
	}
	
	public String getSessionID()
	{
		return sessionID;
	}
	
	public static CurrentUser getMe()
	{
		return me;
	}
}
