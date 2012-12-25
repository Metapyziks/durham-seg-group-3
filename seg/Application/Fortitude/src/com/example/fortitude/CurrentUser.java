package com.example.fortitude;

public class CurrentUser extends User
{
	private static CurrentUser me = null;
	private String sessionID;
	private String phash;
	private String balance;
	
	public CurrentUser(String accountid, String username, String joindate, String rank, String sessionID, String phash, String balance)
	{
		super(accountid, username, joindate, rank);
		this.sessionID = sessionID;
		this.phash = phash;
		this.balance = balance;
		me = this;
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
