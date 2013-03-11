package com.example.fortitude;

import json.JSONObject;
import android.widget.GridLayout;
import android.widget.GridLayout.LayoutParams;
import android.widget.GridLayout.Spec;
import android.widget.SeekBar;
import android.widget.Space;
import android.widget.TextView;
import android.view.Gravity;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.ImageView.ScaleType;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.ImageView;
import android.graphics.Color;

public class ScoutFailureScreen extends Window 
{
	private Spec row1 = GridLayout.spec(0);
	private Spec row2 = GridLayout.spec(1);

	private Spec col1 = GridLayout.spec(0);
	private Spec col2 = GridLayout.spec(1);
	private Spec col3 = GridLayout.spec(2);
	private Spec col4 = GridLayout.spec(3);

	private static Cache staticTheCache;
	private static ScoutFailureScreen me;

	public ScoutFailureScreen(Cache theCache)
	{
		super(R.drawable.scout_report_total_wipeout_);
		me = this;
		staticTheCache = theCache;
		addContentToContentPane(createWindowPane());
	}

	private GridLayout createWindowPane()
	{
		GridLayout mainArea = new GridLayout(Fortitude.getFortitude());
		mainArea.setColumnCount(1);
		mainArea.setRowCount(3);

		LayoutParams topBarImageLayout = new LayoutParams(row1, col1); //top bar image
		topBarImageLayout.width = super.getWindowWidth();
		topBarImageLayout.height = super.getWindowHeight() / 20;
		ImageView topBarImage = new ImageView(mainArea.getContext());
		topBarImage.setScaleType(ScaleType.FIT_XY);
		topBarImage.setImageResource(R.drawable.top);
		topBarImage.setLayoutParams(topBarImageLayout);
		topBarImage.setOnClickListener(new OnClickListener() {
			public void onClick(View view)
			{
				topBarClicked();
			}
		});
		mainArea.addView(topBarImage, topBarImageLayout);

		GridLayout topBarGrid = new GridLayout(mainArea.getContext());
		topBarGrid.setColumnCount(2);
		topBarGrid.setRowCount(1);

		LayoutParams userNameTextViewLayout = new LayoutParams(row1, col1); //username text view
		userNameTextViewLayout.width = super.getWindowWidth() / 2;
		userNameTextViewLayout.height = super.getWindowHeight() / 20;
		TextView userNameTextView = new TextView(topBarGrid.getContext());
		userNameTextView.setTextColor(Color.YELLOW);
		userNameTextView.setText("  " + CurrentUser.getMe().getUserName());
		userNameTextView.setLayoutParams(userNameTextViewLayout);
		userNameTextView.setGravity(Gravity.LEFT);
		topBarGrid.addView(userNameTextView, userNameTextViewLayout);

		LayoutParams userBalanceTextViewLayout = new LayoutParams(row1, col2); //balance text view
		userBalanceTextViewLayout.width = super.getWindowWidth() / 2;
		userBalanceTextViewLayout.height = super.getWindowHeight() / 20;
		TextView userBalanceTextView = new TextView(topBarGrid.getContext());
		userBalanceTextView.setTextColor(Color.YELLOW);
		userBalanceTextView.setText(CurrentUser.getMe().getBalance() + "  ");
		userBalanceTextView.setLayoutParams(userBalanceTextViewLayout);
		userBalanceTextView.setGravity(Gravity.RIGHT);
		topBarGrid.addView(userBalanceTextView, userBalanceTextViewLayout);

		LayoutParams topBarGridLayout = new LayoutParams(row1, col1);
		mainArea.addView(topBarGrid, topBarGridLayout);

		LayoutParams firstRowSpaceLayout = new LayoutParams(row1, col1);
		firstRowSpaceLayout.height = ((super.getWindowHeight() / 4) * 3) + (super.getWindowHeight() / 10);
		Space firstRowSpace = new Space(mainArea.getContext());
		firstRowSpace.setLayoutParams(firstRowSpaceLayout);
		mainArea.addView(firstRowSpace, firstRowSpaceLayout);

		GridLayout secondRowButtonGrid = new GridLayout(mainArea.getContext());
		secondRowButtonGrid.setColumnCount(5);
		secondRowButtonGrid.setRowCount(1);

		LayoutParams secondRowLeftSpaceLayout = new LayoutParams(row1, col1);
		secondRowLeftSpaceLayout.width = (super.getWindowWidth() / 10) - (super.getWindowWidth() / 40);
		Space secondRowLeftSpace = new Space(secondRowButtonGrid.getContext());
		secondRowLeftSpace.setLayoutParams(secondRowLeftSpaceLayout);
		secondRowButtonGrid.addView(secondRowLeftSpace, secondRowLeftSpaceLayout);

		LayoutParams scoutCacheButtonLayout = new LayoutParams(row1, col2);
		scoutCacheButtonLayout.width = (super.getWindowWidth() / 2) - (super.getWindowWidth() / 10);
		scoutCacheButtonLayout.height = super.getWindowHeight() / 10;
		FortitudeButton scoutCacheButton = (new FortitudeButton(R.drawable.scout_cache, R.drawable.scout_cache_pressed) {
			public void preClickActions()
			{

			}
			public void whenClicked()
			{
				if(CurrentUser.getMe().getBalance() > 0)
				{
					killMe();
					new ScoutEnemyCache(ScoutFailureScreen.getStaticTheCache());
				}
				else
				{
					MessageBox.newMsgBox("You must have atleast 1 soldier to scout an enemy cache", true);
				}
			}
		});
		scoutCacheButton.setLayoutParams(scoutCacheButtonLayout);
		secondRowButtonGrid.addView(scoutCacheButton, scoutCacheButtonLayout);

		LayoutParams secondRowMiddleSpaceLayout = new LayoutParams(row1, col3);
		secondRowMiddleSpaceLayout.width = super.getWindowWidth() / 15;
		Space secondRowMiddleSpace = new Space(secondRowButtonGrid.getContext());
		secondRowMiddleSpace.setLayoutParams(secondRowMiddleSpaceLayout);
		secondRowButtonGrid.addView(secondRowMiddleSpace, secondRowMiddleSpaceLayout);

		LayoutParams cancelButtonLayout = new LayoutParams(row1, col4);
		cancelButtonLayout.width = (super.getWindowWidth() / 2) - (super.getWindowWidth() / 10);
		cancelButtonLayout.height = super.getWindowHeight() / 10;
		FortitudeButton cancelButton = (new FortitudeButton(R.drawable.cancel, R.drawable.cancel_pressed) {
			public void preClickActions()
			{

			}
			public void whenClicked()
			{
				killMe();
				new MainScreen();
			}
		});
		cancelButton.setLayoutParams(cancelButtonLayout);
		secondRowButtonGrid.addView(cancelButton, cancelButtonLayout);

		LayoutParams secondRowButtonGridLayout = new LayoutParams(row2, col1);
		mainArea.addView(secondRowButtonGrid, secondRowButtonGridLayout);

		return mainArea;
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
				new ScoutFailureScreen(ScoutFailureScreen.getStaticTheCache());
			}
		};
	}

	////////
	//
	//getMe
	//
	//returns the last created instance of this class, could be null
	//
	////////
	public static ScoutFailureScreen getMe()
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
		this.removeAllViews();
	}

	public static Cache getStaticTheCache()
	{
		return staticTheCache;
	}
}
