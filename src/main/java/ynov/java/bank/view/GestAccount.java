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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ynov.java.bank.controller.Auth;
import ynov.java.bank.controller.BankOperationController;
import ynov.java.bank.modele.BankTradesType;



public class GestAccount extends JPanel {

	public GestAccount(JFrame frame ,final Auth cont) {
		
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
        
        JLabel choiceAccountLabel = new JLabel("Select the desired account");
        
        final JComboBox choiceAccountCombo = new JComboBox();
        
        if(cont.currentUser.bankAccounts.get(0) != null) {
        	choiceAccountCombo.addItem(cont.currentUser.bankAccounts.get(0).getName());
        }
        if(cont.currentUser.bankAccounts.size() > 1){
        	choiceAccountCombo.addItem(cont.currentUser.bankAccounts.get(1).getName());
		}
        
        
        
        JLabel choiceOperationLabel = new JLabel("Select the desired operation");
        final JComboBox choiceOperation = new JComboBox();
        choiceOperation.addItem("Select");
		choiceOperation.addItem(BankTradesType.ADD);
		choiceOperation.addItem(BankTradesType.REMOVE);
		
        
        JPanel amountTransactionPanel = new JPanel();
        
        JLabel amountTransactionLabel = new JLabel("Amount");
        
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
        
        //START OPERATION ON ACCOUNT
        validationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                 try { 	 
                	 if (choiceAccountCombo.getSelectedItem().equals(cont.currentUser.bankAccounts.get(0).getName())) {
                		 contOp.createOperation(cont.currentUser.id, cont.currentUser.getBankAccounts().get(0).getId(), Double.parseDouble(amountTransactionTextField.getText()), (BankTradesType) choiceOperation.getSelectedItem());
     					JOptionPane.showMessageDialog(null, "Operation succesfull");
     					cont.refreshBankAccount();
                	 }
                	 else {
                		 contOp.createOperation(cont.currentUser.id, cont.currentUser.getBankAccounts().get(1).getId(), Double.parseDouble(amountTransactionTextField.getText()), (BankTradesType) choiceOperation.getSelectedItem());
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
