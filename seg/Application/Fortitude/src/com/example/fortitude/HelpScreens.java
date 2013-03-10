package com.example.fortitude;

import android.widget.GridLayout;
import android.widget.GridLayout.LayoutParams;
import android.widget.GridLayout.Spec;
import android.widget.Space;
import android.widget.TextView;
import android.view.Gravity;

public class HelpScreens extends Window
{
	private Spec row1 = GridLayout.spec(0);
	private Spec row2 = GridLayout.spec(1);

	private Spec col1 = GridLayout.spec(0);
	private Spec col2 = GridLayout.spec(1);
	private Spec col3 = GridLayout.spec(2);
	private Spec col4 = GridLayout.spec(3);
	private Spec col5 = GridLayout.spec(4);
	private Spec col6 = GridLayout.spec(5);

	private static HelpScreens me;
	private static int screenIdentity = 0;
	private static final int maximumScreen = 1;
	private static int screenToReturnTo;

	public HelpScreens(int whatScreen, int screenToReturn)
	{
		super(R.drawable.help_fortitude_bg);
		me = this;
		screenIdentity = whatScreen;
		screenToReturnTo = screenToReturn;
		if(screenIdentity == 1)
		{
			super.getBackgroundImage().setImageResource(R.drawable.help_buttons_bg);
		}

		super.addContentToContentPane(addContentToPane());
	}

	private GridLayout addContentToPane()
	{
		GridLayout mainArea = new GridLayout(Fortitude.getFortitude());

		mainArea.setColumnCount(7);
		mainArea.setRowCount(3);

		LayoutParams firstRowSpaceLayout = new LayoutParams(row1,col1);
		firstRowSpaceLayout.height = (int)(super.getWindowHeight()*0.9);
		firstRowSpaceLayout.width = (int)(super.getWindowWidth()*0.075);
		Space firstRowSpace = new Space(mainArea.getContext());
		firstRowSpace.setLayoutParams(firstRowSpaceLayout);
		mainArea.addView(firstRowSpace,firstRowSpaceLayout);

		LayoutParams leftButtonLayout = new LayoutParams(row2,col2);
		leftButtonLayout.height = (int)(super.getWindowHeight()*0.1);
		leftButtonLayout.width = (int)(super.getWindowWidth()*0.2);

		if (screenIdentity != 0)
		{
			FortitudeButton leftButton = (new FortitudeButton(R.drawable.left, R.drawable.left_pressed) {
				public void preClickActions()
				{

				}
				public void whenClicked()
				{
					screenIdentity--;
					HelpScreens.getMe().killMe();
					new HelpScreens (screenIdentity, screenToReturnTo);
				}
			});

			mainArea.addView(leftButton,leftButtonLayout);
		}
		else
		{
			Space leftButtonSpace = new Space(mainArea.getContext());
			leftButtonSpace.setLayoutParams(leftButtonLayout);
			mainArea.addView(leftButtonSpace,leftButtonLayout);
		}

		LayoutParams thirdColSpaceLayout = new LayoutParams(row2,col3);
		thirdColSpaceLayout.height = (int)(super.getWindowHeight()*0.1);
		thirdColSpaceLayout.width = (int)(super.getWindowWidth()*0.05);
		Space thirdColSpace = new Space(mainArea.getContext());
		thirdColSpace.setLayoutParams(thirdColSpaceLayout);
		mainArea.addView(thirdColSpace,thirdColSpaceLayout);

		LayoutParams cancelButtonLayout = new LayoutParams(row2,col4);
		cancelButtonLayout.height = (int)(super.getWindowHeight()*0.1);
		cancelButtonLayout.width = (int)(super.getWindowWidth()*0.4);

		FortitudeButton cancelButton = (new FortitudeButton(R.drawable.cancel, R.drawable.cancel_pressed) {
			public void preClickActions()
			{

			}
			public void whenClicked()
			{
				showNextScreen();
			}
		});

		mainArea.addView(cancelButton,cancelButtonLayout);

		LayoutParams fifthColSpaceLayout = new LayoutParams(row2,col5);
		fifthColSpaceLayout.height = (int)(super.getWindowHeight()*0.1);
		fifthColSpaceLayout.width = (int)(super.getWindowWidth()*0.05);
		Space fifthColSpace = new Space(mainArea.getContext());
		fifthColSpace.setLayoutParams(fifthColSpaceLayout);
		mainArea.addView(fifthColSpace,fifthColSpaceLayout);

		LayoutParams rightButtonLayout = new LayoutParams(row2,col6);
		rightButtonLayout.height = (int)(super.getWindowHeight()*0.1);
		rightButtonLayout.width = (int)(super.getWindowWidth()*0.2);

		if (screenIdentity != maximumScreen)
		{
			FortitudeButton rightButton = (new FortitudeButton(R.drawable.right, R.drawable.right_pressed) {
				public void preClickActions()
				{

				}
				public void whenClicked()
				{
					screenIdentity++;
					HelpScreens.getMe().killMe();
					new HelpScreens (screenIdentity, screenToReturnTo);

				}
			});

			mainArea.addView(rightButton,rightButtonLayout);
		}
		else
		{
			Space rightButtonSpace = new Space(mainArea.getContext());
			rightButtonSpace.setLayoutParams(rightButtonLayout);
			mainArea.addView(rightButtonSpace,leftButtonLayout);
		}

		return mainArea;
	}

	////////
	//
	//getMe
	//
	//returns the last created instance of this class, could be null
	//
	////////
	public static HelpScreens getMe()
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
	//showNextScreen
	//
	//This method is called when the user presses the cancel button and is overridden by methods that want to go somewhere 
	//that isn't back to the main screen when the cancel button is pressed.
	//
	////////
	public void showNextScreen()
	{
		killMe();
		if(screenToReturnTo == 0)
		{
			new MainScreen();
		}
		else if(screenToReturnTo == 1)
		{
			new AccountScreen() {
				public void showNextScreen()
				{
					new MainScreen();
				}
			};
		}
		else if(screenToReturnTo == 2)
		{
			new MainLoginScreen();
		}
	}

}