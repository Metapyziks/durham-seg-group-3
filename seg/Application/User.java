import java.util.ArrayList;

//Class that describes a user and contains all of their user info apart from their email and password.
public class User
{
    private String accountid;
    private String username;
    private String joindate;
    private String rank;

    public User(String accountid, String username, String joindate, String rank)
    {
        this.accountid = accountid;
        this.username = username;
        this.joindate = joindate;
        this.rank = rank;	
    }

    public String getAccountId()
    {
        return accountid;
    }

    public String getUserName()
    {
        return username;
    }

    public String getJoinDate()
    {
	return joindate;
    }

    public String getRank()
    {
	return rank;
    }
}
