package sudoku;

// This class is meant to manage the game board, though it relies on its components to handle much of the logic.
public class BoardManager 
{	
	private Board board;		// This will be 9 by 9
	private int difficulty;
	private int hints;
	private int time;			// in seconds
	private int parTime;		// in seconds
	private int currentNum;		// This represents the number the user is trying to input
	private boolean overTime;	// If true this means the user has gone over par time
	private User currentUser;
	
	public BoardManager(int diff, User currU)
	{	
		board = new Board(diff);
		difficulty = diff;
		currentUser = currU;
		hints = 4;
		overTime = false;
		time = 0;
		parTime = 1200;
		currentNum = 0;
	}
	
	public boolean placeNum(int row, int col)
	{
		return board.placeValue(row, col, currentNum);
	}
	
	public int getCellValueAt(int row, int col)
	{
		return board.getCellValue(row, col);
	}
	
	public boolean removeNum(int row, int col)
	{
		return board.removeCellValue(row, col);
	}
	
	public boolean useHint()
	{
		if (board.revealRandomCell())
		{
			hints--;
			return true;
		}
		else
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
	
	public int getCurrentNum()
	{
		return currentNum;
	}
	
	public void setCurrentNum(int newNum)
	{
		currentNum = newNum;
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
	
	public boolean hasWon()
	{
		return board.hasWon();
	}
}
