package ynov.java.bank.controller;

import java.io.EOFException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ynov.java.bank.modele.BankAccount;

public class BankBeneficiary {
    

    public static boolean addBeneciary(int userId, String BAName) throws EOFException, SQLException {
        BankAccountController BACtrl = new BankAccountController();

        int accountId = BACtrl.getBankAccountIdByName(BAName);
        // si on ne trouve pas le bankAccount on renvoit success false;
        if(accountId < 1) return false;

        Connexion conn = new Connexion();
		Connection sql = conn.getConnexion();
		Statement state = sql.createStatement();

        state.executeUpdate("INSERT INTO beneficiary (id_user, id_account_benef) VALUES ('" + userId + "', '" + accountId + "')");
		sql.close();

        return true;
    }

    public static void removeBeneciary(int idAccount) throws EOFException, SQLException {
        Connexion conn = new Connexion();
		Connection sql = conn.getConnexion();
		Statement state = sql.createStatement();

        state.executeUpdate("DELETE FROM beneficiary WHERE id_account_benef = '"+idAccount+"'");
		sql.close();
    }

    // remove beneficiary


    public static List<BankAccount> getBankAccountBeneficiariesByUserId(int userId) throws EOFException, SQLException {
        List<Integer> accountIds = new ArrayList<Integer>();

        Connexion conn = new Connexion();
		Connection sql = conn.getConnexion();
		Statement state = sql.createStatement();

        ResultSet result = state.executeQuery("SELECT id_account_benef FROM beneficiary WHERE id_user = '"+userId+"'");
		
        while (result.next()) {
			int accountId = result.getInt("id_account_benef");
            accountIds.add(accountId);
		}

        sql.close();
        
        return BankAccountController.getBankAccountByIds(accountIds);
    }


}
