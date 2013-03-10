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

public class YourCacheScreen extends Window
{
	private static YourCacheScreen me;
	private static Cache staticTheCache;
	
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
	private Spec col5 = GridLayout.spec(4);
	
	public YourCacheScreen(Cache theCache)
	{
		super(R.drawable.allied_cache);
    	me = this;
    	staticTheCache = theCache;
        addContentToContentPane(createWindowPane());
	}
	
	public GridLayout createWindowPane()
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
    	cacheNameTextView.setText(YourCacheScreen.getStaticTheCache().getCacheName());
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
    	if(staticTheCache.isMine())
    	{
    	    usernameTextView.setText(CurrentUser.getMe().getUserName());
    	}
    	else if(staticTheCache.isAdminCache())
    	{
    		usernameTextView.setText("OUTLAWS");
    	}
    	else
    	{
    		usernameTextView.setText("UNOWNED");
    	}
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
    	if(staticTheCache.isMine())
    	{
    	    standingArmyTextView.setText(staticTheCache.getGarrison() + " soldiers");
    	}
    	else if(staticTheCache.isUnowned())
    	{
    		standingArmyTextView.setText("0 soldiers");
    	}
    	else
    	{
    		standingArmyTextView.setText("bandits");
    	}
    	standingArmyTextView.setGravity(Gravity.RIGHT);
    	standingArmyTextView.setLayoutParams(standingArmyTextViewLayout);
    	standingArmyGrid.addView(standingArmyTextView, standingArmyTextViewLayout);
    	
    	LayoutParams standingArmyGridLayout = new LayoutParams(row6, col1);
    	mainArea.addView(standingArmyGrid, standingArmyGridLayout);
    	
    	LayoutParams seventhRowSpaceLayout = new LayoutParams(row7, col1); //seventh row space
    	seventhRowSpaceLayout.height = super.getWindowHeight() / 30;
    	Space seventhRowSpace = new Space(mainArea.getContext());
    	seventhRowSpace.setLayoutParams(seventhRowSpaceLayout);
    	mainArea.addView(seventhRowSpace, seventhRowSpaceLayout);
    	
    	LayoutParams eighthRowTextViewLayout = new LayoutParams(row8, col1);
    	eighthRowTextViewLayout.width = super.getWindowWidth();
    	eighthRowTextViewLayout.height = (super.getWindowHeight() / 20);
    	TextView eighthRowTextView = new TextView(mainArea.getContext());
    	eighthRowTextView.setGravity(Gravity.CENTER);
    	eighthRowTextView.setTextSize(12);
    	eighthRowTextView.setTextColor(Color.WHITE);
    	eighthRowTextView.setText("");
    	eighthRowTextView.setLayoutParams(eighthRowTextViewLayout);
    	mainArea.addView(eighthRowTextView, eighthRowTextViewLayout);
    	
    	LayoutParams ninthRowSpaceLayout = new LayoutParams(row9, col1);
    	ninthRowSpaceLayout.height = super.getWindowHeight() / 30;
    	Space ninthRowSpace = new Space(mainArea.getContext());
    	ninthRowSpace.setLayoutParams(ninthRowSpaceLayout);
    	mainArea.addView(ninthRowSpace, ninthRowSpaceLayout);
    	
    	GridLayout tenthRowGrid = new GridLayout(mainArea.getContext());
    	tenthRowGrid.setColumnCount(5);
    	tenthRowGrid.setRowCount(1);
    	
    	LayoutParams tenthRowLeftSpaceLayout = new LayoutParams(row1, col1);
    	tenthRowLeftSpaceLayout.width = (super.getWindowWidth() / 10) - (super.getWindowWidth() / 40);
    	Space tenthRowLeftSpace = new Space(tenthRowGrid.getContext());
    	tenthRowLeftSpace.setLayoutParams(tenthRowLeftSpaceLayout);
    	tenthRowGrid.addView(tenthRowLeftSpace, tenthRowLeftSpaceLayout);
    	
    	LayoutParams planRouteButtonLayout = new LayoutParams(row1, col2); //plan route button
    	planRouteButtonLayout.width = (super.getWindowWidth() / 2) - (super.getWindowWidth() / 10);
    	planRouteButtonLayout.height = super.getWindowHeight() / 10;
    	FortitudeButton planRouteButton = (new FortitudeButton(R.drawable.plan_route, R.drawable.plan_route_pressed) {
    		public void preClickActions()
    		{
    			
    		}
    		public void whenClicked()
    		{
				if(TheMap.getMe().getGoogleMap().getMyLocation() == null)
				{
					MessageBox.newMsgBox("Unable to get your location!", true);
					return;
				}
				ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Mapping Route", false));
				ServerRequests.getGoogleMapRoute(Double.toString(TheMap.getMe().getGoogleMap().getMyLocation().getLatitude()) + "," + Double.toString(TheMap.getMe().getGoogleMap().getMyLocation().getLongitude()), YourCacheScreen.getStaticTheCache().getLat() + "," + YourCacheScreen.getStaticTheCache().getLon());
				Thread thread = new Thread(new Runnable() {
					public void run()
					{
						while(!ServerRequests.getStaticGoogleRouteComplete())
						{
							//wait
						}
						if(ServerRequests.getStaticGoogleRouteSuccess())
						{
							Fortitude.getFortitude().runOnUiThread(new Runnable() {
								public void run()
								{
									if(ServerRequests.getTheMessageBox() != null)
									{
										ServerRequests.getTheMessageBox().killMe();
									}
									if(MessageBox.getMe() != null)
									{
										MessageBox.getMe().killMe();
									}
									YourCacheScreen.getMe().killMe();
									new MainScreen();
								}
							});
						}
					}
				});
				thread.start();
    		}
    	});
    	planRouteButton.setLayoutParams(planRouteButtonLayout);
    	tenthRowGrid.addView(planRouteButton, planRouteButtonLayout);
    	
    	LayoutParams tenthRowMiddleSpacerLayout = new LayoutParams(row1, col3); //tenth row middle spacer
    	tenthRowMiddleSpacerLayout.width = super.getWindowWidth() / 15;
    	Space tenthRowMiddleSpacer = new Space(tenthRowGrid.getContext());
    	tenthRowMiddleSpacer.setLayoutParams(tenthRowMiddleSpacerLayout);
    	tenthRowGrid.addView(tenthRowMiddleSpacer, tenthRowMiddleSpacerLayout);
    	
    	LayoutParams cancelButtonLayout = new LayoutParams(row1, col4);
    	cancelButtonLayout.width = (super.getWindowWidth() / 2) - (super.getWindowWidth() / 10);
    	cancelButtonLayout.height = super.getWindowHeight() / 10;
    	FortitudeButton cancelButton = (new FortitudeButton(R.drawable.cancel, R.drawable.cancel_pressed) {
    		public void preClickActions()
    		{
    			
    		}
    		public void whenClicked()
    		{
    			YourCacheScreen.getMe().killMe();
    			new MainScreen();
    			GUI.makeAllTheGUIElementsBetter(Fortitude.getFortitude().getWindow().getDecorView());
    		}
    	});
    	cancelButton.setLayoutParams(cancelButtonLayout);
    	tenthRowGrid.addView(cancelButton, cancelButtonLayout);
    	
    	LayoutParams tenthRowGridLayout = new LayoutParams(row10, col1);
    	mainArea.addView(tenthRowGrid, tenthRowGridLayout);
    	
		return mainArea;
	}
	
	////////
	//
	//getMe
	//
	//returns the last created instance of this class, could be null
	//
	////////
	public static YourCacheScreen getMe()
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
				new YourCacheScreen(YourCacheScreen.getStaticTheCache());
			}
		};
	}
	
	public static Cache getStaticTheCache()
	{
		return staticTheCache;
	}
}
