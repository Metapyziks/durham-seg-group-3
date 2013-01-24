package com.example.fortitude;

import android.widget.GridLayout;
import android.widget.GridLayout.LayoutParams;
import android.widget.GridLayout.Spec;
import android.widget.Space;

public class ForgottenDetailsScreen extends Window
{
	private Spec row1 = GridLayout.spec(0);
	private Spec row2 = GridLayout.spec(1);
	private Spec row3 = GridLayout.spec(2);
	private Spec row4 = GridLayout.spec(3);

	private Spec col1 = GridLayout.spec(0);
	private Spec col2 = GridLayout.spec(1);
	private Spec col3 = GridLayout.spec(2);
	private Spec col4 = GridLayout.spec(3);
	
	private Spec allcols = GridLayout.spec(0,2);
	
	TextField emailTextField;
	
	private static ForgottenDetailsScreen me = null;
	
	////////
	//
	//Constructor
	//
	////////
    public ForgottenDetailsScreen()
    {
    	super(R.drawable.forgotten_details);
    	me = this;
    	addContentToContentPane(createWindowPane());
    }
    
    private GridLayout createWindowPane()
    {
    	GridLayout mainArea = new GridLayout(super.getContext());
    	mainArea.setColumnCount(2);
    	mainArea.setRowCount(4);
    	
    	LayoutParams positionalSpaceLayout = new LayoutParams(row1, col1); //positional space top left
    	positionalSpaceLayout.width = (super.getWindowWidth() / 2) - (super.getWindowWidth() / 15);
    	positionalSpaceLayout.height = (super.getWindowHeight() / 2) + (super.getWindowHeight() / 40);
    	Space firstColumnSpace = new Space(mainArea.getContext());
    	firstColumnSpace.setLayoutParams(positionalSpaceLayout);
    	mainArea.addView(firstColumnSpace, positionalSpaceLayout);

		LayoutParams emailTextFieldImageLayout = new LayoutParams(row2, col2); //email text field image
		emailTextFieldImageLayout.width = super.getWindowWidth() / 2;
		emailTextFieldImageLayout.height = super.getWindowHeight() / 12;
		TextFieldImage emailTextFieldImage = new TextFieldImage();
		emailTextFieldImage.setLayoutParams(emailTextFieldImageLayout);
	    mainArea.addView(emailTextFieldImage, emailTextFieldImageLayout);
	    
	    LayoutParams emailTextFieldLayout = new LayoutParams(row2, col2); //email text field
	    emailTextFieldLayout.width = super.getWindowWidth() / 2;
	    emailTextFieldLayout.height = super.getWindowHeight() / 12;
	    emailTextField = new TextField();
	    emailTextField.setLayoutParams(emailTextFieldLayout);
	    mainArea.addView(emailTextField, emailTextFieldLayout);
	    
	    LayoutParams thirdRowSpaceLayout = new LayoutParams(row3, allcols); //third row spacer
	    thirdRowSpaceLayout.height = super.getWindowHeight() / 4;
	    Space thirdRowSpace = new Space(mainArea.getContext());
	    thirdRowSpace.setLayoutParams(thirdRowSpaceLayout);
	    mainArea.addView(thirdRowSpace, thirdRowSpaceLayout);
	    
	    GridLayout buttonGrid = new GridLayout(mainArea.getContext());
	    buttonGrid.setColumnCount(5);
	    buttonGrid.setRowCount(1);
	    
	    LayoutParams buttonLeftSpaceLayout = new LayoutParams(row1, col1);
		buttonLeftSpaceLayout.width = super.getWindowWidth() / 12;
		Space buttonLeftSpace = new Space(buttonGrid.getContext());
		buttonLeftSpace.setLayoutParams(buttonLeftSpaceLayout);
		buttonGrid.addView(buttonLeftSpace, buttonLeftSpaceLayout);
		
		LayoutParams sendEmailButtonLayout = new LayoutParams(row1, col2);
		sendEmailButtonLayout.width = (super.getWindowWidth() / 2) - (super.getWindowWidth() / 10);
		sendEmailButtonLayout.height = super.getWindowHeight() / 10;
		FortitudeButton sendEmailButton = (new FortitudeButton(R.drawable.send_email, R.drawable.send_email_pressed) {
			public void preClickActions()
			{
				
			}
			public void whenClicked()
			{
				makeResetPasswordRequest();
			}
		});
		sendEmailButton.setLayoutParams(sendEmailButtonLayout);
		buttonGrid.addView(sendEmailButton, sendEmailButtonLayout);
		
		LayoutParams middleButtonSpaceLayout = new LayoutParams(row1, col3);
		middleButtonSpaceLayout.width = super.getWindowWidth() / 15;
		Space middleButtonSpace = new Space(buttonGrid.getContext());
		middleButtonSpace.setLayoutParams(middleButtonSpaceLayout);
		buttonGrid.addView(middleButtonSpace, middleButtonSpaceLayout);
		
		LayoutParams cancelButtonLayout = new LayoutParams(row1, col4);
		cancelButtonLayout.width = (super.getWindowWidth() / 2) - (super.getWindowWidth() / 10);
		cancelButtonLayout.height = super.getWindowHeight() / 10;
		FortitudeButton cancelButton = (new FortitudeButton(R.drawable.cancel, R.drawable.cancel_pressed) {
			public void preClickActions()
			{
				
			}
			public void whenClicked()
			{
				ForgottenDetailsScreen.getMe().killMe();
				new MainLoginScreen();
			}
		});
		cancelButton.setLayoutParams(cancelButtonLayout);
		buttonGrid.addView(cancelButton, cancelButtonLayout);
	    
	    LayoutParams buttonGridLayout = new LayoutParams(row4, allcols);
	    mainArea.addView(buttonGrid, buttonGridLayout);
    	
    	return mainArea;
    }
    
    ////////
    //
    //makeRequestPasswordRequest
    //
    //is triggered when the "send email" button is pressed
    //
    ////////
    public void makeResetPasswordRequest()
    {
    	if(this.emailTextField.getText().toString().equals(""))
    	{
    		MessageBox.newMsgBox("No email address input", true);
    		return;
    	}
		ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Connecting To Server", false));
		ServerRequests.resetPassReset(this.emailTextField.getText().toString());
    }
    
    ////////
    //
    //allows other objects to get the current instance
    //of this window, if it exists.
    //
    ////////
    public static ForgottenDetailsScreen getMe()
    {
    	return me;
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
}
