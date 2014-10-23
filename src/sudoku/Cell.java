package sudoku;

public class Cell 
{
	private int row;
	private int column;
	private Cell block;
	private int value;
	private int answer;
	private boolean isClue;
	
	public Cell (int ro, int col, Cell blo, int ans, boolean clue)
	{
		row = ro;
		column = col;
		if (blo == null)
			block = this;
		else
			block = blo;
		answer = ans;
		isClue = clue;
		if (clue)
			value = ans;
		else
			value = 0;
	}
	
	public String toString()
	{
		if (isClue)
			return Integer.toString(answer);
		else if(!isClue && value != 0)
			return Integer.toString(value);
		else
			return null;
	}
	
	public int getRow()
	{
		return row;
	}
	
	public int getColumn()
	{
		return column;
	}
	
	public Cell getBlock()
	{
		return block;
	}
	
	public int getValue()
	{
		return value;
	}
	
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
	
	public boolean removeValue()
	{
		if (value == 0 && !isClue)
			return false;
		else
		{	
			value = 0;
			return true;
		}
	}
	
	public int getAnswer()
	{
		return answer;
	}
	
	public boolean isClue()
	{
		return isClue;
	}
	
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
}
