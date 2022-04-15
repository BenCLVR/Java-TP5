package ynov.java.bank.controller;

import java.io.EOFException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import ynov.java.bank.modele.BankAccountType;

public class BankController {


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
	
	


	
	
	
}
