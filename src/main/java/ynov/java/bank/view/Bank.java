package ynov.java.bank.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
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

import ynov.java.bank.modele.BankAccount;
import ynov.java.bank.modele.User;

public class Bank {

	
	JPanel currentPanel;

	// VARIABLES POOL
	private ArrayList<BankAccount> bankAccountPool = new ArrayList<BankAccount>();
	private ArrayList<User> userPool = new ArrayList<User>();

	// JACKSON my boy
	private ObjectMapper objectMapper = new ObjectMapper();
	private String dir = System.getProperty("user.dir");
	private String bankAccountsJsonFilePath = "\\ressources\\bankAccounts.json";
	private String usersJsonFilePath = "\\ressources\\users.json";

	public Bank() {

		
		final JLabel labelname = new JLabel("Nom");
		final JTextField name = new JTextField(10);
		final JButton button = new JButton("Sign Up");
		JButton buttonok = new JButton("Validate");



		labelname.setVisible(false);
		
		//FORM AUTH & GRID AUTH
		JPanel panelFormAuth = new JPanel();
		
		JLabel labelpseudo = new JLabel("Pseudo");
		JTextField pseudo = new JTextField(10);
		JLabel labelpw = new JLabel("Password");
		JTextField password = new JTextField(10);
		
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

				if (button.getText().equals("Sign Up")) {

					BankView test = new BankView (frame);
					frame.setContentPane(test);
					frame.repaint();
					frame.revalidate();
					
				} else {

				}
			}

		});
	}

	private ArrayList<BankAccount> loadBankAccounts() {
		ArrayList<BankAccount> bankAccounts = null;

		try {
			bankAccounts = objectMapper.readValue(new File(this.dir + this.bankAccountsJsonFilePath),
					new TypeReference<ArrayList<BankAccount>>() {
					});
		} catch (JsonParseException e) {
			e.printStackTrace();
			bankAccounts = new ArrayList<BankAccount>();
		} catch (JsonMappingException e) {
			e.printStackTrace();
			bankAccounts = new ArrayList<BankAccount>();
		} catch (IOException e) {
			e.printStackTrace();
			bankAccounts = new ArrayList<BankAccount>();
		}

		return (bankAccounts);
	}

	private int saveBankAccounts() {
		try {
			objectMapper.writeValue(new File(this.dir + this.bankAccountsJsonFilePath), this.bankAccountPool);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			return (1);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return (2);
		} catch (IOException e) {
			e.printStackTrace();
			return (3);
		}
		return (0);
	}

	private ArrayList<User> loadUsers() {
		ArrayList<User> users = null;

		try {
			users = objectMapper.readValue(new File(this.dir + this.usersJsonFilePath),
					new TypeReference<ArrayList<User>>() {
					});
		} catch (JsonParseException e) {
			e.printStackTrace();
			users = new ArrayList<User>();
		} catch (JsonMappingException e) {
			e.printStackTrace();
			users = new ArrayList<User>();
		} catch (IOException e) {
			e.printStackTrace();
			users = new ArrayList<User>();
		}

		return (users);
	}

	private int saveUsers() {
		try {
			objectMapper.writeValue(new File(this.dir + usersJsonFilePath), this.userPool);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			return (1);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return (2);
		} catch (IOException e) {
			e.printStackTrace();
			return (3);
		}
		return (0);
	}

}
