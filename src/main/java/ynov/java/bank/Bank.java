package ynov.java.bank;

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
}
