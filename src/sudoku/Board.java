package sudoku;

public class Board 
{
	private static final int SIZE = 9;
	
	private Cell[][] board;
	
	public Board(int difficulty)
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
					board[i][k] = new Cell(i, k, tempBoardAnswer[count], tempBoardClues[count]);
				else
					board[i][k] = new Cell(i, k, tempBoardAnswer[count], tempBoardClues[count]);
				count ++;
			}
	}
	
	public boolean placeValue(int row, int col, int numToPlace)
	{
		if (checkPlace(row, col, numToPlace))
		{
			return board[row][col].setValue(numToPlace);
		}
		else
			return false;
	}
	
	public boolean removeCellValue(int row, int col)
	{
		return board[row][col].removeValue();
	}
	
	public int getCellValue(int row, int col)
	{
		return board[row][col].getValue();
	}
	
	public void solveBoard()
	{
		for (int i = 0; i < SIZE; i++)
			for(int j = 0; j < SIZE; j++)
				board[i][j].solveCell();
	}
	
	public boolean revealRandomCell()
	{
		////////////////////  yeah, still not implemented
		return true;
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
	
	private boolean blockCheck(Cell cellToCheck, int numToCheck)
	{
		boolean unique = true;
		
		int checkedRow = cellToCheck.getRow();
		int checkedCol = cellToCheck.getColumn();
		
		checkedRow = checkedRow - checkedRow % 3;
		checkedCol = checkedCol - checkedCol % 3;
		
		for (int i = checkedRow; i < checkedRow+3; i++)
			for (int j = checkedCol; j < checkedCol+3; i++)
				if (board[i][j].getValue() == numToCheck)
					unique = false;
		
		return unique;
	}
}
