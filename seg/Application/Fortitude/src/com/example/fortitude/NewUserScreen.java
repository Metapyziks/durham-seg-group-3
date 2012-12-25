package com.example.fortitude;

import java.util.regex.Pattern;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridLayout.LayoutParams;
import android.widget.GridLayout.Spec;
import android.widget.Space;
import android.graphics.Color;
import android.widget.TextView;
import android.view.Gravity;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.EditText;
import android.text.method.PasswordTransformationMethod;


public class NewUserScreen extends GridLayout
{
	private int windowWidth; //Visible window width
	private int windowHeight; //Visible window height

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
	private Spec col5 = GridLayout.spec(4);

	private Spec allcols = GridLayout.spec(0,5);

	private EditText usernameField = null; //username text box
	private EditText passwordField1 = null; //password text box
	private EditText passwordField2 = null; //password text box
	private EditText emailField1 = null; //email address
	private EditText emailField2 = null; //email address
	
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
		new MainLoginScreen();	
	}

	////////
	//
	//Constructor
	//
	////////
	public NewUserScreen()
	{
		super(Fortitude.getFortitude());

		me = this;
		
		setWindowDimensions();
		setColsAndRows();

		createPositionalSpace();

		addNewUserScreen();

		addThisToDisplay();
	}

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
		if(!(emailField1.getText().toString().equals(emailField2.getText().toString())))
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
	
	private void cancelCreateUser()
	{
		killMe();
		new MainLoginScreen();
	}

	////////
	//
	//addLoginScreen
	//
	//create and add login screen to this grid
	//
	////////
	private void addNewUserScreen()
	{
		GridLayout mainArea = new GridLayout(this.getContext()); //The gridlayout for the layout of the NewUserScreen itself
		mainArea.setColumnCount(5);
		mainArea.setRowCount(12);
		mainArea.setBackgroundColor(Color.BLUE);
		addLayoutToNewUserScreen(mainArea);
	}

	////////
	//
	//addLayoutToLoginScreen
	//
	//adds all the relevant layout to the given LoginScreen
	//
	////////
	private void addLayoutToNewUserScreen(GridLayout mainArea)
	{   
		LayoutParams firstRowTextViewLayout = new LayoutParams(row1, allcols); //1st row text view middle
		firstRowTextViewLayout.width = windowWidth;
		firstRowTextViewLayout.height = windowHeight / 6;
		TextView firstRowTextView = new TextView(mainArea.getContext());
		firstRowTextView.setLayoutParams(firstRowTextViewLayout);
		firstRowTextView.setText("Create A New User");
		firstRowTextView.setTextSize(14);
		firstRowTextView.setGravity(Gravity.CENTER);
		firstRowTextView.setBackgroundColor(Color.RED);
		firstRowTextView.setGravity(Gravity.CENTER);
		mainArea.addView(firstRowTextView, firstRowTextViewLayout);

		LayoutParams secondRowSpaceLayout = new LayoutParams(row2, allcols); //2nd row space
		secondRowSpaceLayout.width = windowWidth;
		secondRowSpaceLayout.height =  windowHeight / 20;
		Space secondRowSpace = new Space(mainArea.getContext());
		secondRowSpace.setLayoutParams(secondRowSpaceLayout);
		mainArea.addView(secondRowSpace, secondRowSpaceLayout);

		LayoutParams thirdRowLeftSpaceLayout = new LayoutParams(row3, col1); //3rd row left space
		thirdRowLeftSpaceLayout.width = (windowWidth / 10) / 3;
		thirdRowLeftSpaceLayout.height = (windowHeight / 2) / 6;
		Space thirdRowLeftSpace = new Space(mainArea.getContext());
		thirdRowLeftSpace.setLayoutParams(thirdRowLeftSpaceLayout);
		mainArea.addView(thirdRowLeftSpace, thirdRowLeftSpaceLayout);

		LayoutParams secondRowTextViewLayout = new LayoutParams(row3, col2); //Username label
		secondRowTextViewLayout.width = (windowWidth / 10) * 3;
		secondRowTextViewLayout.height = (windowHeight / 2) / 6;
		TextView usernameLabel = new TextView(mainArea.getContext());
		usernameLabel.setLayoutParams(secondRowTextViewLayout);
		usernameLabel.setText("Username:");
		usernameLabel.setTextSize(14);
		usernameLabel.setGravity(Gravity.CENTER);
		mainArea.addView(usernameLabel, secondRowTextViewLayout);

		LayoutParams usernameFieldLayout = new LayoutParams(row3, col3); //Username text box
		usernameFieldLayout.width = windowWidth / 2;
		usernameFieldLayout.height = (windowHeight / 2) / 6;
		usernameField = new EditText(mainArea.getContext());
		usernameField.setLayoutParams(usernameFieldLayout);
		usernameField.setBackgroundColor(Color.WHITE);
		usernameField.setTextSize(12);
		mainArea.addView(usernameField, usernameFieldLayout);

		LayoutParams fourthRowSpaceLayout = new LayoutParams(row4, allcols); //4th row space
		fourthRowSpaceLayout.width = windowWidth;
		fourthRowSpaceLayout.height = windowHeight / 20;
		Space fourthRowSpace = new Space(mainArea.getContext());
		fourthRowSpace.setLayoutParams(fourthRowSpaceLayout);
		mainArea.addView(fourthRowSpace, fourthRowSpaceLayout);        

		LayoutParams fithRowLeftSpaceLayout = new LayoutParams(row5, col1); //5th row left space
		fithRowLeftSpaceLayout.width = (windowWidth / 10) / 3;
		fithRowLeftSpaceLayout.height = (windowHeight / 2) / 6;
		Space fithRowLeftSpace = new Space(mainArea.getContext());
		fithRowLeftSpace.setLayoutParams(fithRowLeftSpaceLayout);
		mainArea.addView(fithRowLeftSpace, fithRowLeftSpaceLayout);

		LayoutParams fithRowTextViewLayout = new LayoutParams(row5, col2); //Password1 label
		fithRowTextViewLayout.width = (windowWidth / 10) * 3;
		fithRowTextViewLayout.height = (windowHeight / 2) / 6;
		TextView password1Label = new TextView(mainArea.getContext());
		password1Label.setLayoutParams(fithRowTextViewLayout);
		password1Label.setText("Password:");
		password1Label.setTextSize(14);
		password1Label.setGravity(Gravity.CENTER);
		mainArea.addView(password1Label, fithRowTextViewLayout);

		LayoutParams passwordField1Layout = new LayoutParams(row5, col3); //Password1 text box
		passwordField1Layout.width = windowWidth / 2;
		passwordField1Layout.height = (windowHeight / 2) / 6;
		passwordField1 = new EditText(mainArea.getContext());
		passwordField1.setLayoutParams(passwordField1Layout);
		passwordField1.setBackgroundColor(Color.WHITE);
		passwordField1.setTransformationMethod(PasswordTransformationMethod.getInstance());
		passwordField1.setTextSize(12);
		mainArea.addView(passwordField1, passwordField1Layout);

		LayoutParams sixthRowSpaceLayout = new LayoutParams(row6, allcols); //6th row space
		sixthRowSpaceLayout.width = windowWidth;
		sixthRowSpaceLayout.height = windowHeight / 20;
		Space sixthRowSpace = new Space(mainArea.getContext());
		sixthRowSpace.setLayoutParams(sixthRowSpaceLayout);
		mainArea.addView(sixthRowSpace, sixthRowSpaceLayout);
		
		LayoutParams seventhRowLeftSpaceLayout = new LayoutParams(row7, col1); //7th row left space
		seventhRowLeftSpaceLayout.width = (windowWidth / 10) / 3;
		seventhRowLeftSpaceLayout.height = (windowHeight / 2) / 6;
		Space seventhRowLeftSpace = new Space(mainArea.getContext());
		seventhRowLeftSpace.setLayoutParams(seventhRowLeftSpaceLayout);
		mainArea.addView(seventhRowLeftSpace, seventhRowLeftSpaceLayout);

		LayoutParams seventhRowTextViewLayout = new LayoutParams(row7, col2); //Password label 2
		seventhRowTextViewLayout.width = (windowWidth / 10) * 3;
		seventhRowTextViewLayout.height = (windowHeight / 2) / 6;
		TextView password2Label = new TextView(mainArea.getContext());
		password2Label.setLayoutParams(seventhRowTextViewLayout);
		password2Label.setText("Re-enter Pass.:");
		password2Label.setTextSize(14);
		password2Label.setGravity(Gravity.CENTER);
		mainArea.addView(password2Label, seventhRowTextViewLayout);

		LayoutParams passwordField2Layout = new LayoutParams(row7, col3); //Password text box 2
		passwordField2Layout.width = windowWidth / 2;
		passwordField2Layout.height = (windowHeight / 2) / 6;
		passwordField2 = new EditText(mainArea.getContext());
		passwordField2.setLayoutParams(passwordField2Layout);
		passwordField2.setBackgroundColor(Color.WHITE);
		passwordField2.setTransformationMethod(PasswordTransformationMethod.getInstance());
		passwordField2.setTextSize(12);
		mainArea.addView(passwordField2, passwordField2Layout);
		
		LayoutParams eigthRowSpaceLayout = new LayoutParams(row8, allcols); //8th row space
		eigthRowSpaceLayout.width = windowWidth;
		eigthRowSpaceLayout.height = windowHeight / 20;
		Space eigthRowSpace = new Space(mainArea.getContext());
		eigthRowSpace.setLayoutParams(eigthRowSpaceLayout);
		mainArea.addView(eigthRowSpace, eigthRowSpaceLayout);
		
		LayoutParams ninthRowLeftSpaceLayout = new LayoutParams(row9, col1); //9th row left space
		ninthRowLeftSpaceLayout.width = (windowWidth / 10) / 3;
		ninthRowLeftSpaceLayout.height = (windowHeight / 2) / 6;
		Space ninthRowLeftSpace = new Space(mainArea.getContext());
		ninthRowLeftSpace.setLayoutParams(ninthRowLeftSpaceLayout);
		mainArea.addView(ninthRowLeftSpace, ninthRowLeftSpaceLayout);

		LayoutParams ninthRowTextViewLayout = new LayoutParams(row9, col2); //Email label 1
		ninthRowTextViewLayout.width = (windowWidth / 10) * 3;
		ninthRowTextViewLayout.height = (windowHeight / 2) / 6;
		TextView emailLabel1 = new TextView(mainArea.getContext());
		emailLabel1.setLayoutParams(ninthRowTextViewLayout);
		emailLabel1.setText("Email:");
		emailLabel1.setTextSize(14);
		emailLabel1.setGravity(Gravity.CENTER);
		mainArea.addView(emailLabel1, ninthRowTextViewLayout);

		LayoutParams emailField1Layout = new LayoutParams(row9, col3); //Email edit text 1
		emailField1Layout.width = windowWidth / 2;
		emailField1Layout.height = (windowHeight / 2) / 6;
		emailField1 = new EditText(mainArea.getContext());
		emailField1.setLayoutParams(emailField1Layout);
		emailField1.setBackgroundColor(Color.WHITE);
		emailField1.setTextSize(12);
		mainArea.addView(emailField1, emailField1Layout);

		LayoutParams tenthRowSpaceLayout = new LayoutParams(row10, allcols); //10th row space
		tenthRowSpaceLayout.width = windowWidth;
		tenthRowSpaceLayout.height = windowHeight / 20;
		Space tenthRowSpace = new Space(mainArea.getContext());
		tenthRowSpace.setLayoutParams(tenthRowSpaceLayout);
		mainArea.addView(tenthRowSpace, tenthRowSpaceLayout); 
		
		LayoutParams eleventhRowLeftSpaceLayout = new LayoutParams(row11, col1); //11th row left space
		eleventhRowLeftSpaceLayout.width = (windowWidth / 10) / 3;
		eleventhRowLeftSpaceLayout.height = (windowHeight / 2) / 6;
		Space eleventhRowLeftSpace = new Space(mainArea.getContext());
		eleventhRowLeftSpace.setLayoutParams(eleventhRowLeftSpaceLayout);
		mainArea.addView(eleventhRowLeftSpace, eleventhRowLeftSpaceLayout);

		LayoutParams eleventhRowTextViewLayout = new LayoutParams(row11, col2); //Email label 2
		eleventhRowTextViewLayout.width = (windowWidth / 10) * 3;
		eleventhRowTextViewLayout.height = (windowHeight / 2) / 6;
		TextView emailLabel2 = new TextView(mainArea.getContext());
		emailLabel2.setLayoutParams(eleventhRowTextViewLayout);
		emailLabel2.setText("Re-enter Email:");
		emailLabel2.setTextSize(14);
		emailLabel2.setGravity(Gravity.CENTER);
		mainArea.addView(emailLabel2, eleventhRowTextViewLayout);

		LayoutParams emailField2Layout = new LayoutParams(row11, col3); //Email edit text 2
		emailField2Layout.width = windowWidth / 2;
		emailField2Layout.height = (windowHeight / 2) / 6;
		emailField2 = new EditText(mainArea.getContext());
		emailField2.setLayoutParams(emailField2Layout);
		emailField2.setBackgroundColor(Color.WHITE);
		emailField2.setTextSize(12);
		mainArea.addView(emailField2, emailField2Layout);

		LayoutParams twelthRowSpaceLayout = new LayoutParams(row12, allcols); //12th row space
		twelthRowSpaceLayout.width = windowWidth;
		twelthRowSpaceLayout.height = windowHeight / 20;
		Space twelthRowSpace = new Space(mainArea.getContext());
		twelthRowSpace.setLayoutParams(twelthRowSpaceLayout);
		mainArea.addView(twelthRowSpace, twelthRowSpaceLayout); 

		GridLayout signInGrid = new GridLayout(mainArea.getContext());
		signInGrid.setRowCount(1);
		signInGrid.setColumnCount(5);

		LayoutParams signInButtonLeftSpaceLayout = new LayoutParams(row1, col1); //space left of sign in button
		signInButtonLeftSpaceLayout.width = windowWidth / 9;
		signInButtonLeftSpaceLayout.height = (windowHeight / 2) / 6;
		Space signInButtonLeftSpace = new Space(mainArea.getContext());
		signInButtonLeftSpace.setLayoutParams(signInButtonLeftSpaceLayout);
		signInGrid.addView(signInButtonLeftSpace, signInButtonLeftSpaceLayout);

		LayoutParams signInLayout = new LayoutParams(row1, col2); //Sign in button
		signInLayout.width = windowWidth / 3;
		signInLayout.height = windowHeight / 10;
		Button signInButton = new Button(mainArea.getContext());
		signInButton.setLayoutParams(signInLayout);
		signInButton.setTextSize(14);
		signInButton.setText("Create User");
		signInButton.setBackgroundColor(Color.GRAY);
		signInButton.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0)
			{
				createUser();
			}
		});
		signInGrid.addView(signInButton, signInLayout);

		LayoutParams middleButtonSpacerLayout = new LayoutParams(row1, col3); //middle button spacer
		middleButtonSpacerLayout.width = windowWidth / 9;
		middleButtonSpacerLayout.height = (windowHeight / 2) / 6;
		Space middleButtonSpacerSpace = new Space(mainArea.getContext());
		middleButtonSpacerSpace.setLayoutParams(middleButtonSpacerLayout);
		signInGrid.addView(middleButtonSpacerSpace, middleButtonSpacerLayout);

		LayoutParams newUserLayout = new LayoutParams(row1, col4); //New User button
		newUserLayout.width = windowWidth / 3;
		newUserLayout.height = windowHeight / 10;
		Button newUserButton = new Button(mainArea.getContext());
		newUserButton.setLayoutParams(newUserLayout);
		newUserButton.setTextSize(14);
		newUserButton.setText("Cancel");
		newUserButton.setBackgroundColor(Color.GRAY);
		newUserButton.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0)
			{
				cancelCreateUser();
			}
		});
		signInGrid.addView(newUserButton, newUserLayout);

		LayoutParams xx = new LayoutParams(row13, allcols);

		mainArea.addView(signInGrid, xx);

		LayoutParams fourteenthRowSpaceLayout = new LayoutParams(row14, allcols); //14th row space
		fourteenthRowSpaceLayout.width = (windowWidth / 10);
		fourteenthRowSpaceLayout.height = (windowHeight / 2) / 2;
		Space fourteenthRowSpace = new Space(mainArea.getContext());
		fourteenthRowSpace.setLayoutParams(fourteenthRowSpaceLayout);
		mainArea.addView(fourteenthRowSpace, fourteenthRowSpaceLayout); 

		LayoutParams messageLayout = new LayoutParams(row2, col2);

		addView(mainArea, messageLayout);
	}

	////////
	//
	//addThisToDisplay
	//
	//adds the current object to the top of the application's display
	//
	////////
	private void addThisToDisplay()
	{
		LayoutParams messageboxParams = new LayoutParams();
		Fortitude.getFortitude().addContentView(this, messageboxParams);
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

	////////
	//
	//createPositionalSpace
	//
	//Creates an invisible square in the top left corner of the screen.
	//The width and height of this square define where the message box will
	//appear on screen as the new user screen is positioned starting at the bottom
	//right hand corner of this square.
	//
	////////
	private void createPositionalSpace()
	{
		LayoutParams spaceLayout = new LayoutParams(row1, col1); //Top left to dictate main layout
		spaceLayout.width = 0;
		spaceLayout.height = 0;
		Space space = new Space(Fortitude.getFortitude());
		space.setLayoutParams(spaceLayout);
		addView(space, spaceLayout);
	}

	////////
	//
	//setColsAndRows
	//
	//sets the number of columns and rows this main grid will have to define the
	//overall positional layout of where the message box will go on the screen.
	//
	////////
	private void setColsAndRows()
	{
		setColumnCount(2);
		setRowCount(2);
	}

	////////
	//
	//setWindowDimensions
	//
	//sets the values for dimensions of the visible window
	//
	////////
	private void setWindowDimensions()
	{
		windowWidth = GUI.calculateWindowWidth();
		windowHeight = GUI.calculateWindowHeight();
	}
}
