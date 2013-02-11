package com.example.fortitude;

import java.util.regex.Pattern;
import android.widget.GridLayout;
import android.widget.GridLayout.LayoutParams;
import android.widget.GridLayout.Spec;
import android.widget.Space;


public class NewUserScreen extends Window
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

	private Spec col1 = GridLayout.spec(0);
	private Spec col2 = GridLayout.spec(1);
	private Spec col3 = GridLayout.spec(2);
	private Spec col4 = GridLayout.spec(3);

	private Spec allcols = GridLayout.spec(0,2);

	private TextField usernameField = null; //username text box
	private PasswordTextField passwordField1 = null; //password text box
	private PasswordTextField passwordField2 = null; //password text box
	private TextField emailField1 = null; //email address
	private TextField emailField2 = null; //email address
	
	private static NewUserScreen me = null;

	////////
	//
	//newLoginScreen
	//
	//static constructor
	//
	////////
	public static void newNewUserScreen()
	{
		new NewUserScreen();	
	}

	////////
	//
	//Constructor
	//
	////////
	public NewUserScreen()
	{
		super(R.drawable.new_user);
		me = this;
		addContentToContentPane(createWindowPane());
	}

	private GridLayout createWindowPane()
	{
		GridLayout mainArea = new GridLayout(Fortitude.getFortitude());
		mainArea.setColumnCount(2);
		mainArea.setRowCount(12);
		
		LayoutParams firstColumnSpacerLayout = new LayoutParams(row1, col1); //like a positional spacer in top left
		firstColumnSpacerLayout.width = (GUI.calculateWindowWidth() / 2) - (GUI.calculateWindowWidth() / 15);
		firstColumnSpacerLayout.height = GUI.calculateWindowHeight() / 4;
		Space firstColumnSpacer = new Space(mainArea.getContext());
		firstColumnSpacer.setLayoutParams(firstColumnSpacerLayout);
		mainArea.addView(firstColumnSpacer, firstColumnSpacerLayout);
		
		LayoutParams usernameFieldImageLayout = new LayoutParams(row2, col2); //username image
		usernameFieldImageLayout.width = GUI.calculateWindowWidth() / 2;
		usernameFieldImageLayout.height = GUI.calculateWindowHeight() / 12;
		TextFieldImage usernameFieldImage = new TextFieldImage();
		usernameFieldImage.setLayoutParams(usernameFieldImageLayout);
		mainArea.addView(usernameFieldImage, usernameFieldImageLayout);
		
		LayoutParams usernameFieldLayout = new LayoutParams(row2, col2); //username textField
		usernameFieldLayout.width = GUI.calculateWindowWidth() / 2;
		usernameFieldLayout.height = GUI.calculateWindowHeight() / 12;
		this.usernameField = new TextField(16);
		usernameField.setLayoutParams(usernameFieldLayout);
		mainArea.addView(usernameField, usernameFieldLayout);
		
		LayoutParams thirdRowSpaceLayout = new LayoutParams(row3, col2); //third row space
		thirdRowSpaceLayout.height = GUI.calculateWindowHeight() / 25;
		Space thirdRowSpace = new Space(mainArea.getContext());
		thirdRowSpace.setLayoutParams(thirdRowSpaceLayout);
		mainArea.addView(thirdRowSpace, thirdRowSpaceLayout);
		
		LayoutParams password1FieldImageLayout = new LayoutParams(row4, col2); //fourth row password1 image
		password1FieldImageLayout.width = GUI.calculateWindowWidth() / 2;
		password1FieldImageLayout.height = GUI.calculateWindowHeight() / 12;
		TextFieldImage password1FieldImage = new TextFieldImage();
		password1FieldImage.setLayoutParams(password1FieldImageLayout);
		mainArea.addView(password1FieldImage, password1FieldImageLayout);
		
		LayoutParams password1FieldLayout = new LayoutParams(row4, col2); //fourth row password1 textField
		password1FieldLayout.width = GUI.calculateWindowWidth() / 2;
		password1FieldLayout.height = GUI.calculateWindowHeight() / 12;
		this.passwordField1 = new PasswordTextField(16);
		passwordField1.setLayoutParams(password1FieldLayout);
		mainArea.addView(passwordField1, password1FieldLayout);
		
		LayoutParams fithRowSpaceLayout = new LayoutParams(row5, col2); //fith row space
		fithRowSpaceLayout.height = GUI.calculateWindowHeight() / 25;
		Space fithRowSpace = new Space(mainArea.getContext());
		fithRowSpace.setLayoutParams(fithRowSpaceLayout);
		mainArea.addView(fithRowSpace, fithRowSpaceLayout);
		
		LayoutParams password2FieldImageLayout = new LayoutParams(row6, col2); //sixth row password2 image
		password2FieldImageLayout.width = GUI.calculateWindowWidth() / 2;
		password2FieldImageLayout.height = GUI.calculateWindowHeight() / 12;
		TextFieldImage password2FieldImage = new TextFieldImage();
		password2FieldImage.setLayoutParams(password2FieldImageLayout);
		mainArea.addView(password2FieldImage, password2FieldImageLayout);
		
		LayoutParams password2FieldLayout = new LayoutParams(row6, col2); //sixth row password2 textfield
		password2FieldLayout.width = GUI.calculateWindowWidth() / 2;
		password2FieldLayout.height = GUI.calculateWindowHeight() / 12;
		this.passwordField2 = new PasswordTextField(16);
		passwordField2.setLayoutParams(password2FieldLayout);
		mainArea.addView(passwordField2, password2FieldLayout);
		
		LayoutParams sixthRowSpaceLayout = new LayoutParams(row7, col2); //seventh row space
		sixthRowSpaceLayout.height = GUI.calculateWindowHeight() / 25;
		Space sixthRowSpace = new Space(mainArea.getContext());
		sixthRowSpace.setLayoutParams(sixthRowSpaceLayout);
		mainArea.addView(sixthRowSpace, sixthRowSpaceLayout);
		
		LayoutParams email1FieldImageLayout = new LayoutParams(row8, col2); //eighth row email1 image
		email1FieldImageLayout.width = GUI.calculateWindowWidth() / 2;
		email1FieldImageLayout.height = GUI.calculateWindowHeight() / 12;
		TextFieldImage email1FieldImage = new TextFieldImage();
		email1FieldImage.setLayoutParams(email1FieldImageLayout);
		mainArea.addView(new TextFieldImage(), email1FieldImageLayout);
		
		LayoutParams email1FieldLayout = new LayoutParams(row8, col2); //eighth row email1 textfield
		email1FieldLayout.width = GUI.calculateWindowWidth() / 2;
		email1FieldLayout.height = GUI.calculateWindowHeight() / 12;
		this.emailField1 = new TextField(50);
		emailField1.setLayoutParams(email1FieldLayout);
		mainArea.addView(emailField1, email1FieldLayout);
		
		LayoutParams eighthRowSpaceLayout = new LayoutParams(row9, col2); //ninth row space
		eighthRowSpaceLayout.height = GUI.calculateWindowHeight() / 25;
		Space eighthRowSpace = new Space(mainArea.getContext());
		eighthRowSpace.setLayoutParams(eighthRowSpaceLayout);
		mainArea.addView(eighthRowSpace, eighthRowSpaceLayout);
		
		LayoutParams email2FieldImageLayout = new LayoutParams(row10, col2); //tenth row email2 text box image
		email2FieldImageLayout.width = GUI.calculateWindowWidth() / 2;
		email2FieldImageLayout.height = GUI.calculateWindowHeight() / 12;
		TextFieldImage email2FieldImage = new TextFieldImage();
		email2FieldImage.setLayoutParams(email2FieldImageLayout);
		mainArea.addView(email2FieldImage, email2FieldImageLayout);
		
		LayoutParams email2FieldLayout = new LayoutParams(row10, col2); //tenth row email2 textbox
		email2FieldLayout.width = GUI.calculateWindowWidth() / 2;
		email2FieldLayout.height = GUI.calculateWindowHeight() / 12;
		this.emailField2 = new TextField(50);
		emailField2.setLayoutParams(email2FieldLayout);
		mainArea.addView(emailField2, email2FieldLayout);
		
		LayoutParams eleventhRowSpaceLayout = new LayoutParams(row11, col2);
		eleventhRowSpaceLayout.height = GUI.calculateWindowHeight() / 25;
		Space eleventhRowSpace = new Space(mainArea.getContext());
		eleventhRowSpace.setLayoutParams(eleventhRowSpaceLayout);
		mainArea.addView(eleventhRowSpace);
		
		GridLayout buttonGrid = new GridLayout(mainArea.getContext());
		buttonGrid.setColumnCount(4);
		buttonGrid.setRowCount(1);
		
		LayoutParams buttonLeftSpaceLayout = new LayoutParams(row1, col1);
		buttonLeftSpaceLayout.width = GUI.calculateWindowWidth() / 12;
		buttonLeftSpaceLayout.height = GUI.calculateWindowHeight() / 10;
		Space buttonLeftSpace = new Space(buttonGrid.getContext());
		buttonLeftSpace.setLayoutParams(buttonLeftSpaceLayout);
		buttonGrid.addView(buttonLeftSpace, buttonLeftSpaceLayout);
		
		LayoutParams createUserButtonLayout = new LayoutParams(row1, col2);
		createUserButtonLayout.width = (GUI.calculateWindowWidth() / 2) - (GUI.calculateWindowWidth() / 10);
		createUserButtonLayout.height = GUI.calculateWindowHeight() / 10;
		FortitudeButton createUserButton = (new FortitudeButton(R.drawable.create_user, R.drawable.create_user_pressed) {
			public void preClickActions()
			{
				
			}
			public void whenClicked()
			{
				createUser();
			}
		});
		createUserButton.setLayoutParams(createUserButtonLayout);
		buttonGrid.addView(createUserButton, createUserButtonLayout);
		
		LayoutParams buttonMiddleSpaceLayout = new LayoutParams(row1, col3);
		buttonMiddleSpaceLayout.width = GUI.calculateWindowWidth() / 15;
		buttonMiddleSpaceLayout.height = GUI.calculateWindowHeight() / 10;
		Space buttonMiddleSpace = new Space(buttonGrid.getContext());
		buttonMiddleSpace.setLayoutParams(buttonMiddleSpaceLayout);
		buttonGrid.addView(buttonMiddleSpace, buttonMiddleSpaceLayout);
		
		LayoutParams cancelButtonLayout = new LayoutParams(row1, col4);
		cancelButtonLayout.width = (GUI.calculateWindowWidth() / 2) - (GUI.calculateWindowWidth() / 10);
		cancelButtonLayout.height = GUI.calculateWindowHeight() / 10;
		FortitudeButton cancelButton = (new FortitudeButton(R.drawable.cancel, R.drawable.cancel_pressed) {
			public void preClickActions()
			{
				
			}
			public void whenClicked()
			{
				cancelCreateUser();
			}
		});
		cancelButton.setLayoutParams(cancelButtonLayout);
		buttonGrid.addView(cancelButton, cancelButtonLayout);
		
		LayoutParams buttonGridLayout = new LayoutParams(row12, allcols);
		mainArea.addView(buttonGrid, buttonGridLayout);
		
		return mainArea;
	}
	
	////////
	//
	//getMe()
	//
	//returns the last instance of this class that was created, could be null
	//
	////////
	public static NewUserScreen getMe()
	{
		return me;
	}
	
	////////
	//
	//signIn
	//
	//This method is called when the Create User button is pressed by the user
	//
	////////
	private void createUser()
	{
		if(validateUserInput())
		{
	    	ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Connecting To Server", false));
		    Login.createUser(usernameField.getText().toString(), passwordField1.getText().toString(), emailField1.getText().toString());
		}
	}

	////////
	//
	//validateUserInput
	//
	//validates the text in the text boxes when createUser is called
	//
	////////
	private boolean validateUserInput()
	{
		if(usernameField.getText().toString().equals(""))
		{
			MessageBox.newMsgBox("No username entered", true);
			return false;
		}
		if(usernameField.getText().toString().length() < 5)
		{
			MessageBox.newMsgBox("Username must be atleast 5 characters", true);
			return false;
		}
		if(usernameField.getText().toString().length() > 16)
		{
			MessageBox.newMsgBox("Username must be at most 16 characters", true);
			return false;
		}
		if(passwordField1.getText().toString().length() > 16)
		{
			MessageBox.newMsgBox("Passwords can be at most 16 characters long", true);
			return false;
		}
		if(passwordField1.getText().toString().length() < 5)
		{
			MessageBox.newMsgBox("Passwords must be atleast 5 characters long", true);
			return false;
		}
		if(passwordField1.getText().toString().equals(""))
		{
			MessageBox.newMsgBox("No password entered", true);
			return false;
		}
		if(!(passwordField1.getText().toString().equals(passwordField2.getText().toString())))
		{
			passwordField1.setText("");
			passwordField2.setText("");
			MessageBox.newMsgBox("Passwords did not match", true);
			return false;
		}
		if(emailField1.getText().toString().equals(""))
		{
			MessageBox.newMsgBox("No email address entered", true);
			return false;
		}
		if(!(emailField1.getText().toString().toLowerCase().equals(emailField2.getText().toString().toLowerCase())))
		{
			MessageBox.newMsgBox("Emails did not match", true);	
			return false;
		}
		if(!(Pattern.compile("^[a-z0-9._%-]+@[a-z0-9.-]+\\.[a-z]{2,4}$").matcher(emailField1.getText().toString()).matches()))
		{
			MessageBox.newMsgBox("Email address entered is not a valid email address", true);
			return false;
		}
		if(!(Pattern.compile("[0-9]").matcher(passwordField1.getText().toString()).find()))
		{
			MessageBox.newMsgBox("Passwords must contain atleast one digit", true);
			return false;
		}
		if(!(Pattern.compile("[a-z]").matcher(passwordField1.getText().toString().toLowerCase()).find()))
		{
			MessageBox.newMsgBox("Passwords must contain atleast one character", true);
			return false;
		}
		return true;
	}
	
	////////
	//
	//cancelCreateUser
	//
	//called when the user presses the cancel button. clears screen and returns to Main login screen
	//
	////////
	private void cancelCreateUser()
	{
		killMe();
		new MainLoginScreen();
	}

	////////
	//
	//killMe
	//
	//removes the NewUserScreen from view
	//
	////////
	public void killMe()
	{
		me = null;
		this.removeAllViews();
	}
}
