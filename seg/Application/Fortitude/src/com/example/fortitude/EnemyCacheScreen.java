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
	private EnemyCacheScreen me;
	
	private static Cache staticTheCache;
	private static User staticTheUser;
	
	private Spec row1 = GridLayout.spec(0);
	private Spec row2 = GridLayout.spec(1);
	private Spec row3 = GridLayout.spec(2);
	private Spec row4 = GridLayout.spec(3);
	private Spec row5 = GridLayout.spec(4);
	
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
		mainArea.setRowCount(5);
		
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
	public EnemyCacheScreen getMe()
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
