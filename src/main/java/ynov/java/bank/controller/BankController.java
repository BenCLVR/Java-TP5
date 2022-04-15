package ynov.java.bank.controller;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ynov.java.bank.modele.BankAccount;
import ynov.java.bank.modele.BankAccountType;
import ynov.java.bank.modele.User;

public class BankController {

	private String dir = System.getProperty("user.dir");
	private String bankAccountsJsonFilePath = "\\ressources\\bankAccounts.json";
	private String usersJsonFilePath = "\\ressources\\users.json";
	private ObjectMapper objectMapper = new ObjectMapper();
	private ArrayList<BankAccount> bankAccountPool = new ArrayList<BankAccount>();
	private ArrayList<User> userPool = new ArrayList<User>();
	List<String> accountList = new ArrayList<String>();
	
	Connexion conn = new Connexion();
	

	
	public BankController() {
		// TODO Auto-generated constructor stub
	}
	
	public int createAccount(int id_user, String name, Double amount, BankAccountType type ) throws EOFException, SQLException {
		Connection sql = conn.getConnexion();
		Statement state = sql.createStatement();
		String id_account = new String();
		int result = state.executeUpdate("INSERT INTO accounts (name, amount, accounttype) VALUES ('"+name+"','"+amount+"','"+type+"')");
		ResultSet resultid = state.executeQuery("SELECT id FROM accounts WHERE name = '"+name+"'");

		
		
		while (resultid.next()) {
			id_account = resultid.getString("id");
		}
		int result1 = state.executeUpdate("INSERT INTO linkaccount (id_user, id_account) VALUES ('"+id_user+"','"+id_account+ "')");
		sql.close();
		System.out.println("Account added");
		return result;
	}
	
	
	
	// A REVOIR 
	public boolean GetAccount(String name) throws EOFException, SQLException {
		String Rname = new String();
		String Ramount = new String();
		String Rtype = new String();
		Connection sql = conn.getConnexion();
		Statement state = sql.createStatement();

		
		// COMMENT GET ACCOUNT
		//Passer par l'id user sur la table de jointure et récup tout les id accounts de cette table qui on l'user log et les retourner
		ResultSet result = state.executeQuery("SELECT * FROM accounts WHERE nom = '"+name+"'");

		while (result.next()) {
			Rname = result.getString("name");
			Ramount = result.getString("amount");
			Rtype= result.getString("accounttype");
		}
		// 
		if(Rname != null && name.equals(Rname)){
			System.out.println("Account retriev");
			return true;
		}
		System.out.println("Account not found");
		return false;
	}
	
	
	
	//JSON

	private ArrayList<BankAccount> loadBankAccounts() {
		ArrayList<BankAccount> bankAccounts = null;

		try {
			bankAccounts = objectMapper.readValue(new File(this.dir + this.bankAccountsJsonFilePath),
					new TypeReference<ArrayList<BankAccount>>() {
					});
		} catch (JsonParseException e) {
			e.printStackTrace();
			bankAccounts = new ArrayList<BankAccount>();
		} catch (JsonMappingException e) {
			e.printStackTrace();
			bankAccounts = new ArrayList<BankAccount>();
		} catch (IOException e) {
			e.printStackTrace();
			bankAccounts = new ArrayList<BankAccount>();
		}

		return (bankAccounts);
	}
	
	private int saveUsers() {
		try {
			objectMapper.writeValue(new File(this.dir + usersJsonFilePath), this.userPool);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			return (1);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return (2);
		} catch (IOException e) {
			e.printStackTrace();
			return (3);
		}
		return (0);
	}
	
	private int saveBankAccounts() {
		try {
			objectMapper.writeValue(new File(this.dir + this.bankAccountsJsonFilePath), this.bankAccountPool);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			return (1);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return (2);
		} catch (IOException e) {
			e.printStackTrace();
			return (3);
		}
		return (0);
	}

	private ArrayList<User> loadUsers() {
		ArrayList<User> users = null;

		try {
			users = objectMapper.readValue(new File(this.dir + this.usersJsonFilePath),
					new TypeReference<ArrayList<User>>() {
					});
		} catch (JsonParseException e) {
			e.printStackTrace();
			users = new ArrayList<User>();
		} catch (JsonMappingException e) {
			e.printStackTrace();
			users = new ArrayList<User>();
		} catch (IOException e) {
			e.printStackTrace();
			users = new ArrayList<User>();
		}

		return (users);
	}
	

	
	
	
}
