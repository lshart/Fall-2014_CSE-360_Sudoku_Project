package sudoku;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PuzzlePanel extends JFrame 
{
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private BoardManager newManager;
	private CellLabel[][] cellLabelGrid;
	private JLabel currNumLable;
	JLabel messageLabel;
	private boolean pen;
	private boolean erase;
	
	public PuzzlePanel(BoardManager nManager) 
	{
		addKeyListener(new KeyWatcher());
		setFocusable(true);
		
		cellLabelGrid = new CellLabel[9][9];
		newManager = nManager;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 498, 473);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(30, 144, 255));
		contentPane.setBorder(new EmptyBorder(9, 9, 9, 9));
		setContentPane(contentPane);
		pen = true;
		erase = false;
		
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{124, 83, 59, 63, 71, 0};
		gbl_contentPane.rowHeights = new int[]{261, 75, 23, 40, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		
		JButton btnGetHint = new JButton("Get hint");
		btnGetHint.setFont(new Font("Tahoma", Font.PLAIN, 11));
		JButton btnEraser = new JButton("Eraser");
		JButton btnPencil = new JButton("Pencil");

		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridwidth = 5;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		panel.setLayout(new GridLayout(9, 9, 1, 1));
		
		for (int c = 0; c < 9; c++)
			for (int r = 0; r < 9; r++)
			{
				cellLabelGrid[r][c] = new CellLabel(r, c);
				cellLabelGrid[r][c].setOpaque(true);
				cellLabelGrid[r][c].setBackground(Color.WHITE);
				cellLabelGrid[r][c].setHorizontalTextPosition(SwingConstants.CENTER);
				cellLabelGrid[r][c].setHorizontalAlignment(SwingConstants.CENTER);
				cellLabelGrid[r][c].addMouseListener(new CellAction());
				panel.add(cellLabelGrid[r][c]);
			}
		
		messageLabel = new JLabel("");
		messageLabel.setForeground(Color.ORANGE);
		messageLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		messageLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_messageLabel = new GridBagConstraints();
		gbc_messageLabel.insets = new Insets(0, 0, 5, 5);
		gbc_messageLabel.gridx = 0;
		gbc_messageLabel.gridy = 1;
		contentPane.add(messageLabel, gbc_messageLabel);

		btnPencil.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_btnPencil = new GridBagConstraints();
		gbc_btnPencil.anchor = GridBagConstraints.SOUTH;
		gbc_btnPencil.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPencil.insets = new Insets(0, 0, 5, 5);
		gbc_btnPencil.gridx = 2;
		gbc_btnPencil.gridy = 2;
		contentPane.add(btnPencil, gbc_btnPencil);
		btnEraser.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_btnEraser = new GridBagConstraints();
		gbc_btnEraser.anchor = GridBagConstraints.SOUTH;
		gbc_btnEraser.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEraser.insets = new Insets(0, 0, 5, 5);
		gbc_btnEraser.gridx = 3;
		gbc_btnEraser.gridy = 2;
		contentPane.add(btnEraser, gbc_btnEraser);
		GridBagConstraints gbc_btnGetHint = new GridBagConstraints();
		gbc_btnGetHint.anchor = GridBagConstraints.NORTH;
		gbc_btnGetHint.insets = new Insets(0, 0, 5, 0);
		gbc_btnGetHint.gridx = 4;
		gbc_btnGetHint.gridy = 2;
		contentPane.add(btnGetHint, gbc_btnGetHint);
			
		JTextArea txtrScore = new JTextArea();
		
		txtrScore.setBackground(new Color(30, 144, 255));
		txtrScore.setText("Par Time: 00:00\r\nTime: 00:00");
		GridBagConstraints gbc_txtrScore = new GridBagConstraints();
		gbc_txtrScore.anchor = GridBagConstraints.NORTHWEST;
		gbc_txtrScore.insets = new Insets(0, 0, 0, 5);
		gbc_txtrScore.gridx = 0;
		gbc_txtrScore.gridy = 3;
		contentPane.add(txtrScore, gbc_txtrScore);
		
		currNumLable = new JLabel();
		
		currNumLable.setFont(new Font("Tahoma", Font.PLAIN, 15));
		currNumLable.setForeground(Color.BLACK);
		currNumLable.setHorizontalTextPosition(SwingConstants.CENTER);
		currNumLable.setHorizontalAlignment(SwingConstants.CENTER);


		GridBagConstraints gbc_currNumLable = new GridBagConstraints();
		gbc_currNumLable.fill = GridBagConstraints.BOTH;
		gbc_currNumLable.insets = new Insets(0, 0, 0, 5);
		gbc_currNumLable.gridx = 2;
		gbc_currNumLable.gridy = 3;
		contentPane.add(currNumLable, gbc_currNumLable);
		
		updatePanel();
	}
	
	private void updatePanel()
	{
		for (int c = 0; c < 9; c++)
			for (int r = 0; r < 9; r++)
			{
				if (newManager.getCellValueAt(r, c) != 0)
					cellLabelGrid[r][c].setText(newManager.getCellValueAt(r, c) + "");
				else
					cellLabelGrid[r][c].setText("");
			}
		
		currNumLable.setText(newManager.getCurrentNum() + "");
		
		contentPane.updateUI();
	}
	
	private class CellAction extends MouseAdapter
	{
		public void mouseClicked(MouseEvent cell) 
		{
			CellLabel theCell = (CellLabel) cell.getSource();
			int theRow = theCell.getRow();
			int theCol = theCell.getCol();
			
			if (erase)
			{
				if(newManager.removeNum(theRow, theCol))
					newManager.updateTime(5);
			}
			
			if (pen)
			{
				if(!newManager.placeNum(theRow, theCol))
				{
					messageLabel.setText("<html><div style=\"text-align: center;\">Cell is a<br> clue</html>");
				}

			}
			updatePanel();		
		}
	}
	
	private class KeyWatcher extends KeyAdapter
	{
		public void keyPressed(KeyEvent key) 
		{
			char keyChar = key.getKeyChar();

			if (Character.isDigit(keyChar))
			{
				
				String temp =  "" + key.getKeyChar();
				int tempInt = Integer.parseInt(temp);
				if (tempInt != 0)
				{
					newManager.setCurrentNum(tempInt);
					updatePanel();
				}
			}
		}
	}
	
	
	private class CellLabel extends JLabel
	{
		private static final long serialVersionUID = 453763732448995862L;
		private int row;
		private int col;
		
		public CellLabel(int r, int c)
		{
			super();
			row = r;
			col = c;
		}
		
		public int getRow()
		{	return row;	}
		
		public int getCol()
		{	return col;	}
	}
}
	
