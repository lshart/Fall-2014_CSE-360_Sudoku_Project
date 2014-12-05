package sudoku;

import java.io.*;
import java.util.ArrayList;

public class UserManager 
{
	private User selectedUser;
	private UserNode userListHead;
	
	UserNode tempCurrent = userListHead; // for highscores
	UserNode tempNext = null;			//for highscores
	
	public UserManager()
	{
		selectedUser = null;
		userListHead = null;
	}
	
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
	
	public User getSelectedUser()
	{
		return selectedUser;
	}
	
	public boolean isLoggedIn()
	{
		if (selectedUser == null)
			return false;
		else
			return true;
	}
	
	public boolean logOut()
	{
		selectedUser = null;
		return true;
	}
	
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
	
	public User HighScoreMethod()
	{
		if(tempCurrent == null && tempNext == null)
		{
			tempCurrent = userListHead;
			tempNext = tempCurrent;
			tempCurrent = tempCurrent.getNextUser();
			return tempNext.getUser();
		}
		else if (tempCurrent == null && tempNext != null)
		{
			return null;
		}
		else
		{
			tempNext = tempCurrent;
			tempCurrent = tempCurrent.getNextUser();
			return tempNext.getUser();
			
		}
		
	}
	
	public boolean isListEmpty()
	{
		if (userListHead == null)
			return false;
		else
			return true;
	}
	
	public class UserNode
	{
		private User thisUser;
		private UserNode nextUser;
		
		public UserNode(User newUser)
		{
			thisUser = newUser;
			nextUser = null;
		}
		
		public User getUser()
		{
			return thisUser;
		}
		
		public UserNode getNextUser()
		{
			return nextUser;
		}
		
		public void setNextUser(UserNode next)
		{
			nextUser = next;
		}
	}
}
