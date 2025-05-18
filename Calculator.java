import javax.swing.*;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import java.util.ArrayList;
/**
* Calculator class
*/
public class Calculator extends FixFrame implements Runnable
{
	//Variable to hold the operand as a string
	private String operandString;
	
	//Variable to hold the operator as a string
	private String operator;
	
	//Variable to hold the first operand as a double
	private double operand1;
	
	//Variable to hold the second operand as a double
	private double operand2;
	
	//Variable to hold memory 1
	private double mem1;
	
	//Variable to hold memory 2
	private double mem2;
	
	//Variable to hold memory 3
	private double mem3;
	
	//Variables for the number buttons
	private JButton num0;
	private JButton num1;
	private JButton num2;
	private JButton num3;
	private JButton num4;
	private JButton num5;
	private JButton num6;
	private JButton num7;
	private JButton num8;
	private JButton num9;
	
	//Variable for the JTextField that holds the display
	private JTextField display;
	
	//Varialbes for the operand buttons
	private JButton plus;
	private JButton minus;
	private JButton multiply;
	private JButton divide;
	private JButton period;
	private JButton squared;
	private JButton m1plus;
	private JButton m2plus;
	private JButton m3plus;
	private JButton m1minus;
	private JButton m2minus;
	private JButton m3minus;
	private JButton readm1;
	private JButton readm2;
	private JButton readm3;
	
	/**
	* Basic constructor for calculator class, to be used when ran as a thread<br>
	* Preconditions: none<br>
	* Postconditions: none<br>
	* Throws: none<br>
	*/
	public Calculator()
	{
		super(640, 480, "Calculator");
	}
	
	/**
	* Overloaded Constructor for calculator class<br>
	* Preconditions: none<br>
	* Postconditions: none<br>
	* Throws: none<br>
	*/
	public Calculator(int i)
	{
		//Remove this when used as a thread
		super(640, 480, "Calculator");
		setup();
	}
	
	/**
	* Run method used when the calculator is run as a thread<br>
	* Preconditions: none<br>
	* Postconditions: The calculator thread is started.<br>
	* Throws: none<br>
	*/
	public void run()
	{
		setup();
	}
	
	/**
	* Method to setup the calculator gui<br>
	* Preconditions: none<br>
	* Postconditions: The calculator gui is setup<br>
	* Throws: none<br>
	*/
	public void setup()
	{
		//Make the window visible
		setVisible(true);
	}
}
	