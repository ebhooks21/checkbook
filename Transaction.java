/**
* Class for holding transaction information.
*/
public class Transaction
{
	//Variable for holding the place the transaction
	private String placeOfSale;
	private String orgPOS;
	
	//Variable for holding the amount of the transaction
	private double amountOfSale;
	
	//Variable for the type of transaction
	private int typeOfTransaction;
	private String transactionType;
	
	//Variable for the date of the transaction
	private String date;
	
	/**
	* Constructor for the transaction class<br>
	* Preconditions: none<br>
	* Postconditions: A new check is created.<br>
	* Throws: none<br>
	*/
	public Transaction(String placeOfSale, double amountOfSale, int typeOfTransaction, String date)
	{
		//Set the place of sale
		this.placeOfSale = placeOfSale;
		this.orgPOS = placeOfSale;
		
		//Change the size of the place of sale to match the output
		if(this.placeOfSale.length() <= 25)
		{
			for(int i = this.placeOfSale.length(); this.placeOfSale.length() <= 25; i++)
			{
				this.placeOfSale += " ";
			}
		}
		
		else if(this.placeOfSale.length() > 25)
		{
			//Create a new string to hold the partial string
			String pos = "";
			for(int i = 0; i < 22; i++)
			{
				pos += this.placeOfSale.charAt(i);
			}
			
			//Append ellipis to the name
			pos += "...";
			
			//Put the name into the instance variable
			this.placeOfSale = "";
			this.placeOfSale = pos;
		}
		
		//Set the amount of the sale
		this.amountOfSale = amountOfSale;
		
		//Set the type of transaction
		this.typeOfTransaction = typeOfTransaction;
		this.transactionType = "";
		
		//Check to see what type of transaction occured
		if((this.typeOfTransaction - 1) == 0)
		{
			transactionType = "Check";
		}
		
		else if((this.typeOfTransaction - 1) == 1)
		{
			transactionType = "Debit";
		}
		
		else if((this.typeOfTransaction - 1) == 2)
		{
			transactionType = "Automatic Withdraw";
		}
		
		else
		{
			transactionType = "Deposit";
		}
		
		//Set the date of the transaction
		this.date = date;
	}
	
	/**
	* Method to get the place of sale for the transaction<br>
	* Preconditions: none<br>
	* Postconditions: The place of sale is returned to the caller.<br>
	* Throws: none<br>
	*/
	public String getPos()
	{
		//Return the place of sale to the caller
		return placeOfSale;
	}
	
	/**
	* Method to get the place of sale for the transaction<br>
	* Preconditions: none<br>
	* Postconditions: The place of sale is returned to the caller.<br>
	* Throws: none<br>
	*/
	public String getOrgPos()
	{
		//Return the place of sale to the caller
		return orgPOS;
	}
	
	/**
	* Method to get the amount of purchase<br>
	* Preconditions: none<br>
	* Postconditions: The amount of purchase is returned to the caller.<br>
	* Throws: none<br>
	*/
	public double getAmountOfSale()
	{
		//Return the amount of the sale to the caller
		return amountOfSale;
	}
	
	/**
	* Method to get the type of transaction<br>
	* Preconditions: none<br>
	* Postconditions: The type of transaction is returned to the caller.<br>
	* Throws: none<br>
	*/
	public String getTypeOfTrans()
	{
		//Return the type of transaction to the caller
		return transactionType;
	}
	
	/**
	* Method to get the date of the transaction.<br>
	* Preconditions: none<br>
	* Postconditions: The date of the transaction is returned.<br>
	* Throws: none<br>
	*/
	public String getDateOfSale()
	{
		//Return the date of the sale to the caller
		return date;
	}
	
	/**
	* Method to print out the transaction information.<br>
	* Preconditions: none<br>
	* Postconditions: The transaction information is returned to the caller.<br>
	* Throws: none<br>
	*/
	public String toString()
	{
		String transString = getPos() + "                " + getTypeOfTrans() +
										"                                 " +
								Currency.formatCurrency(getAmountOfSale()) + 
								"                     " + getDateOfSale() + "\r\n";
		
		//Return information to the caller
		return transString;
	}
}