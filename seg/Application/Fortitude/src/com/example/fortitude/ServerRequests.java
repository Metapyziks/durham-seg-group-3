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
	public static MessageBox getTheMessageBox()
	{
		return theMessageBox;
	}

	public static void setTheMessageBox(MessageBox x)
	{
		theMessageBox = x;
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
		theMessageBox = MessageBox.newMsgBox("Connecting To Server...", false);

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
							this.setOutputMessage("Failed To Log in0");
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
												ServerRequests.getTheMessageBox().killMe();
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
														ServerRequests.getTheMessageBox().killMe();
														ServerRequests.getUserBalance(staticUname, staticSessionId);
														Thread getBalanceThread = new Thread(new Runnable() {
															public void run()
															{
																while(!(ServerRequests.getGetUserBalanceComplete()))
																{
																	////Do nothing
																}
																if(ServerRequests.getGetUserBalanceSuccess())
																{
																	Fortitude.getFortitude().runOnUiThread(new Runnable() {
																		public void run()
																		{
																			ServerRequests.getTheMessageBox().killMe();
																			ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Successfully Signed In!", false));
																		}
																	});
																	new CurrentUser(staticUserInfo.get(0).getAccountId(), staticUserInfo.get(0).getUserName(), staticUserInfo.get(0).getJoinDate(), staticUserInfo.get(0).getRank(), staticSessionId, staticPhash, staticUserBalance);
																	try
																	{
																		Thread.sleep(1500);
																	}
																	catch(Exception e)
																	{

																	}
																	Fortitude.getFortitude().runOnUiThread(new Runnable() {
																		public void run()
																		{
																			ServerRequests.getTheMessageBox().killMe();
																			MainLoginScreen.getMe().killMe();
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
	//getUserBalance
	//
	//gets the balance of a user with a valid sessionId
	//
	////////
	public static void getUserBalance(String uname, String sessionId)
	{
		ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Connecting To Server...", false));

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
						this.setOutputMessage(response.get("balance").asString());
						this.setSuccess("2");
					}

				};
				rt.setURL("http://" + ServerIP + "/api/balance?uname=" + ServerRequests.getStaticUname() + "&session=" + staticSessionId);
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
							ServerRequests.setStaticUserBalance(ServerRequests.getStaticOutputMessage());
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
		theMessageBox = MessageBox.newMsgBox("Connecting To Server...", false);

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
									ServerRequests.setTheMessageBox(MessageBox.newMsgBox(ServerRequests.getStaticOutputMessage(), true));
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
	//getUserInfo
	//
	//creates a static arraylist of Users from a given list of users usernames
	//
	////////
	public static void getUserInfo(String users)
	{
		getUserInfoComplete = false;
		getUserInfoSuccess = false;

		theMessageBox = MessageBox.newMsgBox("Connecting To Server...", false);

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
						for(int i = 0; i < response.get("users").length(); i++)
						{
							String accountid = Integer.toString(response.get("users").get(i).get("accountid").asInteger());
							String uname = response.get("users").get(i).get("uname").asString();
							String joindate = Integer.toString(response.get("users").get(i).get("joindate").asInteger());
							String rank = response.get("users").get(i).get("rank").asString();
							User u = new User(accountid, uname, joindate, rank);
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
						ServerRequests.setGetUserInfoComplete(true);
					}
					else if(rt.getSuccess().equals("2"))
					{
						Fortitude.getFortitude().runOnUiThread(new Runnable() {
							public void run()
							{
								ServerRequests.getTheMessageBox().killMe();
							}
						});
						while(MessageBox.doYouExist())
						{
							//wait till the connecting to server message has gone!
						}
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
}

