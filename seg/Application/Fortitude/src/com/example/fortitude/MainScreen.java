package com.example.fortitude;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;

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
import android.view.LayoutInflater;
import com.google.android.gms.maps.CameraUpdateFactory;

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

	private Spec allcols = GridLayout.spec(0,1);

	private TextView userBalanceTextView = null;

	FortitudeButton zoomInButton;
	FortitudeButton zoomOutButton;

	private static MainScreen me = null;

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
		TheMap.getMe().zoomToMyPosition();
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
		new AccountScreen();
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

		LayoutParams gettingUserLocationTextViewLayout = new LayoutParams(row2, col1);
		gettingUserLocationTextViewLayout.width = super.getWindowWidth();
		gettingUserLocationTextViewLayout.height = super.getWindowHeight() - (super.getWindowHeight() / 5) - (super.getWindowHeight() / 20);
		TextView gettingUserLocationTextView = new TextView(mainArea.getContext());
		gettingUserLocationTextView.setText("Getting Your Location!");
		gettingUserLocationTextView.setTextColor(Color.WHITE);
		gettingUserLocationTextView.setGravity(Gravity.CENTER);
		gettingUserLocationTextView.setTextSize(18);
		gettingUserLocationTextView.setLayoutParams(gettingUserLocationTextViewLayout);
		mainArea.addView(gettingUserLocationTextView, gettingUserLocationTextViewLayout);
		
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

		}

		return mainArea;
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
}
