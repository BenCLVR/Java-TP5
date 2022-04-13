package ynov.java.bank.modele;

public class BankAccount {	
	int id;
	User[] users;
	BankAccountType type;
	double amount;
	
	public BankAccount(int id, User[] users, BankAccountType type, double amount) {
		super();
		this.id = id;
		this.users = users;
		this.type = type;
		this.amount = amount;
	}

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
