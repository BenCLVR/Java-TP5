package ynov.java.bank;

public class BankTrades {

	int id;
	User[] users;
	BankAccount[] account;
	
	public BankTrades(int id, User[] users, BankAccount[] account, BankTradesType type, double amount) {
		super();
		this.id = id;
		this.users = users;
		this.account = account;
		this.type = type;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User[] getUsers() {
		return users;
	}

	public void setUsers(User[] users) {
		this.users = users;
	}

	public BankAccount[] getAccount() {
		return account;
	}

	public void setAccount(BankAccount[] account) {
		this.account = account;
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

	BankTradesType type;
	double amount;
	
	public BankTrades() {
		// TODO Auto-generated constructor stub
	}

}
