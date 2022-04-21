package ynov.java.bank.modele;

public class BankTrades {

	int id;
	int user_id;
	int account_id;
	User user;
	BankAccount bankAccount;
	BankTradesType type;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	public BankTradesType getType() {
		return type;
	}

	public void setType(BankTradesType type) {
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	double amount;

	public BankTrades(int id, User user, BankAccount bankAccount, BankTradesType type, double amount) {
		super();
		this.id = id;
		this.user = user;
		this.bankAccount = bankAccount;
		this.type = type;
		this.amount = amount;
	}

	public BankTrades(int id, int user_id, int account_id, BankTradesType type, double amount) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.account_id = account_id;
		this.type = type;
		this.amount = amount;
	}

	public BankTrades(int userId, BankTradesType type, double amount) {
		this.user_id = userId;
		this.type = type;
		this.amount = amount;
	}

	public static BankTradesType getBankTradesTypeFromString(String str) {
		if (str.equals("ADD")) {
			return BankTradesType.ADD;
		} else {
			return BankTradesType.REMOVE;
		}
	}

}
