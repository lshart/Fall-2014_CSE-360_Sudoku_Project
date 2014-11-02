package sudoku;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class PuzzlePanel extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	//private JTable table;  // why is this table here?

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	 
	public PuzzlePanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(9, 9, 9, 9));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Pencil");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnNewButton.setBounds(246, 105, 53, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Paper");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnNewButton_1.setBounds(306, 105, 53, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Eraser");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnNewButton_2.setBounds(369, 105, 55, 23);
		contentPane.add(btnNewButton_2);
		
		table = new JTable();
		Border lineBorder = new LineBorder(Color.BLACK,2);
		JPanel table2 = new JPanel(new GridLayout(3,3));
		table2.setBorder(lineBorder);
		JPanel table =  new JPanel(new GridLayout(3,3));
		for (int k =0; k<9; k++)
		{
			table2 = new JPanel(new GridLayout(3,3));

			table2.setBorder(lineBorder);
			for(int i =0; i <=8; i++){
				table2.add(new JTextField(1));
			}
			for(int i = 0; i <=8; i++){
				table.add(table2);
			}
		}
		
		
		JButton btnGetHint = new JButton("Get hint");
		btnGetHint.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		btnGetHint.setBounds(283, 194, 89, 23);
		contentPane.add(btnGetHint);
		table.setBounds(10, 24, 214, 144);
		contentPane.add(table);
		
		JTextArea textArea = new JTextArea();
		
		textArea.addInputMethodListener(new InputMethodListener() {
			public void caretPositionChanged(InputMethodEvent arg0) {
			}
			public void inputMethodTextChanged(InputMethodEvent arg0) {
			}
		});
		textArea.setBounds(283, 150, 81, 22);
		contentPane.add(textArea);
		
		JTextArea txtrScore = new JTextArea();
		txtrScore.setText("Score:\r\nTime:"); //this is where you return score and time data
		txtrScore.setBounds(20, 193, 101, 40);
		contentPane.add(txtrScore);
	}
}
	
	
