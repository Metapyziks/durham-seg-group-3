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

public class CurrentUserScreen extends GridLayout
{
	private int windowWidth; //Visible window width
	private int windowHeight; //Visible window height
	
	private Spec row1 = GridLayout.spec(0);
	private Spec row2 = GridLayout.spec(1);
	private Spec row3 = GridLayout.spec(2);
	private Spec row4 = GridLayout.spec(3);
	private Spec row5 = GridLayout.spec(4);
	private Spec row6 = GridLayout.spec(5);
	private Spec row7 = GridLayout.spec(6);
	
	private Spec col1 = GridLayout.spec(0);
	private Spec col2 = GridLayout.spec(1);
	private Spec col3 = GridLayout.spec(2);
	
	private Spec allcols = GridLayout.spec(0,2);
	private Spec allcolsExceptFirst = GridLayout.spec(1,2);
	
	private static CurrentUserScreen me = null;
	
    public CurrentUserScreen()
    {
    	super(Fortitude.getFortitude());
    	GUI.disableAllTheGUIElements(Fortitude.getFortitude().getWindow().getDecorView());
    	
    	setWindowDimensions();
    	setColsAndRows();
    	
    	createPositionalSpace();
    	
    	addCurrentUserScreen();
    	
    	addThisToDisplay();
    }
    
    ////////
    //
    //addLoginScreen
    //
    //create and add CurrentUserScreen to this grid
    //
    ////////
    private void addCurrentUserScreen()
    {
        GridLayout mainArea = new GridLayout(this.getContext()); //The gridlayout for the layout of the MainLoginScreen itself
        mainArea.setColumnCount(3);
        mainArea.setRowCount(6);
        mainArea.setBackgroundColor(Color.CYAN);
        addLayoutToCurrentUserScreen(mainArea);
    }
    
	////////
	//
	//addLayoutToCurrentUserScreen
	//
	//adds all the relevant layout to the given CurrentUserScreen
	//
	////////
    private void addLayoutToCurrentUserScreen(GridLayout mainArea)
    {   
        LayoutParams firstRowTextViewLayout = new LayoutParams(row1, allcols); //1st row text view middle
        firstRowTextViewLayout.width = (windowWidth / 10) * 8;
        firstRowTextViewLayout.height = (windowHeight / 8);
        TextView firstRowTextView = new TextView(mainArea.getContext());
        firstRowTextView.setLayoutParams(firstRowTextViewLayout);
        firstRowTextView.setText("Account Settings");
        firstRowTextView.setTextSize(18);
        firstRowTextView.setGravity(Gravity.CENTER);
        firstRowTextView.setBackgroundColor(Color.RED);
        mainArea.addView(firstRowTextView, firstRowTextViewLayout);
        
        LayoutParams secondRowSpaceLayout = new LayoutParams(row2, allcols); //2nd row space
        secondRowSpaceLayout.width = (windowWidth / 10);
        secondRowSpaceLayout.height =  (windowHeight / 20);
        Space secondRowSpace = new Space(mainArea.getContext());
        secondRowSpace.setLayoutParams(secondRowSpaceLayout);
        mainArea.addView(secondRowSpace, secondRowSpaceLayout);
        
        LayoutParams thirdRowLeftSpaceLayout = new LayoutParams(row3, col1); //3rd row left space
        thirdRowLeftSpaceLayout.width = (windowWidth / 10);
        thirdRowLeftSpaceLayout.height = (windowHeight / 2) / 6;
        Space thirdRowLeftSpace = new Space(mainArea.getContext());
        thirdRowLeftSpace.setLayoutParams(thirdRowLeftSpaceLayout);
        mainArea.addView(thirdRowLeftSpace, thirdRowLeftSpaceLayout);
        
        LayoutParams thirdRowTextViewLayout = new LayoutParams(row3, allcolsExceptFirst); //Username text view
        thirdRowTextViewLayout.height = ((windowHeight / 2) / 6);
        TextView userDetailsTextView = new TextView(mainArea.getContext());
        userDetailsTextView.setLayoutParams(thirdRowTextViewLayout);
        userDetailsTextView.setText("Username: " + CurrentUser.getMe().getUserName());
        userDetailsTextView.setTextSize(16);
        mainArea.addView(userDetailsTextView, thirdRowTextViewLayout);
        
        LayoutParams fourthRowLeftSpaceLayout = new LayoutParams(row4, col1); //4th row left space
        fourthRowLeftSpaceLayout.width = (windowWidth / 10);
        fourthRowLeftSpaceLayout.height = (windowHeight / 2) / 6;
        Space fourthRowLeftSpace = new Space(mainArea.getContext());
        fourthRowLeftSpace.setLayoutParams(fourthRowLeftSpaceLayout);
        mainArea.addView(fourthRowLeftSpace, fourthRowLeftSpaceLayout);
        
        LayoutParams fourthRowTextViewLayout = new LayoutParams(row4, allcolsExceptFirst); //Balance text view
        fourthRowTextViewLayout.height = (windowHeight / 2) / 6;
        TextView userBalanceTextView = new TextView(mainArea.getContext());
        userBalanceTextView.setLayoutParams(fourthRowTextViewLayout);
        userBalanceTextView.setText("Balance: " + CurrentUser.getMe().getBalance());
        userBalanceTextView.setTextSize(16);
        mainArea.addView(userBalanceTextView, fourthRowTextViewLayout);
        
        LayoutParams fithRowLeftSpaceLayout = new LayoutParams(row5, col1); //5th row left space
        fithRowLeftSpaceLayout.width = (windowWidth / 10);
        fithRowLeftSpaceLayout.height = (windowHeight / 2) / 6;
        Space fithRowLeftSpace = new Space(mainArea.getContext());
        fithRowLeftSpace.setLayoutParams(fithRowLeftSpaceLayout);
        mainArea.addView(fithRowLeftSpace, fithRowLeftSpaceLayout);
        
        LayoutParams fithRowTextViewLayout = new LayoutParams(row5, allcolsExceptFirst); //Balance text view
        fithRowTextViewLayout.height = (windowHeight / 2) / 6;
        TextView cacheNumberTextView = new TextView(mainArea.getContext());
        cacheNumberTextView.setLayoutParams(fithRowTextViewLayout);
        cacheNumberTextView.setText("Number Of Caches: " + "0");
        cacheNumberTextView.setTextSize(16);
        mainArea.addView(cacheNumberTextView, fithRowTextViewLayout);
        
        LayoutParams windowLayout = new LayoutParams(row2, col2);
        
        addView(mainArea, windowLayout);
        
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
        LayoutParams windowLayoutParams = new LayoutParams();
        Fortitude.getFortitude().addContentView(this, windowLayoutParams);
	}
    
	////////
	//
	//killMe
	//
	//removes the window from view
	//
	////////
	public void killMe()
	{
		me = null;
		this.removeAllViews();
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
        spaceLayout.width = windowWidth / 10;
        spaceLayout.height = windowHeight / 6;
        Space space = new Space(Fortitude.getFortitude());
        space.setLayoutParams(spaceLayout);
        addView(space, spaceLayout);
	}
    
	////////
	//
	//setColsAndRows
	//
	//sets the number of columns and rows this main grid will have to define the
	//overall positional layout of where the window will go on the screen.
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
    
    public static CurrentUserScreen getMe()
    {
        return me;
    }
}
