package com.example.fortitude;

public class CurrentUser extends User
{
	private static CurrentUser me = null;
	private String sessionID;
	private String phash;
	private String balance;
	private String numberOfCaches;
	private String totalBalance;
	
	public CurrentUser(String accountid, String username, String joindate, String rank, String sessionID, String phash, String balance, String numberOfCaches, String totalBalance, String caches)
	{
		super(accountid, username, joindate, rank, caches);
		this.sessionID = sessionID;
		this.phash = phash;
		this.balance = truncateBalance(balance);
		this.numberOfCaches = truncateBalance(numberOfCaches);
		this.totalBalance = Integer.toString((Integer.parseInt(truncateBalance(totalBalance))) + (Integer.parseInt(this.balance))); 
		me = this;
	}
	
	public void setSessionID(String x)
	{
		sessionID = x;
	}
	
	public void setBalance(String x)
	{
		balance = truncateBalance(x);
	}
	
	public void setNumberOfCaches(String x)
	{
		numberOfCaches = truncateBalance(x);
	}
	
	public void setTotalBalance(String x)
	{
		totalBalance = Integer.toString((Integer.parseInt(truncateBalance(x))) + (Integer.parseInt(this.balance)));
	}
		
	public String getTotalBalance()
	{
		return totalBalance;
	}
	
	public int getIntTotalBalance()
	{
		return Integer.parseInt(totalBalance);
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
	
	public String getBalance()
	{
		return balance;
	}
	
	public int getIntBalance()
	{
		return Integer.parseInt(balance);
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
