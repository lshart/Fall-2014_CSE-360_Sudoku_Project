/////////////
// This class is meant to manage the game board, though it relies on its components to handle much of the logic.
/////////////
package sudoku;


public class BoardManager 
{	
	private Board board;		// This will be 9 by 9
	private int difficulty;
	private int hints;
	private int parTime;		// in seconds
	private int currentNum;		// This represents the number the user is trying to input
	private boolean overTime;	// If true this means the user has gone over par time
	private User currentUser;
	
	private static final int EVIL_PAR = 20*60;
	private static final int HARD_PAR = 30*60;
	private static final int MEDIUM_PAR = 40*60;
	private static final int EASY_PAR = 50*60;
	
	
	public BoardManager(int diff, User currU)
	{ // gets difficulty and retuns the puzzel that matches, number of hints used.
		board = new Board(diff);
		difficulty = diff;   
		currentUser = currU;
		hints = 4;
		overTime = false;
		
		if (difficulty == 1)
		{
			parTime = MEDIUM_PAR;
		}
		else if (difficulty == 2)
		{
			parTime = HARD_PAR;
		}
		else if (difficulty == 3)
		{
			parTime = EVIL_PAR;
		}
		else 
		{
			parTime = EASY_PAR;
		}
		
		currentUser.startGame(difficulty);
		currentNum = 1;
	}
	// will return correct hint value
	public boolean isHint(int row, int col)
	{
		return board.isHint(row, col);
	}
	// asks if this is the right cell
	public boolean isCellCorrect(int row, int col)
	{
		return board.isCellCorrect(row, col);
	}
	// allows a number to be places
	public boolean placeNum(int row, int col)
	{
		return board.placeValue(row, col, currentNum);
	}
	// gets the current cell value
	public int getCellValueAt(int row, int col)
	{
		return board.getCellValue(row, col);
	}
	// this will remove a number
	public boolean removeNum(int row, int col)
	{
		return board.removeCellValue(row, col);
	}
	//allows the correct cell to be placed
	public void placeCorrectCell(int row, int col)
	{
		board.correctCell(row, col);
	}
	
	// A return of -1 means no hints remain
	public int useHint()
	{
		if (hints > 0)
		{
			hints --;
			return board.clearBadCells();
		}
		else
			return -1;
	}
	// returns the hints
	public int getHints()
	{
		return hints;
	}
	//will get the difficulty
	public int getDifficulty()
	{
		return difficulty;
	}
	//obtains the current user
	public User getCurrentUser()
	{
		return currentUser;
	}
	// gets the number that is currently in the cell
	public int getCurrentNum()
	{
		return currentNum;
	}
	// will set the current number to a new number
	public void setCurrentNum(int newNum)
	{
		currentNum = newNum;
	}
	//gets the time
	public int getParTime()
	{
		return parTime;
	}
	// will set the over time value
	public void set_overTime()
	{
		if(!overTime)
		{
			overTime = !overTime;
		}
	}
	//returns the over time value
	public boolean get_overTime()
	{
		return overTime;		
	}
	// if the users has won get the information
	public boolean hasWon(int min, int sec)
	{
		boolean won = board.hasWon();
		if (won)
		{
			currentUser.win(difficulty, min, sec, overTime);
		}
		return won;
	}
	// this solves the board
	public void cheatWin()
	{
		board.solveBoard();
	}
}
