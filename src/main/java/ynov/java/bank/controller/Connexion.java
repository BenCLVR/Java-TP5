package ynov.java.bank.controller;

import java.io.EOFException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {

	public static Connection getConnexion() throws EOFException, SQLException {
		
		//REFAIRE UNE DB
		String driver = "org.postgresql.Driver";
		//String url = "jdbc:postgresql://localhost:5432/ynov";
		String url = "postgresql-claverie_bank.alwaysdata.net";
		String username = "ynovuser";
		String password = "azerty123";
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection conn = DriverManager.getConnection("postgresql-claverie.alwaysdata.net:5432", "claverie_greg", "Azerty=12345");

		return conn;
	}

}
