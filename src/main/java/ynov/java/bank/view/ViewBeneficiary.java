package ynov.java.bank.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ynov.java.bank.controller.Auth;
import ynov.java.bank.controller.BankBeneficiaryController;
import ynov.java.bank.modele.BankAccount;

public class ViewBeneficiary extends JPanel
{

	public ViewBeneficiary(final JFrame frame, final Auth cont) throws EOFException, SQLException
	{
		// this is mainpanel
		// PANEL & LAYOUT
		final JTable table = new JTable(); // tableau de donn�es
		JScrollPane scrollPanel = new JScrollPane(table); // panneau coulissant dans lequel se trouve le tableau de donn�es
		//JPanel gestionPanel = new JPanel(); // 
		//gestionPanel.setLayout(new BoxLayout(gestionPanel, BoxLayout.Y_AXIS));

		//
		// GET DATAS
		// Array<Account> CLASS::getBeneficiaryAccount(User currentUser);
		//
		List<BankAccount> beneficiariesOfCurrentUser = BankBeneficiaryController.getBankAccountBeneficiariesByUserId(cont.currentUser.getId());

		// COLUMNS
		DefaultTableModel defTabMod = new DefaultTableModel();
		defTabMod.addColumn("Name");
		//defTabMod.addColumn("User");

		// ROWS
		for(BankAccount beneficiaryOfCurrentUser : beneficiariesOfCurrentUser)
		{
			defTabMod.addRow(createTableRowFromBeneficiary(beneficiaryOfCurrentUser));
		}
		
		JButton btnClear = new JButton("Delete");
		// COMPOSITION
		GridBagConstraints gbc = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		table.setModel(defTabMod); // ajouter les lignes et colones au tabeau de donn�es.
		this.add(scrollPanel, gbc); // ajouter le tout � la vue
		
		this.add(btnClear);
		
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String benremove = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
				
				try {
					BankBeneficiaryController.removeBeneciary(benremove);
				} catch (EOFException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	
	private Vector<String> createTableRowFromBeneficiary(BankAccount beneficiary)
	{
		Vector<String> row = new Vector<String>();

		row.add(beneficiary.getName());

		return (row);
	}
	



}
