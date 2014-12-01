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


public class UserPanel extends JFrame 
{
	private static final long serialVersionUID = -5802065054242533217L;
	private JPanel contentPane;
	private JLabel messageLable;
	private JTextField passField;
	private JTextField userField;
	private UserManager thisManager;
	private boolean loggedIn;
	JButton btnHard, btnEasy, btnEvil, btnMedium, btnCreateNewUser; 

	/**
	 * Create the frame.
	 */
	public UserPanel(UserManager thisM) {
		thisManager = thisM;
		loggedIn= false;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();// make a new panel
		contentPane.setBackground(new Color(30, 144, 255));// set color and border and such
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		passField = new JTextField();// new text fields
		passField.setBounds(61, 177, 86, 20);
		contentPane.add(passField);
		passField.setColumns(10);
		
		userField = new JTextField();// new text field
		userField.setBounds(61, 118, 86, 20);
		contentPane.add(userField);
		userField.setColumns(10);
		
		JButton btnEasy = new JButton("Easy");// make a new button
		btnEasy.setBounds(295, 49, 89, 23);
		btnEasy.addActionListener(new ActionListener() {// if button is pushed
			public void actionPerformed(ActionEvent e)
			{
				if (loggedIn)
				{
					BoardManager bMe = new BoardManager(0, thisManager.getSelectedUser());
					PuzzlePanel panel = new PuzzlePanel(bMe);// get new puzzle board
					panel.setVisible(true);
					contentPane.setVisible(false);// destroy the old content pane
					dispose();
				}
			}
		});
		contentPane.add(btnEasy);
		
		JButton btnMedium = new JButton("Medium");
		btnMedium.setBounds(295, 92, 89, 23);
		btnMedium.addActionListener(new ActionListener() {// same as easy
			public void actionPerformed(ActionEvent e)
			{
				if (loggedIn)
				{
					BoardManager bMe = new BoardManager(1, thisManager.getSelectedUser());
					PuzzlePanel panel = new PuzzlePanel(bMe);// get new puzzle board
					panel.setVisible(true);
					contentPane.setVisible(false);
					dispose();
				}
			}
		});
		contentPane.add(btnMedium);
		
		
		JButton btnHard = new JButton("Hard");
		btnHard.setBounds(295, 131, 89, 23);
		btnHard.addActionListener(new ActionListener() {// smae as easy
			public void actionPerformed(ActionEvent e)
			{
				if (loggedIn)
				{
					BoardManager bMe = new BoardManager(2, thisManager.getSelectedUser());
					PuzzlePanel panel = new PuzzlePanel(bMe);// get new puzzle board
					panel.setVisible(true);
					contentPane.setVisible(false);
					dispose();
				}
			}
		});
		contentPane.add(btnHard);
		
		
		JButton btnEvil = new JButton("Evil");// same as easy
		btnEvil.setBounds(295, 176, 89, 23);
		btnEvil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if (loggedIn)
				{
					BoardManager bMe = new BoardManager(3, thisManager.getSelectedUser());
					PuzzlePanel panel = new PuzzlePanel(bMe);// get new puzzle board
					panel.setVisible(true);
					contentPane.setVisible(false);
					dispose();
				}
			}
		});
		contentPane.add(btnEvil);
		
		
		JButton btnCreateNewUser = new JButton("Create new");
		btnCreateNewUser.setBounds(50, 208, 113, 23);
		btnCreateNewUser.addActionListener (new NewUserAction()); // if create new is clicked 
					
		contentPane.add(btnCreateNewUser);
		
		JLabel lblPassword = new JLabel("Password");// make a label for the password
		lblPassword.setBackground(new Color(30, 144, 255));
		lblPassword.setBounds(77, 159, 70, 14);
		contentPane.add(lblPassword);
		
		JLabel lblUsername = new JLabel("Username");// make a label for username
		lblUsername.setBackground(new Color(30, 144, 255));
		lblUsername.setBounds(77, 101, 69, 14);
		contentPane.add(lblUsername);
		
		JLabel lblNewLabel = new JLabel("Sudoku");// makes the title
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBackground(new Color(30, 144, 255));
		lblNewLabel.setBounds(77, 34, 69, 14);
		contentPane.add(lblNewLabel);
		
		messageLable = new JLabel("");
		messageLable.setForeground(new Color(255, 255, 255));
		messageLable.setBounds(295, 218, 133, 42);
		contentPane.add(messageLable);
		
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new LoginAction());
		loginButton.setBounds(50, 243, 117, 29);
		contentPane.add(loginButton);
	

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
				}
				else
				{
					messageLable.setText("<html><div style=\"text-align: center;\">A User With that<br>Name Already Exists</html>");
				}
			}
			else
			{
				messageLable.setText("<html><div style=\"text-align: center;\">Please Fill<br>All Fields</html>");
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
				}
				else
				{
					messageLable.setText("<html><div style=\"text-align: center;\">User Name or <br>Password is Invalid</html>");
				}
			}
			else
			{
				messageLable.setText("<html><div style=\"text-align: center;\">Please Fill<br>All Fields</html>");
			}
			
			contentPane.updateUI();
		}
	}
}

