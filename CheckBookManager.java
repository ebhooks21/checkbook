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
* Manager class for the checkbook driver program.
*/
public class CheckBookManager extends FixFrame
{
	//Variable to hold the transactions
	private ArrayList<Transaction> transArray;
	
	//Variable for the transaction list
	private JList transList;
	
	//Variable for the new transaction button
	private JButton newTransBtn;
	
	//Variable for the remove transaction button
	private JButton removeTransBtn;
	
	//Variable for the load a transaction register button
	private JButton loadTransBtn;
	
	//Variable for the save transaction register button
	private JButton saveTransBtn;
	
	//Variable for the deposit button
	private JButton depositBtn;
	
	//Variable for the exit button
	private JButton exitBtn;
	
	//Variable for the calculator button
	//private JButton calcBtn;
	
	//Variable for the refresh button
	//private JButton refreshBtn;
	
	//Variable for the JButton that holds file info
	//private JButton fileInfoBtn;
	
	//Variable for the create new trans reg button
	//private JButton createTransRegBtn;
	
	//Variable for the menu bar
	private JMenuBar menuBar;
	
	//Variable for the actions menu bar item
	private JMenu actionMenu;
	private JMenu aboutMenu;
	
	//Varialbes for the text fields for the balances
	private JTextField cBalance;
	private JTextField pBalance;
	private JLabel transNum;
	private JLabel pos;
	private JLabel transtype;
	private JLabel amount;
	private JLabel date;
	
	//Variables for the actionMenu actions
	private JMenuItem newReg;
	private JMenuItem newRegwoldamount;
	private JMenuItem newTrans;
	private JMenuItem removeTrans;
	private JMenuItem updateTrans;
	private JMenuItem loadTrans;
	private JMenuItem saveTrans;
	private JMenuItem exit;
	private JMenuItem about;
	private JMenuItem help;
	private JMenuItem changeBalance;
	
	//Variable for the available balance
	private double currentBalance;
	private double previousBalance;
	private double unalteredBalance;
	
	//Variable for the gui listener
	private GuiListener listener = new GuiListener();
	
	//Set a font
	private static final Font font = new Font("Courier", Font.BOLD, 12);
	
	//Variable to hold the name of the transaction register
	private String transName;
	
	//Variable to hold whether or not the user has saved the current register
	private volatile boolean isSaved;
	
	//Variable to hold the calculator thread
	//Thread calcThread;
	
	/**
	* Constructor for the CheckBook Manager.<br>
	* Preconditions: none<br>
	* Postconditions: The gui is set up.<br>
	* Throws: none<br>
	*/
	public CheckBookManager(int width, int height, String title)
	{
		//Pass the width, height, and title to fixframe
		super(width, height, title);
		
		//Set the balances to 0
		currentBalance = 0.00;
		previousBalance = 0.00;
		
		//Set up the jlist
		transList = new JList();
		
		//Set isSaved to true
		isSaved = true;
		
		//Set up the transaction array
		transArray = new ArrayList<Transaction>();
		
		//Create a default object for the jlist
		Object [] obj = {"No transaction register loaded, please load a transaction register"};
		transList.setListData(obj);
		transList.setFont(font);
		
		//Create a south panel
		JPanel south = new JPanel();
		south.setBackground(Color.white);
		south.setLayout(new GridLayout(1, 6)); //Old layout was 1, 6
		
		//Create the buttons for the south panel
		newTransBtn = new JButton("Add Transaction");
		newTransBtn.addActionListener(listener);
		newTransBtn.setActionCommand("Add Transaction");
		//Make the button inactive, until the user loads a register or makes a new one
		newTransBtn.setEnabled(false);
		removeTransBtn = new JButton("Remove Transaction");
		removeTransBtn.addActionListener(listener);
		removeTransBtn.setActionCommand("Remove Transaction");
		//Make the button inactive, until the user loads a register or makes a new one
		removeTransBtn.setEnabled(false);
		depositBtn = new JButton("Deposit");
		depositBtn.addActionListener(listener);
		depositBtn.setActionCommand("Deposit");
		//Make the button inactive, until the user loads a register or makes a new one
		depositBtn.setEnabled(false);
		loadTransBtn = new JButton("Load");
		loadTransBtn.addActionListener(listener);
		loadTransBtn.setActionCommand("Load");
		saveTransBtn = new JButton("Save");
		saveTransBtn.addActionListener(listener);
		saveTransBtn.setActionCommand("Save");
		saveTransBtn.setEnabled(false);
		exitBtn = new JButton("Quit");
		exitBtn.addActionListener(listener);
		exitBtn.setActionCommand("Quit");
		/*calcBtn = new JButton("Calculator");
		calcBtn.addActionListener(listener);
		calcBtn.setActionCommand("Calculator");
		refreshBtn = new JButton("Refresh");
		refreshBtn.addActionListener(listener);
		refreshBtn.setActionCommand("Refresh");
		fileInfoBtn = new JButton("File Info");
		fileInfoBtn.addActionListener(listener);
		fileInfoBtn.setActionCommand("File Info");
		createTransRegBtn = new JButton("Create Trans. Reg.");
		createTransRegBtn.addActionListener(listener);
		createTransRegBtn.setActionCommand("Create a new Transaction Register");*/
		
		//Add the buttons to the south panel
		south.add(newTransBtn);
		south.add(removeTransBtn);
		south.add(depositBtn);
		south.add(loadTransBtn);
		south.add(saveTransBtn);
		/*south.add(fileInfoBtn);
		south.add(calcBtn);
		south.add(refreshBtn);
		south.add(createTransRegBtn);*/
		south.add(exitBtn);
		
		//Create the menubar
		menuBar = new JMenuBar();
		actionMenu = new JMenu("Actions");
		newReg = new JMenuItem("Create a new Transaction Register");
		newReg.addActionListener(listener);
		newRegwoldamount = new JMenuItem("Create a new Transaction Register with previous balance");
		newRegwoldamount.addActionListener(listener);
		newTrans = new JMenuItem("Add Transaction");
		newTrans.addActionListener(listener);
		newTrans.setEnabled(false);
		removeTrans = new JMenuItem("Remove Transaction");
		removeTrans.addActionListener(listener);
		removeTrans.setEnabled(false);
		updateTrans = new JMenuItem("Refresh");
		updateTrans.addActionListener(listener);
		changeBalance = new JMenuItem("Manually Change Current Balance");
		changeBalance.addActionListener(listener);
		changeBalance.setEnabled(false);
		loadTrans = new JMenuItem("Load");
		loadTrans.addActionListener(listener);
		saveTrans = new JMenuItem("Save");
		saveTrans.addActionListener(listener);
		saveTrans.setEnabled(false);
		exit = new JMenuItem("Quit");
		exit.addActionListener(listener);
		aboutMenu = new JMenu("About/Help");
		help = new JMenuItem("Help");
		help.addActionListener(listener);
		about = new JMenuItem("About CheckBook");
		about.addActionListener(listener);
		
		
		//Add the menu items to the menu
		actionMenu.add(newReg);
		actionMenu.add(newRegwoldamount);
		actionMenu.add(newTrans);
		actionMenu.add(removeTrans);
		actionMenu.add(updateTrans);
		actionMenu.add(changeBalance);
		actionMenu.add(loadTrans);
		actionMenu.add(saveTrans);
		actionMenu.add(exit);
		menuBar.add(actionMenu);
		aboutMenu.add(help);
		aboutMenu.add(about);
		menuBar.add(aboutMenu);
		
		//Create a north panel
		JPanel north = new JPanel();
		north.setBackground(Color.white);
		EasyGridBag northBag = new EasyGridBag(2, 10, north);
		north.setLayout(northBag);
		
		//Create new text areas and fill them
		cBalance = new JTextField();
		cBalance.setEditable(false);
		pBalance = new JTextField();
		pBalance.setEditable(false);
		transNum = new JLabel("#");
		pos = new JLabel("Place of Sale");
		transtype = new JLabel("Transaction Type");
		amount = new JLabel("Amount");
		date = new JLabel("Date");
		cBalance.setText("Current Balance: " + Currency.formatCurrency(getCurrBalance()));
		pBalance.setText("Starting Balance: " + Currency.formatCurrency(getPrevBalance()));
		
		//Add the text areas to the north panel
		northBag.fillCell(1, 1, 1, 5, GridBagConstraints.BOTH, pBalance);
		northBag.fillCell(1, 6, 1, 10, GridBagConstraints.BOTH, cBalance);
		northBag.fillCell(2, 1, 2, 1, GridBagConstraints.BOTH, transNum);
		northBag.fillCell(2, 2, 2, 3, GridBagConstraints.BOTH, pos);
		northBag.fillCell(2, 5, 2, 5, GridBagConstraints.BOTH, transtype);
		northBag.fillCell(2, 8, 2, 9, GridBagConstraints.BOTH, amount);
		northBag.fillCell(2, 10, 2, 12, GridBagConstraints.BOTH, date);
		
		//Create a center panel and add items to it
		JPanel center = new JPanel();
		center.setBackground(Color.white);
		center.setLayout(new GridLayout(1,1));
		
		//Set up a scrollpane for the jlist
		JScrollPane scroller = new JScrollPane();
		scroller.getViewport().add(transList);
		scroller.setPreferredSize(new Dimension(900, 300));
		scroller.setMaximumSize(new Dimension(900, 300));
		
		//Add the JList to the center panel
		center.add(scroller);
		
		//Add the menubar to the gui
		setJMenuBar(menuBar);
		
		//Add the panels to the gui
		add(north, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);
		
		//Make the window visible
		setVisible(true);
	}
	
	/**
	* Method to get the current balance
	*/
	public double getCurrBalance()
	{
		//Return the current balance to the caller
		return currentBalance;
	}
	
	/**
	* Method to get the previous balance
	*/
	public double getPrevBalance()
	{
		//Return the previous balance to the caller
		return previousBalance;
	}

	/**
	* Method to save transaction registers
	*/
	public void save()
	{
	      //Create the file name for the save file
	      String saveFile = "Registers/" + transName;// + ".register";
				
	      //Try-Catch for opening the file
	      try
	      {
		//Create the file and open it for writing
		FileIO save = new FileIO(saveFile, FileIO.FOR_WRITING);
					
		//Change the transaction information to a string
		String currBS = "" + currentBalance;
		String transName = "";
		String transType = "";
		String transAmount = "";
		String transDate = "";
		String size = "" + transArray.size();
					
		//Begin saving the file
		save.writeLine(currBS);
		save.writeLine(size);
					
		//Write the array of transactions to the file
		for(int i = 0; i < transArray.size(); i++)
		{
		    //Get the transaction out of the array
		    Transaction trans = transArray.get(i);
		    transName += trans.getOrgPos();
		    transType += trans.getTypeOfTrans();
		    transAmount += trans.getAmountOfSale();
		    transDate += trans.getDateOfSale();
						
		    /*Put the transaction into the file in the order:
		      transName,transType,transAmount,transDate*/
						  
		    //Write the transaction to the file
		    save.writeLine(transName + "," + transType + "," + transAmount + "," + transDate);
						
		    //Set the strings back to empty
		    transName = "";
		    transType = "";
		    transAmount = "";
		    transDate = "";
		    trans = null;
		}
					
		//Close the file
		save.close();
					
		//Set the save file to null
		save = null;
					
		//Suggest Garbage collection
		System.gc();
					
		//Change is saved to true
		isSaved = true;
	      }
				
	      catch(Exception e)
	      {
		String error = "An error occured while attempting to write to the file: " + saveFile;
					
		//Output an error message to the user
		JOptionPane.showMessageDialog(null, error, "Error writing to file", JOptionPane.ERROR_MESSAGE);
	      }
	}
	
	/**
	* Private inner class for action listener
	*/
	private class GuiListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			//Get the action and put it in a string
			String action = ae.getActionCommand();
			
			//If the action was exit
			if(action.equals("Quit"))
			{
				//Check to see if the user has saved, if they have not allow them to do so
				if(!isSaved)
				{
					String dialog = "You have not saved the current transaction register.\n" +
									"Would you like to save now?";
					int choice = JOptionPane.showConfirmDialog(null, dialog, "Save", JOptionPane.YES_NO_OPTION);
					
					if(choice == 0)
					{
						//Save the transaction register
						save();

						//Close all threads
						System.exit(0);
					}
					
					else
					{
						//Close all threads
						System.exit(0);
					}
				}
				
				else
				{
					//Close all threads
					System.exit(0);
				}
			}

			else if(action.equals("Manually Change Current Balance"))
			{
				//Ask the user if they really wish to make the change
				String warning = "Warning: Do you really wish to change your account balance?";

				//Display the JOptionPane
				int choice = JOptionPane.showConfirmDialog(null, warning, "Change account balance", JOptionPane.YES_NO_OPTION);

				//Check to see what the user's choice was
				if(choice == 0)
				{
					String alteredBalance =
					JOptionPane.showInputDialog(null, "Please Enter new Account Balance: ");

					//Convert the input to a double
					double aBalance = Double.parseDouble(alteredBalance);

					//Change the balance
					unalteredBalance = currentBalance;
					currentBalance = aBalance;

					//Show the user what has occured
					String message = "Balance changed\nOld Balance: " + Currency.formatCurrency(unalteredBalance)
					+ "\nNew Balance: " + Currency.formatCurrency(currentBalance);

					JOptionPane.showMessageDialog(null, message, "Balance Changed", JOptionPane.PLAIN_MESSAGE);

					//Change the balance display
					cBalance.setText("Current Balance: " + Currency.formatCurrency(getCurrBalance()));
					pBalance.setText("Starting Balance: " + Currency.formatCurrency(getPrevBalance()));
				}
			}
			
			else if(action.equals("Refresh"))
			{
				//Suggest garbage collection
				System.gc();
				
				//Repaint the display
				repaint();
			}
			
			else if(action.equals("Create a new Transaction Register"))
			{
				//Check to see if the user has saved
				if(!isSaved)
				{
				    String dialog = "You have not saved the current transaction register.\n" +
									"Would you like to save now?";
				    int choice = JOptionPane.showConfirmDialog(null, dialog, "Save", JOptionPane.YES_NO_OPTION);
					
				    if(choice == 0)
				    {
					//Save the transaction register
					save();
				    }
					
				}

				//Set is saved to false
				isSaved = false;
				
				transName = " ";
				//Get the name that the user wants to call the transaction register
				transName =
				JOptionPane.showInputDialog(null, "Please enter the name of the transaction register:");
				
				if(transName.equals(" "))
				{
					transName = "transReg1";
				}
				
				//Enable the actions
				newTrans.setEnabled(true);
				removeTrans.setEnabled(true);
				saveTrans.setEnabled(true);
				newTransBtn.setEnabled(true);
				removeTransBtn.setEnabled(true);
				depositBtn.setEnabled(true);
				saveTransBtn.setEnabled(true);
				changeBalance.setEnabled(true);
				
				//Get the user's current balance
				String currentBalanceString =
				JOptionPane.showInputDialog(null, "Please enter the balance in your bank account:");
				
				//Get the currentbalance
				currentBalance = Double.parseDouble(currentBalanceString);
				
				//Set the previousbalance
				previousBalance = currentBalance;
				cBalance.setText("Current Balance: " + Currency.formatCurrency(getCurrBalance()));
				pBalance.setText("Starting Balance: " + Currency.formatCurrency(getPrevBalance()));

				//Empty the old transArray, if any
				transArray.clear();
				
				//Reset the JList
				Object [] objs = {"List is empty, add some transactions to fill it."};
				transList.setListData(objs);
				
				//Repaint the screen
				repaint();
			}
			
			else if(action.equals("Create a new Transaction Register with previous balance"))
			{
				//Check to see if the user has saved
				if(!isSaved)
				{
				    String dialog = "You have not saved the current transaction register.\n" +
									"Would you like to save now?";
				    int choice = JOptionPane.showConfirmDialog(null, dialog, "Save", JOptionPane.YES_NO_OPTION);
					
				    if(choice == 0)
				    {
					//Save the transaction register
					save();
				    }
					
				}

				//Set is saved to false
				isSaved = false;
				
				transName = " ";
				//Get the name that the user wants to call the transaction register
				transName =
				JOptionPane.showInputDialog(null, "Please enter the name of the transaction register:");
				
				if(transName.equals(" "))
				{
					transName = "transReg1";
				}
				
				//Enable the actions
				newTrans.setEnabled(true);
				removeTrans.setEnabled(true);
				saveTrans.setEnabled(true);
				newTransBtn.setEnabled(true);
				removeTransBtn.setEnabled(true);
				depositBtn.setEnabled(true);
				saveTransBtn.setEnabled(true);
				changeBalance.setEnabled(true);
				
				//Get the user's current balance
				String balanceFileName = "";
				//JOptionPane.showInputDialog(null, "Please enter the register you wish to load the balance from:");
				
				JFileChooser fc = new JFileChooser("./Registers");
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				
				int rv = fc.showOpenDialog(CheckBookManager.this);
				
				//Save the name of the transaction register for resaving
				balanceFileName = (fc.getSelectedFile()).getName();
				
				//Get the amount out of the file
				try
				{
					//Create the string for the file name
					String balanceFileFinal = "Registers/" + balanceFileName;// + ".register";
					
					//Attempt to open the file
					FileIO balanceFile = new FileIO(balanceFileFinal, FileIO.FOR_READING);
					
					//Read the first line of the file for the balance
					String currentBalanceString = balanceFile.readLine();
				
					//Get the currentbalance
					currentBalance = Double.parseDouble(currentBalanceString);
					
					//Close the file
					balanceFile.close();
					
					//Set balanceFile to null
					balanceFile = null;
					
					//Suggest garbage collection
					System.gc();
				
					//Set the previousbalance
					previousBalance = currentBalance;
					cBalance.setText("Current Balance: " + Currency.formatCurrency(getCurrBalance()));
					pBalance.setText("Starting Balance: " + Currency.formatCurrency(getPrevBalance()));

					//Empty the old transArray, if any
					transArray.clear();
				
					//Reset the JList
					Object [] objs = {"List is empty, add some transactions to fill it."};
					transList.setListData(objs);
				
					//Repaint the screen
					repaint();
				}
				
				catch(Exception e)
				{
					String error = "An error occured while attempting to open" + balanceFileName + ". The file may not exist, or may be corrupted";
					
					JOptionPane.showMessageDialog(null, error, "Error creating transaction register", JOptionPane.ERROR_MESSAGE);
				}
			}
			
			else if(action.equals("Deposit"))
			{
				//Set isSaved to false
				isSaved = false;
				
				//Get the amount of the deposit
				String depositAmount = 
				JOptionPane.showInputDialog(null, "Enter the amount of the deposit:");
				
				double dAmount = Double.parseDouble(depositAmount);
				
				String depositDate =
				JOptionPane.showInputDialog(null, "Enter the date of the deposit:");
				
				Transaction depositTrans = new Transaction("Bank", dAmount, 4, depositDate);
			
				//Add the transaction to the Object array
				transArray.add(depositTrans);
				
				//Set depositTrans to null
				depositTrans = null;
				
				//Create a new object array
				ArrayList<String> objs = new ArrayList<String>();
				
				//Change all everything in the trans array to a string for output
				for(int i = 0; i < transArray.size(); i++)
				{
					//Get the transaction out
					Transaction trans = transArray.get(i);
					
					String transString = ((i + 1) + "                 " + trans);
					
					//Add the transaction to the object array
					objs.add(transString);
					
					//Set trans to null
					trans = null;
				}	
				
				//Add the array to the JList
				transList.setListData(objs.toArray());
				
				//Subtract the transaction cost
				currentBalance = currentBalance + dAmount;
				cBalance.setText("Current Balance: " + Currency.formatCurrency(getCurrBalance()));
				pBalance.setText("Starting Balance: " + Currency.formatCurrency(getPrevBalance()));
				
				//Set the object array to null
				objs = null;
				
				//Suggest garbage collection
				System.gc();
				
				//Repaint the screen
				repaint();
			}
			
			else if(action.equals("Remove Transaction"))
			{
				//Try to remove a transaction
				try
				{
					//Get the number of the selected transaction
					int transNum = transList.getSelectedIndex();
		
					//Get the transaction, and then get the amount and the type of transaction
					Transaction delTrans = transArray.get(transNum);
					double transAmount = delTrans.getAmountOfSale();
					String transType = delTrans.getTypeOfTrans();
				
					//Check to see the type of transaction
					if(transType.equals("Deposit"))
					{
						//Subtract the amount from the current balance
						currentBalance = currentBalance - transAmount;
					
						//Set the balances
						cBalance.setText("Current Balance: " + Currency.formatCurrency(getCurrBalance()));
						pBalance.setText("Starting Balance: " + Currency.formatCurrency(getPrevBalance()));
					}
				
					else
					{
						//Add the amount to the current balance
						currentBalance = currentBalance + transAmount;
					
						//Set the balances
						cBalance.setText("Current Balance: " + Currency.formatCurrency(getCurrBalance()));
						pBalance.setText("Starting Balance: " + Currency.formatCurrency(getPrevBalance()));
					}
		
					//Remove the transaction from the trans array
					transArray.remove(transNum);
					
					//Set delTrans to null
					delTrans = null;
				
					//Create a new object array
					ArrayList<String> objs = new ArrayList<String>();
				
					//Change all everything in the trans array to a string for output
					for(int i = 0; i < transArray.size(); i++)
					{
						//Get the transaction out
						Transaction trans = transArray.get(i);
						
						//Put the transaction into a string
						String transString = ((i + 1) + "                 " + trans);
					
						//Add the transaction to an array
						objs.add(transString);
						
						//Clear out the transaction
						trans = null;
					}	
				
					//Add the array to the JList
					transList.setListData(objs.toArray());
					
					//Delete the object array
					objs = null;
					
					//Suggest garbage collection
					System.gc();
					
					//Set isSaved to false
					isSaved = false;
				
					//Repaint the screen
					repaint();
				}
				
				catch(ArrayIndexOutOfBoundsException aioobe)
				{
					String error = "No transaction selected, please select a transaction.";
					JOptionPane.showMessageDialog(null, error, "Invalid Transaction", JOptionPane.ERROR_MESSAGE);
				}
			}
			
			else if(action.equals("Save"))
			{
				//Save the transaction register
				save();
			}
			
			else if(action.equals("Load"))
			{
				//Check to see if the user has saved
				if(!isSaved)
				{
				    String dialog = "You have not saved the current transaction register.\n" +
									"Would you like to save now?";
				    int choice = JOptionPane.showConfirmDialog(null, dialog, "Save", JOptionPane.YES_NO_OPTION);
					
				    if(choice == 0)
				    {
					//Save the transaction register
					save();
				    }
					
				}

				//Get the name of the file from the user
				String regName = "";
				//JOptionPane.showInputDialog(null, "Please enter the name that you gave the register:");
				
				JFileChooser fc = new JFileChooser("./Registers");
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				
				int rv = fc.showOpenDialog(CheckBookManager.this);
				
				//Save the name of the transaction register for resaving
				regName = (fc.getSelectedFile()).getName();
				transName = regName;
				
				//Try to open the file and read from it
				try
				{
					//Setup the file name
					String loadFile = "Registers/" + regName;// + ".register";
					
					//Attempt to open the file
					FileIO load = new FileIO(loadFile, FileIO.FOR_READING);
					
					//Read in the current bank balance from the file and set it
					String bAmount = load.readLine();
					currentBalance = Double.parseDouble(bAmount);
					previousBalance = currentBalance;
					cBalance.setText("Current Balance: " + Currency.formatCurrency(getCurrBalance()));
					pBalance.setText("Starting Balance: " + Currency.formatCurrency(getPrevBalance()));
					
					//Set a counter for the number of transactions
					String counter = load.readLine();
					int i = Integer.parseInt(counter);
					
					//Reset transArray
					transArray = null;
					transArray = new ArrayList<Transaction>();
					
					while(i > 0)
					{
						//Read a line of the file
						String str = load.readLine();
						
						//Split the line up to put into transactions
						String [] trans = str.split(",");
						
						//Parse the amount to a double
						double amount = Double.parseDouble(trans[2]);
						
						//Check to see what type of transaction it was
						int transType = -3;
						
						if(trans[1].equals("Check"))
						{
							transType = 1;
						}
						
						else if(trans[1].equals("Debit"))
						{
							transType = 2;
						}
						
						else if(trans[1].equals("Automatic Withdraw"))
						{
							transType = 3;
						}
						
						else if(trans[1].equals("Deposit"))
						{
							transType = 4;
						}
						
						//Create a new transaction and fill it with the information
						Transaction newTrans = new Transaction(trans[0], amount, transType, trans[3]);
						transArray.add(newTrans);
						
						//Decrement the counter
						i--;
						
						//Clear the memory
						newTrans = null;
						trans = null;
						str = null;
						
						//Check to see if it is at the end of the file
						if(load.EOF() || i <= 0)
						{
							break;
						}
					}
					
					//Put the information in transArray, then put it in the JList
					//Create a new object array
					ArrayList<String> objs = new ArrayList<String>();
				
					//Change all everything in the trans array to a string for output
					for(int j = 0; j < transArray.size(); j++)
					{
						//Get the transaction out
						Transaction trans = transArray.get(j);
					
						String transString = ((j + 1) + "           " + trans);
					
						//Add the transaction to the array
						objs.add(transString);
						
						//Set trans to null
						trans = null;
					}	
				
					//Add the array to the JList
					transList.setListData(objs.toArray());
					
					//Enable the actions
					newTrans.setEnabled(true);
					removeTrans.setEnabled(true);
					saveTrans.setEnabled(true);
					newTransBtn.setEnabled(true);
					removeTransBtn.setEnabled(true);
					depositBtn.setEnabled(true);
					saveTransBtn.setEnabled(true);
					
					//Set the object array to null
					objs = null;
					
					//Suggest garbage collection
					System.gc();
					
					//Change is saved to false
					isSaved = false;
					
					//Repaint the screen
					repaint();
					
				}
				
				catch(Exception e)
				{
					String error = "An error occured opening the file.  The file may not exist or may be corrupted.";
					
					//Output an error message to the user
					JOptionPane.showMessageDialog(null, error, "Error reading from file", JOptionPane.ERROR_MESSAGE);
				}
			}
			
			else if(action.equals("Add Transaction"))
			{
				//Set isSaved to false
				isSaved = false;
				
				//Get the place the transaction occured
				String posSale =
				JOptionPane.showInputDialog(null, "Enter the place the transaction occured:");
				
				//Get the type of sale from the user
				String typeSale = 
				JOptionPane.showInputDialog(null, "Enter the type of sale: \n\t1. Check\n\t2. Debit\n\t" +
											"3. Automatic Withdraw");
				
				//Convert the type to an integer
				int type = Integer.parseInt(typeSale);
				
				//Get the amount of the transaction
				String amountSale =
				JOptionPane.showInputDialog(null, "Enter the amount of the transaction:");
				
				//Convert the amount to a double
				double amount = Double.parseDouble(amountSale);
				
				//Get the date that the transaction occured
				String dateSale = 
				JOptionPane.showInputDialog(null, "Enter the date that the transaction occured(MM/DD/YYYY):");
				
				//Create a new transaction object
				Transaction newTrans = new Transaction(posSale, amount, type, dateSale);
				
				//Add the transaction to the Object array
				transArray.add(newTrans);
				
				//Set newTrans to null
				newTrans = null;
				
				//Create a new object array
				ArrayList<String> objs = new ArrayList<String>();
				
				//Change all everything in the trans array to a string for output
				for(int i = 0; i < transArray.size(); i++)
				{
					//Get the transaction out
					Transaction trans = transArray.get(i);
					
					String transString = ((i + 1) + "           " + trans);
					
					//Add the transaction to the object array
					objs.add(transString);
					
					//Set trans to null
					trans = null;
				}	
				
				//Add the array to the JList
				transList.setListData(objs.toArray());
				
				//Set the object array to null
				objs = null;
				
				//Subtract the transaction cost
				currentBalance = currentBalance - amount;
				cBalance.setText("Current Balance: " + Currency.formatCurrency(getCurrBalance()));
				pBalance.setText("Previous Balance: " + Currency.formatCurrency(getPrevBalance()));
				
				//Suggest garbage collection
				System.gc();
				
				//Repaint the screen
				repaint();
			}
			
			else if(action.equals("About CheckBook"))
			{
				String about = "CheckBook Program\nWritten By: Eric Hooks\nDate Of Creation: 06/21/2010" +
								"\nVersion: 2.5";
				
				JOptionPane.showMessageDialog(null, about, "About CheckBook", JOptionPane.PLAIN_MESSAGE);
				
				about = null;
			}
			
			else if(action.equals("Help"))
			{
				//Create a new JTextArea
				JTextArea area = new JTextArea();
				
				//Set the JTextArea to not be editable
				area.setEditable(false);
				
				//Set the font for the JTextArea
				area.setFont(font);
				
				//Set up a JScrollPane and add it to the JTextArea
				//Put the information into the JTextArea
				String helpText = "To begin, please follow the following steps:\n\n" +
									"1. Create a new transaction register by going to the actions menu\n" +
									"and select Create a new transaction register, or Load an already saved\n" +
									"transaction register by selecting Load from the button bar at the bottom\n" +
									"of the window.\n\n2. You can now add transactions by clicking on the\n" +
									"Add Transaction button.\n\n3. To remove a transaction, click on the one\n" +
									"that you wish to remove from the list, then click on the Remove Transaction\n" +
									"button.\n\n4. To add a deposit, just click on the Deposit button.\n\n5. " +
									"To save, just click on the save button. Be sure to remember the name that\n" +
									"you saved it under, so that you may reload it later.\n\n6. You may use " +
									"the quit button to quit at any time,\n but remember to save your " +
									"transaction register for later use.\n";
				area.setText(helpText);
				
				JScrollPane scroller = new JScrollPane();
				scroller.getViewport().add(area);
				
				//Display the help
				JOptionPane.showMessageDialog(null, scroller, "Help", JOptionPane.PLAIN_MESSAGE);
				
				//Set scroller to null and garabage collect
				scroller = null;
				System.gc();
			}
			
			else if(action.equals("Calculator"))
			{
				//Create a new calculator thread
				//calcThread = new Thread(new Calculator(0));
			}
		}
	}
}
	