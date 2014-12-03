package sudoku;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class WinScreen extends JFrame 
{

	private static final long serialVersionUID = -8523586303609256724L;
	private JPanel contentPane;
	private UserManager thisUserManager;

	public WinScreen(UserManager thisM) 
	{
		thisUserManager = thisM;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 352, 284);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCongradulations = new JLabel("Congradulations!");
		lblCongradulations.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblCongradulations.setHorizontalAlignment(SwingConstants.CENTER);
		lblCongradulations.setBounds(54, 44, 210, 50);
		contentPane.add(lblCongradulations);
		
		JLabel nameLabel = new JLabel("UserName");
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setBounds(89, 105, 124, 20);
		contentPane.add(nameLabel);
		
		JLabel timeLabel = new JLabel("Won the game in 00:00");
		timeLabel.setHorizontalAlignment(SwingConstants.LEFT);
		timeLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		timeLabel.setBounds(77, 136, 180, 25);
		contentPane.add(timeLabel);
		
		JButton btnOkay = new JButton("Okay");
		btnOkay.setBounds(105, 184, 89, 23);
		contentPane.add(btnOkay);
	}
}
