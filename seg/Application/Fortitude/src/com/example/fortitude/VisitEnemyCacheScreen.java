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

public class VisitEnemyCacheScreen extends Window
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
	
	private Spec col1 = GridLayout.spec(0);
	private Spec col2 = GridLayout.spec(1);
	private Spec col3 = GridLayout.spec(2);
	private Spec col4 = GridLayout.spec(3);
	
	private static VisitEnemyCacheScreen me;
	private static Cache staticTheCache;
	private static User staticTheUser;
	
	public VisitEnemyCacheScreen(Cache theCache, User theUser)
	{
		super(R.drawable.enemy_cache);
    	me = this;
    	staticTheCache = theCache;
    	staticTheUser = theUser;
        addContentToContentPane(createWindowPane());
	}
	
	private GridLayout createWindowPane()
	{
		GridLayout mainArea = new GridLayout(Fortitude.getFortitude());
		mainArea.setColumnCount(1);
		mainArea.setRowCount(10);
		
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
		topBarGrid.setRowCount(5);
		
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
    	cacheNameTextView.setText(VisitEnemyCacheScreen.getStaticTheCache().getCacheName());
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
    	usernameTextView.setText(VisitEnemyCacheScreen.getStaticTheUser().getUserName());
    	mainArea.addView(usernameTextView, usernameTextViewLayout);
    	
    	LayoutParams fithRowSpaceLayout = new LayoutParams(row5, col1); //fith row space
    	fithRowSpaceLayout.height = super.getWindowHeight() / 30;
    	Space fithRowSpace = new Space(mainArea.getContext());
    	fithRowSpace.setLayoutParams(fithRowSpaceLayout);
    	//mainArea.addView(fithRowSpace, fithRowSpaceLayout);
		
    	LayoutParams sixthRowTextViewLayout = new LayoutParams(row6, col1);
    	sixthRowTextViewLayout.width = super.getWindowWidth();
    	sixthRowTextViewLayout.height = super.getWindowHeight() / 10;
    	TextView sixthRowTextView = new TextView(mainArea.getContext());
    	sixthRowTextView.setGravity(Gravity.CENTER);
    	sixthRowTextView.setTextSize(12);
    	sixthRowTextView.setTextColor(Color.WHITE);
    	sixthRowTextView.setText("You and your army arrive at an enemy fortification, what would you like to do?");
    	sixthRowTextView.setLayoutParams(sixthRowTextViewLayout);
    	mainArea.addView(sixthRowTextView, sixthRowTextViewLayout);
    	
    	LayoutParams seventhRowSpaceLayout = new LayoutParams(row7, col1);
    	seventhRowSpaceLayout.height = super.getWindowHeight() / 8;
    	Space seventhRowSpace = new Space(mainArea.getContext());
    	seventhRowSpace.setLayoutParams(seventhRowSpaceLayout);
    	mainArea.addView(seventhRowSpace, seventhRowSpaceLayout);
    	
    	GridLayout eighthRowGrid = new GridLayout(mainArea.getContext());
    	eighthRowGrid.setColumnCount(5);
    	eighthRowGrid.setRowCount(1);
    	
    	LayoutParams eighthRowLeftSpaceLayout = new LayoutParams(row1, col1);
    	eighthRowLeftSpaceLayout.width = (super.getWindowWidth() / 10) - (super.getWindowWidth() / 40);
    	Space eighthRowLeftSpace = new Space(eighthRowGrid.getContext());
    	eighthRowLeftSpace.setLayoutParams(eighthRowLeftSpaceLayout);
    	eighthRowGrid.addView(eighthRowLeftSpace, eighthRowLeftSpaceLayout);
    	
    	LayoutParams scoutCacheButtonLayout = new LayoutParams(row1, col2); //plan route button
    	scoutCacheButtonLayout.width = (super.getWindowWidth() / 2) - (super.getWindowWidth() / 10);
    	scoutCacheButtonLayout.height = super.getWindowHeight() / 10;
    	FortitudeButton scoutCacheButton = (new FortitudeButton(R.drawable.increase_army, R.drawable.increase_army_pressed) {
    		public void preClickActions()
    		{
    			
    		}
    		public void whenClicked()
    		{
    			
    		}
    	});
    	scoutCacheButton.setLayoutParams(scoutCacheButtonLayout);
    	eighthRowGrid.addView(scoutCacheButton, scoutCacheButtonLayout);
    	
    	LayoutParams eighthRowMiddleSpacerLayout = new LayoutParams(row1, col3); //eighth row middle spacer
    	eighthRowMiddleSpacerLayout.width = super.getWindowWidth() / 15;
    	Space eighthRowMiddleSpacer = new Space(eighthRowGrid.getContext());
    	eighthRowMiddleSpacer.setLayoutParams(eighthRowMiddleSpacerLayout);
    	eighthRowGrid.addView(eighthRowMiddleSpacer, eighthRowMiddleSpacerLayout);
    	
    	LayoutParams attackCacheLayout = new LayoutParams(row1, col4);
    	attackCacheLayout.width = (super.getWindowWidth() / 2) - (super.getWindowWidth() / 10);
    	attackCacheLayout.height = super.getWindowHeight() / 10;
    	FortitudeButton attackCacheButton = (new FortitudeButton(R.drawable.decrease_army, R.drawable.decrease_army_pressed) {
    		public void preClickActions()
    		{
    			
    		}
    		public void whenClicked()
    		{
    			
    		}
    	});
    	attackCacheButton.setLayoutParams(attackCacheLayout);
    	eighthRowGrid.addView(attackCacheButton, attackCacheLayout);
    	
    	LayoutParams eighthRowGridLayout = new LayoutParams(row8, col1);
    	mainArea.addView(eighthRowGrid, eighthRowGridLayout);
    	
    	LayoutParams ninthRowSpaceLayout = new LayoutParams(row9, col1);
    	ninthRowSpaceLayout.height = super.getWindowHeight() / 30;
    	Space ninthRowSpace = new Space(mainArea.getContext());
    	ninthRowSpace.setLayoutParams(ninthRowSpaceLayout);
    	mainArea.addView(ninthRowSpace, ninthRowSpaceLayout);

    	GridLayout cancelButtonGrid = new GridLayout(mainArea.getContext());
    	cancelButtonGrid.setColumnCount(3);
    	cancelButtonGrid.setRowCount(1);
    	
    	LayoutParams spaceLeftOfButtonLayout = new LayoutParams(row1, col1);
    	spaceLeftOfButtonLayout.width = (super.getWindowWidth() / 4)  + (super.getWindowWidth() / 20);
    	Space spaceLeftOfButton = new Space(cancelButtonGrid.getContext());
    	spaceLeftOfButton.setLayoutParams(spaceLeftOfButtonLayout);
    	cancelButtonGrid.addView(spaceLeftOfButton, spaceLeftOfButtonLayout);
    	
    	LayoutParams cancelButtonLayout = new LayoutParams(row1, col2); //cancel button
    	cancelButtonLayout.height = super.getWindowHeight() / 10;
    	cancelButtonLayout.width = (super.getWindowWidth() / 2) - (super.getWindowWidth() / 10);
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
    	cancelButtonGrid.addView(cancelButton, cancelButtonLayout);
    	
    	LayoutParams cancelButtonGridLayout = new LayoutParams(row10, col1);
    	mainArea.addView(cancelButtonGrid, cancelButtonGridLayout);
    	
		return mainArea;
	}
	
	////////
	//
	//getMe
	//
	//returns the last created instance of this class, could be null
	//
	////////
	public static VisitEnemyCacheScreen getMe()
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
				new VisitEnemyCacheScreen(VisitEnemyCacheScreen.getStaticTheCache(), VisitEnemyCacheScreen.getStaticTheUser());
			}
		};
	}
	
	public static Cache getStaticTheCache()
	{
		return staticTheCache;
	}
	
	public static User getStaticTheUser()
	{
		return staticTheUser;
	}
}
