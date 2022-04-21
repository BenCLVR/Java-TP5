package ynov.java.bank.controller;

import java.io.EOFException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserController {

    public static int getUserIdByName(String name) throws SQLException, EOFException {
        Connexion conn = new Connexion();
		Connection sql = conn.getConnexion();
		Statement state = sql.createStatement();

        ResultSet result = state.executeQuery("SELECT id from users WHERE name = '" + name + "'");
		int id = 0;
		while (result.next()) {
			id = result.getInt("id");
		}
		sql.close();
		return id;
    }
    
}