package sudoku;

import javax.swing.Timer;//imports
import java.awt.event.*;

public class myTimer
{
	private Timer countUp;
	private int sec;
	private int min;


	private myTimer()
	{
		ActionListener actione = new ActionListener()
		{
				public void actionPerformed(ActionEvent e)
					{

						if(e.getSource() == countUp)
						{
							sec++;

							if(sec == 60)
							{
								sec = 0;
								min++;
							}

							PuzzlePanel.updatePanel();
						}
					}

		};
		countUp = new Timer(1000, actione);
		countUp.start();
		sec = 0;
		min = 0;
	}


	public int getSec()
	{
		return sec;
	}

	public int getMin()
	{
		return min;
	}
	public void setSec(int inc)
	{

		sec += inc;
		while(sec >= 60)
		{
			sec -= 60;
			min++;
		}

	}
	public void setMin (int inc)
	{
		min += inc; 
	}


}
