/////////////
// The UserManager class will get all user related informationl, it will keep track if you are loged on or off. 
//It will also add new users to the user list. 
/////////////
package sudoku;

import java.io.*;
import java.util.ArrayList;

public class UserManager 
{
	private User selectedUser;
	private UserNode userListHead;
	

	public UserManager()
	{
		selectedUser = null;
		userListHead = null;
	}
	// adds a new user to the user list
	public boolean addUser(String name, String password)
	{
		boolean addedUser = false;
		
		if (userListHead == null)
		{
			userListHead = new UserNode(new User(name, password));
			selectedUser = userListHead.getUser();
			addedUser = true;
		}
		else
		{
			UserNode listIterator = userListHead;
			
			while (listIterator.getNextUser() != null && !listIterator.getUser().getName().equalsIgnoreCase(name))
			{
				listIterator = listIterator.getNextUser();
			}
			
			if (listIterator.getNextUser() == null && !listIterator.getUser().getName().equalsIgnoreCase(name))
			{
				listIterator.setNextUser(new UserNode(new User(name, password)));
				selectedUser = listIterator.getNextUser().getUser();
				addedUser = true;
			}
		}
		
		return addedUser;
	}
	// this method will find a user within the user list
	public boolean findUser(String name, String password)
	{
		boolean foundUser = false;
		
		if (userListHead == null)
		{
			foundUser = false;
		}
		else
		{
			UserNode listIterator = userListHead;
			
			while (listIterator.getNextUser() != null && !listIterator.getUser().getName().equalsIgnoreCase(name))
			{
				listIterator = listIterator.getNextUser();
			}
			
			if (listIterator.getUser().getName().equalsIgnoreCase(name) && listIterator.getUser().checkPass(password))
			{
				foundUser = true;
				selectedUser = listIterator.getUser();
			}
		}
		
		return foundUser;
	}
	//returns the selected user
	public User getSelectedUser()
	{
		return selectedUser;
	}
	// will see if you are logged in
	public boolean isLoggedIn()
	{
		if (selectedUser == null)
			return false;
		else
			return true;
	}
	
	// checks to see if you are logged out
	public boolean logOut()
	{
		selectedUser = null;
		return true;
	}
	// this method will store the user list to a file 
	public boolean storeUserList (String fileName)
	{
		boolean savedFile = false;
		ArrayList<User> userArray = new ArrayList<User>();
		
		if (userListHead != null)
		{
			UserNode listIterator = userListHead;
			
			while (listIterator != null)
			{
				userArray.add(listIterator.getUser());
				listIterator = listIterator.getNextUser();
			}
			
			try
			{
				FileOutputStream fileOut = new FileOutputStream(fileName);
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(userArray);
				out.close();
				fileOut.close();
				savedFile = true;
				
			}catch(IOException i)
			{
				i.printStackTrace();
			}
		}
		
		return savedFile;
	}
	
	@SuppressWarnings("unchecked")
	// this method will get the user list from the file 
	public boolean retrieveUserList (String fileName)
	{
		boolean loadedFile = false;
		ArrayList<User> userArray = null;
		
		try
		{
			FileInputStream fileIn = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			userArray = (ArrayList<User>) in.readObject();
			
			in.close();
			fileIn.close();
			loadedFile = true;
			
		}catch(IOException i)
		{
			i.printStackTrace();
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		if (loadedFile && userArray.size() != 0)
		{
			UserNode newUser = new UserNode(userArray.get(0));
			userListHead = newUser;
			UserNode listIterator = userListHead;
			
			for (int i = 1; i < userArray.size(); i++)
			{
				newUser = new UserNode(userArray.get(i));
				listIterator.setNextUser(newUser);
				listIterator = listIterator.getNextUser();
			}
		}
		else
		{
			loadedFile = false;
		}
		
		
		return loadedFile;
	}
	public UserNode getHeadUser()
	{
		return userListHead;
	}

	//checks to see if the user list is empty	
	public boolean isListEmpty()
	{
		if (userListHead == null)
			return false;
		else
			return true;
	}
}
