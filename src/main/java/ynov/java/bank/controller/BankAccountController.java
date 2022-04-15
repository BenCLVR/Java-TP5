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

		return new BankAccount(id, BankAccountType.CURRENT, amount);

	}
}
