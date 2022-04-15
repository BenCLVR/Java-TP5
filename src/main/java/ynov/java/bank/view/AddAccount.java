package ynov.java.bank.view;

import java.awt.GridBagConstraints;
import java.awt.Insets;
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

import ynov.java.bank.controller.BankController;

public class AddAccount extends JPanel{

	public AddAccount(final JFrame frame) {

		final BankController cont = new BankController();
		GridBagConstraints gbcp = new GridBagConstraints();
		gbcp.insets = new Insets(5, 5, 5, 5);

		gbcp.gridx = 0;
		gbcp.gridy = 0;
		
		JLabel lblTitre = new JLabel("Titre du Compte");
		add(lblTitre, gbcp);

		
		gbcp.gridx = 1;
		gbcp.gridy = 0;
		
		final JTextField textField_1 = new JTextField();
		add(textField_1, gbcp);
		textField_1.setColumns(10);

		gbcp.gridx = 2;
		gbcp.gridy = 1;
		JLabel lblApport = new JLabel("Apport d'ouverture");
		add(lblApport, gbcp);

		
		gbcp.gridx = 3;
		gbcp.gridy = 1;
		final JTextField textField_2 = new JTextField();
		add(textField_2, gbcp);
		textField_2.setColumns(10);

		
		gbcp.gridx = 4;
		gbcp.gridy = 0;
		JLabel lblTitu = new JLabel("Titulaire du compte");
		add(lblTitu, gbcp);
		
		
		
		gbcp.gridx = 5;
		gbcp.gridy = 0;
		final JTextField textField_3 = new JTextField();
		add(textField_3, gbcp);
		textField_3.setColumns(10);
		
		gbcp.gridx = 4;
		gbcp.gridy = 0;
		final JLabel lblTitu2 = new JLabel("Second Titulaire");
		lblTitu2.setVisible(false);
		add(lblTitu2, gbcp);
		
		
		gbcp.gridx = 3;
		gbcp.gridy = 1;
		final JTextField textField_4 = new JTextField();
		textField_4.setVisible(false);
		add(textField_4, gbcp);
		textField_4.setColumns(10);





		
		

		

		gbcp.gridx = 12;
		gbcp.gridy = 0;
		JLabel lblAccount = new JLabel("Type de compte");
		add(lblAccount, gbcp);

		
		

		gbcp.gridx = 13;
		gbcp.gridy = 0;
		final JComboBox comboBox = new JComboBox();
		comboBox.addItem("Select");
		comboBox.addItem("Courant");
		comboBox.addItem("Joint");

		



		comboBox.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if (comboBox.getSelectedItem().equals("Compte Join")) {
					lblTitu2.setVisible(true);
					textField_4.setVisible(true);				}
			}
		});
		add(comboBox, gbcp);

		
		gbcp.gridx = 6;
		gbcp.gridy = 0;
		JButton btnClear = new JButton("Clear");
		add(btnClear, gbcp);

		JButton btnSubmit = new JButton("submit");

		add(btnSubmit, gbcp);
		
		
		btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if((textField_1.getText().isEmpty())||(textField_2.getText().isEmpty())||(textField_3.getText().isEmpty())||(comboBox.getSelectedItem().equals("Select")))
                    JOptionPane.showMessageDialog(null, "Data Missing");
				else
					try {
						cont.createAccount(textField_1.getText(), textField_2.getText(), comboBox.getSelectedItem());
					} catch (EOFException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                JOptionPane.showMessageDialog(null, "Data Submitted");
            }
        });
         
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField_1.setText(null);
                textField_2.setText(null);
                textField_3.setText(null);
                textField_4.setText(null);
                textField_4.setVisible(false);
                lblTitu2.setVisible(false);
                comboBox.setSelectedItem("Select");
                 
                 
            }
        });

	}
	
}


