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

public class ScoutSuccessScreen extends Window
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

	private static ScoutSuccessScreen me;
	private static Cache staticTheCache;
	private static JSONObject staticTheResponse;

	public ScoutSuccessScreen(Cache theCache, JSONObject theResponse)
	{
		super(R.drawable.scout_report_success_bg);
		me = this;
		staticTheCache = theCache;
		staticTheResponse = theResponse;
		addContentToContentPane(createWindowPane());
	}

	private GridLayout createWindowPane()
	{
		GridLayout mainArea = new GridLayout(Fortitude.getFortitude());
		mainArea.setColumnCount(1);
		mainArea.setRowCount(6);

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
		firstRowSpaceLayout.height = (super.getWindowHeight() / 2) + (super.getWindowHeight() / 20);
		Space firstRowSpace = new Space(mainArea.getContext());
		firstRowSpace.setLayoutParams(firstRowSpaceLayout);
		mainArea.addView(firstRowSpace, firstRowSpaceLayout);

		LayoutParams secondRowTextViewLayout = new LayoutParams(row2, col1);
		secondRowTextViewLayout.width = super.getWindowWidth();
		secondRowTextViewLayout.height = super.getWindowHeight() / 10;
		TextView scoutsLostTextView = new TextView(mainArea.getContext());
		scoutsLostTextView.setTextSize(14);
		scoutsLostTextView.setTextColor(Color.WHITE);
		String menLost = Integer.toString(ScoutSuccessScreen.getStaticTheResponse().get("scouts").asInteger() - ScoutSuccessScreen.getStaticTheResponse().get("survivors").asInteger());
		scoutsLostTextView.setText("The misson was a success. " + menLost + " of your scouts were discovered and killed by the enemy.");
		scoutsLostTextView.setGravity(Gravity.CENTER);
		scoutsLostTextView.setLayoutParams(secondRowTextViewLayout);
		mainArea.addView(scoutsLostTextView, secondRowTextViewLayout);

		LayoutParams thirdRowTextViewLayout = new LayoutParams(row3, col1);
		thirdRowTextViewLayout.width = super.getWindowWidth();
		thirdRowTextViewLayout.height = super.getWindowHeight() / 10;
		TextView garrisonTextView = new TextView(mainArea.getContext());
		garrisonTextView.setTextSize(14);
		garrisonTextView.setTextColor(Color.WHITE);
		garrisonTextView.setText("It is reported that there is a defence of " + Integer.toString((int)(Double.parseDouble(ScoutSuccessScreen.getStaticTheResponse().get("garrison").asString()))) + " soldiers.");
		garrisonTextView.setGravity(Gravity.CENTER);
		garrisonTextView.setLayoutParams(thirdRowTextViewLayout);
		mainArea.addView(garrisonTextView, thirdRowTextViewLayout);

		LayoutParams fourthRowSpaceLayout = new LayoutParams(row4, col1);
		fourthRowSpaceLayout.height = super.getWindowHeight() / 10;
		Space fourthRowSpace = new Space(mainArea.getContext());
		fourthRowSpace.setLayoutParams(fourthRowSpaceLayout);
		mainArea.addView(fourthRowSpace, fourthRowSpaceLayout);

		GridLayout fithRowGrid = new GridLayout(mainArea.getContext());
		fithRowGrid.setColumnCount(5);
		fithRowGrid.setRowCount(1);

		LayoutParams fithRowLeftSpaceLayout = new LayoutParams(row1, col1);
		fithRowLeftSpaceLayout.width = (super.getWindowWidth() / 10) - (super.getWindowWidth() / 40);
		Space fithRowLeftSpace = new Space(fithRowGrid.getContext());
		fithRowLeftSpace.setLayoutParams(fithRowLeftSpaceLayout);
		fithRowGrid.addView(fithRowLeftSpace, fithRowLeftSpaceLayout);

		LayoutParams attackCacheButtonLayout = new LayoutParams(row1, col2); //scout cache button
		attackCacheButtonLayout.width = (super.getWindowWidth() / 2) - (super.getWindowWidth() / 10);
		attackCacheButtonLayout.height = super.getWindowHeight() / 10;
		FortitudeButton attackCacheButton = (new FortitudeButton(R.drawable.attack_cache, R.drawable.attack_cache_pressed) {
			public void preClickActions()
			{

			}
			public void whenClicked()
			{
				if(CurrentUser.getMe().getIntBalance() > 0)
				{
					ScoutSuccessScreen.getMe().killMe();
					new AttackCacheScreen(ScoutSuccessScreen.getStaticTheCache());
				}
				else
				{
					MessageBox.newMsgBox("You must have atleast 1 soldier to attack issue an attack", true);
				}
			}
		});
		attackCacheButton.setLayoutParams(attackCacheButtonLayout);
		fithRowGrid.addView(attackCacheButton, attackCacheButtonLayout);

		LayoutParams fithRowMiddleSpacerLayout = new LayoutParams(row1, col3); //fith row middle spacer
		fithRowMiddleSpacerLayout.width = super.getWindowWidth() / 15;
		Space fithRowMiddleSpacer = new Space(fithRowGrid.getContext());
		fithRowMiddleSpacer.setLayoutParams(fithRowMiddleSpacerLayout);
		fithRowGrid.addView(fithRowMiddleSpacer, fithRowMiddleSpacerLayout);

		LayoutParams cancelLayout = new LayoutParams(row1, col4); //attack cache button
		cancelLayout.width = (super.getWindowWidth() / 2) - (super.getWindowWidth() / 10);
		cancelLayout.height = super.getWindowHeight() / 10;
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
		cancelButton.setLayoutParams(cancelLayout);
		fithRowGrid.addView(cancelButton, cancelLayout);

		LayoutParams fithRowGridLayout = new LayoutParams(row5, col1);
		mainArea.addView(fithRowGrid, fithRowGridLayout);

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
				new ScoutSuccessScreen(ScoutSuccessScreen.getStaticTheCache(), ScoutSuccessScreen.getStaticTheResponse());
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
	public static ScoutSuccessScreen getMe()
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

	public static JSONObject getStaticTheResponse()
	{
		return staticTheResponse;
	}

}
