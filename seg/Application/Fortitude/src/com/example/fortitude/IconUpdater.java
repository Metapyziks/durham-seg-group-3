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
									boolean canPlaceMarker = true;
									for(Marker marker : TheMap.getMe().getMarkers())
									{
										double latlat;
										double lonlon;
										latlat = Math.abs(marker.getPosition().latitude - latitude);
										lonlon = Math.abs(marker.getPosition().longitude - longitude);
										if((lonlon < 0.00028) && (latlat < 0.00028))
										{
											onLocation = true;
										}
										if((lonlon < 0.00300) && (latlat < 0.00300))
										{
											canPlaceMarker = false;
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
									if(canPlaceMarker)
									{
										if(MainScreen.getMe() != null)
										{
											if(MainScreen.getMe().getFlagIcon() != null)
											{
												MainScreen.getMe().getFlagIcon().setImageResource(R.drawable.flag);
												MainScreen.setFlagClickable(true);
											}
										}
									}
									else
									{
										if(MainScreen.getMe() != null)
										{
											if(MainScreen.getMe().getFlagIcon() != null)
											{
												MainScreen.getMe().getFlagIcon().setImageResource(R.drawable.flag_grey);
												MainScreen.setFlagClickable(true);
											}
										}									
									}
								}
								if(MainScreen.getMe() != null)
								{
									if(NotificationManager.isUnread())
									{
                                        MainScreen.getMe().getMailIcon().setImageResource(R.drawable.mail_alert);
									}
									else
									{
										MainScreen.getMe().getMailIcon().setImageResource(R.drawable.mail);
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
						xx++;
						if(xx > 10)
						{
							Fortitude.getFortitude().runOnUiThread(new Runnable() {
								public void run()
								{
									MessageBox.newMsgBox("IconUpdater stopped running :(", true);
								}
							});
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
