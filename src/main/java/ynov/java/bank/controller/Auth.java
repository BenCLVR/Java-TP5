package ynov.java.bank.controller;

import java.util.List;

import ynov.java.bank.modele.User;

public class Auth {
	int toto;

	public static User login (String pseudo, String password, User[] users) {
		for (User user : users) {
			if(user.pseudo.equals(pseudo) && user.password.equals(password)) {
				return user;
			}
		}
		return null;
	}
	
	
	public static List<User> register (int id, String nom, String pseudo, String password, List<User> users) {
		User toCreate = new User(id, nom, pseudo, password);
		users.add(toCreate);
		return users;
	}
}