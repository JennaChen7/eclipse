class makeBankAccountsAndTransactions
{
	public static void main(String[] args)
	{
		if( args.length != 1 )
		{
			System.out.println("format: makeBankAccountsAndTransactions \"input file\"");
			System.exit(0);
		}
		
		try
		{
			java.io.BufferedReader input = new java.io.BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream(args[0])));
			String inn;
			java.util.ArrayList<String> inputLines = new java.util.ArrayList<>();
			while( (inn = input.readLine()) != null )
			{
				if( (inn.trim().length() > 0) && (!inn.trim().startsWith("#")) )
				{
					inputLines.add(inn);
				}
			}
			
			for( int i = 0; i < inputLines.size(); )
			{
				inn = inputLines.get(i);
				System.out.println(inn);
				
				// is the current line for a new BankAccount
				if( inn.startsWith("new BankAccount") )
				{
					// get the account information
					java.util.StringTokenizer st = new java.util.StringTokenizer(inn, "\t");
					st.nextToken();
					String lastName = st.nextToken();
					String firstName = st.nextToken();
					double checkingInitialValue = Double.parseDouble(st.nextToken());
					double savingsInitialValue = Double.parseDouble(st.nextToken());
					BankAccount ba = new BankAccount(lastName, firstName, checkingInitialValue, savingsInitialValue);
					System.out.println(ba.toString());
					i = i+1;
					while( true )
					{
						if( i >= inputLines.size() )
						{
							System.out.println(ba.toString());
							break;
						}
						
						inn = inputLines.get(i);
						
						// the current line is for a new account, break out of the loop
						if( inn.startsWith("new") )
						{
							System.out.println(ba.toString());
							break;
						}
						
						// get the type and amount of the transaction
						st = new java.util.StringTokenizer(inn, "\t");
						String type = st.nextToken();
						double amount = Double.parseDouble(st.nextToken());
						
						
						// if the transaction type is withdrawFromChecking 
						if( type.equals("withdrawFromChecking") )
						{
							ba.withdrawFromChecking(amount);
							System.out.println("withdrawFromChecking " + amount);
						}
						
						// add code below to process the other five transaction types
						
						// if the transaction type is withdrawFromSavings
						if( type.equals("withdrawFromSavings") )
						{
							ba.withdrawFromSavings(amount);
							System.out.println("withdrawFromSavings " + amount);
						}
						
						// if the transaction type is depositToChecking
						if( type.equals("depositToChecking") )
						{
							ba.depositToChecking(amount);
							System.out.println("depositToChecking " + amount);
						}
						
						// if the transaction type is depositToSavings
						if( type.equals("depositToSavings") )
						{
							ba.depositToSavings(amount);
							System.out.println("depositToSavings " + amount);
						}
						
						// if the transaction type is transferToChecking
						if( type.equals("transferToChecking") )
						{
							ba.transferFromSavingsToChecking(amount);
							System.out.println("transferToChecking " + amount);
						}
						
						// if the transaction type is transferToSavings
						if( type.equals("transferToSavings") )
						{
							ba.transferFromCheckingToSavings(amount);
							System.out.println("transferToSavings " + amount);
						}
						
						i = i+1;
						
					}
				}
				
				// is the current line for a new EnhancedBankAccount
				if( inn.startsWith("new EnhancedBankAccount") )
				{
					//!testing_getdate
					java.util.Date[] dates = new java.util.Date[10];
					// get the account information
					java.util.StringTokenizer st = new java.util.StringTokenizer(inn, "\t");
					st.nextToken();
					String lastName = st.nextToken();
					String firstName = st.nextToken();
					double checkingInitialValue = Double.parseDouble(st.nextToken());
					double savingsInitialValue = Double.parseDouble(st.nextToken());
					EnhancedBankAccount eba = new EnhancedBankAccount(lastName, firstName, checkingInitialValue, savingsInitialValue);
					System.out.println(eba.toString());
					
					i = i+1;
					while( true )
					{
						if( i >= inputLines.size() )
						{
							System.out.println(eba.toString());
							break;
						}
						//!testing_add time between the transactions
						try {
							java.lang.Thread.sleep(100);
						}
						catch(Exception e) {
							
						}
						
						inn = inputLines.get(i);
						
						// the current line is for a new account, break out of the loop
						if( inn.startsWith("new") )
						{
							System.out.println(eba.toString());
							break;
						}
						
						// get the type and amount of the transaction
						st = new java.util.StringTokenizer(inn, "\t");
						String type = st.nextToken();
						double amount = Double.parseDouble(st.nextToken());
						
						//!testing_getdate
						if(type.equals("getDate")) {
							int index = (int) amount;
							dates[index] = new java.util.Date();
							System.out.println("date" + index + " " + dates[index]);
						}
						//!testing_get and output successful transactions between dates[index0] and dates[index1]
						if(type.equals("getSuccessfulTransactions")) {
							int index0 = (int) amount;
							int index1 = Integer.parseInt(st.nextToken());
							System.out.println("getSuccessfulTransactions" + dates[index0] + " " + dates[index1]);
							java.util.ArrayList<Transaction> transactions = eba.getSuccessfulTransactions(dates[index0], dates[index1]);
							if(transactions != null) {
								for(Transaction e : transactions) {
									System.out.println("\t" + e);
								}
							}
							else
							{
								System.out.println("\t" + "transactions is null");
							}
						}
						if(type.equals("getFailedTransactions")) {
							int index0 = (int) amount;
							int index1 = Integer.parseInt(st.nextToken());
							System.out.println("getFailedTransactions" + dates[index0] + " " + dates[index1]);
							java.util.ArrayList<Transaction> transactions = eba.getFailedTransactions(dates[index0], dates[index1]);
							
								for(Transaction e : transactions) {
									System.out.println("\t" + e);
								}
						}
						//!testing_get methods
						if(type.equals("getWithdrawFromChecking")) {
							System.out.println("getWithdrawFromChecking");
							java.util.ArrayList<Transaction> transactions = eba.getCheckingWithdrawalTransactions();
							for(Transaction e : transactions) {
								System.out.println("\t" + e);
							}
						}
						if(type.equals("getWithdrawFromSavings")) {
							System.out.println("getWithdrawFromSavings");
							java.util.ArrayList<Transaction> transactions = eba.getSavingsWithdrawalTransactions();
							for(Transaction e : transactions) {
								System.out.println("\t" + e);
							}
						}
						if(type.equals("getDepositToChecking")) {
							System.out.println("getDepositToChecking");
							java.util.ArrayList<Transaction> transactions = eba.getCheckingDepositTransactions();
							for(Transaction e : transactions) {
								System.out.println("\t" + e);
							}
						}
						
						if(type.equals("getDepositToSavings")) {
							System.out.println("getDepositToSavings");
							java.util.ArrayList<Transaction> transactions = eba.getSavingsDepositTransactions();
							for(Transaction e : transactions) {
								System.out.println("\t" + e);
							}
						}
						if(type.equals("getTransferToChecking")) {
							System.out.println("getTransferToChecking");
							java.util.ArrayList<Transaction> transactions = eba.getSavingsToCheckingTransferTransactions();
							for(Transaction e : transactions) {
								System.out.println("\t" + e);
							}
						}
						if(type.equals("getTransferToSavings")) {
							System.out.println("getTransferToSavings");
							java.util.ArrayList<Transaction> transactions = eba.getCheckingToSavingsTransferTransactions();
							for(Transaction e : transactions) {
								System.out.println("\t" + e);
							}
						}
						
						// if the transaction type is withdrawFromChecking
						if( type.equals("withdrawFromChecking") )
						{
							eba.withdrawFromChecking(amount);
							System.out.println("withdrawFromChecking " + amount);
						}
						
						// add code below to process the other five transaction types
						
						// if the transaction type is withdrawFromSavings
						if( type.equals("withdrawFromSavings") )
						{
							eba.withdrawFromSavings(amount);
							System.out.println("withdrawFromSavings " + amount);
						}
						
						// if the transaction type is depositToChecking
						if( type.equals("depositToChecking") )
						{
							eba.depositToChecking(amount);
							System.out.println("depositToChecking " + amount);
						}
						
						// if the transaction type is depositToSavings
						if( type.equals("depositToSavings") )
						{
							eba.depositToSavings(amount);
							System.out.println("depositToSavings " + amount);
						}
						
						// if the transaction type is transferToChecking
						if( type.equals("transferToChecking") )
						{
							eba.transferFromSavingsToChecking(amount);
							System.out.println("transferToChecking " + amount);
						}
						
						// if the transaction type is transferToSavings
						if( type.equals("transferToSavings") )
						{
							eba.transferFromCheckingToSavings(amount);
							System.out.println("transferToSavings " + amount);
						}
						
						i = i+1;
						
					}
				}
			}
		}
		catch(java.lang.IndexOutOfBoundsException e)
		{
			System.out.println(e.toString());
			System.exit(0);
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			System.exit(0);
		}
	}
}
