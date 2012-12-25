package com.example.fortitude;

import java.net.URL;
import java.net.URLConnection;
import java.net.ConnectException;
import json.JSONObject;
import java.net.SocketTimeoutException;
import java.io.FileNotFoundException;

public abstract class RequestThread implements Runnable
{   
	private String url = null;
	private String outputMessage = "null";
	private String success = "0";

	public synchronized String getOutputMessage()
	{
		return outputMessage;
	}

	public synchronized String getSuccess()
	{
		return success;
	}

	public synchronized void setOutputMessage(String x)
	{
		outputMessage = x;
	}

	public synchronized void setSuccess(String x)
	{
		success = x;
	}

	////////
	//
	//run
	//
	////////
	public void run()
	{
		try
		{
			if(!(Fortitude.getFortitude().isNetworkAvailable()))
			{
				setOutputMessage("Unable to connect to server, no internet connection detected");
				setSuccess("1");
				return;
			}
			JSONObject obj = makeGetRequest();
			processResponse(obj);
		}
		catch(ConnectException e)
		{
			setOutputMessage("Unable to connect to server");
			setSuccess("1");
		}
		catch(SocketTimeoutException e)
		{
			setOutputMessage("Unable to connect to server");
			setSuccess("1");
		}
		catch(FileNotFoundException e)
		{
			setOutputMessage("Unable to connect to server, the server may be down");
			setSuccess("1");
		}
		catch(Exception e)
		{
			setOutputMessage("Unable to connect to server: " + e.toString());
			setSuccess("1");
		}
	}

	////////
	//
	//processResponse
	//
	//This thread does this when it has received it's response
	//
	////////
	public abstract void processResponse(JSONObject response) throws Exception;

	////////
	//
	//makeGetRequest
	//
	//Generic method to make a get request to the server and return the 
	//response.
	//
	////////
	private JSONObject makeGetRequest() throws Exception
	{
		URL urler = new URL(url);
		URLConnection connection = urler.openConnection();
		connection.setConnectTimeout(10000);
		return JSONObject.parseStream(connection.getInputStream());
	}

	public void setURL(String x)
	{
		url = x;
	}
}
