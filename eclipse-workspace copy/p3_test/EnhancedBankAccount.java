class EnhancedBankAccount extends BankAccount
{
	private final java.util.ArrayList<Transaction> successfulTransactions;
	private final java.util.ArrayList<Transaction> failedTransactions;
	
	EnhancedBankAccount(String ownerLastName, String ownerFirstName, double checkingBalance, double savingsBalance)
	{
		super(ownerLastName, ownerFirstName, checkingBalance, savingsBalance);
		
		successfulTransactions = new java.util.ArrayList<>();
		failedTransactions = new java.util.ArrayList<>();
		
		java.util.Date date = new java.util.Date();
		successfulTransactions.add(new Transaction(TransactionType.NEW_ACCOUNT, date, 0.0));
		if( checkingBalance > 0.0 )
		{
			successfulTransactions.add(new Transaction(TransactionType.DEPOSIT_TO_CHECKING, date, checkingBalance));
		}
		
		if( savingsBalance > 0.0 )
		{
			successfulTransactions.add(new Transaction(TransactionType.DEPOSIT_TO_SAVINGS, date, savingsBalance));
		}
	}
	
	EnhancedBankAccount(String ownerLastName, String ownerFirstName)
	{
		this(ownerLastName, ownerFirstName, 0.0, 0.0);
	}
	
	public boolean withdrawFromChecking(double amount)
	{
		boolean i = false;
		java.util.Date date = new java.util.Date(); 
		if(super.withdrawFromChecking(amount)) {
			successfulTransactions.add(new Transaction(TransactionType.WITHDRAW_FROM_CHECKING, date, amount));
			i = true;
		}else
		{
			failedTransactions.add(new Transaction(TransactionType.WITHDRAW_FROM_CHECKING, date, amount));
		}
		return i;
	}
	
	public boolean withdrawFromSavings(double amount)
	{
		boolean returnValue = false;
		java.util.Date date = new java.util.Date(); 
		if( super.withdrawFromSavings(amount) )
		{
			successfulTransactions.add(new Transaction(TransactionType.WITHDRAW_FROM_SAVINGS, date, amount));
			returnValue = true;
		}
		else
		{
			failedTransactions.add(new Transaction(TransactionType.WITHDRAW_FROM_SAVINGS, date, amount));
		}
		return returnValue;
	}
	
	public boolean transferFromSavingsToChecking(double amount)
	{
		boolean returnValue = false;
		java.util.Date date = new java.util.Date(); 
		if( super.transferFromSavingsToChecking(amount) )
		{
			successfulTransactions.add(new Transaction(TransactionType.TRANSFER_TO_CHECKING, date, amount));
			returnValue = true;
		}
		else
		{
			failedTransactions.add(new Transaction(TransactionType.TRANSFER_TO_CHECKING, date, amount));
		}
		return returnValue;
	}
	
	public boolean transferFromCheckingToSavings(double amount)
	{
		boolean returnValue = false;
		java.util.Date date = new java.util.Date(); 
		if( super.transferFromCheckingToSavings(amount) )
		{
			successfulTransactions.add(new Transaction(TransactionType.TRANSFER_TO_SAVINGS, date, amount));
			returnValue = true;
		}
		else
		{
			failedTransactions.add(new Transaction(TransactionType.TRANSFER_TO_SAVINGS, date, amount));
		}
		return returnValue;
	}
	
	public void depositToSavings(double amount)
	{
		java.util.Date date = new java.util.Date();
		super.depositToSavings(amount);
		successfulTransactions.add(new Transaction(TransactionType.DEPOSIT_TO_SAVINGS, date, amount));
	}
	
	public void depositToChecking(double amount)
	{
		java.util.Date date = new java.util.Date();
		super.depositToChecking(amount);
		successfulTransactions.add(new Transaction(TransactionType.DEPOSIT_TO_CHECKING, date, amount));
	}
	
	public java.util.ArrayList<Transaction> getAllSuccessfulTransactions()
	{
		return successfulTransactions;
	}
	
	public java.util.ArrayList<Transaction> getAllFailedTransactions()
	{
		return failedTransactions;
	}
	
	public java.util.ArrayList<Transaction> getSavingsDepositTransactions()
	{
		java.util.ArrayList<Transaction> results = new java.util.ArrayList<>();
		for( Transaction t : successfulTransactions )
		{
			if( t.type() == TransactionType.DEPOSIT_TO_SAVINGS )
			{
				results.add(t);
			}
		}
		return results;
	}
	
	public java.util.ArrayList<Transaction> getCheckingDepositTransactions()
	{
		java.util.ArrayList<Transaction> results = new java.util.ArrayList<>();
		for( Transaction t : successfulTransactions )
		{
			if( t.type() == TransactionType.DEPOSIT_TO_CHECKING )
			{
				results.add(t);
			}
		}
		return results;
	}
	
	public java.util.ArrayList<Transaction> getSavingsWithdrawalTransactions()
	{
		java.util.ArrayList<Transaction> results = new java.util.ArrayList<>();
		for( Transaction t : successfulTransactions )
		{
			if( t.type() == TransactionType.WITHDRAW_FROM_SAVINGS )
			{
				results.add(t);
			}
		}
		return results;
	}
	
	public java.util.ArrayList<Transaction> getCheckingWithdrawalTransactions()
	{
		java.util.ArrayList<Transaction> results = new java.util.ArrayList<>();
		for( Transaction t : successfulTransactions )
		{
			if( t.type() == TransactionType.WITHDRAW_FROM_CHECKING)
			{
				results.add(t);
			}
		}
		return results;
	}
	
	public java.util.ArrayList<Transaction> getSavingsToCheckingTransferTransactions()
	{
		java.util.ArrayList<Transaction> results = new java.util.ArrayList<>();
		for( Transaction t : successfulTransactions )
		{
			if( t.type() == TransactionType.TRANSFER_TO_CHECKING )
			{
				results.add(t);
			}
		}
		return results;
	}
	
	public java.util.ArrayList<Transaction> getCheckingToSavingsTransferTransactions()
	{
		java.util.ArrayList<Transaction> results = new java.util.ArrayList<>();
		for( Transaction t : successfulTransactions )
		{
			if( t.type() == TransactionType.TRANSFER_TO_SAVINGS )
			{
				results.add(t);
			}
		}
		return results;
	}
	
	public java.util.ArrayList<Transaction> getFailedTransactions(java.util.Date startDate, java.util.Date endDate)
	{
		java.util.ArrayList<Transaction> results = new java.util.ArrayList<>();
		for( Transaction t : failedTransactions )
		{
			if(t.date().after(startDate) && t.date().before(endDate))
			{
				results.add(t);
			}
		}
		return results;
	}
	
	public java.util.ArrayList<Transaction> getSuccessfulTransactions(java.util.Date startDate, java.util.Date endDate)
	{
		java.util.ArrayList<Transaction> results = new java.util.ArrayList<>();
		for( Transaction t : successfulTransactions )
		{
			if(t.date().after(startDate) && t.date().before(endDate))
			{
				results.add(t);
			}
		}
		return results;
	}
}
