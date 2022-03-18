package ynov.java.bank;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.fasterxml.jackson.databind.ObjectMapper;


public class Bank {
	
	JPanel currentPanel;

	public Bank() {
		
		JPanel mainpanel = new JPanel();
		final JPanel accountpanel = new JPanel();
		JPanel panelpseudo = new JPanel();
		JPanel panelpass = new JPanel();
		JPanel panelname = new JPanel();
		

		JLabel labelpseudo = new JLabel("Pseudo");
		JTextField pseudo = new JTextField(10);
		JLabel labelpw = new JLabel("Password");
		JTextField password = new JTextField(10);
		final JLabel labelname = new JLabel("Nom");
		final JTextField name = new JTextField(10);
		final JButton button = new JButton("Sign Up");
		JButton buttonok = new JButton("Validate");
		
		panelpseudo.add(labelpseudo);
		panelpseudo.add(pseudo);
		panelpass.add(labelpw);
		panelpass.add(password);
		panelname.add(labelname);
		panelname.add(name);
		mainpanel.add(button);
		mainpanel.add(panelpseudo);
		mainpanel.add(panelpass);
		mainpanel.add(panelname);
		
		
		labelname.setVisible(false);
		
		final JFrame frame = new JFrame("Exemple");
		mainpanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		
		gbc.insets = new Insets(5,5,5,5);
		gbc.weightx = 1;
		gbc.weighty = 1;

		gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 1; mainpanel.add(panelpseudo, gbc);
		gbc.gridx = 1; gbc.gridy = 0;  gbc.gridwidth = 1; mainpanel.add(panelpass, gbc);
		gbc.gridx = 2; mainpanel.add(panelname, gbc);
		gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 0; mainpanel.add(button, gbc);
		gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 3; mainpanel.add(buttonok, gbc);

		

		name.setVisible(false);
		
		
		
		accountpanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc1 = new GridBagConstraints();
		gbc1.fill = GridBagConstraints.BOTH;
		
		gbc1.insets = new Insets(5,5,5,5);
		gbc1.weightx = 1;
		gbc1.weighty = 1;

		gbc1.gridx = 0; gbc1.gridy = 0; gbc1.gridwidth = 1; accountpanel.add(panelpseudo, gbc1);
		gbc1.gridx = 1; gbc1.gridy = 0;  gbc1.gridwidth = 1; 
		gbc1.gridx = 0; gbc1.gridy = 1; gbc1.gridwidth = 0; 
		gbc1.gridx = 0; gbc1.gridy = 2; gbc1.gridwidth = 3;

		currentPanel = mainpanel;
		frame.add(currentPanel);
		frame.setSize(700, 400);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				if(button.getText().equals("Sign Up")) {
					labelname.setVisible(true);
					name.setVisible(true);
					button.setText("Login");
				}
				else {
					labelname.setVisible(false);
					name.setVisible(false);
					button.setText("Sign Up");
				}
				
				
			}

		});
		
		buttonok.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
					currentPanel = accountpanel; 
				
			}

		});
	}
}
