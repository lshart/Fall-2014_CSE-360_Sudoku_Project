package sudoku;

import java.io.Serializable;

public class User implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5220066483445795019L;
	public static final int NUMBER_OF_DIFFICULTIES = 4;
	private String uName;
	private String password;
	int[] winCount;					// should have 4 indices, initialized to zero. This is the score
	int[] fastestTime;				// should also have 4 indices initialized to zero. This is bragging rights
	
	public User(String name, String pass)
	{
		uName = name;
		password = pass;
		winCount = new int[NUMBER_OF_DIFFICULTIES];
		fastestTime = new int[NUMBER_OF_DIFFICULTIES];
		
		for (int i = 0; i < NUMBER_OF_DIFFICULTIES; i++)
		{
			winCount[i] = 0;
			fastestTime[i] = -1;
		}
	}
	
	public void startGame (int difficulty)
	{
		winCount[difficulty] --;
	}
	
	public void win(int difficulty, int time, boolean underPar)
	{
		if (underPar)
		{
			if (fastestTime[difficulty] > time || fastestTime[difficulty] < -1)
			{
				fastestTime[difficulty] = time;
			}
			
			winCount[difficulty] += 2;			// this is because when the game is started, the player's score for that difficulty is lowered by one	
		}
		else
		{
			winCount[difficulty] ++;
		}
	}
	
	public int getFastestTime(int difficulty)
	{
		return fastestTime[difficulty];
	}
	
	public boolean checkPass(String passToCheck)
	{
		return password.equals(passToCheck);
	}
	
	public String getName()
	{
		return uName;
	}
	
	public int getScore(int difficulty)
	{
		return winCount[difficulty];
	}
}