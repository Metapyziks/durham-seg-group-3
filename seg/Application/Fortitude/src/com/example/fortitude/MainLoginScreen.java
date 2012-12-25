package com.example.fortitude;

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


public class MainLoginScreen extends GridLayout
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
    
    private Spec col1 = GridLayout.spec(0);
    private Spec col2 = GridLayout.spec(1);
    private Spec col3 = GridLayout.spec(2);
    private Spec col4 = GridLayout.spec(3);
    private Spec col5 = GridLayout.spec(4);

    private Spec allcols = GridLayout.spec(0,5);
    
    private EditText usernameField = null; //username text box
    private EditText passwordField = null; //password text box
    
    private static MainLoginScreen me = null;
    
	////////
	//
	//newLoginScreen
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
    public MainLoginScreen()
    {
    	super(Fortitude.getFortitude());
    	
    	me = this;
    	
    	setWindowDimensions();
    	setColsAndRows();
    	
    	createPositionalSpace();
    	
    	addMainLoginScreen();
    	
    	addThisToDisplay();
    }
    
    public static MainLoginScreen getMe()
    {
    	return me;
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
    //addLoginScreen
    //
    //create and add login screen to this grid
    //
    ////////
    private void addMainLoginScreen()
    {
        GridLayout mainArea = new GridLayout(this.getContext()); //The gridlayout for the layout of the MainLoginScreen itself
        mainArea.setColumnCount(5);
        mainArea.setRowCount(7);
        mainArea.setBackgroundColor(Color.BLUE);
        addLayoutToLoginScreen(mainArea);
    }
    
	////////
	//
	//addLayoutToLoginScreen
	//
	//adds all the relevant layout to the given LoginScreen
	//
	////////
    private void addLayoutToLoginScreen(GridLayout mainArea)
    {   
        LayoutParams firstRowTextViewLayout = new LayoutParams(row1, allcols); //1st row text view middle
        firstRowTextViewLayout.width = windowWidth;
        firstRowTextViewLayout.height = windowHeight / 6;
        TextView firstRowTextView = new TextView(mainArea.getContext());
        firstRowTextView.setLayoutParams(firstRowTextViewLayout);
        firstRowTextView.setText("Please Sign In!");
        firstRowTextView.setTextSize(14);
        firstRowTextView.setGravity(Gravity.CENTER);
        firstRowTextView.setBackgroundColor(Color.RED);
        firstRowTextView.setGravity(Gravity.CENTER);
        mainArea.addView(firstRowTextView, firstRowTextViewLayout);
        
        LayoutParams secondRowTextViewLayout = new LayoutParams(row2, allcols); //2nd row space
        secondRowTextViewLayout.width = windowWidth;
        secondRowTextViewLayout.height =  windowHeight / 5;
        TextView secondRowTextView = new TextView(mainArea.getContext());
        secondRowTextView.setLayoutParams(secondRowTextViewLayout);
        secondRowTextView.setText("Sign in with your Fortitude username and password");
        secondRowTextView.setTextSize(14);
        secondRowTextView.setGravity(Gravity.CENTER);
        mainArea.addView(secondRowTextView, secondRowTextViewLayout);
        
        LayoutParams thirdRowLeftSpaceLayout = new LayoutParams(row3, col1); //3rd row left space
        thirdRowLeftSpaceLayout.width = (windowWidth / 10) / 3;
        thirdRowLeftSpaceLayout.height = (windowHeight / 2) / 6;
        Space thirdRowLeftSpace = new Space(mainArea.getContext());
        thirdRowLeftSpace.setLayoutParams(thirdRowLeftSpaceLayout);
        mainArea.addView(thirdRowLeftSpace, thirdRowLeftSpaceLayout);
        
        LayoutParams thirdRowTextViewLayout = new LayoutParams(row3, col2); //Username label
        thirdRowTextViewLayout.width = (windowWidth / 10) * 3;
        thirdRowTextViewLayout.height = (windowHeight / 2) / 6;
        TextView usernameLabel = new TextView(mainArea.getContext());
        usernameLabel.setLayoutParams(thirdRowTextViewLayout);
        usernameLabel.setText("Username:");
        usernameLabel.setTextSize(14);
        usernameLabel.setGravity(Gravity.CENTER);
        mainArea.addView(usernameLabel, thirdRowTextViewLayout);
        
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
        fourthRowSpaceLayout.height = usernameFieldLayout.height;
        Space fourthRowSpace = new Space(mainArea.getContext());
        fourthRowSpace.setLayoutParams(fourthRowSpaceLayout);
        mainArea.addView(fourthRowSpace, fourthRowSpaceLayout);        
    	
        LayoutParams fithRowLeftSpaceLayout = new LayoutParams(row5, col1); //5th row left space
        fithRowLeftSpaceLayout.width = (windowWidth / 10) / 3;
        fithRowLeftSpaceLayout.height = (windowHeight / 2) / 6;
        Space fithRowLeftSpace = new Space(mainArea.getContext());
        fithRowLeftSpace.setLayoutParams(fithRowLeftSpaceLayout);
        mainArea.addView(fithRowLeftSpace, fithRowLeftSpaceLayout);
        
        LayoutParams fithRowTextViewLayout = new LayoutParams(row5, col2); //Password label
        fithRowTextViewLayout.width = (windowWidth / 10) * 3;
        fithRowTextViewLayout.height = (windowHeight / 2) / 6;
        TextView passwordLabel = new TextView(mainArea.getContext());
        passwordLabel.setLayoutParams(fithRowTextViewLayout);
        passwordLabel.setText("Password:");
        passwordLabel.setTextSize(14);
        passwordLabel.setGravity(Gravity.CENTER);
        mainArea.addView(passwordLabel, fithRowTextViewLayout);
        
        LayoutParams passwordFieldLayout = new LayoutParams(row5, col3); //Password text box
        passwordFieldLayout.width = windowWidth / 2;
        passwordFieldLayout.height = (windowHeight / 2) / 6;
        passwordField = new EditText(mainArea.getContext());
        passwordField.setLayoutParams(passwordFieldLayout);
        passwordField.setBackgroundColor(Color.WHITE);
        passwordField.setTransformationMethod(PasswordTransformationMethod.getInstance());
        passwordField.setTextSize(12);
        mainArea.addView(passwordField, passwordFieldLayout);
        
        LayoutParams sixthRowSpaceLayout = new LayoutParams(row6, allcols); //6th row space
        sixthRowSpaceLayout.width = windowWidth;
        sixthRowSpaceLayout.height = (windowHeight / 2) / 4;
        Space sixthRowSpace = new Space(mainArea.getContext());
        sixthRowSpace.setLayoutParams(sixthRowSpaceLayout);
        mainArea.addView(sixthRowSpace, sixthRowSpaceLayout);      
        
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
        signInButton.setText("Sign in");
        signInButton.setBackgroundColor(Color.GRAY);
        signInButton.setOnClickListener(new OnClickListener(){
        	public void onClick(View arg0)
        	{
        		signIn();
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
        newUserButton.setText("New User");
        newUserButton.setBackgroundColor(Color.GRAY);
        newUserButton.setOnClickListener(new OnClickListener(){
        	public void onClick(View arg0)
        	{
        		newUser();
        	}
        });
        signInGrid.addView(newUserButton, newUserLayout);
        
        LayoutParams xx = new LayoutParams(row7, allcols);
        
        mainArea.addView(signInGrid, xx);
        
        LayoutParams eigthRowSpaceLayout = new LayoutParams(row8, allcols); //8th row space
        eigthRowSpaceLayout.width = (windowWidth / 10);
        eigthRowSpaceLayout.height = (windowHeight / 2) / 2;
        Space eigthRowSpace = new Space(mainArea.getContext());
        eigthRowSpace.setLayoutParams(eigthRowSpaceLayout);
        mainArea.addView(eigthRowSpace, eigthRowSpaceLayout); 
        
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
	//removes the MainLoginScreen from view
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
	//appear on screen as the MainLoginScreen is positioned starting at the bottom
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
	//overall positional layout of where the login screen will go on the screen.
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
