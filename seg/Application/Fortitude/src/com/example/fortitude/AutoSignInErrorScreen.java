package com.example.fortitude;

import android.graphics.Color;
import android.view.Gravity;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.GridLayout.LayoutParams;
import android.widget.GridLayout.Spec;
import android.widget.Space;

public class AutoSignInErrorScreen extends Window 
{
	private Spec row1 = GridLayout.spec(0);
	private Spec row2 = GridLayout.spec(1);
	private Spec row3 = GridLayout.spec(2);
	private Spec row4 = GridLayout.spec(3);
	
	private Spec col1 = GridLayout.spec(0);
	private Spec col2 = GridLayout.spec(1);
	private Spec col3 = GridLayout.spec(2);
	
	private static AutoSignInErrorScreen me;
	private String uname;
	private String phash;
	private boolean initialLogin;
	private TextView errorBox;
	
	////////
	//
	//Constructor
	//
	////////
	public AutoSignInErrorScreen(String uname, String phash, boolean initialLogin)
	{
		super(R.drawable.error_bg);
    	me = this;
    	this.uname = uname;
    	this.phash = phash;
    	this.initialLogin = initialLogin;
    	addContentToContentPane(createWindowPane());
	}
	
	private GridLayout createWindowPane()
    {
		GridLayout mainArea = new GridLayout(Fortitude.getFortitude());
		mainArea.setRowCount(5);
		mainArea.setColumnCount(1);
		
		LayoutParams firstRowSpaceLayout = new LayoutParams(row1, col1); //top row space
		firstRowSpaceLayout.height = (int) (super.getWindowHeight() * 0.58);
		Space firstRowSpace = new Space(mainArea.getContext());
		firstRowSpace.setLayoutParams(firstRowSpaceLayout);
		mainArea.addView(firstRowSpace, firstRowSpaceLayout);
		
		GridLayout errorGrid = new GridLayout(mainArea.getContext());
		errorGrid.setRowCount(1);
		errorGrid.setColumnCount(3);
		
		LayoutParams errorGridLeftSpaceLayout = new LayoutParams(row1, col1);
		errorGridLeftSpaceLayout.width = super.getWindowWidth() / 10;
		Space errorGridLeftSpace = new Space(errorGrid.getContext());
		errorGridLeftSpace.setLayoutParams(errorGridLeftSpaceLayout);
		errorGrid.addView(errorGridLeftSpace, errorGridLeftSpaceLayout);
		
		LayoutParams errorBoxLayout = new LayoutParams(row1, col2);
		errorBoxLayout.width = (int)(super.getWindowWidth() * 0.8);
		errorBoxLayout.height = super.getWindowHeight() / 4;
		errorBox = new TextView(errorGrid.getContext());
		errorBox.setTextColor(Color.WHITE);
		errorBox.setTextSize(14);
		errorBox.setGravity(Gravity.CENTER);
		errorBox.setLayoutParams(errorBoxLayout);
		errorGrid.addView(errorBox, errorBoxLayout);
		
		LayoutParams errorGridLayout = new LayoutParams(row2, col1);
		mainArea.addView(errorGrid, errorGridLayout);
		
		LayoutParams thirdRowSpaceLayout = new LayoutParams(row3, col1);
		thirdRowSpaceLayout.height = super.getWindowHeight() / 17;
		Space thirdRowSpace = new Space(mainArea.getContext());
		thirdRowSpace.setLayoutParams(thirdRowSpaceLayout);
		mainArea.addView(thirdRowSpace, thirdRowSpaceLayout);
		
		GridLayout buttonRow = new GridLayout(mainArea.getContext()); //button row
		buttonRow.setRowCount(1);
		buttonRow.setColumnCount(3);
		
		LayoutParams buttonRowLeftSpaceLayout = new LayoutParams(row1, col1);
		buttonRowLeftSpaceLayout.width = super.getWindowWidth() / 4;
		Space buttonRowLeftSpace = new Space(buttonRow.getContext());
		buttonRowLeftSpace.setLayoutParams(buttonRowLeftSpaceLayout);
		buttonRow.addView(buttonRowLeftSpace, buttonRowLeftSpaceLayout);
		
		LayoutParams retryButtonLayout = new LayoutParams(row1, col2);
		retryButtonLayout.width = super.getWindowWidth() / 2;
		retryButtonLayout.height = super.getWindowHeight() / 10;
		FortitudeButton retryButton = (new FortitudeButton(R.drawable.retry, R.drawable.retry_pressed) {
			public void preClickActions() 
			{
				
			}

			public void whenClicked() 
			{
				ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Connecting To Server", false));
				ServerRequests.createSession(uname, phash, initialLogin);
			}
		});
		retryButton.setLayoutParams(retryButtonLayout);
		buttonRow.addView(retryButton, retryButtonLayout);
		
		LayoutParams buttonRowLayout = new LayoutParams(row4, col1);
		mainArea.addView(buttonRow, buttonRowLayout);
		
		return mainArea;
    }
	
	public TextView getErrorBox()
	{
		return errorBox;
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
    public static AutoSignInErrorScreen getMe()
    {
    	return me;
    }
}
