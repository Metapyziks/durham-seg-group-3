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


public abstract class SendMessageScreen extends Window 
{

	private Spec row1 = GridLayout.spec(0);
	private Spec row2 = GridLayout.spec(1);
	private Spec row3 = GridLayout.spec(2);
	private Spec row4 = GridLayout.spec(3);
	private Spec row5 = GridLayout.spec(4);
	private Spec row6 = GridLayout.spec(5);
	private Spec row7 = GridLayout.spec(6);
	private Spec row8 = GridLayout.spec(7);
	private Spec col1 = GridLayout.spec(0);
	private Spec col2 = GridLayout.spec(1);
	private Spec col3 = GridLayout.spec(2);
	private Spec col4 = GridLayout.spec(3);
	private TextField recipientField;
	private TextField subjectField;
	private TextField contentField;
	private static SendMessageScreen me;
	private static String receipient;

	public SendMessageScreen(String xreceipient)
	{
		super(R.drawable.send_message_bg);
		me = this;
		receipient = xreceipient;
		super.addContentToContentPane(createWindowPane());
	}

	private GridLayout createWindowPane()
	{
		GridLayout mainArea = new GridLayout(Fortitude.getFortitude());
		mainArea.setColumnCount(1);
		mainArea.setRowCount(9);

		LayoutParams topSpaceLayout = new LayoutParams(row1, col1); //top space
		topSpaceLayout.height = (int) (super.getWindowHeight() * 0.2345);
		Space topSpace = new Space(mainArea.getContext());
		topSpace.setLayoutParams(topSpaceLayout);
		mainArea.addView(topSpace, topSpaceLayout);

		GridLayout recipientGrid = new GridLayout(mainArea.getContext()); //Recipient Grid
		recipientGrid.setColumnCount(3);
		recipientGrid.setRowCount(1);

		LayoutParams firstInputSpaceLayout = new LayoutParams(row1, col1); //first input space spacer
		firstInputSpaceLayout.width = (int) (super.getWindowWidth() * 0.45);
		Space firstInputSpace = new Space(recipientGrid.getContext());
		firstInputSpace.setLayoutParams(firstInputSpaceLayout);
		recipientGrid.addView(firstInputSpace, firstInputSpaceLayout);

		LayoutParams firstInputTextFieldLayout = new LayoutParams(row1, col2); //first input text field
		firstInputTextFieldLayout.width = (int) (super.getWindowWidth() * 0.5);
		firstInputTextFieldLayout.height = super.getWindowHeight() / 12;
		TextFieldImage firstInputTextFieldImage = new TextFieldImage();
		firstInputTextFieldImage.setLayoutParams(firstInputTextFieldLayout);
		recipientGrid.addView(firstInputTextFieldImage, firstInputTextFieldLayout);

		recipientField = new TextField(16);
		recipientField.setText(receipient);
		recipientField.setLayoutParams(firstInputTextFieldLayout);
		recipientGrid.addView(recipientField, firstInputTextFieldLayout);

		LayoutParams receipientGridLayout = new LayoutParams(row2, col1); //Add Recipient grid to view
		mainArea.addView(recipientGrid, receipientGridLayout);

		LayoutParams secondRowSpacerLayout = new LayoutParams (row3, col1); //Spacer between Recipient and Subject
		secondRowSpacerLayout.height = (int) (super.getWindowHeight() * 0.03);
		Space secondRowSpacer = new Space(mainArea.getContext());
		secondRowSpacer.setLayoutParams(secondRowSpacerLayout);
		mainArea.addView(secondRowSpacer, secondRowSpacerLayout);

		GridLayout subjectGrid = new GridLayout (mainArea.getContext()); //Subject Grid
		subjectGrid.setColumnCount(3);
		subjectGrid.setRowCount(1);

		LayoutParams secondInputSpaceLayout = new LayoutParams(row1, col1); //second input space spacer
		secondInputSpaceLayout.width = (int) (super.getWindowWidth() * 0.45);
		Space secondInputSpace = new Space(subjectGrid.getContext());
		secondInputSpace.setLayoutParams(secondInputSpaceLayout);
		subjectGrid.addView(secondInputSpace, secondInputSpaceLayout);

		LayoutParams secondInputTextFieldLayout = new LayoutParams(row1, col2); //second input text field
		secondInputTextFieldLayout.width = (int) (super.getWindowWidth() * 0.5);
		secondInputTextFieldLayout.height = super.getWindowHeight() / 12;
		TextFieldImage secondInputTextFieldImage = new TextFieldImage();
		secondInputTextFieldImage.setLayoutParams(secondInputTextFieldLayout);
		subjectGrid.addView(secondInputTextFieldImage, secondInputTextFieldLayout);
		subjectField = new TextField(20);
		subjectField.setLayoutParams(secondInputTextFieldLayout);
		subjectGrid.addView(subjectField, secondInputTextFieldLayout);

		LayoutParams subjectGridLayout = new LayoutParams(row4, col1); //add Subject Grid to view
		mainArea.addView(subjectGrid, subjectGridLayout);

		LayoutParams thirdRowSpacerLayout = new LayoutParams (row5, col1); //Spacer between subject and main message
		thirdRowSpacerLayout.height = (int) (super.getWindowHeight() * 0.03);
		Space thirdRowSpacer = new Space(mainArea.getContext());
		thirdRowSpacer.setLayoutParams(thirdRowSpacerLayout);
		mainArea.addView(thirdRowSpacer, thirdRowSpacerLayout);

		GridLayout messageGrid = new GridLayout (mainArea.getContext());
		messageGrid.setColumnCount(3);
		messageGrid.setRowCount(1);

		LayoutParams thirdInputSpaceLayout = new LayoutParams(row1, col1); //third input space spacer
		thirdInputSpaceLayout.width = (int) (super.getWindowWidth() * 0.1);
		Space thirdInputSpace = new Space(messageGrid.getContext());
		thirdInputSpace.setLayoutParams(thirdInputSpaceLayout);
		messageGrid.addView(thirdInputSpace, thirdInputSpaceLayout);

		LayoutParams thirdInputTextFieldLayout = new LayoutParams(row1, col2); //third input text field
		thirdInputTextFieldLayout.width = (int) (super.getWindowWidth() * 0.8);
		thirdInputTextFieldLayout.height = super.getWindowHeight() / 3;
		TextFieldImage thirdInputTextFieldImage = new TextFieldImage();
		thirdInputTextFieldImage.setImageResource(R.drawable.large_text_field);
		thirdInputTextFieldImage.setLayoutParams(thirdInputTextFieldLayout);
		messageGrid.addView(thirdInputTextFieldImage, thirdInputTextFieldLayout);

		contentField = new TextField(1000);
		contentField.setGravity(Gravity.TOP);
		contentField.setLayoutParams(thirdInputTextFieldLayout);
		messageGrid.addView(contentField, thirdInputTextFieldLayout);

		LayoutParams messageGridLayout = new LayoutParams(row6, col1);// Adding main message to view
		mainArea.addView(messageGrid, messageGridLayout);

		LayoutParams seventhRowSpaceLayout = new LayoutParams(row7, col1);
		seventhRowSpaceLayout.height = super.getWindowHeight() / 15;
		Space seventhRowSpace = new Space(mainArea.getContext());
		seventhRowSpace.setLayoutParams(seventhRowSpaceLayout);
		mainArea.addView(seventhRowSpace, seventhRowSpaceLayout);

		GridLayout buttonRowGrid = new GridLayout (mainArea.getContext()); // Button Grid
		buttonRowGrid.setColumnCount(5);
		buttonRowGrid.setRowCount(1);

		LayoutParams firstButtonSpaceLayout = new LayoutParams(row1, col1); //first button spacer
		firstButtonSpaceLayout.width = super.getWindowWidth() / 12;
		Space firstButtonSpace = new Space(buttonRowGrid.getContext());
		firstButtonSpace.setLayoutParams(firstButtonSpaceLayout);
		buttonRowGrid.addView(firstButtonSpace, firstButtonSpaceLayout);

		LayoutParams sendButtonLayout = new LayoutParams(row1, col2); // Send Button
		sendButtonLayout.height = super.getWindowHeight() /10;
		sendButtonLayout.width = (int) (super.getWindowWidth() *0.4);
		FortitudeButton sendButton = (new FortitudeButton(R.drawable.send_message, R.drawable.send_message_pressed) {
			public void preClickActions() 
			{

			}
			public void whenClicked() 
			{
				if(!validateFields())
				{
					return;
				}
				ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Connecting To Server", false));
				ServerRequests.sendMessage(recipientField.getText().toString(), subjectField.getText().toString(), contentField.getText().toString());
				Thread thread = new Thread(new Runnable() {
					public void run()
					{
						while(!ServerRequests.getSendMessageComplete())
						{
							try
							{
								Thread.sleep(100);
							}
							catch(Exception e)
							{
								//oh well
							}
						}
						if(ServerRequests.getSendMessageSuccess())
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
									SendMessageScreen.getMe().killMe();
									whenCancelled();
								}
							});
						}
					}
				});
				thread.start();
			}			
		});
		sendButton.setLayoutParams(sendButtonLayout);
		buttonRowGrid.addView(sendButton, sendButtonLayout);

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
				SendMessageScreen.getMe().killMe();
				whenCancelled();
			}

		});
		cancelButton.setLayoutParams(cancelButtonLayout);
		buttonRowGrid.addView(cancelButton, cancelButtonLayout);

		LayoutParams buttonRowGridLayout = new LayoutParams(row8, col1);
		mainArea.addView(buttonRowGrid, buttonRowGridLayout);

		return mainArea;
	}

	private boolean validateFields()
	{
		if(recipientField.getText().toString().length() < 5)
		{
			MessageBox.newMsgBox("Receipient name must be greater than 5 characters!", true);
			return false;
		}
		else if(subjectField.getText().toString().length() < 1)
		{
			MessageBox.newMsgBox("Please enter a subject!", true);
			return false;
		}
		else if(contentField.getText().toString().length() < 1)
		{
			MessageBox.newMsgBox("Please enter a message to send!", true);
			return false;
		}
		return true;
	}

	public void killMe()
	{
		me = null;
		this.removeAllViews();
	}

	public static SendMessageScreen getMe()
	{
		return me;
	}

	public abstract void whenCancelled();
}
