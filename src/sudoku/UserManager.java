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
	
	public boolean addUser(String name, String password)
	{
		boolean addedUser = false;
		
		if (userListHead == null)
		{
			userListHead = new UserNode(new User(name, password));
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
			foundUser = true;
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
	
	private class UserNode
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
