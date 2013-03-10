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

public class EnemyProfileScreen extends Window 
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
	
	private Spec col1 = GridLayout.spec(0);
	private Spec col2 = GridLayout.spec(1);
	private Spec col3 = GridLayout.spec(2);
	private Spec col4 = GridLayout.spec(3);
	
	private static User staticTheUser;
	private static Cache staticCacheComeFrom;
	private static EnemyProfileScreen me;
	
	public EnemyProfileScreen(User theUser, Cache cacheComeFrom)
	{
		super(R.drawable.user_profile);
		me = this;
		staticTheUser = theUser;
		staticCacheComeFrom = cacheComeFrom;
		addContentToContentPane(createWindowPane());
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
		
    	LayoutParams positionalSpaceLayout = new LayoutParams(row1, col1); //positional spacer
    	positionalSpaceLayout.height = (super.getWindowHeight() / 5) + (super.getWindowHeight() / 40);
    	Space positionalSpace = new Space(mainArea.getContext());
    	positionalSpace.setLayoutParams(positionalSpaceLayout);
    	mainArea.addView(positionalSpace, positionalSpaceLayout);
    	
    	LayoutParams usernameTextViewLayout = new LayoutParams(row2, col1); //USERNAME TEXTVIEW
    	usernameTextViewLayout.width = super.getWindowWidth();
    	usernameTextViewLayout.height = super.getWindowWidth() / 4;
    	TextView usernameTextView = new TextView(mainArea.getContext());
    	usernameTextView.setTextSize(26);
    	usernameTextView.setTextColor(Color.rgb(218, 218, 218));
    	usernameTextView.setGravity(Gravity.CENTER);
    	usernameTextView.setTypeface(Typeface.createFromAsset(Fortitude.getFortitude().getAssets(), "Fonts/Copperplate-Gothic-Light-Regular.ttf"));
    	usernameTextView.setText(EnemyProfileScreen.getStaticTheUser().getUserName());
    	mainArea.addView(usernameTextView, usernameTextViewLayout);
    	
    	LayoutParams thirdRowSpaceLayout = new LayoutParams(row3, col1);
    	thirdRowSpaceLayout.height = (super.getWindowHeight() / 10) - (super.getWindowHeight() / 100);
    	Space thirdRowSpace = new Space(mainArea.getContext());
    	thirdRowSpace.setLayoutParams(thirdRowSpaceLayout);
    	mainArea.addView(thirdRowSpace, thirdRowSpaceLayout);
    	
    	GridLayout totalCachesGrid = new GridLayout(mainArea.getContext());
    	totalCachesGrid.setColumnCount(3);
    	totalCachesGrid.setRowCount(1);
    	
    	LayoutParams totalCachesLeftSpaceLayout = new LayoutParams(row1, col1);
    	totalCachesLeftSpaceLayout.width = super.getWindowWidth() / 2;
    	Space totalCachesLeftSpace = new Space(totalCachesGrid.getContext());
    	totalCachesLeftSpace.setLayoutParams(totalCachesLeftSpaceLayout);
    	totalCachesGrid.addView(totalCachesLeftSpace, totalCachesLeftSpaceLayout);
    	
    	LayoutParams totalCachesTextViewLayout = new LayoutParams(row1, col2); //total caches textView
    	totalCachesTextViewLayout.width = (super.getWindowWidth() / 2) - (super.getWindowWidth() / 10);
    	totalCachesTextViewLayout.height = super.getWindowHeight() / 20;
    	TextView totalCachesTextView = new TextView(totalCachesGrid.getContext());
    	totalCachesTextView.setTextSize(14);
    	totalCachesTextView.setTextColor(Color.WHITE);
    	totalCachesTextView.setText(EnemyProfileScreen.getStaticTheUser().getCaches() + " caches");
    	totalCachesTextView.setGravity(Gravity.RIGHT);
    	totalCachesTextView.setLayoutParams(totalCachesTextViewLayout);
    	totalCachesGrid.addView(totalCachesTextView, totalCachesTextViewLayout);
		
        LayoutParams totalCachesGridLayout = new LayoutParams(row4, col1);
    	mainArea.addView(totalCachesGrid, totalCachesGridLayout);
    	
    	LayoutParams fithRowSpaceLayout = new LayoutParams(row5, col1);
    	fithRowSpaceLayout.height = super.getWindowHeight() / 12;
    	Space fithRowSpace = new Space(mainArea.getContext());
    	fithRowSpace.setLayoutParams(fithRowSpaceLayout);
    	mainArea.addView(fithRowSpace, fithRowSpaceLayout);
    	
    	GridLayout firstButtonRowGrid = new GridLayout(mainArea.getContext());
    	firstButtonRowGrid.setColumnCount(5);
    	firstButtonRowGrid.setRowCount(1);
    	
    	LayoutParams firstButtonRowLeftSpaceLayout = new LayoutParams(row1, col1);
    	firstButtonRowLeftSpaceLayout.width = (super.getWindowWidth() / 12);
    	Space firstButtonRowLeftSpace = new Space(firstButtonRowGrid.getContext());
    	firstButtonRowLeftSpace.setLayoutParams(firstButtonRowLeftSpaceLayout);
    	firstButtonRowGrid.addView(firstButtonRowLeftSpace, firstButtonRowLeftSpaceLayout);
    	
    	LayoutParams sendMessageButtonLayout = new LayoutParams(row1, col2); //send message button
    	sendMessageButtonLayout.height = super.getWindowHeight() / 10;
    	sendMessageButtonLayout.width = (super.getWindowWidth() / 2) - (super.getWindowWidth() / 10);
    	FortitudeButton sendMessageButton = (new FortitudeButton(R.drawable.send_message, R.drawable.send_message_pressed) {
    		public void preClickActions()
    		{
    			
    		}
    		public void whenClicked()
    		{
    			EnemyProfileScreen.getMe().killMe();
    			new SendMessageScreen(EnemyProfileScreen.getStaticTheUser().getUserName()) {
    				public void whenCancelled()
    				{
    					new EnemyProfileScreen(EnemyProfileScreen.getStaticTheUser(), EnemyProfileScreen.getStaticCacheComeFrom());
    				}
    			};
    		}
    	});
    	sendMessageButton.setLayoutParams(sendMessageButtonLayout);
    	firstButtonRowGrid.addView(sendMessageButton, sendMessageButtonLayout);
    	
    	LayoutParams firstRowMiddleSpaceLayout = new LayoutParams(row1, col3); //first row middle space
		firstRowMiddleSpaceLayout.width = super.getWindowWidth() / 15;
		Space firstRowMiddleSpace = new Space(firstButtonRowGrid.getContext());
		firstRowMiddleSpace.setLayoutParams(firstRowMiddleSpaceLayout);
		firstButtonRowGrid.addView(firstRowMiddleSpace, firstRowMiddleSpaceLayout);
		
    	LayoutParams blockUserButtonLayout = new LayoutParams(row1, col4); //block user button
    	blockUserButtonLayout.height = super.getWindowHeight() / 10;
    	blockUserButtonLayout.width = (super.getWindowWidth() / 2) - (super.getWindowWidth() / 10);
    	FortitudeButton blockUserButton = (new FortitudeButton(R.drawable.block_user, R.drawable.block_user_pressed) {
    		public void preClickActions()
    		{
    			
    		}
    		public void whenClicked()
    		{
    			
    		}
    	});
    	blockUserButton.setLayoutParams(blockUserButtonLayout);
    	firstButtonRowGrid.addView(blockUserButton, blockUserButtonLayout);
    	
    	LayoutParams firstButtonRowGridLayout = new LayoutParams(row6, col1);
    	mainArea.addView(firstButtonRowGrid, firstButtonRowGridLayout);
    	
    	LayoutParams seventhRowSpaceLayout = new LayoutParams(row7, col1); //seventh row space
    	seventhRowSpaceLayout.height = super.getWindowHeight() / 20;
    	Space seventhRowSpace = new Space(mainArea.getContext());
    	seventhRowSpace.setLayoutParams(seventhRowSpaceLayout);
    	mainArea.addView(seventhRowSpace, seventhRowSpaceLayout);
    	
    	GridLayout secondButtonRowGrid = new GridLayout(mainArea.getContext());
    	secondButtonRowGrid.setColumnCount(5);
    	secondButtonRowGrid.setRowCount(1);
    	
    	LayoutParams secondButtonRowLeftSpaceLayout = new LayoutParams(row1, col1);
    	secondButtonRowLeftSpaceLayout.width = (super.getWindowWidth() / 12);
    	Space secondButtonRowLeftSpace = new Space(secondButtonRowGrid.getContext());
    	secondButtonRowLeftSpace.setLayoutParams(secondButtonRowLeftSpaceLayout);
    	secondButtonRowGrid.addView(secondButtonRowLeftSpace, secondButtonRowLeftSpaceLayout);
    	
    	LayoutParams reportButtonLayout = new LayoutParams(row1, col2); //cancel button
    	reportButtonLayout.width = (super.getWindowWidth() / 2) - (super.getWindowWidth() / 10);
    	reportButtonLayout.height = super.getWindowHeight() / 10;
    	FortitudeButton reportButton = (new FortitudeButton(R.drawable.report, R.drawable.report_pressed) {
    		public void preClickActions()
    		{
    			
    		}
    		public void whenClicked()
    		{
    			EnemyProfileScreen.getMe().killMe();
    			new ReportScreens(0) {
    				public void whenCancelled()
    				{
    				    new EnemyProfileScreen(EnemyProfileScreen.getStaticTheUser(), EnemyProfileScreen.getStaticCacheComeFrom());
    				}
    			};
    		}
    	});
    	reportButton.setLayoutParams(reportButtonLayout);
    	secondButtonRowGrid.addView(reportButton, reportButtonLayout);
    	
    	LayoutParams secondRowMiddleSpaceLayout = new LayoutParams(row1, col3); //second row middle space
		secondRowMiddleSpaceLayout.width = super.getWindowWidth() / 15;
		Space secondRowMiddleSpace = new Space(secondButtonRowGrid.getContext());
		secondRowMiddleSpace.setLayoutParams(secondRowMiddleSpaceLayout);
		secondButtonRowGrid.addView(secondRowMiddleSpace, secondRowMiddleSpaceLayout);
    	
    	LayoutParams cancelButtonLayout = new LayoutParams(row1, col4); //cancel button
    	cancelButtonLayout.width = (super.getWindowWidth() / 2) - (super.getWindowWidth() / 10);
    	cancelButtonLayout.height = super.getWindowHeight() / 10;
    	FortitudeButton cancelButton = (new FortitudeButton(R.drawable.cancel, R.drawable.cancel_pressed) {
    		public void preClickActions()
    		{
    			
    		}
    		public void whenClicked()
    		{
    			EnemyProfileScreen.getMe().killMe();
    			new EnemyCacheScreen(EnemyProfileScreen.getStaticCacheComeFrom(), EnemyProfileScreen.getStaticTheUser());
    		}
    	});
    	cancelButton.setLayoutParams(cancelButtonLayout);
    	secondButtonRowGrid.addView(cancelButton, cancelButtonLayout);
    	
    	LayoutParams secondButtonRowGridLayout = new LayoutParams(row8, col1);
    	mainArea.addView(secondButtonRowGrid, secondButtonRowGridLayout);
    	
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
				new EnemyProfileScreen(EnemyProfileScreen.getStaticTheUser(), EnemyProfileScreen.getStaticCacheComeFrom());
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
	public static EnemyProfileScreen getMe()
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
    
    public static User getStaticTheUser()
    {
    	return staticTheUser;
    }
    
    public static Cache getStaticCacheComeFrom()
    {
    	return staticCacheComeFrom;
    }
}
