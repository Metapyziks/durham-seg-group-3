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
	//createSession
	//
	//Creates a session on the server and returns the session hash.
	//This hash will be able to be used as a means of authentication with
	//the server so the user doesn't have to keep logging in for every
	//action that requires authentication. This method effectively be
	//viewed as a 'Log in' method...
	//
	////////
	public static void createSession(String uname, String phash)
	{
		MessageBox mb = MessageBox.newMsgBox("Connecting", false);
		
		String ServerIP = Fortitude.getFortitude().getResources().getString(R.string.ServerIP);

		if(ServerIP == null)
		{
			mb.killMe();
			mb = MessageBox.newMsgBox("Unable To Retrieve Setting 'ServerIP'", true);
			return;
		}

		RequestThread rt = new RequestThread() {

			public void processResponse(JSONObject response) throws Exception
			{
                if(response.get("error") != null)
                {
                	this.setOutputMessage(response.get("error").asString());
                	this.setSuccess("1");
                	return;
                }
                this.setOutputMessage("Success");
                this.setSuccess("2");
			}

		};
		rt.setURL("http://" + ServerIP + "/api/session?uname=" + uname + "&phash=" + phash);
		Thread thread = new Thread(rt);
		thread.start();
		boolean connecting = false;
		while(connecting == false)
		{
			if(!(rt.getSuccess().equals("0")))
			{
				mb.killMe();
				mb = MessageBox.newMsgBox(rt.getOutputMessage(), true);
				connecting = true;
			}
	    }
	}
}

