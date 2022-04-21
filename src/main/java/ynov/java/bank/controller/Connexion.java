package ynov.java.bank.controller;

import java.io.EOFException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import java.util.Properties;

public class Connexion {

	public Connexion() {};

	public Connection getConnexion() throws EOFException, SQLException {

		Connection conn =DriverManager.getConnection("jdbc:postgresql://postgresql-claverie.alwaysdata.net:5432/claverie_bank", "claverie_greg", "Azerty=12345");

		return conn;
	}

}
