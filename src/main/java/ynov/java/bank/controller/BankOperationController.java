package ynov.java.bank.controller;

import java.io.EOFException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ynov.java.bank.modele.BankTradesType;

public class BankOperationController {

	
	public BankOperationController() {
		// TODO Auto-generated constructor stub
	}

	public int createOperation(int id_user,int id_account , double amount, BankTradesType object ) throws EOFException, SQLException {
		Connexion conn = new Connexion();
		Connection sql = conn.getConnexion();
		Statement state = sql.createStatement();
		String id_op = new String();
		int result = state.executeUpdate("INSERT INTO operation (id_user, amount, types, id_account) VALUES ('"+id_user+"','"+amount+"','"+object+"','"+id_account+"')");
		ResultSet resultid = state.executeQuery("SELECT id FROM operation WHERE id_user = '"+id_user+"' AND id_account = '"+id_account+"'");

		
		
		while (resultid.next()) {
			id_op = resultid.getString("id");
		}
		
		sql.close();
		System.out.println("Operation Submitted");
		return result;
	}
}
