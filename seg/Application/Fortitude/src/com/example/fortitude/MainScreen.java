package com.example.fortitude;

import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridLayout.LayoutParams;
import android.widget.GridLayout.Spec;
import android.widget.Space;
import android.graphics.Color;
import android.widget.TextView;
import android.view.Gravity;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.EditText;

public class MainScreen extends GridLayout
{
	private int windowWidth; //Visible window width
	private int windowHeight; //Visible window height
	
    private Spec row1 = GridLayout.spec(0);
    
    private Spec col1 = GridLayout.spec(0);
    private Spec col2 = GridLayout.spec(1);
    
    private Spec allcols = GridLayout.spec(0,1);
    
    private static MainScreen me = null;
	
	////////
	//
	//newMainLoginScreen
	//
	//static constructor
	//
	////////
	public static void newMainLoginScreen()
	{
	    new MainLoginScreen();	
	}
    
    ////////
    //
    //Constructor
    //
    ////////
    public MainScreen()
    {
    	super(Fortitude.getFortitude());
    	
    	me = this;
    	
    	setWindowDimensions();
    	setColsAndRows();
    	createPositionalSpace();
    	
    	addMainScreen();
    	
    	addThisToDisplay();
    }
    
    ////////
    //
    //addMainScreen
    //
    //create and add main screen to this grid
    //
    ////////
    private void addMainScreen()
    {
        GridLayout mainArea = new GridLayout(this.getContext()); //The gridlayout for the layout of the MainLoginScreen itself
        mainArea.setColumnCount(1);
        mainArea.setRowCount(1);
        mainArea.setBackgroundColor(Color.BLUE);
        addLayoutToMainScreen(mainArea);
    }
    
	////////
	//
	//addThisToDisplay
	//
	//adds the current object to the top of the application's display
	//
	////////
	private void addThisToDisplay()
	{
        LayoutParams messageboxParams = new LayoutParams();
        Fortitude.getFortitude().addContentView(this, messageboxParams);
	}
    
	////////
	//
	//addLayoutToMainScreen
	//
	//adds all the relevant layout to the given MainScreen
	//
	////////
    private void addLayoutToMainScreen(GridLayout mainArea)
    {  
        GridLayout signInGrid = new GridLayout(mainArea.getContext());
        signInGrid.setRowCount(1);
        signInGrid.setColumnCount(2);
        
        LayoutParams firstRowLeftTextViewLayout = new LayoutParams(row1, col1); //top bar text view left (username)
        firstRowLeftTextViewLayout.width = windowWidth / 2;
        firstRowLeftTextViewLayout.height = windowHeight / 20;
        TextView firstRowLeftTextView = new TextView(mainArea.getContext());
        firstRowLeftTextView.setLayoutParams(firstRowLeftTextViewLayout);
        firstRowLeftTextView.setText(" " + CurrentUser.getMe().getUserName());
        firstRowLeftTextView.setTextSize(12);
        firstRowLeftTextView.setGravity(Gravity.LEFT);
        firstRowLeftTextView.setBackgroundColor(Color.RED);
        signInGrid.addView(firstRowLeftTextView, firstRowLeftTextViewLayout);
        
        LayoutParams firstRowRightTextViewLayout = new LayoutParams(row1, col2); //top bar text view right (balance)
        firstRowRightTextViewLayout.width = windowWidth / 2;
        firstRowRightTextViewLayout.height = windowHeight / 20;
        TextView firstRowRightTextView = new TextView(mainArea.getContext());
        firstRowRightTextView.setLayoutParams(firstRowRightTextViewLayout);
        firstRowRightTextView.setText("Balance: " + CurrentUser.getMe().getBalance() + " ");
        firstRowRightTextView.setTextSize(12);
        firstRowRightTextView.setGravity(Gravity.RIGHT);
        firstRowRightTextView.setBackgroundColor(Color.RED);
        signInGrid.addView(firstRowRightTextView, firstRowRightTextViewLayout);
        
        LayoutParams signInGridLayoutParams = new LayoutParams(row1, allcols);
        mainArea.addView(signInGrid, signInGridLayoutParams);
        
        LayoutParams mainAreaParams = new LayoutParams(col1, row1);
        addView(mainArea, mainAreaParams);
    }
    
	////////
	//
	//killMe
	//
	//removes the MainScreen from view
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
	//returns me.
	//
	////////
	public static MainScreen getMe()
	{
		return me;
	}
	
	////////
	//
	//createPositionalSpace
	//
	//Creates an invisible square in the top left corner of the screen.
	//The width and height of this square define where the Main Screen will
	//appear on screen as the MainScreen is positioned starting at the bottom
	//right hand corner of this square.
	//
	////////
	private void createPositionalSpace()
	{
        LayoutParams spaceLayout = new LayoutParams(row1, col1); //Top left to dictate main layout
        spaceLayout.width = 0;
        spaceLayout.height = 0;
        Space space = new Space(Fortitude.getFortitude());
        space.setLayoutParams(spaceLayout);
        addView(space, spaceLayout);
	}
    
	////////
	//
	//setColsAndRows
	//
	//sets the number of columns and rows this main grid will have to define the
	//overall positional layout of where the message box will go on the screen.
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
}
