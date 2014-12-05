package sudoku;

// makes a new user node
public class UserNode
{
	private User thisUser;
	private UserNode nextUser;
	// make the node
	public UserNode(User newUser)
	{
		thisUser = newUser;
		nextUser = null;
	}
	// return the user
	public User getUser()
	{
		return thisUser;
	}
	// get the next user in the list
	public UserNode getNextUser()
	{
		return nextUser;
	}
	// will set the next user
	public void setNextUser(UserNode next)
	{
		nextUser = next;
	}
}