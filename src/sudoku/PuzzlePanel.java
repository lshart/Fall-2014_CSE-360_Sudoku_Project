package sudoku;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;

public class PuzzlePanel extends JFrame 
{
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private BoardManager newManager;
	private CellLabel[][] cellLabelGrid;
	private JLabel currNumLabel;
	private JLabel messageLabel;
	private JLabel hintLabel;
	private JLabel parTimeLabel;
	private JLabel timeLabel;
	private boolean pen;
	private boolean erase;
	private boolean overtime_check;
	private DecimalFormat leadingZero = new DecimalFormat("#00");
	private myTimer gameTime ;
	
	public PuzzlePanel(BoardManager nManager) 
	{	
		addKeyListener(new KeyWatcher());
		setFocusable(true);
		
		overtime_check = false;
		
		cellLabelGrid = new CellLabel[9][9];
		newManager = nManager;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 498, 473);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(30, 144, 255));
		contentPane.setBorder(new EmptyBorder(9, 9, 9, 9));
		this.setContentPane(contentPane);
		pen = true;
		erase = false;
		
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{124, 83, 59, 63, 71, 0};
		gbl_contentPane.rowHeights = new int[]{261, 75, 23, 0, 0, 40, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		
		JButton btnGetHint = new JButton("Get hint");
		btnGetHint.addActionListener (new HintButton());
		btnGetHint.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnGetHint.setFocusable(false);
		JButton btnEraser = new JButton("Eraser");
		btnEraser.addActionListener (new eraseButton());
		btnEraser.setFocusable(false);
		JButton btnPencil = new JButton("Pencil");
		btnPencil.addActionListener (new penButton());
		btnPencil.setFocusable(false);

		
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
		
		String curUserStr = newManager.getCurrentUser().getName();
		String easyWins = "<font color=\"green\">" + newManager.getCurrentUser().getScore(0);
		String medWins = "<font color=\"orange\">" + newManager.getCurrentUser().getScore(0);
		String hardWins = "<font color=\"red\">" + newManager.getCurrentUser().getScore(0);
		String evilWins = "<font color=\"black\">" + newManager.getCurrentUser().getScore(0);
		String winStr = easyWins + "/" + medWins + "/" + hardWins + "/" + evilWins;
		JLabel userLabel = new JLabel("<html><div style=\"text-align: center;\">" + curUserStr + " " + winStr + "</html>");
		userLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		userLabel.setHorizontalAlignment(SwingConstants.LEFT);
		
		GridBagConstraints gbc_userLabel = new GridBagConstraints();
		gbc_userLabel.anchor = GridBagConstraints.EAST;
		gbc_userLabel.insets = new Insets(0, 0, 5, 0);
		gbc_userLabel.gridx = 4;
		gbc_userLabel.gridy = 1;
		contentPane.add(userLabel, gbc_userLabel);

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
		
		timeLabel = new JLabel("Time: 00:00");
		timeLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_timeLabel = new GridBagConstraints();
		gbc_timeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_timeLabel.gridx = 0;
		gbc_timeLabel.gridy = 3;
		contentPane.add(timeLabel, gbc_timeLabel);
		
		currNumLabel = new JLabel();
		
		currNumLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		currNumLabel.setForeground(Color.BLACK);
		currNumLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		currNumLabel.setHorizontalAlignment(SwingConstants.CENTER);
		

		GridBagConstraints gbc_currNumLable = new GridBagConstraints();
		gbc_currNumLable.fill = GridBagConstraints.BOTH;
		gbc_currNumLable.insets = new Insets(0, 0, 5, 5);
		gbc_currNumLable.gridx = 2;
		gbc_currNumLable.gridy = 3;
		contentPane.add(currNumLabel, gbc_currNumLable);

		hintLabel = new JLabel("Hints: ");
		GridBagConstraints gbc_hintLabel = new GridBagConstraints();
		gbc_hintLabel.insets = new Insets(0, 0, 5, 0);
		gbc_hintLabel.gridx = 4;
		gbc_hintLabel.gridy = 3;
		contentPane.add(hintLabel, gbc_hintLabel);
		
		int tempParTime = newManager.getParTime();
		int parSeconds = tempParTime % 60;
		int parMinutes = tempParTime / 60;
		parTimeLabel = new JLabel("Par Time: "+ leadingZero.format(parMinutes) +":" + leadingZero.format(parSeconds));
		GridBagConstraints gbc_parTimeLabel = new GridBagConstraints();
		gbc_parTimeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_parTimeLabel.gridx = 0;
		gbc_parTimeLabel.gridy = 4;
		contentPane.add(parTimeLabel, gbc_parTimeLabel);
		
		gameTime = new myTimer(timeLabel, this);
		gameTime.setPar(parMinutes, parSeconds);
		
		updatePanel();
	}
	
	public void toggle_overtime()
	{
		if (!overtime_check)
		{
			overtime_check = !overtime_check;
			newManager.set_overTime();
		}
		
	}
	public boolean get_overtime()
	{
		return overtime_check;
	}
	
	public void updatePanel()
	{
		for (int c = 0; c < 9; c++)
			for (int r = 0; r < 9; r++)
			{
				if (newManager.getCellValueAt(r, c) != 0)
					cellLabelGrid[r][c].setText(newManager.getCellValueAt(r, c) + "");
				else
					cellLabelGrid[r][c].setText("");
			}
		
		currNumLabel.setText(newManager.getCurrentNum() + "");
		hintLabel.setText("Hints: " + newManager.getHints());
		
		contentPane.updateUI();
	}
	
	public void setParTime(int time)
	{
		parTimeLabel.setText("Par Time: " + time + " : 00");
		
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
					gameTime.setSec(5);
			}
			
			if (pen)
			{
				if(!newManager.placeNum(theRow, theCol))
				{
					messageLabel.setText("<html><div style=\"text-align: center;\">INVALID<br> MOVE</html>");
				}
				else
					messageLabel.setText(null);
					

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
	
	private class HintButton implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			if (newManager.getHints() > 0)
			{
				int temp = newManager.useHint();
				String message = "<html><div style=\"text-align: center;\">"+ temp + " incorrect cells <br> removed</html>";
				
				messageLabel.setText(message);
				
				updatePanel();
			}
			else
			{
				String message = "<html><div style=\"text-align: center;\"> No hints <br> remaining</html>";
				
				messageLabel.setText(message);
			}
		}
	}
	
	private class penButton implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			pen = true;
			erase = false;
		}
		
	
	}
	
	private class eraseButton implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			pen = false;
			erase = true;
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
	
