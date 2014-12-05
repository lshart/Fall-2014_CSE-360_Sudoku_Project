/////////////
// The myTimer class creates the timer that will displayed on the puzzle pannel, and it will add penalties accordingly. 
/////////////
package sudoku;

import javax.swing.JLabel;
import javax.swing.Timer;

import java.awt.event.*;
import java.text.DecimalFormat;

public class myTimer
{
	//declares global variables for timer class
	private Timer countUp;
	private int sec;
	private int min;
	private DecimalFormat leadingZero = new DecimalFormat("#00");//format
	private PuzzlePanel panelForTest;
	private int parMin;
	private int parSec;

	// DEFAULT CONSTRUCTOR initializes the timer and starts it counting up from 0
	public myTimer()
	{
		parMin = 0;
		parSec = 0;
		ActionListener actione = new ActionListener()
		{
				// generates an event to increment the counter
				public void actionPerformed(ActionEvent e)
					{

						if(e.getSource() == countUp)
						{
							sec++;
							//if the seconds reach 60 increase minutes by one and reset sec to 0.
							if(sec == 60)
							{
								sec = 0;
								min++;
							}

						}
					}

		};
		// the timer should wait 1000 miliseconds before creating an event
		countUp = new Timer(1000, actione);
		countUp.start();
		sec = 0;
		min = 0;
	}

	//creates a timer that will place its time onto a jlabel and activate an event based on a preset time
	public myTimer(final JLabel timed, PuzzlePanel tempPanel)
	{
		// variables for crating a panel
		panelForTest = tempPanel;
		parMin = 0;
		parSec = 0;
		//action listener for the timer
		ActionListener actione = new ActionListener()
		{
			// generates an event to increment the counter
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
							//test to see if conditions are met
							testPar();
							// print to jlabel in a standar 00:00 format
							timed.setText("Time: " + leadingZero.format(getMin()) + " : " + leadingZero.format(getSec()));
						}
					}

		};
		// the timer should wait 1000 miliseconds before creating an event
		countUp = new Timer(1000, actione);
		countUp.start();
		sec = 0;
		min = 0;
	}

	// getters for the time variables
	public int getSec()
	{
		return sec;
	}

	public int getMin()
	{
		return min;
	}
	
	//increment function for penalties for seconds, if more than 1 min is added it will decrease seconds till below 1 min and increase min acordingly
	public void setSec(int inc)
	{

		sec += inc;
		while(sec >= 60)
		{
			sec -= 60;
			min++;
		}

	}
	
	//increase min for penalties
	public void setMin (int inc)
	{
		min += inc; 
	}
	
	//setter for time to execute a method 
	public void setPar(int tempMin, int tempSec)
	{
		parMin = tempMin;
		parSec = tempSec;
		
	}

	//tests conditions to executed a method passed in
	public void testPar()
	{
		if (min >= parMin && sec >= parSec)
		{
			panelForTest.toggle_overtime();
		}
		
	}


}
