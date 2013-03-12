package com.example.fortitude;

import java.util.ArrayList;

import android.widget.ImageView.ScaleType;
import android.widget.ImageView;
import android.widget.GridLayout;
import android.widget.GridLayout.LayoutParams;
import android.widget.GridLayout.Spec;
import android.widget.Space;
import android.widget.TextView;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;

public class InboxScreen extends Window 
{
	private Spec row1 = GridLayout.spec(0);
	private Spec row2 = GridLayout.spec(1);
	private Spec row3 = GridLayout.spec(2);
	private Spec row4 = GridLayout.spec(3);
	private Spec row5 = GridLayout.spec(4);
	
	private Spec col1 = GridLayout.spec(0);
	private Spec col2 = GridLayout.spec(1);
	private Spec col3 = GridLayout.spec(2);
	private Spec col4 = GridLayout.spec(3);
	private Spec col5 = GridLayout.spec(4);
	private Spec col6 = GridLayout.spec(5);
	
	private static InboxScreen me;
	public static int pageId;
	
	private boolean needRightArrow;
	
	public InboxScreen(int pageidentifier)
	{
		super(R.drawable.inbox_bg);
		me = this;
		pageId = pageidentifier;
		needRightArrow = true;
		super.addContentToContentPane(createWindowPane());
	}
	
	private GridLayout createWindowPane()
	{
		GridLayout mainArea = new GridLayout(Fortitude.getFortitude());
		mainArea.setColumnCount(1);
		mainArea.setRowCount(7);
		
		LayoutParams topSpaceLayout = new LayoutParams(row1, col1); //top space
		topSpaceLayout.height = (int) (super.getWindowHeight() * 0.2345);
		Space topSpace = new Space(mainArea.getContext());
		topSpace.setLayoutParams(topSpaceLayout);
		mainArea.addView(topSpace, topSpaceLayout);
		
		GridLayout messagesGrid = new GridLayout(mainArea.getContext());
		messagesGrid.setColumnCount(1);
		messagesGrid.setRowCount(8);
		
		ArrayList<NotificationStub> messages = NotificationManager.getMessages();
		
		for(int i = (pageId * 8); i < ((pageId + 1) * 7); i++)
		{
			NotificationStub theStub = null;
			if(!(i >= messages.size()))
			{
				theStub = messages.get(i);
			}
			LayoutParams panelLayout = new LayoutParams(GridLayout.spec(i - (pageId * 7)), col1);
			panelLayout.width = super.getWindowWidth();
			panelLayout.height = super.getWindowHeight() / 12;
			NotificationPanel n = new NotificationPanel(messagesGrid.getContext(), theStub, super.getWindowWidth(), super.getWindowHeight());	
			if(theStub == null)
			{
				needRightArrow = false;
			}
			if(theStub == null)
			{
				needRightArrow = false;
			}
			if(theStub != null)
			{
				if(theStub.getType().equals("Message"))
				{
					n.setOnClickListener(new OnClickListener() {
						public void onClick(View arg0)
						{
							NotificationPanel theView = (NotificationPanel) arg0;
							NotificationStub theStub = theView.getStub();
							ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Connecting To Server", false));
							ServerRequests.readMessage(theStub.getNotificationId());
							Thread thread = new Thread(new Runnable() {
								public void run()
								{
									while(!ServerRequests.getStaticReadMessageComplete())
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
									if(ServerRequests.getStaticReadMessageSuccess())
									{
										NotificationManager.setAsRead(ServerRequests.getStaticMessageId());
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
												InboxScreen.getMe().killMe();
												new ViewMessageScreen(ServerRequests.getMessageSenderName(), ServerRequests.getMessageSubject(), ServerRequests.getMessageContent(), ServerRequests.getStaticMessageId(), 1) {
													public void whenCancelled()
													{
														new InboxScreen(InboxScreen.pageId);
													}
												};
											}
										});
									}
								}
							});
							thread.start();
						}
					});
				}
				else if(NotificationManager.getMessageStub(i).getType().equals("BattleReport"))
				{

				}
			}
			n.setLayoutParams(panelLayout);
			messagesGrid.addView(n, panelLayout);
		}
		
		LayoutParams messagesGridLayout = new LayoutParams(row2, col1);
		mainArea.addView(messagesGrid, messagesGridLayout);
		
		GridLayout firstButtonGrid = new GridLayout(mainArea.getContext());
		firstButtonGrid.setColumnCount(5);
		firstButtonGrid.setRowCount(1);
		
		LayoutParams firstButtonGridLeftSpaceLayout = new LayoutParams(row1, col1); //firstbutton grid left space
		firstButtonGridLeftSpaceLayout.width = super.getWindowWidth() / 12;
		Space firstButtonGridLeftSpace = new Space(firstButtonGrid.getContext());
		firstButtonGridLeftSpace.setLayoutParams(firstButtonGridLeftSpaceLayout);
		firstButtonGrid.addView(firstButtonGridLeftSpace, firstButtonGridLeftSpaceLayout);
		
		LayoutParams sendMessageButtonLayout = new LayoutParams(row1, col2); //send messages button
		sendMessageButtonLayout.width = (super.getWindowWidth() / 2) - (super.getWindowWidth() / 10);
		sendMessageButtonLayout.height = super.getWindowHeight() / 10;
		FortitudeButton sendMessageButton = (new FortitudeButton(R.drawable.send_message, R.drawable.send_message_pressed) {
			public void preClickActions() 
			{
				
			}
			public void whenClicked() 
			{
				InboxScreen.getMe().killMe();
				new SendMessageScreen("") {
					public void whenCancelled()
					{
						new InboxScreen(InboxScreen.pageId);
					}
				};
			}
		});
		sendMessageButton.setLayoutParams(sendMessageButtonLayout);
		firstButtonGrid.addView(sendMessageButton, sendMessageButtonLayout);
		
		LayoutParams firstButtonGridMiddleSpacerLayout = new LayoutParams(row1, col3);
		firstButtonGridMiddleSpacerLayout.width = super.getWindowWidth() / 15;
		Space firstButtonGridMiddleSpacer = new Space(firstButtonGrid.getContext());
		firstButtonGridMiddleSpacer.setLayoutParams(firstButtonGridMiddleSpacerLayout);
		firstButtonGrid.addView(firstButtonGridMiddleSpacer, firstButtonGridMiddleSpacerLayout);
		
		LayoutParams settingsButtonLayout = new LayoutParams(row1, col4);
		settingsButtonLayout.height = super.getWindowHeight() / 10;
		settingsButtonLayout.width = (super.getWindowWidth() / 2) - (super.getWindowWidth() / 10);
		FortitudeButton settingsButton = (new FortitudeButton(R.drawable.settings, R.drawable.settings_pressed) {
			public void preClickActions()
			{
				
			}
			public void whenClicked() 
			{
				InboxScreen.getMe().killMe();
				new MessageSettingsScreen();
			}
		});
		settingsButton.setLayoutParams(settingsButtonLayout);
		firstButtonGrid.addView(settingsButton, settingsButtonLayout);
		
		LayoutParams firstButtonGridLayout = new LayoutParams(row3, col1);
		mainArea.addView(firstButtonGrid, firstButtonGridLayout);
		
		LayoutParams fourthRowSpaceLayout = new LayoutParams(row4, col1);
		fourthRowSpaceLayout.height = super.getWindowHeight() / 160;
		Space fourthRowSpace = new Space(mainArea.getContext());
		fourthRowSpace.setLayoutParams(fourthRowSpaceLayout);
		mainArea.addView(fourthRowSpace, fourthRowSpaceLayout);
		
		GridLayout secondRowGrid = new GridLayout(mainArea.getContext());
		secondRowGrid.setRowCount(1);
		secondRowGrid.setColumnCount(7);
		
		LayoutParams secondButtonRowLeftSpaceLayout = new LayoutParams(row1, col1);
		secondButtonRowLeftSpaceLayout.width = (int)(super.getWindowWidth() * 0.075);
		Space secondButtonRowLeftSpace = new Space(secondRowGrid.getContext());
		secondButtonRowLeftSpace.setLayoutParams(secondButtonRowLeftSpaceLayout);
		secondRowGrid.addView(secondButtonRowLeftSpace, secondButtonRowLeftSpaceLayout);
		
		LayoutParams leftButtonLayout = new LayoutParams(row1, col2);
		leftButtonLayout.height = (int)(super.getWindowHeight()*0.1);
		leftButtonLayout.width = (int)(super.getWindowWidth()*0.2);
		if(pageId != 0)
		{
			FortitudeButton leftButton = (new FortitudeButton(R.drawable.left, R.drawable.left_pressed) { //Left button!
				public void preClickActions()
				{

				}
				public void whenClicked()
				{
					pageId--;
					InboxScreen.getMe().killMe();
					new InboxScreen(pageId);
				}
			});
			leftButton.setLayoutParams(leftButtonLayout);
			secondRowGrid.addView(leftButton,leftButtonLayout);
		}
		else
		{
			Space leftButtonSpace = new Space(mainArea.getContext());
			leftButtonSpace.setLayoutParams(leftButtonLayout);
			secondRowGrid.addView(leftButtonSpace,leftButtonLayout);
		}

		LayoutParams secondRowGridMiddleLeftSpaceLayout = new LayoutParams(row1,col3);
		secondRowGridMiddleLeftSpaceLayout.height = (int)(super.getWindowHeight()*0.1);
		secondRowGridMiddleLeftSpaceLayout.width = (int)(super.getWindowWidth()*0.05);
		Space secondRowGridMiddleLeftSpace = new Space(secondRowGrid.getContext());
		secondRowGridMiddleLeftSpace.setLayoutParams(secondRowGridMiddleLeftSpaceLayout);
		secondRowGrid.addView(secondRowGridMiddleLeftSpace, secondRowGridMiddleLeftSpaceLayout);
		
		LayoutParams cancelButtonLayout = new LayoutParams(row1, col4);
		cancelButtonLayout.height = (int)(super.getWindowHeight()*0.1);
		cancelButtonLayout.width = (int)(super.getWindowWidth()*0.4);
		FortitudeButton cancelButton = (new FortitudeButton(R.drawable.cancel, R.drawable.cancel_pressed) { //cancel button
			public void preClickActions()
			{

			}
			public void whenClicked()
			{
				InboxScreen.getMe().killMe();
				new AccountScreen() {
					public void showNextScreen() {
						new MainScreen();
					}
				};
			}
		});
		cancelButton.setLayoutParams(cancelButtonLayout);
		secondRowGrid.addView(cancelButton, cancelButtonLayout);

		LayoutParams secondRowGridMiddleRightSpaceLayout = new LayoutParams(row1,col5);
		secondRowGridMiddleRightSpaceLayout.height = (int)(super.getWindowHeight()*0.1);
		secondRowGridMiddleRightSpaceLayout.width = (int)(super.getWindowWidth()*0.05);
		Space secondRowGridMiddleRightSpace = new Space(secondRowGrid.getContext());
		secondRowGridMiddleRightSpace.setLayoutParams(secondRowGridMiddleRightSpaceLayout);
		secondRowGrid.addView(secondRowGridMiddleRightSpace, secondRowGridMiddleRightSpaceLayout);

		LayoutParams rightButtonLayout = new LayoutParams(row1,col6);
		rightButtonLayout.height = (int)(super.getWindowHeight()*0.1);
		rightButtonLayout.width = (int)(super.getWindowWidth()*0.2);

		if(needRightArrow)
		{
			FortitudeButton rightButton = (new FortitudeButton(R.drawable.right, R.drawable.right_pressed) {
				public void preClickActions()
				{

				}
				public void whenClicked()
				{
					pageId++;
					InboxScreen.getMe().killMe();
					new InboxScreen(pageId);
				}
			});
			rightButton.setLayoutParams(rightButtonLayout);
			secondRowGrid.addView(rightButton, rightButtonLayout);
		}
		else
		{
			Space rightButtonSpace = new Space(secondRowGrid.getContext());
			rightButtonSpace.setLayoutParams(rightButtonLayout);
			secondRowGrid.addView(rightButtonSpace, rightButtonLayout);
		}
		
		LayoutParams secondButtonGridLayout = new LayoutParams(row5, col1);
		mainArea.addView(secondRowGrid, secondButtonGridLayout);
		
		return mainArea;
	}
	
	public static InboxScreen getMe()
	{
		return me;
	}
	
	public void killMe()
	{
		me = null;
		this.removeAllViews();
	}
}