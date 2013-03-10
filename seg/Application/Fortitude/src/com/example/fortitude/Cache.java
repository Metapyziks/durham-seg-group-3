package com.example.fortitude;

public class Cache 
{
	private String cacheId;
	private String ownerId;
	private String cacheName;
	private String lat;
	private String lon;
	private String garrison;
	
	public Cache(String cacheId, String ownerId, String cacheName, String lat, String lon, String garrison)
	{
		this.cacheId = cacheId;
		this.ownerId = ownerId;
		this.cacheName = cacheName;
		this.lat = lat;
		this.lon = lon;
		this.garrison = truncateServerNumber(garrison);
	}
	
	public boolean isUnowned()
	{
		return getOwnerId().equals("0");
	}
	
	public boolean isAdminCache()
	{
		return getOwnerId().equals("-1");
	}
	
	public boolean isMine()
	{
		return getOwnerId().equals(CurrentUser.getMe().getAccountId());
	}
	
	public void setGarrison(int newGarrison)
	{
		garrison = Integer.toString(newGarrison);
	}
	
	private String truncateServerNumber(String x)
	{
		return Integer.toString((int)(Double.parseDouble(x)));
	}
	
	public String getCacheId()
	{
		return cacheId;
	}
	
	public String getOwnerId()
	{
		return ownerId;
	}
	
	public String getCacheName()
	{
		return cacheName;
	}
	
	public String getLat()
	{
		return lat;
	}
	
	public String getLon()
	{
		return lon;
	}
	
	public String getGarrison()
	{
		return garrison;
	}
}
