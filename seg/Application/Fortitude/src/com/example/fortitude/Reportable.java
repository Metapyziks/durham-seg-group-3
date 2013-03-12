package com.example.fortitude;

public abstract class Reportable 
{	
	private int identifier;
	
	public Reportable(int identifier)
	{
		this .identifier = identifier;
	}
	
	public int getIdentifier()
	{
		return identifier;
	}
}
