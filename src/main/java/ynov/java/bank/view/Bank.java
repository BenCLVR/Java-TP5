package ynov.java.bank.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ynov.java.bank.controller.Auth;
import ynov.java.bank.controller.BankController;
import ynov.java.bank.controller.Connexion;
import ynov.java.bank.modele.BankAccount;
import ynov.java.bank.modele.User;

public class Bank {

	
	JPanel currentPanel;

	// VARIABLES POOL

	// JACKSON my boy
	


	public Bank() {

		
		final JLabel labelname = new JLabel("Nom");
		final JTextField name = new JTextField(10);
		final JButton button = new JButton("Sign Up");
		JButton buttonok = new JButton("Validate");
		final Auth cont = new Auth();



		labelname.setVisible(false);
		
		//FORM AUTH & GRID AUTH
		JPanel panelFormAuth = new JPanel();
		
		JLabel labelpseudo = new JLabel("Pseudo");
		final JTextField pseudo = new JTextField(10);
		JLabel labelpw = new JLabel("Password");
		final JTextField password = new JTextField(10);
		
		panelFormAuth.setLayout(new GridBagLayout());
		panelFormAuth.setBorder(BorderFactory.createLineBorder(Color.black));
		GridBagConstraints gbcp = new GridBagConstraints();
		gbcp.fill = GridBagConstraints.BOTH;

		gbcp.insets = new Insets(5, 5, 5, 5);

		gbcp.gridx = 0;
		gbcp.gridy = 0;
		
		panelFormAuth.add(labelpseudo, gbcp);
		
		gbcp.gridx = 0;
		gbcp.gridy = 1;
		
		panelFormAuth.add(pseudo, gbcp);
		
		gbcp.gridx = 1;
		gbcp.gridy = 0;
		panelFormAuth.add(labelname, gbcp);
		
		gbcp.gridy = 1;
		panelFormAuth.add(name, gbcp);
		
		gbcp.gridx = 2;
		gbcp.gridy = 0;
		panelFormAuth.add(labelpw, gbcp);
		
		gbcp.gridy = 1;
		panelFormAuth.add(password, gbcp);
		
		gbcp.gridx = 2;
		gbcp.gridy = 2;
		panelFormAuth.add(button, gbcp);
		
		
		gbcp.gridx = 0;
		gbcp.gridy = 2;
		panelFormAuth.add(buttonok, gbcp);
		
		
		labelname.setVisible(false);
		name.setVisible(false);
		


		//MAIN GRID
		final JFrame frame = new JFrame("Banco");
		


		frame.add(panelFormAuth);
		frame.setSize(700, 400);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (button.getText().equals("Sign Up")) {
					labelname.setVisible(true);
					name.setVisible(true);
					button.setText("Login");
				} else {
					labelname.setVisible(false);
					name.setVisible(false);
					button.setText("Sign Up");
				}

			}

		});

		buttonok.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (button.getText().equals("Login")) {
					try {
						cont.createUser(name.getText(), pseudo.getText(), password.getText());
					} catch (EOFException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				} if (button.getText().equals("Sign Up")){
					try {
						ResultSet result = cont.LogUser(pseudo.getText(), password.getText());
						if (pseudo.getText().equals("e")) {
							BankView test = new BankView (frame);
							frame.setContentPane(test);
							frame.repaint();
							frame.revalidate();
						}
					} catch (EOFException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}

		});
	}

	

	

	

}
