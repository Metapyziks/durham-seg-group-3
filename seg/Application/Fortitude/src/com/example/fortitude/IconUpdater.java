package com.example.fortitude;

import java.lang.InterruptedException;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.view.View;

import com.google.android.gms.maps.model.Marker;
import java.util.ArrayList;

public class IconUpdater 
{
	public static boolean shouldIRun;
	public static IconUpdater me;
	public static int wifiEnableCounter;
	public static int wifiScanCounter;

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
		wifiEnableCounter = 0;
		wifiScanCounter = 0;
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
												MainScreen.setFlagClickable(false);
											}
										}									
									}
								}
								if(MainScreen.getMe() != null)
								{
									if(CurrentUser.getMe().isVerified())
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
								wifiEnableCounter++;
								if(wifiEnableCounter > 900)
								{
									wifiEnableCounter = 0;
									if(!Fortitude.isWifiavailable())
									{
										Fortitude.enableWifi();
									}
								}
								wifiScanCounter++;
								if(wifiScanCounter > 10)
								{
									wifiScanCounter = 0;
									boolean foundSpecialCache = false;
									for(String address : MACManager.getMacs())
									{
										for(String mac : getMACs())
										{
											if(mac.equals(address))
											{
												foundSpecialCache = true;
												break;
											}
										}
									}
									if(foundSpecialCache)
									{
										MainScreen.setStarClickable(true);
										if(MainScreen.getMe() != null)
										{
											MainScreen.getMe().getStarIcon().setVisibility(View.VISIBLE);
											//MainScreen.getMe().getStarIcon().setImageResource();
										}
									}
									else
									{
										MainScreen.setStarClickable(false);
										if(MainScreen.getMe() != null)
										{
											MainScreen.getMe().getStarIcon().setVisibility(View.INVISIBLE);
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

	public static ArrayList<String> getMACs()
	{
		ArrayList<String> macs = new ArrayList<String>();
		try
		{
			WifiManager wifi = (WifiManager) Fortitude.getFortitude().getSystemService(Context.WIFI_SERVICE);
			wifi.startScan();
			for(ScanResult sc : wifi.getScanResults())
			{
				macs.add(sc.BSSID);
			}
			return macs;
		}
		catch(Exception e)
		{
			return macs;
		}
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