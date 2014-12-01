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
	DecimalFormat leadingZero = new DecimalFormat("#00");


	public myTimer()
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

						}
					}

		};
		countUp = new Timer(1000, actione);
		countUp.start();
		sec = 0;
		min = 0;
	}

	public myTimer(JLabel timed)
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


}
