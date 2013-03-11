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

public class SpecialCacheScreen extends Window
{
	private Spec row1 = GridLayout.spec(0);
	private Spec row2 = GridLayout.spec(1);
	private Spec row3 = GridLayout.spec(2);
	
	private Spec col1 = GridLayout.spec(0);
	private Spec col2 = GridLayout.spec(1);
	private Spec col3 = GridLayout.spec(2);
	private Spec col4 = GridLayout.spec(3);
	
	private static SpecialCacheScreen me;
	
	public SpecialCacheScreen()
	{
		super(R.drawable.special_cache_bg);
		me = this;
		super.addContentToContentPane(createContentPane());
	}
	
	private GridLayout createContentPane()
	{
		GridLayout mainArea = new GridLayout(Fortitude.getFortitude());
		mainArea.setRowCount(3);
		mainArea.setColumnCount(1);
		
		LayoutParams topRowSpaceLayout = new LayoutParams(row1, col1);
		topRowSpaceLayout.height = (int) (super.getWindowHeight() * 0.9);
		Space topRowSpace = new Space(mainArea.getContext());
		topRowSpace.setLayoutParams(topRowSpaceLayout);
		mainArea.addView(topRowSpace, topRowSpaceLayout);
		
		GridLayout buttonRowGrid = new GridLayout (mainArea.getContext()); // Button Grid
		buttonRowGrid.setColumnCount(5);
		buttonRowGrid.setRowCount(1);
		
		LayoutParams firstButtonSpaceLayout = new LayoutParams(row1, col1); //first button spacer
		firstButtonSpaceLayout.width = super.getWindowWidth() / 12;
		Space firstButtonSpace = new Space(buttonRowGrid.getContext());
		firstButtonSpace.setLayoutParams(firstButtonSpaceLayout);
		buttonRowGrid.addView(firstButtonSpace, firstButtonSpaceLayout);
		
		LayoutParams claimButtonLayout = new LayoutParams(row1, col2); // Send Button
		claimButtonLayout.height = super.getWindowHeight() /10;
		claimButtonLayout.width = (int) (super.getWindowWidth() *0.4);
		FortitudeButton claimButton = (new FortitudeButton(R.drawable.claim, R.drawable.claim_pressed) {
			public void preClickActions() 
			{
				
			}
			public void whenClicked() 
			{
				ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Connecting To Server", false));
				ServerRequests.claimSpecial(MACManager.getClaimingAddress());
			}			
		});
		claimButton.setLayoutParams(claimButtonLayout);
		buttonRowGrid.addView(claimButton, claimButtonLayout);
		
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
				SpecialCacheScreen.getMe().killMe();
				new MainScreen();
			}
			
		});
		cancelButton.setLayoutParams(cancelButtonLayout);
		buttonRowGrid.addView(cancelButton, cancelButtonLayout);
		
		LayoutParams buttonGridLayout = new LayoutParams(row2, col1);
		mainArea.addView(buttonRowGrid, buttonGridLayout);
		
		return mainArea;
	}
	
	public static SpecialCacheScreen getMe()
	{
		return me;
	}
	
	public void killMe()
	{
		me = null;
		this.removeAllViews();
	}
}
