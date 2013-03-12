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

public class DepositUnitsScreen extends Window 
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

	private Spec col1 = GridLayout.spec(0);
	private Spec col2 = GridLayout.spec(1);
	private Spec col3 = GridLayout.spec(2);
	private Spec col4 = GridLayout.spec(3);

	private static DepositUnitsScreen me;
	private SeekBar unitsSeekBar;
	private TextView unitsToPlaceBox;
	private static int rememberId;

	public DepositUnitsScreen()
	{
		super(R.drawable.deposit_points_bg);
		me = this;
		super.addContentToContentPane(createWindowPane());
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

		LayoutParams firstRowSpaceLayout = new LayoutParams(row1, col1); //first row space
		firstRowSpaceLayout.height = (int) (super.getWindowHeight() * 0.17);
		Space firstRowSpace = new Space(mainArea.getContext());
		firstRowSpace.setLayoutParams(firstRowSpaceLayout);
		mainArea.addView(firstRowSpace, firstRowSpaceLayout);

		GridLayout cacheNameGrid = new GridLayout(mainArea.getContext());
		cacheNameGrid.setColumnCount(3);
		cacheNameGrid.setRowCount(1);

		LayoutParams cacheNameFirstLeftSpaceLayout = new LayoutParams(row1, col1);
		cacheNameFirstLeftSpaceLayout.width = (super.getWindowWidth() / 3) + (super.getWindowWidth() / 15);
		Space cacheNameFirstLeftSpace = new Space(cacheNameGrid.getContext());
		cacheNameFirstLeftSpace.setLayoutParams(cacheNameFirstLeftSpaceLayout);
		cacheNameGrid.addView(cacheNameFirstLeftSpace, cacheNameFirstLeftSpaceLayout);

		LayoutParams cacheNameTextViewLayout = new LayoutParams(row1, col2); //cache name!
		cacheNameTextViewLayout.width = super.getWindowWidth() / 2;
		cacheNameTextViewLayout.height = super.getWindowWidth() / 3;
		TextView cacheNameTextView = new TextView(cacheNameGrid.getContext());
		cacheNameTextView.setTextSize(28);
		cacheNameTextView.setTextColor(Color.rgb(218, 218, 218));
		cacheNameTextView.setGravity(Gravity.LEFT);
		cacheNameTextView.setTypeface(Typeface.createFromAsset(Fortitude.getFortitude().getAssets(), "Fonts/Copperplate-Gothic-Light-Regular.ttf"));
		cacheNameTextView.setText(VisitYourCacheScreen.getStaticTheCache().getCacheName());
		cacheNameTextView.setLayoutParams(cacheNameTextViewLayout);
		cacheNameGrid.addView(cacheNameTextView, cacheNameTextViewLayout);

		LayoutParams cacheNameGridLayout = new LayoutParams(row2, col1);
		mainArea.addView(cacheNameGrid, cacheNameGridLayout);

		LayoutParams usernameTextViewLayout = new LayoutParams(row3, col1); //USERNAME TEXTVIEW
		usernameTextViewLayout.width = (super.getWindowWidth());
		usernameTextViewLayout.height = super.getWindowWidth() / 4;
		TextView usernameTextView = new TextView(mainArea.getContext());
		usernameTextView.setTextSize(24);
		usernameTextView.setTextColor(Color.rgb(160, 160, 160));
		usernameTextView.setGravity(Gravity.CENTER);
		usernameTextView.setTypeface(Typeface.createFromAsset(Fortitude.getFortitude().getAssets(), "Fonts/Copperplate-Gothic-Light-Regular.ttf"));
		if(VisitYourCacheScreen.getStaticTheCache().isUnowned())
		{
			usernameTextView.setText("UNOWNED");
		}
		else
		{
			usernameTextView.setText(CurrentUser.getMe().getUserName());
		}
		mainArea.addView(usernameTextView, usernameTextViewLayout);

		LayoutParams fourthRowSpaceLayout = new LayoutParams(row4, col1); //fourth row space
		fourthRowSpaceLayout.height = 0;
		Space fourthRowSpace = new Space(mainArea.getContext());
		fourthRowSpace.setLayoutParams(fourthRowSpaceLayout);
		mainArea.addView(fourthRowSpace, fourthRowSpaceLayout);

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
		if(VisitYourCacheScreen.getStaticTheCache().isUnowned())
		{
			standingArmyTextView.setText("0 soldiers");
		}
		else
		{
		    standingArmyTextView.setText(VisitYourCacheScreen.getStaticTheCache().getGarrison() + " soldiers");
		}
		standingArmyTextView.setGravity(Gravity.RIGHT);
		standingArmyTextView.setLayoutParams(standingArmyTextViewLayout);
		standingArmyGrid.addView(standingArmyTextView, standingArmyTextViewLayout);

		LayoutParams standingArmyGridLayout = new LayoutParams(row5, col1);
		mainArea.addView(standingArmyGrid, standingArmyGridLayout);

		LayoutParams sixthRowSpadeLayout = new LayoutParams(row6, col1);
		sixthRowSpadeLayout.height = super.getWindowHeight() / 7;
		Space sixthRowSpade = new Space(mainArea.getContext());
		sixthRowSpade.setLayoutParams(sixthRowSpadeLayout);
		mainArea.addView(sixthRowSpade, sixthRowSpadeLayout);

		GridLayout SliderRowGrid = new GridLayout(mainArea.getContext());
		SliderRowGrid.setRowCount(1);
		SliderRowGrid.setColumnCount(5);

		LayoutParams seventhRowLeftSpaceLayout = new LayoutParams(row1, col1);
		seventhRowLeftSpaceLayout.width = super.getWindowWidth() / 10;
		Space seventhRowLeftSpace = new Space(SliderRowGrid.getContext());
		seventhRowLeftSpace.setLayoutParams(seventhRowLeftSpaceLayout);
		SliderRowGrid.addView(seventhRowLeftSpace, seventhRowLeftSpaceLayout);

		LayoutParams seekBarLayout = new LayoutParams(row1, col2);
		seekBarLayout.width = super.getWindowWidth() / 2;
		seekBarLayout.height = super.getWindowHeight() / 10;
		unitsSeekBar = new SeekBar(SliderRowGrid.getContext());
		unitsSeekBar.setMax(CurrentUser.getMe().getBalance() - 1);
		unitsSeekBar.setProgress(0);
		unitsSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
			{
				DepositUnitsScreen.getMe().setUnitsToPlaceBox(Integer.toString(progress + 1));
			}
			public void onStartTrackingTouch(SeekBar seekBar)
			{

			}
			public void onStopTrackingTouch(SeekBar seekBar)
			{

			}
		});
		unitsSeekBar.setLayoutParams(seekBarLayout);
		SliderRowGrid.addView(unitsSeekBar, seekBarLayout);

		LayoutParams seventhRowMiddleSpaceLayout = new LayoutParams(row1, col3);
		seventhRowMiddleSpaceLayout.width = super.getWindowWidth() / 20;
		Space seventhRowMiddleSpace = new Space(SliderRowGrid.getContext());
		seventhRowMiddleSpace.setLayoutParams(seventhRowMiddleSpaceLayout);
		SliderRowGrid.addView(seventhRowMiddleSpace, seventhRowMiddleSpaceLayout);

		LayoutParams unitsToPlaceTextViewLayout = new LayoutParams(row1, col4);
		unitsToPlaceTextViewLayout.width = (super.getWindowWidth() / 10) + (super.getWindowWidth() / 10);
		unitsToPlaceTextViewLayout.height = (super.getWindowWidth() / 10);
		unitsToPlaceBox = new TextView(SliderRowGrid.getContext());
		unitsToPlaceBox.setText(Integer.toString(unitsSeekBar.getProgress() + 1));
		unitsToPlaceBox.setLayoutParams(unitsToPlaceTextViewLayout);
		unitsToPlaceBox.setTextSize(18);
		unitsToPlaceBox.setTextColor(Color.BLACK);
		unitsToPlaceBox.setBackgroundColor(Color.WHITE);
		unitsToPlaceBox.setGravity(Gravity.CENTER);
		SliderRowGrid.addView(unitsToPlaceBox, unitsToPlaceTextViewLayout);

		LayoutParams SliderRowGridLayout = new LayoutParams(row7, col1);
		mainArea.addView(SliderRowGrid, SliderRowGridLayout);

		LayoutParams eighthRowSpaceLayout = new LayoutParams(row8, col1);
		eighthRowSpaceLayout.height = super.getWindowWidth() / 40;
		Space eighthRowSpace = new Space(mainArea.getContext());
		eighthRowSpace.setLayoutParams(eighthRowSpaceLayout);
		mainArea.addView(eighthRowSpace, eighthRowSpaceLayout);

		GridLayout buttonGrid = new GridLayout(mainArea.getContext());
		buttonGrid.setColumnCount(5);
		buttonGrid.setRowCount(1);

		LayoutParams ninthRowLeftSpaceLayout = new LayoutParams(row1, col1);
		ninthRowLeftSpaceLayout.width = (super.getWindowWidth() / 10) - (super.getWindowWidth() / 40);
		Space ninthRowLeftSpace = new Space(buttonGrid.getContext());
		ninthRowLeftSpace.setLayoutParams(ninthRowLeftSpaceLayout);
		buttonGrid.addView(ninthRowLeftSpace, ninthRowLeftSpaceLayout);    	

		LayoutParams depositUnitsButtonLayout = new LayoutParams(row1, col2); //plan route button
		depositUnitsButtonLayout.width = (super.getWindowWidth() / 2) - (super.getWindowWidth() / 10);
		depositUnitsButtonLayout.height = super.getWindowHeight() / 10;
		FortitudeButton depositUnitsButton = (new FortitudeButton(R.drawable.deposit, R.drawable.deposit_pressed) {
			public void preClickActions()
			{

			}
			public void whenClicked()
			{
				if(TheMap.getMe() == null)
				{
					MessageBox.newMsgBox("Could Not Get Your Location!", true);
					return;
				}
				if(TheMap.getMe().getGoogleMap() == null)
				{
					MessageBox.newMsgBox("Could Not Get Your Location!", true);	
					return;
				}
				if(TheMap.getMe().getGoogleMap().getMyLocation() == null)
				{
					MessageBox.newMsgBox("Could Not Get Your Location!", true);
					return;
				}
				double lat;
				double lon;
				try
				{
					lat = TheMap.getMe().getGoogleMap().getMyLocation().getLatitude();
					lon = TheMap.getMe().getGoogleMap().getMyLocation().getLongitude();
				}
				catch(Exception e)
				{
					MessageBox.newMsgBox("Could Not Get Your Location!", true);
					return;
				}
				ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Connecting To Server", false));
				ServerRequests.depositUnits(Double.toString(lat), Double.toString(lon), Integer.toString(VisitYourCacheScreen.getStaticTheCache().getCacheId()), DepositUnitsScreen.getMe().unitsToPlaceBox.getText().toString());
				Thread thread = new Thread(new Runnable() {
					public void run()
					{
						while(!ServerRequests.getStaticDepositComplete())
						{
							//WAIT!
							try
							{
								Thread.sleep(100);
							}
							catch(Exception e)
							{
								//oh well
							}
						}
						if(ServerRequests.getStaticDepositSuccess())
						{
							Fortitude.getFortitude().runOnUiThread(new Runnable() {
								public void run()
								{
									rememberId = VisitYourCacheScreen.getStaticTheCache().getCacheId();
									ServerRequests.refreshData(false);
									Thread thread = new Thread(new Runnable() {
										public void run()
										{
											while(!ServerRequests.getRefreshDataComplete())
											{
												//wait!
												try
												{
													Thread.sleep(100);
												}
												catch(Exception e)
												{
													//oh well
												}
											}
											if(!ServerRequests.getRefreshDataSuccess())
											{
												return;
											}
											Fortitude.getFortitude().runOnUiThread(new Runnable() {
												public void run()
												{
													boolean foundCache = false;
													for(Cache c : ServerRequests.getNearbyCaches())
													{
														if(c.getCacheId() == rememberId)
														{
															foundCache = true;
															VisitYourCacheScreen.setStaticTheCache(c);
														}
													}
													if(ServerRequests.getTheMessageBox() != null)
													{
														ServerRequests.getTheMessageBox().killMe();
													}
													if(MessageBox.getMe() != null)
													{
														MessageBox.getMe().killMe();
													}
													DepositUnitsScreen.getMe().killMe();
													if(foundCache)
													{
														new VisitYourCacheScreen(VisitYourCacheScreen.getStaticTheCache());
														MessageBox.newMsgBox("Successfully Deposited Units!", true);
													}
													else
													{
														new MainScreen();
														MessageBox.newMsgBox("The Cache No Longer Exists!", true);
													}
												}
											});
										}
									});
									thread.start();
								}
							});
						}
					}
				});
				thread.start();
			}
		});
		depositUnitsButton.setLayoutParams(depositUnitsButtonLayout);
		buttonGrid.addView(depositUnitsButton, depositUnitsButtonLayout);

		LayoutParams ninthRowMiddleSpacerLayout = new LayoutParams(row1, col3); //ninth row middle spacer
		ninthRowMiddleSpacerLayout.width = super.getWindowWidth() / 15;
		Space ninthRowMiddleSpacer = new Space(buttonGrid.getContext());
		ninthRowMiddleSpacer.setLayoutParams(ninthRowMiddleSpacerLayout);
		buttonGrid.addView(ninthRowMiddleSpacer, ninthRowMiddleSpacerLayout);

		LayoutParams cancelLayout = new LayoutParams(row1, col4);
		cancelLayout.width = (super.getWindowWidth() / 2) - (super.getWindowWidth() / 10);
		cancelLayout.height = super.getWindowHeight() / 10;
		FortitudeButton cancel = (new FortitudeButton(R.drawable.cancel, R.drawable.cancel_pressed) {
			public void preClickActions()
			{

			}
			public void whenClicked()
			{
				DepositUnitsScreen.getMe().killMe();
				new VisitYourCacheScreen(VisitYourCacheScreen.getStaticTheCache());
			}
		});
		cancel.setLayoutParams(cancelLayout);
		buttonGrid.addView(cancel, cancelLayout);

		LayoutParams buttonGridLayout = new LayoutParams(row9, col1);
		mainArea.addView(buttonGrid, buttonGridLayout);

		return mainArea;
	}

	private void topBarClicked()
	{
		killMe();
		new AccountScreen() 
		{
			public void showNextScreen() 
			{
				new DepositUnitsScreen();
			}
		};
	}

	public static DepositUnitsScreen getMe()
	{
		return me;
	}

	public void killMe()
	{
		me = null;
		this.removeAllViews();
	}

	public void setUnitsToPlaceBox(String x)
	{
		unitsToPlaceBox.setText(x);
	}
}
