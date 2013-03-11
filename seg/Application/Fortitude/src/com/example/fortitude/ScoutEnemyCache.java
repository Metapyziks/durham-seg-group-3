package com.example.fortitude;

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
import json.*;

public class ScoutEnemyCache extends Window 
{
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
	
	private static Cache staticTheCache;
	private static ScoutEnemyCache me;
	
	private SeekBar unitsSeekBar;
	private TextView unitsToPlaceBox;
	
	public ScoutEnemyCache(Cache theCache)
	{
		super(R.drawable.scout_cache_bg);
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
		
		LayoutParams firstRowSpaceLayout = new LayoutParams(row1, col1); //first row space
		firstRowSpaceLayout.height = (super.getWindowHeight() / 3) - (super.getWindowHeight() / 20);
		Space firstRowSpace = new Space(mainArea.getContext());
		firstRowSpace.setLayoutParams(firstRowSpaceLayout);
		mainArea.addView(firstRowSpace, firstRowSpaceLayout);
		
		GridLayout cacheNameGrid = new GridLayout(mainArea.getContext());
		cacheNameGrid.setColumnCount(3);
		cacheNameGrid.setRowCount(1);
		
		LayoutParams cacheNameFirstLeftSpaceLayout = new LayoutParams(row1, col1);
		cacheNameFirstLeftSpaceLayout.width = (super.getWindowWidth() / 3) + (super.getWindowWidth() / 12);
		Space cacheNameFirstLeftSpace = new Space(cacheNameGrid.getContext());
		cacheNameFirstLeftSpace.setLayoutParams(cacheNameFirstLeftSpaceLayout);
		cacheNameGrid.addView(cacheNameFirstLeftSpace, cacheNameFirstLeftSpaceLayout);
		
		LayoutParams cacheNameTextViewLayout = new LayoutParams(row1, col2);
		cacheNameTextViewLayout.width = super.getWindowWidth() / 2;
		cacheNameTextViewLayout.height = super.getWindowHeight() / 3;
		TextView cacheNameTextView = new TextView(cacheNameGrid.getContext());
    	cacheNameTextView.setTextSize(28);
    	cacheNameTextView.setTextColor(Color.rgb(218, 218, 218));
    	cacheNameTextView.setGravity(Gravity.LEFT);
    	cacheNameTextView.setTypeface(Typeface.createFromAsset(Fortitude.getFortitude().getAssets(), "Fonts/Copperplate-Gothic-Light-Regular.ttf"));
    	cacheNameTextView.setText(ScoutEnemyCache.getStaticTheCache().getCacheName());
    	cacheNameTextView.setLayoutParams(cacheNameTextViewLayout);
    	cacheNameGrid.addView(cacheNameTextView, cacheNameTextViewLayout);
		
		LayoutParams cacheNameGridLayout = new LayoutParams(row2, col1);
		mainArea.addView(cacheNameGrid, cacheNameGridLayout);
		
		LayoutParams thirdRowSpaceLayout = new LayoutParams(row3, col1);
		thirdRowSpaceLayout.height = super.getWindowHeight() / 15;
		Space thirdRowSpace = new Space(mainArea.getContext());
		thirdRowSpace.setLayoutParams(thirdRowSpaceLayout);
		mainArea.addView(thirdRowSpace, thirdRowSpaceLayout);
		
		GridLayout fourthRowGrid = new GridLayout(mainArea.getContext());
		fourthRowGrid.setRowCount(1);
		fourthRowGrid.setColumnCount(5);

		LayoutParams fourthRowLeftSpaceLayout = new LayoutParams(row1, col1);
		fourthRowLeftSpaceLayout.width = super.getWindowWidth() / 10;
		Space fourthRowLeftSpace = new Space(fourthRowGrid.getContext());
		fourthRowLeftSpace.setLayoutParams(fourthRowLeftSpaceLayout);
		fourthRowGrid.addView(fourthRowLeftSpace, fourthRowLeftSpaceLayout);

		LayoutParams seekBarLayout = new LayoutParams(row1, col2);
		seekBarLayout.width = super.getWindowWidth() / 2;
		seekBarLayout.height = super.getWindowHeight() / 10;
		unitsSeekBar = new SeekBar(fourthRowGrid.getContext());
		unitsSeekBar.setMax(CurrentUser.getMe().getBalance() - 1);
		unitsSeekBar.setProgress(0);
		unitsSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
			{
				ScoutEnemyCache.getMe().setUnitsToPlaceBox(Integer.toString(progress + 1));
			}
			public void onStartTrackingTouch(SeekBar seekBar)
			{

			}
			public void onStopTrackingTouch(SeekBar seekBar)
			{

			}
		});
		unitsSeekBar.setLayoutParams(seekBarLayout);
		fourthRowGrid.addView(unitsSeekBar, seekBarLayout);

		LayoutParams fourthRowMiddleSpaceLayout = new LayoutParams(row1, col3);
		fourthRowMiddleSpaceLayout.width = super.getWindowWidth() / 20;
		Space fourthRowMiddleSpace = new Space(fourthRowGrid.getContext());
		fourthRowMiddleSpace.setLayoutParams(fourthRowMiddleSpaceLayout);
		fourthRowGrid.addView(fourthRowMiddleSpace, fourthRowMiddleSpaceLayout);

		LayoutParams unitsToPlaceTextViewLayout = new LayoutParams(row1, col4);
		unitsToPlaceTextViewLayout.width = (super.getWindowWidth() / 10) + (super.getWindowWidth() / 10);
		unitsToPlaceTextViewLayout.height = (super.getWindowWidth() / 10);
		unitsToPlaceBox = new TextView(fourthRowGrid.getContext());
		unitsToPlaceBox.setText(Integer.toString(unitsSeekBar.getProgress() + 1));
		unitsToPlaceBox.setLayoutParams(unitsToPlaceTextViewLayout);
		unitsToPlaceBox.setTextSize(18);
		unitsToPlaceBox.setTextColor(Color.BLACK);
		unitsToPlaceBox.setBackgroundColor(Color.WHITE);
		unitsToPlaceBox.setGravity(Gravity.CENTER);
		fourthRowGrid.addView(unitsToPlaceBox, unitsToPlaceTextViewLayout);

		LayoutParams fourthRowGridLayout = new LayoutParams(row4, col1);
		mainArea.addView(fourthRowGrid, fourthRowGridLayout);
		
		LayoutParams fithRowSpaceLayout = new LayoutParams(row5, col1);
		fithRowSpaceLayout.height = super.getWindowHeight() / 10;
		Space fithRowSpace = new Space(mainArea.getContext());
		fithRowSpace.setLayoutParams(fithRowSpaceLayout);
		mainArea.addView(fithRowSpace, fithRowSpaceLayout);

		GridLayout sixthRowButtonGrid = new GridLayout(mainArea.getContext());
		sixthRowButtonGrid.setColumnCount(5);
		sixthRowButtonGrid.setRowCount(1);

		LayoutParams sixthRowLeftSpaceLayout = new LayoutParams(row1, col1);
		sixthRowLeftSpaceLayout.width = (super.getWindowWidth() / 10) - (super.getWindowWidth() / 40);
		Space sixthRowLeftSpace = new Space(sixthRowButtonGrid.getContext());
		sixthRowLeftSpace.setLayoutParams(sixthRowLeftSpaceLayout);
		sixthRowButtonGrid.addView(sixthRowLeftSpace, sixthRowLeftSpaceLayout);

		LayoutParams scoutCacheButtonLayout = new LayoutParams(row1, col2);
		scoutCacheButtonLayout.width = (super.getWindowWidth() / 2) - (super.getWindowWidth() / 10);
		scoutCacheButtonLayout.height = super.getWindowHeight() / 10;
		FortitudeButton scoutCacheButton = (new FortitudeButton(R.drawable.scout_cache, R.drawable.scout_cache_pressed) {
			public void preClickActions()
			{

			}
			public void whenClicked()
			{
				if(TheMap.getMe().getGoogleMap() == null)
				{
				    MessageBox.newMsgBox("can't get map", true);
				    return;
				}
				if(TheMap.getMe().getGoogleMap().getMyLocation() == null)
				{
					MessageBox.newMsgBox("can't get location", true);
					return;
				}
				ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Connecting To Server", false));
				ServerRequests.scoutCache(Double.toString(TheMap.getMe().getGoogleMap().getMyLocation().getLatitude()), Double.toString(TheMap.getMe().getGoogleMap().getMyLocation().getLongitude()), ScoutEnemyCache.getStaticTheCache().getCacheId(), ScoutEnemyCache.getMe().getUnitsToPlaceBox());
				Thread thread = new Thread(new Runnable() {
					public void run()
					{
						while(!ServerRequests.getScoutCacheComplete())
						{
							//wait
						}
						if(!ServerRequests.getScoutCacheSuccess())
						{
							return;
						}
						Fortitude.getFortitude().runOnUiThread(new Runnable() {
							public void run()
							{
								ServerRequests.refreshData(false);
								Thread thread = new Thread(new Runnable() {
									public void run()
									{
										while(!ServerRequests.getRefreshDataComplete())
										{
											//wait
										}
										if(!ServerRequests.getRefreshDataSuccess())
										{
											return;
										}
										JSONObject response = ServerRequests.getScoutCacheResponse();
										String survivors = Integer.toString((int)(Double.parseDouble(response.get("survivors").asString())));
										if(survivors.equals("0"))
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
													ScoutEnemyCache.getMe().killMe();
													new ScoutFailureScreen(ScoutEnemyCache.getStaticTheCache());
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
													if(MessageBox.getMe() != null)
													{
														MessageBox.getMe().killMe();
													}
													ScoutEnemyCache.getMe().killMe();
													new ScoutSuccessScreen(ScoutEnemyCache.getStaticTheCache(), ServerRequests.getScoutCacheResponse());
												}
											});
										}

									}
								});
								thread.start();
							}
						});
					}
				});
				thread.start();
			}
		});
		scoutCacheButton.setLayoutParams(scoutCacheButtonLayout);
		sixthRowButtonGrid.addView(scoutCacheButton, scoutCacheButtonLayout);

		LayoutParams sixthRowMiddleSpaceLayout = new LayoutParams(row1, col3);
		sixthRowMiddleSpaceLayout.width = super.getWindowWidth() / 15;
		Space sixthRowMiddleSpace = new Space(sixthRowButtonGrid.getContext());
		sixthRowMiddleSpace.setLayoutParams(sixthRowMiddleSpaceLayout);
		sixthRowButtonGrid.addView(sixthRowMiddleSpace, sixthRowMiddleSpaceLayout);

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
		sixthRowButtonGrid.addView(cancelButton, cancelButtonLayout);

		LayoutParams sixthRowButtonGridLayout = new LayoutParams(row6, col1);
		mainArea.addView(sixthRowButtonGrid, sixthRowButtonGridLayout);
		
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
				new ScoutEnemyCache(ScoutEnemyCache.getStaticTheCache());
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
	public static ScoutEnemyCache getMe()
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
	
	public SeekBar getUnitsSeekBar()
	{
		return unitsSeekBar;
	}
	
	public void setUnitsToPlaceBox(String x)
	{
		unitsToPlaceBox.setText(x);
	}
	
	public String getUnitsToPlaceBox()
	{
		return unitsToPlaceBox.getText().toString();
	}
}
