package ynov.java.bank.controller;

import java.io.EOFException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connexion {
	
	public Connexion() {};

	public static Connection getConnexion() throws EOFException, SQLException {
		
		//REFAIRE UNE DB

//		String url = "jdbc:postgresql://localhost:5432/ynov";
		String url = "jbdc:postgresql://postgresql-claverie_bank.alwaysdata.net";
		String username = "ynovuser";
		String password = "azerty123";
		Properties props = new Properties();
		props.setProperty("user", username);
		props.setProperty("password", password);
		props.setProperty("ssl","true");
		try {
			System.out.println("tata");
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection(url, props);
			System.out.println("titi");
			return conn;

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		

		
	}

}
