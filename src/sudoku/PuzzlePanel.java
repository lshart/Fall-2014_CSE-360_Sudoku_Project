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
		contentPane.setBackground(new Color(30, 144, 255));
		contentPane.setBorder(new EmptyBorder(9, 9, 9, 9));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnPencil = new JButton("Pencil");
		btnPencil.addActionListener(new ActionListener() {// if button is pushed
			public void actionPerformed(ActionEvent e)
			{
			}
		});
		btnPencil.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnPencil.setBounds(264, 105, 68, 23);
		contentPane.add(btnPencil);
		
		JButton btnEraser = new JButton("Eraser");
		btnEraser.addActionListener(new ActionListener() {// if button is pushed
			public void actionPerformed(ActionEvent e)
			{
			}
		});
		btnEraser.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnEraser.setBounds(342, 105, 68, 23);
		contentPane.add(btnEraser);
		
	//	table = new JTable();
		Border lineBorder = new LineBorder(Color.BLACK,2);
		JPanel table2 = new JPanel(new GridLayout(3,3));
		table2.setBorder(lineBorder);
		JPanel table =  new JPanel(new GridLayout(3,3));
		table.setBackground(Color.WHITE);
		for (int k =0; k<9; k++)
		{
			table2 = new JPanel(new GridLayout(3,3));

			table2.setBorder(lineBorder);
			for(int i =0; i <=8; i++){
				JLabel temp = new JLabel();
				temp.setText("  1");
				temp.setBorder(lineBorder);
				table2.add(temp);
			}
			for(int i = 0; i <=8; i++){
				table.add(table2);
			}
		}
		
		
		JButton btnGetHint = new JButton("Get hint");
		btnGetHint.addActionListener(new ActionListener() {// if button is pushed
			public void actionPerformed(ActionEvent e)
			{
			}
		});
		btnGetHint.setBounds(283, 194, 89, 23);
		contentPane.add(btnGetHint);
		table.setBounds(10, 24, 214, 144);
		contentPane.add(table);
		
		//JTextArea textArea = new JTextArea();
		JLabel inputLabel = new JLabel(); 
		inputLabel.setText("Pencil: 1");
		
	//	textArea.addInputMethodListener(new InputMethodListener() {
	//		public void caretPositionChanged(InputMethodEvent arg0) {
	//		}
		//	public void inputMethodTextChanged(InputMethodEvent arg0) {
		//	}
	//	});
		inputLabel.setBounds(283, 150, 81, 22);
		contentPane.add(inputLabel);
		
		JTextArea txtrScore = new JTextArea();
		txtrScore.setBackground(new Color(30, 144, 255));
		txtrScore.setText("Score:\r\nTime:"); //this is where you return score and time data
		txtrScore.setBounds(20, 193, 101, 40);
		contentPane.add(txtrScore);
	}
}
	
