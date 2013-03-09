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

public class NotificationScreen extends Window 
{
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
	private Spec col4 = GridLayout.spec(3);
	private Spec col5 = GridLayout.spec(4);
	private Spec col6 = GridLayout.spec(5);

	private static int pageId;
	public static int rememberSize;

	private static NotificationScreen me;

	public NotificationScreen(int pageIdentity)
	{
		super(R.drawable.news);
		me = this;
		pageId = pageIdentity;
		addContentToContentPane(createWindowPane());
	}

	private GridLayout createWindowPane()
	{
		boolean needRightArrow = true;

		GridLayout mainArea = new GridLayout(Fortitude.getFortitude());
		mainArea.setRowCount(5);
		mainArea.setColumnCount(1);

		LayoutParams topSpaceRowLayout = new LayoutParams(row1, col1);
		topSpaceRowLayout.height = super.getWindowHeight() / 5;
		Space topSpaceRow = new Space(mainArea.getContext());
		topSpaceRow.setLayoutParams(topSpaceRowLayout);
		mainArea.addView(topSpaceRow, topSpaceRowLayout);

		GridLayout notificationGrid = new GridLayout(mainArea.getContext()); //notifications
		notificationGrid.setColumnCount(1);
		notificationGrid.setRowCount(8);

		for(int i = (pageId * 8); i < ((pageId + 1) * 8); i++)
		{
			LayoutParams panelLayout = new LayoutParams(GridLayout.spec(i - (pageId * 8)), col1);
			panelLayout.width = super.getWindowWidth();
			panelLayout.height = super.getWindowHeight() / 12;
			NotificationPanel n = new NotificationPanel(notificationGrid.getContext(), NotificationManager.getMessageStub(i), super.getWindowWidth(), super.getWindowHeight());	
			if(NotificationManager.getMessageStub(i) == null)
			{
				needRightArrow = false;
			}
			n.setLayoutParams(panelLayout);
			notificationGrid.addView(n, panelLayout);
		}

		LayoutParams notificationGridLayout = new LayoutParams(row2, col1);
		mainArea.addView(notificationGrid, notificationGridLayout);

		LayoutParams thirdRowSpaceLayout = new LayoutParams(row3, col1);
		thirdRowSpaceLayout.height = super.getWindowHeight() / 20;
		Space thirdRowSpace = new Space(mainArea.getContext());
		thirdRowSpace.setLayoutParams(thirdRowSpaceLayout);
		mainArea.addView(thirdRowSpace, thirdRowSpaceLayout);

		GridLayout buttonRow = new GridLayout(mainArea.getContext());
		buttonRow.setRowCount(1);
		buttonRow.setColumnCount(7);

		LayoutParams firstColumnSpaceLayout = new LayoutParams(row1, col1);
		firstColumnSpaceLayout.width = (int)(super.getWindowWidth() * 0.075);
		Space firstColumnSpace = new Space(buttonRow.getContext());
		firstColumnSpace.setLayoutParams(firstColumnSpaceLayout);
		buttonRow.addView(firstColumnSpace, firstColumnSpaceLayout);

		LayoutParams leftButtonLayout = new LayoutParams(row1,col2);
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
					NotificationScreen.getMe().killMe();
					new NotificationScreen(pageId);
				}
			});
			leftButton.setLayoutParams(leftButtonLayout);
			buttonRow.addView(leftButton,leftButtonLayout);
		}
		else
		{
			Space leftButtonSpace = new Space(mainArea.getContext());
			leftButtonSpace.setLayoutParams(leftButtonLayout);
			buttonRow.addView(leftButtonSpace,leftButtonLayout);
		}

		LayoutParams thirdColSpaceLayout = new LayoutParams(row1,col3);
		thirdColSpaceLayout.height = (int)(super.getWindowHeight()*0.1);
		thirdColSpaceLayout.width = (int)(super.getWindowWidth()*0.05);
		Space thirdColSpace = new Space(mainArea.getContext());
		thirdColSpace.setLayoutParams(thirdColSpaceLayout);
		buttonRow.addView(thirdColSpace,thirdColSpaceLayout);

		LayoutParams cancelButtonLayout = new LayoutParams(row1,col4);
		cancelButtonLayout.height = (int)(super.getWindowHeight()*0.1);
		cancelButtonLayout.width = (int)(super.getWindowWidth()*0.4);

		FortitudeButton cancelButton = (new FortitudeButton(R.drawable.cancel, R.drawable.cancel_pressed) { //cancel button
			public void preClickActions()
			{

			}
			public void whenClicked()
			{
				NotificationScreen.getMe().killMe();
				new MainScreen();
			}
		});
		cancelButton.setLayoutParams(cancelButtonLayout);
		buttonRow.addView(cancelButton, cancelButtonLayout);

		LayoutParams fifthColSpaceLayout = new LayoutParams(row1,col5);
		fifthColSpaceLayout.height = (int)(super.getWindowHeight()*0.1);
		fifthColSpaceLayout.width = (int)(super.getWindowWidth()*0.05);
		Space fifthColSpace = new Space(mainArea.getContext());
		fifthColSpace.setLayoutParams(fifthColSpaceLayout);
		buttonRow.addView(fifthColSpace,fifthColSpaceLayout);

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
					if(NotificationManager.getMessageStub((pageId + 1) * 8) == null)
					{
						//there are no more messages left in the manager, check for more...
						if(NotificationManager.getMessageStub((pageId * 8) + 7) == null)
						{
							MessageBox.newMsgBox("Error Fetching News", true);
							return;
						}
						ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Fetching Older News", true));
						rememberSize = NotificationManager.getSize();
						ServerRequests.getNewsStubs(NotificationManager.getMessageStub((pageId * 8) + 7).getTimeStamp(), 16);
						Thread thread = new Thread(new Runnable() {
							public void run()
							{
								while(!ServerRequests.getStaticGetNewsComplete())
								{
									//WAIT!
									try
									{
										Thread.sleep(100);
									}
									catch(Exception e)
									{
										//oh well
									}
								}
								if(ServerRequests.getStaticGetNewsSuccess())
								{
									if(NotificationManager.getSize() > rememberSize)
									{
										Fortitude.getFortitude().runOnUiThread(new Runnable() {
											public void run()
											{
												if(ServerRequests.getTheMessageBox() != null)
												{
												    ServerRequests.getTheMessageBox().killMe();
												}
												pageId++;
												NotificationScreen.getMe().killMe();
												new NotificationScreen(pageId);
											}
										});
									}
									else if(NotificationManager.getSize() == rememberSize)
									{
										Fortitude.getFortitude().runOnUiThread(new Runnable() {
											public void run()
											{
												if(ServerRequests.getTheMessageBox() != null)
												{
													ServerRequests.getTheMessageBox().killMe();
													MessageBox.newMsgBox("No Older News Found", true);
												}
											}
										});
									}
								}
							}
						});
						thread.start();
					}
					else
					{
						pageId++;
						NotificationScreen.getMe().killMe();
						new NotificationScreen(pageId);
					}
				}
			});
			rightButton.setLayoutParams(rightButtonLayout);
			buttonRow.addView(rightButton, rightButtonLayout);
		}
		else
		{
			Space rightButtonSpace = new Space(buttonRow.getContext());
			rightButtonSpace.setLayoutParams(rightButtonLayout);
			buttonRow.addView(rightButtonSpace, rightButtonLayout);
		}

		LayoutParams buttonRowLayout = new LayoutParams(row4, col1);
		mainArea.addView(buttonRow, buttonRowLayout);

		return mainArea;
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
	public static NotificationScreen getMe()
	{
		return me;
	}
}
