package com.example.fortitude;

import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridLayout.LayoutParams;
import android.widget.GridLayout.Spec;
import android.graphics.Color;
import android.graphics.Point;
import android.widget.Space;
import android.util.TypedValue;
import android.view.View;

public class GUI
{   
	////////
	//
	//Constructor
	//
	////////
    public GUI()
    {

    }

    ////////
    //
    //setUpActivityGraphics
    //
    //sets up initial graphics for the activity before any further graphics are added.
    //
    ////////
    public static void setUpActivityGraphics()
    {
        Fortitude.getFortitude().getWindow().getDecorView().setBackgroundColor(Color.BLACK);
        Fortitude.getFortitude().getActionBar().hide();
    }
    
    ////////
    //
    //calculateWindowWidth
    //
    //calculates and returns the width of the window visible to the user (the screen).
    //
    ////////
    public static int calculateWindowWidth()
    {
    	Point size = new Point();
        Fortitude.getFortitude().getWindowManager().getDefaultDisplay().getSize(size);
        return size.x;
    }
    
    ////////
    //
    //calculateWindowHeight
    //
    //calculates and returns the height of the window visible to the user (the screen).
    //
    ////////
    public static int calculateWindowHeight()
    {
    	Point size = new Point();
        Fortitude.getFortitude().getWindowManager().getDefaultDisplay().getSize(size);
        return (size.y - calculateActionBarHeight() - calculateStatusBarHeight());
    }
    
    ////////
    //
    //calculateActionBarHeight
    //
    //calculates and returns the height of the android action bar currently displayed.
    //
    ////////
    public static int calculateActionBarHeight()
    {
        //TypedValue tv = new TypedValue();
        //Fortitude.getFortitude().getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true);
        //return Fortitude.getFortitude().getResources().getDimensionPixelSize(tv.resourceId);
    	return 0;
    }
    
	////////
	//
	//killAll
	//
	//removes all GUI elements
	//
	////////
	public static void killAll(View v)
	{
		if(v instanceof ViewGroup)
		{
		    ViewGroup vg = ((ViewGroup) v);
		    int z = vg.getChildCount();
		    for(int i = 0; i < z; i++)
		    {
			    vg.removeAllViews();
		    }
		}
	}
    
    ////////
    //
    //calculateStatusBarHeight()
    //
    //calculates and returns the height of the android status bar currently displayed.
    //
    ////////
    public static int calculateStatusBarHeight()
    {
    	int x;
        x = Fortitude.getFortitude().getResources().getIdentifier("status_bar_height", "dimen", "android");
        return Fortitude.getFortitude().getResources().getDimensionPixelSize(x);
    }
}
