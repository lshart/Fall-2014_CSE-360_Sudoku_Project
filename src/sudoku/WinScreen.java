/////////////
// The WinScreen class makes the win screen, it is activated once the user wins a sudoku game
/////////////
package sudoku;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.awt.Color;

public class WinScreen extends JFrame 
{
	//declares global values for WinScreen class
	private static final long serialVersionUID = -8523586303609256724L;
	private JPanel contentPane;
	private UserManager thisUserManager;
	// default constructor makes the board,buttons, labels, and it sets the location of each piece
	public WinScreen(UserManager thisM, int min, int sec) 
	{
		DecimalFormat leadingZero = new DecimalFormat("#00");//format
		
		thisUserManager = thisM;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 352, 284);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCongradulations = new JLabel("Congradulations!");// makes the jlabel
		lblCongradulations.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblCongradulations.setHorizontalAlignment(SwingConstants.CENTER);
		lblCongradulations.setBounds(54, 44, 210, 50);
		contentPane.add(lblCongradulations);
		
		JLabel nameLabel = new JLabel(thisUserManager.getSelectedUser().getName());// gets the current username
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setBounds(89, 105, 124, 20);
		contentPane.add(nameLabel);
		
		String timeStr = min + ":" + leadingZero.format(sec);
		JLabel timeLabel = new JLabel("Won the game in " + timeStr);// returns the time
		timeLabel.setHorizontalAlignment(SwingConstants.LEFT);
		timeLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		timeLabel.setBounds(77, 136, 180, 25);
		contentPane.add(timeLabel);
		
		JButton btnOkay = new JButton("Okay");// if the okay button is pressed then go back to user pannel
		btnOkay.setBounds(105, 184, 89, 23);
		btnOkay.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				UserPanel panel = new UserPanel(thisUserManager);
				panel.setVisible(true);
				contentPane.setVisible(false);
				dispose();
			}
		});
		
		contentPane.add(btnOkay);
	}
}
