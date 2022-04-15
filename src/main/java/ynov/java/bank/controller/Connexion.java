package ynov.java.bank.controller;

import java.io.EOFException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connexion {

	public Connexion() {};

	public Connection getConnexion() throws EOFException, SQLException {

		//REFAIRE UNE DB


		
		//		String url = "jdbc:postgresql://localhost:5432/ynov";
		String url = "jbdc:postgresql://postgresql-claverie.alwaysdata.net:5432/claverie_bank";
		String username = "claverie_greg";
		String password = "Azerty=12345";
		Properties props = new Properties();
		props.setProperty("user", username);
		props.setProperty("password", password);
		props.setProperty("ssl","true");

		System.out.println("titi");

		Connection conn =DriverManager.getConnection("jdbc:postgresql://postgresql-claverie.alwaysdata.net:5432/claverie_bank", "claverie_greg", "Azerty=12345");

		return conn;



	}

}
