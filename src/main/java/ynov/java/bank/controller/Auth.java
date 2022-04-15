package ynov.java.bank.controller;

import java.io.EOFException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import ynov.java.bank.modele.User;

public class Auth {
	int toto;

	Connexion conn = new Connexion();
	User currentUser = null;
	
	public User login (String pseudo, String password, User[] users) {
		System.out.println(users.length);
		for (User user : users) {
			if(user.pseudo.equals(pseudo) && user.password.equals(password)) {
				this.currentUser = user;
				return user;
			}
		}
		return null;
	}
	
	
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
			currentUser = new User(tempId, tempNom, tempPrenom, tempMdp);
			System.out.println(currentUser.pseudo +" " +currentUser.password);
			return true;
		}
		System.out.println("loggin failed");
		return false;
	}

	public static List<User> register (int id, String nom, String pseudo, String password, List<User> users) {
		User toCreate = new User(id, nom, pseudo, password);
		users.add(toCreate);
		return users;
	}
	
	public User getCurrentUser()
	{
		return (currentUser);
	}
}