package com.example.fortitude;

import android.widget.GridLayout;
import android.widget.GridLayout.LayoutParams;
import android.widget.GridLayout.Spec;

public class SplashScreen extends Window
{
	private static SplashScreen me;
	
    public SplashScreen()
    {
    	super(R.drawable.splash);
    	me = this;
    	addContentToContentPane(createWindowPane());
    }
    
    private GridLayout createWindowPane()
    {
    	GridLayout mainArea = new GridLayout(Fortitude.getFortitude());
    	mainArea.setColumnCount(1);
    	mainArea.setRowCount(1);
    	return mainArea;
    }
    
    ////////
    //
    //killMe
    //
    //removes this window from view
    //
    ////////
    public void killMe()
    {
    	me = null;
    	this.removeAllViews();
    }
    
    ////////
    //
    //getMe
    //
    //returns the last created instance of this class, may be null!
    //
    ////////
    public static SplashScreen getMe()
    {
    	return me;
    }
}
