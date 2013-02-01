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

public class VisitYourCacheScreen extends Window
{
	private static Cache staticTheCache;
	private static VisitYourCacheScreen me;
	
	private Spec col1 = GridLayout.spec(0);
	private Spec col2 = GridLayout.spec(1);
	
	private Spec row1 = GridLayout.spec(0);
	private Spec row2 = GridLayout.spec(1);
	private Spec row3 = GridLayout.spec(2);
	private Spec row4 = GridLayout.spec(3);
	private Spec row5 = GridLayout.spec(4);
	private Spec row6 = GridLayout.spec(5);
	
	public VisitYourCacheScreen(Cache theCache)
	{
		super(R.drawable.allied_cache);
    	me = this;
    	staticTheCache = theCache;
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
		
		LayoutParams secondRowSpaceLayout = new LayoutParams(row2, col1); //second row space
		secondRowSpaceLayout.width = super.getWindowWidth();
		secondRowSpaceLayout.height = super.getWindowHeight() / 8;
		Space secondRowSpace = new Space(mainArea.getContext());
		secondRowSpace.setLayoutParams(secondRowSpaceLayout);
		mainArea.addView(secondRowSpace, secondRowSpaceLayout);
		
		GridLayout cacheNameGrid = new GridLayout(mainArea.getContext());
		cacheNameGrid.setColumnCount(3);
		cacheNameGrid.setRowCount(1);
		
		LayoutParams thirdRowLeftSpacerLayout = new LayoutParams(row1, col1); //cache name left spacer
		thirdRowLeftSpacerLayout.width = (super.getWindowWidth() / 3) + (super.getWindowWidth() / 15);
		Space thirdRowLeftSpacer = new Space(cacheNameGrid.getContext());
		thirdRowLeftSpacer.setLayoutParams(thirdRowLeftSpacerLayout);
		cacheNameGrid.addView(thirdRowLeftSpacer, thirdRowLeftSpacerLayout);
		
    	LayoutParams cacheNameTextViewLayout = new LayoutParams(row1, col2); //Cache Name TEXTVIEW
    	cacheNameTextViewLayout.width = (super.getWindowWidth() / 2);
    	cacheNameTextViewLayout.height = super.getWindowWidth() / 3;
    	TextView cacheNameTextView = new TextView(cacheNameGrid.getContext());
    	cacheNameTextView.setTextSize(28);
    	cacheNameTextView.setTextColor(Color.rgb(218, 218, 218));
    	cacheNameTextView.setGravity(Gravity.LEFT);
    	cacheNameTextView.setTypeface(Typeface.createFromAsset(Fortitude.getFortitude().getAssets(), "Fonts/Copperplate-Gothic-Light-Regular.ttf"));
    	cacheNameTextView.setText(VisitYourCacheScreen.getStaticTheCache().getCacheName());
    	cacheNameTextView.setLayoutParams(cacheNameTextViewLayout);
    	cacheNameGrid.addView(cacheNameTextView, cacheNameTextViewLayout);
    	
    	LayoutParams cacheNameGridLayout = new LayoutParams(row3, col1);
    	mainArea.addView(cacheNameGrid, cacheNameGridLayout);
    	
    	LayoutParams usernameTextViewLayout = new LayoutParams(row4, col1); //USERNAME TEXTVIEW
    	usernameTextViewLayout.width = (super.getWindowWidth());
    	usernameTextViewLayout.height = super.getWindowWidth() / 4;
    	TextView usernameTextView = new TextView(mainArea.getContext());
    	usernameTextView.setTextSize(24);
    	usernameTextView.setTextColor(Color.rgb(160, 160, 160));
    	usernameTextView.setGravity(Gravity.CENTER);
    	usernameTextView.setTypeface(Typeface.createFromAsset(Fortitude.getFortitude().getAssets(), "Fonts/Copperplate-Gothic-Light-Regular.ttf"));
    	usernameTextView.setText(CurrentUser.getMe().getUserName());
    	mainArea.addView(usernameTextView, usernameTextViewLayout);
    	
    	LayoutParams fithRowSpaceLayout = new LayoutParams(row5, col1); //fith row space
    	fithRowSpaceLayout.height = super.getWindowHeight() / 30;
    	Space fithRowSpace = new Space(mainArea.getContext());
    	fithRowSpace.setLayoutParams(fithRowSpaceLayout);
    	mainArea.addView(fithRowSpace, fithRowSpaceLayout);
    	
    	GridLayout standingArmyGrid = new GridLayout(mainArea.getContext());
    	standingArmyGrid.setColumnCount(3);
    	standingArmyGrid.setRowCount(1);
    	
    	LayoutParams standingArmyLeftSpaceLayout = new LayoutParams(row1, col1);
    	standingArmyLeftSpaceLayout.width = super.getWindowWidth() / 2;
    	Space standingArmyLeftSpace = new Space(standingArmyGrid.getContext());
    	standingArmyLeftSpace.setLayoutParams(standingArmyLeftSpaceLayout);
    	standingArmyGrid.addView(standingArmyLeftSpace, standingArmyLeftSpaceLayout);
    	
    	LayoutParams standingArmyTextViewLayout = new LayoutParams(row1, col2); //standing army text view
    	standingArmyTextViewLayout.width = (super.getWindowWidth() / 2) - (super.getWindowWidth() / 8);
    	standingArmyTextViewLayout.height = super.getWindowHeight() / 20;
    	TextView standingArmyTextView = new TextView(standingArmyGrid.getContext());
    	standingArmyTextView.setTextSize(14);
    	standingArmyTextView.setTextColor(Color.WHITE);
    	standingArmyTextView.setText(staticTheCache.getGarrison() + " soldiers");
    	standingArmyTextView.setGravity(Gravity.RIGHT);
    	standingArmyTextView.setLayoutParams(standingArmyTextViewLayout);
    	standingArmyGrid.addView(standingArmyTextView, standingArmyTextViewLayout);
    	
    	LayoutParams standingArmyGridLayout = new LayoutParams(row6, col1);
    	mainArea.addView(standingArmyGrid, standingArmyGridLayout);
		
		return mainArea;
	}
	
	////////
	//
	//getMe
	//
	//returns the last created instance of this class, could be null
	//
	////////
	public static VisitYourCacheScreen getMe()
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
				new VisitYourCacheScreen(VisitYourCacheScreen.getStaticTheCache());
			}
		};
	}
	
	public static Cache getStaticTheCache()
	{
		return staticTheCache;
	}
}
