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

public abstract class AccountScreen extends Window 
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
	private Spec row11 = GridLayout.spec(10);
	private Spec row12 = GridLayout.spec(11);
	private Spec row13 = GridLayout.spec(12);
	private Spec row14 = GridLayout.spec(13);
	
	private Spec col1 = GridLayout.spec(0);
	private Spec col2 = GridLayout.spec(1);
	private Spec col3 = GridLayout.spec(2);
	private Spec col4 = GridLayout.spec(3);
	
	private Spec allcols = GridLayout.spec(0,2);
	
	private static AccountScreen me;
	
	////////
	//
	//Constructor
	//
	////////
    public AccountScreen()
    {
    	super(R.drawable.account);
    	me = this;
    	addContentToContentPane(createWindowPane());
    }
    
    private GridLayout createWindowPane()
    {
    	GridLayout mainArea = new GridLayout(Fortitude.getFortitude());
    	mainArea.setColumnCount(2);
    	mainArea.setRowCount(14);
    	
    	LayoutParams positionalSpaceLayout = new LayoutParams(row1, col1); //positional spacer
    	positionalSpaceLayout.width = (super.getWindowWidth() / 10) - (super.getWindowWidth() / 40);
    	positionalSpaceLayout.height = (super.getWindowHeight() / 5) + (super.getWindowHeight() / 40);
    	Space positionalSpace = new Space(mainArea.getContext());
    	positionalSpace.setLayoutParams(positionalSpaceLayout);
    	mainArea.addView(positionalSpace, positionalSpaceLayout);
    	
    	LayoutParams usernameTextViewLayout = new LayoutParams(row2, allcols); //USERNAME TEXTVIEW
    	usernameTextViewLayout.width = super.getWindowWidth();
    	usernameTextViewLayout.height = super.getWindowWidth() / 4;
    	TextView usernameTextView = new TextView(mainArea.getContext());
    	usernameTextView.setTextSize(26);
    	usernameTextView.setTextColor(Color.rgb(218, 218, 218));
    	usernameTextView.setGravity(Gravity.CENTER);
    	usernameTextView.setTypeface(Typeface.createFromAsset(Fortitude.getFortitude().getAssets(), "Fonts/Copperplate-Gothic-Light-Regular.ttf"));
    	usernameTextView.setText(CurrentUser.getMe().getUserName());
    	mainArea.addView(usernameTextView, usernameTextViewLayout);
    	
    	LayoutParams thirdRowSpacerLayout = new LayoutParams(row3, col1); //third row spacer
    	thirdRowSpacerLayout.height = (super.getWindowHeight() / 20) + (super.getWindowHeight() / 120);
    	Space thirdRowSpacer = new Space(mainArea.getContext());
    	thirdRowSpacer.setLayoutParams(thirdRowSpacerLayout);
    	mainArea.addView(thirdRowSpacer, thirdRowSpacerLayout);
    	
    	GridLayout totalCachesGrid = new GridLayout(mainArea.getContext());
    	totalCachesGrid.setRowCount(1);
    	totalCachesGrid.setColumnCount(2);
    	
        LayoutParams fourthRowLeftSpacerLayout = new LayoutParams(row1, col1); //fourth row left spacer
        fourthRowLeftSpacerLayout.width = super.getWindowWidth() / 2;
        Space fourthRowLeftSpacer = new Space(totalCachesGrid.getContext());
        fourthRowLeftSpacer.setLayoutParams(fourthRowLeftSpacerLayout);
        totalCachesGrid.addView(fourthRowLeftSpacer, fourthRowLeftSpacerLayout);
        
        LayoutParams totalCachesTextViewLayout = new LayoutParams(row1, col2); //totalcaches textview
        totalCachesTextViewLayout.width = (super.getWindowWidth() / 2) - ((super.getWindowWidth() / 10) - (super.getWindowWidth() / 40)) - (super.getWindowWidth() / 10);
        totalCachesTextViewLayout.height = super.getWindowHeight() / 20;
        TextView totalCachesTextView = new TextView(totalCachesGrid.getContext());
        totalCachesTextView.setTextSize(14);
        totalCachesTextView.setTextColor(Color.WHITE);
        totalCachesTextView.setText(CurrentUser.getMe().getNumberOfCaches() + " caches");
        totalCachesTextView.setGravity(Gravity.RIGHT);
        totalCachesTextView.setLayoutParams(totalCachesTextViewLayout);
        totalCachesGrid.addView(totalCachesTextView, totalCachesTextViewLayout);
        
    	LayoutParams totalCachesGridLayout = new LayoutParams(row4, col2);
    	mainArea.addView(totalCachesGrid, totalCachesGridLayout);
    	
    	LayoutParams fithRowSpacerLayout = new LayoutParams(row5, col1); //firth row spacer
    	fithRowSpacerLayout.height = super.getWindowHeight() / 90;
    	Space fithRowSpacer = new Space(mainArea.getContext());
    	fithRowSpacer.setLayoutParams(fithRowSpacerLayout);
    	mainArea.addView(fithRowSpacer, fithRowSpacerLayout);
    	
    	GridLayout availableSoldiersGrid = new GridLayout(mainArea.getContext());
    	availableSoldiersGrid.setRowCount(1);
    	availableSoldiersGrid.setColumnCount(2);
    	
    	LayoutParams fithRowLeftSpacerLayout = new LayoutParams(row1, col1); //left spacer available Soldiers textview
    	fithRowLeftSpacerLayout.width = super.getWindowWidth() / 2;
    	Space fithRowLeftSpacer = new Space(mainArea.getContext());
    	fithRowLeftSpacer.setLayoutParams(fithRowLeftSpacerLayout);
    	availableSoldiersGrid.addView(fithRowLeftSpacer, fithRowLeftSpacerLayout);
    	
    	LayoutParams availableSoldiersTextViewLayout = new LayoutParams(row1, col2); //availableSoldiers textView
    	availableSoldiersTextViewLayout.height = super.getWindowHeight() / 20;
    	availableSoldiersTextViewLayout.width = (super.getWindowWidth() / 2) - ((super.getWindowWidth() / 10) - (super.getWindowWidth() / 40)) - (super.getWindowWidth() / 10);
    	TextView availableSoldiersTextView = new TextView(availableSoldiersGrid.getContext());
    	availableSoldiersTextView.setTextSize(14);
    	availableSoldiersTextView.setTextColor(Color.WHITE);
    	availableSoldiersTextView.setText(CurrentUser.getMe().getBalance());
    	availableSoldiersTextView.setGravity(Gravity.RIGHT);
    	availableSoldiersTextView.setLayoutParams(availableSoldiersTextViewLayout);
    	availableSoldiersGrid.addView(availableSoldiersTextView, availableSoldiersTextViewLayout);
    	
    	LayoutParams availableSoldiersGridLayout = new LayoutParams(row6, col2);
    	mainArea.addView(availableSoldiersGrid, availableSoldiersGridLayout);
    	
    	LayoutParams seventhRowSpacerLayout = new LayoutParams(row7, col1); //seventh row space
    	seventhRowSpacerLayout.height = (super.getWindowHeight() / 90);
    	Space seventhRowSpacer = new Space(mainArea.getContext());
    	seventhRowSpacer.setLayoutParams(seventhRowSpacerLayout);
    	mainArea.addView(seventhRowSpacer, seventhRowSpacerLayout);
    	
    	GridLayout totalSoldiersGrid = new GridLayout(mainArea.getContext());
    	totalSoldiersGrid.setColumnCount(2);
    	totalSoldiersGrid.setRowCount(1);
    	
    	LayoutParams eigthRowLeftSpacerLayout = new LayoutParams(row1, col1); //totalSoldiers left spacer
    	eigthRowLeftSpacerLayout.width = super.getWindowWidth() / 2;
    	Space eigthRowLeftSpacer = new Space(totalSoldiersGrid.getContext());
    	eigthRowLeftSpacer.setLayoutParams(eigthRowLeftSpacerLayout);
    	totalSoldiersGrid.addView(eigthRowLeftSpacer, eigthRowLeftSpacerLayout);
    	
    	LayoutParams totalSoldiersTextViewLayout = new LayoutParams(row1, col2); //totalSoldiers textview
    	totalSoldiersTextViewLayout.width = (super.getWindowWidth() / 2) - ((super.getWindowWidth() / 10) - (super.getWindowWidth() / 40)) - (super.getWindowWidth() / 10);
    	totalSoldiersTextViewLayout.height = super.getWindowHeight() / 20;
    	TextView totalSoldiersTextView = new TextView(totalSoldiersGrid.getContext());
    	totalSoldiersTextView.setTextSize(14);
    	totalSoldiersTextView.setTextColor(Color.WHITE);
    	totalSoldiersTextView.setText(CurrentUser.getMe().getTotalBalance());
    	totalSoldiersTextView.setGravity(Gravity.RIGHT);
    	totalSoldiersTextView.setLayoutParams(totalSoldiersTextViewLayout);
    	totalSoldiersGrid.addView(totalSoldiersTextView, totalSoldiersTextViewLayout);
    	
    	LayoutParams totalSoldiersGridLayout = new LayoutParams(row8, col2);
    	mainArea.addView(totalSoldiersGrid, totalSoldiersGridLayout);
    	
    	LayoutParams ninthRowSpacerLayout = new LayoutParams(row9, col1); //ninthRowSpacer
    	ninthRowSpacerLayout.height = super.getWindowHeight() / 20;
    	Space ninthRowSpacer = new Space(mainArea.getContext());
    	ninthRowSpacer.setLayoutParams(ninthRowSpacerLayout);
    	mainArea.addView(ninthRowSpacer, ninthRowSpacerLayout);
    	
    	GridLayout firstButtonRow = new GridLayout(mainArea.getContext()); //first row of buttons
    	firstButtonRow.setColumnCount(3);
    	firstButtonRow.setRowCount(1);
    	
    	LayoutParams messagesButtonLayout = new LayoutParams(row1, col1); //Messages button
    	messagesButtonLayout.width = (super.getWindowWidth() / 2) - (super.getWindowWidth() / 10);
    	messagesButtonLayout.height = super.getWindowHeight() / 10;
    	FortitudeButton messagesButton = (new FortitudeButton(R.drawable.messages, R.drawable.messages_pressed) {
    		public void preClickActions()
    		{
    			
    		}
    		public void whenClicked()
    		{
    			
    		}
    	});
    	messagesButton.setLayoutParams(messagesButtonLayout);
    	firstButtonRow.addView(messagesButton, messagesButtonLayout);
    	
    	LayoutParams tenthRowMiddleSpacerLayout = new LayoutParams(row1, col2); //tenthRowMiddleSpace
    	tenthRowMiddleSpacerLayout.width = super.getWindowWidth() / 15;
    	Space tenthRowMiddleSpacer = new Space(firstButtonRow.getContext());
    	tenthRowMiddleSpacer.setLayoutParams(tenthRowMiddleSpacerLayout);
    	firstButtonRow.addView(tenthRowMiddleSpacer, tenthRowMiddleSpacerLayout);
    	
    	LayoutParams settingsButtonLayout = new LayoutParams(row1, col3); //Settings Button
    	settingsButtonLayout.width = (super.getWindowWidth() / 2) - (super.getWindowWidth() / 10);
    	settingsButtonLayout.height = super.getWindowHeight() / 10;
    	FortitudeButton settingsButton = (new FortitudeButton(R.drawable.settings, R.drawable.settings_pressed) {
    		public void preClickActions()
    		{
    			
    		}
    		public void whenClicked()
    		{
    			killMe();
    			new SettingsScreen();
    		}
    	});
    	settingsButton.setLayoutParams(settingsButtonLayout);
    	firstButtonRow.addView(settingsButton, settingsButtonLayout);
    			
    	LayoutParams firstButtonRowLayout = new LayoutParams(row10, col2);
    	mainArea.addView(firstButtonRow, firstButtonRowLayout);
    	
    	LayoutParams spaceBelowFirstRowLayout = new LayoutParams(row11, col1); //space below first row of buttons
    	spaceBelowFirstRowLayout.height = super.getWindowHeight() / 30;
    	Space spaceBelowFirstRow = new Space(mainArea.getContext());
    	spaceBelowFirstRow.setLayoutParams(spaceBelowFirstRowLayout);
    	mainArea.addView(spaceBelowFirstRow, spaceBelowFirstRowLayout);
    	
    	GridLayout secondButtonRow = new GridLayout(mainArea.getContext());
    	secondButtonRow.setColumnCount(3);
    	secondButtonRow.setRowCount(1);
    	
    	LayoutParams clearRouteButtonLayout = new LayoutParams(row1, col1); //Clear route button
    	clearRouteButtonLayout.width = (super.getWindowWidth() / 2) - (super.getWindowWidth() / 10);
    	clearRouteButtonLayout.height = super.getWindowHeight() / 10;
    	FortitudeButton clearRouteButton = (new FortitudeButton(R.drawable.clear_route, R.drawable.clear_route_pressed) {
    		public void preClickActions()
    		{
    			
    		}
    		public void whenClicked()
    		{
    			if(TheMap.getMe().removeRoute())
    			{
    				MessageBox.newMsgBox("Route Cleared!", true);
    			}
    			else
    			{
    				MessageBox.newMsgBox("There Is No Route To Clear!", true);
    			}
    		}
    	});
    	clearRouteButton.setLayoutParams(clearRouteButtonLayout);
    	secondButtonRow.addView(clearRouteButton, clearRouteButtonLayout);
    	
    	LayoutParams twelthRowMiddleSpacerLayout = new LayoutParams(row1, col2); //twelthRowMiddleSpace
    	twelthRowMiddleSpacerLayout.width = super.getWindowWidth() / 15;
    	Space twelthRowMiddleSpacer = new Space(secondButtonRow.getContext());
    	twelthRowMiddleSpacer.setLayoutParams(twelthRowMiddleSpacerLayout);
    	secondButtonRow.addView(twelthRowMiddleSpacer, twelthRowMiddleSpacerLayout);
    	
    	LayoutParams signOutButtonLayout = new LayoutParams(row1, col3); //Sign Out Button
    	signOutButtonLayout.width = (super.getWindowWidth() / 2) - (super.getWindowWidth() / 10);
    	signOutButtonLayout.height = super.getWindowHeight() / 10;
    	FortitudeButton signOutButton = (new FortitudeButton(R.drawable.sign_out, R.drawable.sign_out_pressed) {
    		public void preClickActions()
    		{
    			
    		}
    		public void whenClicked()
    		{
    			killMe();
    			GUI.killAll();
    			new MainLoginScreen();
    		}
    	});
    	signOutButton.setLayoutParams(signOutButtonLayout);
    	secondButtonRow.addView(signOutButton, signOutButtonLayout);
    	
    	LayoutParams secondButtonRowLayout = new LayoutParams(row12, col2);
    	mainArea.addView(secondButtonRow, secondButtonRowLayout);
    	
    	LayoutParams spaceBelowSecondRowLayout = new LayoutParams(row13, col1); //space below second row of buttons
    	spaceBelowSecondRowLayout.height = super.getWindowHeight() / 30;
    	Space spaceBelowSecondRow = new Space(mainArea.getContext());
    	spaceBelowSecondRow.setLayoutParams(spaceBelowSecondRowLayout);
    	mainArea.addView(spaceBelowSecondRow, spaceBelowSecondRowLayout);
    	
    	GridLayout thirdButtonRow = new GridLayout(mainArea.getContext());
    	thirdButtonRow.setColumnCount(2);
    	thirdButtonRow.setRowCount(1);
    	
    	LayoutParams spaceLeftOfButtonLayout = new LayoutParams(row1, col1);
    	spaceLeftOfButtonLayout.width = (super.getWindowWidth() / 4) - (super.getWindowWidth() / 40);
    	Space spaceLeftOfButton = new Space(thirdButtonRow.getContext());
    	spaceLeftOfButton.setLayoutParams(spaceLeftOfButtonLayout);
    	thirdButtonRow.addView(spaceLeftOfButton, spaceLeftOfButtonLayout);
    	
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
    			showNextScreen();
    		}
    	});
    	cancelButton.setLayoutParams(cancelButtonLayout);
    	thirdButtonRow.addView(cancelButton, cancelButtonLayout);
    	
    	LayoutParams thirdButtonRowLayout = new LayoutParams(row14, col2);
    	mainArea.addView(thirdButtonRow, thirdButtonRowLayout);
    	
    	return mainArea;
    }
    
    ////////
    //
    //showNextScreen
    //
    //This method is called when the user presses the cancel button.
    //
    ////////
    public abstract void showNextScreen();
    
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
    //getMe
    //
    //returns the last created instance of this class, may be null!
    //
    ////////
    public static AccountScreen getMe()
    {
    	return me;
    }
}
