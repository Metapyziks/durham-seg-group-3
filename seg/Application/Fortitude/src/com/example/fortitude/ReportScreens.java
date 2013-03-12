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
	public static Reportable reportable;
	private static int screenIdentity = 0;
	// 0 = User Profile
	// 1 = User Owned Cache
	// 2 = Resource Cache
	// 3 = User Communication
	private static ReportScreens me;
	private static String reportableType;
	
	public ReportScreens(int whatScreen, Reportable reportablex)
	{
		super(R.drawable.report_user_profile_bg);
		me = this;
		reportable = reportablex;
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
		
		if(reportable instanceof Cache)
		{
			reportableType = "Cache";
		}
		else if(reportable instanceof User)
		{
			reportableType = "Account";
		}
		else if(reportable instanceof NotificationStub)
		{
			reportableType = "Message";
		}
		else
		{
			reportableType = "ERROR!";
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
				String content = recipientField.getText().toString();
				if(content.length() < 1)
				{
					MessageBox.newMsgBox("Please enter a reason for why you are sending the report", true);
					return;
				}
				ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Connecting To Server", false));
				ServerRequests.report(reportable.getIdentifier(), reportableType, content);
				Thread thread = new Thread(new Runnable() {
					public void run()
					{
						while(!ServerRequests.reportThingComplete)
						{
							try
							{
								Thread.sleep(100);
							}
							catch(Exception e)
							{
								
							}
						}
						if(ServerRequests.reportThingSuccess)
						{
							Fortitude.getFortitude().runOnUiThread(new Runnable() {
								public void run()
								{
									if(ServerRequests.getTheMessageBox() != null)
									{
										ServerRequests.getTheMessageBox().killMe();
									}
									if(MessageBox.getMe() != null)
									{
										MessageBox.getMe().killMe();
									}
									ReportScreens.getMe().killMe();
									whenCancelled();
									MessageBox.newMsgBox("Thankyou for reporting, we'll take a look at and deal with what you sent, and contact you about the issue if we need to", true);
								}
							});
						}
					}
				});
				thread.start();
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
