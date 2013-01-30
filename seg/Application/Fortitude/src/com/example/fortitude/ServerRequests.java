package com.example.fortitude;

import java.net.URL;
import java.net.URLConnection;
import java.lang.StringBuilder;
import java.net.URLEncoder;
import java.net.ConnectException;
import json.*;
import java.util.ArrayList;

//A class that handles all requests to the server through static methods.
public class ServerRequests
{
	private static MessageBox theMessageBox = null;
	private static String staticUname = null;
	private static String staticPhash = null;
	private static String staticEmail = null;
	private static String staticOutputMessage = null;
	private static ArrayList<User> staticUserInfo = null;
	private static boolean getUserInfoComplete = false;
	private static boolean getUserInfoSuccess = false;
	private static boolean getUserBalanceComplete = false;
	private static boolean getUserBalanceSuccess = false;
	private static String staticUserBalance;
	private static String usersToGet = null;
	private static boolean staticInitialLogin;
	private static String staticSessionId;
	private static String staticTotalUnits;
	private static int getNearbyCachesInfoStatus;
	private static ArrayList<Cache> nearbyCaches;
	private static String staticCacheCount;
	private static String staticLon;
	private static String staticLat;
	private static String cacheRadius;

	////////
	//
	//Constructor
	//
	////////
	public ServerRequests()
	{

	}

	////////
	//
	//A series of static accessors and mutators to share resources between threads.
	//
	////////
	public static String getCacheRadius()
	{
		return cacheRadius;
	}

	public static void setCacheRadius(String x)
	{
		cacheRadius = x;
	}

	public static String getStaticLat()
	{
		return staticLat;
	}

	public static void setStaticLat(String x)
	{
		staticLat = x;
	}

	public static String getStaticSessionId()
	{
		return staticSessionId;
	}

	public static String getStaticLon()
	{
		return staticLon;
	}

	public static void setStaticLon(String x)
	{
		staticLon = x;
	}

	public static ArrayList<Cache> getNearbyCaches()
	{
		return nearbyCaches;
	}

	public static void setNearbyCaches(ArrayList<Cache> x)
	{
		nearbyCaches = x;
	}

	public static String getStaticCacheCount()
	{
		return staticCacheCount;
	}

	public static void setStaticCacheCount(String x)
	{
		staticCacheCount = x;
	}

	public static int getGetNearbyCachesInfoStatus()
	{
		return getNearbyCachesInfoStatus;
	}

	public static void setGetNearbyCachesInfoStatus(int x)
	{
		getNearbyCachesInfoStatus = x;
	}

	public static MessageBox getTheMessageBox()
	{
		return theMessageBox;
	}

	public static void setTheMessageBox(MessageBox x)
	{
		theMessageBox = x;
	}

	public static String getStaticTotalUnits()
	{
		return staticTotalUnits;
	}

	public static void setStaticTotalUnits(String x)
	{
		staticTotalUnits = x;
	}

	public static String getStaticUname()
	{
		return staticUname;
	}

	public static String getStaticPhash()
	{
		return staticPhash;
	}

	public static String getStaticOutputMessage()
	{
		return staticOutputMessage;
	}

	public static String getStaticEmail()
	{
		return staticEmail;
	}

	public static ArrayList<User> getStaticUserInfo()
	{
		return staticUserInfo;
	}

	public static boolean getGetUserInfoComplete()
	{
		return getUserInfoComplete;
	}

	public static String getUsersToGet()
	{
		return usersToGet;
	}

	public static void setGetUserInfoComplete(boolean x)
	{
		getUserInfoComplete = x;
	}

	public static boolean getGetUserInfoSuccess()
	{
		return getUserInfoSuccess;
	}

	public static void setGetUserInfoSuccess(boolean x)
	{
		getUserInfoSuccess = x;
	}

	public static void setStaticUserBalance(String x)
	{
		staticUserBalance = x;
	}

	public static String getStaticUserBalance()
	{
		return staticUserBalance;
	}

	public static void setGetUserBalanceSuccess(boolean x)
	{
		getUserBalanceSuccess = x;
	}

	public static boolean getGetUserBalanceSuccess()
	{
		return getUserBalanceSuccess;
	}

	public static void setGetUserBalanceComplete(boolean x)
	{
		getUserBalanceComplete = x;
	}

	public static boolean getGetUserBalanceComplete()
	{
		return getUserBalanceComplete;
	}

	////////
	//
	//createSession
	//
	//Creates a session on the server and saves the session hash to a file.
	//This hash will be able to be used as a means of authentication with
	//the server so the user doesn't have to keep logging in for every
	//action that requires authentication. This method effectively be
	//viewed as a 'Log in' method...
	//
	////////
	public static void createSession(String uname, String phash, boolean initialLogin)
	{
		staticInitialLogin = initialLogin;

		staticUname = uname;
		staticPhash = phash;

		Runnable runnable = new Runnable() {

			public void run()
			{
				String ServerIP = Fortitude.getFortitude().getResources().getString(R.string.ServerIP);

				if(ServerIP == null)
				{
					Fortitude.getFortitude().runOnUiThread(new Runnable() {
						public void run()
						{
							ServerRequests.getTheMessageBox().killMe();
							ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Unable To Retrieve Setting 'ServerIP'", true));
						}
					});
					return;
				}

				RequestThread rt = new RequestThread() {

					public void processResponse(JSONObject response) throws Exception
					{
						if(response == null)
						{
							this.setOutputMessage("Failed To Log in");
							this.setSuccess("1");
							return;
						}
						if(response.get("error") != null)
						{
							this.setOutputMessage(response.get("error").asString());
							this.setSuccess("1");
							return;
						}
						if(response.get("success") == null)
						{
							this.setOutputMessage("Failed To Log in");
							this.setSuccess("1");
							return;
						}
						if(response.get("success").asString().equals("false"))
						{
							this.setOutputMessage("Failed To Log in");
							this.setSuccess("1");
							return;
						}
						if(response.get("code") == null)
						{
							this.setOutputMessage("Failed To Log in");
							this.setSuccess("1");
							return;
						}
						this.setOutputMessage(response.get("code").asString());
						this.setSuccess("2");
					}

				};
				rt.setURL("http://" + ServerIP + "/api/session?uname=" + ServerRequests.getStaticUname() + "&phash=" + ServerRequests.getStaticPhash());
				Thread thread = new Thread(rt);
				thread.start();
				boolean connecting = false;
				if(staticInitialLogin)
				{
					while(connecting == false)
					{
						if(!(rt.getSuccess().equals("0")))
						{
							staticOutputMessage = rt.getOutputMessage();
							if(rt.getSuccess().equals("2"))
							{
								Thread getUserInfoThread = new Thread(new Runnable() {
									public void run()
									{
										Fortitude.getFortitude().runOnUiThread(new Runnable() {
											public void run()
											{
												staticSessionId = staticOutputMessage;
												ServerRequests.getUserInfo(staticUname); 
											}
										});
										try
										{
											Thread.sleep(100);
										}
										catch(Exception e)
										{
											//Do nothing
										}
										while(!(ServerRequests.getGetUserInfoComplete()))
										{
											//Wait till
										}
										if(ServerRequests.getGetUserInfoSuccess())
										{
											Fortitude.getFortitude().runOnUiThread(new Runnable() {
												public void run()
												{
													if(ServerRequests.getStaticUserInfo().size() != 1)
													{
														ServerRequests.getTheMessageBox().killMe();
														MessageBox.newMsgBox("An error has occurred whilst retrieving your user information", true);
													}
													else
													{
														ServerRequests.getUserStats(staticUname, staticSessionId);
														Thread getBalanceThread = new Thread(new Runnable() {
															public void run()
															{
																while(!(ServerRequests.getGetUserBalanceComplete()))
																{
																	//Do nothing
																}
																if(ServerRequests.getGetUserBalanceSuccess())
																{
																	Fortitude.getFortitude().runOnUiThread(new Runnable() {
																		public void run()
																		{
																			if(ServerRequests.getTheMessageBox() != null)
																			{
																				ServerRequests.getTheMessageBox().changeMessageToDisplay("Successfully Signed In!");
																			}
																			//ServerRequests.getTheMessageBox().killMe();
																			//ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Successfully Signed In!", false));
																		}
																	});
																	new CurrentUser(staticUserInfo.get(0).getAccountId(), staticUserInfo.get(0).getUserName(), staticUserInfo.get(0).getJoinDate(), staticUserInfo.get(0).getRank(), staticSessionId, staticPhash, staticUserBalance, "2130837506", ServerRequests.getStaticCacheCount(), ServerRequests.getStaticTotalUnits());
																	Fortitude.getFortitude().runOnUiThread(new Runnable() {
																		public void run()
																		{
																			try
																			{
																				Thread.sleep(1500);
																			}
																			catch(Exception e)
																			{

																			}
																			if(ServerRequests.getTheMessageBox() != null)
																			{
																				ServerRequests.getTheMessageBox().killMe();
																			}
																			MainLoginScreen.getMe().killMe();
																			TheMap.newTheMap(Fortitude.getFortitude());
																			new MainScreen();
																		}
																	});
																}
															}
														});
														getBalanceThread.start();
													}
												}
											});
										}
										else
										{
											Fortitude.getFortitude().runOnUiThread(new Runnable() {
												public void run()
												{
													ServerRequests.getTheMessageBox().killMe();
													ServerRequests.setTheMessageBox(MessageBox.newMsgBox(ServerRequests.getStaticOutputMessage(), false));
												}
											});
										}
									}
								});
								getUserInfoThread.start();
							}
							if(rt.getSuccess().equals("1"))
							{
								Fortitude.getFortitude().runOnUiThread(new Runnable() {
									public void run()
									{
										ServerRequests.getTheMessageBox().killMe();
										MainLoginScreen.getMe().killMe();
										new MainLoginScreen();
										ServerRequests.setTheMessageBox(MessageBox.newMsgBox(ServerRequests.getStaticOutputMessage(), true));
									}
								});
							}
							connecting = true;
						}
					}
				}
				else
				{
					while(connecting == false)
					{
						if(!(rt.getSuccess().equals("0")))
						{
							staticOutputMessage = rt.getOutputMessage();
							Fortitude.getFortitude().runOnUiThread(new Runnable() {
								public void run()
								{
									ServerRequests.getTheMessageBox().killMe();
									ServerRequests.setTheMessageBox(MessageBox.newMsgBox(ServerRequests.getStaticOutputMessage(), true));
								}
							});
							connecting = true;
						}
					}
				}
			}
		};
		Thread thread = new Thread(runnable);
		thread.start();
	}

	////////
	//
	//getUserStats
	//
	//gets the balance of a user with a valid sessionId
	//
	////////
	public static void getUserStats(String uname, String sessionId)
	{
		staticUname = uname;
		staticSessionId = sessionId;

		getUserBalanceSuccess = false;
		getUserBalanceComplete = false;

		Runnable runnable = new Runnable() {

			public void run()
			{
				String ServerIP = Fortitude.getFortitude().getResources().getString(R.string.ServerIP);

				if(ServerIP == null)
				{
					Fortitude.getFortitude().runOnUiThread(new Runnable() {
						public void run()
						{
							ServerRequests.getTheMessageBox().killMe();
							ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Unable To Retrieve Setting 'ServerIP'", true));
						}
					});
					ServerRequests.setGetUserBalanceSuccess(false);
					ServerRequests.setGetUserBalanceComplete(true);
					return;
				}

				RequestThread rt = new RequestThread() {

					public void processResponse(JSONObject response) throws Exception
					{
						if(response == null)
						{
							this.setOutputMessage("Failed to get user balance");
							this.setSuccess("1");
							return;
						}
						if(response.get("error") != null)
						{
							this.setOutputMessage(response.get("error").asString());
							this.setSuccess("1");
							return;
						}
						if(response.get("success") == null)
						{
							this.setOutputMessage("Failed to get user balance");
							this.setSuccess("1");
							return;
						}
						if(response.get("success").asString().equals("false"))
						{
							this.setOutputMessage("Failed to get user balance");
							this.setSuccess("1");
							return;
						}
						this.setOutputMessage("done");
						ServerRequests.setStaticUserBalance(response.get("balance").asString());
						ServerRequests.setStaticTotalUnits(response.get("garrison").asString());
						ServerRequests.setStaticCacheCount(response.get("cachecount").asString());
						this.setSuccess("2");
					}

				};
				rt.setURL("http://" + ServerIP + "/api/userstats?uname=" + ServerRequests.getStaticUname() + "&session=" + staticSessionId);
				Thread thread = new Thread(rt);
				thread.start();
				boolean connecting = false;
				while(connecting == false)
				{
					if(!(rt.getSuccess().equals("0")))
					{
						staticOutputMessage = rt.getOutputMessage();
						if(rt.getSuccess().equals("1"))
						{
							ServerRequests.setGetUserBalanceSuccess(false);
							ServerRequests.setGetUserBalanceComplete(true);
							Fortitude.getFortitude().runOnUiThread(new Runnable() {
								public void run()
								{
									ServerRequests.getTheMessageBox().killMe();
									ServerRequests.setTheMessageBox(MessageBox.newMsgBox(ServerRequests.getStaticOutputMessage(), true));
								}
							});
						}
						else if(rt.getSuccess().equals("2"))
						{
							ServerRequests.setGetUserBalanceSuccess(true);
							ServerRequests.setGetUserBalanceComplete(true);
						}
						connecting = true;
					}
				}
			}
		};
		Thread thread = new Thread(runnable);
		thread.start();
	}

	////////
	//
	//registerUser
	//
	//registers a new user on the servers database.
	//
	////////
	public static void registerUser(String uname, String phash, String email)
	{
		staticUname = uname;
		staticPhash = phash;
		staticEmail = email;

		Runnable runnable = new Runnable() {

			public void run()
			{
				String ServerIP = Fortitude.getFortitude().getResources().getString(R.string.ServerIP);

				if(ServerIP == null)
				{
					Fortitude.getFortitude().runOnUiThread(new Runnable() {
						public void run()
						{
							ServerRequests.getTheMessageBox().killMe();
							ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Unable To Retrieve Setting 'ServerIP'", true));
						}
					});
					return;
				}

				RequestThread rt = new RequestThread() {

					public void processResponse(JSONObject response) throws Exception
					{
						if(response == null)
						{
							this.setOutputMessage("Failed to create new user");
							this.setSuccess("1");
							return;
						}
						if(response.get("error") != null)
						{
							this.setOutputMessage(response.get("error").asString());
							this.setSuccess("1");
							return;
						}
						if(response.get("success") == null)
						{
							this.setOutputMessage("Failed To Create New User");
							this.setSuccess("1");
							return;
						}
						if(response.get("success").asString().equals("false"))
						{
							this.setOutputMessage("Failed To Create New User");
							this.setSuccess("1");
							return;
						}
						this.setOutputMessage("User Created!\n\nYou have been sent an email containing a link that you must follow inorder to activate your account!");
						this.setSuccess("2");
					}

				};
				rt.setURL("http://" + ServerIP + "/api/register?uname=" + ServerRequests.getStaticUname() + "&phash=" + ServerRequests.getStaticPhash() + "&email=" + ServerRequests.getStaticEmail());
				Thread thread = new Thread(rt);
				thread.start();
				boolean connecting = false;
				while(connecting == false)
				{
					if(!(rt.getSuccess().equals("0")))
					{
						staticOutputMessage = rt.getOutputMessage();
						if(rt.getSuccess().equals("1"))
						{
							Fortitude.getFortitude().runOnUiThread(new Runnable() {
								public void run()
								{
									ServerRequests.getTheMessageBox().killMe();
									ServerRequests.setTheMessageBox(MessageBox.newMsgBox(ServerRequests.getStaticOutputMessage(), true));
								}
							});
						}
						else if(rt.getSuccess().equals("2"))
						{
							Fortitude.getFortitude().runOnUiThread(new Runnable() {
								public void run()
								{
									ServerRequests.getTheMessageBox().killMe();
									NewUserScreen.getMe().killMe();
									new MainLoginScreen();
									MessageBox.newMsgBox(ServerRequests.getStaticOutputMessage(), true);
								}
							});
						}
						connecting = true;
					}
				}
			}
		};
		Thread thread = new Thread(runnable);
		thread.start();
	}

	public static void resetPassReset(String email)
	{
		staticEmail = email;

		Thread thread = new Thread(new Runnable() {
			public void run()
			{
				String ServerIP = Fortitude.getFortitude().getResources().getString(R.string.ServerIP);

				if(ServerIP == null)
				{
					Fortitude.getFortitude().runOnUiThread(new Runnable() {
						public void run()
						{
							ServerRequests.getTheMessageBox().killMe();
							ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Unable To Retrieve Setting 'ServerIP'", true));
						}
					});
					return;
				}
				RequestThread rt = new RequestThread() {

					public void processResponse(JSONObject response) throws Exception
					{
						if(response == null)
						{
							this.setOutputMessage("Failed To Send Email To Account");
							this.setSuccess("1");
							return;
						}
						if(response.get("error") != null)
						{
							this.setOutputMessage(response.get("error").asString());
							this.setSuccess("1");
							return;
						}
						if(response.get("success") == null)
						{
							this.setOutputMessage("Failed To Send Email To Account");
							this.setSuccess("1");
							return;
						}
						if(response.get("success").asString().equals("false"))
						{
							this.setOutputMessage("Failed To Send Email To Account");
							this.setSuccess("1");
							return;
						}
						this.setSuccess("2");
					}

				};
				rt.setURL("http://" + ServerIP + "/api/sendpassreset?email=" + ServerRequests.getStaticEmail());
				Thread thread = new Thread(rt);
				thread.start();

				boolean connecting = false; //wait for response
				while(connecting == false)
				{
					if(rt.getSuccess().equals("1"))
					{
						staticOutputMessage = rt.getOutputMessage();
						Fortitude.getFortitude().runOnUiThread(new Runnable() {
							public void run()
							{
								ServerRequests.getTheMessageBox().killMe();
								ServerRequests.setTheMessageBox(MessageBox.newMsgBox(ServerRequests.getStaticOutputMessage(), true));
							}
						});
						connecting = true;
					}
					else if(rt.getSuccess().equals("2"))
					{
						connecting = true;
						Fortitude.getFortitude().runOnUiThread(new Runnable() {
							public void run()
							{
								ServerRequests.getTheMessageBox().killMe();
								ForgottenDetailsScreen.getMe().killMe();
								new MainLoginScreen();
								MessageBox.newMsgBox("An email has been sent to your email address containg your username and instructions of how to change your password", true);
							}
						});
					}
				}
			}
		});
		thread.start();
	}

	////////
	//
	//getUserInfo
	//
	//creates a static arraylist of Users from a given list of users usernames
	//
	////////
	public static void getUserInfo(String users)
	{
		getUserInfoComplete = false;
		getUserInfoSuccess = false;

		usersToGet = users;
		staticUserInfo = new ArrayList<User>();

		Runnable runnable = new Runnable() {

			public void run()
			{
				String ServerIP = Fortitude.getFortitude().getResources().getString(R.string.ServerIP);

				if(ServerIP == null)
				{
					Fortitude.getFortitude().runOnUiThread(new Runnable() {
						public void run()
						{
							ServerRequests.getTheMessageBox().killMe();
							ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Unable To Retrieve Setting 'ServerIP'", true));
						}
					});
					ServerRequests.setGetUserInfoSuccess(false);
					ServerRequests.setGetUserInfoComplete(true);
					return;
				}

				RequestThread rt = new RequestThread() {

					public void processResponse(JSONObject response) throws Exception
					{
						if(response == null)
						{
							this.setOutputMessage("Failed To Get User Info");
							this.setSuccess("1");
							return;
						}
						if(response.get("error") != null)
						{
							this.setOutputMessage(response.get("error").asString());
							this.setSuccess("1");
							return;
						}
						if(response.get("success") == null)
						{
							this.setOutputMessage("Failed To Get User Info");
							this.setSuccess("1");
							return;
						}
						if(response.get("success").asString().equals("false"))
						{
							this.setOutputMessage("Failed To Get User Info");
							this.setSuccess("1");
							return;
						}
						for(User u: ServerRequests.getStaticUserInfo())
						{
							ServerRequests.getStaticUserInfo().remove(u);
						}
						for(int i = 0; i < response.get("users").length(); i++)
						{
							String accountid = Integer.toString(response.get("users").get(i).get("accountid").asInteger());
							String uname = response.get("users").get(i).get("uname").asString();
							String joindate = Integer.toString(response.get("users").get(i).get("joindate").asInteger());
							String rank = response.get("users").get(i).get("rank").asString();
							User u = new User(accountid, uname, joindate, rank, "2130837506");
							ServerRequests.getStaticUserInfo().add(u);
						}
						this.setSuccess("2");
					}

				};
				rt.setURL("http://" + ServerIP + "/api/userinfo?unames=" + ServerRequests.getUsersToGet());
				Thread thread = new Thread(rt);
				thread.start();
				boolean connecting = false;
				while(connecting == false)
				{
					if(rt.getSuccess().equals("1"))
					{
						staticOutputMessage = rt.getOutputMessage();
						Fortitude.getFortitude().runOnUiThread(new Runnable() {
							public void run()
							{
								ServerRequests.getTheMessageBox().killMe();
								ServerRequests.setTheMessageBox(MessageBox.newMsgBox(ServerRequests.getStaticOutputMessage(), true));
							}
						});
						connecting = true;
						ServerRequests.setGetUserInfoSuccess(false);
						ServerRequests.setGetUserInfoComplete(true);
					}
					else if(rt.getSuccess().equals("2"))
					{
						connecting = true;
						ServerRequests.setGetUserInfoSuccess(true);
						ServerRequests.setGetUserInfoComplete(true);
					}
				}
			}
		};
		Thread thread = new Thread(runnable);
		thread.start();
	}

	////////
	//
	//getNearbyCaches
	//
	//creates a static arraylist of Caches within a given radius of a given location
	//
	////////
	public static void getNearbyCaches(String username, String sessionId, String lat, String lon, String radius)
	{
		getNearbyCachesInfoStatus = 0;
		nearbyCaches = new ArrayList<Cache>();
		staticUname = username;
		staticSessionId = sessionId;
		staticLat = lat;
		staticLon = lon;
		cacheRadius = radius;

		Runnable runnable = new Runnable() {

			public void run()
			{
				String ServerIP = Fortitude.getFortitude().getResources().getString(R.string.ServerIP);

				if(ServerIP == null)
				{
					Fortitude.getFortitude().runOnUiThread(new Runnable() {
						public void run()
						{
							if(ServerRequests.getTheMessageBox() != null)
							{
								ServerRequests.getTheMessageBox().killMe();
							}
							ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Unable To Retrieve Setting 'ServerIP'", true));
						}
					});
					ServerRequests.setGetNearbyCachesInfoStatus(1);
					return;
				}

				RequestThread rt = new RequestThread() {

					public void processResponse(JSONObject response) throws Exception
					{
						if(response == null)
						{
							this.setOutputMessage("Failed To Get Nearby Cache Data");
							this.setSuccess("1");
							return;
						}
						if(response.get("error") != null)
						{
							this.setOutputMessage(response.get("error").asString());
							this.setSuccess("1");
							return;
						}
						if(response.get("success") == null)
						{
							this.setOutputMessage("Failed To Get Nearby Cache Data");
							this.setSuccess("1");
							return;
						}
						if(response.get("success").asString().equals("false"))
						{
							this.setOutputMessage("Failed To Get Nearby Cache Data");
							this.setSuccess("1");
							return;
						}
						for(Cache c : ServerRequests.getNearbyCaches())
						{
							ServerRequests.getNearbyCaches().remove(c);
						}
						for(int i = 0; i < response.get("caches").length(); i++)
						{
							String cacheid = Integer.toString(response.get("caches").get(i).get("cacheid").asInteger());
							String ownerid = Integer.toString(response.get("caches").get(i).get("ownerid").asInteger());
							String name = response.get("caches").get(i).get("name").asString();
							String latitude = response.get("caches").get(i).get("latitude").asString();
							String longitude = response.get("caches").get(i).get("longitude").asString();
							String garrison = response.get("caches").get(i).get("garrison").asString();
							Cache c = new Cache(cacheid, ownerid, name, latitude, longitude, garrison);
							ServerRequests.getNearbyCaches().add(c);
						}
						this.setSuccess("2");
					}

				};
				rt.setURL("http://" + ServerIP + "/api/nearbycaches?uname=" + ServerRequests.getStaticUname() + "&session=" + ServerRequests.getStaticSessionId() + "&lat=" + ServerRequests.getStaticLat() + "&lon=" + ServerRequests.getStaticLon() + "&radius=" + ServerRequests.getCacheRadius());
				Thread thread = new Thread(rt);
				thread.start();
				boolean connecting = false;
				while(connecting == false)
				{
					if(rt.getSuccess().equals("1"))
					{
						staticOutputMessage = rt.getOutputMessage();
						Fortitude.getFortitude().runOnUiThread(new Runnable() {
							public void run()
							{
								if(ServerRequests.getTheMessageBox() != null)
								{
									ServerRequests.getTheMessageBox().killMe();
								}
								ServerRequests.setTheMessageBox(MessageBox.newMsgBox(ServerRequests.getStaticOutputMessage(), true));
							}
						});
						connecting = true;
						ServerRequests.setGetNearbyCachesInfoStatus(1);
					}
					else if(rt.getSuccess().equals("2"))
					{
						connecting = true;
						if(ServerRequests.getTheMessageBox() != null)
						{
							Fortitude.getFortitude().runOnUiThread(new Runnable() {
								public void run()
								{
									ServerRequests.getTheMessageBox().killMe();
								}
							});
						}
						ServerRequests.setGetNearbyCachesInfoStatus(2);
					}
				}
			}
		};
		Thread thread = new Thread(runnable);
		thread.start();
	}
}


