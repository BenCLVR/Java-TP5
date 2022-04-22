package ynov.java.bank.modele;

import java.util.List;

public class User {
	public int id;
	public List<BankAccount> bankAccounts;
	public String lastname;
	public String firstname;
	public String password;
	public List<BankAccount> beneficiaries;
	
	public User(int id, String lastname, String firstname, String password) {
		this.id = id;
		this.lastname = lastname;
		this.firstname = firstname;
		this.password = password;
	}

	public User(int id, String lastname, String firstname) {
		this.lastname = lastname;
		this.firstname = firstname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<BankAccount> getBankAccounts() {
		return bankAccounts;
	}

	public void setBankAccounts(List<BankAccount> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void addBankAccount() {
		
	}
}
