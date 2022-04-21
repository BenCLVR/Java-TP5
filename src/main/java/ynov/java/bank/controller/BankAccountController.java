package ynov.java.bank.controller;

import java.io.EOFException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ynov.java.bank.modele.BankAccount;
import ynov.java.bank.modele.BankAccountType;


public class BankAccountController {
    
    public static List<Integer> getBankAccountIdsByUser(int userId) throws EOFException, SQLException {
		Connexion conn = new Connexion();
		Connection sql = conn.getConnexion();
		Statement linkAccountRequest = sql.createStatement();

		ResultSet result = linkAccountRequest.executeQuery("SELECT id_account FROM linkaccount WHERE id_user = '"+userId+"'");
		List<Integer> accountIds = new ArrayList<Integer>();
		while (result.next()) {
			accountIds.add(result.getInt("id_account"));
		}
		linkAccountRequest.close();

		return accountIds;
		// return new BankAccount(1, BankAccountType.CURRENT, 123.0);
    }

	public static BankAccount getBankAccountById(Integer BAId) throws EOFException, SQLException {
		Connexion conn = new Connexion();
		Connection sql = conn.getConnexion();
		Statement bankAccountRequest = sql.createStatement();

		ResultSet result = bankAccountRequest.executeQuery("SELECT name, amount, types, id FROM accounts WHERE id = '"+BAId+"'");

		String name = "";
		double amount = 0;
		int id = 0;
		while (result.next()) {
			name = result.getString("name");
			amount = result.getDouble("amount");
			id = result.getInt("id");
		}
		bankAccountRequest.close();

		return new BankAccount(id, name,BankAccountType.CURRENT, amount);

	}
	
	public int createAccount(String name, String amount, BankAccountType object ) throws EOFException, SQLException {
		Connexion conn = new Connexion();
		Connection sql = conn.getConnexion();
		Statement state = sql.createStatement();
		String id_account = new String();
		int result = state.executeUpdate("INSERT INTO accounts (name, amount, types) VALUES ('"+name+"','"+amount+"','"+object+"')");
		ResultSet resultid = state.executeQuery("SELECT id FROM accounts WHERE name = '"+name+"'");

		
		
		while (resultid.next()) {
			id_account = resultid.getString("id");
		}
		
		//int result1 = state.executeUpdate("INSERT INTO linkaccount (id_user, id_account) VALUES ('"+id_user+"','"+id_account+ "')");
		sql.close();
		System.out.println("Account added");
		return result;
	}

	private boolean canCreateAccountByUserId(int userId, BankAccountType type) throws EOFException, SQLException {
		Connexion conn = new Connexion();
		Connection sql = conn.getConnexion();
		Statement state = sql.createStatement();

		ResultSet result = state.executeQuery("SELECT count(a.id) AS count FROM accounts a JOIN linkaccount la ON a.id = la.id_account JOIN users u ON u.id = la.id_user WHERE u.id = '"+ userId +"' AND a.types = '"+ type.toString() +"'");
		System.out.println(result);

		int count = 0;
		while(result.next()) {
			count = result.getInt("count");
		}

		if(count > 0) {
			return false;
		}

		return true;
	}
	

	
	
	// A REVOIR 
	public boolean GetAccount(String name) throws EOFException, SQLException {
		Connexion conn = new Connexion();
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
}
