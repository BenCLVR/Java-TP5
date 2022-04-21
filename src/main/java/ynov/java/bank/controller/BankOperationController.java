package ynov.java.bank.controller;

import java.io.EOFException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ynov.java.bank.modele.BankAccount;
import ynov.java.bank.modele.BankTrades;
import ynov.java.bank.modele.BankTradesType;
import ynov.java.bank.modele.User;

public class BankOperationController {

	public BankOperationController() {
		// TODO Auto-generated constructor stub
	}

	public void createOperation(int id_user, int id_account, double amount, BankTradesType BTType)
			throws EOFException, SQLException {
		Connexion conn = new Connexion();
		Connection sql = conn.getConnexion();
		Statement state = sql.createStatement();
		state.executeUpdate("INSERT INTO operation (id_user, amount, types, id_account) VALUES ('" + id_user + "','"
				+ amount + "','" + BTType + "','" + id_account + "')");
		// ResultSet resultid = state.executeQuery("SELECT id FROM operation WHERE
		// id_user = '"+id_user+"' AND id_account = '"+id_account+"'");
		sql.close();

		BankAccount account = BankAccountController.getBankAccountById(id_account);
		this.updateAccountAmountByAccountId(account, amount, BTType);
	}

	private void updateAccountAmountByAccountId(BankAccount account, double amount, BankTradesType BTType) throws EOFException, SQLException {
		Connexion conn = new Connexion();
		Connection sql = conn.getConnexion();
		Statement state = sql.createStatement();

		double updatedAmount = 0;
		switch (BTType) {
			case ADD:
				updatedAmount = account.getAmount() + amount;
				break;
			case REMOVE:
				updatedAmount = account.getAmount() - amount;
				break;
			default: 
				return;
		}

		state.executeUpdate("UPDATE accounts SET amount = '"+updatedAmount+"' WHERE id = '"+account.getId()+"'");
		sql.close();
	}

	public List<BankTrades> getOperationsByAccountId(int accountId) throws EOFException, SQLException {
		Connexion conn = new Connexion();
		Connection sql = conn.getConnexion();
		Statement state = sql.createStatement();
		ResultSet result = state.executeQuery(
				"SELECT o.types type, o.amount amount, o.id_user userId, u.nom nom , u.prenom prenom FROM operation o JOIN accounts a ON a.id = o.id_account JOIN users u ON u.id = o.id_user WHERE a.id = '"
						+ accountId + "'");

		List<BankTrades> BT = new ArrayList<BankTrades>();

		while (result.next()) {
			BankTradesType type = BankTrades.getBankTradesTypeFromString(result.getString("type"));
			double amount = result.getDouble("amount");
			String name = result.getString("nom");
			String lastName = result.getString("prenom");
			int userId = result.getInt("userId");
			BT.add(new BankTrades(new User(userId, name, lastName), type, amount));
		}
		sql.close();
		return BT;
	}

	// update l'amount de l'account quand opération

	// && refresh sur le currentUser
}
