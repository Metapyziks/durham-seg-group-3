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


public class LoginScreen extends GridLayout
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
    private Spec col6 = GridLayout.spec(5);

    private Spec allcols = GridLayout.spec(0,5);
    
    private EditText usernameField = null; //username text box
    private EditText passwordField = null; //password text box
    
	////////
	//
	//newLoginScreen
	//
	//static constructor
	//
	////////
	public static void newLoginScreen()
	{
	    new LoginScreen();	
	}
	
	////////
	//
	//Constructor
	//
	////////
    public LoginScreen()
    {
    	super(Fortitude.getFortitude());
    	
    	setWindowDimensions();
    	setColsAndRows();
    	
    	createPositionalSpace();
    	
    	addLoginScreen();
    	
    	addThisToDisplay();
    }
    
    private void signIn()
    {
    	MessageBox mb = MessageBox.newMsgBox("Connecting", false);
    	RequestThread rt = new RequestThread("createUser");
    	rt.start();
    	
    }
    
    ////////
    //
    //addLoginScreen
    //
    //create and add login screen to this grid
    //
    ////////
    private void addLoginScreen()
    {
        GridLayout mainArea = new GridLayout(this.getContext()); //The gridlayout for the layout of the messagebox itself
        mainArea.setColumnCount(8);
        mainArea.setRowCount(8);
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
        firstRowTextViewLayout.width = (windowWidth / 10) * 8;
        firstRowTextViewLayout.height = (windowHeight / 2) / 6;
        TextView firstRowTextView = new TextView(mainArea.getContext());
        firstRowTextView.setLayoutParams(firstRowTextViewLayout);
        firstRowTextView.setText("Please Sign In!");
        firstRowTextView.setTextSize(14);
        firstRowTextView.setGravity(Gravity.CENTER);
        firstRowTextView.setBackgroundColor(Color.RED);
        firstRowTextView.setGravity(Gravity.CENTER);
        mainArea.addView(firstRowTextView, firstRowTextViewLayout);
        
        LayoutParams secondRowSpaceLayout = new LayoutParams(row2, allcols); //2nd row space
        secondRowSpaceLayout.width = (windowWidth / 10);
        secondRowSpaceLayout.height =  (windowHeight / 2) / 6;
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
        
        LayoutParams usernameFieldLayout = new LayoutParams(row3, col4); //Username text box
        usernameFieldLayout.width = (windowWidth / 10) * 4;
        usernameFieldLayout.height = (windowHeight / 2) / 6;
        usernameField = new EditText(mainArea.getContext());
        usernameField.setLayoutParams(usernameFieldLayout);
        usernameField.setBackgroundColor(Color.WHITE);
        usernameField.setTextSize(12);
        mainArea.addView(usernameField, usernameFieldLayout);
        
        LayoutParams thirdRowRightSpaceLayout = new LayoutParams(row3, col5); //3rd row right space
        thirdRowRightSpaceLayout.width = (windowWidth / 10) - ((windowWidth / 10) / 3);
        thirdRowRightSpaceLayout.height = (windowHeight / 2) / 6;
        Space thirdRowRightSpace = new Space(mainArea.getContext());
        thirdRowRightSpace.setLayoutParams(thirdRowRightSpaceLayout);
        mainArea.addView(thirdRowRightSpace, thirdRowRightSpaceLayout);
        
        LayoutParams fourthRowSpaceLayout = new LayoutParams(row4, allcols); //4th row space
        fourthRowSpaceLayout.width = (windowWidth / 10);
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
        
        LayoutParams passwordFieldLayout = new LayoutParams(row5, col4); //Password text box
        passwordFieldLayout.width = (windowWidth / 10) * 4;
        passwordFieldLayout.height = (windowHeight / 2) / 6;
        passwordField = new EditText(mainArea.getContext());
        passwordField.setLayoutParams(passwordFieldLayout);
        passwordField.setBackgroundColor(Color.WHITE);
        passwordField.setTransformationMethod(PasswordTransformationMethod.getInstance());
        passwordField.setTextSize(12);
        mainArea.addView(passwordField, passwordFieldLayout);
        
        LayoutParams fithRowRightSpaceLayout = new LayoutParams(row5, col5); //5th row right space
        fithRowRightSpaceLayout.width = (windowWidth / 10) - ((windowWidth / 10) / 3);
        fithRowRightSpaceLayout.height = (windowHeight / 2) / 6;
        Space fithRowRightSpace = new Space(mainArea.getContext());
        fithRowRightSpace.setLayoutParams(fithRowRightSpaceLayout);
        mainArea.addView(fithRowRightSpace, fithRowRightSpaceLayout);
        
        LayoutParams sixthRowSpaceLayout = new LayoutParams(row6, allcols); //6th row space
        sixthRowSpaceLayout.width = (windowWidth / 10);
        sixthRowSpaceLayout.height = (windowHeight / 2) / 6;
        Space sixthRowSpace = new Space(mainArea.getContext());
        sixthRowSpace.setLayoutParams(sixthRowSpaceLayout);
        mainArea.addView(sixthRowSpace, sixthRowSpaceLayout);      
        
        GridLayout signInGrid = new GridLayout(mainArea.getContext());
        signInGrid.setRowCount(1);
        signInGrid.setColumnCount(3);
        
        LayoutParams signInButtonLeftSpaceLayout = new LayoutParams(row1, col1); //space left of sign in button
        signInButtonLeftSpaceLayout.width = (windowWidth - ((windowWidth / 10) * 2)) / 3;
        signInButtonLeftSpaceLayout.height = (windowHeight / 2) / 6;
        Space signInButtonLeftSpace = new Space(mainArea.getContext());
        signInButtonLeftSpace.setLayoutParams(signInButtonLeftSpaceLayout);
        signInGrid.addView(signInButtonLeftSpace, signInButtonLeftSpaceLayout);
        
        LayoutParams signInLayout = new LayoutParams(row1, col2); //Sign in button
        signInLayout.width = (windowWidth - ((windowWidth / 10) * 2)) / 3;
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
        
        LayoutParams signInButtonRightSpaceLayout = new LayoutParams(row1, col3); //space left of sign in button
        signInButtonRightSpaceLayout.width = 50;
        signInButtonRightSpaceLayout.height = (windowHeight / 2) / 6;
        Space signInButtonRightSpace = new Space(mainArea.getContext());
        signInButtonRightSpace.setLayoutParams(signInButtonRightSpaceLayout);
        signInGrid.addView(signInButtonRightSpace, signInButtonRightSpaceLayout);
        
        LayoutParams xx = new LayoutParams(row7, allcols);
        
        mainArea.addView(signInGrid, xx);
        
        LayoutParams eigthRowSpaceLayout = new LayoutParams(row8, allcols); //8th row space
        eigthRowSpaceLayout.width = (windowWidth / 10);
        eigthRowSpaceLayout.height = (windowHeight / 2) / 6;
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
	//removes the messagebox from view
	//
	////////
	private void killMe()
	{
		this.removeAllViews();
	}
	
	////////
	//
	//createPositionalSpace
	//
	//Creates an invisible square in the top left corner of the screen.
	//The width and height of this square define where the message box will
	//appear on screen as the messagebox is positioned starting at the bottom
	//right hand corner of this square.
	//
	////////
	private void createPositionalSpace()
	{
        LayoutParams spaceLayout = new LayoutParams(row1, col1); //Top left to dictate main layout
        spaceLayout.width = windowWidth / 10;
        spaceLayout.height = windowHeight / 6;
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
