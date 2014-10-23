package sudoku;

public class Cell 
{
	private int row;
	private int column;
	private int group;
	private int value;
	private int answer;
	private boolean isClue;
	
	public void Clue (int ro, int col, int gro, int ans, boolean clue)
	{
		row = ro;
		column = col;
		group = gro;
		answer = ans;
		isClue = clue;
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
	
	public int getGroup()
	{
		return group;
	}
	
	public int getValue()
	{
		return value;
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
		if (isClue)
			return false;
		else
		{
			isClue = true;
			return true;
		}
	}
}
