package com.example.fortitude;

import java.net.URL;
import java.net.URLConnection;
import java.io.UnsupportedEncodingException;
import java.lang.StringBuilder;
import java.net.URLEncoder;
import java.net.ConnectException;
import json.*;

import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
	private static boolean getUserInfoUseUsernames;
	private static boolean refreshDataComplete;
	private static boolean refreshDataSuccess;
	private static String staticOriginPosition;
	private static String staticDestinationPosition;
	private static boolean scoutCacheComplete;
	private static boolean scoutCacheSuccess;
	private static String staticCacheIdToScout;
	private static boolean placeCacheComplete;
	private static boolean placeCacheSuccess;
	private static String unitsToPlace;
	private static boolean attackCacheComplete;
	private static boolean attackCacheSuccess;
	private static String cacheIdToAttack;
	private static String unitsToAttackWith;
	private static String staticScoutsSent;
	private static boolean staticGoogleRouteSuccess;
	private static boolean staticGoogleRouteComplete;
	private static JSONObject googleDirectionsResponse;
	private static JSONObject scoutCacheResponse;
	private static JSONObject attackCacheResponse;
	private static boolean staticDepositComplete;
	private static boolean staticDepositSuccess;
	private static long staticTimeStamp;
	private static int staticNumberOfStubs;
	private static boolean staticGetNewsSuccess;
	private static boolean staticGetNewsComplete;
	private static String staticNewsFilter;
	private static boolean staticDeleteMessagebox;

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
	public static String getStaticNewsFilter()
	{
		return staticNewsFilter;
	}

	public static boolean getStaticGetNewsSuccess()
	{
		return staticGetNewsSuccess;
	}

	public static void setStaticGetNewsSuccess(boolean x)
	{
		staticGetNewsSuccess = x;
	}

	public static boolean getStaticGetNewsComplete()
	{
		return staticGetNewsComplete;
	}

	public static void setStaticGetNewsComplete(boolean x)
	{
		staticGetNewsComplete = x;
	}

	public static long getStaticTimeStamp()
	{
		return staticTimeStamp;
	}

	public static int getStaticNumberOfStubs()
	{
		return staticNumberOfStubs;
	}

	public static void setStaticTimeStamp(long x)
	{
		staticTimeStamp = x;
	}

	public static void setStaticNumberOfStubs(int x)
	{
		staticNumberOfStubs = x;
	}

	public static boolean getStaticDepositComplete()
	{
		return staticDepositComplete;
	}

	public static void setStaticDepositComplete(boolean x)
	{
		staticDepositComplete = x;
	}

	public static boolean getStaticDepositSuccess()
	{
		return staticDepositSuccess;	
	}

	public static void setStaticDepositSuccess(boolean x)
	{
		staticDepositSuccess = x;
	}

	public static JSONObject getAttackCacheResponse()
	{
		return attackCacheResponse;
	}

	public static void setAttackCacheResponse(JSONObject x)
	{
		attackCacheResponse = x;
	}

	public static JSONObject getScoutCacheResponse()
	{
		return scoutCacheResponse;
	}

	public static void setScoutCacheResponse(JSONObject x)
	{
		scoutCacheResponse = x;
	}

	public static JSONObject getGoogleDirectionsResponse()
	{
		return googleDirectionsResponse;
	}

	public static void setGoogleDirectionsResponse(JSONObject x)
	{
		googleDirectionsResponse = x;
	}

	public static boolean getStaticGoogleRouteComplete()
	{
		return staticGoogleRouteComplete;
	}

	public static void setStaticGoogleRouteComplete(boolean x)
	{
		staticGoogleRouteComplete = x;
	}

	public static boolean getStaticGoogleRouteSuccess()
	{
		return staticGoogleRouteSuccess;
	}

	public static void setStaticGoogleRouteSuccess(boolean x)
	{
		staticGoogleRouteSuccess = x;
	}

	public static String getStaticScoutsSent()
	{
		return staticScoutsSent;
	}

	public static String getUnitsToAttackWith()
	{
		return unitsToAttackWith;
	}

	public void setUnitsToAttackWith(String x)
	{
		unitsToAttackWith = x;
	}

	public static String getCacheIdToAttack()
	{
		return cacheIdToAttack;
	}

	public static void setCacheIdToAttack(String x)
	{
		cacheIdToAttack = x;
	}

	public static boolean getAttackCacheSuccess()
	{
		return attackCacheSuccess;
	}

	public static void setAttackCacheSuccess(boolean x)
	{
		attackCacheSuccess = x;
	}

	public static boolean getAttackCacheComplete()
	{
		return attackCacheComplete;
	}

	public static void setAttackCacheComplete(boolean x)
	{
		attackCacheComplete = x;
	}

	public static String getUnitsToPlace()
	{
		return unitsToPlace;
	}

	public static void setUnitsToPlace(String x)
	{
		unitsToPlace = x;
	}

	public static boolean getPlaceCacheSuccess()
	{
		return placeCacheSuccess;
	}

	public static void setPlaceCacheSuccess(boolean x)
	{
		placeCacheSuccess = x;
	}

	public static boolean getPlaceCacheComplete()
	{
		return placeCacheComplete;
	}

	public static void setPlaceCacheComplete(boolean x)
	{
		placeCacheComplete = x;
	}

	public static String getCacheIdToScout()
	{
		return staticCacheIdToScout;
	}

	public static void setCacheIdToScout(String x)
	{
		staticCacheIdToScout = x;
	}

	public static boolean getScoutCacheSuccess()
	{
		return scoutCacheSuccess;	
	}

	public static void setScoutCacheSuccess(boolean x)
	{
		scoutCacheSuccess = x;
	}

	public static boolean getScoutCacheComplete()
	{
		return scoutCacheComplete;
	}

	public static void setScoutCacheComplete(boolean x)
	{
		scoutCacheComplete = x;
	}

	public static String getStaticDestinationPosition()
	{
		return staticDestinationPosition;
	}

	public static String getStaticOriginPosition()
	{
		return staticOriginPosition;
	}

	public static boolean getRefreshDataComplete()
	{
		return refreshDataComplete;
	}

	public static void setRefreshDataComplete(boolean x)
	{
		refreshDataComplete = x;
	}

	public static boolean getRefreshDataSuccess()
	{
		return refreshDataSuccess;
	}

	public static void setRefreshDataSuccess(boolean x)
	{
		refreshDataSuccess = x;
	}

	public static String getCacheRadius()
	{
		return cacheRadius;
	}

	public static void setCacheRadius(String x)
	{
		cacheRadius = x;
	}

	public static boolean getGetUserInfoUseUsernames()
	{
		return getUserInfoUseUsernames;
	}

	public static void setGetUserInfoUseUsernames(boolean x)
	{
		getUserInfoUseUsernames = x;
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
				while(connecting == false)
				{
					sleepFunction();
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
											ServerRequests.getUserInfo(staticUname, true); 
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
										//Wait till done
										sleepFunction();
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
																sleepFunction();
															}
															if(ServerRequests.getGetUserBalanceSuccess())
															{
																new CurrentUser(staticUserInfo.get(0).getAccountId(), staticUserInfo.get(0).getUserName(), staticUserInfo.get(0).getJoinDate(), staticUserInfo.get(0).getRank(), staticSessionId, staticPhash, ServerRequests.staticUserBalance, ServerRequests.getStaticCacheCount(), ServerRequests.getStaticTotalUnits(), staticUserInfo.get(0).getCaches());
																FileSave fs = new FileSave();
																fs.CreateFileDialog("username", CurrentUser.getMe().getUserName());
																fs.CreateFileDialog("password", CurrentUser.getMe().getPhash());
																Fortitude.getFortitude().runOnUiThread(new Runnable() {
																	public void run()
																	{
																		ServerRequests.getNewsStubs(System.currentTimeMillis(), 16);
																		Thread thread = new Thread(new Runnable() {
																			public void run()
																			{
																				while(!ServerRequests.getStaticGetNewsComplete())
																				{
																					//wait!
																					ServerRequests.sleepFunction();
																				}
																				if(ServerRequests.getStaticGetNewsSuccess())
																				{
																					Fortitude.getFortitude().runOnUiThread(new Runnable() {
																						public void run()
																						{
																							if(ServerRequests.getTheMessageBox() != null)
																							{
																								ServerRequests.getTheMessageBox().changeMessageToDisplay("Successfully Signed In!");
																							}
																							else if(MessageBox.getMe() != null)
																							{
																								MessageBox.getMe().changeMessageToDisplay("Successfully Signed In!");
																							}
																							else
																							{
																								ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Successfully Signed In!", false));
																							}

																							//ServerRequests.getTheMessageBox().killMe();
																							//ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Successfully Signed In!", false));
																						}
																					});
																					try
																					{
																						Thread.sleep(1500);
																					}
																					catch(Exception e)
																					{
																						//oh well
																					}
																					Fortitude.getFortitude().runOnUiThread(new Runnable() {
																						public void run()
																						{
																							if(ServerRequests.getTheMessageBox() != null)
																							{
																								ServerRequests.getTheMessageBox().killMe();
																							}
																							TheMap.newTheMap(Fortitude.getFortitude());
																							if(staticInitialLogin)
																							{
																								if(MainLoginScreen.getMe() != null)
																								{
																									MainLoginScreen.getMe().killMe();
																								}
																								if(AutoSignInErrorScreen.getMe() != null)
																								{
																									AutoSignInErrorScreen.getMe().killMe();
																								}
																								if(SplashScreen.getMe() != null)
																								{
																									SplashScreen.getMe().killMe();
																								}
																								new MainScreen();
																							}
																							else
																							{
																								NewUserScreen.getMe().killMe();
																								new HelpScreens(0, 0);
																								MessageBox.newMsgBox("User Created!\n\nYou have been sent an email containing a link that you must follow inorder to activate your account!  Take a look through the help screens and when you're done press cancel to start playing!", true);
																							}
																						}
																					});
																				}
																			}
																		});
																		thread.start();
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
												if(MainLoginScreen.getMe() != null)
												{
													ServerRequests.getTheMessageBox().killMe();
													MainLoginScreen.getMe().getPasswordField().setText("");
													ServerRequests.setTheMessageBox(MessageBox.newMsgBox(ServerRequests.getStaticOutputMessage(), true));
												}
												else
												{
													ServerRequests.getTheMessageBox().killMe();
													if(SplashScreen.getMe() != null)
													{
														SplashScreen.getMe().killMe();
														new AutoSignInErrorScreen(staticUname, staticPhash, staticInitialLogin);
													}
													ServerRequests.setTheMessageBox(MessageBox.newMsgBox(ServerRequests.getStaticOutputMessage(), true));
												}
											}
										});
									}
								}
							});
							getUserInfoThread.start();
						}
						if(rt.getSuccess().equals("1"))
						{
							if(rt.getOutputMessage().equals("auth error: incorrect credentials"))
							{
								Fortitude.getFortitude().runOnUiThread(new Runnable() {
									public void run()
									{
										ServerRequests.getTheMessageBox().killMe();
										GUI.killAll();
										new MainLoginScreen();
										ServerRequests.setTheMessageBox(MessageBox.newMsgBox(ServerRequests.getStaticOutputMessage(), true));
									}
								});
							}
							else
							{
								if(MainLoginScreen.getMe() != null)
								{
									Fortitude.getFortitude().runOnUiThread(new Runnable() {
										public void run()
										{
											ServerRequests.getTheMessageBox().killMe();
											MainLoginScreen.getMe().getPasswordField().setText("");
											ServerRequests.setTheMessageBox(MessageBox.newMsgBox(ServerRequests.getStaticOutputMessage(), true));
										}
									});
								}
								else
								{
									Fortitude.getFortitude().runOnUiThread(new Runnable() {
										public void run()
										{
											ServerRequests.getTheMessageBox().killMe();
											if(SplashScreen.getMe() != null)
											{
												SplashScreen.getMe().killMe();
												new AutoSignInErrorScreen(staticUname, staticPhash, staticInitialLogin);
											}
											ServerRequests.setTheMessageBox(MessageBox.newMsgBox(ServerRequests.getStaticOutputMessage(), true));
										}
									});
								}
							}
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
					sleepFunction();
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
									if(ServerRequests.getStaticOutputMessage().equals("auth error: session expired"))
									{
										sessionExpiredActions();
									}
									else if(ServerRequests.getStaticOutputMessage().equals("auth error: incorrect session code"))
									{
										sessionExpiredActions();
									}
									else
									{
										ServerRequests.getTheMessageBox().killMe();
										ServerRequests.setTheMessageBox(MessageBox.newMsgBox(ServerRequests.getStaticOutputMessage(), true));
									}
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
						this.setOutputMessage("User Created!");
						this.setSuccess("2");
					}

				};
				rt.setURL("http://" + ServerIP + "/api/register?uname=" + ServerRequests.getStaticUname() + "&phash=" + ServerRequests.getStaticPhash() + "&email=" + ServerRequests.getStaticEmail());
				Thread thread = new Thread(rt);
				thread.start();
				boolean connecting = false;
				while(connecting == false)
				{
					sleepFunction();
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
									ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Connecting To Server", false));
									Login.logIn(staticUname, staticPhash);
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

	////////
	//
	//resetPassRest
	//
	//sends email to the email address provided if it exists on the server
	//containing the username of the account and a link to the website to
	//change their password if they want
	//
	////////
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
					sleepFunction();
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
	public static void getUserInfo(String users, boolean useUsernames)
	{	
		getUserInfoComplete = false;
		getUserInfoSuccess = false;
		getUserInfoUseUsernames = useUsernames;

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
							String caches = response.get("users").get(i).get("caches").asString();
							User u = new User(accountid, uname, joindate, rank, caches);
							ServerRequests.getStaticUserInfo().add(u);
						}
						this.setSuccess("2");
					}

				};
				if(ServerRequests.getGetUserInfoUseUsernames())
				{
					rt.setURL("http://" + ServerIP + "/api/userinfo?unames=" + ServerRequests.getUsersToGet());
				}
				else
				{
					rt.setURL("http://" + ServerIP + "/api/userinfo?uids=" + ServerRequests.getUsersToGet());
				}
				Thread thread = new Thread(rt);
				thread.start();
				boolean connecting = false;
				while(connecting == false)
				{
					sleepFunction();
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
					sleepFunction();
					if(rt.getSuccess().equals("1"))
					{
						staticOutputMessage = rt.getOutputMessage();
						Fortitude.getFortitude().runOnUiThread(new Runnable() {
							public void run()
							{
								if(ServerRequests.getStaticOutputMessage().equals("auth error: session expired"))
								{
									sessionExpiredActions();
								}
								else if(ServerRequests.getStaticOutputMessage().equals("auth error: incorrect session code"))
								{
									sessionExpiredActions();
								}
								else
								{
									if(ServerRequests.getTheMessageBox() != null)
									{
										ServerRequests.getTheMessageBox().killMe();
									}
									else if(MessageBox.getMe() != null)
									{
										MessageBox.getMe().killMe();
									}
									ServerRequests.setTheMessageBox(MessageBox.newMsgBox(ServerRequests.getStaticOutputMessage(), true));
								}
							}
						});
						connecting = true;
						ServerRequests.setGetNearbyCachesInfoStatus(1);
					}
					else if(rt.getSuccess().equals("2"))
					{
						connecting = true;
						ServerRequests.setGetNearbyCachesInfoStatus(2);
					}
				}
			}
		};
		Thread thread = new Thread(runnable);
		thread.start();
	}

	////////
	//
	//refreshData
	//
	//refreshes markers on the map and updates user balance and cache number
	//
	////////
	public static void refreshData(boolean deleteMessageBox)
	{
		refreshDataComplete = false;
		refreshDataSuccess = false;
		staticDeleteMessagebox = deleteMessageBox;
		try
		{
			if(TheMap.getMe().getGoogleMap() != null)
			{
				if(TheMap.getMe().getGoogleMap().getMyLocation() != null)
				{
					String saveLon = Double.toString(TheMap.getMe().getGoogleMap().getMyLocation().getLongitude());
					String saveLat = Double.toString(TheMap.getMe().getGoogleMap().getMyLocation().getLatitude());
					FileSave fs = new FileSave();
					fs.CreateFileDialog("latitude", saveLat);
					fs.CreateFileDialog("longitude", saveLon);
				}
			}
		}
		catch(Exception e)
		{
			//oh well...
		}
		TheMap.getMe().updateCachePositions();
		Thread thread = new Thread(new Runnable() {
			public void run()
			{
				while(ServerRequests.getGetNearbyCachesInfoStatus() == 0)
				{
					//WAIT!
					sleepFunction();
				}
				if(ServerRequests.getGetNearbyCachesInfoStatus() == 2)
				{
					ServerRequests.getUserStats(CurrentUser.getMe().getUserName(), CurrentUser.getMe().getSessionID());
					while(!(ServerRequests.getGetUserBalanceComplete()))
					{
						sleepFunction();
					}
					if(ServerRequests.getGetUserBalanceSuccess())
					{
						CurrentUser.getMe().setBalance(ServerRequests.getStaticUserBalance());
						CurrentUser.getMe().setNumberOfCaches(ServerRequests.getStaticCacheCount());
						CurrentUser.getMe().setTotalBalance(ServerRequests.getStaticTotalUnits());
						Fortitude.getFortitude().runOnUiThread(new Runnable() {
							public void run()
							{
								if(ServerRequests.getTheMessageBox() != null)
								{
									ServerRequests.getTheMessageBox().killMe();
								}
								if(MessageBox.getMe() != null)
								{
									MessageBox.getMe().killMe();
								}
								if(MainScreen.getMe() != null)
								{
									if(MainScreen.getMe().getUserBalanceTextView() != null)
									{
										MainScreen.getMe().getUserBalanceTextView().setText(CurrentUser.getMe().getBalance() + " ");
									}
								}
								ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Connecting To Server", false));
								ServerRequests.getNewsStubs(System.currentTimeMillis(), 16);
								Thread thread = new Thread(new Runnable() {
									public void run()
									{
										while(!ServerRequests.getStaticGetNewsComplete())
										{
											ServerRequests.sleepFunction();
											//wait!
										}
										if(ServerRequests.getStaticGetNewsSuccess())
										{
											Fortitude.getFortitude().runOnUiThread(new Runnable() {
												public void run()
												{
													if(TheMap.getMe().getCacheRoutePosition().equals("null"))
													{
														if(staticDeleteMessagebox)
														{
															if(ServerRequests.getTheMessageBox() != null)
															{
																ServerRequests.getTheMessageBox().killMe();
															}
															if(MessageBox.getMe() != null)
															{
																MessageBox.getMe().killMe();
															}
														}
														ServerRequests.setRefreshDataSuccess(true);
														ServerRequests.setRefreshDataComplete(true);
														return;
													}
													ServerRequests.getTheMessageBox().changeMessageToDisplay("Mapping Route");
													ServerRequests.getGoogleMapRoute(Double.toString(TheMap.getMe().getGoogleMap().getMyLocation().getLatitude()) + "," + Double.toString(TheMap.getMe().getGoogleMap().getMyLocation().getLongitude()), TheMap.getMe().getCacheRoutePosition());
													Thread thread = new Thread(new Runnable() {
														public void run()
														{
															while(!ServerRequests.getStaticGoogleRouteComplete())
															{
																//wait
																sleepFunction();
															}
															if(ServerRequests.getStaticGoogleRouteSuccess())
															{
																Fortitude.getFortitude().runOnUiThread(new Runnable() {
																	public void run()
																	{
																		if(staticDeleteMessagebox)
																		{
																			if(ServerRequests.getTheMessageBox() != null)
																			{
																				ServerRequests.getTheMessageBox().killMe();
																			}
																			if(MessageBox.getMe() != null)
																			{
																				MessageBox.getMe().killMe();
																			}
																		}
																	}
																});
															}
															ServerRequests.setRefreshDataSuccess(true);
															ServerRequests.setRefreshDataComplete(true);
														}
													});
													thread.start();
												}
											});
										}
									}
								});
								thread.start();
							}	
						});
					}
					else
					{
						ServerRequests.setRefreshDataSuccess(false);
						ServerRequests.setRefreshDataComplete(true);
					}
				}
				else
				{
					ServerRequests.setRefreshDataSuccess(false);
					ServerRequests.setRefreshDataComplete(true);
				}
			}
		});
		thread.start();
	}

	////////
	//
	//getGoogleMapRoute
	//
	//gets googleMaps route from google maps routes
	//
	////////
	public static void getGoogleMapRoute(String originPosition, String destinationPosition)
	{
		staticGoogleRouteSuccess = false;
		staticGoogleRouteComplete = false;

		staticOriginPosition = originPosition;
		staticDestinationPosition = destinationPosition;

		Thread thread = new Thread(new Runnable() {
			public void run()
			{
				RequestThread rt = new RequestThread() {
					public void processResponse(JSONObject response) throws Exception
					{
						if(response == null)
						{
							this.setOutputMessage("Failed To Get Google Directions");
							this.setSuccess("1");
							return;
						}
						if(response.get("status") == null)
						{
							this.setOutputMessage("Failed To Get Google Directions");
							this.setSuccess("1");
							return;
						}
						if(!response.get("status").asString().equals("OK"))
						{
							System.out.println("debug1" + " " + response.get("status").asString());
							this.setOutputMessage("Failed To Get Google Directions");
							this.setSuccess("1");
							return;
						}
						ServerRequests.setGoogleDirectionsResponse(response);
						this.setSuccess("2");
					}

				};
				rt.setURL("http://maps.googleapis.com/maps/api/directions/json?origin=" + ServerRequests.getStaticOriginPosition() + "&destination=" + ServerRequests.getStaticDestinationPosition() + "&sensor=true");
				Thread thread = new Thread(rt);
				thread.start();

				boolean connecting = false; //wait for response
				while(connecting == false)
				{
					sleepFunction();
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
								if(MessageBox.getMe() != null)
								{
									MessageBox.getMe().killMe();
								}
								ServerRequests.setTheMessageBox(MessageBox.newMsgBox(ServerRequests.getStaticOutputMessage(), true));
							}
						});
						ServerRequests.setStaticGoogleRouteSuccess(false);
						ServerRequests.setStaticGoogleRouteComplete(true);
						connecting = true;
					}
					else if(rt.getSuccess().equals("2"))
					{	
						connecting = true;
						Fortitude.getFortitude().runOnUiThread(new Runnable() {
							public void run()
							{
								TheMap.getMe().addRoute(ServerRequests.getGoogleDirectionsResponse());
								if(ServerRequests.getTheMessageBox() != null)
								{
									ServerRequests.getTheMessageBox().killMe();
								}
								if(MessageBox.getMe() != null)
								{
									MessageBox.getMe().killMe();
								}
								TheMap.getMe().setCacheRoutePosition(ServerRequests.getStaticDestinationPosition());
								ServerRequests.setStaticGoogleRouteSuccess(true);
								ServerRequests.setStaticGoogleRouteComplete(true);
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
	//placeCache
	//
	//places a cache at the given location
	//
	////////
	public static void placeCache(String latitude, String longitude, String numberOfUnits)
	{
		placeCacheComplete = false;
		placeCacheSuccess = false;
		staticLon = longitude;
		staticLat = latitude;
		unitsToPlace = numberOfUnits;
		Thread thread = new Thread(new Runnable() {
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
					ServerRequests.setPlaceCacheSuccess(false);
					ServerRequests.setPlaceCacheComplete(true);
					return;
				}

				RequestThread rt = new RequestThread() {

					public void processResponse(JSONObject response) throws Exception
					{
						if(response == null)
						{
							this.setOutputMessage("Failed To Place Cache");
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
							this.setOutputMessage("Failed To Place Cache");
							this.setSuccess("1");
							return;
						}
						this.setOutputMessage("done");
						this.setSuccess("2");
					}

				};
				try
				{
					rt.setURL("http://" + ServerIP + "/api/placecache?&uname=" + CurrentUser.getMe().getUserName() + "&session=" + CurrentUser.getMe().getSessionID() + "&units=" + ServerRequests.getUnitsToPlace() + "&" + ServerRequests.constructLocationUrlStuff(ServerRequests.getStaticLat(), ServerRequests.getStaticLon()));
				}
				catch(Exception e)
				{
					System.out.println(e.getStackTrace()[0]);
					Fortitude.getFortitude().runOnUiThread(new Runnable() {
						public void run()
						{
							if(ServerRequests.getTheMessageBox() != null)
							{
								ServerRequests.getTheMessageBox().killMe();
							}
							else if(MessageBox.getMe() != null)
							{
								MessageBox.getMe().killMe();
							}
							ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Error hashing scout url", true));	
						}
					});
					ServerRequests.setPlaceCacheSuccess(false);
					ServerRequests.setPlaceCacheComplete(true);
					return;
				}
				Thread thread = new Thread(rt);
				thread.start();
				boolean connecting = false;
				while(connecting == false)
				{
					sleepFunction();
					if(rt.getSuccess().equals("1"))
					{
						staticOutputMessage = rt.getOutputMessage();
						Fortitude.getFortitude().runOnUiThread(new Runnable() {
							public void run()
							{
								if(ServerRequests.getStaticOutputMessage().equals("auth error: session expired"))
								{
									sessionExpiredActions();
								}
								else if(ServerRequests.getStaticOutputMessage().equals("auth error: incorrect session code"))
								{
									sessionExpiredActions();
								}
								else
								{
									if(ServerRequests.getTheMessageBox() != null)
									{
										ServerRequests.getTheMessageBox().killMe();
									}
									else if(MessageBox.getMe() != null)
									{
										MessageBox.getMe().killMe();
									}
									ServerRequests.setTheMessageBox(MessageBox.newMsgBox(ServerRequests.getStaticOutputMessage(), true));
								}
							}
						});
						connecting = true;
						ServerRequests.setPlaceCacheSuccess(false);
						ServerRequests.setPlaceCacheComplete(true);
					}
					else if(rt.getSuccess().equals("2"))
					{
						connecting = true;
						ServerRequests.setPlaceCacheSuccess(true);
						ServerRequests.setPlaceCacheComplete(true);
					}
				}
			}
		});
		thread.start();
	}

	////////
	//
	//scoutCache
	//
	//gets the information on a cache
	//
	////////
	public static void scoutCache(String latitude, String longitude, String cacheIdToScout, String scoutsSent)
	{
		ServerRequests.setScoutCacheComplete(false);
		ServerRequests.setScoutCacheSuccess(false);
		staticLon = longitude;
		staticLat = latitude;
		staticCacheIdToScout = cacheIdToScout;
		staticScoutsSent = scoutsSent;

		Thread thread = new Thread(new Runnable() {
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
					ServerRequests.setScoutCacheSuccess(false);
					ServerRequests.setScoutCacheComplete(true);
					return;
				}

				RequestThread rt = new RequestThread() {

					public void processResponse(JSONObject response) throws Exception
					{
						if(response == null)
						{
							this.setOutputMessage("Failed To Scout Cache");
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
							this.setOutputMessage("Failed To Scout Cache");
							this.setSuccess("1");
							return;
						}
						ServerRequests.setScoutCacheResponse(response);
						this.setSuccess("2");
					}
				};
				try
				{
					rt.setURL("http://" + ServerIP + "/api/scout?units=" + ServerRequests.getStaticScoutsSent() + "&cacheid=" + ServerRequests.getCacheIdToScout() + "&uname=" + CurrentUser.getMe().getUserName() + "&session=" + CurrentUser.getMe().getSessionID() + "&" + ServerRequests.constructLocationUrlStuff(ServerRequests.getStaticLat(), ServerRequests.getStaticLon()));
				}
				catch(Exception e)
				{
					System.out.println(e.getStackTrace()[0]);
					Fortitude.getFortitude().runOnUiThread(new Runnable() {
						public void run()
						{
							if(ServerRequests.getTheMessageBox() != null)
							{
								ServerRequests.getTheMessageBox().killMe();
							}
							else if(MessageBox.getMe() != null)
							{
								MessageBox.getMe().killMe();
							}
							ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Error hashing scout url", true));	
						}
					});
					ServerRequests.setScoutCacheSuccess(false);
					ServerRequests.setScoutCacheComplete(true);
					return;
				}
				Thread thread = new Thread(rt);
				thread.start();
				boolean connecting = false;
				while(connecting == false)
				{
					sleepFunction();
					if(rt.getSuccess().equals("1"))
					{
						staticOutputMessage = rt.getOutputMessage();
						Fortitude.getFortitude().runOnUiThread(new Runnable() {
							public void run()
							{
								if(ServerRequests.getStaticOutputMessage().equals("auth error: session expired"))
								{
									sessionExpiredActions();
								}
								else if(ServerRequests.getStaticOutputMessage().equals("auth error: incorrect session code"))
								{
									sessionExpiredActions();
								}
								else
								{
									if(ServerRequests.getTheMessageBox() != null)
									{
										ServerRequests.getTheMessageBox().killMe();
									}
									else if(MessageBox.getMe() != null)
									{
										MessageBox.getMe().killMe();
									}
									ServerRequests.setTheMessageBox(MessageBox.newMsgBox(ServerRequests.getStaticOutputMessage(), true));
								}
							}
						});
						connecting = true;
						ServerRequests.setScoutCacheSuccess(false);
						ServerRequests.setScoutCacheComplete(true);
					}
					else if(rt.getSuccess().equals("2"))
					{
						connecting = true;
						staticOutputMessage = rt.getOutputMessage();
						Fortitude.getFortitude().runOnUiThread(new Runnable() {
							public void run()
							{
								ServerRequests.setScoutCacheSuccess(true);
								ServerRequests.setScoutCacheComplete(true);
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
	//attackCache
	//
	//attacks the cache the user is at with a number of units from the logged in users balance
	//
	////////
	public static void attackCache(String latitude, String longitude, String attackUnits, String cacheId)
	{
		attackCacheComplete = false;
		attackCacheSuccess = false;

		staticLat = latitude;
		staticLon = longitude;
		cacheIdToAttack = cacheId;
		unitsToAttackWith = attackUnits;

		Thread thread = new Thread(new Runnable() {
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
					ServerRequests.setAttackCacheSuccess(false);
					ServerRequests.setAttackCacheComplete(true);
					return;
				}

				RequestThread rt = new RequestThread() {

					public void processResponse(JSONObject response) throws Exception
					{
						if(response == null)
						{
							this.setOutputMessage("Failed To Issue Attack Cache Command");
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
							this.setOutputMessage("Failed To Place Attack Cache Command");
							this.setSuccess("1");
							return;
						}
						this.setOutputMessage("done");
						ServerRequests.setAttackCacheResponse(response);
						this.setSuccess("2");
					}

				};
				try
				{
					rt.setURL("http://" + ServerIP + "/api/attack?&uname=" + CurrentUser.getMe().getUserName() + "&session=" + CurrentUser.getMe().getSessionID() + "&units=" + ServerRequests.getUnitsToAttackWith() + "&cacheid=" + ServerRequests.getCacheIdToAttack() + "&" + ServerRequests.constructLocationUrlStuff(ServerRequests.getStaticLat(), ServerRequests.getStaticLon()));
				}
				catch(Exception e)
				{
					System.out.println(e.getStackTrace()[0]);
					Fortitude.getFortitude().runOnUiThread(new Runnable() {
						public void run()
						{
							if(ServerRequests.getTheMessageBox() != null)
							{
								ServerRequests.getTheMessageBox().killMe();
							}
							else if(MessageBox.getMe() != null)
							{
								MessageBox.getMe().killMe();
							}
							ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Error hashing attack url", true));	
						}
					});
					ServerRequests.setAttackCacheSuccess(false);
					ServerRequests.setAttackCacheComplete(true);
					return;
				}
				Thread thread = new Thread(rt);
				thread.start();
				boolean connecting = false;
				while(connecting == false)
				{
					sleepFunction();
					if(rt.getSuccess().equals("1"))
					{
						staticOutputMessage = rt.getOutputMessage();
						Fortitude.getFortitude().runOnUiThread(new Runnable() {
							public void run()
							{
								if(ServerRequests.getStaticOutputMessage().equals("auth error: session expired"))
								{
									sessionExpiredActions();
								}
								else if(ServerRequests.getStaticOutputMessage().equals("auth error: incorrect session code"))
								{
									sessionExpiredActions();
								}
								else
								{
									if(ServerRequests.getTheMessageBox() != null)
									{
										ServerRequests.getTheMessageBox().killMe();
									}
									else if(MessageBox.getMe() != null)
									{
										MessageBox.getMe().killMe();
									}
									ServerRequests.setTheMessageBox(MessageBox.newMsgBox(ServerRequests.getStaticOutputMessage(), true));
								}
							}
						});
						connecting = true;
						ServerRequests.setAttackCacheSuccess(false);
						ServerRequests.setAttackCacheComplete(true);
					}
					else if(rt.getSuccess().equals("2"))
					{
						connecting = true;
						staticOutputMessage = rt.getOutputMessage();
						ServerRequests.setAttackCacheSuccess(true);
						ServerRequests.setAttackCacheComplete(true);
					}
				}	
			}
		});
		thread.start();
	}

	////////
	//
	//constructLocationUrlStuff
	//
	//returns part of the url required for location verification requests (place cache etc...)
	//
	////////
	public static String constructLocationUrlStuff(String latitude, String longitude) throws UnsupportedEncodingException, NoSuchAlgorithmException
	{
		final byte[] salt = new byte[] {((byte)0x2a), ((byte)0x1e), ((byte)0x97), ((byte)0xab), ((byte)0x2b), ((byte)0xb1), ((byte)0x3c)};

		int i = 0;
		int s = 0;
		String theTimeStamp = Long.toString((System.currentTimeMillis() / 1000));
		byte[] hashArray = new byte[latitude.length() + longitude.length() + theTimeStamp.length() + salt.length];
		hashArray[i++] = salt[s++];
		hashArray[i++] = salt[s++];
		byte[] tempBytes = latitude.getBytes("UTF-8");
		for(byte b : tempBytes)
		{
			hashArray[i++] = b;
		}
		hashArray[i++] = salt[s++];
		hashArray[i++] = salt[s++];
		tempBytes = longitude.getBytes("UTF-8");
		for(byte b : tempBytes)
		{
			hashArray[i++] = b;
		}
		hashArray[i++] = salt[s++];
		tempBytes = theTimeStamp.getBytes("UTF-8");
		for(byte b : tempBytes)
		{
			hashArray[i++] = b;
		}
		hashArray[i++] = salt[s++];
		hashArray[i++] = salt[s++];
		MessageDigest m = MessageDigest.getInstance("MD5");
		byte[] digest = m.digest(hashArray);
		StringBuffer builder = new StringBuffer();
		for(int ii = 0; ii < digest.length; ++ii) {
			builder.append(Integer.toHexString((digest[ii] & 0xFF) | 0x100).substring(1, 3));
		}
		return "lat=" + latitude + "&lng=" + longitude + "&time=" + theTimeStamp + "&hash=" + builder.toString();
	}

	public static void sessionExpiredActions()
	{
		GUI.killAll();
		if(ServerRequests.getTheMessageBox() != null)
		{
			ServerRequests.getTheMessageBox().killMe();
		}
		MessageBox.newMsgBox("You have been signed out due to inactivity or because the server has been reset", false);
		Thread thread = new Thread(new Runnable() {
			public void run()
			{
				try
				{
					Thread.sleep(4000);
				}
				catch(Exception e)
				{
					//do nothing
				}
				Fortitude.getFortitude().runOnUiThread(new Runnable() {
					public void run()
					{
						if(ServerRequests.getTheMessageBox() != null)
						{
							ServerRequests.getTheMessageBox().killMe();
						}
						if(MessageBox.getMe() != null)
						{
							MessageBox.getMe().killMe();
						}
						GUI.killAll();
						FileSave fs = new FileSave();
						String username = fs.OpenFileDialog("username");
						String password = fs.OpenFileDialog("password");
						if((username == null) || (password == null))
						{
							new MainLoginScreen();
						}
						else if((username.equals("")) || (password.equals("")))
						{
							new MainLoginScreen();
						}
						else
						{
							new SplashScreen();
							Thread thread = new Thread(new Runnable() {
								public void run()
								{
									try
									{
										Thread.sleep(1000);
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
					}
				});
			}
		});
		thread.start();
	}

	public static void resendActivationEmail(String emailAddress)
	{
		staticEmail = emailAddress;
		Thread thread = new Thread(new Runnable() {
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
					return;
				}

				RequestThread rt = new RequestThread() {

					public void processResponse(JSONObject response) throws Exception
					{
						if(response == null)
						{
							this.setOutputMessage("Failed To Send Email, Please Try Again");
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
							this.setOutputMessage("Failed To Send Email, Please Try Again");
							this.setSuccess("1");
							return;
						}
						this.setOutputMessage("done");
						this.setSuccess("2");
					}

				};
				rt.setURL("http://" + ServerIP + "/api/sendverify?email=" + ServerRequests.getStaticEmail());
				Thread thread = new Thread(rt);
				thread.start();
				boolean connecting = false;
				while(connecting == false)
				{
					sleepFunction();
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
								else if(MessageBox.getMe() != null)
								{
									MessageBox.getMe().killMe();
								}
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
								if(ServerRequests.getTheMessageBox() != null)
								{
									ServerRequests.getTheMessageBox().killMe();
								}
								else if(MessageBox.getMe() != null)
								{
									MessageBox.getMe().killMe();
								}
								ActivateUserScreen.getMe().killMe();
								new MainScreen();
								MessageBox.newMsgBox("We have resent an activation email to your email account", true);
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
	//getNewsStubs
	//
	//gets a given number of stubs message stubs before a certain period in time 
	//
	////////
	public static void getNewsStubs(long timestamp, int numberOfStubs)
	{
		getNewsStubs(timestamp, numberOfStubs, "All");
	}
	public static void getNewsStubs(long timestamp, int numberOfStubs, String newsFilter)
	{
		staticNumberOfStubs = numberOfStubs;
		staticTimeStamp = timestamp;
		staticNewsFilter = newsFilter;

		ServerRequests.staticGetNewsComplete = false;
		ServerRequests.staticGetNewsSuccess = false;

		Thread thread = new Thread(new Runnable() {
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
					ServerRequests.setStaticGetNewsSuccess(false);
					ServerRequests.setStaticGetNewsComplete(true);
					return;
				}

				RequestThread rt = new RequestThread() {

					public void processResponse(JSONObject response) throws Exception
					{
						if(response == null)
						{
							this.setOutputMessage("Failed To Get News, Please Try Again");
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
							this.setOutputMessage("Failed To Get News, Please Try Again");
							this.setSuccess("1");
							return;
						}
						for(int i = 0; i < response.get("items").length(); i++)
						{
							JSONObject item = (JSONObject) response.get("items").get(i);
							int notificationid = item.get("notificationid").asInteger();
							int receiverid = item.get("receiverid").asInteger();
							int timestamp = item.get("timestamp").asInteger();
							String type = item.get("type").asString();
							String status = item.get("status").asString();
							NotificationManager.addStub(new NotificationStub(notificationid, receiverid, timestamp, type, status));
						}
						this.setOutputMessage("done");
						this.setSuccess("2");
					}

				};
				rt.setURL("http://" + ServerIP + "/api/newsstubs?uname=" + CurrentUser.getMe().getUserName() + "&phash=" + CurrentUser.getMe().getPhash() + "&count=" + ServerRequests.getStaticNumberOfStubs() + "&since=" + ServerRequests.getStaticTimeStamp() + "&filter=" + ServerRequests.getStaticNewsFilter());
				Thread thread = new Thread(rt);
				thread.start();
				boolean connecting = false;
				while(connecting == false)
				{
					sleepFunction();
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
								else if(MessageBox.getMe() != null)
								{
									MessageBox.getMe().killMe();
								}
								ServerRequests.setTheMessageBox(MessageBox.newMsgBox(ServerRequests.getStaticOutputMessage(), true));
							}
						});
						ServerRequests.setStaticGetNewsSuccess(false);
						ServerRequests.setStaticGetNewsComplete(true);
						connecting = true;
					}
					else if(rt.getSuccess().equals("2"))
					{
						connecting = true;
						ServerRequests.setStaticGetNewsSuccess(true);
						ServerRequests.setStaticGetNewsComplete(true);
					}
				}	
			}
		});
		thread.start();
	}

	////////
	//
	//depositUnits
	//
	//Transfer a number of units from an account to a cache owned by that account, or vice versa.
	//use a negative number to withdraw
	//
	////////
	public static void depositUnits(String latitude, String longitude, String cacheId, String units)
	{
		ServerRequests.setStaticDepositComplete(false);
		ServerRequests.setStaticDepositSuccess(false);
		staticLon = longitude;
		staticLat = latitude;
		staticCacheIdToScout = cacheId;
		staticScoutsSent = units;

		Thread thread = new Thread(new Runnable() {
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
					ServerRequests.setStaticDepositComplete(false);
					ServerRequests.setStaticDepositSuccess(true);
					return;
				}

				RequestThread rt = new RequestThread() {

					public void processResponse(JSONObject response) throws Exception
					{
						if(response == null)
						{
							this.setOutputMessage("Failed To Transfer Units");
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
							this.setOutputMessage("Failed To Transfer Units");
							this.setSuccess("1");
							return;
						}
						this.setOutputMessage("DONE!");
						this.setSuccess("2");
					}
				};
				try
				{
					rt.setURL("http://" + ServerIP + "/api/transfer?units=" + ServerRequests.getStaticScoutsSent() + "&cacheid=" + ServerRequests.getCacheIdToScout() + "&uname=" + CurrentUser.getMe().getUserName() + "&session=" + CurrentUser.getMe().getSessionID() + "&" + ServerRequests.constructLocationUrlStuff(ServerRequests.getStaticLat(), ServerRequests.getStaticLon()));
				}
				catch(Exception e)
				{
					System.out.println(e.getStackTrace()[0]);
					Fortitude.getFortitude().runOnUiThread(new Runnable() {
						public void run()
						{
							if(ServerRequests.getTheMessageBox() != null)
							{
								ServerRequests.getTheMessageBox().killMe();
							}
							else if(MessageBox.getMe() != null)
							{
								MessageBox.getMe().killMe();
							}
							ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Error hashing transfer url", true));	
						}
					});
					ServerRequests.setStaticDepositSuccess(false);
					ServerRequests.setStaticDepositComplete(true);
					return;
				}
				Thread thread = new Thread(rt);
				thread.start();
				boolean connecting = false;
				while(connecting == false)
				{
					sleepFunction();
					if(rt.getSuccess().equals("1"))
					{
						staticOutputMessage = rt.getOutputMessage();
						Fortitude.getFortitude().runOnUiThread(new Runnable() {
							public void run()
							{
								if(ServerRequests.getStaticOutputMessage().equals("auth error: session expired"))
								{
									sessionExpiredActions();
								}
								else if(ServerRequests.getStaticOutputMessage().equals("auth error: incorrect session code"))
								{
									sessionExpiredActions();
								}
								else
								{
									if(ServerRequests.getTheMessageBox() != null)
									{
										ServerRequests.getTheMessageBox().killMe();
									}
									else if(MessageBox.getMe() != null)
									{
										MessageBox.getMe().killMe();
									}
									ServerRequests.setTheMessageBox(MessageBox.newMsgBox(ServerRequests.getStaticOutputMessage(), true));
								}
							}
						});
						connecting = true;
						ServerRequests.setStaticDepositSuccess(false);
						ServerRequests.setStaticDepositComplete(true);
					}
					else if(rt.getSuccess().equals("2"))
					{
						connecting = true;
						staticOutputMessage = rt.getOutputMessage();
						ServerRequests.setStaticDepositSuccess(true);
						ServerRequests.setStaticDepositComplete(true);
					}
				}
			}
		});
		thread.start();
	}

	private static void sleepFunction()
	{
		try
		{
			Thread.sleep(100);
		}
		catch(Exception e)
		{
			//do nothing
		}
	}
}