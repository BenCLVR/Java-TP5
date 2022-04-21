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

		ResultSet result = linkAccountRequest
				.executeQuery("SELECT id_account FROM linkaccount WHERE id_user = '" + userId + "'");
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

		ResultSet result = bankAccountRequest
				.executeQuery("SELECT name, amount, types, id FROM accounts WHERE id = '" + BAId + "'");

		String name = "";
		double amount = 0;
		int id = 0;
		while (result.next()) {
			name = result.getString("name");
			amount = result.getDouble("amount");
			id = result.getInt("id");
		}
		bankAccountRequest.close();

		return new BankAccount(id, name, BankAccountType.CURRENT, amount);

	}

	public boolean createAccount(String BAName, BankAccountType BAType, int userId)
			throws EOFException, SQLException {
		
		boolean canCreateAccount = this.canCreateAccountByUserId(userId, BAType);
		if (!canCreateAccount) {
			return false;
		}

		this.addBankAccount(BAName, BAType);
		int BAId = this.getBankAccountIdByName(BAName);

		Connexion conn = new Connexion();
		Connection sql = conn.getConnexion();
		Statement state = sql.createStatement();

		state.executeQuery("INSERT INTO linkaccount (id_user, id_account) VALUES ('"+userId+"', '"+BAId+"')");
		sql.close();
		return true;

	}

	private void addBankAccount(String accountName, BankAccountType BAType) throws EOFException, SQLException {
		Connexion conn = new Connexion();
		Connection sql = conn.getConnexion();
		Statement state = sql.createStatement();
		state.executeQuery(
				"INSERT INTO accounts (name, amount, types) VALUES ('" + accountName + "', 0,'" + BAType + "')");
		sql.close();
	}

	private int getBankAccountIdByName(String BAName) throws SQLException, EOFException {
		Connexion conn = new Connexion();
		Connection sql = conn.getConnexion();
		Statement state = sql.createStatement();
		ResultSet result = state.executeQuery("SELECT id from accounts WHERE name = '" + BAName + "'");
		int id = 0;
		while (result.next()) {
			id = result.getInt("id");
		}
		sql.close();
		return id;
	}

	private boolean canCreateAccountByUserId(int userId, BankAccountType type) throws EOFException, SQLException {
		Connexion conn = new Connexion();
		Connection sql = conn.getConnexion();
		Statement state = sql.createStatement();

		ResultSet result = state.executeQuery(
				"SELECT count(a.id) AS count FROM accounts a JOIN linkaccount la ON a.id = la.id_account JOIN users u ON u.id = la.id_user WHERE u.id = '"
						+ userId + "' AND a.types = '" + type.toString() + "'");
		System.out.println(result);

		int count = 0;
		while (result.next()) {
			count = result.getInt("count");
		}
		sql.close();
		if (count > 0) {
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
		// Passer par l'id user sur la table de jointure et récup tout les id accounts
		// de cette table qui on l'user log et les retourner
		ResultSet result = state.executeQuery("SELECT * FROM accounts WHERE nom = '" + name + "'");

		while (result.next()) {
			Rname = result.getString("name");
			Ramount = result.getString("amount");
			Rtype = result.getString("accounttype");
		}
		//
		if (Rname != null && name.equals(Rname)) {
			System.out.println("Account retriev");
			return true;
		}
		System.out.println("Account not found");
		return false;
	}
}
