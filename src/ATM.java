import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class ATM implements iATM{
	private ArrayList<Account> accountList = new ArrayList<Account>(); 
	private Account currentAccount = null;
	private boolean accountFlag = false;
	private boolean exit = false;
	private ArrayList<String> types = new ArrayList<String>();
	private ArrayList<Float> amounts = new ArrayList<Float>();
	private int index = 0;
	//make a deposit, make a withdraw or check the balance.The ATM class must also display the results of any transaction or inquiry.
	public ATM(){
		initAccounts();
		Scanner s = new Scanner(System.in);
		askAccount(s);
	}
	
	private void askAccount(Scanner s) {
		// TODO Auto-generated method stub
		System.out.println("What is your account number?");
		int testAccount = s.nextInt();		
		for(Account a: accountList){
			if (a.getAccountNum() == testAccount){
				accountFlag = true;
				currentAccount = a;
				break;				
			}
		}
		if (accountFlag){
			checkPIN(s, 0);
		}else{
			System.out.println("The account number you gave is invalid, please try again.");
			askAccount(s);
		}
	}

	private void checkPIN(Scanner s, int count) {
		// TODO Auto-generated method stub
		System.out.println("What is your PIN?");
		int testP = s.nextInt();
		if (currentAccount.getPIN() == testP){
			takeAction(s, true, false);
		}
		else{
			if((2-count) > 0){
				System.out.printf("The PIN you gave is invalid, please try again. %d tries left\n", (2-count));
				checkPIN(s, count+1);
			}
			else{
				System.out.println("You have incorrectly guessed too many times and are now locked out.  Please come again"
						+ " later when you know your PIN.");
				currentAccount = null;
			}
		}
	}

	private void takeAction(Scanner s, boolean check, boolean duocheck){
		System.out.printf("Welcome %s!  What would you like to do? ('Deposit','Withdraw','CheckBalance', 'Exit')\n", currentAccount.getUserName());
		if(duocheck){
			s.nextLine();
			s.nextLine();
		}
		else if(check){
			s.nextLine();
		}
		String action = s.nextLine();
		if(action.equals("Deposit")){
			System.out.println("How much would you like to deposit?");
			float amount = s.nextFloat();
			types.add(index, "Deposit");
			amounts.add(index, amount);
			index += 1;
			this.deposit(amount);
			check = true;
			duocheck = false;
		}
		else if(action.equals("Withdraw")){
			System.out.println("How much would you like to withdraw?");
			float amount = s.nextFloat();
			withdraw(amount);
			check = true;
			duocheck = false;
		}
		else if(action.equals("CheckBalance")){
			checkBalance();
			check = false;
			duocheck = false;
		}
		else if(action.equals("Exit")){
			exit();
		}
		else{
			System.out.println("Your command was not recognized, please try again.");
			check = false;
			duocheck =false;
		}
		if(!exit){
			takeAction(s, check, duocheck);
		}
	}
	
	private void exit() {
		// TODO Auto-generated method stub
		exit = true;
		TransactionSummary t = new TransactionSummary();
		System.out.println(t.printReceipt(currentAccount.getUserName(), currentAccount.getAccountNum(), types, amounts, currentAccount.getBalance(), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.YEAR)));
		currentAccount = null;
	}

	private void initAccounts(){
		accountList.add(new Account(1000000, 9999, "Bob", 10.00f));
		accountList.add(new Account(1000001, 8888, "Jimmy", 20.00f));
		accountList.add(new Account(1000002, 1234, "Carl", 50.50f));
		accountList.add(new Account(1000003, 2341, "Joe", 234.23f));
		accountList.add(new Account(1000004, 4782, "Adam", 32498.28f));
	}
	
	private void deposit(float amount){
		currentAccount.update_balance(amount);
		System.out.printf("You deposited $%2.2f and now have $%2.2f in your account's balance\n",amount, currentAccount.getBalance());
	}
	private void withdraw(float amount){
		if(currentAccount.getBalance() > amount){
			currentAccount.update_balance(-amount);
			System.out.printf("You withdrew $%2.2f and currently have $%2.2f in your account's balance\n",amount, currentAccount.getBalance());
			types.add(index, "Withdrawal");
			amounts.add(index, amount);
			index += 1;
		}
		else{
			System.out.println("You do not have enough money in your account to make that large of a withdrawal.");
		}
	}
	
	private void checkBalance(){
		System.out.printf("You currently have $%2.2f\n",currentAccount.getBalance());
	}
}
