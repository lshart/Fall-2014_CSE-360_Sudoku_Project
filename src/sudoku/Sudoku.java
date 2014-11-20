package sudoku;

public class Sudoku
{
	public static final String FILE_NAME = "/data/userList.ser";

	public static void main(String[] args) 
	{
		UserManager thisM = new UserManager();
		BoardManager blaj = new BoardManager(0, null);
		blaj.placeNum(2, 4, 8);
		userBoard frame = new userBoard(thisM);
		frame.setVisible(true);
	}
}
