package ynov.java.bank.controller;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ynov.java.bank.modele.BankAccount;
import ynov.java.bank.modele.User;

public class BankController {

	private String dir = System.getProperty("user.dir");
	private String bankAccountsJsonFilePath = "\\ressources\\bankAccounts.json";
	private String usersJsonFilePath = "\\ressources\\users.json";
	private ObjectMapper objectMapper = new ObjectMapper();
	private ArrayList<BankAccount> bankAccountPool = new ArrayList<BankAccount>();
	private ArrayList<User> userPool = new ArrayList<User>();
	
	Connexion conn = new Connexion();
	

	
	public BankController() {
		// TODO Auto-generated constructor stub
	}

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
	
	public  int createUser(String name, String surname, String pwd ) throws EOFException, SQLException {
		Connection sql = conn.getConnexion();
		Statement state = sql.createStatement();
		int result = state.executeUpdate("INSERT INTO users (nom, prenom, mdp) VALUES ('"+name+"','"+surname+"','"+pwd+"')");
		return result;
	}
	
	
	
}
