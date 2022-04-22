package ynov.java.bank.view;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ynov.java.bank.controller.Auth;
import ynov.java.bank.controller.BankBeneficiaryController;

public class AddBenef extends JPanel {

	public AddBenef(final JFrame frame, final Auth cont) {

		//CONTROLLER
		final BankBeneficiaryController contBen = new BankBeneficiaryController();

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



		gbcp.gridx = 2;
		gbcp.gridy = 0;
		JButton btnClear = new JButton("Clear");
		add(btnClear, gbcp);

		JButton btnSubmit = new JButton("submit");

		add(btnSubmit, gbcp);

		//ACTIONS


		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if ((textField_1.getText().isEmpty()))
					JOptionPane.showMessageDialog(null, "Data Missing");
				else {
					try {
						boolean success = contBen.addBeneciary(cont.currentUser.id, textField_1.getText());
						if(!success) {
							JOptionPane.showMessageDialog(null,
									"We can not find this account : " + textField_1.getText());
						}
					} catch (EOFException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} 
			}}

				);

		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_1.setText(null);
			}
		});

	}

}
