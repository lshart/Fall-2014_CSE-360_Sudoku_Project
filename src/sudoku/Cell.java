/////////////
// The Cell class will contain all cell information. 
/////////////
package sudoku;

public class Cell 
{
	//declares global variables for the cell class
	private int row;
	private int column;
	private int value;
	private int answer;
	private boolean isClue;
	// makes the cell information
	public Cell (int ro, int col, int ans, boolean clue)
	{
		row = ro;
		column = col;
		answer = ans;
		isClue = clue;
		if (isClue)
			value = ans;
		else
			value = 0;
	}
	// makes the hint visible on the board
	public String toString()
	{
		if (isClue)
			return Integer.toString(answer);
		else if(!isClue && value != 0)
			return Integer.toString(value);
		else
			return null;
	}
	// returns the row of the cell
	public int getRow()
	{
		return row;
	}
	// returns the column of the cell
	public int getColumn()
	{
		return column;
	}
	// get the value of each cell
	public int getValue()
	{
		return value;
	}
	// if there is a hint set new value in the cell
	public boolean setValue(int val)
	{
		if (value == 0 && !isClue)
		{
			value = val;
			return true;
		}
		else
			return false;
	}
	//delets the value from the cell
	public boolean removeValue()
	{
		if (value == 0 || isClue)
			return false;
		else
		{	
			value = 0;
			return true;
		}
	}
	// gets the answer to the cell
	public int getAnswer()
	{
		return answer;
	}
	// returns clue information
	public boolean isClue()
	{
		return isClue;
	}
	// will make a new hint for the board
	public boolean makeAHint()
	{
		if (isClue && value == 0)
			return false;
		else
		{
			value = answer;
			isClue = true;
			return true;
		}
	}
	// will make the cell answer the right one
	public void solveCell()
	{
		value = answer;
	}
	// checks to see if the cell value is a correct answer
	public boolean isCorrect()
	{
		if (answer == value)
			return true;
		else
			return false;
	}
}
