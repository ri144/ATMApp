
public class Account {
//account number, user name, balance,PIN
	private int account_num, PIN;
	private String user_name;
	private float balance;
	
	public Account(int num, int P, String user, float bal){
		account_num = num;
		PIN = P;
		user_name = user;
		balance = bal;
	}
	
	public float getBalance(){
		return balance;
	}
	
	public void update_balance(float amount){
		balance += amount;
	}
	
	public int getAccountNum(){
		return account_num;
	}

	public int getPIN() {
		// TODO Auto-generated method stub
		return PIN;
	}

	public String getUserName() {
		// TODO Auto-generated method stub
		return user_name;
	}
}
