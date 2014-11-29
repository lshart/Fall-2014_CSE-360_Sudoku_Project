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
	private JTextField textField;
	private JTextField textField_1;
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
		
		textField = new JTextField();// new text fields
		textField.setBounds(61, 177, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();// new text field
		textField_1.setBounds(61, 118, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnEasy = new JButton("Easy");// make a new button
		btnEasy.setBounds(295, 49, 89, 23);
		btnEasy.addActionListener(new ActionListener() {// if button is pushed
			public void actionPerformed(ActionEvent e)
			{
				BoardManager bMe = new BoardManager(0, thisManager.getSelectedUser());
				PuzzlePanel panel = new PuzzlePanel(bMe);// get new puzzle board
				panel.setVisible(true);
				contentPane.setVisible(false);// destroy the old content pane
				dispose();
			}
		});
		contentPane.add(btnEasy);
		
		JButton btnMedium = new JButton("Medium");
		btnMedium.setBounds(295, 92, 89, 23);
		btnMedium.addActionListener(new ActionListener() {// same as easy
			public void actionPerformed(ActionEvent e)
			{
				BoardManager bMe = new BoardManager(1, thisManager.getSelectedUser());
				PuzzlePanel panel = new PuzzlePanel(bMe);// get new puzzle board
				panel.setVisible(true);
				contentPane.setVisible(false);
				dispose();
			}
		});
		contentPane.add(btnMedium);
		
		
		JButton btnHard = new JButton("Hard");
		btnHard.setBounds(295, 131, 89, 23);
		btnHard.addActionListener(new ActionListener() {// smae as easy
			public void actionPerformed(ActionEvent e)
			{
				BoardManager bMe = new BoardManager(2, thisManager.getSelectedUser());
				PuzzlePanel panel = new PuzzlePanel(bMe);// get new puzzle board
				panel.setVisible(true);
				contentPane.setVisible(false);
				dispose();
			}
		});
		contentPane.add(btnHard);
		
		
		JButton btnEvil = new JButton("Evil");// same as easy
		btnEvil.setBounds(295, 176, 89, 23);
		btnEvil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				BoardManager bMe = new BoardManager(3, thisManager.getSelectedUser());
				PuzzlePanel panel = new PuzzlePanel(bMe);// get new puzzle board
				panel.setVisible(true);
				contentPane.setVisible(false);
				dispose();
			}
		});
		contentPane.add(btnEvil);
		
		
		JButton btnCreateNewUser = new JButton("Create new");
		btnCreateNewUser.setBounds(50, 208, 113, 23);
		btnCreateNewUser.addActionListener (new ActionListener() {// if create new is clicked 
			public void actionPerformed(ActionEvent e)// add the new user info to user manager
			{
					String User = textField.getText();
					String Password = textField_1.getText();
		//		    String u = userNameTxt.getText();
		//	        String p = passwordTxt.getText();
			   //     thisManager.addUser(u, p);
				if(true)
				{
	
					JFrame Messageframe =new JFrame();
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					setBounds(100, 100, 228, 139);
					JPanel usernameWindow = new JPanel();
					Messageframe.add(usernameWindow);
					usernameWindow.setBorder(new EmptyBorder(5, 5, 5, 5));
					setContentPane(usernameWindow);
					usernameWindow.setLayout(null);
					JLabel lblNewLabel = new JLabel("Please fill in all fields");
					lblNewLabel.setBounds(53, 31, 134, 14);
					usernameWindow.add(lblNewLabel);
					JButton btnOk = new JButton("Ok");
					btnOk.setBounds(67, 66, 89, 23);
					usernameWindow.add(btnOk);
					usernameWindow.setVisible(true);
					btnOk.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e)
						{
							UserPanel board = new UserPanel(thisManager);
							board.setVisible(true);
							
						//	Messageframe.setVisible(false);
							dispose();
							
						}
					}
					);
					
				}
						
			}
		});
					
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
	

		}
}

