/////////////
//The UserPanel class makes the panel to create username, create password, the login and logout features, it also contains
//the level selcetion. 
/////////////
package sudoku;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;


public class UserPanel extends JFrame 
{
	//declares global variables for UserPanel class
	private static final long serialVersionUID = -5802065054242533217L;
	private JPanel contentPane;
	private JLabel messageLable;
	private JTextField passField;
	private JTextField userField;
	private UserManager thisManager;
	private boolean loggedIn;
	JCheckBox AICheckbox;
	JButton logOutButton;
	JButton btnHard, btnEasy, btnEvil, btnMedium, btnCreateNewUser; 

	/**
	 * Create the frame.
	 */
	public UserPanel(UserManager thisM) {
		thisManager = thisM;
		loggedIn= thisManager.isLoggedIn();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 352, 307);
		contentPane = new JPanel();// make a new panel
		contentPane.setBackground(new Color(245, 245, 245));// set color and border and such
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		passField = new JTextField();// new text fields
		passField.setBounds(61, 138, 86, 20);
		contentPane.add(passField);
		passField.setColumns(10);
		
		userField = new JTextField();// new text field
		userField.setBounds(61, 93, 86, 20);
		contentPane.add(userField);
		userField.setColumns(10);
		
		JButton btnEasy = new JButton("Easy");// make a new button
		btnEasy.setFocusable(false);
		btnEasy.setBounds(213, 92, 89, 23);
		btnEasy.addActionListener(new ActionListener() {// if button is pushed
			public void actionPerformed(ActionEvent e)
			{
				if (loggedIn)
				{
					boolean AI = AICheckbox.isSelected();
					BoardManager bMe = new BoardManager(0, thisManager.getSelectedUser());
					PuzzlePanel panel = new PuzzlePanel(bMe, thisManager, AI);// get new puzzle board
					panel.setVisible(true);
					contentPane.setVisible(false);// destroy the old content pane
					dispose();
				}
			}
		});
		contentPane.add(btnEasy);
		
		JButton btnMedium = new JButton("Medium");
		btnMedium.setFocusable(false);
		btnMedium.setBounds(213, 126, 89, 23);
		btnMedium.addActionListener(new ActionListener() {// same as easy
			public void actionPerformed(ActionEvent e)
			{
				if (loggedIn)
				{
					boolean AI = AICheckbox.isSelected();
					BoardManager bMe = new BoardManager(1, thisManager.getSelectedUser());
					PuzzlePanel panel = new PuzzlePanel(bMe, thisManager, AI);// get new puzzle board
					panel.setVisible(true);
					contentPane.setVisible(false);
					dispose();
				}
			}
		});
		contentPane.add(btnMedium);
		
		
		JButton btnHard = new JButton("Hard");
		btnHard.setFocusable(false);
		btnHard.setBounds(213, 160, 89, 23);
		btnHard.addActionListener(new ActionListener() {// smae as easy
			public void actionPerformed(ActionEvent e)
			{
				if (loggedIn)
				{
					boolean AI = AICheckbox.isSelected();
					BoardManager bMe = new BoardManager(2, thisManager.getSelectedUser());
					PuzzlePanel panel = new PuzzlePanel(bMe,thisManager, AI);// get new puzzle board
					panel.setVisible(true);
					contentPane.setVisible(false);
					dispose();
				}
			}
		});
		contentPane.add(btnHard);
		
		
		JButton btnEvil = new JButton("Evil");// same as easy
		btnEvil.setFocusable(false);
		btnEvil.setBounds(213, 194, 89, 23);
		btnEvil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if (loggedIn)
				{
					boolean AI = AICheckbox.isSelected();
					BoardManager bMe = new BoardManager(3, thisManager.getSelectedUser());
					PuzzlePanel panel = new PuzzlePanel(bMe, thisManager, AI);// get new puzzle board
					panel.setVisible(true);
					contentPane.setVisible(false);
					dispose();
				}
			}
		});
		contentPane.add(btnEvil);
		
		
		JButton btnCreateNewUser = new JButton("Create new");
		btnCreateNewUser.setFocusable(false);
		btnCreateNewUser.setBounds(50, 169, 113, 23);
		btnCreateNewUser.addActionListener (new NewUserAction()); // if create new is clicked 
					
		contentPane.add(btnCreateNewUser);
		
		JLabel lblPassword = new JLabel("Password");// make a label for the password
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBackground(new Color(30, 144, 255));
		lblPassword.setBounds(61, 124, 86, 14);
		contentPane.add(lblPassword);
		
		JLabel lblUsername = new JLabel("Username");// make a label for username
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setBackground(new Color(30, 144, 255));
		lblUsername.setBounds(61, 74, 86, 14);
		contentPane.add(lblUsername);
		
		JLabel lblNewLabel = new JLabel("Challenge Sudoku");// makes the title
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBackground(new Color(30, 144, 255));
		lblNewLabel.setBounds(67, 22, 208, 65);
		contentPane.add(lblNewLabel);
		
		messageLable = new JLabel("");
		messageLable.setHorizontalAlignment(SwingConstants.CENTER);
		messageLable.setForeground(SystemColor.menuText);
		messageLable.setBounds(201, 228, 113, 42);
		contentPane.add(messageLable);
		
		JButton loginButton = new JButton("Login");
		loginButton.setFocusable(false);
		loginButton.addActionListener(new LoginAction());
		loginButton.setBounds(50, 203, 113, 23);
		contentPane.add(loginButton);
		
		logOutButton = new JButton("");
		logOutButton.setFocusable(false);
		logOutButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				messageLable.setText("<html><div style=\"text-align: center;\">" + thisManager.getSelectedUser().getName() + "<br> Logged Out<html>");
				thisManager.logOut();
				logOutButton.setText("Log off");
				loggedIn = false;
			}
		});
		if (thisManager.getSelectedUser() != null)
			logOutButton.setText("Log off " + thisManager.getSelectedUser().getName());
		else
			logOutButton.setText("Log off");
		logOutButton.setBounds(50, 235, 113, 23);
		contentPane.add(logOutButton);
		
		JButton btnHighScore = new JButton("High Score");
		btnHighScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if (thisManager.isListEmpty())
				{
					HighScore score = new HighScore();
					score.setVisible(true);
				}
			}
		});
		btnHighScore.setBounds(213, 11, 113, 29);
		btnHighScore.setFocusable(false);
		contentPane.add(btnHighScore);
		
		AICheckbox = new JCheckBox("Activate AI");
		AICheckbox.setBackground(new Color(245, 245, 245));
		AICheckbox.setFocusable(false);
		AICheckbox.setBounds(50, 17, 97, 23);
		contentPane.add(AICheckbox);
		}
	
	private class NewUserAction implements ActionListener
	{
		public void actionPerformed(ActionEvent e)// add the new user info to user manager
		{
			String user = userField.getText();
			String password = passField.getText();
			
			if (!user.isEmpty() && !password.isEmpty())
			{
				if(thisManager.addUser(user, password))
				{
					messageLable.setText("<html><div style=\"text-align: center;\">Created New User<br>" + thisManager.getSelectedUser().getName() + "</html>");
					loggedIn = true;
					logOutButton.setText("Log off " + thisManager.getSelectedUser().getName());
				}
				else
				{
					messageLable.setText("<html><div style=\"text-align: center;\">A User With that<br>Name Already Exists</html>");
				}
			}
			else
			{
				messageLable.setText("<html><div style=\"text-align: center;\">Please fill in<br>all fields</html>");
			}
			
			contentPane.updateUI();
		}
	}
	
	private class LoginAction implements ActionListener
	{
		public void actionPerformed(ActionEvent e)// add the new user info to user manager
		{
			String user = userField.getText();
			String password = passField.getText();
			if (!user.isEmpty() && !password.isEmpty())
			{
				if(thisManager.findUser(user, password))
				{
					messageLable.setText(thisManager.getSelectedUser().getName() + " Logged In");
					loggedIn = true;
					logOutButton.setText("Log off " + thisManager.getSelectedUser().getName());
				}
				else
				{
					messageLable.setText("<html><div style=\"text-align: center;\">User Name or <br>Password is Invalid</html>");
				}
			}
			else
			{
				messageLable.setText("<html><div style=\"text-align: center;\">Please fill in<br>all fields</html>");
			}
			
			contentPane.updateUI();
		}
	}
}

