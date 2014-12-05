/////////////
// The Sudoku class is the main method it makes a new user list and manager 
/////////////
package sudoku;

public class Sudoku
{
	public static final String FILE_NAME = "data/userList.ser";

	public static void main(String[] args) 
	{
		// makes the new instances of each GUI and user class
		UserManager thisM = new UserManager();
		thisM.retrieveUserList(FILE_NAME);
		UserPanel frame = new UserPanel(thisM);
		frame.setVisible(true);
	}
}
