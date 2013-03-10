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
import android.graphics.Typeface;

public abstract class ReportScreens extends Window 
{
	
	private Spec row1 = GridLayout.spec(0);
	private Spec row2 = GridLayout.spec(1);
	private Spec row3 = GridLayout.spec(2);
	private Spec row4 = GridLayout.spec(3);
	private Spec col1 = GridLayout.spec(0);
	private Spec col2 = GridLayout.spec(1);
	private Spec col3 = GridLayout.spec(2);
	private Spec col4 = GridLayout.spec(3);
	
	private TextField recipientField;
	private static int screenIdentity = 0;
	// 0 = User Profile
	// 1 = User Owned Cache
	// 2 = Resource Cache
	// 3 = User Communication
	private static ReportScreens me;
	
	public ReportScreens(int whatScreen)
	{
		super(R.drawable.report_user_profile_bg);
		me = this;
		screenIdentity = whatScreen;
		if(screenIdentity == 1)
		{
			super.getBackgroundImage().setImageResource(R.drawable.report_user_owned_cache_bg);
		}
		if(screenIdentity == 2)
		{
			super.getBackgroundImage().setImageResource(R.drawable.report_resource_cache_bg);
		}
		if(screenIdentity == 3)
		{
			super.getBackgroundImage().setImageResource(R.drawable.report_message_bg);
		}
		
		super.addContentToContentPane(addContentToPane());
	}
	
	private GridLayout addContentToPane()
	{
		GridLayout mainArea = new GridLayout(Fortitude.getFortitude());
		mainArea.setColumnCount(1);
		mainArea.setRowCount(5);
		
		LayoutParams topSpaceLayout = new LayoutParams(row1, col1); //top space
		topSpaceLayout.height = (int) (super.getWindowHeight() * 0.5);
		Space topSpace = new Space(mainArea.getContext());
		topSpace.setLayoutParams(topSpaceLayout);
		mainArea.addView(topSpace, topSpaceLayout);
		
		GridLayout messageGrid = new GridLayout (mainArea.getContext());
		messageGrid.setColumnCount(3);
		messageGrid.setRowCount(1);
		
		LayoutParams firstInputSpaceLayout = new LayoutParams(row1, col1); //first input space spacer
		firstInputSpaceLayout.width = (int) (super.getWindowWidth() * 0.1);
		Space firstInputSpace = new Space(messageGrid.getContext());
		firstInputSpace.setLayoutParams(firstInputSpaceLayout);
		messageGrid.addView(firstInputSpace, firstInputSpaceLayout);
		
		LayoutParams firstInputTextFieldLayout = new LayoutParams(row1, col2); //first input text field
		firstInputTextFieldLayout.width = (int) (super.getWindowWidth() * 0.8);
		firstInputTextFieldLayout.height = super.getWindowHeight() / 3;
		TextFieldImage firstInputTextFieldImage = new TextFieldImage();
		firstInputTextFieldImage.setImageResource(R.drawable.large_text_field);
		firstInputTextFieldImage.setLayoutParams(firstInputTextFieldLayout);
		messageGrid.addView(firstInputTextFieldImage, firstInputTextFieldLayout);
		
		recipientField = new TextField(1000);
		recipientField.setGravity(Gravity.TOP);
		recipientField.setLayoutParams(firstInputTextFieldLayout);
		messageGrid.addView(recipientField, firstInputTextFieldLayout);
		
		LayoutParams messageGridLayout = new LayoutParams(row2, col1);// Adding main message to view
		mainArea.addView(messageGrid, messageGridLayout);
		
		LayoutParams secondRowSpacerLayout = new LayoutParams (row3, col1); //Spacer between Message and Buttons
		secondRowSpacerLayout.height = (int) (super.getWindowHeight() * 0.066);
		Space secondRowSpacer = new Space(mainArea.getContext());
		secondRowSpacer.setLayoutParams(secondRowSpacerLayout);
		mainArea.addView(secondRowSpacer, secondRowSpacerLayout);
		
		GridLayout buttonRowGrid = new GridLayout (mainArea.getContext()); // Button Grid
		buttonRowGrid.setColumnCount(5);
		buttonRowGrid.setRowCount(1);
		
		LayoutParams firstButtonSpaceLayout = new LayoutParams(row1, col1); //first button spacer
		firstButtonSpaceLayout.width = super.getWindowWidth() / 12;
		Space firstButtonSpace = new Space(buttonRowGrid.getContext());
		firstButtonSpace.setLayoutParams(firstButtonSpaceLayout);
		buttonRowGrid.addView(firstButtonSpace, firstButtonSpaceLayout);
		
		LayoutParams submitButtonLayout = new LayoutParams(row1, col2); // Submit Button
		submitButtonLayout.height = super.getWindowHeight() /10;
		submitButtonLayout.width = (int) (super.getWindowWidth() *0.4);
		FortitudeButton submitButton = (new FortitudeButton(R.drawable.submit, R.drawable.submit_pressed) {
			public void preClickActions() 
			{

			}

			public void whenClicked() 
			{
				
			}
		});
		submitButton.setLayoutParams(submitButtonLayout);
		buttonRowGrid.addView(submitButton, submitButtonLayout);
		
		LayoutParams secondButtonSpaceLayout = new LayoutParams(row1, col3); //second button spacer
		secondButtonSpaceLayout.width = super.getWindowWidth() / 12;
		Space secondButtonSpace = new Space(buttonRowGrid.getContext());
		secondButtonSpace.setLayoutParams(secondButtonSpaceLayout);
		buttonRowGrid.addView(secondButtonSpace, secondButtonSpaceLayout);
		
		LayoutParams cancelButtonLayout = new LayoutParams(row1, col4); //cancel button
		cancelButtonLayout.height = super.getWindowHeight() /10;
		cancelButtonLayout.width = (int) (super.getWindowWidth() *0.4);
		FortitudeButton cancelButton = (new FortitudeButton(R.drawable.cancel, R.drawable.cancel_pressed) {
			public void preClickActions() 
			{

			}

			public void whenClicked() 
			{
				ReportScreens.getMe().killMe();
				whenCancelled();
			}
		});
		cancelButton.setLayoutParams(cancelButtonLayout);
		buttonRowGrid.addView(cancelButton, cancelButtonLayout);
		
		LayoutParams buttonRowGridLayout = new LayoutParams(row4, col1);
		mainArea.addView(buttonRowGrid, buttonRowGridLayout);
		
		return mainArea;
	}
	
    public void killMe()
    {
    	me = null;
    	super.removeAllViews();
    }
    
    public static ReportScreens getMe()
    {
    	return me;
    }
    
    public abstract void whenCancelled();
}
