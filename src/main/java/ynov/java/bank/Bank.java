package ynov.java.bank;

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


public class Bank
{
	// VARIABLES POOL
	private ArrayList<BankAccount> bankAccountPool = new ArrayList<BankAccount>();
	private ArrayList<User> userPool = new ArrayList<User>();
	
	// JACKSON my boy
	private ObjectMapper objectMapper = new ObjectMapper();
	private String dir = System.getProperty("user.dir");
	private String bankAccountsJsonFilePath = "\\ressources\\bankAccounts.json";
	private String usersJsonFilePath = "\\ressources\\users.json";

	public Bank() {
		JFrame frame = new JFrame("Exemple");

		JPanel mainpanel = new JPanel();
		JPanel paneltext = new JPanel();

		JLabel labelpseudo = new JLabel("Pseudo");
		JTextField pseudo = new JTextField();
		JLabel labelpw = new JLabel("Password");
		JTextField password = new JTextField();
		final JLabel labelnom = new JLabel("Nom");
		final JTextField nom = new JTextField();
		final JButton button = new JButton("Sign Up");
		
		labelnom.setVisible(false);
		nom.setVisible(false);

		Object[] form = new Object[] {labelpseudo, pseudo, labelpw, password, labelnom, nom, button};

		int result = JOptionPane.showOptionDialog(
				frame,
				form,
				"Ajout Formateur",
				JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.INFORMATION_MESSAGE,
				null,
				null,
				null
				);



		frame.setSize(700, 400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				labelnom.setVisible(true);
				nom.setVisible(true);
				button.setText("Login");
				
			}

		});
	}
	
	private ArrayList<BankAccount> loadBankAccounts()
	{
		ArrayList<BankAccount> bankAccounts = null;

		try
		{
			bankAccounts = objectMapper.readValue(new File(this.dir + this.bankAccountsJsonFilePath), new TypeReference<ArrayList<BankAccount>>(){});
		}
		catch (JsonParseException e) {
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
	private int saveBankAccounts()
	{
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
	
	private ArrayList<User> loadUsers()
	{
		ArrayList<User> users = null;

		try
		{
			users = objectMapper.readValue(new File(this.dir + this.usersJsonFilePath), new TypeReference<ArrayList<User>>(){});
		}
		catch (JsonParseException e) {
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
	private int saveUsers()
	{
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
