package com.example.fortitude;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Fortitude extends Activity 
{
    private static Fortitude fortitude = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        fortitude = this;
        this.setRequestedOrientation(1); //lock application to portrait
    	GUI.setUpActivityGraphics();
        runApp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.activity_fortitude, menu);
        return true;
    }
    
    ////////
    //
    //runApp
    //
    //main method that is run when the activity is created
    //
    ////////
    private void runApp()
    {
        //new LoginScreen();
    	Login.createUser("marypoppins", "sugar", "marypoppins@london.com");
    	//Login.logIn("MaryPoppins", "sugar");
    }
    
    ////////
    //
    //getFortitude
    //
    //returns the instance of the activity. This is primarily used for gui methods as all
    //gui elements are children of this activity.
    //
    ////////
    public static Fortitude getFortitude()
    {
        return fortitude;	
    }
}