package ynov.java.bank.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GestAccount extends JPanel
{

	public GestAccount(JFrame frame)
	{
		this.setLayout(new BorderLayout());
		 
        JPanel ColumnPanel  = new JPanel();
        ColumnPanel.setLayout(new GridLayout(6,1));
        
        JPanel labelWelcomePanel = new JPanel();
        labelWelcomePanel.setLayout(new BorderLayout());
        
        JLabel welcomeLabel = new JLabel("Gestion du Compte", SwingConstants.CENTER);
        
        JPanel emptyPanel;
        
        JPanel choiceAccountPanel = new JPanel();
        
        JLabel choiceAccountLabel = new JLabel("S�lectionner le compte d�sir�");
        
        JComboBox choiceAccountCombo = new JComboBox();
        // 
        // WHILE ACCOUNTS > ADD TO COMBO
        // choiceAccountCombo.addItem("accountname");
        // doit r�cup�rer de Auth les comptes de l'user
        
        
        JLabel choiceOperationLabel = new JLabel("S�lectionner l'op�ration d�sir�e");
        JComboBox choiceOperation = new JComboBox();
		//choiceOperation.addItem("Select");
		choiceOperation.addItem("Ajouter");
		choiceOperation.addItem("Retirer");

        JPanel amountTransactionPanel = new JPanel();

        JLabel amountTransactionLabel = new JLabel("Montant");

        JTextField amountTransactionTextField = new JTextField();
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
	}

}
