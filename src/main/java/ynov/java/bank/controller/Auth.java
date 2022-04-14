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
	
	public static User login (String pseudo, String password, User[] users) {
		for (User user : users) {
			if(user.pseudo.equals(pseudo) && user.password.equals(password)) {
				return user;
			}
		}
		return null;
	}
	
	
	public  int createUser(String name, String surname, String pwd ) throws EOFException, SQLException {
		Connection sql = conn.getConnexion();
		Statement state = sql.createStatement();
		int result = state.executeUpdate("INSERT INTO users (nom, prenom, mdp) VALUES ('"+name+"','"+surname+"','"+pwd+"')");
		sql.close();
		System.out.println(result);
		return result;
		
	}
	
	public  String LogUser(String name, String pwd ) throws EOFException, SQLException {
		String Rname = new String();
		Connection sql = conn.getConnexion();
		Statement state = sql.createStatement();
		ResultSet result = state.executeQuery("SELECT nom, mdp FROM users WHERE nom = '"+name+"' AND mdp = '"+pwd+"'");
		while (result.next()) {
			Rname = result.getString("nom");
			String Rpwd = result.getString("mdp");
			}
		System.out.println(Rname);
		return Rname;
	}
	
	public static List<User> register (int id, String nom, String pseudo, String password, List<User> users) {
		User toCreate = new User(id, nom, pseudo, password);
		users.add(toCreate);
		return users;
	}
}