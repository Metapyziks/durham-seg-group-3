package com.example.fortitude;


import android.widget.GridLayout;
import android.widget.GridLayout.LayoutParams;
import android.widget.GridLayout.Spec;
import android.widget.Space;
import android.widget.TextView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.view.Gravity;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.ImageView.ScaleType;
import android.widget.ImageView;
import android.graphics.Color;

public class PlaceCacheScreen extends Window 
{
	private Spec row1 = GridLayout.spec(0);
	private Spec row2 = GridLayout.spec(1);
	private Spec row3 = GridLayout.spec(2);
	private Spec row4 = GridLayout.spec(3);

	private Spec col1 = GridLayout.spec(0);
	private Spec col2 = GridLayout.spec(1);
	private Spec col3 = GridLayout.spec(2);
	private Spec col4 = GridLayout.spec(3);

	private SeekBar unitsSeekBar;
	private TextView unitsToPlaceBox;

	private static PlaceCacheScreen me;

	public PlaceCacheScreen()
	{
		super(R.drawable.place_cache_bg);
		me = this;
		super.addContentToContentPane(createWindowPane());
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

		LayoutParams firstRowSpaceLayout = new LayoutParams(row1, col1);
		firstRowSpaceLayout.height = (super.getWindowHeight() / 2) + (super.getWindowWidth() / 4);
		Space firstRowSpace = new Space(mainArea.getContext());
		firstRowSpace.setLayoutParams(firstRowSpaceLayout);
		mainArea.addView(firstRowSpace, firstRowSpaceLayout);

		GridLayout secondRowGrid = new GridLayout(mainArea.getContext());
		secondRowGrid.setRowCount(1);
		secondRowGrid.setColumnCount(5);

		LayoutParams secondRowLeftSpaceLayout = new LayoutParams(row1, col1);
		secondRowLeftSpaceLayout.width = super.getWindowWidth() / 10;
		Space secondRowLeftSpace = new Space(secondRowGrid.getContext());
		secondRowLeftSpace.setLayoutParams(secondRowLeftSpaceLayout);
		secondRowGrid.addView(secondRowLeftSpace, secondRowLeftSpaceLayout);

		LayoutParams seekBarLayout = new LayoutParams(row1, col2);
		seekBarLayout.width = super.getWindowWidth() / 2;
		seekBarLayout.height = super.getWindowHeight() / 10;
		unitsSeekBar = new SeekBar(secondRowGrid.getContext());
		unitsSeekBar.setMax(CurrentUser.getMe().getIntBalance() - 5);
		unitsSeekBar.setProgress(0);
		unitsSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
			{
				PlaceCacheScreen.getMe().setUnitsToPlaceBox(Integer.toString(progress + 5));
			}
			public void onStartTrackingTouch(SeekBar seekBar)
			{

			}
			public void onStopTrackingTouch(SeekBar seekBar)
			{

			}
		});
		unitsSeekBar.setLayoutParams(seekBarLayout);
		secondRowGrid.addView(unitsSeekBar, seekBarLayout);

		LayoutParams secondRowMiddleSpaceLayout = new LayoutParams(row1, col3);
		secondRowMiddleSpaceLayout.width = super.getWindowWidth() / 20;
		Space secondRowMiddleSpace = new Space(secondRowGrid.getContext());
		secondRowMiddleSpace.setLayoutParams(secondRowMiddleSpaceLayout);
		secondRowGrid.addView(secondRowMiddleSpace, secondRowMiddleSpaceLayout);

		LayoutParams unitsToPlaceTextViewLayout = new LayoutParams(row1, col4);
		unitsToPlaceTextViewLayout.width = (super.getWindowWidth() / 10) + (super.getWindowWidth() / 10);
		unitsToPlaceTextViewLayout.height = (super.getWindowWidth() / 10);
		unitsToPlaceBox = new TextView(secondRowGrid.getContext());
		unitsToPlaceBox.setText(Integer.toString(unitsSeekBar.getProgress() + 5));
		unitsToPlaceBox.setLayoutParams(unitsToPlaceTextViewLayout);
		unitsToPlaceBox.setTextSize(18);
		unitsToPlaceBox.setTextColor(Color.BLACK);
		unitsToPlaceBox.setBackgroundColor(Color.WHITE);
		unitsToPlaceBox.setGravity(Gravity.CENTER);
		secondRowGrid.addView(unitsToPlaceBox, unitsToPlaceTextViewLayout);

		LayoutParams secondRowGridLayout = new LayoutParams(row2, col1);
		mainArea.addView(secondRowGrid, secondRowGridLayout);

		LayoutParams thirdRowSpaceLayout = new LayoutParams(row3, col1);
		thirdRowSpaceLayout.height = super.getWindowHeight() / 10;
		Space thirdRowSpace = new Space(mainArea.getContext());
		thirdRowSpace.setLayoutParams(thirdRowSpaceLayout);
		mainArea.addView(thirdRowSpace, thirdRowSpaceLayout);

		GridLayout fourthRowButtonGrid = new GridLayout(mainArea.getContext());
		fourthRowButtonGrid.setColumnCount(5);
		fourthRowButtonGrid.setRowCount(1);

		LayoutParams fourthRowLeftSpaceLayout = new LayoutParams(row1, col1);
		fourthRowLeftSpaceLayout.width = (super.getWindowWidth() / 10) - (super.getWindowWidth() / 40);
		Space fourthRowLeftSpace = new Space(fourthRowButtonGrid.getContext());
		fourthRowLeftSpace.setLayoutParams(fourthRowLeftSpaceLayout);
		fourthRowButtonGrid.addView(fourthRowLeftSpace, fourthRowLeftSpaceLayout);

		LayoutParams placeCacheButtonLayout = new LayoutParams(row1, col2);
		placeCacheButtonLayout.width = (super.getWindowWidth() / 2) - (super.getWindowWidth() / 10);
		placeCacheButtonLayout.height = super.getWindowHeight() / 10;
		FortitudeButton placeCacheButton = (new FortitudeButton(R.drawable.place_cache, R.drawable.place_cache_pressed) {
			public void preClickActions()
			{

			}
			public void whenClicked()
			{
				if(TheMap.getMe().getGoogleMap().getMyLocation() == null)
				{
					MessageBox.newMsgBox("Can't Get Your GPS Location!", true);
					return;
				}
				ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Connecting To Server", false));
				ServerRequests.placeCache(Double.toString(TheMap.getMe().getGoogleMap().getMyLocation().getLatitude()), Double.toString(TheMap.getMe().getGoogleMap().getMyLocation().getLongitude()), PlaceCacheScreen.getMe().getUnitsToPlace());
				Thread thread = new Thread(new Runnable() {
					public void run()
					{
						while(!ServerRequests.getPlaceCacheComplete())
						{
							//wait
						}
						if(ServerRequests.getPlaceCacheSuccess())
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
									ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Connecting To Server", false));
									ServerRequests.refreshData();
									Thread thread = new Thread(new Runnable() {
										public void run()
										{
											while(!ServerRequests.getRefreshDataComplete())
											{
												//wait
											}
											if(ServerRequests.getRefreshDataSuccess())
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
														PlaceCacheScreen.getMe().killMe();
														new MainScreen();
														MessageBox.newMsgBox("Successfully Placed Cache!", true);
													}
												});
											}
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
		placeCacheButton.setLayoutParams(placeCacheButtonLayout);
		fourthRowButtonGrid.addView(placeCacheButton, placeCacheButtonLayout);

		LayoutParams fourthRowMiddleSpaceLayout = new LayoutParams(row1, col3);
		fourthRowMiddleSpaceLayout.width = super.getWindowWidth() / 15;
		Space fourthRowMiddleSpace = new Space(fourthRowButtonGrid.getContext());
		fourthRowMiddleSpace.setLayoutParams(fourthRowMiddleSpaceLayout);
		fourthRowButtonGrid.addView(fourthRowMiddleSpace, fourthRowMiddleSpaceLayout);

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
		fourthRowButtonGrid.addView(cancelButton, cancelButtonLayout);

		LayoutParams fourthRowButtonGridLayout = new LayoutParams(row4, col1);
		mainArea.addView(fourthRowButtonGrid, fourthRowButtonGridLayout);

		return mainArea;
	}

	public static PlaceCacheScreen getMe()
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

	public SeekBar getUnitsSeekBar()
	{
		return unitsSeekBar;
	}

	public void setUnitsToPlaceBox(String x)
	{
		unitsToPlaceBox.setText(x);
	}

	public String getUnitsToPlace()
	{
		return unitsToPlaceBox.getText().toString();
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
				if(TheMap.getMe().getGoogleMap().getMyLocation() != null)
				{
				    new PlaceCacheScreen();
				}
				else
				{
					new MainScreen();
					MessageBox.newMsgBox("Cannot Get Your GPS Location!", true);
				}
			}
		};
	}

}
