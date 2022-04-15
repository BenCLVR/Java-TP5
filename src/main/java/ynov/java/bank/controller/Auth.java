package ynov.java.bank.controller;

import java.io.EOFException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ynov.java.bank.modele.User;

public class Auth {
	int toto;

	Connexion conn = new Connexion();
	User currentUser = null;
	
	public int createUser(String name, String surname, String pwd ) throws EOFException, SQLException {
		Connection sql = conn.getConnexion();
		Statement state = sql.createStatement();
		int result = state.executeUpdate("INSERT INTO users (nom, prenom, mdp) VALUES ('"+name+"','"+surname+"','"+pwd+"')");
		sql.close();
		System.out.println("User added");
		return result;
	}
	
	public boolean LogUser(String name, String pwd ) throws EOFException, SQLException {
		String Rpwd = new String();
		Connection sql = conn.getConnexion();
		Statement state = sql.createStatement();
		
		int tempId = 0;
		String tempNom = null;
		String tempPrenom = null;
		String tempMdp = null;

		ResultSet result = state.executeQuery("SELECT id, nom, prenom, mdp FROM users WHERE nom = '"+name+"'");

		while (result.next()) {
			tempId = result.getInt("id");
			tempNom = result.getString("nom");
			tempPrenom = result.getString("prenom");
			tempMdp = result.getString("mdp");
			System.out.println(tempNom +" " +tempPrenom +" " +tempMdp);
			System.out.println("");
			Rpwd = tempMdp;
		}

		// si on a un resultat de la db (un user trouvé) et que les mdp correspondent on valide
		if(Rpwd != null && pwd.equals(Rpwd)){
			System.out.println("loggin succeced");
			this.currentUser = new User(tempId, tempNom, tempPrenom, tempMdp);
			System.out.println(currentUser.pseudo +" " +currentUser.password);
			return true;
		}

		result = state.executeQuery("SELECT id_account from linkaccount where id_user = '"+this.currentUser.getId()+"'");
		List<Integer> accountIds = new ArrayList<Integer>();
		while (result.next()) {
			accountIds.add(result.getInt("id_account"));
		}

		// String concatenedIds = accountIds.stream().map(String::valueOf).collect(Collectors.joining(","));



		System.out.println("loggin failed");
		return false;
	}

}