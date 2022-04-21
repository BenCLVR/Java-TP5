package ynov.java.bank.modele;

public class BankAccount {	
	int id;
	BankAccountType type;
	double amount;
	String name;
	
	public BankAccount(int id, String name, BankAccountType type, double amount) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.amount = amount;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
