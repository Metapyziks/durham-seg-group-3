package com.example.fortitude;

import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridLayout.LayoutParams;
import android.widget.GridLayout.Spec;
import android.widget.Space;
import android.graphics.Color;
import android.widget.TextView;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View;
import android.graphics.Paint;
import java.lang.Math;

//Class to display a message box on the screen that is capable
//of displaying 20-40 characters of text.
//
//This class extends the gridlayout as this class adds itself to the screen.
public class MessageBox extends GridLayout
{
	
	private int windowWidth; //Visible window width
	private int windowHeight; //Visible window height
	
	//row layouts
    private Spec row1 = GridLayout.spec(0);
    private Spec row2 = GridLayout.spec(1);
    private Spec row3 = GridLayout.spec(2);
    private Spec row4 = GridLayout.spec(3);
    
    //column layouts
    private Spec col1 = GridLayout.spec(0);
    private Spec col2 = GridLayout.spec(1);
    private Spec col3 = GridLayout.spec(2);
    private Spec col4 = GridLayout.spec(3);
    private Spec col5 = GridLayout.spec(4);
    
    private Spec textAreaBoxCol = GridLayout.spec(1,3); //definition of number of columns for the TextView to go across
    private Spec textBreaker = GridLayout.spec(0,4); //row between button and text
    
    private static MessageBox iExist = null;//a static variable to store whether an instance of this class currrently exists or not.
    
    private Boolean haveOkButton = true;
    
    private Space positionalSpace = null;
    
    ////////
    //
    //newMsgBox
    //
    //static constructor
    //
    ////////
    public static MessageBox newMsgBox(String messageToDisplay, Boolean okButton)
    {
	    if(MessageBox.activeMessageBox())
	    {
	    	return null;
	    }
	    
	    disableAllTheChildren(Fortitude.getFortitude().getWindow().getDecorView());
	    
        return new MessageBox(messageToDisplay, okButton);
    }
    
    ////////
    //
    //Constructor
    //
    ////////
	private MessageBox(String messageToDisplay, Boolean okButton)
	{
	    super(Fortitude.getFortitude());	    
	    
	    haveOkButton = okButton;
	    
	    iExist = this;
	    
	    setWindowDimensions();
	    setColsAndRows();

	    createPositionalSpace();
	    
	    addMessageBox(messageToDisplay);
        
	    addThisToDisplay();
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
	//addMessageBox
	//
	//adds the message box to the cell positioned by the method "createPositionalSpace"
	//
	////////
	private void addMessageBox(String messageToDisplay)
	{
        GridLayout boxArea = new GridLayout(this.getContext()); //The gridlayout for the layout of the messagebox itself
        boxArea.setColumnCount(5);
        boxArea.setRowCount(5);
        boxArea.setBackgroundColor(Color.RED);
        addLayoutToMessageBox(boxArea, messageToDisplay);
	}
	
	////////
	//
	//addLayoutToMessageBox
	//
	//adds all the relevant layout to the given messagebox
	//
	////////
	private void addLayoutToMessageBox(GridLayout msgBox, String messageToDisplay)
	{
        LayoutParams row1col1Layout = new LayoutParams(row1, col1); //Top left Space
        row1col1Layout.width = windowWidth / 10;
        row1col1Layout.height = windowHeight / 10;
        Space row1col1 = new Space(msgBox.getContext());
        row1col1.setLayoutParams(row1col1Layout);
        msgBox.addView(row1col1, row1col1Layout);
        
        LayoutParams row1col2Layout = new LayoutParams(row1, col2); //Top left right Space
        row1col2Layout.width = windowWidth / 10;
        row1col2Layout.height = windowHeight / 10;
        Space row1col2 = new Space(msgBox.getContext());
        row1col2.setLayoutParams(row1col2Layout);
        msgBox.addView(row1col2, row1col2Layout);
        
        LayoutParams row1col3Layout = new LayoutParams(row1, col3); //Top middle Space (MAYBE TITLE SUCH AS "Alert" or "Message" TO BE ADDED)
        row1col3Layout.width = ((windowWidth / 10) * 4);
        row1col3Layout.height = windowHeight / 10;
        Space row1col3 = new Space(msgBox.getContext());
        row1col3.setLayoutParams(row1col3Layout);
        msgBox.addView(row1col3, row1col3Layout);
        
        LayoutParams row1col4Layout = new LayoutParams(row1, col4); //Top right left Space
        row1col4Layout.width = (windowWidth / 10);
        row1col4Layout.height = windowHeight / 10;
        Space row1col4 = new Space(msgBox.getContext());
        row1col4.setLayoutParams(row1col4Layout);
        msgBox.addView(row1col4, row1col4Layout);
        
        LayoutParams row1col5Layout = new LayoutParams(row1, col5); //Top Right Space
        row1col5Layout.width = (windowWidth / 10);
        row1col5Layout.height = windowHeight / 10;
        Space row1col5 = new Space(msgBox.getContext());
        row1col5.setLayoutParams(row1col5Layout);
        msgBox.addView(row1col5, row1col5Layout);
        
        LayoutParams row2col1Layout = new LayoutParams(row2, col1); //Middle Left Space
        row2col1Layout.width = (windowWidth / 10);
        Space row2col1 = new Space(msgBox.getContext());
        row2col1.setLayoutParams(row2col1Layout);
        msgBox.addView(row2col1, row2col1Layout);
        
        LayoutParams row2TextLayout = new LayoutParams(row2, textAreaBoxCol); //Middle middle TEXT GOES HERE!!!
        Paint mPaint = new Paint();
        mPaint.setTextSize(14);
        row2TextLayout.width = ((windowWidth / 10) * 6); //TIDY ME UP YOU FOOL!!!!
        row2TextLayout.height = ((int)((float)(Math.ceil(mPaint.measureText(messageToDisplay, 0, messageToDisplay.length()) / row2TextLayout.width)))) * 16;
        if((row2TextLayout.height + ((windowWidth / 10) * 2.5)) > windowWidth) //If the height of the variable height message box goes below the screen then we don't want the ok button hidden so just set the height of the messagebox to the maximum height that is visible
        {
        	row2TextLayout.height = windowHeight - positionalSpace.getLayoutParams().height - ((int)((float)Math.ceil((windowHeight / 10) * 2.5)));
        	messageToDisplay = "walrus";
        }
        TextView messageCell = new TextView(msgBox.getContext());
        messageCell.setLayoutParams(row2TextLayout);
        messageCell.setText(messageToDisplay);
        messageCell.setTextSize(14);
        messageCell.setGravity(Gravity.CENTER);
        msgBox.addView(messageCell, row2TextLayout);
        
        LayoutParams row2col5Layout = new LayoutParams(row2, col5); //Middle right
        row2col5Layout.width = (windowWidth / 10);
        Space row2col5 = new Space(msgBox.getContext());
        row2col5.setLayoutParams(row2col5Layout);
        msgBox.addView(row2col5, row2col5Layout);
        
        LayoutParams row3Spacer = new LayoutParams(row3, textBreaker); //Entire blank row separating text and button
        row3Spacer.width = ((windowWidth / 10) * 6);
        row3Spacer.height = (windowHeight / 10) / 2;
        Space row3Space = new Space(msgBox.getContext());
        row3Space.setLayoutParams(row3Spacer);
        msgBox.addView(row3Space, row3Spacer);
        
        LayoutParams row4col1Layout = new LayoutParams(row4, col1); //Space left of space
        row4col1Layout.width = (windowWidth / 10);
        row4col1Layout.height = windowHeight / 10;
        Space row4col1 = new Space(msgBox.getContext());
        row4col1.setLayoutParams(row4col1Layout);
        msgBox.addView(row4col1, row4col1Layout);
        
        LayoutParams row4col2Layout = new LayoutParams(row4, col2); //Space left of button
        row4col2Layout.width = (windowWidth / 10);
        row4col2Layout.height = windowHeight / 10;
        Space row4col2 = new Space(msgBox.getContext());
        row4col2.setLayoutParams(row4col2Layout);
        msgBox.addView(row4col2, row4col2Layout);
        
        if(haveOkButton)
        {
        	LayoutParams row4col3Layout = new LayoutParams(row4, col3); //OK button
        	row4col3Layout.width = ((windowWidth / 10) * 4);
        	row4col3Layout.height = windowHeight / 10;
        	Button row4col3 = new Button(msgBox.getContext());
        	row4col3.setLayoutParams(row4col3Layout);
        	row4col3.setTextSize(14);
        	row4col3.setText("OK");
        	row4col3.setOnClickListener(new OnClickListener(){
        		public void onClick(View arg0)
        		{
        			killMe();
        		}
        	});
        	msgBox.addView(row4col3);
        }
        LayoutParams row4col4Layout = new LayoutParams(row4, col4); //Space right of button
        row4col4Layout.width = (windowWidth / 10);
        row4col4Layout.height = windowHeight / 10;
        Space row4col4 = new Space(msgBox.getContext());
        row4col4.setLayoutParams(row4col4Layout);
        msgBox.addView(row4col4, row4col4Layout);
        
        LayoutParams row4col5Layout = new LayoutParams(row4, col5); //Space right of space right of button
        row4col5Layout.width = (windowWidth / 10);
        row4col5Layout.height = windowHeight / 10;
        Space row4col5 = new Space(msgBox.getContext());
        row4col5.setLayoutParams(row4col5Layout);
        msgBox.addView(row4col5, row4col5Layout);
        
        LayoutParams messageLayout = new LayoutParams(row2, col2);
        
        addView(msgBox, messageLayout);
	}
	
	////////
	//
	//killMe
	//
	//removes the messagebox from view
	//
	////////
	public void killMe()
	{
		makeAllTheChildrenBetter(Fortitude.getFortitude().getWindow().getDecorView());
		iExist = null;
		removeAllViews();
	}
	
	////////
	//
	//activeMessageBox
	//
	//returns true if there is currently a message box being displayed
	//
	////////
	public static boolean activeMessageBox()
	{
		if(iExist == null)
		{
			return false;
		}
		else
		{
			return true;
		}
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
        spaceLayout.height = windowHeight / 4;
        positionalSpace = new Space(Fortitude.getFortitude());
        positionalSpace.setLayoutParams(spaceLayout);
        addView(positionalSpace, spaceLayout);
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
	//makeAllTheChildrenBetter
	//
	//opposite of "disableAllTheChildren", calls when finished to re enable all of the elements
	//
	////////
	private static void makeAllTheChildrenBetter(View v)
	{
		v.setEnabled(true);
		if(v instanceof ViewGroup)
		{
		    ViewGroup vg = ((ViewGroup) v);
		    int z = vg.getChildCount();
		    for(int i = 0; i < z; i++)
		    {
			    makeAllTheChildrenBetter(vg.getChildAt(i));
		    }
		}
	}
	
	////////
	//
	//disableAllTheChildren
	//
	//disable all  elements in the application that aren't this messagebox
	//
	////////
	private static void disableAllTheChildren(View v)
	{
		if(!(v instanceof MessageBox))
		{
		    v.setEnabled(false);
		}
		if(v instanceof ViewGroup)
		{
		    ViewGroup vg = ((ViewGroup) v);
		    int z = vg.getChildCount();
		    for(int i = 0; i < z; i++)
		    {
			    disableAllTheChildren(vg.getChildAt(i));
		    }
		}
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
