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
		switch (difficulty)
		{
		case 0:
			loadPuzzle("/Puzzles/Easy1.dat");
			break;
		case 1:
			loadPuzzle("/Puzzles/Easy1.dat"); //
			break;
		case 2:
			loadPuzzle("/Puzzles/Easy1.dat"); // UNTIL SUCH TIME AS I (or someone) MAKES 2 MORE PUZZLES
			break;
		case 3:
			loadPuzzle("/Puzzles/Evil1.dat");
			break;
		default:
			loadPuzzle("/Puzzles/Easy1.dat");
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
	
	public boolean hasWon()
	{
		boolean won = true;
		
		for (int r = 0; r < SIZE; r++)
			for (int c = 0; c < SIZE; c++)
				if (!board[r][c].isCorrect())
					won = false;
		
		return won;
	}
	
	private boolean loadPuzzle (String fileName) /////////////////////////////////////////////////////// I"M WORKING!!!
	{
		boolean loadedFile = false;
		String answerKey, clueKey;
		int[] boardAnswer = new int[SIZE];
		boolean[] boardClues = new boolean[SIZE];
		
		try
		{
			FileReader fileIn = new FileReader(fileName);
			BufferedReader in = new BufferedReader(fileIn);
			
			answerKey = in.readLine();
			clueKey = in.readLine();
			
			InputStream answers = new ByteArrayInputStream(answerKey.getBytes(Charset.defaultCharset()));
			InputStream clues = new ByteArrayInputStream(clueKey.getBytes(Charset.defaultCharset()));
			
			Scanner reader = new Scanner(answers);
			
			for (int i = 0; i < SIZE; i++)
				boardAnswer[i] = reader.nextInt();
			
			reader.close();
			reader = new Scanner(clues);
			
			for (int i = 0; i < SIZE; i++)
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
		
	
		int count = 0; // a temporary count, used to assign the answers to the cells, as well as their clue status
		for (int i = 0; i < SIZE; i++)
		{
			for (int k = 0; k < SIZE; k++)
			{		
				board[i][k] = new Cell(i, k, boardAnswer[count], boardClues[count]);
				count ++;
			}
		}
		
		return loadedFile;
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
