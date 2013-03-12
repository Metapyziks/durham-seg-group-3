package com.example.fortitude;

//Class that describes a user and contains all of their user info apart from their email and password.
public class User extends Reportable
{
  private String username; //username of user
  private String joindate; //date (time since the epoc in milliseconds) since the user joined
  private String rank; //Whether the user is verified or not.
  private String caches;

  ////////
  //
  //Constructor
  //
  ////////
  public User(int accountid, String username, String joindate, String rank, String caches)
  {
      super(accountid);
      this.username = username;
      this.joindate = joindate;
      this.rank = rank;	
      this.caches = Integer.toString((int)(Double.parseDouble(caches)));
  }
  
  public String getCaches()
  {
	  return caches;
  }
  
  public boolean isBlocked()
  {
	  return BlockedManager.isBlocked(getUserName());
  }
  
  ////////
  //
  //getAccountId
  //
  // returns accountid
  //
  ////////
  public int getAccountId()
  {
      return super.getIdentifier();
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

