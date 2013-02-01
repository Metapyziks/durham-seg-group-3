package com.example.fortitude;

import java.lang.InterruptedException;
import com.google.android.gms.maps.model.Marker;

public class IconUpdater 
{
	public static boolean shouldIRun;
	public static IconUpdater me;

	public static IconUpdater newIconUpdater()
	{
		if(me == null)
		{
			return (new IconUpdater());
		}
		else
		{
			return me;
		}
	}
	
	private IconUpdater()
	{
		shouldIRun = true;
		me = this;
		runUpdater();
	}

	public void runUpdater()
	{
		Thread thread = new Thread(new Runnable() {
			public void run()
			{
				int xx = 0;
				while(IconUpdater.getShouldIRun())
				{
					try
					{
						Thread.sleep(1000);
						Fortitude.getFortitude().runOnUiThread(new Runnable() {
							public void run()
							{
								if(TheMap.getMe().getGoogleMap().getMyLocation() != null)
								{
									double latitude = TheMap.getMe().getGoogleMap().getMyLocation().getLatitude();
									double longitude = TheMap.getMe().getGoogleMap().getMyLocation().getLongitude();
									boolean onLocation = false;
									for(Marker marker : TheMap.getMe().getMarkers())
									{
										double latlat;
										double lonlon;
										if(marker.getPosition().latitude > latitude)
										{
											latlat = marker.getPosition().latitude - latitude;
										}
										else
										{
											latlat = latitude - marker.getPosition().latitude;
										}
										if(marker.getPosition().longitude > longitude)
										{
											lonlon = marker.getPosition().longitude - longitude;
										}
										else
										{
											lonlon = longitude - marker.getPosition().longitude;
										}
										if((lonlon < 0.00013) && (latlat < 0.00013))
										{
											System.out.println(lonlon + " " + latlat);
											onLocation = true;
										}
									}
									if(onLocation)
									{
										if(MainScreen.getMe() != null)
										{
											if(MainScreen.getMe().getCastleIcon() != null)
											{
												MainScreen.getMe().getCastleIcon().setImageResource(R.drawable.castle);
												MainScreen.setCastleClickable(true);
											}
										}
									}
									else
									{
										if(MainScreen.getMe() != null)
										{
											if(MainScreen.getMe().getCastleIcon() != null)
											{
												MainScreen.getMe().getCastleIcon().setImageResource(R.drawable.castle_grey);
												MainScreen.setCastleClickable(false);
											}
										}
									}
								}
							}
						});
					}
					catch(InterruptedException e)
					{

					}
					catch(Exception e)
					{
						System.out.println(e.toString());
						xx++;
						if(xx > 10)
						{
							return;
						}
					}
				}
			}
		});
		thread.start();
	}

	public static synchronized boolean getShouldIRun()
	{
		return shouldIRun;
	}

	public static synchronized void setShouldIRun(boolean x)
	{
		shouldIRun = x;
	}
}
