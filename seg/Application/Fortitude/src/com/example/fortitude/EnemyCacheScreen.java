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

public class EnemyCacheScreen extends Window
{
	private static EnemyCacheScreen me;
	
	private static Cache staticTheCache;
	private static User staticTheUser;
	
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
	
	private Spec col1 = GridLayout.spec(0);
	private Spec col2 = GridLayout.spec(1);
	private Spec col3 = GridLayout.spec(2);
	private Spec col4 = GridLayout.spec(3);
	
	private Spec allcols = GridLayout.spec(0, 2);
	
	public EnemyCacheScreen(Cache theCache, User theUser)
	{
		super(R.drawable.enemy_cache);
		staticTheCache = theCache;
		staticTheUser = theUser;
		me = this;
		
		//GridLayout topBarSpaceGrid = new GridLayout(super.getContext());   //CODE TO MATCH LINE HEIGHTS
		//topBarSpaceGrid.setColumnCount(1);
		//topBarSpaceGrid.setRowCount(2);
		
		//LayoutParams topBarSpaceLayout = new LayoutParams(row1, col1);
		//topBarSpaceLayout.width = this.getWindowWidth();
		//topBarSpaceLayout.height = (this.getWindowHeight() / 20) + (this.getWindowHeight() / 70);
		//Space topBarSpace = new Space(topBarSpaceGrid.getContext());
		//topBarSpace.setLayoutParams(topBarSpaceLayout);
		//topBarSpaceGrid.addView(topBarSpace, topBarSpaceLayout);
		
		//LayoutParams backgroundImageLayout = new LayoutParams(row2, col1);
		//backgroundImageLayout.width = this.getWindowWidth();
		//backgroundImageLayout.height = this.getWindowHeight() - topBarSpaceLayout.height;
		//BackgroundImage backgroundImage = new BackgroundImage(imageID);
		//backgroundImage.setLayoutParams(backgroundImageLayout);
		//topBarSpaceGrid.addView(backgroundImage, backgroundImageLayout);
		
		//LayoutParams topBarSpaceGridLayout = new LayoutParams(row2, col2);
		//super.addView(topBarSpaceGrid, topBarSpaceGridLayout);
		
		addContentToContentPane(createWindowPane());
	}
	
	public GridLayout createWindowPane()
	{
		GridLayout mainArea = new GridLayout(Fortitude.getFortitude());
		mainArea.setColumnCount(1);
		mainArea.setRowCount(11);
		
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
    	cacheNameTextView.setText(EnemyCacheScreen.getStaticTheCache().getCacheName());
    	cacheNameTextView.setLayoutParams(cacheNameTextViewLayout);
    	cacheNameGrid.addView(cacheNameTextView, cacheNameTextViewLayout);
    	
    	LayoutParams cacheNameGridLayout = new LayoutParams(row3, col1);
    	mainArea.addView(cacheNameGrid, cacheNameGridLayout);
    	
    	GridLayout avatarUsernameGrid = new GridLayout(mainArea.getContext());
    	avatarUsernameGrid.setRowCount(1);
    	avatarUsernameGrid.setColumnCount(4);
    	
    	LayoutParams fourthRowLeftSpacerLayout = new LayoutParams(row1, col1);
    	fourthRowLeftSpacerLayout.width = (super.getWindowWidth() / 9);
    	Space fourthRowLeftSpacer = new Space(avatarUsernameGrid.getContext());
    	fourthRowLeftSpacer.setLayoutParams(fourthRowLeftSpacerLayout);
    	avatarUsernameGrid.addView(fourthRowLeftSpacer, fourthRowLeftSpacerLayout);
    	
    	LayoutParams avatarImageLayout = new LayoutParams(row1, col2); //avatar image
    	avatarImageLayout.width = super.getWindowWidth() / 4;
    	avatarImageLayout.height = super.getWindowWidth() / 4;
    	ImageView avatarImage = new ImageView(avatarUsernameGrid.getContext());
    	avatarImage.setScaleType(ScaleType.FIT_XY);
    	avatarImage.setLayoutParams(avatarImageLayout);
    	avatarImage.setImageResource(EnemyCacheScreen.getTheUser().getIntAvatarId());
    	avatarUsernameGrid.addView(avatarImage, avatarImageLayout);
    	
    	LayoutParams avatarImageMiddleSpaceLayout = new LayoutParams(row1, col3); //avatar username spacer
    	avatarImageMiddleSpaceLayout.width = super.getWindowWidth() / 22;
    	Space avatarImageMiddleSpace = new Space(avatarUsernameGrid.getContext());
    	avatarImageMiddleSpace.setLayoutParams(avatarImageMiddleSpaceLayout);
    	avatarUsernameGrid.addView(avatarImageMiddleSpace, avatarImageMiddleSpaceLayout);
    	
    	LayoutParams usernameTextViewLayout = new LayoutParams(row1, col4); //USERNAME TEXTVIEW
    	usernameTextViewLayout.width = (super.getWindowWidth() / 2);
    	usernameTextViewLayout.height = super.getWindowWidth() / 4;
    	TextView usernameTextView = new TextView(avatarUsernameGrid.getContext());
    	usernameTextView.setTextSize(24);
    	usernameTextView.setTextColor(Color.rgb(160, 160, 160));
    	usernameTextView.setGravity(Gravity.CENTER_VERTICAL);
    	usernameTextView.setTypeface(Typeface.createFromAsset(Fortitude.getFortitude().getAssets(), "Fonts/Copperplate-Gothic-Light-Regular.ttf"));
    	usernameTextView.setText(EnemyCacheScreen.getTheUser().getUserName());
    	avatarUsernameGrid.addView(usernameTextView, usernameTextViewLayout);
    	
    	LayoutParams avatarUsernameGridLayout = new LayoutParams(row4, col1);
    	mainArea.addView(avatarUsernameGrid, avatarUsernameGridLayout);
    	
    	LayoutParams fithRowSpaceLayout = new LayoutParams(row5, col1); //fith row space
    	fithRowSpaceLayout.height = super.getWindowHeight() / 30;
    	Space fithRowSpace = new Space(mainArea.getContext());
    	fithRowSpace.setLayoutParams(fithRowSpaceLayout);
    	mainArea.addView(fithRowSpace, fithRowSpaceLayout);
    	
    	GridLayout sixthRowGrid = new GridLayout(mainArea.getContext());
    	sixthRowGrid.setRowCount(1);
    	sixthRowGrid.setColumnCount(5);
    	
    	LayoutParams sixthLeftSpacerLayout = new LayoutParams(row1, col1); //sixth row left spacer
    	sixthLeftSpacerLayout.width = (super.getWindowWidth() / 10) - (super.getWindowWidth() / 40);
    	Space sixthLeftSpacer = new Space(sixthRowGrid.getContext());
    	sixthLeftSpacer.setLayoutParams(sixthLeftSpacerLayout);
    	sixthRowGrid.addView(sixthLeftSpacer, sixthLeftSpacerLayout);
    	
    	LayoutParams seeProfileButtonLayout = new LayoutParams(row1, col2); //seeProfileButton
    	seeProfileButtonLayout.width = (super.getWindowWidth() / 2) - (super.getWindowWidth() / 10);
    	seeProfileButtonLayout.height = super.getWindowHeight() / 10;
    	FortitudeButton seeProfileButton = (new FortitudeButton(R.drawable.see_profile, R.drawable.see_profile_pressed) {
    		public void preClickActions()
    		{
    			
    		}
    		public void whenClicked()
    		{
    			
    		}
    	});
    	seeProfileButton.setLayoutParams(seeProfileButtonLayout);
    	sixthRowGrid.addView(seeProfileButton, seeProfileButtonLayout);
    	
    	LayoutParams sixthRowMiddleSpacerLayout = new LayoutParams(row1, col3); //sixth row middle spacer
    	sixthRowMiddleSpacerLayout.width = super.getWindowWidth() / 15;
    	Space sixthRowMiddleSpacer = new Space(sixthRowGrid.getContext());
    	sixthRowMiddleSpacer.setLayoutParams(sixthRowMiddleSpacerLayout);
    	sixthRowGrid.addView(sixthRowMiddleSpacer, sixthRowMiddleSpacerLayout);
    	
    	LayoutParams sendMessageButtonLayout = new LayoutParams(row1, col4); //send message button
    	sendMessageButtonLayout.width = (super.getWindowWidth() / 2) - (super.getWindowWidth() / 10);
    	seeProfileButtonLayout.height = super.getWindowHeight() / 10;
    	FortitudeButton sendMessageButton = (new FortitudeButton(R.drawable.send_message, R.drawable.send_message_pressed) {
    		public void preClickActions()
    		{
    			
    		}
    		public void whenClicked()
    		{
    			
    		}
    	});
    	sendMessageButton.setLayoutParams(sendMessageButtonLayout);
    	sixthRowGrid.addView(sendMessageButton, sendMessageButtonLayout);
    	
    	LayoutParams sixthRowGridLayout = new LayoutParams(row6, col1);
    	mainArea.addView(sixthRowGrid, sixthRowGridLayout);
    	
    	LayoutParams seventhRowTextViewLayout = new LayoutParams(row7, col1);
    	seventhRowTextViewLayout.width = super.getWindowWidth();
    	seventhRowTextViewLayout.height = super.getWindowHeight() / 20;
    	TextView seventhRowTextView = new TextView(mainArea.getContext());
    	seventhRowTextView.setGravity(Gravity.CENTER);
    	seventhRowTextView.setTextSize(12);
    	seventhRowTextView.setTextColor(Color.WHITE);
    	seventhRowTextView.setText("Stuff about distances between caches here :)");
    	seventhRowTextView.setLayoutParams(seventhRowTextViewLayout);
    	mainArea.addView(seventhRowTextView, seventhRowTextViewLayout);
    	
    	LayoutParams eighthRowSpaceLayout = new LayoutParams(row8, col1);
    	eighthRowSpaceLayout.height = super.getWindowHeight() / 40;
    	Space eighthRowSpace = new Space(mainArea.getContext());
    	eighthRowSpace.setLayoutParams(eighthRowSpaceLayout);
    	mainArea.addView(eighthRowSpace, eighthRowSpaceLayout);
    	
    	GridLayout ninthRowGrid = new GridLayout(mainArea.getContext());
    	ninthRowGrid.setColumnCount(4);
    	ninthRowGrid.setRowCount(1);
    	
    	LayoutParams ninthLeftSpacerLayout = new LayoutParams(row1, col1); //ninth row left spacer
    	ninthLeftSpacerLayout.width = (super.getWindowWidth() / 10) - (super.getWindowWidth() / 40);
    	Space ninthLeftSpacer = new Space(ninthRowGrid.getContext());
    	ninthLeftSpacer.setLayoutParams(ninthLeftSpacerLayout);
    	ninthRowGrid.addView(ninthLeftSpacer, ninthLeftSpacerLayout);
    	
    	LayoutParams planRouteButtonLayout = new LayoutParams(row1, col2); //plan route button
    	planRouteButtonLayout.width = (super.getWindowWidth() / 2) - (super.getWindowWidth() / 10);
    	planRouteButtonLayout.height = super.getWindowHeight() / 10;
    	FortitudeButton planRouteButton = (new FortitudeButton(R.drawable.plan_route, R.drawable.plan_route_pressed) {
    		public void preClickActions()
    		{
    			
    		}
    		public void whenClicked()
    		{
    			
    		}
    	});
    	planRouteButton.setLayoutParams(planRouteButtonLayout);
    	ninthRowGrid.addView(planRouteButton, planRouteButtonLayout);
    	
    	LayoutParams ninthRowMiddleSpaceLayout = new LayoutParams(row1, col3); //ninth row middle space
    	ninthRowMiddleSpaceLayout.width = super.getWindowWidth() / 15;
    	Space ninthRowMiddleSpace = new Space(ninthRowGrid.getContext());
    	ninthRowMiddleSpace.setLayoutParams(ninthRowMiddleSpaceLayout);
    	ninthRowGrid.addView(ninthRowMiddleSpace, ninthRowMiddleSpaceLayout);
    	
    	LayoutParams cancelButtonLayout = new LayoutParams(row1, col4); //cancel button
    	cancelButtonLayout.width = (super.getWindowWidth() / 2) - (super.getWindowWidth() / 10);
    	cancelButtonLayout.height = super.getWindowHeight() / 10;
    	FortitudeButton cancelButton = (new FortitudeButton(R.drawable.cancel, R.drawable.cancel_pressed) {
    		public void preClickActions()
    		{
    		
    		}
    		public void whenClicked()
    		{
    			EnemyCacheScreen.getMe().killMe();
    			new MainScreen();
    		}
    	});
    	cancelButton.setLayoutParams(cancelButtonLayout);
    	ninthRowGrid.addView(cancelButton, cancelButtonLayout);
    	
    	LayoutParams ninthRowGridLayout = new LayoutParams(row9, col1);
    	mainArea.addView(ninthRowGrid, ninthRowGridLayout);
    	
    	LayoutParams tenthRowSpaceLayout = new LayoutParams(row10, col1);
    	tenthRowSpaceLayout.height = super.getWindowWidth() / 20;
    	Space tenthRowSpace = new Space(mainArea.getContext());
    	tenthRowSpace.setLayoutParams(tenthRowSpaceLayout);
    	mainArea.addView(tenthRowSpace, tenthRowSpaceLayout);
    	
    	GridLayout reportCacheGrid = new GridLayout(mainArea.getContext());
    	reportCacheGrid.setColumnCount(3);
    	reportCacheGrid.setRowCount(1);
    	
    	LayoutParams reportCacheGridLeftSpaceLayout = new LayoutParams(row1, col1);
    	reportCacheGridLeftSpaceLayout.width = super.getWindowWidth() / 4;
    	Space reportCacheGridLeftSpace = new Space(reportCacheGrid.getContext());
    	reportCacheGridLeftSpace.setLayoutParams(reportCacheGridLeftSpaceLayout);
    	reportCacheGrid.addView(reportCacheGridLeftSpace, reportCacheGridLeftSpaceLayout);
    	
    	LayoutParams reportCacheButtonLayout = new LayoutParams(row1, col2);
    	reportCacheButtonLayout.width = super.getWindowWidth() / 2;
    	reportCacheButtonLayout.height = super.getWindowHeight() / 10;
    	FortitudeButton reportCacheButton = (new FortitudeButton(R.drawable.report_cache, R.drawable.report_cache_pressed) {
    		public void preClickActions()
    		{
    			
    		}
    		public void whenClicked()
    		{
    			
    		}
    	});
    	reportCacheButton.setLayoutParams(reportCacheButtonLayout);
    	reportCacheGrid.addView(reportCacheButton, reportCacheButtonLayout);
    	
    	LayoutParams reportCacheGridLayout = new LayoutParams(row11, col1);
    	mainArea.addView(reportCacheGrid, reportCacheGridLayout);
    	
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
				new EnemyCacheScreen(EnemyCacheScreen.getStaticTheCache(), EnemyCacheScreen.getTheUser());
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
	public static EnemyCacheScreen getMe()
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
    //staticTheCache
    //
    //returns the cache that created the last instance of this class
    //
    ////////
    public static Cache getStaticTheCache()
    {
    	return staticTheCache;
    }
    
    public static User getTheUser()
    {
    	return staticTheUser;
    }
}
