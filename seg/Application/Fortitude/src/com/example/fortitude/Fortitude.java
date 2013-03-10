package com.example.fortitude;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
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

		FileSave fs = new FileSave();
		String username = fs.OpenFileDialog("username");
		String password = fs.OpenFileDialog("password");
		if((username == null) || (password == null))
		{
			new SplashScreen();
			Thread thread = new Thread(new Runnable() {
				public void run()
				{
					try
					{
						Thread.sleep(3000);
					}
					catch(Exception e)
					{
						//do nothing...
					}
					Fortitude.getFortitude().runOnUiThread(new Runnable() {
						public void run()
						{
							SplashScreen.getMe().killMe();
							new MainLoginScreen();
						}
					});
				}
			});
			thread.start();
		}
		else if((username.equals("")) || (password.equals("")))
		{
			new SplashScreen();
			Thread thread = new Thread(new Runnable() {
				public void run()
				{
					try
					{
						Thread.sleep(3000);
					}
					catch(Exception e)
					{
						//do nothing...
					}
					Fortitude.getFortitude().runOnUiThread(new Runnable() {
						public void run()
						{
							SplashScreen.getMe().killMe();
							new MainLoginScreen();
						}
					});
				}
			});
			thread.start();
		}
		else
		{
			new SplashScreen();
			Thread thread = new Thread(new Runnable() {
				public void run()
				{
					try
					{
						Thread.sleep(3000);
					}
					catch(Exception e)
					{
						//do nothing...
					}
					Fortitude.getFortitude().runOnUiThread(new Runnable() {
						public void run()
						{
							FileSave fs = new FileSave();
							String username = fs.OpenFileDialog("username");
							String password = fs.OpenFileDialog("password");
							ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Connecting To Server", false));
							ServerRequests.createSession(username, password, true);
						}
					});
				}
			});
			thread.start();
		}


		//TRANSFER UNITS!
		//ServerRequests.depositUnits("54.771125", "-1.568215", "18", "-10");
		//while(!ServerRequests.getStaticDepositComplete())
		//{
		//    //wait	
		//}
		//if(ServerRequests.getStaticDepositSuccess())
		//{
		//	MessageBox.newMsgBox(ServerRequests.getStaticOutputMessage(), true);
		//}

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

	public static boolean isWifiavailable()
	{
		try
		{
			ConnectivityManager connectivityManager = (ConnectivityManager) Fortitude.getFortitude().getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if(networkInfo == null)
			{
				return false;
			}
			return networkInfo.isAvailable();
		}
		catch(Exception e)
		{
			System.out.println("Cannot check if wifi is enabled " + e.toString());
			return false;
		}
	}
	
	public static void enableWifi()
	{
		try
		{
			WifiManager wifi = (WifiManager) Fortitude.getFortitude().getSystemService(Context.WIFI_SERVICE);
			wifi.setWifiEnabled(true);
		}
		catch(Exception e)
		{
			//oh well
		}
	}
}