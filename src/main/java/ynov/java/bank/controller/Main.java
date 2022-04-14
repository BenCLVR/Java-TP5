package ynov.java.bank.controller;

import java.io.EOFException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import ynov.java.bank.view.Bank;


public class Main {
	public static void main(String[] args) {
		System.out.println("toto");
		Connexion conn = new Connexion();
		try {
			Connection sql = conn.getConnexion();
			System.out.println("toto");
			Statement state = sql.createStatement();
			System.out.println("toto");
			Bank h = new Bank(state);
		} catch (EOFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
