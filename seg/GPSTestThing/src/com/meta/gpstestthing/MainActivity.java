package com.meta.gpstestthing;

import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.location.LocationListener;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

public class MainActivity extends Activity
{
	private class GPSLocationListener implements LocationListener
	{
		private MainActivity myContext;
		
		public GPSLocationListener( MainActivity context )
		{
			myContext = context;
		}
		
		public void onLocationChanged( Location location )
		{
			myContext.displayText( "Latitude: " + location.getLatitude() + "\nLongitude: " + location.getLongitude());
		}

		public void onProviderDisabled( String provider )
		{
			myContext.println( "provider \"" + provider + "\" disabled." );			
		}

		public void onProviderEnabled( String provider )
		{
			myContext.println( "provider \"" + provider + "\" enabled." );
		}

		public void onStatusChanged( String provider, int status, Bundle extras )
		{
			// TODO Auto-generated method stub
			
		}
	}
	
	private TextView myTextView;
	private LocationManager myLocManager;
	private GPSLocationListener myLocListener;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		myTextView = new TextView( this );
		this.setContentView( myTextView );
		
		try
		{			
			myLocManager = (LocationManager) this.getSystemService( Context.LOCATION_SERVICE );
			
			LocationProvider provider = myLocManager.getProvider( LocationManager.GPS_PROVIDER );
			
			myLocListener = new GPSLocationListener( this );
			myLocManager.requestLocationUpdates( provider.getName(), 500, 1, myLocListener );
			displayText("Getting coordinates...");
		}
		catch( Exception ex )
		{
			println( "It done broked: " + ex.toString() );
			for( StackTraceElement el : ex.getStackTrace() )
			{
				println( el.toString() );
			}
		}
	}
	
	public void displayText( String display )
	{
		myTextView.setText( display );
	}
	
	public void println( String line )
	{
		myTextView.setText( myTextView.getText() + line + "\n" );
	}
}
