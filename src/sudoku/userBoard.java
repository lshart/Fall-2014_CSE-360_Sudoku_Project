package sudoku;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class userBoard extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3012544989644192322L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnUsername = new JButton("username");
		btnUsername.setBounds(37, 62, 89, 23);
		contentPane.add(btnUsername);
		
		JButton btnPassword = new JButton("password");
		btnPassword.setBounds(37, 143, 89, 23);
		contentPane.add(btnPassword);
		
		textField = new JTextField();
		textField.setBounds(37, 177, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(37, 93, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnEasy = new JButton("Easy");
		btnEasy.setBounds(295, 49, 89, 23);
		contentPane.add(btnEasy);
		
		JButton btnMedium = new JButton("Medium");
		btnMedium.setBounds(295, 92, 89, 23);
		contentPane.add(btnMedium);
		
		JButton btnHard = new JButton("Hard");
		btnHard.setBounds(295, 131, 89, 23);
		contentPane.add(btnHard);
		
		JButton btnEvil = new JButton("Evil");
		btnEvil.setBounds(295, 176, 89, 23);
		contentPane.add(btnEvil);
		
		JButton btnCreateNewUser = new JButton("Create new user");
		btnCreateNewUser.setBounds(34, 208, 113, 23);
		contentPane.add(btnCreateNewUser);
		
		JTextPane txtpnPleaseEnterIn = new JTextPane();
		txtpnPleaseEnterIn.setText("Please enter in a new user name and password");
		txtpnPleaseEnterIn.setBounds(47, 11, 144, 40);
		contentPane.add(txtpnPleaseEnterIn);
	}
}