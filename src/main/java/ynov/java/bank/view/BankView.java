package ynov.java.bank.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ynov.java.bank.controller.Auth;

public class BankView extends JPanel{

	public BankView(final JFrame frame, final Auth cont) {

		//MENU BAR SUR LA FRAME
		JMenuBar menuBar = new JMenuBar();
		JMenu options = new JMenu("Options");
		JMenu comptes = new JMenu("Mes Comptes");
		JMenuItem menuItemLeave = new JMenuItem("Quitter");
		JMenuItem menuItemHome = new JMenuItem("Accueil");
		JMenuItem menuItemListComptes = new JMenuItem("Mes Comptes");
		JMenuItem menuItemAddComptes = new JMenuItem("Ajouter un Comptes");
		
		JLabel labelApp = new JLabel("Application de gestion bancaire");
		JLabel labelVersion = new JLabel("Version 1.0");
		JLabel labelDev = new JLabel("Joffrey Jeunehomme, Benjamin Claverie, Gr�goire Bisso");
		
		options.add(menuItemLeave);
		options.add(menuItemHome);
		comptes.add(menuItemListComptes);
		comptes.add(menuItemAddComptes);
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
				AccountView test = new AccountView (frame, cont);
				frame.setContentPane(test);
				frame.repaint();
				frame.revalidate();
			};
		});
		
		//CLOSE APP
		menuItemLeave.addActionListener(new ActionListener () {

			public void actionPerformed(ActionEvent e) {
				frame.dispose();
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
				AddAccount test = new AddAccount (frame);
				frame.setContentPane(test);
				frame.repaint();
				frame.revalidate();
			};
		});
	}

}
