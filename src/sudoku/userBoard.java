package sudoku;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.*;


public class userBoard extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	JButton btnHard, btnEasy, btnEvil, btnMedium, btnCreateNewUser; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					userBoard frame = new userBoard();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public userBoard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();// make a new panel
		contentPane.setBackground(new Color(30, 144, 255));// set color and border and such
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();// new text fiels
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
				PuzzlePanel panel =new PuzzlePanel();// get new puzzle board
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
				PuzzlePanel panel =new PuzzlePanel();
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
				PuzzlePanel panel =new PuzzlePanel();
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
				PuzzlePanel panel =new PuzzlePanel();
				panel.setVisible(true);
				contentPane.setVisible(false);
				dispose();
			}
		});
		contentPane.add(btnEvil);
		
		
		JButton btnCreateNewUser = new JButton("Create new");
		btnCreateNewUser.setBounds(50, 208, 113, 23);
		contentPane.add(btnCreateNewUser);
		btnCreateNewUser.addActionListener(new ActionListener() {// if create new is clicked 
			public void actionPerformed(ActionEvent e)// add the new user info to user manager
			{
				if(textField.getText() != null && textField_1.getText() != null)
				{
					String name= textField.getText();
					String pass = textField_1.getText();
				}
			}
		});
		
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

