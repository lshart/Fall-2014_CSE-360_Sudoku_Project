package sudoku;

public class Sudoku
{
	public static final String FILE_NAME = "/data/userList.ser";

	public static void main(String[] args) 
	{
		UserManager thisM = new UserManager();
		userPanel frame = new userPanel(thisM);
		frame.setVisible(true);
	}
}
