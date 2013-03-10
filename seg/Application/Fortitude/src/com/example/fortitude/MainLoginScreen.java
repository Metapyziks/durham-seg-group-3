package com.example.fortitude;

import android.widget.GridLayout;
import android.widget.GridLayout.LayoutParams;
import android.widget.GridLayout.Spec;
import android.widget.Space;

public class MainLoginScreen extends Window
{	
	private Spec row1 = GridLayout.spec(0);
	private Spec row2 = GridLayout.spec(1); 
	private Spec row3 = GridLayout.spec(2);
	private Spec row4 = GridLayout.spec(3);
	private Spec row5 = GridLayout.spec(4);
	private Spec row6 = GridLayout.spec(5);
	private Spec row7 = GridLayout.spec(6);
	private Spec row8 = GridLayout.spec(7);

	private Spec col1 = GridLayout.spec(0);
	private Spec col2 = GridLayout.spec(1);
	private Spec col3 = GridLayout.spec(2);
	private Spec col4 = GridLayout.spec(3);

	private Spec allcols = GridLayout.spec(0,2);

	private TextField usernameField = null; //username text box
	private PasswordTextField passwordField = null; //password text box

	private static MainLoginScreen me = null;

	////////
	//
	//Constructor
	//
	////////
	public MainLoginScreen()
	{
		super(R.drawable.sign_in);
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
		//GridLayout to be returned
		GridLayout mainArea = new GridLayout(Fortitude.getFortitude());
		mainArea.setRowCount(8);
		mainArea.setColumnCount(2);

		LayoutParams firstRowSpaceLayout = new LayoutParams(row1, allcols); //first row space
		firstRowSpaceLayout.width = super.getWindowWidth();
		firstRowSpaceLayout.height = (super.getWindowHeight() / 3) + (super.getWindowHeight() / 12);
		Space firstRowSpace = new Space(mainArea.getContext());
		firstRowSpace.setLayoutParams(firstRowSpaceLayout);
		mainArea.addView(firstRowSpace, firstRowSpaceLayout);

		LayoutParams secondRowTextFieldLeftSpaceLayout = new LayoutParams(row2, col1); //second row left space
		secondRowTextFieldLeftSpaceLayout.width = (super.getWindowWidth() / 2) - (super.getWindowWidth() / 15);
		secondRowTextFieldLeftSpaceLayout.height = super.getWindowHeight() / 12;
		Space secondRowTextFieldLeftSpace = new Space(mainArea.getContext());
		secondRowTextFieldLeftSpace.setLayoutParams(secondRowTextFieldLeftSpaceLayout);
		mainArea.addView(secondRowTextFieldLeftSpace, secondRowTextFieldLeftSpaceLayout);

		LayoutParams secondRowTextFieldLayout = new LayoutParams(row2, col2); //second row username textfield
		secondRowTextFieldLayout.width = super.getWindowWidth() / 2;
		secondRowTextFieldLayout.height = super.getWindowHeight() / 12;
		TextFieldImage usernameTextFieldImage = new TextFieldImage();
		usernameTextFieldImage.setLayoutParams(secondRowTextFieldLayout);
		mainArea.addView(usernameTextFieldImage, secondRowTextFieldLayout);

		LayoutParams usernameFieldLayout = new LayoutParams(row2, col2); //Username text box
		usernameFieldLayout.width = super.getWindowWidth() / 2;
		usernameFieldLayout.height = super.getWindowHeight() / 12;
		usernameField = new TextField(16);
		usernameField.setLayoutParams(usernameFieldLayout);
		mainArea.addView(usernameField, usernameFieldLayout);

		LayoutParams thirdRowSpaceLayout = new LayoutParams(row3, allcols); //third row space
		thirdRowSpaceLayout.width = super.getWindowWidth();
		thirdRowSpaceLayout.height = super.getWindowHeight() / 15;
		Space thirdRowSpace = new Space(mainArea.getContext());
		thirdRowSpace.setLayoutParams(thirdRowSpaceLayout);
		mainArea.addView(thirdRowSpace, thirdRowSpaceLayout);

		LayoutParams fourthRowTextFieldLeftSpaceLayout = new LayoutParams(row4, col1); //fourth row left space
		fourthRowTextFieldLeftSpaceLayout.width = (super.getWindowWidth() / 2) - (super.getWindowWidth() / 15);
		fourthRowTextFieldLeftSpaceLayout.height = super.getWindowHeight() / 12;
		Space fourthRowTextFieldLeftSpace = new Space(mainArea.getContext());
		fourthRowTextFieldLeftSpace.setLayoutParams(fourthRowTextFieldLeftSpaceLayout);
		mainArea.addView(fourthRowTextFieldLeftSpace, fourthRowTextFieldLeftSpaceLayout);

		LayoutParams fourthRowTextFieldLayout = new LayoutParams(row4, col2); //Fourth row password text field
		fourthRowTextFieldLayout.width = super.getWindowWidth() / 2;
		fourthRowTextFieldLayout.height = super.getWindowHeight() / 12;
		TextFieldImage passwordFieldImage = new TextFieldImage();
		passwordFieldImage.setLayoutParams(fourthRowTextFieldLayout);
		mainArea.addView(passwordFieldImage, fourthRowTextFieldLayout);

		LayoutParams passwordFieldLayout = new LayoutParams(row4, col2); //password text box
		passwordFieldLayout.width = super.getWindowWidth() / 2;
		passwordFieldLayout.height = super.getWindowHeight() / 12;
		passwordField = new PasswordTextField(16);
		passwordField.setLayoutParams(passwordFieldLayout);
		mainArea.addView(passwordField, passwordFieldLayout);

		LayoutParams fithRowSpaceLayout = new LayoutParams(row5, allcols); //fith row space
		fithRowSpaceLayout.width = super.getWindowWidth();
		fithRowSpaceLayout.height = super.getWindowHeight() / 15;
		Space fithRowSpace = new Space(mainArea.getContext());
		fithRowSpace.setLayoutParams(fithRowSpaceLayout);
		mainArea.addView(fithRowSpace, fithRowSpaceLayout);

		GridLayout forgottenDetailsButtonGrid = new GridLayout(mainArea.getContext());
		forgottenDetailsButtonGrid.setColumnCount(2);
		forgottenDetailsButtonGrid.setRowCount(1);

		LayoutParams sixthRowLeftSpaceLayout = new LayoutParams(row1, col1); //Space to position the forgotten details link 
		sixthRowLeftSpaceLayout.width = super.getWindowWidth() / 4;
		Space sixthRowLeftSpace = new Space(forgottenDetailsButtonGrid.getContext());
		sixthRowLeftSpace.setLayoutParams(sixthRowLeftSpaceLayout);
		forgottenDetailsButtonGrid.addView(sixthRowLeftSpace, sixthRowLeftSpaceLayout);

		LayoutParams forgottenDetailsButtonLayout = new LayoutParams(row1, col2); //forgotten details link
		forgottenDetailsButtonLayout.width = super.getWindowWidth() / 2;
		forgottenDetailsButtonLayout.height = super.getWindowHeight() / 36;
		FortitudeButton forgottenDetailsButton = (new FortitudeButton(R.drawable.forgotten_your_details, R.drawable.forgotten_your_details, 0) {
			public void preClickActions()
			{

			}
			public void whenClicked()
			{
				MainLoginScreen.getMe().killMe();
				new ForgottenDetailsScreen();
			}
		});
		forgottenDetailsButton.setLayoutParams(forgottenDetailsButtonLayout);
		forgottenDetailsButtonGrid.addView(forgottenDetailsButton, forgottenDetailsButtonLayout);

		LayoutParams forgottenDetailsButtonGridLayout = new LayoutParams(row6, allcols);
		mainArea.addView(forgottenDetailsButtonGrid, forgottenDetailsButtonGridLayout);

		LayoutParams seventhRowSpaceLayout = new LayoutParams(row7, allcols); //seventh row space
		seventhRowSpaceLayout.width = super.getWindowWidth();
		seventhRowSpaceLayout.height = (super.getWindowHeight() / 15) + (super.getWindowHeight() / 24) + (super.getWindowHeight() / 70);
		Space seventhRowSpace = new Space(mainArea.getContext());
		seventhRowSpace.setLayoutParams(seventhRowSpaceLayout);
		mainArea.addView(seventhRowSpace, seventhRowSpaceLayout);

		GridLayout buttonsArea = new GridLayout(Fortitude.getFortitude());
		buttonsArea.setRowCount(1);
		buttonsArea.setColumnCount(4);

		LayoutParams eigthRowLeftSpaceLayout = new LayoutParams(row1, col1); //eigth row left space
		eigthRowLeftSpaceLayout.width = super.getWindowWidth() / 12;
		eigthRowLeftSpaceLayout.height = super.getWindowHeight() / 10;
		Space eigthRowLeftSpace = new Space(buttonsArea.getContext());
		eigthRowLeftSpace.setLayoutParams(eigthRowLeftSpaceLayout);
		buttonsArea.addView(eigthRowLeftSpace, eigthRowLeftSpaceLayout);

		LayoutParams eigthRowSignInButtonLayout = new LayoutParams(row1, col2); //eigth row sign in button
		eigthRowSignInButtonLayout.width = (super.getWindowWidth() / 2) - (super.getWindowWidth() / 10);
		eigthRowSignInButtonLayout.height = super.getWindowHeight() / 10;
		FortitudeButton signInButton = (new FortitudeButton(R.drawable.sign_in_button, R.drawable.sign_in_button_pressed) {
			public void preClickActions()
			{
				disableAllTheChildren(MainLoginScreen.getMe());
			}

			public void whenClicked()
			{
				signIn();
			}
		});
		signInButton.setLayoutParams(eigthRowSignInButtonLayout);
		buttonsArea.addView(signInButton, eigthRowSignInButtonLayout);

		LayoutParams eigthRowMiddleSpaceLayout = new LayoutParams(row1, col3); //eigth row middle space
		eigthRowMiddleSpaceLayout.width = super.getWindowWidth() / 15;
		eigthRowMiddleSpaceLayout.height = super.getWindowHeight() / 10;
		Space eigthRowMiddleSpace = new Space(buttonsArea.getContext());
		eigthRowMiddleSpace.setLayoutParams(eigthRowMiddleSpaceLayout);
		buttonsArea.addView(eigthRowMiddleSpace, eigthRowMiddleSpaceLayout);

		LayoutParams eigthRowNewUserButtonLayout = new LayoutParams(row1, col4); //eigth row sign in button
		eigthRowNewUserButtonLayout.width = (super.getWindowWidth() / 2) - (super.getWindowWidth() / 10);
		eigthRowNewUserButtonLayout.height = super.getWindowHeight() / 10;
		FortitudeButton newUserButton = (new FortitudeButton(R.drawable.new_user_button, R.drawable.new_user_button_pressed) {
			public void preClickActions()
			{
				disableAllTheChildren(MainLoginScreen.getMe());
			}
			public void whenClicked()
			{
				if(MainLoginScreen.getMe() != null)
				{
					newUser();
				}
			}
		});
		newUserButton.setLayoutParams(eigthRowNewUserButtonLayout);
		buttonsArea.addView(newUserButton, eigthRowNewUserButtonLayout);

		LayoutParams sixthRowLayout = new LayoutParams(row8, allcols);
		mainArea.addView(buttonsArea, sixthRowLayout);

		return mainArea;
	}

	////////
	//
	//forgottenDetails
	//
	//called when the forgotten details link is pressed
	//
	////////
	public void forgottenDetails()
	{
		getMe().killMe();
		new ForgottenDetailsScreen();
	}

	////////
	//
	//signIn
	//
	//This method is called when the Sign in button is pressed by the user
	//
	////////
	private void signIn()
	{
		if(usernameField.getText().toString().equals(""))
		{
			MessageBox.newMsgBox("No username input", true);
			return;
		}
		if(passwordField.getText().toString().equals(""))
		{
			MessageBox.newMsgBox("No password input", true);
			return;
		}
		ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Connecting To Server", false));
		Login.initialLogIn(usernameField.getText().toString(), passwordField.getText().toString());
	}

	////////
	//
	//newUser
	//
	//closes this screen and opens the screen use to create a new user
	//
	////////
	private void newUser()
	{
		killMe();
		new NewUserScreen();
	}

	////////
	//
	//getMe
	//
	//returns the last instance of the MainLoginScreen that was created.
	//warning! may not exist or may have been destroyed! careful when using this method!
	//
	////////
	public static MainLoginScreen getMe()
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

	public TextField getPasswordField()
	{
		return passwordField;
	}
}
