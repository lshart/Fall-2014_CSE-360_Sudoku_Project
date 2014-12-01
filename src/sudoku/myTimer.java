package sudoku;

import javax.swing.JLabel;
import javax.swing.Timer;

import java.awt.event.*;
import java.text.DecimalFormat;

public class myTimer
{
	private Timer countUp;
	private int sec;
	private int min;
	private DecimalFormat leadingZero = new DecimalFormat("#00");//format
	private PuzzlePanel panelForTest;
	private int parMin;
	private int parSec;

	public myTimer()
	{
		parMin = 0;
		parSec = 0;
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

						}
					}

		};
		countUp = new Timer(1000, actione);
		countUp.start();
		sec = 0;
		min = 0;
	}

	public myTimer(JLabel timed, PuzzlePanel tempPanel)
	{
		panelForTest = tempPanel;
		parMin = 0;
		parSec = 0;
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
							testPar();
							timed.setText("Time: " + leadingZero.format(getMin()) + " : " + leadingZero.format(getSec()));
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
	public void setPar(int tempMin, int tempSec)
	{
		parMin = tempMin;
		parSec = tempSec;
		
	}

	public void testPar()
	{
		if (min >= parMin && sec >= parSec)
		{
			panelForTest.toggle_overtime();
		}
		
	}


}
