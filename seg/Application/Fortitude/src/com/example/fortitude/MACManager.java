package com.example.fortitude;

import java.util.ArrayList;

public class MACManager 
{
	private static ArrayList<String> macs;
	private static String claimingAddress;
	
	static
	{
		macs = new ArrayList<String>();
	}
	
	public static void setClaimingAddress(String x)
	{
		claimingAddress = x;
	}
	
	public static String getClaimingAddress()
	{
		return claimingAddress;
	}
	
	public static String getMacAddress(int i)
	{
		if(i >= macs.size())
		{
			return null;
		}
		return macs.get(i);
	}
	
	public synchronized static void addMacAddress(String x)
	{
		macs.add(x);
	}
	
	public static ArrayList<String> getMacs()
	{
		return macs;
	}
	
	public synchronized static void clearMacs()
	{
		macs.clear();
	}
}
