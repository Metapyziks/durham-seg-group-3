package com.example.fortitude;

import android.widget.GridLayout.LayoutParams;

public class GreyScreen extends Window
{
	private static GreyScreen me;
	
	public GreyScreen()
	{
		super(R.drawable.message_box_fade_screen);
		me = this;
		LayoutParams windowParams = new LayoutParams();
        Fortitude.getFortitude().addContentView(this, windowParams);
	}
	
	public static GreyScreen getMe()
	{
		return me;
	}
	
	public void killMe()
	{
		removeAllViews();
	}
}
