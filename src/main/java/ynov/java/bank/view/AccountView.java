package ynov.java.bank.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ynov.java.bank.controller.Auth;
import ynov.java.bank.controller.BankOperationController;
import ynov.java.bank.modele.BankAccount;
import ynov.java.bank.modele.BankTrades;

public class AccountView extends JPanel {

	public AccountView(final JFrame frame, final Auth cont) throws SQLException, EOFException {

		//CONTROLLER
		final BankOperationController contOp = new BankOperationController();

		//PANEL
		JPanel panelLastTransaction = new JPanel();


		JPanel panelGridRight = new JPanel();
		panelGridRight.setLayout(new GridLayout(4, 1));

		JButton btnGestAccount = new JButton("Remove/Add money");

		JPanel panelGridAccountBalance = new JPanel();
		panelGridAccountBalance.setLayout(new GridLayout(2, 1));

		JLabel labelUserBalance = new JLabel("User : " + cont.currentUser.lastname);
		final JComboBox comboAccountBalance = new JComboBox();

		final JLabel labelAmountBalance = new JLabel("Your balance : N/C");


		final DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Type");
		model.addColumn("Amount");
		model.addColumn("User.s");

		JTable table = new JTable(model);

		//CHECK ACCOUNT
		if (cont.currentUser.bankAccounts.size() > 0) {
			panelGridRight.add(btnGestAccount);
			comboAccountBalance.addItem(cont.currentUser.bankAccounts.get(0).getName());
			labelAmountBalance.setText("Your balance :" + cont.currentUser.bankAccounts.get(0).getAmount());
			List<BankTrades> listTrades = contOp.getOperationsByAccountId(cont.currentUser.bankAccounts.get(0).getId());
			for (BankTrades trade : listTrades) {
				model.addRow(new Object[] { trade.getType(), trade.getAmount(), trade.getUser().lastname });
			}
		}

		//CHECK ACCOUNT 2
		if (cont.currentUser.bankAccounts.size() > 1) {
			comboAccountBalance.addItem(cont.currentUser.bankAccounts.get(1).getName());
		}
		JScrollPane scroll = new JScrollPane(table);

		panelLastTransaction.add(scroll, BorderLayout.CENTER);

		panelGridAccountBalance.add(comboAccountBalance);
		panelGridRight.add(labelUserBalance);
		panelGridRight.add(panelGridAccountBalance);
		panelGridRight.add(labelAmountBalance);

		this.add(panelLastTransaction);
		this.add(panelGridRight, BorderLayout.EAST);

		
		
		//GO GEST ACCOUNT
		btnGestAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestAccount test = new GestAccount(frame, cont);
				frame.setContentPane(test);
				frame.repaint();
				frame.revalidate();
			};
		});

		//SELECT ACCOUNT
		comboAccountBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BankAccount BA;

				if (cont.currentUser.bankAccounts.size() > 1 && comboAccountBalance.getSelectedItem()
						.equals(cont.currentUser.bankAccounts.get(1).getName())) {
					BA = cont.currentUser.bankAccounts.get(1);
				} else {
					BA = cont.currentUser.bankAccounts.get(0);
				}
				labelAmountBalance.setText("Your balance :" + BA.getAmount());
				List<BankTrades> listTrades1;
				try {
					System.out.println("Start OP");
					System.out.println(BA.getId());
					listTrades1 = contOp.getOperationsByAccountId(BA.getId());
					System.out.println("Start OP");
					model.setRowCount(0);
					for (BankTrades trade : listTrades1) {
						model.addRow(new Object[] { trade.getType(), trade.getAmount(), trade.getUser().lastname });
					}
				} catch (EOFException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
	}

}
