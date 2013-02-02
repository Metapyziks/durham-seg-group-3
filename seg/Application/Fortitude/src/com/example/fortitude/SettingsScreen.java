package com.example.fortitude;

import android.widget.GridLayout;
import android.widget.GridLayout.LayoutParams;
import android.widget.GridLayout.Spec;
import android.widget.Space;
import android.widget.TextView;
import android.view.Gravity;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.ImageView.ScaleType;
import android.widget.ImageView;
import android.graphics.Color;

public class SettingsScreen extends Window
{
	private Spec row1 = GridLayout.spec(0);
	private Spec row2 = GridLayout.spec(1);
	private Spec row3 = GridLayout.spec(2);
	private Spec row4 = GridLayout.spec(3);
	
	private Spec col1 = GridLayout.spec(0);
	private Spec col2 = GridLayout.spec(1);
	private Spec col3 = GridLayout.spec(2);
	private Spec col4 = GridLayout.spec(3);
	
	private static SettingsScreen me;
	
	////////
	//
	//constructor
	//
	////////
	public SettingsScreen()
	{
		super(R.drawable.settings_background);
		me = this;
		addContentToContentPane(createWindowPane());
	}
	
	private GridLayout createWindowPane()
	{
		GridLayout mainArea = new GridLayout(Fortitude.getFortitude());
		mainArea.setColumnCount(1);
		mainArea.setRowCount(10);
		
		LayoutParams firstRowSpaceLayout = new LayoutParams(row1, col1); //first row space
		firstRowSpaceLayout.height = super.getWindowHeight() / 3;
		firstRowSpaceLayout.width = super.getWindowWidth();
		Space firstRowSpace = new Space(mainArea.getContext());
		firstRowSpace.setLayoutParams(firstRowSpaceLayout);
		mainArea.addView(firstRowSpace, firstRowSpaceLayout);
		
		GridLayout secondRowButtonGrid = new GridLayout(mainArea.getContext());
		secondRowButtonGrid.setColumnCount(5);
		secondRowButtonGrid.setRowCount(1);
		
		LayoutParams secondRowButtonLeftSpaceLayout = new LayoutParams(row1, col1); //first row of buttons left space
		secondRowButtonLeftSpaceLayout.width = (super.getWindowWidth() / 10) - (super.getWindowWidth() / 40);
		secondRowButtonLeftSpaceLayout.height = super.getWindowHeight() / 10;
		Space secondRowButtonLeftSpace = new Space(secondRowButtonGrid.getContext());
		secondRowButtonLeftSpace.setLayoutParams(secondRowButtonLeftSpaceLayout);
		secondRowButtonGrid.addView(secondRowButtonLeftSpace, secondRowButtonLeftSpaceLayout);
    	
		//DO ME LATER!!!
		
		LayoutParams secondRowButtonGridLayout = new LayoutParams(row2, col1);
		mainArea.addView(secondRowButtonGrid, secondRowButtonGridLayout);
		
		LayoutParams thirdRowSpaceLayout = new LayoutParams(row3, col1); //third row space
		thirdRowSpaceLayout.height = super.getWindowHeight() / 4;
		Space thirdRowSpace = new Space(mainArea.getContext());
		thirdRowSpace.setLayoutParams(thirdRowSpaceLayout);
		mainArea.addView(thirdRowSpace, thirdRowSpaceLayout);
		
		GridLayout fourthRowButtonsGrid = new GridLayout(mainArea.getContext());
		fourthRowButtonsGrid.setRowCount(1);
		fourthRowButtonsGrid.setColumnCount(5);
		
		LayoutParams fourthRowButtonLeftSpaceLayout = new LayoutParams(row1, col1);
		fourthRowButtonLeftSpaceLayout.width = (super.getWindowWidth() / 10) - (super.getWindowWidth() / 40);
		Space fourthRowButtonLeftSpace = new Space(fourthRowButtonsGrid.getContext());
		fourthRowButtonLeftSpace.setLayoutParams(fourthRowButtonLeftSpaceLayout);
		fourthRowButtonsGrid.addView(fourthRowButtonLeftSpace, fourthRowButtonLeftSpaceLayout);
		
		LayoutParams viewBlockedButtonLayout = new LayoutParams(row1, col2); //view blocked users button
		viewBlockedButtonLayout.width = (super.getWindowWidth() / 2) - (super.getWindowWidth() / 10);
		viewBlockedButtonLayout.height = super.getWindowHeight() / 10;
		FortitudeButton viewBlockedButton = (new FortitudeButton(R.drawable.view_blocked, R.drawable.view_blocked_pressed) {
			public void preClickActions()
			{
				
			}
			public void whenClicked()
			{
				
			}
		});
		viewBlockedButton.setLayoutParams(viewBlockedButtonLayout);
		fourthRowButtonsGrid.addView(viewBlockedButton, viewBlockedButtonLayout);
		
		LayoutParams fourthRowButtonMiddleSpaceLayout = new LayoutParams(row1, col3);
		fourthRowButtonMiddleSpaceLayout.width = super.getWindowWidth() / 15;
		Space fourthRowButtonMiddleSpace = new Space(fourthRowButtonsGrid.getContext());
		fourthRowButtonMiddleSpace.setLayoutParams(fourthRowButtonMiddleSpaceLayout);
		fourthRowButtonsGrid.addView(fourthRowButtonMiddleSpace, fourthRowButtonMiddleSpaceLayout);
		
		//asdfasfad
		
		LayoutParams fourthRowButtonsGridLayout = new LayoutParams(row4, col1);
		mainArea.addView(fourthRowButtonsGrid, fourthRowButtonsGridLayout);
		
		return mainArea;
	}
	
	////////
	//
	//getMe
	//
	//returns the last created instance of this class, could be null..
	//
	////////
	public static SettingsScreen getMe()
	{
		return me;
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
		removeAllViews();
	}
	
	////////
	//
	//topBarClicked
	//
	//called when the top bar is clicked
	//
	////////
	public void topBarClicked()
	{
		killMe();
		new AccountScreen() {
			public void showNextScreen()
			{
				new SettingsScreen();
			}
		};
	}
}
