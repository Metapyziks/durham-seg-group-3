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
import android.widget.ScrollView;

public class MessageSettingsScreen extends Window
{
	private Spec row1 = GridLayout.spec(0);
	private Spec row2 = GridLayout.spec(1);
	private Spec row3 = GridLayout.spec(2);
	private Spec row4 = GridLayout.spec(3);
	private Spec row5 = GridLayout.spec(4);
	private Spec row6 = GridLayout.spec(5);
	private Spec row7 = GridLayout.spec(6);
	private Spec row8 = GridLayout.spec(7);
	private Spec row9 = GridLayout.spec(8);
	private Spec row10 = GridLayout.spec(9);
	private Spec row11 = GridLayout.spec(10);
	private Spec row12 = GridLayout.spec(11);
	private Spec row13 = GridLayout.spec(12);
	private Spec row14 = GridLayout.spec(13);

	private Spec col1 = GridLayout.spec(0);
	private Spec col2 = GridLayout.spec(1);
	private Spec col3 = GridLayout.spec(2);
	private Spec col4 = GridLayout.spec(3);

	private static ImageView turnOnMessagesIcon;
	private static MessageSettingsScreen me;
	private static TextField blockTextField;
	private static boolean changedActive = false;
	private static String usernameToBlock;

	public MessageSettingsScreen()
	{
		super(R.drawable.message_settings_bg);
		me = this;
		super.addContentToContentPane(createContentPane());
	}

	private GridLayout createContentPane()
	{
		GridLayout mainArea = new GridLayout(Fortitude.getFortitude());
		mainArea.setColumnCount(1);
		mainArea.setRowCount(7);

		LayoutParams topSpaceLayout = new LayoutParams(row1, col1);
		topSpaceLayout.height = (int) (super.getWindowHeight() * 0.22);
		Space topSpace = new Space(mainArea.getContext());
		topSpace.setLayoutParams(topSpaceLayout);
		mainArea.addView(topSpace, topSpaceLayout);

		GridLayout turnOnMessagesGrid = new GridLayout(mainArea.getContext());
		turnOnMessagesGrid.setRowCount(1);
		turnOnMessagesGrid.setColumnCount(3);

		LayoutParams turnOnMessagesLeftSpaceLayout = new LayoutParams(row1, col1);
		turnOnMessagesLeftSpaceLayout.width = (int) (super.getWindowWidth() * 0.6);
		Space turnOnMessagesLeftSpace = new Space(turnOnMessagesGrid.getContext());
		turnOnMessagesLeftSpace.setLayoutParams(turnOnMessagesLeftSpaceLayout);
		turnOnMessagesGrid.addView(turnOnMessagesLeftSpace, turnOnMessagesLeftSpaceLayout);

		LayoutParams turnOnMessagesIconLayout = new LayoutParams(row1, col2);
		turnOnMessagesIconLayout.width = super.getWindowWidth() / 4;
		turnOnMessagesIconLayout.height = super.getWindowHeight() / 10;
		turnOnMessagesIcon = new ImageView(turnOnMessagesGrid.getContext());
		if(CurrentUser.getMe().getMessagesActive())
		{
			turnOnMessagesIcon.setImageResource(R.drawable.slider_on);
		}
		else
		{
			turnOnMessagesIcon.setImageResource(R.drawable.slider_off);
		}
		turnOnMessagesIcon.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0)
			{
				changedActive = !changedActive;
				if(CurrentUser.getMe().getMessagesActive())
				{
					turnOnMessagesIcon.setImageResource(R.drawable.slider_off);
					CurrentUser.getMe().setMessagesActive(false);
				}
				else
				{
					turnOnMessagesIcon.setImageResource(R.drawable.slider_on);
					CurrentUser.getMe().setMessagesActive(true);
				}
			}
		});
		turnOnMessagesIcon.setLayoutParams(turnOnMessagesIconLayout);
		turnOnMessagesGrid.addView(turnOnMessagesIcon, turnOnMessagesIconLayout);

		LayoutParams turnOnMessagesGridLayout = new LayoutParams(row2, col1);
		mainArea.addView(turnOnMessagesGrid, turnOnMessagesGridLayout);

		LayoutParams thirdRowSpaceLayout = new LayoutParams(row3, col1);
		thirdRowSpaceLayout.height = super.getWindowHeight() / 60;
		Space thirdRowSpace = new Space(mainArea.getContext());
		thirdRowSpace.setLayoutParams(thirdRowSpaceLayout);
		mainArea.addView(thirdRowSpace, thirdRowSpaceLayout);

		GridLayout blockGrid = new GridLayout(mainArea.getContext());
		blockGrid.setColumnCount(3);
		blockGrid.setRowCount(1);

		LayoutParams blockGridLeftSpaceLayout = new LayoutParams(row1, col1);
		blockGridLeftSpaceLayout.width = (int) (super.getWindowWidth() * 0.4);
		Space blockGridLeftSpace = new Space(blockGrid.getContext());
		blockGridLeftSpace.setLayoutParams(blockGridLeftSpaceLayout);
		blockGrid.addView(blockGridLeftSpace, blockGridLeftSpaceLayout);

		LayoutParams blockTextImageLayout = new LayoutParams(row1, col2);
		blockTextImageLayout.width = super.getWindowWidth() / 2;
		blockTextImageLayout.height = super.getWindowHeight() / 10;
		TextFieldImage blockTextImage = new TextFieldImage();
		blockTextImage.setLayoutParams(blockTextImageLayout);
		blockGrid.addView(blockTextImage, blockTextImageLayout);

		blockTextField = new TextField(16);
		blockTextField.setLayoutParams(blockTextImageLayout);
		blockGrid.addView(blockTextField, blockTextImageLayout);

		LayoutParams blockGridLayout = new LayoutParams(row4, col1);
		mainArea.addView(blockGrid, blockGridLayout);

		LayoutParams fithRowSpaceLayout = new LayoutParams(row5, col1);
		fithRowSpaceLayout.height = super.getWindowHeight() / 5;
		Space fithRowSpace = new Space(mainArea.getContext());
		fithRowSpace.setLayoutParams(fithRowSpaceLayout);
		mainArea.addView(fithRowSpace, fithRowSpaceLayout);

		GridLayout unblockUserGrid = new GridLayout(mainArea.getContext());
		unblockUserGrid.setColumnCount(1);
		unblockUserGrid.setRowCount(3);

		LayoutParams unblockUserLeftSpaceLayout = new LayoutParams(row1, col1);
		unblockUserLeftSpaceLayout.width = super.getWindowWidth() / 10;
		Space unblockUserLeftSpace = new Space(unblockUserGrid.getContext());
		unblockUserLeftSpace.setLayoutParams(unblockUserLeftSpaceLayout);
		unblockUserGrid.addView(unblockUserLeftSpace, unblockUserLeftSpaceLayout);

		GridLayout containerGrid = new GridLayout(unblockUserGrid.getContext());
		containerGrid.setColumnCount(1);
		containerGrid.setRowCount(1);
		LayoutParams containerGridLayout = new LayoutParams(row6, col1);
		containerGridLayout.width = (int) (super.getWindowWidth() * 0.8);
		containerGridLayout.height = super.getWindowHeight() / 4;

		ScrollView unblockUsers = new ScrollView(unblockUserGrid.getContext());

		GridLayout blockedUsersGrid = new GridLayout(unblockUsers.getContext());
		blockedUsersGrid.setRowCount(BlockedManager.getSize());
		blockedUsersGrid.setColumnCount(1);

		for(int i = 0; i < BlockedManager.getSize(); i++)
		{
			String username = BlockedManager.getBlocked(i);
			if(username == null)
			{
				continue;
			}
			else
			{	
				LayoutParams textViewLayout = new LayoutParams(GridLayout.spec(i), GridLayout.spec(0));
				textViewLayout.width = (int) (super.getWindowWidth() * 0.8);
				textViewLayout.height = super.getWindowHeight() / 15;
				TextView textView = new TextView(unblockUsers.getContext());
				textView.setTextColor(Color.WHITE);
				textView.setTextSize(14);
				textView.setGravity(Gravity.CENTER);
				textView.setText(username);
				textView.setLayoutParams(textViewLayout);
				textView.setOnClickListener(new OnClickListener() {
					public void onClick(View arg0)
					{
						TextView textView = (TextView) arg0;
						usernameToBlock = textView.getText().toString();
						ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Connecting To Server", false));
						ServerRequests.setBlocked(usernameToBlock, false);
						Thread thread = new Thread(new Runnable() {
							public void run()
							{
								while(!ServerRequests.getGetBlockedUsersComplete())
								{
									try
									{
										Thread.sleep(100);
									}
									catch(Exception e)
									{

									}
								}
								if(ServerRequests.getGetBlockedUsersSuccessful())
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
											BlockedManager.removeBlocked(usernameToBlock);
											MessageSettingsScreen.getMe().killMe();
											new MessageSettingsScreen();
											MessageBox.newMsgBox("Successfully Unblocked " + usernameToBlock + "!", true);
										}
									});
								}
							}
						});
						thread.start();
					}
				});
				blockedUsersGrid.addView(textView, textViewLayout);
			}
		}
		blockedUsersGrid.setLayoutParams(new LayoutParams());
		unblockUsers.addView(blockedUsersGrid, new LayoutParams());



		unblockUsers.setLayoutParams(new LayoutParams());
		unblockUserGrid.addView(unblockUsers, new LayoutParams());

		LayoutParams unblockUserGridLayout = new LayoutParams(row1, col1);
		unblockUserGrid.setLayoutParams(unblockUserGridLayout);

		containerGrid.addView(unblockUserGrid, unblockUserGridLayout);

		containerGrid.setLayoutParams(containerGridLayout);

		mainArea.addView(containerGrid, containerGridLayout);

		LayoutParams seventhRowSpaceLayout = new LayoutParams(row7, col1);
		seventhRowSpaceLayout.height = super.getWindowHeight() / 40;
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

		LayoutParams blockButtonLayout = new LayoutParams(row1, col2); // block Button
		blockButtonLayout.height = super.getWindowHeight() /10;
		blockButtonLayout.width = (int) (super.getWindowWidth() *0.4);
		FortitudeButton blockButton = (new FortitudeButton(R.drawable.block_user, R.drawable.block_user_pressed) {
			public void preClickActions() 
			{

			}
			public void whenClicked() 
			{
				if(!validateFields())
				{
					return;
				}
				String usernameToBlock = blockTextField.getText().toString();
				ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Connecting To Server", false));
				ServerRequests.setBlocked(usernameToBlock, true);
				Thread thread = new Thread(new Runnable() {
					public void run()
					{
						while(!ServerRequests.getBlockUserComplete())
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
						if(ServerRequests.getBlockUserSuccess())
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
									BlockedManager.addBlocked(blockTextField.getText().toString());
									MessageSettingsScreen.getMe().killMe();
									new MessageSettingsScreen();
									MessageBox.newMsgBox("Successfully blocked user!", true);
								}
							});
						}
					}
				});
				thread.start();
			}			
		});
		blockButton.setLayoutParams(blockButtonLayout);
		buttonRowGrid.addView(blockButton, blockButtonLayout);

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
				if(changedActive)
				{
					changedActive = false;
					ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Connecting To Server", false));
					if(CurrentUser.getMe().getMessagesActive())
					{
						ServerRequests.setting("receivemessages", "Default");
					}
					else
					{
						ServerRequests.setting("receivemessages", "BlockAll");
					}
					Thread thread = new Thread(new Runnable() {
						public void run()
						{
							while(!ServerRequests.getUpdateSettingComplete())
							{
								try
								{
									Thread.sleep(100);	
								}
								catch(Exception e)
								{

								}
							}
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
									if(!ServerRequests.getUpdateSettingSuccess())
									{
										CurrentUser.getMe().setMessagesActive(!CurrentUser.getMe().getMessagesActive());
									}
									changedActive = false;
									MessageSettingsScreen.getMe().killMe();
									new InboxScreen(InboxScreen.pageId);
									if(!ServerRequests.getUpdateSettingSuccess())
									{
									    MessageBox.newMsgBox(ServerRequests.getStaticOutputMessage(), true);
									}
									else
									{
										MessageBox.newMsgBox("Successfully changed receive messages setting", true);
									}
								}
							});
						}
					});
					thread.start();
				}
			}
		});
		cancelButton.setLayoutParams(cancelButtonLayout);
		buttonRowGrid.addView(cancelButton, cancelButtonLayout);

		LayoutParams buttonRowGridLayout = new LayoutParams(row8, col1);
		mainArea.addView(buttonRowGrid, buttonRowGridLayout);

		return mainArea;
	}

	public boolean validateFields()
	{
		String input = blockTextField.getText().toString();
		if(input.length() < 1)
		{
			MessageBox.newMsgBox("Please enter a user to block", true);
			return false;
		}
		return true;
	}

	public void killMe()
	{
		me = null;
		this.removeAllViews();
	}

	public static MessageSettingsScreen getMe()
	{
		return me;
	}
}
