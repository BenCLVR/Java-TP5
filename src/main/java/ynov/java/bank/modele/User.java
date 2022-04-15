package ynov.java.bank.modele;

import java.util.List;

public class User {
	public int id;
	public List<BankAccount> bankAccounts;
	public String nom;
	public String pseudo;
	public String password;
	
	User() {}
	public User(int id, String nom, String pseudo, String password) {
		this.id = id;
		this.nom = nom;
		this.pseudo = pseudo;
		this.password = password;
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

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
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
