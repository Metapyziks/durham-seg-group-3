package com.example.fortitude;

import android.content.Context;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.GridLayout.LayoutParams;
import android.widget.GridLayout.Spec;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.GoogleMap;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import android.graphics.Color;
import android.view.Gravity;
import java.util.ArrayList;

public class TheMap extends GridLayout
{	
	private Spec row1 = GridLayout.spec(0);

	private Spec col1 = GridLayout.spec(0);

	private GoogleMap googleMap = null; //googlemap options class

	View mapView; //The container of the googlemaps element

	private static boolean gotInitialLocation = false; //don't touch me...

	private static TheMap me = null; //holds the last created of this instance

	private static boolean freeToGetCaches;

	private static TextView gettingLocationTextView;

	private static ArrayList<Marker> markers = new ArrayList<Marker>();

	////////
	//
	//private constructor
	//
	////////
	public static TheMap newTheMap(Context context)
	{
		if(me == null)
		{
			return (new TheMap(context));
		}
		return null;
	}

	////////
	//
	//Constructor
	//
	////////
	private TheMap(Context context)
	{
		super(context);
		me = this;
		freeToGetCaches = true;

		LayoutInflater factory = LayoutInflater.from(Fortitude.getFortitude()); //create the map and add it to this view
		mapView = (factory.inflate(R.layout.maplayout, null));
		this.addView(mapView);
		googleMap = ((MapFragment) Fortitude.getFortitude().getFragmentManager().findFragmentById(R.id.map)).getMap();

		googleMap.getUiSettings().setZoomControlsEnabled(false); //disable default zoom controls
		googleMap.getUiSettings().setMyLocationButtonEnabled(false); //disable default my location button
		googleMap.setMyLocationEnabled(true); //tell googlemaps to get and display my location

		Thread thread = new Thread(new Runnable() { //thread that runs on initial set up and displays the users
			public void run()                       //location asap
			{
				while(!getGotInitialLocation())
				{
					Fortitude.getFortitude().runOnUiThread(new Runnable() {
						public void run()
						{
							Location xx = TheMap.getMe().getGoogleMap().getMyLocation();
							if(xx != null)
							{
								setGotInitialLocation(true);
								TheMap.getMe().zoomToMyPosition();
								updateCachePositions();
							}
						}
					});
					try
					{
						Thread.sleep(1000);
					}
					catch(Exception e)
					{

					}
				}
			}
		});
		thread.start();
	}

	public void updateCachePositions()
	{
		Fortitude.getFortitude().runOnUiThread(new Runnable() {
			public void run()
			{
				ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Connecting To Server", false));
			}
		});a

		updateMap();
	}

	private void updateMap()
	{
		ServerRequests.getNearbyCaches(CurrentUser.getMe().getUserName(), CurrentUser.getMe().getSessionID(), Double.toString(TheMap.getMe().getGoogleMap().getCameraPosition().target.latitude), Double.toString(TheMap.getMe().getGoogleMap().getCameraPosition().target.longitude), "5000");
		Thread thread = new Thread(new Runnable() {
			public void run()
			{
				while(ServerRequests.getGetNearbyCachesInfoStatus() == 0)
				{
					// do nothing
				}
				if(ServerRequests.getGetNearbyCachesInfoStatus() == 2)
				{
					Fortitude.getFortitude().runOnUiThread(new Runnable() {
						public void run()
						{
							ArrayList<Cache> caches = ServerRequests.getNearbyCaches();

							if(caches == null)
							{
								return;
							}
							for(Marker marker: TheMap.getMe().getMarkers())
							{
								marker.remove();
							}

							TheMap.getMe().setMarkers(new ArrayList<Marker>());
							for(Cache cache: caches)
							{
								if(cache.getGarrison().equals("-1"))
								{
									TheMap.getMe().getMarkers().add(TheMap.getMe().getGoogleMap().addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(cache.getLat()), Double.parseDouble(cache.getLon()))).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))));
								}
								if(!cache.getGarrison().equals("-1"))
								{
									TheMap.getMe().getMarkers().add(TheMap.getMe().getGoogleMap().addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(cache.getLat()), Double.parseDouble(cache.getLon()))).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))));
								}
							}
							if(ServerRequests.getTheMessageBox() != null)
							{
								ServerRequests.getTheMessageBox().killMe();
							}
						}
					});
				}
			}
		});
		thread.start();
	}

	public static boolean getFreeToGetCaches()
	{
		return freeToGetCaches;
	}

	public static void setFreeToGetCaches(boolean x)
	{
		freeToGetCaches = x;
	}

	////////
	//
	//getGotInitialLocation
	//
	//used by the initial display user location thread
	//
	////////
	public static boolean getGotInitialLocation()
	{
		return gotInitialLocation;
	}

	////////
	//
	//getGotInitialLocation
	//
	//used by the initial display user location thread
	//
	////////
	public static void setGotInitialLocation(boolean x)
	{
		gotInitialLocation = x;
	}

	////////
	//
	//zoomToMyPosition
	//
	//if it is possible to get the users current location the camera moves to it and zooms to a default level
	//
	////////
	public void zoomToMyPosition()
	{
		if(googleMap.getMyLocation() != null)
		{
			googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(googleMap.getMyLocation().getLatitude(), googleMap.getMyLocation().getLongitude()), 14));
		}
		else
		{
			if(TheMap.getGotInitialLocation())
			{
				Fortitude.getFortitude().runOnUiThread(new Runnable() {
					public void run()
					{
						MessageBox.newMsgBox("Unable To Find Your Location", true);
					}
				});
			}
		}
	}

	////////
	//
	//zoomToMyPositionAtMyZoom
	//
	//if it is possible to get the users current location the camera moves to it and without zooming in or out
	//
	////////
	public void zoomToMyPositionAtMyZoom()
	{
		if(googleMap.getMyLocation() != null)
		{
			googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(googleMap.getMyLocation().getLatitude(), googleMap.getMyLocation().getLongitude()), googleMap.getCameraPosition().zoom));
		}
	}

	////////
	//
	//getMe
	//
	//returns the last created instance of this class, could be null
	//
	////////
	public static TheMap getMe()
	{
		return me;
	}

	////////
	//
	//removeMeFromMyParent
	//
	//removes this view from its parent so it can be reused later
	//
	////////
	public void removeMeFromMyParent()
	{
		((ViewGroup)this.getParent()).removeView(this);
	}

	////////
	//
	//getGoogleMap
	//
	//returns the googlemaps class from the google map
	//
	////////
	public GoogleMap getGoogleMap()
	{
		return googleMap;
	}

	////////
	//
	//getMapView
	//
	//returns the view that contains the googlemaps
	//
	////////
	public View getMapView()
	{
		return mapView;
	}

	public TextView getGettingLocationTextView()
	{
		return gettingLocationTextView;
	}

	public synchronized ArrayList<Marker> getMarkers()
	{
		return markers;
	}

	public synchronized void setMarkers(ArrayList<Marker> newMarkers)
	{
		markers = newMarkers;
	}
}
