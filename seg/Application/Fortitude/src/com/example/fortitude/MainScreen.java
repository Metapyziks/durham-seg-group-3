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
import com.google.android.gms.maps.MapView;

public class MainScreen extends Window
{
	private String googleMapsKey = "AIzaSyBl6HtJfwSSQ3X44pjMVftPnLdFEvarHCQ";
	//intent = new Intent(CONTEXT, MYMAP.CLASS);
	//Fortitude.getFortitude().startActivity();
	
    private Spec row1 = GridLayout.spec(0);
    private Spec row2 = GridLayout.spec(1);
    private Spec row3 = GridLayout.spec(2);
    
    private Spec col1 = GridLayout.spec(0);
    private Spec col2 = GridLayout.spec(1);
    
    private Spec allcols = GridLayout.spec(0,1);
    
    private TextView userBalanceTextView = null;
    
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
    	MessageBox.newMsgBox("top bar has been clicked!", true);
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
	    
	    LayoutParams theMapLayout = new LayoutParams(row2, col1);
	    theMapLayout.width = super.getWindowWidth();
	    theMapLayout.height = super.getWindowHeight() / 2;
	    MapView theMap = new MapView(mainArea.getContext());
	    theMap.setLayoutParams(theMapLayout);
	    mainArea.addView(theMap, theMapLayout);
	    
	    LayoutParams activateAccountBarLayout = new LayoutParams(row3, col1);
	    activateAccountBarLayout.width = super.getWindowWidth();
	    activateAccountBarLayout.height = super.getWindowWidth() / 10;
	    ImageView activateAccountBar = new ImageView(mainArea.getContext());
	    activateAccountBar.setLayoutParams(activateAccountBarLayout);
	    activateAccountBar.setScaleType(ScaleType.FIT_XY);
	    activateAccountBar.setImageResource(R.drawable.cancel);
	    mainArea.addView(activateAccountBar, activateAccountBarLayout);
	    
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
}
