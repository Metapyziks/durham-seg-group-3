package com.example.fortitude;

//Class that describes a user and contains all of their user info apart from their email and password.
public class User
{
  private String accountid; //id of account as stored on the server
  private String username; //username of user
  private String joindate; //date (time since the epoc in milliseconds) since the user joined
  private String rank; //Whether the user is verified or not.

  ////////
  //
  //Constructor
  //
  ////////
  public User(String accountid, String username, String joindate, String rank)
  {
      this.accountid = accountid;
      this.username = username;
      this.joindate = joindate;
      this.rank = rank;	
  }
  
  ////////
  //
  //getAccountId
  //
  // returns accountid
  //
  ////////
  public String getAccountId()
  {
      return accountid;
  }

  ////////
  //
  //getUserName
  //
  //returns username;
  ////////
  public String getUserName()
  {
      return username;
  }

  ////////
  //
  //getJoinDate
  //
  //return joindate;
  //
  ////////
  public String getJoinDate()
  {
	return joindate;
  }

  ////////
  //
  //getRank
  //
  //returns the rank (E.g. verified)
  //
  ////////
  public String getRank()
  {
	return rank;
  }

  ////////
  //
  //isVerified
  //
  //returns boolean as to whether the user is verified or not.
  //
  ////////
  public boolean isVerified()
  {
	return (!rank.equals("Unverified"));
  }
}

