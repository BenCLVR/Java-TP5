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
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

		JPanel mainpanel = new JPanel();
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

		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.weightx = 1;
		gbc.weighty = 1;

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		mainpanel.add(panelpseudo, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		mainpanel.add(panelpass, gbc);
		gbc.gridx = 2;
		mainpanel.add(panelname, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 0;
		mainpanel.add(button, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 3;
		mainpanel.add(buttonok, gbc);

		name.setVisible(false);

		currentPanel = mainpanel;
		frame.add(currentPanel);
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

					BankView test = new BankView ();
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
