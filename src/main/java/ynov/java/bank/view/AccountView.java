package ynov.java.bank.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ynov.java.bank.controller.Auth;

public class AccountView extends JPanel{

	public AccountView(final JFrame frame, final Auth cont) {
		 
        JPanel panelLastTransaction = new JPanel();
 
        JPanel panelGridRight = new JPanel();
        panelGridRight.setLayout(new GridLayout(4,1));
        
        JButton btnGestAccount = new JButton("Retirer/Ajouter de l'argent");
		add(btnGestAccount);
 
        JPanel panelGridAccountBalance = new JPanel();
        panelGridAccountBalance.setLayout(new GridLayout(2,1));
        
        JLabel labelUserBalance = new JLabel("User : " + cont.currentUser.nom);
        final JComboBox comboAccountBalance = new JComboBox();
        comboAccountBalance.addItem(cont.currentUser.bankAccounts.get(0).getName());
        if(cont.currentUser.bankAccounts.get(1) != null){
        	comboAccountBalance.addItem(cont.currentUser.bankAccounts.get(1).getName());
		}
        //JButton btnValidateBalance = new JButton("Validate");
        final JLabel labelAmountBalance = new JLabel("Your balance :" + cont.currentUser.bankAccounts.get(0).getAmount());
 
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Type");
        model.addColumn("Amount");
        model.addColumn("User.s");
        JTable table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        
        panelLastTransaction.add(scroll, BorderLayout.CENTER);
        
        panelGridAccountBalance.add(comboAccountBalance);
        //panelGridAccountBalance.add(btnValidateBalance);
        panelGridRight.add(labelUserBalance);
        panelGridRight.add(panelGridAccountBalance);
        panelGridRight.add(labelAmountBalance);
        panelGridRight.add(btnGestAccount);
 
        this.add(panelLastTransaction);
        this.add(panelGridRight, BorderLayout.EAST); 
		
		
		
		btnGestAccount.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				GestAccount test = new GestAccount (frame, cont);
				frame.setContentPane(test);
				frame.repaint();
				frame.revalidate();
			};
		});
		
		comboAccountBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if(comboAccountBalance.getSelectedItem().equals(cont.currentUser.bankAccounts.get(1).getName())){
						labelAmountBalance.setText("Your balance :" + cont.currentUser.bankAccounts.get(1).getAmount());
					}
					else{
						labelAmountBalance.setText("Your balance :" + cont.currentUser.bankAccounts.get(0).getAmount());
					};
			}
		});
	}

}
