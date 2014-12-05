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
	private UserManager thisUserManager;
	private CellLabel[][] cellLabelGrid;
	private JLabel currNumLabel;
	private JLabel messageLabel;
	private JLabel hintLabel;
	private JLabel parTimeLabel;
	private JLabel timeLabel;
	private boolean pen;
	private boolean erase;
	private boolean overtime_check;
	private boolean hasAI;
	private DecimalFormat leadingZero = new DecimalFormat("#00");
	private myTimer gameTime;
	private JLabel lblCurrentPlayer;
	private JButton btnQuit;
	private JPanel scorePanel;
	private JPanel gridSubPanel;
	private JPanel messagePanel;
	
	public PuzzlePanel(BoardManager nManager, UserManager uManager, boolean aI) 
	{	
		JPanel cellGridPanel = new JPanel();
		thisUserManager = uManager;
		thisUserManager.storeUserList(Sudoku.FILE_NAME);
		overtime_check = false;
		hasAI = aI;
		
		timeLabel = new JLabel("Time: 00:00");
		gameTime = new myTimer(timeLabel, this);

		newManager = nManager;
		
		int tempParTime = newManager.getParTime();
		int parSeconds = tempParTime % 60;
		int parMinutes = tempParTime / 60;
		gameTime.setPar(parMinutes, parSeconds);
		
		addKeyListener(new KeyWatcher());
		setFocusable(true);
		
		
		cellLabelGrid = new CellLabel[9][9];
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 440, 647);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(9, 9, 9, 9));
		this.setContentPane(contentPane);
		pen = true;
		erase = false;
		
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {124};
		gbl_contentPane.rowHeights = new int[] {1, 300};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		contentPane.setLayout(gbl_contentPane);
		
		for (int r = 0; r < 9; r++)
			for (int c = 0; c < 9; c++)
			{
				cellLabelGrid[r][c] = new CellLabel(r, c);
				cellLabelGrid[r][c].setOpaque(true);
				cellLabelGrid[r][c].setBackground(Color.WHITE);
				cellLabelGrid[r][c].setHorizontalTextPosition(SwingConstants.CENTER);
				cellLabelGrid[r][c].setHorizontalAlignment(SwingConstants.CENTER);
				cellLabelGrid[r][c].addMouseListener(new CellAction());
				cellGridPanel.add(cellLabelGrid[r][c]);
			}
		
		for (int i = 0; i < 9; i++)
			cellLabelGrid[i][2].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		for (int i = 0; i < 9; i++)
			cellLabelGrid[i][5].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		for (int i = 0; i < 9; i++)
			cellLabelGrid[3][i].setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
		for (int i = 0; i < 9; i++)
			cellLabelGrid[6][i].setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
		
		cellLabelGrid[3][2].setBorder(BorderFactory.createMatteBorder(1, 0, 0, 1, Color.BLACK));
		cellLabelGrid[3][5].setBorder(BorderFactory.createMatteBorder(1, 0, 0, 1, Color.BLACK));
		cellLabelGrid[6][2].setBorder(BorderFactory.createMatteBorder(1, 0, 0, 1, Color.BLACK));
		cellLabelGrid[6][5].setBorder(BorderFactory.createMatteBorder(1, 0, 0, 1, Color.BLACK));
		
		String curUserStr = newManager.getCurrentUser().getName();

		String winStr = newManager.getCurrentUser().printUserScore();
		
		gridSubPanel = new JPanel();
		gridSubPanel.setBackground(new Color(245, 245, 245));
		gridSubPanel.setLayout(null);
		GridBagConstraints gbc_gridSubPanel = new GridBagConstraints();
		gbc_gridSubPanel.gridheight = 6;
		gbc_gridSubPanel.gridwidth = 7;
		gbc_gridSubPanel.insets = new Insets(0, 0, 5, 0);
		gbc_gridSubPanel.fill = GridBagConstraints.BOTH;
		gbc_gridSubPanel.gridx = 0;
		gbc_gridSubPanel.gridy = 0;
		contentPane.add(gridSubPanel, gbc_gridSubPanel);
		
				
				cellGridPanel.setBounds(10, 11, 390, 390);
				gridSubPanel.add(cellGridPanel);
				cellGridPanel.setBackground(Color.BLACK);
				cellGridPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
				cellGridPanel.setLayout(new GridLayout(9, 9, 1, 1));
				
				messagePanel = new JPanel();
				messagePanel.setBackground(new Color(245, 245, 245));
				messagePanel.setBounds(10, 412, 99, 83);
				gridSubPanel.add(messagePanel);
				messagePanel.setLayout(null);
				
				messageLabel = new JLabel("");
				messageLabel.setBounds(0, 0, 99, 83);
				messagePanel.add(messageLabel);
				messageLabel.setForeground(Color.ORANGE);
				messageLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
				messageLabel.setHorizontalTextPosition(SwingConstants.CENTER);
				messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
				
				scorePanel = new JPanel();
				scorePanel.setBackground(new Color(245, 245, 245));
				scorePanel.setBounds(140, 412, 226, 55);
				gridSubPanel.add(scorePanel);
				scorePanel.setLayout(null);
				
				lblCurrentPlayer = new JLabel("Current Player:");
				lblCurrentPlayer.setBounds(4, 19, 87, 16);
				scorePanel.add(lblCurrentPlayer);
				lblCurrentPlayer.setFont(new Font("Tahoma", Font.PLAIN, 13));
				
				JLabel userLabel = new JLabel("");
				userLabel.setBackground(new Color(245, 245, 245));
				userLabel.setBounds(93, 11, 131, 33);
				scorePanel.add(userLabel);
				userLabel.setText("<html><div style=\"text-align: left;\">" + curUserStr + " " + winStr + "</html>");
				userLabel.setHorizontalTextPosition(SwingConstants.LEFT);
				userLabel.setHorizontalAlignment(SwingConstants.LEFT);
		
		timeLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_timeLabel = new GridBagConstraints();
		gbc_timeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_timeLabel.gridx = 0;
		gbc_timeLabel.gridy = 6;
		contentPane.add(timeLabel, gbc_timeLabel);
	
		JButton btnPencil = new JButton("Pencil");
		btnPencil.addActionListener (new penButton());
		btnPencil.setFocusable(false);
		
				btnPencil.setFont(new Font("Tahoma", Font.PLAIN, 11));
				GridBagConstraints gbc_btnPencil = new GridBagConstraints();
				gbc_btnPencil.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnPencil.insets = new Insets(0, 0, 5, 5);
				gbc_btnPencil.gridx = 2;
				gbc_btnPencil.gridy = 6;
				contentPane.add(btnPencil, gbc_btnPencil);
		JButton btnEraser = new JButton("Eraser");
		btnEraser.addActionListener (new eraseButton());
		btnEraser.setFocusable(false);
		btnEraser.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_btnEraser = new GridBagConstraints();
		gbc_btnEraser.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEraser.insets = new Insets(0, 0, 5, 5);
		gbc_btnEraser.gridx = 3;
		gbc_btnEraser.gridy = 6;
		contentPane.add(btnEraser, gbc_btnEraser);
		
		
		JButton btnGetHint = new JButton("Get hint");
		btnGetHint.addActionListener (new HintButton());
		btnGetHint.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnGetHint.setFocusable(false);
		GridBagConstraints gbc_btnGetHint = new GridBagConstraints();
		gbc_btnGetHint.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnGetHint.insets = new Insets(0, 0, 5, 5);
		gbc_btnGetHint.gridx = 4;
		gbc_btnGetHint.gridy = 6;
		contentPane.add(btnGetHint, gbc_btnGetHint);
		parTimeLabel = new JLabel("Par Time: "+ leadingZero.format(parMinutes) +":" + leadingZero.format(parSeconds));
		GridBagConstraints gbc_parTimeLabel = new GridBagConstraints();
		gbc_parTimeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_parTimeLabel.gridx = 0;
		gbc_parTimeLabel.gridy = 7;
		contentPane.add(parTimeLabel, gbc_parTimeLabel);
		
		currNumLabel = new JLabel();
		
		currNumLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		currNumLabel.setForeground(Color.BLACK);
		currNumLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		currNumLabel.setHorizontalAlignment(SwingConstants.CENTER);
		

		GridBagConstraints gbc_currNumLable = new GridBagConstraints();
		gbc_currNumLable.fill = GridBagConstraints.BOTH;
		gbc_currNumLable.insets = new Insets(0, 0, 5, 5);
		gbc_currNumLable.gridx = 2;
		gbc_currNumLable.gridy = 7;
		contentPane.add(currNumLabel, gbc_currNumLable);
		
				hintLabel = new JLabel("Hints: ");
				GridBagConstraints gbc_hintLabel = new GridBagConstraints();
				gbc_hintLabel.insets = new Insets(0, 0, 5, 5);
				gbc_hintLabel.gridx = 4;
				gbc_hintLabel.gridy = 7;
				contentPane.add(hintLabel, gbc_hintLabel);
		
		btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				UserPanel panel = new UserPanel(thisUserManager);
				panel.setVisible(true);
				contentPane.setVisible(false);
				dispose();
			}
		});
		GridBagConstraints gbc_btnQuit = new GridBagConstraints();
		gbc_btnQuit.insets = new Insets(0, 0, 5, 5);
		gbc_btnQuit.gridx = 4;
		gbc_btnQuit.gridy = 8;
		contentPane.add(btnQuit, gbc_btnQuit);
		
		
		updatePanel();
	}
	
	public void toggle_overtime()
	{
		if (!overtime_check)
		{
			overtime_check = !overtime_check;
			newManager.set_overTime();
			timeLabel.setForeground(Color.RED);
		}
		
	}
	public boolean get_overtime()
	{
		return overtime_check;
	}
	
	public void updatePanel()
	{
		for (int r = 0; r < 9; r++)
		{
			for (int c = 0; c < 9; c++)
			{
				if (newManager.getCellValueAt(r, c) != 0)
				{
					if (!newManager.isHint(r, c))
						cellLabelGrid[r][c].setForeground(Color.BLUE);
					cellLabelGrid[r][c].setText(newManager.getCellValueAt(r, c) + "");
				}
				else
					cellLabelGrid[r][c].setText("");
			}

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
				updatePanel();
			}
			
			if (pen)
			{
				if(!newManager.placeNum(theRow, theCol))
				{
					if(!newManager.isHint(theRow, theCol))
					{
						if(cellLabelGrid[theRow][theCol].getText().equalsIgnoreCase(""))
						{
							cellLabelGrid[theRow][theCol].setForeground(Color.RED);
							cellLabelGrid[theRow][theCol].setText(newManager.getCurrentNum() + "");
						}
						messageLabel.setText(null);
					}
					else
					{
						messageLabel.setText("<html><div style=\"text-align: center;\">Cell is a<br> hint</html>");
					}
				}
				else
				{
					messageLabel.setText(null);
					cellLabelGrid[theRow][theCol].setForeground(Color.BLACK);
					updatePanel();
					if (newManager.hasWon(gameTime.getMin(), gameTime.getSec()))
					{
						WinScreen panel = new WinScreen(thisUserManager, gameTime.getMin(), gameTime.getSec());
						panel.setVisible(true);
						contentPane.setVisible(false);
						dispose();
					}
					
					if (hasAI)
					{
						if(!newManager.isCellCorrect(theRow, theCol))
						{
							newManager.removeNum(theRow, theCol);
							messageLabel.setText("<html><div style=\"text-align: center;\">AI says, <br>\"Bad move!\"</html>");
							gameTime.setSec(5);
							updatePanel();
						}
						else
						{
							int randrow = ((int)(Math.random() * 9.0));
							int randcol = ((int)(Math.random() * 9.0));
							
							for (int r = 0; r < 9; r++)
							{
								for (int c = 0; c < 9; c++)
								{
									if (!newManager.isHint(randrow, randcol) && newManager.getCellValueAt(randrow, randcol) == 0)
									{
										r = 9;
										c = 9;
										newManager.placeCorrectCell(randrow, randcol);
										messageLabel.setText("<html><div style=\"text-align: center;\">AI says, <br>\"Placed Number at\" <br>"+ randrow + ", " + randcol +"</html>");
										updatePanel();
									}
									randcol++;
									if (randcol > 8)
										randcol = 0;
								}
								
								randcol = ((int)(Math.random() * 9.0));
								randrow++;
								if (randrow > 8)
									randrow = 0;
							}
								
						}
						
					}
					
					if (newManager.hasWon(gameTime.getMin(), gameTime.getSec()))
					{
						WinScreen panel = new WinScreen(thisUserManager, gameTime.getMin(), gameTime.getSec());
						panel.setVisible(true);
						contentPane.setVisible(false);
						dispose();
					}
				}

			}
					
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
					pen = true;
					erase = false;
					newManager.setCurrentNum(tempInt);
					updatePanel();
				}
			}
			
			if (key.getKeyCode() == 35)
			{
				newManager.cheatWin();
				updatePanel();
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
				gameTime.setSec(5*temp);
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
	
