package com.example.fortitude;

import json.JSONObject;

public class Cache extends Reportable
{
	private int ownerId;
	private String cacheName;
	private double lat;
	private double lon;
	private int garrison;
	
	public Cache(int cacheId, int ownerId, String cacheName, double lat, double lon, int garrison)
	{
		super(cacheId);
		this.ownerId = ownerId;
		this.cacheName = cacheName;
		this.lat = lat;
		this.lon = lon;
		this.garrison = garrison;
	}
	
	public Cache(JSONObject object)
	{
		super(object.get("cacheid").asInteger());
		this.ownerId = object.get("ownerid").asInteger();
		this.cacheName = object.get("name").asString();
		this.lat = object.get("latitude").asDouble();
		this.lon = object.get("longitude").asDouble();
		this.garrison = object.get("garrison").asInteger();
	}
	
	public boolean isUnowned()
	{
		return getOwnerId() == 0;
	}
	
	public boolean isAdminCache()
	{
		return getOwnerId() == -1;
	}
	
	public boolean isMine()
	{
		return getOwnerId() == CurrentUser.getMe().getAccountId();
	}
	
	public void setGarrison(int newGarrison)
	{
		garrison = newGarrison;
	}
	
	public int getCacheId()
	{
		return super.getIdentifier();
	}
	
	public int getOwnerId()
	{
		return ownerId;
	}
	
	public String getCacheName()
	{
		return cacheName;
	}
	
	public double getLat()
	{
		return lat;
	}
	
	public double getLon()
	{
		return lon;
	}
	
	public int getGarrison()
	{
		return garrison;
	}
}
