package com.example.fortitude;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.GridLayout.LayoutParams;
import android.widget.GridLayout.Spec;
import android.widget.GridLayout;
import android.widget.Space;

public abstract class Window extends GridLayout
{
	private int positionalSpaceX = 0; //width of the positional space
	private int positionalSpaceY = 0; //height of the positional space
	
	private int windowHeight = 0; //Visible window height
	private int windowWidth = 0; //Visible window width
	
	private BackgroundImage backgroundImage;
	
    private Spec row1 = GridLayout.spec(0);
    private Spec row2 = GridLayout.spec(1);
    
    private Spec col1 = GridLayout.spec(0);
    private Spec col2 = GridLayout.spec(1);
	
	////////
	//
	//Constructor for full screen window
	//
	////////
    public Window()
    {
    	super(Fortitude.getFortitude());
    	setWindowDimensions();
    	setColsAndRows();
    	createPositionalSpace();
    }
    
    ////////
    //
    //Constructor for full screen window with set background image
    //
    ////////
    public Window(int imageID)
    {
    	super(Fortitude.getFortitude());
    	setWindowDimensions();
    	setColsAndRows();
    	createPositionalSpace();
        addBackgroundImage(imageID);
    }
    
    ////////
    //
    //Constructor where you can set window width and window position
    //
    ////////
    public Window(int windowWidth, int positionalSpaceX, int positionalSpaceY)
    {
    	super(Fortitude.getFortitude());
    	this.windowWidth = windowWidth;
    	this.windowHeight = 0;
    	this.positionalSpaceX = positionalSpaceX;
    	this.positionalSpaceY = positionalSpaceY;
    	setColsAndRows();
    	createPositionalSpace();
    }
    
    ////////
    //
    //Constructor where you can set window width and position and its background image
    //
    ////////
    public Window(int windowWidth, int positionalSpaceX, int positionalSpaceY, int imageID)
    {
    	super(Fortitude.getFortitude());
    	this.windowWidth = windowWidth;
    	this.windowHeight = 0;
    	this.positionalSpaceX = positionalSpaceX;
    	this.positionalSpaceY = positionalSpaceY;
    	setColsAndRows();
    	createPositionalSpace();
        addBackgroundImage(imageID);
    }
    
    ////////
    //
    //addContentToContentPane
    //
    //Adds GridLayout to the screen
    //
    ////////
    public void addContentToContentPane(GridLayout viewToAdd)
    {
    	if(viewToAdd != null)
    	{
            LayoutParams windowLayout = new LayoutParams(row2, col2);
            addView(viewToAdd, windowLayout);
    	}
    	addThisToDisplay();
    }
    
	////////
	//
	//addThisToDisplay
	//
	//adds this Window to the top of the application's display
	//
	////////
	private void addThisToDisplay()
	{
        LayoutParams windowParams = new LayoutParams();
        Fortitude.getFortitude().addContentView(this, windowParams);
	}
    
	////////
	//
	//createPositionalSpace
	//
	//Creates an invisible square in the top left corner of the screen.
	//The width and height of this square define where the window will
	//appear on screen as the window is positioned starting at the bottom
	//right hand corner of this square.
	//
	////////
	private void createPositionalSpace()
	{
        LayoutParams spaceLayout = new LayoutParams(row1, col1); //Top left to dictate main layout
        spaceLayout.width = positionalSpaceX;
        spaceLayout.height = positionalSpaceY;
        Space space = new Space(Fortitude.getFortitude());
        space.setLayoutParams(spaceLayout);
        addView(space, spaceLayout);
	}
    
	////////
	//
	//setColsAndRows
	//
	//sets the number of columns and rows this main grid will have to define the
	//overall positional layout of where the login screen will go on the screen.
	//
	////////
	private void setColsAndRows()
	{
        setColumnCount(2);
        setRowCount(2);
	}
    
	////////
	//
	//setWindowDimensions
	//
	//sets the values for dimensions of the visible window
	//
	////////
	private void setWindowDimensions()
	{
	    windowWidth = GUI.calculateWindowWidth();
	    windowHeight = GUI.calculateWindowHeight();
	}
	
	////////
	//
	//addBackgroundImage
	//
	//adds image to background
	//
	////////
	public void addBackgroundImage(int imageID)
	{
        LayoutParams backgroundImageLayout = new LayoutParams(row2, col2);
        backgroundImageLayout.width = windowWidth;
        backgroundImageLayout.height = windowHeight;
        backgroundImage = new BackgroundImage(imageID);
        backgroundImage.setLayoutParams(backgroundImageLayout);
        addView(backgroundImage, backgroundImageLayout);
	}
	
	public BackgroundImage getBackgroundImage()
	{
		return backgroundImage;
	}
	
	////////
	//
	//getWindowWidth
	//
	//return visible window width
	//
	////////
	public int getWindowWidth()
	{
		return windowWidth;
	}
	
	////////
	//
	//getWindowHeight
	//
	//return visible window height
	//
	////////
	public int getWindowHeight()
	{
		return windowHeight;
	}
	
	////////
	//
	//getPositionalSpaceX
	//
	//returns the width of the positionalSpace
	//
	////////
	public int getPositionalSpaceX()
	{
		return positionalSpaceX;
	}
	
	////////
	//
	//getPositionalSpaceY
	//
	//returns the height of the positionalSpace
	//
	////////
	public int getPositionalSpaceY()
	{
		return positionalSpaceY;
	}
	
	////////
	//
	//disableAllTheChildren
	//
	//disable all  elements in the application that aren't this messagebox
	//
	////////
	public static void disableAllTheChildren(View v)
	{
		v.setEnabled(false);
		if(v instanceof ViewGroup)
		{
		    ViewGroup vg = ((ViewGroup) v);
		    int z = vg.getChildCount();
		    for(int i = 0; i < z; i++)
		    {
			    disableAllTheChildren(vg.getChildAt(i));
		    }
		}
	}
	
	////////
	//
	//makeAllTheChildrenBetter
	//
	//opposite of "disableAllTheChildren"
	//
	////////
	public static void makeAllTheChildrenBetter(View v)
	{
		v.setEnabled(true);
		if(v instanceof ViewGroup)
		{
		    ViewGroup vg = ((ViewGroup) v);
		    int z = vg.getChildCount();
		    for(int i = 0; i < z; i++)
		    {
			    makeAllTheChildrenBetter(vg.getChildAt(i));
		    }
		}
	}
}
