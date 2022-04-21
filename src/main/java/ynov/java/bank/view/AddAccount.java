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

import ynov.java.bank.controller.Auth;
import ynov.java.bank.controller.BankAccountController;
import ynov.java.bank.controller.UserController;
import ynov.java.bank.modele.BankAccountType;

public class AddAccount extends JPanel {

	public AddAccount(final JFrame frame, final Auth cont) {
		
		//CONTROLLER
		final BankAccountController contAcc = new BankAccountController();
		
		//PANEL
		GridBagConstraints gbcp = new GridBagConstraints();
		gbcp.insets = new Insets(5, 5, 5, 5);

		gbcp.gridx = 0;
		gbcp.gridy = 0;

		JLabel lblTitre = new JLabel("Account title");
		add(lblTitre, gbcp);

		gbcp.gridx = 1;
		gbcp.gridy = 0;

		final JTextField textField_1 = new JTextField();
		add(textField_1, gbcp);
		textField_1.setColumns(10);

		gbcp.gridx = 4;
		gbcp.gridy = 0;
		final JLabel lblTitu2 = new JLabel("Second account owner");
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
		JLabel lblAccount = new JLabel("Account type");
		add(lblAccount, gbcp);

		gbcp.gridx = 13;
		gbcp.gridy = 0;
		final JComboBox comboBox = new JComboBox();
		comboBox.addItem("Select");
		comboBox.addItem(BankAccountType.CURRENT);
		comboBox.addItem(BankAccountType.JOINT);


		add(comboBox, gbcp);

		gbcp.gridx = 6;
		gbcp.gridy = 0;
		JButton btnClear = new JButton("Clear");
		add(btnClear, gbcp);

		JButton btnSubmit = new JButton("submit");

		add(btnSubmit, gbcp);

		//ACTIONS
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox.getSelectedItem().equals(BankAccountType.JOINT)) {
					lblTitu2.setVisible(true);
					textField_4.setVisible(true);
				} else if (comboBox.getSelectedItem().equals(BankAccountType.CURRENT)
						|| comboBox.getSelectedItem().equals("Select")) {
					lblTitu2.setVisible(false);
					textField_4.setVisible(false);
					textField_4.setText("");
				}
			}
		});
		
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if ((textField_1.getText().isEmpty()) || (comboBox.getSelectedItem().equals("Select")))
					JOptionPane.showMessageDialog(null, "Data Missing");
				else
					try {
						BankAccountType BAType = (BankAccountType) comboBox.getSelectedItem();
						boolean success = contAcc.createAccount(textField_1.getText(),
								BAType,
								cont.currentUser.getId());

						if (success) {
							cont.refreshBankAccount();
						} else {
							JOptionPane.showMessageDialog(null,
									"You can not create another account with type " + BAType.toString());
						}
						if (!textField_4.getText().isEmpty()) {
							String name = textField_4.getText();
							int id = UserController.getUserIdByName(name);
							if (id > 0) {
								success = contAcc.linkAccountWithUserId(textField_1.getText(), id);
								
							}
							if (!success) {
								JOptionPane.showMessageDialog(null,
										"Second user doesn't exist");
							}
							else {
								JOptionPane.showMessageDialog(null, "Data Submitted");
							}
						}


					} catch (EOFException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
		});

		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_1.setText(null);
				textField_4.setText(null);
				textField_4.setVisible(false);
				lblTitu2.setVisible(false);
				comboBox.setSelectedItem("Select");

			}
		});

	}

}
