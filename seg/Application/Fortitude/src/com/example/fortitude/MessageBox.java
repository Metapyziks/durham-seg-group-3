package com.example.fortitude;

import android.widget.GridLayout;
import android.widget.GridLayout.LayoutParams;
import android.widget.GridLayout.Spec;
import android.widget.Space;
import android.graphics.Color;
import android.widget.TextView;
import android.view.Gravity;
import android.graphics.Paint;
import java.lang.Math;
import android.widget.ImageView.ScaleType;
import android.widget.ImageView;

public class MessageBox extends Window
{	
	//row layouts
	private Spec row1 = GridLayout.spec(0);
	private Spec row2 = GridLayout.spec(1);
	private Spec row3 = GridLayout.spec(2);
	private Spec row4 = GridLayout.spec(3);
	private Spec row5 = GridLayout.spec(4);
	private Spec row6 = GridLayout.spec(5);

	//column layouts
	private Spec col1 = GridLayout.spec(0);
	private Spec col2 = GridLayout.spec(1);

	private Spec allcols = GridLayout.spec(0,3);

	private static MessageBox me = null;

	private String messageToDisplay = "";

	private Boolean haveOkButton;

	private TextView messageCell;

	////////
	//
	//newMsgBox
	//
	//static constructor
	//
	////////
	public static MessageBox newMsgBox(String messageToDisplay, boolean okButton)
	{
		if(me != null)
		{
			return null; //perhaps me?
		}

		disableAllTheChildren(Fortitude.getFortitude().getWindow().getDecorView());

		return new MessageBox(messageToDisplay, okButton);
	}
	
	////////
	//
	//Constructor
	//
	////////
	private MessageBox(String messageToDisplay, Boolean okButton)
	{
		super(((GUI.calculateWindowWidth() / 10) * 8), (GUI.calculateWindowWidth() / 10), (GUI.calculateWindowHeight() / 4), R.drawable.message_box_middle);
		me = this;
		haveOkButton = okButton;
		this.messageToDisplay = messageToDisplay;
		
		new GreyScreen();
		
		addContentToContentPane(createWindowPane());
	}

	////////
	//
	//createWindowPane
	//
	//returns a gridlayout that is added to the window
	//
	////////
	private GridLayout createWindowPane()
	{	
		GridLayout mainArea = new GridLayout(Fortitude.getFortitude());
		mainArea.setColumnCount(3);
		mainArea.setRowCount(7);

		LayoutParams topBarLayout = new LayoutParams(row1, allcols); //top bar
		topBarLayout.width = super.getWindowWidth();
		ImageView topBar = new ImageView(mainArea.getContext());
		topBar.setScaleType(ScaleType.FIT_XY);
		topBar.setImageResource(R.drawable.message_box_top);
		mainArea.addView(topBar, topBarLayout);

		LayoutParams secondRowSpaceLayout = new LayoutParams(row2, allcols); //second row space
		secondRowSpaceLayout.width = super.getWindowWidth();
		secondRowSpaceLayout.height = GUI.calculateWindowHeight() / 15;
		Space secondRowSpace = new Space(mainArea.getContext());
		secondRowSpace.setLayoutParams(secondRowSpaceLayout);
		mainArea.addView(secondRowSpace, secondRowSpaceLayout);
        
        LayoutParams buttonSpacerLayout = new LayoutParams(row4, allcols); //fourth row space
        buttonSpacerLayout.width = super.getWindowWidth();
        buttonSpacerLayout.height = GUI.calculateWindowHeight() / 20;
        Space buttonSpacer = new Space(mainArea.getContext());
        buttonSpacer.setLayoutParams(buttonSpacerLayout);
		mainArea.addView(buttonSpacer, buttonSpacerLayout);
		
		LayoutParams okButtonLayout = null;
		if(haveOkButton)
		{
			GridLayout okButtonGridLayout = new GridLayout(mainArea.getContext());
			okButtonGridLayout.setColumnCount(2);
			okButtonGridLayout.setRowCount(1);
			
		    okButtonLayout = new LayoutParams(row1, col2); //ok button
		    FortitudeButton okButton = new FortitudeButton(R.drawable.ok, R.drawable.ok_pressed) {
		    	public void preClickActions()
		    	{
		    		
		    	}
		    	public void whenClicked()
		    	{
		    		MessageBox.getMe().killMe();
		    	}
		    };
		    okButtonLayout.height = GUI.calculateWindowHeight() / 10;
		    okButtonLayout.width = GUI.calculateWindowWidth() / 3;
		    okButton.setLayoutParams(okButtonLayout);
		    okButtonGridLayout.addView(okButton, okButtonLayout);
		    
			LayoutParams okButtonLeftSpacerLayout = new LayoutParams(row1, col1);
			okButtonLeftSpacerLayout.width = (super.getWindowWidth() - okButtonLayout.width) / 2;
			Space okButtonLeftSpacer = new Space(okButtonGridLayout.getContext());
			okButtonLeftSpacer.setLayoutParams(okButtonLeftSpacerLayout);
			okButtonGridLayout.addView(okButtonLeftSpacer, okButtonLeftSpacerLayout);
		    
		    LayoutParams okButtonGridLayoutParams = new LayoutParams(row5, allcols);
		    mainArea.addView(okButtonGridLayout, okButtonGridLayoutParams);
		}
		
		LayoutParams middlePartLayout = new LayoutParams(row3, allcols); //text to display
		middlePartLayout.width = super.getWindowWidth();
		Paint mPaint = new Paint();
		mPaint.setTextSize(14);
		middlePartLayout.height = ((int)((float)(Math.ceil(mPaint.measureText(messageToDisplay, 0, messageToDisplay.length()) / middlePartLayout.width)))) * 20;
		middlePartLayout.height = middlePartLayout.height + (getLineBreakOccurrencesInString(messageToDisplay) * 20) + (GUI.calculateWindowWidth() / 10);
		if((middlePartLayout.height + ((middlePartLayout.width / 10) * 2.5)) > middlePartLayout.width) //If the height of the variable height message box goes below the screen then we don't want the ok button hidden so just set the height of the messagebox to the maximum height that is visible
		{
			middlePartLayout.height = middlePartLayout.width - secondRowSpace.getLayoutParams().height - topBarLayout.height - ((int)((float)Math.ceil((middlePartLayout.height / 10) * 2.5)));
		}
		messageCell = new TextView(mainArea.getContext());
        messageCell.setLayoutParams(middlePartLayout);
        messageCell.setText(messageToDisplay);
        messageCell.setTextSize(14);
        messageCell.setGravity(Gravity.CENTER);
        messageCell.setTextColor(Color.WHITE);
        mainArea.addView(messageCell, middlePartLayout);
        
        LayoutParams bottomPartLayout = new LayoutParams(row6, allcols); //bottom bar image
        bottomPartLayout.width = super.getWindowWidth();
		ImageView bottomBar = new ImageView(mainArea.getContext());
		bottomBar.setScaleType(ScaleType.FIT_XY);
		bottomBar.setImageResource(R.drawable.message_box_bottom);
		mainArea.addView(bottomBar, bottomPartLayout);
		
		LayoutParams newBackgroundLayout = new LayoutParams(row2, col2); //background if not full size is inappropriately sized so resize;
		if(!haveOkButton)
		{
		    newBackgroundLayout.height= 0 + secondRowSpaceLayout.height + middlePartLayout.height + buttonSpacerLayout.height + 5;
		}
		else
		{
			newBackgroundLayout.height= 0 + secondRowSpaceLayout.height + middlePartLayout.height + buttonSpacerLayout.height + okButtonLayout.height + 5;
		}
		newBackgroundLayout.width = super.getWindowWidth();
		super.getBackgroundImage().setLayoutParams(newBackgroundLayout);
        
		return mainArea;
	}

	////////
	//
	//changeMessageToDisplay
	//
	//allows the message in this message box to be changed from elsewhere. WARNING! if you change the text the
	//messagebox will NOT resize. If you require and messagebox of similar size you msut kill this one and
	//create a new one.
	//
	////////
	public void changeMessageToDisplay(String x)
	{
		messageCell.setText(x);
	}

	////////
	//
	//getLineBreakOccurrencesInString
	//
	//returns the number of complete line breaks in a string
	//
	////////
	private int getLineBreakOccurrencesInString(String theString)
	{
		int lastIndex = 0;
		int count = 0;

		while(lastIndex != -1)
		{
			lastIndex = theString.indexOf("\n\n", lastIndex);

			if( lastIndex != -1)
			{
				count++;
				lastIndex++;
			}
		}
		return count;
	}

	////////
	//
	//killMe
	//
	//removes the messagebox from view
	//
	////////
	public void killMe()
	{
		makeAllTheChildrenBetter(Fortitude.getFortitude().getWindow().getDecorView());
		me = null;
		GreyScreen.getMe().killMe();
		this.removeAllViews();
	}

	////////
	//
	//getMe
	//
	//returns the last created instance of this class
	//
	////////
	public static MessageBox getMe()
	{
		return me;
	}
}