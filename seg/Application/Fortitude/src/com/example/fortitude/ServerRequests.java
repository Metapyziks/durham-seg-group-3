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
	private static int staticUserBalance;
	private static String usersToGet = null;
	private static boolean staticInitialLogin;
	private static String staticSessionId;
	private static int staticTotalUnits;
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
	private static boolean staticGetSpecialCachesComplete;
	private static boolean staticGetSpecialCachesSuccess;
	private static boolean staticClaimSpecialComplete;
	private static boolean staticClaimSpecialSuccess;
	private static String staticMacAddress;
	private static boolean staticReadMessageComplete;
	private static boolean staticReadMessageSuccess;
	private static String messageContent;
	private static String messageSenderName;
	private static String messageSubject;
	private static boolean sendMessageComplete;
	private static boolean sendMessageSuccess;
	private static boolean battleReportSuccess;
	private static boolean battleReportComplete;
	private static int staticMessageId;
	private static boolean blockUserSuccess;
	private static boolean blockUserComplete;
	private static boolean blockTrue;
	private static String usernameToBeBlocked;
	private static boolean getBlockedUsersSuccessful;
	private static boolean getBlockedUsersComplete;
	private static boolean getUpdateSettingComplete;
	private static boolean getUpdateSettingSuccess;
	public static String settingName;
	public static String newVal;
	public static boolean reportThingSuccess;
	public static boolean reportThingComplete;
	public static int reportId;
	public static String reportType;

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
	public static void setGetUpdateSettingComplete(boolean x)
	{
		getUpdateSettingComplete = x;
	}

	public static void setGetUpdateSettingSuccess(boolean x)
	{
		getUpdateSettingSuccess = x;
	}

	public static boolean getUpdateSettingSuccess()
	{
		return getUpdateSettingSuccess;
	}

	public static boolean getUpdateSettingComplete()
	{
		return getUpdateSettingComplete;
	}

	public static void setGetBlockedUsersComplete(boolean x)
	{
		getBlockedUsersComplete = x;
	}

	public static void setGetBlockedUsersSuccessful(boolean x)
	{
		getBlockedUsersSuccessful = x;
	}

	public static boolean getGetBlockedUsersSuccessful()
	{
		return getBlockedUsersSuccessful;
	}

	public static boolean getGetBlockedUsersComplete()
	{
		return getBlockedUsersComplete;
	}

	public static String getUsernameToBeBlocked()
	{
		return usernameToBeBlocked;
	}

	public static boolean getBlockTrue()
	{
		return blockTrue;
	}

	public static void setBlockUserSuccess(boolean x)
	{
		blockUserSuccess = x;
	}

	public static void setBlockUserComplete(boolean x)
	{
		blockUserComplete = x;
	}

	public static boolean getBlockUserSuccess()
	{
		return blockUserSuccess;
	}

	public static boolean getBlockUserComplete()
	{
		return blockUserComplete;
	}

	public static void setBattleReportComplete(boolean x)
	{
		battleReportComplete = x;
	}

	public static void setBattleReportSuccess(boolean x)
	{
		battleReportSuccess = x;
	}

	public static boolean getBattleReportComplete()
	{
		return battleReportComplete;
	}

	public static boolean getBattleReportSuccess()
	{
		return battleReportSuccess;
	}

	public static void setSendMessageSuccess(boolean x)
	{
		sendMessageSuccess = x;
	}

	public static void setSendMessageComplete(boolean x)
	{
		sendMessageComplete = x;
	}

	public static boolean getSendMessageSuccess()
	{
		return sendMessageSuccess;
	}

	public static boolean getSendMessageComplete()
	{
		return sendMessageComplete;
	}

	public static String getMessageSubject()
	{
		return messageSubject;
	}

	public static String getMessageSenderName()
	{
		return messageSenderName;
	}

	public static int getStaticMessageId()
	{
		return staticMessageId;
	}

	public static String getMessageContent()
	{
		return messageContent;
	}

	public static void setStaticReadMessageSuccess(boolean x)
	{
		staticReadMessageSuccess = x;
	}

	public static void setStaticReadMessageComplete(boolean x)
	{
		staticReadMessageComplete = x;
	}

	public static boolean getStaticReadMessageSuccess()
	{
		return staticReadMessageSuccess;
	}

	public static boolean getStaticReadMessageComplete()
	{
		return staticReadMessageComplete;
	}

	public static String getStaticMacAddress()
	{
		return staticMacAddress;
	}

	public static boolean getStaticClaimSpecialComplete()
	{
		return staticClaimSpecialComplete;
	}

	public static boolean getStaticClaimSpecialSuccess()
	{
		return staticClaimSpecialSuccess;
	}

	public static void setStaticClaimSpecialComplete(boolean x)
	{
		staticClaimSpecialComplete = x;
	}

	public static void setStaticClaimSpecialSuccess(boolean x)
	{
		staticClaimSpecialSuccess = x;
	}

	public static void setStaticGetSpecialCachesComplete(boolean x)
	{
		staticGetSpecialCachesComplete = x;
	}

	public static void setStaticGetSpecialCachesSuccess(boolean x)
	{
		staticGetSpecialCachesSuccess = x;
	}

	public static boolean getStaticGetSpecialCachesComplete()
	{
		return staticGetSpecialCachesComplete;
	}

	public static boolean getStaticGetSpecialCachesSuccess()
	{
		return staticGetSpecialCachesSuccess;
	}

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

	public static int getStaticTotalUnits()
	{
		return staticTotalUnits;
	}

	public static void setStaticTotalUnits(int x)
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

	public static void setStaticUserBalance(int x)
	{
		staticUserBalance = x;
	}

	public static int getStaticUserBalance()
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

		NotificationManager.clearStubs();
		BlockedManager.clearBlocked();

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
				try
				{
					rt.setURL("http://" + ServerIP + "/api/session?uname=" + encode(ServerRequests.getStaticUname()) + "&phash=" + encode(ServerRequests.getStaticPhash()));
				}
				catch(Exception e)
				{
					signInError();
					return;
				}
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
																if(CurrentUser.getMe().isVerified())
																{
																	Fortitude.getFortitude().runOnUiThread(new Runnable() {
																		public void run()
																		{
																			ServerRequests.getNewsStubs(System.currentTimeMillis(), 30);
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
																								ServerRequests.getSpecialCaches();
																								Thread thread = new Thread(new Runnable() {
																									public void run()
																									{
																										while(!ServerRequests.getStaticGetSpecialCachesComplete())
																										{
																											ServerRequests.sleepFunction();
																										}
																										if(ServerRequests.getStaticGetSpecialCachesSuccess())
																										{
																											Fortitude.getFortitude().runOnUiThread(new Runnable() {
																												public void run()
																												{
																													ServerRequests.getBlocked();
																													Thread thread = new Thread(new Runnable() {
																														public void run()
																														{
																															while(!ServerRequests.getGetBlockedUsersComplete())
																															{
																																ServerRequests.sleepFunction();
																															}
																															if(ServerRequests.getGetBlockedUsersSuccessful())
																															{
																																Fortitude.getFortitude().runOnUiThread(new Runnable() {
																																	public void run()
																																	{
																																		ServerRequests.setting("receivemessages");
																																		Thread thread = new Thread(new Runnable() {
																																			public void run()
																																			{
																																				while(!ServerRequests.getUpdateSettingComplete())
																																				{
																																					ServerRequests.sleepFunction();
																																				}
																																				if(ServerRequests.getUpdateSettingSuccess())
																																				{
																																					if(ServerRequests.settingName.equals("Default"))
																																					{
																																						CurrentUser.getMe().setMessagesActive(true);
																																					}
																																					else
																																					{
																																						CurrentUser.getMe().setMessagesActive(false);
																																					}
																																					cacheRefresh();
																																				}
																																				else
																																				{
																																					signInError();
																																				}
																																			}
																																		});
																																		thread.start();
																																	}
																																});
																															}
																															else
																															{
																																signInError();
																															}
																														}
																													});
																													thread.start();
																												}
																											});
																										}
																										else
																										{
																											signInError();
																										}
																									}
																								});
																								thread.start();
																							}
																						});
																					}
																					else
																					{
																						signInError();
																					}
																				}
																			});
																			thread.start();
																		}
																	});
																}
																else
																{
																	cacheRefresh();
																}
															}
															else
															{
																signInError();
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
										signInError();
									}
								}
							});
							getUserInfoThread.start();
						}
						if(rt.getSuccess().equals("1"))
						{
							signInError();
						}
						connecting = true;
					}
				}

			}
		};
		Thread thread = new Thread(runnable);
		thread.start();
	}

	public static void cacheRefresh()
	{
		Fortitude.getFortitude().runOnUiThread(new Runnable() {
			public void run()
			{
				if(TheMap.getGotInitialLocation())
				{
					if(TheMap.getMe() != null)
					{
						TheMap.getMe().updateCachePositions();
						while(ServerRequests.getGetNearbyCachesInfoStatus() == 0)
						{
							ServerRequests.sleepFunction();
						}
						if(ServerRequests.getGetNearbyCachesInfoStatus() == 2)
						{
							Thread thread = new Thread(new Runnable() {
								public void run()
								{

									signInSuccess();
								}
							});
							thread.start();
						}
						else
						{
							signInError();
						}
					}
					else
					{
						Thread thread = new Thread(new Runnable() {
							public void run()
							{

								signInSuccess();
							}
						});
						thread.start();
					}
				}
				else
				{
					Thread thread = new Thread(new Runnable() {
						public void run()
						{

							signInSuccess();
						}
					});
					thread.start();
				}
			}
		});
	}
	
	public static void signInSuccess()
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
						ServerRequests.setStaticUserBalance(response.get("balance").asInteger());
						ServerRequests.setStaticTotalUnits(response.get("garrison").asInteger());
						ServerRequests.setStaticCacheCount(response.get("cachecount").asString());
						this.setSuccess("2");
					}

				};
				try
				{
					rt.setURL("http://" + ServerIP + "/api/userstats?uname=" + encode(ServerRequests.getStaticUname()) + "&session=" + encode(staticSessionId));
				}
				catch(Exception e)
				{
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
							ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Error hashing url", true));	
						}
					});
					ServerRequests.setGetUserBalanceSuccess(false);
					ServerRequests.setGetUserBalanceComplete(true);
					return;
				}
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
										if(ServerRequests.getTheMessageBox() != null)
										{
											ServerRequests.getTheMessageBox().killMe();
										}
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

	public static void signInError()
	{
		if(ServerRequests.getStaticOutputMessage().equals("auth error: incorrect credentials"))
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
							AutoSignInErrorScreen.getMe().getErrorBox().setText(ServerRequests.getStaticOutputMessage());
						}
						else
						{
							if(AutoSignInErrorScreen.getMe() != null)
							{
								AutoSignInErrorScreen.getMe().getErrorBox().setText(ServerRequests.getStaticOutputMessage());
							}
							else
							{
								ServerRequests.setTheMessageBox(MessageBox.newMsgBox(ServerRequests.getStaticOutputMessage(), true));
							}
						}
					}
				});
			}
		}
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
				try
				{
					rt.setURL("http://" + ServerIP + "/api/register?uname=" + encode(ServerRequests.getStaticUname()) + "&phash=" + encode(ServerRequests.getStaticPhash()) + "&email=" + encode(ServerRequests.getStaticEmail()));
				}
				catch(Exception e)
				{
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
							ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Error hashing url", true));	
						}
					});
					return;
				}
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
				try
				{
					rt.setURL("http://" + ServerIP + "/api/sendpassreset?email=" + encode(ServerRequests.getStaticEmail()));
				}
				catch(Exception e)
				{
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
							ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Error hashing url", true));	
						}
					});
					return;
				}
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
							int accountid = response.get("users").get(i).get("accountid").asInteger();
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
				try
				{
					if(ServerRequests.getGetUserInfoUseUsernames())
					{
						rt.setURL("http://" + ServerIP + "/api/userinfo?unames=" + encode(ServerRequests.getUsersToGet()));
					}
					else
					{
						rt.setURL("http://" + ServerIP + "/api/userinfo?uids=" + encode(ServerRequests.getUsersToGet()));
					}
				}
				catch(Exception e)
				{
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
							ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Error hashing url", true));	
						}
					});
					ServerRequests.setGetUserInfoSuccess(false);
					ServerRequests.setGetUserInfoComplete(true);
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
							int cacheid = response.get("caches").get(i).get("cacheid").asInteger();
							int ownerid = response.get("caches").get(i).get("ownerid").asInteger();
							String name = response.get("caches").get(i).get("name").asString();
							Double latitude = response.get("caches").get(i).get("latitude").asDouble();
							Double longitude = response.get("caches").get(i).get("longitude").asDouble();
							int garrison = response.get("caches").get(i).get("garrison").asInteger();
							Cache c = new Cache(cacheid, ownerid, name, latitude, longitude, garrison);
							ServerRequests.getNearbyCaches().add(c);
						}
						this.setSuccess("2");
					}

				};
				try
				{
					rt.setURL("http://" + ServerIP + "/api/nearbycaches?uname=" + encode(ServerRequests.getStaticUname()) + "&session=" + encode(ServerRequests.getStaticSessionId()) + "&lat=" + encode(ServerRequests.getStaticLat()) + "&lon=" + encode(ServerRequests.getStaticLon()) + "&radius=" + encode(ServerRequests.getCacheRadius()));
				}
				catch(Exception e)
				{
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
							ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Error hashing url", true));	
						}
					});
					ServerRequests.setGetNearbyCachesInfoStatus(1);
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
						CurrentUser.getMe().setGarrison(ServerRequests.getStaticTotalUnits());
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
								ServerRequests.getNewsStubs(System.currentTimeMillis(), 30);
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
													ServerRequests.getSpecialCaches();
													Thread thread = new Thread(new Runnable() {
														public void run()
														{
															while(!ServerRequests.getStaticGetSpecialCachesComplete())
															{
																//wait
																ServerRequests.sleepFunction();
															}
															if(ServerRequests.getStaticGetSpecialCachesSuccess())
															{
																Fortitude.getFortitude().runOnUiThread(new Runnable() {
																	public void run()
																	{
																		ServerRequests.getBlocked();
																		Thread thread = new Thread(new Runnable() {
																			public void run()
																			{
																				while(!ServerRequests.getGetBlockedUsersComplete())
																				{
																					ServerRequests.sleepFunction();
																				}
																				if(ServerRequests.getGetBlockedUsersSuccessful())
																				{
																					Fortitude.getFortitude().runOnUiThread(new Runnable() {
																						public void run()
																						{
																							ServerRequests.setting("receivemessages");
																							Thread thread = new Thread(new Runnable() {
																								public void run()
																								{
																									while(!ServerRequests.getUpdateSettingComplete)
																									{
																										ServerRequests.sleepFunction();
																									}
																									if(ServerRequests.getUpdateSettingSuccess())
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
																									else
																									{
																										ServerRequests.setRefreshDataSuccess(false);
																										ServerRequests.setRefreshDataComplete(true);
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
							this.setOutputMessage("Failed To Get Google Directions");
							this.setSuccess("1");
							return;
						}
						ServerRequests.setGoogleDirectionsResponse(response);
						this.setSuccess("2");
					}

				};
				try
				{
					rt.setURL("http://maps.googleapis.com/maps/api/directions/json?origin=" + encode(ServerRequests.getStaticOriginPosition()) + "&destination=" + encode(ServerRequests.getStaticDestinationPosition()) + "&sensor=true");
				}
				catch(Exception e)
				{
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
							ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Error hashing url", true));	
						}
					});
					ServerRequests.setStaticGoogleRouteSuccess(false);
					ServerRequests.setStaticGoogleRouteComplete(true);
					return;
				}
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
					rt.setURL("http://" + ServerIP + "/api/placecache?&uname=" + encode(CurrentUser.getMe().getUserName()) + "&session=" + encode(CurrentUser.getMe().getSessionID()) + "&units=" + encode(ServerRequests.getUnitsToPlace()) + "&" + ServerRequests.constructLocationUrlStuff(ServerRequests.getStaticLat(), ServerRequests.getStaticLon()));
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
							ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Error hashing url", true));	
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
					rt.setURL("http://" + ServerIP + "/api/scout?units=" + encode(ServerRequests.getStaticScoutsSent()) + "&cacheid=" + encode(ServerRequests.getCacheIdToScout()) + "&uname=" + encode(CurrentUser.getMe().getUserName()) + "&session=" + encode(CurrentUser.getMe().getSessionID()) + "&" + ServerRequests.constructLocationUrlStuff(ServerRequests.getStaticLat(), ServerRequests.getStaticLon()));
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
					rt.setURL("http://" + ServerIP + "/api/attack?&uname=" + encode(CurrentUser.getMe().getUserName()) + "&session=" + encode(CurrentUser.getMe().getSessionID()) + "&units=" + encode(ServerRequests.getUnitsToAttackWith()) + "&cacheid=" + encode(ServerRequests.getCacheIdToAttack()) + "&" + ServerRequests.constructLocationUrlStuff(ServerRequests.getStaticLat(), ServerRequests.getStaticLon()));
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
	public static String constructLocationUrlStuff(String latitude, String longitude) throws UnsupportedEncodingException, NoSuchAlgorithmException, Exception
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
		return "lat=" + encode(latitude) + "&lng=" + encode(longitude) + "&time=" + encode(theTimeStamp) + "&hash=" + encode(builder.toString());
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
				try
				{
					rt.setURL("http://" + ServerIP + "/api/sendverify?email=" + encode(ServerRequests.getStaticEmail()));
				}
				catch(Exception e)
				{
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
							ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Error hashing url", true));	
						}
					});
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
							String person = "";
							String context = "";
							if(type.equals("Message"))
							{
								person = item.get("sender").asString();
								context = item.get("subject").asString();
							}
							else if(type.equals("BattleReport"))
							{
								person = item.get("attacker").asString();
								context = item.get("cache").asString();
							}
							NotificationManager.addStub(new NotificationStub(notificationid, receiverid, timestamp, type, status, person, context));
						}
						this.setOutputMessage("done");
						this.setSuccess("2");
					}

				};
				try
				{
					rt.setURL("http://" + ServerIP + "/api/newsstubs?uname=" + encode(CurrentUser.getMe().getUserName()) + "&phash=" + encode(CurrentUser.getMe().getPhash()) + "&count=" + encode(ServerRequests.getStaticNumberOfStubs()) + "&since=" + encode(ServerRequests.getStaticTimeStamp()) + "&filter=" + encode(ServerRequests.getStaticNewsFilter()));
				}
				catch(Exception e)
				{
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
							ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Error hashing url", true));	
						}
					});
					ServerRequests.setStaticGetNewsSuccess(false);
					ServerRequests.setStaticGetNewsComplete(true);
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
					rt.setURL("http://" + ServerIP + "/api/transfer?units=" + encode(ServerRequests.getStaticScoutsSent()) + "&cacheid=" + encode(ServerRequests.getCacheIdToScout()) + "&uname=" + encode(CurrentUser.getMe().getUserName()) + "&session=" + encode(CurrentUser.getMe().getSessionID()) + "&" + ServerRequests.constructLocationUrlStuff(ServerRequests.getStaticLat(), ServerRequests.getStaticLon()));
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

	public static void getSpecialCaches()
	{
		ServerRequests.setStaticGetSpecialCachesComplete(false);
		ServerRequests.setStaticGetSpecialCachesSuccess(false);

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
					ServerRequests.setStaticGetSpecialCachesSuccess(false);
					ServerRequests.setStaticGetSpecialCachesComplete(true);
					return;
				}

				RequestThread rt = new RequestThread() {

					public void processResponse(JSONObject response) throws Exception
					{
						if(response == null)
						{
							this.setOutputMessage("Failed To Get Special Caches");
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
							this.setOutputMessage("Failed To Get Special Caches");
							this.setSuccess("1");
							return;
						}
						MACManager.clearMacs();
						JSONValue[] addresses = response.get("addresses").asArray();
						for(JSONValue address : addresses)
						{
							MACManager.addMacAddress(address.asString());
						}
						this.setOutputMessage("DONE!");
						this.setSuccess("2");
					}
				};
				try
				{
					rt.setURL("http://" + ServerIP + "/api/specialevents?uname=" + encode(CurrentUser.getMe().getUserName()) + "&session=" + encode(CurrentUser.getMe().getSessionID()));
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
					ServerRequests.setStaticGetSpecialCachesSuccess(false);
					ServerRequests.setStaticGetSpecialCachesComplete(true);
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
						ServerRequests.setStaticGetSpecialCachesSuccess(false);
						ServerRequests.setStaticGetSpecialCachesComplete(true);
					}
					else if(rt.getSuccess().equals("2"))
					{
						connecting = true;
						staticOutputMessage = rt.getOutputMessage();
						ServerRequests.setStaticGetSpecialCachesSuccess(true);
						ServerRequests.setStaticGetSpecialCachesComplete(true);
					}
				}
			}
		});
		thread.start();
	}

	public static void claimSpecial(String macAddress)
	{
		staticClaimSpecialComplete = false;
		staticClaimSpecialSuccess = false;
		staticMacAddress = macAddress;

		ServerRequests.setStaticClaimSpecialComplete(false);
		ServerRequests.setStaticClaimSpecialSuccess(false);

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
					ServerRequests.setStaticClaimSpecialSuccess(false);
					ServerRequests.setStaticClaimSpecialComplete(true);
					return;
				}

				RequestThread rt = new RequestThread() {

					public void processResponse(JSONObject response) throws Exception
					{
						if(response == null)
						{
							this.setOutputMessage("Failed To Claim Special Cache!");
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
							this.setOutputMessage("Failed To Claim Special Cache!");
							this.setSuccess("1");
							return;
						}
						String name = response.get("event").get("name").asString();
						int reward = response.get("event").get("reward").asInteger();
						CurrentUser.getMe().setBalance(CurrentUser.getMe().getBalance() + reward);
						this.setOutputMessage("Congratulations! You have successfully found and claimed the hidden cache " + name + ", and have been rewarded with " + reward + " soldiers!");
						this.setSuccess("2");
					}
				};
				try
				{
					rt.setURL("http://" + ServerIP + "/api/claimspecial?uname=" + encode(CurrentUser.getMe().getUserName()) + "&session=" + encode(CurrentUser.getMe().getSessionID()) + "&address=" + encode(ServerRequests.getStaticMacAddress()));
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
							ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Error hashing url", true));	
						}
					});
					ServerRequests.setStaticClaimSpecialSuccess(false);
					ServerRequests.setStaticClaimSpecialComplete(true);
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
						ServerRequests.setStaticClaimSpecialSuccess(false);
						ServerRequests.setStaticClaimSpecialComplete(true);
					}
					else if(rt.getSuccess().equals("2"))
					{
						staticOutputMessage = rt.getOutputMessage();
						connecting = true;
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
								SpecialCacheScreen.getMe().killMe();
								new MainScreen();
								MessageBox.newMsgBox(staticOutputMessage, true);
							}
						});
						ServerRequests.setStaticClaimSpecialSuccess(true);
						ServerRequests.setStaticClaimSpecialComplete(true);
					}
				}
			}
		});
		thread.start();
	}

	public static void readMessage(int messageId)
	{
		ServerRequests.setStaticReadMessageSuccess(false);
		ServerRequests.setStaticReadMessageComplete(false);
		staticMessageId = messageId;

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
					ServerRequests.setStaticReadMessageSuccess(false);
					ServerRequests.setStaticReadMessageComplete(true);
					return;
				}

				RequestThread rt = new RequestThread() {

					public void processResponse(JSONObject response) throws Exception
					{
						if(response == null)
						{
							this.setOutputMessage("Failed To Get Message!");
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
							this.setOutputMessage("Failed To Get Message!");
							this.setSuccess("1");
							return;
						}
						messageSenderName = response.get("sender").asString();
						messageSubject = response.get("subject").asString();
						messageContent = response.get("content").asString();
						this.setOutputMessage("Done!");
						this.setSuccess("2");
					}
				};
				try
				{
					rt.setURL("http://" + ServerIP + "/api/readmessage?uname=" + encode(CurrentUser.getMe().getUserName()) + "&session=" + encode(CurrentUser.getMe().getSessionID()) + "&messageid=" + encode(ServerRequests.getStaticMessageId()));
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
							ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Error hashing url", true));	
						}
					});
					ServerRequests.setStaticReadMessageSuccess(false);
					ServerRequests.setStaticReadMessageComplete(true);
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
						ServerRequests.setStaticReadMessageSuccess(false);
						ServerRequests.setStaticReadMessageComplete(true);
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
								if(MessageBox.getMe() != null)
								{
									MessageBox.getMe().killMe();
								}
							}
						});
						ServerRequests.setStaticReadMessageSuccess(true);
						ServerRequests.setStaticReadMessageComplete(true);
					}
				}
			}
		});
		thread.start();
	}

	public static void sendMessage(String receiver, String subject, String content)
	{
		ServerRequests.setSendMessageComplete(false);
		ServerRequests.setSendMessageSuccess(false);

		messageContent = content;
		messageSenderName = receiver;
		messageSubject = subject;

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
					ServerRequests.setSendMessageSuccess(false);
					ServerRequests.setSendMessageComplete(true);
					return;
				}

				RequestThread rt = new RequestThread() {

					public void processResponse(JSONObject response) throws Exception
					{
						if(response == null)
						{
							this.setOutputMessage("Failed To Send Message!");
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
							this.setOutputMessage("Failed To Send Message!");
							this.setSuccess("1");
							return;
						}
						this.setOutputMessage("Done!");
						this.setSuccess("2");
					}
				};
				try
				{
					rt.setURL("http://" + ServerIP + "/api/sendmessage?uname=" + encode(CurrentUser.getMe().getUserName()) + "&session=" + encode(CurrentUser.getMe().getSessionID()) + "&receiver=" + encode(ServerRequests.getMessageSenderName()) + "&subject=" + encode(ServerRequests.getMessageSubject()) + "&content=" + encode(ServerRequests.getMessageContent()));
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
							ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Error hashing url", true));	
						}
					});
					ServerRequests.setSendMessageSuccess(false);
					ServerRequests.setSendMessageComplete(true);
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
						ServerRequests.setSendMessageSuccess(false);
						ServerRequests.setSendMessageComplete(true);
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
								if(MessageBox.getMe() != null)
								{
									MessageBox.getMe().killMe();
								}
							}
						});
						ServerRequests.setSendMessageSuccess(true);
						ServerRequests.setSendMessageComplete(true);
					}
				}
			}
		});
		thread.start();
	}

	public static void battleReport(int messageId)
	{
		ServerRequests.setBattleReportComplete(false);
		ServerRequests.setBattleReportSuccess(false);

		staticMessageId = messageId;

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
					ServerRequests.setBattleReportSuccess(false);
					ServerRequests.setBattleReportComplete(true);
					return;
				}

				RequestThread rt = new RequestThread() {

					public void processResponse(JSONObject response) throws Exception
					{
						if(response == null)
						{
							this.setOutputMessage("Failed To Get Report!");
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
							this.setOutputMessage("Failed To Get Report!");
							this.setSuccess("1");
							return;
						}
						this.setOutputMessage("Done!");
						ServerRequests.setAttackCacheResponse(response);
						this.setSuccess("2");
					}
				};
				try
				{
					rt.setURL("http://" + ServerIP + "/api/battlereport?uname=" + encode(CurrentUser.getMe().getUserName()) + "&session=" + encode(CurrentUser.getMe().getSessionID()) + "&reportid=" + encode(ServerRequests.staticMessageId));
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
							ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Error hashing url", true));	
						}
					});
					ServerRequests.setBattleReportSuccess(false);
					ServerRequests.setBattleReportComplete(true);
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
						ServerRequests.setBattleReportSuccess(false);
						ServerRequests.setBattleReportComplete(true);
					}
					else if(rt.getSuccess().equals("2"))
					{
						connecting = true;
						ServerRequests.setBattleReportSuccess(true);
						ServerRequests.setBattleReportComplete(true);
					}
				}
			}
		});
		thread.start();
	}

	public static void setBlocked(String usernameToBeBlockedx, boolean blocked)
	{
		ServerRequests.setBlockUserSuccess(false);
		ServerRequests.setBlockUserComplete(false);

		ServerRequests.usernameToBeBlocked = usernameToBeBlockedx;
		ServerRequests.blockTrue = blocked;

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
					ServerRequests.setBlockUserSuccess(false);
					ServerRequests.setBlockUserComplete(true);
					return;
				}

				RequestThread rt = new RequestThread() {

					public void processResponse(JSONObject response) throws Exception
					{
						if(response == null)
						{
							this.setOutputMessage("Failed To Block User!");
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
							this.setOutputMessage("Failed To Block User!");
							this.setSuccess("1");
							return;
						}
						this.setOutputMessage("Done!");
						this.setSuccess("2");
					}
				};
				try
				{
					rt.setURL("http://" + ServerIP + "/api/setblocked?uname=" + encode(CurrentUser.getMe().getUserName()) + "&session=" + encode(CurrentUser.getMe().getSessionID()) + "&account=" + encode(ServerRequests.usernameToBeBlocked) + "&block=" + ServerRequests.blockTrue);
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
							ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Error hashing url", true));	
						}
					});
					ServerRequests.setBlockUserSuccess(false);
					ServerRequests.setBlockUserComplete(true);
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
						ServerRequests.setBlockUserSuccess(false);
						ServerRequests.setBlockUserComplete(true);
					}
					else if(rt.getSuccess().equals("2"))
					{
						connecting = true;
						ServerRequests.setBlockUserSuccess(true);
						ServerRequests.setBlockUserComplete(true);
					}
				}
			}
		});
		thread.start();
	}

	public static void getBlocked()
	{
		ServerRequests.setGetBlockedUsersSuccessful(false);
		ServerRequests.setGetBlockedUsersComplete(false);

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
					ServerRequests.setGetBlockedUsersSuccessful(false);
					ServerRequests.setGetBlockedUsersComplete(true);
					return;
				}

				RequestThread rt = new RequestThread() {

					public void processResponse(JSONObject response) throws Exception
					{
						if(response == null)
						{
							this.setOutputMessage("Failed To Get Blocked User List!");
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
							this.setOutputMessage("Failed To Get Blocked User List!");
							this.setSuccess("1");
							return;
						}
						JSONValue[] valArray = response.get("users").asArray();
						BlockedManager.clearBlocked();
						for(JSONValue val : valArray)
						{
							JSONObject obj = (JSONObject)val;
							BlockedManager.addBlocked(obj.get("uname").asString());
						}
						this.setOutputMessage("Done!");
						this.setSuccess("2");
					}
				};
				try
				{
					rt.setURL("http://" + ServerIP + "/api/blockedusers?uname=" + encode(CurrentUser.getMe().getUserName()) + "&session=" + encode(CurrentUser.getMe().getSessionID()));
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
							ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Error hashing url", true));	
						}
					});
					ServerRequests.setGetBlockedUsersSuccessful(false);
					ServerRequests.setGetBlockedUsersComplete(true);
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
						ServerRequests.setGetBlockedUsersSuccessful(false);
						ServerRequests.setGetBlockedUsersComplete(true);
					}
					else if(rt.getSuccess().equals("2"))
					{
						connecting = true;
						ServerRequests.setGetBlockedUsersSuccessful(true);
						ServerRequests.setGetBlockedUsersComplete(true);
					}
				}
			}
		});
		thread.start();
	}

	public static void setting(String setting)
	{
		setting(setting, null);
	}

	public static void setting(String setting, String newVal)
	{
		ServerRequests.settingName = setting;
		ServerRequests.newVal = newVal;
		ServerRequests.setGetUpdateSettingComplete(false);
		ServerRequests.setGetUpdateSettingSuccess(false);

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
					ServerRequests.setGetUpdateSettingSuccess(false);
					ServerRequests.setGetUpdateSettingComplete(true);
					return;
				}

				RequestThread rt = new RequestThread() {

					public void processResponse(JSONObject response) throws Exception
					{
						if(response == null)
						{
							this.setOutputMessage("Failed To Set Setting!");
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
							this.setOutputMessage("Failed To Set Setting!");
							this.setSuccess("1");
							return;
						}
						ServerRequests.settingName = response.get("value").asString();
						this.setOutputMessage("Done!");
						this.setSuccess("2");
					}
				};
				try
				{
					String valueMaybe = "";
					if(ServerRequests.newVal != null)
					{
						valueMaybe = "&value=" + encode(ServerRequests.newVal);
					}
					rt.setURL("http://" + ServerIP + "/api/setting?uname=" + encode(CurrentUser.getMe().getUserName()) + "&session=" + encode(CurrentUser.getMe().getSessionID()) + "&name=" + encode(ServerRequests.settingName) + valueMaybe);
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
							ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Error hashing url", true));	
						}
					});
					ServerRequests.setGetUpdateSettingSuccess(false);
					ServerRequests.setGetUpdateSettingComplete(true);
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
						connecting = true;
						staticOutputMessage = rt.getOutputMessage();
						ServerRequests.setGetUpdateSettingSuccess(false);
						ServerRequests.setGetUpdateSettingComplete(true);
					}
					else if(rt.getSuccess().equals("2"))
					{
						connecting = true;
						ServerRequests.setGetUpdateSettingSuccess(true);
						ServerRequests.setGetUpdateSettingComplete(true);
					}
				}
			}
		});
		thread.start();
	}

	public static void report(int identity, String type, String messageToSendWith)
	{
		ServerRequests.reportThingSuccess = false;
		ServerRequests.reportThingComplete = false;
		ServerRequests.reportId = identity;
		ServerRequests.reportType = type;
		ServerRequests.staticOutputMessage = messageToSendWith;

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
					ServerRequests.reportThingSuccess = false;
					ServerRequests.reportThingComplete = true;
					return;
				}

				RequestThread rt = new RequestThread() {

					public void processResponse(JSONObject response) throws Exception
					{
						if(response == null)
						{
							this.setOutputMessage("Failed To Send Report!");
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
							this.setOutputMessage("Failed To Send Report!");
							this.setSuccess("1");
							return;
						}
						this.setOutputMessage("Done!");
						this.setSuccess("2");
					}
				};
				try
				{
					rt.setURL("http://" + ServerIP + "/api/sendreport?uname=" + encode(CurrentUser.getMe().getUserName()) + "&session=" + encode(CurrentUser.getMe().getSessionID()) + "&type=" + encode(ServerRequests.reportType) + "&contextid=" + encode(ServerRequests.reportId) + "&message=" + encode(ServerRequests.staticOutputMessage));
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
							ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Error hashing url", true));	
						}
					});
					ServerRequests.reportThingSuccess = false;
					ServerRequests.reportThingComplete = true;
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
						connecting = true;
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
								ServerRequests.setTheMessageBox(MessageBox.newMsgBox(staticOutputMessage, true));
								ServerRequests.reportThingSuccess = false;
								ServerRequests.reportThingComplete = true;
							}
						});

					}
					else if(rt.getSuccess().equals("2"))
					{
						connecting = true;
						ServerRequests.reportThingSuccess = true;
						ServerRequests.reportThingComplete = true;
					}
				}
			}
		});
		thread.start();
	}

	public static String encode(String x) throws Exception
	{
		return URLEncoder.encode(x, "ISO-8859-1");
	}

	public static String encode(int x) throws Exception
	{
		return encode(Integer.toString(x));
	}

	public static String encode(long x) throws Exception
	{
		return encode(Long.toString(x));
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