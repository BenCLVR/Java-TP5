package ynov.java.bank.view;


import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import ynov.java.bank.controller.Auth;

public class BankView extends JPanel{

	public BankView(final JFrame frame, final Auth cont) {

		//MENUBAR
		JMenuBar menuBar = new JMenuBar();
		JMenu options = new JMenu("Options");
		JMenu comptes = new JMenu("My Accounts");
		JMenuItem menuItemLeave = new JMenuItem("Leave");
		JMenuItem menuItemHome = new JMenuItem("Home");
		JMenuItem menuItemListComptes = new JMenuItem("My Accounts");
		JMenuItem menuItemAddComptes = new JMenuItem("Add Account");
		JMenuItem menuItemTransfert = new JMenuItem("Transferts");
		
		//PANEL ITEMS
		JLabel labelApp = new JLabel("Bank management application");
		JLabel labelVersion = new JLabel("Release 1.0");
		JLabel labelDev = new JLabel("Joffrey Jeunehomme, Benjamin Claverie, Gregoire Bisso");
		
		options.add(menuItemLeave);
		options.add(menuItemHome);
		comptes.add(menuItemListComptes);
		comptes.add(menuItemAddComptes);
		comptes.add(menuItemTransfert);
		menuBar.add(options);
		menuBar.add(comptes);
		
		frame.setJMenuBar(menuBar);
		

		//GRID PANEL BANKVIEW
		GridBagConstraints gbcp = new GridBagConstraints();
		gbcp.fill = GridBagConstraints.BOTH;

		gbcp.insets = new Insets(5, 5, 5, 5);

		gbcp.gridx = 0;
		gbcp.gridy = 0;
		
		add(labelApp, gbcp);
		
		
		
		add(labelVersion, gbcp);
		
		add(labelDev, gbcp);
		
		
		
		//ACTION GO LIST COMPTES
		menuItemListComptes.addActionListener(new ActionListener () {

			public void actionPerformed(ActionEvent e) {
				AccountView test;
				try {
					test = new AccountView (frame, cont);
					frame.setContentPane(test);
					frame.repaint();
					frame.revalidate();
				} catch (EOFException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			};
		});
		
		//CLOSE APP
		menuItemLeave.addActionListener(new ActionListener () {

			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				//Start Disconnect
				//cont.signOut();
//				Bank test = new Bank ();
//				frame.setContentPane(test);
//				frame.repaint();
//				frame.revalidate();
			};
		});
		
		//GO HOME
		menuItemHome.addActionListener(new ActionListener () {

			public void actionPerformed(ActionEvent e) {
				BankView test = new BankView (frame, cont);
				frame.setContentPane(test);
				frame.repaint();
				frame.revalidate();
			};
		});
		
		
		//GO FORM ADD COMPTE
		menuItemAddComptes.addActionListener(new ActionListener () {

			public void actionPerformed(ActionEvent e) {
				AddAccount test = new AddAccount (frame, cont);
				frame.setContentPane(test);
				frame.repaint();
				frame.revalidate();
			};
		});
		
		menuItemTransfert.addActionListener(new ActionListener () {

			public void actionPerformed(ActionEvent e) {
				BankTransfert test = new BankTransfert (frame, cont);
				frame.setContentPane(test);
				frame.repaint();
				frame.revalidate();
			};
		});
		
		
	}

}
