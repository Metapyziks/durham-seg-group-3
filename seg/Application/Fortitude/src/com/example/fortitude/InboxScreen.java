package com.example.fortitude;

import android.widget.ImageView.ScaleType;
import android.widget.ImageView;
import android.widget.GridLayout;
import android.widget.GridLayout.LayoutParams;
import android.widget.GridLayout.Spec;
import android.widget.Space;
import android.widget.TextView;
import android.view.Gravity;

public class InboxScreen extends Window 
{
	private Spec row1 = GridLayout.spec(0);
	private Spec row2 = GridLayout.spec(1);
	private Spec row3 = GridLayout.spec(2);
	
	private Spec col1 = GridLayout.spec(0);
	private Spec col2 = GridLayout.spec(1);
	
	private static InboxScreen me;
	
	public InboxScreen()
	{
		super(R.drawable.inbox_bg);
		me = this;
		super.addContentToContentPane(createWindowPane());
	}
	
	private GridLayout createWindowPane()
	{
		GridLayout mainArea = new GridLayout(Fortitude.getFortitude());
		mainArea.setColumnCount(1);
		mainArea.setRowCount(7);
		
		LayoutParams topSpaceLayout = new LayoutParams(row1, col1); //top space
		topSpaceLayout.height = super.getWindowHeight() / 5;
		Space topSpace = new Space(mainArea.getContext());
		topSpace.setLayoutParams(topSpaceLayout);
		mainArea.addView(topSpace, topSpaceLayout);
		
		GridLayout firstButtonGrid = new GridLayout(mainArea.getContext());
		firstButtonGrid.setColumnCount(5);
		firstButtonGrid.setRowCount(1);
		
		LayoutParams firstButtonGridLeftSpaceLayout = new LayoutParams(row1, col1); //firstbutton grid left space
		firstButtonGridLeftSpaceLayout.width = super.getWindowWidth() / 5;
		Space firstButtonGridLeftSpace = new Space(firstButtonGrid.getContext());
		firstButtonGridLeftSpace.setLayoutParams(firstButtonGridLeftSpaceLayout);
		firstButtonGrid.addView(firstButtonGridLeftSpace, firstButtonGridLeftSpaceLayout);
		
		LayoutParams sendMessageButtonLayout = new LayoutParams(row1, col2); //send messages button
		sendMessageButtonLayout.width = super.getWindowWidth() / 3;
		sendMessageButtonLayout.height = super.getWindowHeight() / 10;
		FortitudeButton sendMessageButton = (new FortitudeButton(R.drawable.send_message, R.drawable.send_message_pressed) {
			public void preClickActions() 
			{
				
			}

			public void whenClicked() 
			{
				InboxScreen.getMe().killMe();
				new SendMessageScreen();
			}
		});
		sendMessageButton.setLayoutParams(sendMessageButtonLayout);
		firstButtonGrid.addView(sendMessageButton, sendMessageButtonLayout);
		
		LayoutParams firstButtonGridLayout = new LayoutParams(row3, col1);
		mainArea.addView(firstButtonGrid, firstButtonGridLayout);
		
		
		return mainArea;
	}
	
	public static InboxScreen getMe()
	{
		return me;
	}
	
	public void killMe()
	{
		me = null;
		super.removeAllViews();
	}
}