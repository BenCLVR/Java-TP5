package ynov.java.bank.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ynov.java.bank.controller.Auth;
import ynov.java.bank.controller.BankOperationController;
import ynov.java.bank.modele.BankAccountType;
import ynov.java.bank.modele.BankTradesType;



public class GestAccount extends JPanel {

	public GestAccount(JFrame frame ,final Auth cont) {
		
		final BankOperationController contOp = new BankOperationController();
		this.setLayout(new BorderLayout());
		 
        JPanel ColumnPanel  = new JPanel();
        ColumnPanel.setLayout(new GridLayout(6,1));
        
        JPanel labelWelcomePanel = new JPanel();
        labelWelcomePanel.setLayout(new BorderLayout());
        
        JLabel welcomeLabel = new JLabel("Gestion du Compte", SwingConstants.CENTER);
        
        JPanel emptyPanel;
        
        JPanel choiceAccountPanel = new JPanel();
        
        JLabel choiceAccountLabel = new JLabel("Sélectionner le compte désirer");
        
        JComboBox choiceAccountCombo = new JComboBox();
        
        if(cont.currentUser.bankAccounts.get(0) != null) {
        	choiceAccountCombo.addItem(cont.currentUser.bankAccounts.get(0).getName());
        }
        if(cont.currentUser.bankAccounts.get(1) != null){
        	choiceAccountCombo.addItem(cont.currentUser.bankAccounts.get(1).getName());
		}
        
        
        
        JLabel choiceOperationLabel = new JLabel("Sélectionner l'opération désirer");
        final JComboBox choiceOperation = new JComboBox();
        choiceOperation.addItem("Select");
		choiceOperation.addItem(BankTradesType.ADD);
		choiceOperation.addItem(BankTradesType.REMOVE);
		
        
        JPanel amountTransactionPanel = new JPanel();
        
        JLabel amountTransactionLabel = new JLabel("Montant");
        
        final JTextField amountTransactionTextField = new JTextField();
        amountTransactionTextField.setColumns(12);
        
        
        JPanel validationPanel = new JPanel();
        
        JButton validationButton = new JButton("Validate transaction");
        
        labelWelcomePanel.add(welcomeLabel, BorderLayout.CENTER);
        
        choiceAccountPanel.add(choiceOperationLabel);
        choiceAccountPanel.add(choiceOperation);
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
        
        validationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                 try {
					contOp.createOperation(cont.currentUser.id, cont.currentUser.getBankAccounts().get(0).getId(), Double.parseDouble(amountTransactionTextField.getText()), (BankTradesType) choiceOperation.getSelectedItem());
                    cont.refreshBankAccount();
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
