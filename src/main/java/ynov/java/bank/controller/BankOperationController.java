package ynov.java.bank.controller;

import java.io.EOFException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BankOperationController {

	
	public BankOperationController() {
		// TODO Auto-generated constructor stub
	}

	public int createOperation(String name, String amount, Object object ) throws EOFException, SQLException {
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
}
