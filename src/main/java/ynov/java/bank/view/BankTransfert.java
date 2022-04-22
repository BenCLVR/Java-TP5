
package ynov.java.bank.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ynov.java.bank.controller.Auth;
import ynov.java.bank.controller.BankBeneficiaryController;
import ynov.java.bank.controller.BankOperationController;
import ynov.java.bank.modele.BankAccount;
import ynov.java.bank.modele.BankTrades;
import ynov.java.bank.modele.BankTradesType;



public class BankTransfert extends JPanel {

	public BankTransfert(JFrame frame ,final Auth cont) throws EOFException, SQLException {

		//CONTROLLER
		final BankOperationController contOp = new BankOperationController();

		//PANEL ITEMS
		this.setLayout(new BorderLayout());

		JPanel ColumnPanel  = new JPanel();
		ColumnPanel.setLayout(new GridLayout(6,1));

		JPanel labelWelcomePanel = new JPanel();
		labelWelcomePanel.setLayout(new BorderLayout());

		JLabel welcomeLabel = new JLabel("Account management", SwingConstants.CENTER);

		JPanel emptyPanel;

		JPanel choiceAccountPanel = new JPanel();

		JLabel choiceAccountLabel = new JLabel("Select your account");

		final JComboBox choiceAccountCombo = new JComboBox();

		if(cont.currentUser.bankAccounts.get(0) != null) {
			choiceAccountCombo.addItem(cont.currentUser.bankAccounts.get(0).getName());
		}
		if(cont.currentUser.bankAccounts.size() > 1){
			choiceAccountCombo.addItem(cont.currentUser.bankAccounts.get(1).getName());
		}


		//A CHANGER POUR LES
		JLabel choiceAccountTransfertLabel = new JLabel("Select the desired benifeciary");
		final JComboBox choiceAccountTransfert = new JComboBox();
		choiceAccountTransfert.addItem("Select");

		List<BankAccount> beneficiare = BankBeneficiaryController.getBankAccountBeneficiariesByUserId(cont.currentUser.getId());
		if(beneficiare.size() > 0) {
			for (BankAccount account : beneficiare) {
				choiceAccountTransfert.addItem(account.getName());
			}
		}


		JPanel amountTransactionPanel = new JPanel();

		JLabel amountTransactionLabel = new JLabel("Amount");

		final JTextField amountTransactionTextField = new JTextField();
		amountTransactionTextField.setColumns(12);


		JPanel validationPanel = new JPanel();

		JButton validationButton = new JButton("Validate transfert");

		labelWelcomePanel.add(welcomeLabel, BorderLayout.CENTER);

		choiceAccountPanel.add(choiceAccountTransfertLabel);
		choiceAccountPanel.add(choiceAccountTransfert);
		choiceAccountPanel.add(choiceAccountLabel);
		choiceAccountPanel.add(choiceAccountCombo);

		amountTransactionPanel.add(amountTransactionLabel);
		amountTransactionPanel.add(amountTransactionTextField);

		validationPanel.add(validationButton);

		ColumnPanel.add(labelWelcomePanel);
		ColumnPanel.add(emptyPanel = new JPanel());
		ColumnPanel.add(choiceAccountPanel);
		ColumnPanel.add(amountTransactionPanel);
		ColumnPanel.add(emptyPanel = new JPanel());
		ColumnPanel.add(validationPanel);

		this.add(ColumnPanel);

		//START TRANSFERT ON ACCOUNT
		validationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				try { 	 

					if (choiceAccountCombo.getSelectedItem().equals(cont.currentUser.bankAccounts.get(0).getName())) {
						double amount = Double.parseDouble(amountTransactionTextField.getText()); 
						contOp.createTransfert(cont.currentUser.id, cont.currentUser.bankAccounts.get(0).getId() , choiceAccountTransfert.getSelectedItem().toString(), amount );
						JOptionPane.showMessageDialog(null, "Operation succesfull");
						cont.refreshBankAccount();
					}
					else {
						double amount = Double.parseDouble(amountTransactionTextField.getText()); 
						contOp.createTransfert(cont.currentUser.id, cont.currentUser.bankAccounts.get(1).getId() , choiceAccountTransfert.getSelectedItem().toString(), amount );
						JOptionPane.showMessageDialog(null, "Operation succesfull");
						cont.refreshBankAccount();
					}

				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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

}
