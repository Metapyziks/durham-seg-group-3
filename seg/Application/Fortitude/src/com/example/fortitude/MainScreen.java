package com.example.fortitude;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;

import android.widget.GridLayout;
import android.widget.GridLayout.LayoutParams;
import android.widget.GridLayout.Spec;
import android.widget.Space;
import android.widget.TextView;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.ImageView.ScaleType;
import android.widget.ImageView;
import android.graphics.Color;
import android.view.LayoutInflater;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.Marker;

public class MainScreen extends Window
{
	private String googleMapsKey = "AIzaSyBl6HtJfwSSQ3X44pjMVftPnLdFEvarHCQ";
	//intent = new Intent(CONTEXT, MYMAP.CLASS);
	//Fortitude.getFortitude().startActivity();

	private Spec row1 = GridLayout.spec(0);
	private Spec row2 = GridLayout.spec(1);
	private Spec row3 = GridLayout.spec(2);
	private Spec row4 = GridLayout.spec(3);
	private Spec row5 = GridLayout.spec(4);
	private Spec row6 = GridLayout.spec(5);

	private Spec col1 = GridLayout.spec(0);
	private Spec col2 = GridLayout.spec(1);
	private Spec col3 = GridLayout.spec(2);
	private Spec col4 = GridLayout.spec(3);

	private Spec allcols = GridLayout.spec(0,1);

	private TextView userBalanceTextView = null;

	FortitudeButton zoomInButton;
	FortitudeButton zoomOutButton;

	private static MainScreen me = null;

	private ImageView mailIcon;
	private ImageView flagIcon;
	private ImageView castleIcon;
	private ImageView refreshIcon;

	private static boolean castleClickable = false;

	////////
	//
	//newMainLoginScreen
	//
	//static constructor
	//
	////////
	public static void newMainLoginScreen()
	{
		new MainLoginScreen();	
	}

	////////
	//
	//Constructor
	//
	////////
	public MainScreen()
	{
		super();
		me = this;
		addContentToContentPane(createWindowPane());
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
				new MainScreen();
			}
		};
	}

	////////
	//
	//getUserBalanceTextView
	//
	//gets the userbalancetext view so the balance displayed can be updated remotely
	//
	////////
	public TextView getUserBalanceTextView()
	{
		return userBalanceTextView;
	}

	////////
	//
	//createWindowPane
	//
	//create a gridlayout to go in the window
	//
	////////
	private GridLayout createWindowPane()
	{
		GridLayout mainArea = new GridLayout(super.getContext()); //GridLayout to be returned
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

		GridLayout topBarGrid = new GridLayout(mainArea.getContext()); //grid to be put on top of the top bar image containing two 
		topBarGrid.setColumnCount(2);                                  //textviews for username and balance
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
		userBalanceTextView = new TextView(topBarGrid.getContext());
		userBalanceTextView.setTextColor(Color.YELLOW);
		userBalanceTextView.setText(CurrentUser.getMe().getBalance() + "  ");
		userBalanceTextView.setLayoutParams(userBalanceTextViewLayout);
		userBalanceTextView.setGravity(Gravity.RIGHT);
		topBarGrid.addView(userBalanceTextView, userBalanceTextViewLayout);

		LayoutParams topBarGridLayout = new LayoutParams(row1, col1);
		mainArea.addView(topBarGrid, topBarGridLayout);

		LayoutParams theMapLayout = new LayoutParams(row2, col1); //The map
		theMapLayout.width = super.getWindowWidth();
		theMapLayout.height = super.getWindowHeight() - (super.getWindowHeight() / 5) - (super.getWindowHeight() / 20);
		try
		{
			mainArea.addView(TheMap.getMe(), theMapLayout);
		}
		catch(IllegalStateException e)
		{
			TheMap.getMe().removeMeFromMyParent();
			mainArea.addView(TheMap.getMe(), theMapLayout);
		}

		GridLayout controlsGrid = new GridLayout(mainArea.getContext());
		controlsGrid.setColumnCount(2);
		controlsGrid.setRowCount(6);

		LayoutParams controlGridPositionalSpaceLayout = new LayoutParams(row1, col1);
		controlGridPositionalSpaceLayout.height = super.getWindowHeight() / 30;
		controlGridPositionalSpaceLayout.width = 10;
		Space controlGridPositionalSpace = new Space(controlsGrid.getContext());
		controlGridPositionalSpace.setLayoutParams(controlGridPositionalSpaceLayout);
		controlsGrid.addView(controlGridPositionalSpace, controlGridPositionalSpaceLayout);

		LayoutParams zoomInButtonLayout = new LayoutParams(row2, col2); //zoom in button
		zoomInButtonLayout.width = 30;
		zoomInButtonLayout.height = 30;
		zoomInButton = (new FortitudeButton(R.drawable.zoom_plus, R.drawable.zoom_plus) {
			public void preClickActions()
			{

			}
			public void whenClicked()
			{

			}
		});
		zoomInButton.setLayoutParams(zoomInButtonLayout);
		controlsGrid.addView(zoomInButton, zoomInButtonLayout);

		LayoutParams firstGapLayout = new LayoutParams(row3, col2);
		firstGapLayout.width = 10;
		firstGapLayout.height = 10;
		Space firstGap = new Space(controlsGrid.getContext());
		firstGap.setLayoutParams(firstGapLayout);
		controlsGrid.addView(firstGap, firstGapLayout);

		LayoutParams zoomOutButtonLayout = new LayoutParams(row4, col2); //zoom out button
		zoomOutButtonLayout.width = 30;
		zoomOutButtonLayout.height = 30;
		zoomOutButton = (new FortitudeButton(R.drawable.zoom_minus, R.drawable.zoom_minus) {
			public void preClickActions()
			{

			}
			public void whenClicked()
			{

			}
		});
		zoomOutButton.setLayoutParams(zoomOutButtonLayout);
		controlsGrid.addView(zoomOutButton, zoomOutButtonLayout);

		LayoutParams secondGapLayout = new LayoutParams(row5, col2);
		secondGapLayout.width = 10;
		secondGapLayout.height = 10;
		Space secondGap = new Space(controlsGrid.getContext());
		secondGap.setLayoutParams(secondGapLayout);
		controlsGrid.addView(secondGap, secondGapLayout);

		LayoutParams toggleTerrotoryButtonLayout = new LayoutParams(row6, col2); //zoom out button
		toggleTerrotoryButtonLayout.width = 30;
		toggleTerrotoryButtonLayout.height = 30;
		FortitudeButton toggleTerrotoryButton = (new FortitudeButton(R.drawable.toggle_areas, R.drawable.toggle_areas) {
			public void preClickActions()
			{

			}
			public void whenClicked()
			{

			}
		});
		toggleTerrotoryButton.setLayoutParams(toggleTerrotoryButtonLayout);
		controlsGrid.addView(toggleTerrotoryButton, toggleTerrotoryButtonLayout);

		LayoutParams controlsGridLayout = new LayoutParams(row2, col1);
		mainArea.addView(controlsGrid, controlsGridLayout);

		if(!CurrentUser.getMe().isVerified())
		{
			LayoutParams activateAccountBarLayout = new LayoutParams(row3, col1);
			activateAccountBarLayout.width = super.getWindowWidth();
			activateAccountBarLayout.height = super.getWindowHeight() / 5;
			ImageView activateAccountBar = new ImageView(mainArea.getContext());
			activateAccountBar.setLayoutParams(activateAccountBarLayout);
			activateAccountBar.setScaleType(ScaleType.FIT_XY);
			activateAccountBar.setOnClickListener(new OnClickListener() {
				public void onClick(View v)
				{
					TheMap.getMe().zoomToMyPositionAtMyZoom();
				}
			});
			activateAccountBar.setImageResource(R.drawable.activate_account);
			mainArea.addView(activateAccountBar, activateAccountBarLayout);
		}
		else
		{
			LayoutParams bottomBarImageLayout = new LayoutParams(row3, col1);
			bottomBarImageLayout.width = super.getWindowWidth();
			bottomBarImageLayout.height = super.getWindowHeight() / 5;
			ImageView bottomBarImage = new ImageView(mainArea.getContext());
			bottomBarImage.setLayoutParams(bottomBarImageLayout);
			bottomBarImage.setScaleType(ScaleType.FIT_XY);
			bottomBarImage.setImageResource(R.drawable.bottom);
			mainArea.addView(bottomBarImage, bottomBarImageLayout);

			GridLayout bottomBarIconGrid = new GridLayout(mainArea.getContext());
			bottomBarIconGrid.setColumnCount(4);
			bottomBarIconGrid.setRowCount(1);

			GridLayout bottomBarMailIconGrid = new GridLayout(bottomBarIconGrid.getContext());
			bottomBarMailIconGrid.setColumnCount(3);
			bottomBarMailIconGrid.setRowCount(3);

			LayoutParams firstTopLeftSpaceLayout = new LayoutParams(row1, col1);
			//firstTopLeftSpaceLayout.width = (super.getWindowWidth() / 4) / 8;
			firstTopLeftSpaceLayout.height = (super.getWindowHeight() / 5) / 20;
			Space firstLeftSpace = new Space(bottomBarMailIconGrid.getContext());
			firstLeftSpace.setLayoutParams(firstTopLeftSpaceLayout);
			bottomBarMailIconGrid.addView(firstLeftSpace, firstTopLeftSpaceLayout);

			LayoutParams mailIconLayout = new LayoutParams(row2, col1); //mailIcon
			mailIconLayout.height = (super.getWindowHeight() / 5) - ((super.getWindowHeight() / 5) / 10);
			mailIconLayout.width = (super.getWindowWidth() / 4);
			mailIcon = new ImageView(bottomBarMailIconGrid.getContext());
			mailIcon.setScaleType(ScaleType.FIT_XY);
			mailIcon.setImageResource(R.drawable.mail);
			mailIcon.setLayoutParams(mailIconLayout);
			mailIcon.setOnClickListener(new OnClickListener() {
				public void onClick(View v)
				{
					MessageBox.newMsgBox("Mail Was Clicked", true);
				}
			});
			bottomBarMailIconGrid.addView(mailIcon, mailIconLayout);

			LayoutParams bottomBarMailIconGridLayout = new LayoutParams(row1, col1);
			bottomBarIconGrid.addView(bottomBarMailIconGrid, bottomBarMailIconGridLayout);

			GridLayout bottomBarflagIconGrid = new GridLayout(bottomBarIconGrid.getContext());
			bottomBarflagIconGrid.setColumnCount(3);
			bottomBarflagIconGrid.setRowCount(3);

			LayoutParams secondTopLeftSpaceLayout = new LayoutParams(row1, col1);
			//secondTopLeftSpaceLayout.width = (super.getWindowWidth() / 4) / 8;
			secondTopLeftSpaceLayout.height = (super.getWindowHeight() / 5) / 20;
			Space secondLeftSpace = new Space(bottomBarflagIconGrid.getContext());
			secondLeftSpace.setLayoutParams(secondTopLeftSpaceLayout);
			bottomBarflagIconGrid.addView(secondLeftSpace, secondTopLeftSpaceLayout);

			LayoutParams flagIconLayout = new LayoutParams(row2, col1); //flagIcon
			flagIconLayout.height = (super.getWindowHeight() / 5) - ((super.getWindowHeight() / 5) / 10);
			flagIconLayout.width = (super.getWindowWidth() / 4);
			flagIcon = new ImageView(bottomBarMailIconGrid.getContext());
			flagIcon.setScaleType(ScaleType.FIT_XY);
			flagIcon.setImageResource(R.drawable.flag);
			flagIcon.setLayoutParams(flagIconLayout);
			bottomBarflagIconGrid.addView(flagIcon, flagIconLayout);

			LayoutParams flagIconGridLayout = new LayoutParams(row1, col2);
			bottomBarIconGrid.addView(bottomBarflagIconGrid, flagIconGridLayout);

			GridLayout clickableFlagAreaGrid = new GridLayout(bottomBarIconGrid.getContext());
			clickableFlagAreaGrid.setColumnCount(2);
			clickableFlagAreaGrid.setRowCount(1);

			LayoutParams clickableFlagAreaLayout = new LayoutParams(row1, col1);
			clickableFlagAreaLayout.width = ((super.getWindowWidth() / 4) / 4) * 3;
			clickableFlagAreaLayout.height = super.getWindowHeight() / 5;
			ImageView clickableFlagArea = new ImageView(clickableFlagAreaGrid.getContext());
			clickableFlagArea.setLayoutParams(clickableFlagAreaLayout);
			clickableFlagArea.setOnClickListener(new OnClickListener() {
				public void onClick(View v)
				{
					if(TheMap.getMe().getGoogleMap().getMyLocation() != null)
					{
					    new PlaceCacheScreen();
					}
					else
					{
						MessageBox.newMsgBox("Cannot Get Your GPS Location", true);
					}
				}
			});
			clickableFlagAreaGrid.addView(clickableFlagArea, clickableFlagAreaLayout);

			LayoutParams clickableFlagAreaGridLayout = new LayoutParams(row1, col2);
			bottomBarIconGrid.addView(clickableFlagAreaGrid, clickableFlagAreaGridLayout);

			GridLayout bottomBarCastleIconGrid = new GridLayout(bottomBarIconGrid.getContext());
			bottomBarCastleIconGrid.setColumnCount(3);
			bottomBarCastleIconGrid.setRowCount(3);

			LayoutParams thirdTopLeftSpaceLayout = new LayoutParams(row1, col1);
			//thirdTopLeftSpaceLayout.width = (super.getWindowWidth() / 4) / 8;
			thirdTopLeftSpaceLayout.height = (super.getWindowHeight() / 5) / 20;
			Space thirdLeftSpace = new Space(bottomBarCastleIconGrid.getContext());
			thirdLeftSpace.setLayoutParams(thirdTopLeftSpaceLayout);
			bottomBarCastleIconGrid.addView(thirdLeftSpace, thirdTopLeftSpaceLayout);

			LayoutParams castleIconLayout = new LayoutParams(row2, col1); //castleIcon
			castleIconLayout.height = (super.getWindowHeight() / 5) - ((super.getWindowHeight() / 5) / 10);
			castleIconLayout.width = (super.getWindowWidth() / 4);
			castleIcon = new ImageView(bottomBarCastleIconGrid.getContext());
			castleIcon.setScaleType(ScaleType.FIT_XY);
			castleIcon.setImageResource(R.drawable.castle_grey);
			castleIcon.setLayoutParams(castleIconLayout);
			bottomBarCastleIconGrid.addView(castleIcon, castleIconLayout);

			LayoutParams bottomBarCastleIconGridLayout = new LayoutParams(row1, col3);
			bottomBarIconGrid.addView(bottomBarCastleIconGrid, bottomBarCastleIconGridLayout);

			GridLayout clickableCastleAreaGrid = new GridLayout(bottomBarIconGrid.getContext());
			clickableCastleAreaGrid.setColumnCount(2);
			clickableCastleAreaGrid.setRowCount(1);

			LayoutParams unclickableCastleAreaLayout = new LayoutParams(row1, col1);
			unclickableCastleAreaLayout.width = (super.getWindowWidth() / 4) / 4;
			Space unclickableCastleArea = new Space(clickableCastleAreaGrid.getContext());
			unclickableCastleArea.setLayoutParams(unclickableCastleAreaLayout);
			clickableCastleAreaGrid.addView(unclickableCastleArea, unclickableCastleAreaLayout);

			LayoutParams clickableCastleAreaLayout = new LayoutParams(row1, col2);
			clickableCastleAreaLayout.width = ((super.getWindowWidth() / 4) / 4) * 3;
			clickableCastleAreaLayout.height = (super.getWindowHeight() / 5);
			ImageView clickableCastleArea = new ImageView(clickableCastleAreaGrid.getContext());
			clickableCastleArea.setLayoutParams(clickableCastleAreaLayout);
			clickableCastleArea.setOnClickListener(new OnClickListener() {
				public void onClick(View v)
				{
					whenCastleClicked();	
				}
			});
			clickableCastleAreaGrid.addView(clickableCastleArea, clickableCastleAreaLayout);

			LayoutParams clickableCastleAreaLayoutLayout = new LayoutParams(row1, col3);
			bottomBarIconGrid.addView(clickableCastleAreaGrid, clickableCastleAreaLayoutLayout);

			GridLayout bottomBarRefreshIconGrid = new GridLayout(bottomBarIconGrid.getContext());
			bottomBarRefreshIconGrid.setColumnCount(3);
			bottomBarRefreshIconGrid.setRowCount(3);

			LayoutParams fourthTopLeftSpaceLayout = new LayoutParams(row1, col1);
			//thirdTopLeftSpaceLayout.width = (super.getWindowWidth() / 4) / 8;
			fourthTopLeftSpaceLayout.height = (super.getWindowHeight() / 5) / 20;
			Space fourthLeftSpace = new Space(bottomBarRefreshIconGrid.getContext());
			fourthLeftSpace.setLayoutParams(fourthTopLeftSpaceLayout);
			bottomBarRefreshIconGrid.addView(fourthLeftSpace, fourthTopLeftSpaceLayout);

			LayoutParams refreshIconLayout = new LayoutParams(row2, col1); //refreshIcon
			refreshIconLayout.height = (super.getWindowHeight() / 5) - ((super.getWindowHeight() / 5) / 10);
			refreshIconLayout.width = (super.getWindowWidth() / 4);
			refreshIcon = new ImageView(bottomBarRefreshIconGrid.getContext());
			refreshIcon.setScaleType(ScaleType.FIT_XY);
			refreshIcon.setImageResource(R.drawable.refresh);
			refreshIcon.setLayoutParams(refreshIconLayout);
			refreshIcon.setOnClickListener(new OnClickListener() {
				public void onClick(View v)
				{
					ServerRequests.refreshData();
				}
			});
			bottomBarRefreshIconGrid.addView(refreshIcon, refreshIconLayout);

			LayoutParams bottomBarRefreshIconGridLayout = new LayoutParams(row1, col4);
			bottomBarIconGrid.addView(bottomBarRefreshIconGrid, bottomBarRefreshIconGridLayout);

			LayoutParams bottomBarIconGridLayout = new LayoutParams(row3, col1);
			mainArea.addView(bottomBarIconGrid, bottomBarIconGridLayout);

			GridLayout centerMeIconGrid = new GridLayout(mainArea.getContext());
			centerMeIconGrid.setRowCount(1);
			centerMeIconGrid.setColumnCount(3);

			LayoutParams centerMeIconLeftSpaceLayout = new LayoutParams(row1, col1);
			centerMeIconLeftSpaceLayout.width = (super.getWindowWidth() / 2) - (super.getWindowWidth() / 28);
			Space centerMeIconLeftSpace = new Space(centerMeIconGrid.getContext());
			centerMeIconLeftSpace.setLayoutParams(centerMeIconLeftSpaceLayout);
			centerMeIconGrid.addView(centerMeIconLeftSpace, centerMeIconLeftSpaceLayout);

			GridLayout centerMeGrid = new GridLayout(centerMeIconGrid.getContext());
			centerMeGrid.setColumnCount(1);
			centerMeGrid.setRowCount(3);

			LayoutParams centerMeIconTopSpaceLayout = new LayoutParams(row1, col1);
			centerMeIconTopSpaceLayout.height = ((super.getWindowHeight() / 5) / 2) - (super.getWindowWidth() / 30);
			Space centerMeIconTopSpace = new Space(centerMeGrid.getContext());
			centerMeIconTopSpace.setLayoutParams(centerMeIconTopSpaceLayout);
			centerMeGrid.addView(centerMeIconTopSpace, centerMeIconTopSpaceLayout);

			LayoutParams centerMeIconLayout = new LayoutParams(row2, col1); // center me icon
			centerMeIconLayout.width = super.getWindowWidth() / 15;
			centerMeIconLayout.height = super.getWindowWidth() / 15;
			ImageView centerMeIcon = new ImageView(centerMeIconGrid.getContext());
			centerMeIcon.setLayoutParams(centerMeIconLayout);
			centerMeIcon.setScaleType(ScaleType.FIT_XY);
			centerMeIcon.setImageResource(R.drawable.center_me);
			centerMeGrid.addView(centerMeIcon, centerMeIconLayout);

			LayoutParams centerMeGridLayout = new LayoutParams(row1, col2);
			centerMeIconGrid.addView(centerMeGrid, centerMeGridLayout);

			LayoutParams centerMeIconGridLayout = new LayoutParams(row3, col1);
			mainArea.addView(centerMeIconGrid, centerMeIconGridLayout);

			GridLayout centerMeClickableAreaGrid = new GridLayout(mainArea.getContext());
			centerMeClickableAreaGrid.setRowCount(1);
			centerMeClickableAreaGrid.setColumnCount(3);

			LayoutParams centerMeClickableAreaSpaceLayout = new LayoutParams(row1, col1);
			centerMeClickableAreaSpaceLayout.width = (super.getWindowWidth() / 4) + (((super.getWindowWidth() / 4) / 4) * 3);
			Space centerMeClickableAreaSpace = new Space(centerMeClickableAreaGrid.getContext());
			centerMeClickableAreaSpace.setLayoutParams(centerMeClickableAreaSpaceLayout);
			centerMeClickableAreaGrid.addView(centerMeClickableAreaSpace, centerMeClickableAreaSpaceLayout);

			LayoutParams centerMeClickableAreaLayout = new LayoutParams(row1, col2); //overlay for the center me button to make it more easy
			centerMeClickableAreaLayout.width = (super.getWindowWidth() / 4) / 2;    //to click it
			centerMeClickableAreaLayout.height = super.getWindowHeight() / 5;
			ImageView centerMeClickableArea = new ImageView(centerMeClickableAreaGrid.getContext());
			centerMeClickableArea.setLayoutParams(centerMeClickableAreaLayout);
			centerMeClickableArea.setOnClickListener(new OnClickListener() {
				public void onClick(View v)
				{
					if(TheMap.getMe().getGoogleMap().getMyLocation() != null)
					{
					    TheMap.getMe().zoomToMyPositionAtMyZoom();
					}
					else
					{
						MessageBox.newMsgBox("Can't Get Your Location!", true);
					}
				}
			});
			centerMeClickableAreaGrid.addView(centerMeClickableArea, centerMeClickableAreaLayout);

			LayoutParams centerMeClickableAreaGridLayout = new LayoutParams(row3, col1);
			mainArea.addView(centerMeClickableAreaGrid, centerMeClickableAreaGridLayout);

			IconUpdater.newIconUpdater();
			MainScreen.setCastleClickable(false);
		}

		return mainArea;
	}

	////////
	//
	//whenCastleClicked
	//
	//called when the castle icon is clicked, finds the cache you are at and displays
	//the relevant screen
	//
	////////
	public void whenCastleClicked()
	{
		if(castleClickable) //if the castle is clickable then find the closest cache to the user
		{                   //and display the relevant user
			ServerRequests.refreshData(); //refresh to make sure the cache still exists, otherwise we are searching for a non existant cache
			ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Connecting To Server", false));
			Thread thread2 = new Thread(new Runnable() {
				public void run()
				{
					while(!ServerRequests.getRefreshDataComplete())
					{

					}
					if(!ServerRequests.getRefreshDataSuccess())
					{
						return;
					}
					Fortitude.getFortitude().runOnUiThread(new Runnable() {
						public void run()
						{
							MessageBox.newMsgBox("Loading Cache Details", false);
						}
					});
					try
					{
						Thread.sleep(500);
					}
					catch(Exception e)
					{
						//wait
					}
					Fortitude.getFortitude().runOnUiThread(new Runnable() {
						public void run()
						{
							int yy = -1;
							try
							{
								if(TheMap.getMe().getGoogleMap() == null)
								{
									throw new Exception("can't get map");
								}
								if(TheMap.getMe().getGoogleMap().getMyLocation() == null)
								{
									throw new Exception("can't get location");
								}
								double myLat = TheMap.getMe().getGoogleMap().getMyLocation().getLatitude();
								double myLon = TheMap.getMe().getGoogleMap().getMyLocation().getLongitude();
								double closestSoFar = 0.0005;
								int xx = 0;
								for(Marker m : TheMap.getMe().getMarkers())
								{
									double latlat = m.getPosition().latitude;
									double lonlon = m.getPosition().longitude;
									if(myLat > latlat)
									{
										latlat = myLat - latlat;
									}
									else
									{
										latlat = latlat - myLat;
									}

									if(myLon > lonlon)
									{
										lonlon = myLon - lonlon;
									}
									else
									{
										lonlon = lonlon - myLon;
									}

									if((latlat + lonlon) < closestSoFar)
									{
										closestSoFar = (latlat + lonlon);
										yy = xx;
									}
									xx++;
								}
								if(closestSoFar == 0.0005)
								{
									throw new Exception();
								}
							}
							catch(Exception e)
							{
								MessageBox.getMe().killMe();
								MessageBox.newMsgBox("Sorry, we can't work out what cache you are at! : " + e.toString(), true);
							}
							if(yy != -1)
							{
								if(ServerRequests.getNearbyCaches().get(yy).getOwnerId().equals(CurrentUser.getMe().getAccountId()))
								{
									if(ServerRequests.getTheMessageBox() != null)
									{
										ServerRequests.getTheMessageBox().killMe();
									}
									if(MessageBox.getMe() != null)
									{
										MessageBox.getMe().killMe();
									}
									GUI.makeAllTheGUIElementsBetter(Fortitude.getFortitude().getWindow().getDecorView()); //TEMP FIX, Messagebox wasn't properly dying here for some raison...	
									MainScreen.getMe().killMe();
									new VisitYourCacheScreen(ServerRequests.getNearbyCaches().get(yy));
								}
								else
								{
									ServerRequests.getUserInfo(ServerRequests.getNearbyCaches().get(TheMap.getMarkerPositionToBePassed()).getOwnerId(), false); 
									Thread thread = new Thread(new Runnable() {
										public void run()
										{
											while(!(ServerRequests.getGetUserInfoComplete()))
											{

											}
											if(ServerRequests.getGetUserInfoSuccess())
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
														GUI.makeAllTheGUIElementsBetter(Fortitude.getFortitude().getWindow().getDecorView()); //TEMP FIX, Messagebox wasn't properly dying here for some raison...			
														MainScreen.getMe().killMe();
														new VisitEnemyCacheScreen(new Cache(ServerRequests.getNearbyCaches().get(TheMap.getMarkerPositionToBePassed()).getCacheId(), ServerRequests.getNearbyCaches().get(TheMap.getMarkerPositionToBePassed()).getOwnerId(), ServerRequests.getNearbyCaches().get(TheMap.getMarkerPositionToBePassed()).getCacheName(), ServerRequests.getNearbyCaches().get(TheMap.getMarkerPositionToBePassed()).getLat(), ServerRequests.getNearbyCaches().get(TheMap.getMarkerPositionToBePassed()).getLon(), ServerRequests.getNearbyCaches().get(TheMap.getMarkerPositionToBePassed()).getGarrison()), ServerRequests.getStaticUserInfo().get(0));
													}
												});
											}
											else
											{
												Fortitude.getFortitude().runOnUiThread(new Runnable() {
													public void run()
													{
														if(ServerRequests.getTheMessageBox() != null)
														{
															ServerRequests.getTheMessageBox().killMe();
														}
													}
												});
											}
										}
									});
									thread.start();
								}
							}
						}
					});
				}
			});
			thread2.start();
		}
	}

	////////
	//
	//killMe
	//
	//removes the MainScreen from view
	//
	////////
	public void killMe()
	{
		me = null;
		TheMap.getMe().removeMeFromMyParent();
		this.removeAllViews();
	}

	////////
	//
	//getMe
	//
	//returns me.
	//
	////////
	public static MainScreen getMe()
	{
		return me;
	}

	public FortitudeButton getZoomInButton()
	{
		return zoomInButton;
	}

	public ImageView getMailIcon()
	{
		return mailIcon;
	}

	public ImageView getFlagIcon()
	{
		return flagIcon;
	}

	public ImageView getCastleIcon()
	{
		return castleIcon;
	}

	public ImageView getRefreshIcon()
	{
		return refreshIcon;
	}

	public static synchronized boolean getCastleClickable()
	{
		return castleClickable;
	}

	public static synchronized void setCastleClickable(boolean x)
	{
		castleClickable = x;
	}
}
