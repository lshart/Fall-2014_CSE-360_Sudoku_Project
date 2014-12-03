package sudoku;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;

@SuppressWarnings("serial")
public class HighScore extends JFrame 
{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	private static final String TITLEINFO = "USER NAME /t/t/t SCORE";
	DefaultListModel<String> listModel = new DefaultListModel();
	
	
	@SuppressWarnings("rawtypes")
	JList scores;
	
	@SuppressWarnings("unchecked")
	public HighScore() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblChallengeSudokuHigh = new JLabel("Challenge Sudoku High Scores");
		lblChallengeSudokuHigh.setForeground(Color.BLACK);
		lblChallengeSudokuHigh.setBackground(new Color(255, 175, 175));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton LogOut = new JButton("Log Out and Exit");
		
		JButton Mainmenu = new JButton("Exit to Main Menu");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(LogOut, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
						.addComponent(Mainmenu, 0, 0, Short.MAX_VALUE))
					.addGap(18))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblChallengeSudokuHigh, GroupLayout.PREFERRED_SIZE, 424, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblChallengeSudokuHigh, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(LogOut, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addGap(31)
							.addComponent(Mainmenu, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		
		
		scrollPane.setViewportView(scores);
		contentPane.setLayout(gl_contentPane);
		listModel.addElement(TITLEINFO);
		populateList();
		scores = new JList(listModel);

	}
	
	public void populateList()
	{
		User currentUser;
		User nextUser;
		UserManager uM = new UserManager();
		uM.retrieveUserList("userList.ser");
		currentUser = uM.getSelectedUser();
		while(uM.getNextUser() != null);
		addScore(currentUser.getName() + " \t\t\t" + currentUser.getScore(0) + " / " + currentUser.getScore(1) + " / " + currentUser.getScore(2) + " / " + currentUser.getScore(3));
		
	}
	
	public void addScore(String addMe)
		{
			listModel.addElement(addMe);
		}
		
		
}