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
    
    private void runApp()
    {
        new LoginScreen();
    }
    
    public static Fortitude getFortitude()
    {
        return fortitude;	
    }
}