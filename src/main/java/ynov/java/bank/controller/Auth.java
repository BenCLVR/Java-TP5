package ynov.java.bank.controller;

import java.io.EOFException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ynov.java.bank.modele.BankAccount;
import ynov.java.bank.modele.User;

public class Auth {
	int toto;

	Connexion conn = new Connexion();
	public User currentUser = null;

	/**
	 * créer un nouvel utilisateur
	 * 
	 * @param lastname
	 * @param firstname
	 * @param pwd
	 * @return
	 * @throws EOFException
	 * @throws SQLException
	 */
	public int createUser(String lastname, String firstname, String pwd) throws EOFException, SQLException {
		Connection sql = conn.getConnexion();
		Statement state = sql.createStatement();
		int result = state.executeUpdate(
				"INSERT INTO users (nom, prenom, mdp) VALUES ('" + lastname + "','" + firstname + "','" + pwd + "')");
		sql.close();
		System.out.println("User added");
		return result;
	}

	/**
	 * Connecte un utilisateur et l'enregistre en global afin d'y avoir accès
	 * partout dans l'app
	 * 
	 * @param name
	 * @param pwd
	 * @return
	 * @throws EOFException
	 * @throws SQLException
	 */
	public boolean LogUser(String name, String pwd) throws EOFException, SQLException {
		String Rpwd = new String();
		Connection sql = conn.getConnexion();
		Statement state = sql.createStatement();

		int tempId = 0;
		String lastName = null;
		String firstName = null;
		String password = null;

		ResultSet result = state.executeQuery("SELECT id, nom, prenom, mdp FROM users WHERE nom = '" + name + "'");
		while (result.next()) {
			tempId = result.getInt("id");
			lastName = result.getString("nom");
			firstName = result.getString("prenom");
			password = result.getString("mdp");
			System.out.println(lastName + " " + firstName + " " + password);
			System.out.println("");
			Rpwd = password;
		}
		state.close();

		// si on a un resultat de la db (un user trouvé) et que les mdp correspondent on
		// valide
		if (Rpwd.equals(null) || !pwd.equals(Rpwd)) {
			System.out.println("loggin failed");
			return false;
		}

		System.out.println("loggin succeced");
		this.currentUser = new User(tempId, lastName, firstName, password);
		System.out.println(currentUser.firstname + " " + currentUser.password);

		List<Integer> BAIds = BankAccountController.getBankAccountIdsByUser(tempId);
		if (this.currentUser.bankAccounts == null) {
			this.currentUser.setBankAccounts(new ArrayList<BankAccount>());
		}

		for (Integer id : BAIds) {
			this.currentUser.bankAccounts.add(BankAccountController.getBankAccountById(id));
		}

		System.out.println(this.currentUser.bankAccounts.size());

		return true;
	}

	/**
	 * rafraichi les données de l'utilisateur connecté
	 * nécessaire après chaque opérations sur les comptes ou les operations
	 * 
	 * @throws EOFException
	 * @throws SQLException
	 */
	public void refreshBankAccount() throws EOFException, SQLException {
		List<Integer> BAIds = BankAccountController
				.getBankAccountIdsByUser(this.currentUser.getId());
		if (this.currentUser.bankAccounts == null) {
			this.currentUser.setBankAccounts(new ArrayList<BankAccount>());
		}
		this.currentUser.bankAccounts.clear();
		for (Integer id : BAIds) {
			this.currentUser.bankAccounts.add(BankAccountController.getBankAccountById(id));
		}
	}

	/**
	 * déconnecte l'utilisateur
	 */
	public void signOut() {
		this.currentUser = null;
	}

}