/////////////
// The Board Class keeps track of all the Cells in the board, as well as managing the delivery of
// commands to all the Cells.
/////////////
package sudoku;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Scanner;

public class Board 
{
	private static final int SIZE = 9;

	private Cell[][] board;
	
	public Board(int difficulty)
	{
		board = new Cell[SIZE][SIZE];
		
		switch (difficulty)
		{
		case 0:
			loadPuzzle("Puzzles/Easy1.dat");
			break;
		case 1:
			loadPuzzle("Puzzles/Medium1.dat"); //
			break;
		case 2:
			loadPuzzle("Puzzles/Hard1.dat"); 
			break;
		case 3:
			loadPuzzle("Puzzles/Evil1.dat");
			break;
		default:
			loadPuzzle("Puzzles/Easy1.dat");
		}
	}
	
	// Places a value at the specified cell, so long as it's a valid place.  Returns false if
	// it can't place the value. 
	public boolean isHint(int row, int col)
	{
		return board[row][col].isClue();
		
	}
	
	
	
	public boolean placeValue(int row, int col, int numToPlace)
	{
		if (checkPlace(row, col, numToPlace))
		{
			return board[row][col].setValue(numToPlace);
		}
		else
		{
			board[row][col].setValue(numToPlace);
			return false;
		}
	}
	
	// Asks the Cell to attempt to remove a value from a cell.  If it is one of the clues, then
	// it returns false.
	public boolean removeCellValue(int row, int col)
	{
		return board[row][col].removeValue();
	}
	
	// Returns the value of the cell at the specified location
	public int getCellValue(int row, int col)
	{
		return board[row][col].getValue();
	}
	
	// Sets every cell to be equal to their answer
	public void solveBoard()
	{
		for (int r = 0; r < SIZE; r++)
			for(int c = 0; c < SIZE; c++)
				board[r][c].solveCell();
	}
	
	// This runs through the board and clears every cell who's value doesn't match the solution
	public int clearBadCells()
	{
		int clearedCells = 0;
		
		for (int r = 0; r < SIZE; r++)
			for (int c = 0; c < SIZE; c++)
				if (!board[r][c].isCorrect())
				{
					if(board[r][c].getValue() != 0)
						clearedCells++;
					board[r][c].removeValue();
				}
		
		return clearedCells;
	}
	
	// Checks to see if the board contains only solved cells
	public boolean hasWon()
	{
		boolean won = true;
		
		for (int r = 0; r < SIZE; r++)
			for (int c = 0; c < SIZE; c++)
				if (!board[r][c].isCorrect())
					won = false;
		
		return won;
	}
	
	// Loads a puzzle from a file with the specified name
	private boolean loadPuzzle (String fileName)
	{
		boolean loadedFile = false;
		String answerKey, clueKey;
		int[] boardAnswer = new int[SIZE*SIZE];
		boolean[] boardClues = new boolean[SIZE*SIZE];
		
		try
		{
			FileReader fileIn = new FileReader(fileName);
			BufferedReader in = new BufferedReader(fileIn);
			
			answerKey = in.readLine();
			clueKey = in.readLine();
			
			InputStream answers = new ByteArrayInputStream(answerKey.getBytes(Charset.defaultCharset()));
			InputStream clues = new ByteArrayInputStream(clueKey.getBytes(Charset.defaultCharset()));
			
			Scanner reader = new Scanner(answers);
			
			for (int i = 0; i < SIZE*SIZE; i++)
				boardAnswer[i] = reader.nextInt();
			
			reader.close();
			reader = new Scanner(clues);
			
			for (int i = 0; i < SIZE*SIZE; i++)
			{
				if (reader.nextInt() == 0)
					boardClues[i] = false;
				else
					boardClues[i] = true;
			}
			
			reader.close();
			clues.close();
			answers.close();
			in.close();
			fileIn.close();
			loadedFile = true;		
		} catch(IOException i)
		{
			i.printStackTrace();
		}
		
		if (loadedFile)
		{
			int count = 0; // a temporary count, used to assign the answers to the cells, as well as their clue status
			for (int r = 0; r < SIZE; r++)
			{
				for (int c = 0; c < SIZE; c++)
				{		
					board[r][c] = new Cell(r, c, boardAnswer[count], boardClues[count]);
					count ++;
				}
			}
		}
		return loadedFile;
	}
	
	// Checks the to see if the specified number can be placed in the specified location
	private boolean checkPlace(int row, int col, int numToCheck)
	{
		boolean unique = false;
		
		if (colCheck(col, numToCheck) && rowCheck(row, numToCheck) && blockCheck(board[row][col], numToCheck))
			unique = true;
		
		return unique;
	}
	
	// Helps checkPlace
	private boolean colCheck(int col, int numToCheck)
	{
		boolean unique = true;
		for (int i = 0; i < SIZE; i ++)
		{
			if (board[i][col].getValue() == numToCheck)
				unique = false;
		}
		return unique;
	}
	
	// Helps checkPlace
	private boolean rowCheck(int row, int numToCheck)
	{
		boolean unique = true;
		for (int i = 0; i < SIZE; i ++)
		{
			if (board[row][i].getValue() == numToCheck)
				unique = false;
		}
		return unique;
	}
	
	// Helps checkPlace
	private boolean blockCheck(Cell cellToCheck, int numToCheck)
	{
		boolean unique = true;
		
		int checkedRow = cellToCheck.getRow();
		int checkedCol = cellToCheck.getColumn();
		
		checkedRow = checkedRow - checkedRow % 3;
		checkedCol = checkedCol - checkedCol % 3;
		
		for (int r = checkedRow; r < checkedRow+3; r++)
		{
			for (int c = checkedCol; c < checkedCol+3; c++)
			{
				if (board[r][c].getValue() == numToCheck)
					unique = false;
			}
		}
		
		return unique;
	}
}
