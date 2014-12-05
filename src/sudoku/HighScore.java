package sudoku;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
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
	private UserManager thisUserManager;

	DefaultListModel<String> listModel = new DefaultListModel<String>();
	
	
	JList<String> scores;
	
	public HighScore(UserManager thisUM) 
	{
		thisUserManager = thisUM;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 317, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblChallengeSudokuHigh = new JLabel("Challenge Sudoku Scoreboard");
		lblChallengeSudokuHigh.setForeground(Color.BLACK);
		lblChallengeSudokuHigh.setBackground(new Color(255, 175, 175));
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(152, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblChallengeSudokuHigh, GroupLayout.PREFERRED_SIZE, 424, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblChallengeSudokuHigh, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		
		scrollPane.setViewportView(scores);
		populateList();
		JList<String> list = new JList<String>(listModel);
		scrollPane.setViewportView(list);
		contentPane.setLayout(gl_contentPane);
		

	}
	
	public void populateList()
	{
		UserNode currentUser = thisUserManager.getHeadUser();
	
		while(currentUser != null)
		{
			
			addScore("<html><div style=\"text-align: center;\">" + currentUser.getUser().getName()+ "<br> Score: /" + currentUser.getUser().getScore(0) +" / " + currentUser.getUser().getScore(1) + " / " + currentUser.getUser().getScore(2) + " / " + currentUser.getUser().getScore(3)+ "</html>");
			currentUser = currentUser.getNextUser();
		}
	}
	
	public void addScore(String addMe)
		{
			listModel.addElement(addMe);
		}
		
		
}
