package sudoku;

public class Sudoku
{
	public static final String FILE_NAME = "data/userList.ser";

	public static void main(String[] args) 
	{
		UserManager thisM = new UserManager();
		thisM.retrieveUserList(FILE_NAME);
		UserPanel frame = new UserPanel(thisM);
		frame.setVisible(true);
/*
		User currU = new User("Luke", "pass");
		BoardManager testM = new BoardManager(0, currU);
		for (int r = 0; r < 9; r++)
		{
			for (int c = 0; c < 9; c++)
			{
				System.out.print(testM.getCellValueAt(r, c) + " ");
			}
			System.out.println();
		}*/
	}
}
