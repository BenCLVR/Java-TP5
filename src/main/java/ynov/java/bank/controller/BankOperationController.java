package ynov.java.bank.controller;

import java.io.EOFException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ynov.java.bank.modele.BankTrades;
import ynov.java.bank.modele.BankTradesType;

public class BankOperationController {

	public BankOperationController() {
		// TODO Auto-generated constructor stub
	}

	public void createOperation(int id_user, int id_account, double amount, BankTradesType object)
			throws EOFException, SQLException {
		Connexion conn = new Connexion();
		Connection sql = conn.getConnexion();
		Statement state = sql.createStatement();
		state.executeUpdate("INSERT INTO operation (id_user, amount, types, id_account) VALUES ('" + id_user + "','"
				+ amount + "','" + object + "','" + id_account + "')");
		// ResultSet resultid = state.executeQuery("SELECT id FROM operation WHERE
		// id_user = '"+id_user+"' AND id_account = '"+id_account+"'");
		sql.close();
	}

	public static List<BankTrades> getOperationsByAccountId(int accountId) throws EOFException, SQLException {
		Connexion conn = new Connexion();
		Connection sql = conn.getConnexion();
		Statement state = sql.createStatement();
		ResultSet result = state.executeQuery(
				"SELECT o.types type, o.amount amount, o.id_user userId FROM operation o JOIN accounts a ON a.id = o.id_account WHERE '"
						+ accountId + "'");

		List<BankTrades> BT = new ArrayList<BankTrades>();

		while (result.next()) {
			BankTradesType type = BankTrades.getBankTradesTypeFromString(result.getString("type"));
			double amount = result.getDouble("amount");
			int userId = result.getInt("userId");
			BT.add(new BankTrades(userId, type, amount));
		}
		sql.close();
		return BT;
	}

	// update l'amount de l'account quand opération

	// && refresh sur le currentUser
}
