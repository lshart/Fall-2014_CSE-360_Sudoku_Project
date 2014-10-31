package sudoku;

public class BoardManager 
{
	private static final int SIZE = 9;
	
	private Cell[][] board;		// This will be 9 by 9
	private int difficulty;
	private int hints;
	private int time;			// in seconds
	private int parTime;		// in seconds
	private int currentNum;
	private boolean overTime;
	private User currentUser;
	
	public BoardManager(int diff, User currU)
	{
		// this is the temporary board /////////////////////////////////////////////////////////////
		int[] tempBoardAnswer = {2,3,9, 6,5,7, 8,4,1,
								 7,1,4, 8,2,3, 9,6,5,
								 5,6,8, 4,9,1, 3,2,7,
								 
								 4,8,5, 9,7,6, 1,3,2,
								 6,9,2, 3,1,8, 7,5,4,
								 1,7,3, 5,4,2, 6,8,9,
								 
								 8,4,1, 2,3,9, 5,7,6,
								 9,2,6, 7,8,5, 4,1,3,
								 3,5,7, 1,6,4, 2,9,8};
		
		boolean[] tempBoardClues = new boolean[81];
		for (int m = 0; m < 81; m++)
			tempBoardClues[m] = false;
		tempBoardClues[1] = true;
		tempBoardClues[5] = true;
		tempBoardClues[10] = true;
		tempBoardClues[11] = true;
		tempBoardClues[12] = true;
		tempBoardClues[19] = true;
		tempBoardClues[20] = true;
		tempBoardClues[22] = true;
		tempBoardClues[23] = true;
		tempBoardClues[26] = true;
		tempBoardClues[32] = true;
		tempBoardClues[33] = true;
		tempBoardClues[38] = true;
		tempBoardClues[39] = true;
		tempBoardClues[40] = true;
		tempBoardClues[41] = true;
		tempBoardClues[42] = true;
		tempBoardClues[47] = true;
		tempBoardClues[48] = true;
		tempBoardClues[54] = true;
		tempBoardClues[57] = true;
		tempBoardClues[58] = true;
		tempBoardClues[60] = true;
		tempBoardClues[61] = true;
		tempBoardClues[68] = true;
		tempBoardClues[69] = true;
		tempBoardClues[70] = true;
		tempBoardClues[75] = true;
		tempBoardClues[79] = true;	
		//////////////////////////////////////////////////////////////////////////////
		int count = 0; // a temporary count, used to assign the answers to the cells, as well as their clue status
		for (int i = 0; i < SIZE; i++)
			for (int k = 0; k < SIZE; k++)
			{  // I actually just realized we don't need to have any reference to the base cell.  It's really easy to figure out from row and column
				if (i % 3 == 0 && k % 3 == 0)					// this if statement is for assigning the base block
					board[i][k] = new Cell(i, k, null, tempBoardAnswer[count], tempBoardClues[count]);
				else
					board[i][k] = new Cell(i, k, board[i - i%3][k- k%3], tempBoardAnswer[count], tempBoardClues[count]);
				count ++;
			}
		
		difficulty = diff;
		currentUser = currU;
		hints = 4;
		overTime = false;
		time = 0;
		parTime = 1200;
		currentNum = 0;
	}
	
	public boolean placeNum(int row, int col, int numToPlace)
	{
		if (checkPlace(row, col, numToPlace))
		{
			return board[row][col].setValue(numToPlace);
		}
		else
			return false;
	}
	
	private boolean checkPlace(int row, int col, int numToCheck)
	{
		boolean unique = false;
		
		if (colCheck(col, numToCheck) && rowCheck(row, numToCheck) && blockCheck(board[row][col], numToCheck))
			unique = true;
		
		return unique;
	}
	
	private boolean colCheck(int col, int numToCheck)
	{
		boolean unique = true;
		for (int i = 0; i < SIZE; i ++)
		{
			if (board[col][i].getValue() == numToCheck)
				unique = false;
		}
		return unique;
	}
	
	private boolean rowCheck(int row, int numToCheck)
	{
		boolean unique = true;
		for (int i = 0; i < SIZE; i ++)
		{
			if (board[i][row].getValue() == numToCheck)
				unique = false;
		}
		return unique;
	}
	
	private boolean blockCheck(Cell blockBase, int numToCheck)
	{
		boolean unique = true;
		
		int basedRow = blockBase.getRow();
		int basedCol = blockBase.getColumn();
		
		basedRow = basedRow - basedRow % 3;
		basedRow = basedCol - basedCol % 3;
		
		for (int i = basedRow; i < basedRow+3; i++)
			for (int j = basedCol; j < basedCol+3; i++)
				if (board[i][j].getValue() == numToCheck)
					unique = false;
		
		return unique;
	}
	
	public boolean removeNum(int row, int col)
	{
		return board[row][col].removeValue();
	}
	
	public boolean useHint()
	{
		/////////////////////////////////////////////// NOT IMPLIMENTED YET!!!!!!!!!!!!!!!!!!!!
		hints--;
		return false;
	}
	
	public int getHints()
	{
		return hints;
	}
	
	public int getDifficulty()
	{
		return difficulty;
	}
	
	public User getCurrentUser()
	{
		return currentUser;
	}
	
	public void setCurrentNum(int newNum)
	{
		currentNum = newNum;
	}
	
	public int getCurrentNum()
	{
		return currentNum;
	}
	
	public int getTime()
	{
		return time;
	}
	
	public int getParTime()
	{
		return parTime;
	}
	
	public boolean updateTime()
	{
		time ++;
		
		if (time == parTime)
			overTime = true;
		
		return overTime;
	}
}
