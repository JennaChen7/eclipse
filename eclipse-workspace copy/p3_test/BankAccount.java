class BankAccount
{
	private final String ownerLastName;
	private final String ownerFirstName;
	private final String accountNumber;
	private double checkingBalance;
	private double savingsBalance;
	private static int bankAccountCount = 0;
	
	BankAccount(String ownerLastName, String ownerFirstName, double checkingBalance, double savingsBalance)
	{
		this.ownerLastName = ownerLastName;
		this.ownerFirstName = ownerFirstName;
		this.accountNumber = makeAccountNumber();
		this.checkingBalance = checkingBalance;
		this.savingsBalance = savingsBalance;
		
		bankAccountCount = bankAccountCount+1;
	}
	
	BankAccount(String ownerLastName, String ownerFirstName)
	{
		this(ownerLastName, ownerFirstName, 0.0, 0.0);
	}
	
	public boolean withdrawFromChecking(double amount)
	{
		boolean i = false;
		if(checkingBalance >= amount) {
			i = true;
			checkingBalance = checkingBalance - amount;
		}
		return i;
	}
	
	public boolean withdrawFromSavings(double amount)
	{
		boolean i = false;
		if(savingsBalance >= amount) {
			i = true;
			savingsBalance = savingsBalance - amount;
		}
		return i;
	}
	
	public boolean transferFromSavingsToChecking(double amount)
	{
		boolean i = false;
		if(savingsBalance >= amount) {
			i = true;
			savingsBalance = savingsBalance - amount;
			checkingBalance = checkingBalance + amount;
		}
		return i;
	}
	
	public boolean transferFromCheckingToSavings(double amount)
	{
		boolean i = false;
		if(checkingBalance >= amount) {
			i = true;
			checkingBalance = checkingBalance - amount;
			savingsBalance = savingsBalance + amount;
		}
		return i;
	}
	
	public void depositToSavings(double amount)
	{
		savingsBalance += amount;
	}
	
	public void depositToChecking(double amount)
	{
		checkingBalance += amount;
	}
	
	public String toString()
	{
		String s = ownerLastName + "," + ownerFirstName + "," + accountNumber + "\n" + checkingBalance + "\n" + savingsBalance;
		return s;
	}
	
	public String getOwnerLastName()
	{
		return ownerLastName;
	}
	
	public String getOwnerFirstName()
	{
		return ownerFirstName;
	}
	
	public String getAccountNumber()
	{
		return accountNumber;
	}
	
	public double getCheckingBalance()
	{
		return checkingBalance;
	}
	
	public double getSavingsBalance()
	{
		return savingsBalance;
	}
	
	private String makeAccountNumber()
	{
		String aNumber = "" + bankAccountCount;
		
		while( aNumber.length() < 6 )
		{
			aNumber = "0" + aNumber;
		}
		return aNumber;
	}
	
	public static int getBankAccountCount()
	{
		return bankAccountCount;
	}
}
