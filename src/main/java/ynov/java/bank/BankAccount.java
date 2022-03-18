package ynov.java.bank;

public class BankAccount {	
	int id;
	User[] users;
	BankAccountType type;
	double amount;
	
	BankAccount() {
		
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

	public BankAccountType getType() {
		return type;
	}

	public void setType(BankAccountType type) {
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public void add(double amount) {
		this.amount += amount;
	}
	
	public void substract(double amount) {
		this.amount -= amount;
	}
	
}
