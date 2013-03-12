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
import android.graphics.Typeface;

public abstract class PostBattleScreen extends Window 
{
	private Spec col1 = GridLayout.spec(0);
	private Spec col2 = GridLayout.spec(1);
	private Spec col3 = GridLayout.spec(2);
	private Spec col4 = GridLayout.spec(3);

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

	private static JSONObject staticTheResponse;
	private static Cache staticTheCache;
	private static PostBattleScreen me;

	public PostBattleScreen(JSONObject theResponse, Cache theCache)
	{
		super(R.drawable.battle_report);
		me = this;
		staticTheResponse = theResponse;
		staticTheCache = theCache;
		addContentToContentPane(createWindowPane());
	}

	private GridLayout createWindowPane()
	{
		GridLayout mainArea = new GridLayout(Fortitude.getFortitude());
		mainArea.setColumnCount(1);
		mainArea.setRowCount(14);

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
		firstRowSpaceLayout.height = (super.getWindowHeight() / 4) - (super.getWindowHeight() / 20);
		Space firstRowSpace = new Space(mainArea.getContext());
		firstRowSpace.setLayoutParams(firstRowSpaceLayout);
		mainArea.addView(firstRowSpace, firstRowSpaceLayout);

		GridLayout cacheNameGrid = new GridLayout(mainArea.getContext());
		cacheNameGrid.setColumnCount(3);
		cacheNameGrid.setRowCount(1);

		LayoutParams thirdRowLeftSpacerLayout = new LayoutParams(row1, col1); //cache name left spacer
		thirdRowLeftSpacerLayout.width = (super.getWindowWidth() / 10);
		Space thirdRowLeftSpacer = new Space(cacheNameGrid.getContext());
		thirdRowLeftSpacer.setLayoutParams(thirdRowLeftSpacerLayout);
		cacheNameGrid.addView(thirdRowLeftSpacer, thirdRowLeftSpacerLayout);

		LayoutParams cacheNameTextViewLayout = new LayoutParams(row1, col2); //Cache Name TEXTVIEW
		cacheNameTextViewLayout.width = (super.getWindowWidth() / 2);
		cacheNameTextViewLayout.height = super.getWindowWidth() / 3;
		TextView cacheNameTextView = new TextView(cacheNameGrid.getContext());
		cacheNameTextView.setTextSize(28);
		cacheNameTextView.setTextColor(Color.rgb(218, 218, 218));
		cacheNameTextView.setGravity(Gravity.CENTER);
		cacheNameTextView.setTypeface(Typeface.createFromAsset(Fortitude.getFortitude().getAssets(), "Fonts/Copperplate-Gothic-Light-Regular.ttf"));
		cacheNameTextView.setText(PostBattleScreen.getStaticTheCache().getCacheName());
		cacheNameTextView.setLayoutParams(cacheNameTextViewLayout);
		cacheNameGrid.addView(cacheNameTextView, cacheNameTextViewLayout);

		LayoutParams cacheNameGridLayout = new LayoutParams(row2, col1);
		mainArea.addView(cacheNameGrid, cacheNameGridLayout);

		LayoutParams thirdRowSpaceLayout = new LayoutParams(row3, col1);
		thirdRowSpaceLayout.height = (super.getWindowHeight() / 20);
		Space thirdRowSpace = new Space(mainArea.getContext());
		thirdRowSpace.setLayoutParams(thirdRowSpaceLayout);
		mainArea.addView(thirdRowSpace, thirdRowSpaceLayout);

		LayoutParams defendingArmyTextViewLayout = new LayoutParams(row4, col1);
		defendingArmyTextViewLayout.width = super.getWindowWidth() - (super.getWindowWidth() / 10);
		defendingArmyTextViewLayout.height = super.getWindowHeight() / 20;
		TextView defendingArmyTextView = new TextView(mainArea.getContext());
		defendingArmyTextView.setTextColor(Color.WHITE);
		defendingArmyTextView.setTextSize(14);
		defendingArmyTextView.setGravity(Gravity.RIGHT);
		defendingArmyTextView.setText(Integer.toString((int)(Double.parseDouble(PostBattleScreen.getStaticTheResponse().get("defenderinitial").asString()))) + " soldiers");
		defendingArmyTextView.setLayoutParams(defendingArmyTextViewLayout);
		mainArea.addView(defendingArmyTextView, defendingArmyTextViewLayout);

		LayoutParams fithRowSpaceLayout = new LayoutParams(row5, col1);
		fithRowSpaceLayout.height = super.getWindowHeight() / 70;
		Space fithRowSpace = new Space(mainArea.getContext());
		fithRowSpace.setLayoutParams(fithRowSpaceLayout);
		mainArea.addView(fithRowSpace, fithRowSpaceLayout);

		LayoutParams attackingArmyTextViewLayout = new LayoutParams(row6, col1);
		attackingArmyTextViewLayout.width = super.getWindowWidth() - (super.getWindowWidth() / 10);
		attackingArmyTextViewLayout.height = super.getWindowHeight() / 20;
		TextView attackingArmyTextView = new TextView(mainArea.getContext());
		attackingArmyTextView.setTextColor(Color.WHITE);
		attackingArmyTextView.setTextSize(14);
		attackingArmyTextView.setGravity(Gravity.RIGHT);
		attackingArmyTextView.setText(Integer.toString((int)(Double.parseDouble(PostBattleScreen.getStaticTheResponse().get("attackerinitial").asString()))) + " soldiers");
		attackingArmyTextView.setLayoutParams(attackingArmyTextViewLayout);
		mainArea.addView(attackingArmyTextView, attackingArmyTextViewLayout);

		LayoutParams seventhRowSpaceLayout = new LayoutParams(row7, col1);
		seventhRowSpaceLayout.height = super.getWindowHeight() / 55;
		Space seventhRowSpace = new Space(mainArea.getContext());
		seventhRowSpace.setLayoutParams(seventhRowSpaceLayout);
		mainArea.addView(seventhRowSpace, seventhRowSpaceLayout);

		LayoutParams victoryTextViewLayout = new LayoutParams(row8, col1);
		victoryTextViewLayout.width = super.getWindowWidth() - (super.getWindowWidth() / 10);
		victoryTextViewLayout.height = super.getWindowHeight() / 20;
		TextView victoryTextView = new TextView(mainArea.getContext());
		victoryTextView.setTextColor(Color.WHITE);
		victoryTextView.setTextSize(14);
		victoryTextView.setGravity(Gravity.RIGHT);
		if(PostBattleScreen.getStaticTheResponse().get("victory").asString().equals("true"))
		{
			victoryTextView.setText("Victory!");
		}
		else
		{
			victoryTextView.setText("Defeat!");
		}
		victoryTextView.setLayoutParams(victoryTextViewLayout);
		mainArea.addView(victoryTextView, victoryTextViewLayout);

		LayoutParams ninthRowSpaceLayout = new LayoutParams(row9, col1);
		ninthRowSpaceLayout.height = super.getWindowHeight() / 60;
		Space ninthRowSpace = new Space(mainArea.getContext());
		ninthRowSpace.setLayoutParams(ninthRowSpaceLayout);
		mainArea.addView(ninthRowSpace, ninthRowSpaceLayout);

		LayoutParams survivorsTextViewLayout = new LayoutParams(row10, col1);
		survivorsTextViewLayout.width = super.getWindowWidth() - (super.getWindowWidth() / 10);
		survivorsTextViewLayout.height = super.getWindowHeight() / 20;
		TextView survivorsTextView = new TextView(mainArea.getContext());
		survivorsTextView.setTextColor(Color.WHITE);
		survivorsTextView.setTextSize(14);
		survivorsTextView.setGravity(Gravity.RIGHT);
		survivorsTextView.setText(Integer.toString((int)(Double.parseDouble(PostBattleScreen.getStaticTheResponse().get("attackersurvivors").asString()))) + " soldiers");
		survivorsTextView.setLayoutParams(survivorsTextViewLayout);
		mainArea.addView(survivorsTextView, survivorsTextViewLayout);

		LayoutParams eleventhRowSpaceLayout = new LayoutParams(row11, col1);
		eleventhRowSpaceLayout.height = super.getWindowHeight() / 20;
		Space eleventhRowSpace = new Space(mainArea.getContext());
		eleventhRowSpace.setLayoutParams(eleventhRowSpaceLayout);
		mainArea.addView(eleventhRowSpace, eleventhRowSpaceLayout);

		LayoutParams captivesTextViewLayout = new LayoutParams(row12, col1);
		captivesTextViewLayout.width = super.getWindowWidth();
		captivesTextViewLayout.height = (int)(super.getWindowHeight() * 0.11);
		TextView captivesTextView = new TextView(mainArea.getContext());
		captivesTextView.setTextColor(Color.WHITE);
		captivesTextView.setTextSize(14);
		captivesTextView.setGravity(Gravity.CENTER);
		if(staticTheCache.getOwnerId() == CurrentUser.getMe().getAccountId())
		{
			if(PostBattleScreen.getStaticTheResponse().get("victory").asString().equals("true"))
			{
				captivesTextView.setText(Integer.toString((int)(Double.parseDouble(PostBattleScreen.getStaticTheResponse().get("attackerdeserters").asString()))) + " enemy soldiers chose to surrender and join your army. They have been sent to you, and your balance has been increased.");
			}
			else
			{
				captivesTextView.setText(Integer.toString((int)(Double.parseDouble(PostBattleScreen.getStaticTheResponse().get("defenderdeserters").asString()))) + " of your soldiers chose to surrender and join the enemy army.");
			}
		}
		else
		{
			if(PostBattleScreen.getStaticTheResponse().get("victory").asString().equals("true"))
			{
				captivesTextView.setText(Integer.toString((int)(Double.parseDouble(PostBattleScreen.getStaticTheResponse().get("defenderdeserters").asString()))) + " of your soldiers chose to surrender and join the attacking army.");
			}
			else
			{
				captivesTextView.setText(Integer.toString((int)(Double.parseDouble(PostBattleScreen.getStaticTheResponse().get("attackerdeserters").asString()))) + " of the enemy soldiers chose to surrender and have joined your army.");
			}
		}
		captivesTextView.setLayoutParams(captivesTextViewLayout);
		mainArea.addView(captivesTextView, captivesTextViewLayout);

		LayoutParams thirteenthRowSpaceLayout = new LayoutParams(row13, col1);
		thirteenthRowSpaceLayout.height = super.getWindowHeight() / 25;
		Space thirteenthRowSpace = new Space(mainArea.getContext());
		thirteenthRowSpace.setLayoutParams(thirteenthRowSpaceLayout);
		mainArea.addView(thirteenthRowSpace, thirteenthRowSpaceLayout);

		GridLayout cancelButtonRow = new GridLayout(mainArea.getContext());
		cancelButtonRow.setColumnCount(2);
		cancelButtonRow.setRowCount(1);

		LayoutParams spaceLeftOfButtonLayout = new LayoutParams(row1, col1);
		spaceLeftOfButtonLayout.width = (super.getWindowWidth() / 4);
		Space spaceLeftOfButton = new Space(cancelButtonRow.getContext());
		spaceLeftOfButton.setLayoutParams(spaceLeftOfButtonLayout);
		cancelButtonRow.addView(spaceLeftOfButton, spaceLeftOfButtonLayout);

		LayoutParams cancelButtonLayout = new LayoutParams(row1, col2); //cancel button
		cancelButtonLayout.height = super.getWindowHeight() / 10;
		cancelButtonLayout.width = (super.getWindowWidth() / 2);
		FortitudeButton cancelButton = (new FortitudeButton(R.drawable.ok, R.drawable.ok_pressed) {
			public void preClickActions()
			{

			}
			public void whenClicked()
			{
				killMe();
				whenCancelled();
			}
		});
		cancelButton.setLayoutParams(cancelButtonLayout);
		cancelButtonRow.addView(cancelButton, cancelButtonLayout);

		LayoutParams cancelButtonRowLayout = new LayoutParams(row14, col1);
		mainArea.addView(cancelButtonRow, cancelButtonRowLayout);

		return mainArea;
	}

	public static JSONObject getStaticTheResponse()
	{
		return staticTheResponse;
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
				new PostBattleScreen(PostBattleScreen.getStaticTheResponse(), PostBattleScreen.getStaticTheCache()) {
					public void whenCancelled()
					{
						new MainScreen();
					}
				};
			}
		};
	}

	public abstract void whenCancelled();

	////////
	//
	//getMe
	//
	//returns the last created instance of this class, could be null
	//
	////////
	public static PostBattleScreen getMe()
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
