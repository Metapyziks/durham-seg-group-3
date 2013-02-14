package com.example.fortitude;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.content.Context;
import android.content.Intent;

import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.GoogleMap;

public class Fortitude extends Activity
{
    private static Fortitude fortitude = null;
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        //setContentView(R.layout.activity_fortitude);
        //GoogleMap mMap;
        //mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        runApp();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.activity_fortitude, menu);
        return true;
    }
    
    @Override
    public void onBackPressed()
    {
    	Intent startMain = new Intent(Intent.ACTION_MAIN);
    	startMain.addCategory(Intent.CATEGORY_HOME);
    	startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	startActivity(startMain);
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
        fortitude = this;
        this.setRequestedOrientation(1); //lock application to portrait
    	GUI.setUpActivityGraphics();
    	
    	new MainLoginScreen();
    	
    	//ServerRequests.getGoogleMapRoute("54.773366,-1.575928", "54.709945,-1.508636");
    	
    	//http://maps.googleapis.com/maps/api/directions/json?origin=54.773366,-1.575928&destination=54.709945,-1.508636&sensor=true
    	
        /////////////////////////////////////////////// Create Login Screen Example
        //new LoginScreen();
        ///////////////////////////////////////////////
    	
        /////////////////////////////////////////////// Create User Example
    	//Login.createUser("marypoppins", "sugar", "marypoppins@london.com");
        ///////////////////////////////////////////////
    	
        /////////////////////////////////////////////// Log User In Example
    	//Login.logIn("MaryPoppins", "sugar"); 
        ///////////////////////////////////////////////
    	
    	/////////////////////////////////////////////// Get User Info Example
    	//ServerRequests.getUserInfo("marypoppins"); 
    	//Thread thread = new Thread(new Runnable() {
    	//	public void run()
    	//	{
    	//		while(!(ServerRequests.getGetUserInfoComplete()))
    	//		{
    	//			
    	//		}
    	//		if(ServerRequests.getGetUserInfoSuccess())
    	//		{
    	//			Fortitude.getFortitude().runOnUiThread(new Runnable() {
    	//				public void run()
    	//				{
    	//					MessageBox.newMsgBox(ServerRequests.getStaticUserInfo().get(0).getUserName(), true);
    	//				}
    	//			});
    	//		}
    	//	}
    	//});
    	//thread.start();
        /////////////////////////////////////////////// 
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
    
    ////////
    //
    //getFortitude
    //
    //detects whether the phone has an internet connection
    //
    ////////
    public static boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) Fortitude.getFortitude().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return (activeNetworkInfo != null);
    }
}