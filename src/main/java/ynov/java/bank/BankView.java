package ynov.java.bank;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class BankView extends JPanel {

	public BankView() {
		JLabel labelpseudo = new JLabel("Pseudo");
		JTextField pseudo = new JTextField(10);
		JLabel labelpw = new JLabel("Password");
		JTextField password = new JTextField(10);
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;

		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.weightx = 1;
		gbc.weighty = 1;

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		add(labelpseudo, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		add(pseudo, gbc);
		gbc.gridx = 2;
		add(labelpw, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 0;
		add(password, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 3;
        
        
	}

}
