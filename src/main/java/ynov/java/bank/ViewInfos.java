package ynov.java.bank;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ViewInfos extends JPanel
{
	String appversion = "";

	ViewInfos(String appversion, ActionListener login)
	{
		super();
		this.appversion = appversion;

		// LAYOUT
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// COMPS
		JLabel titreLabel = new JLabel("AppSuperBank");
		JLabel devsLabel = new JLabel("Développé par : " +"3 gigolos");
		JLabel versionLabel = new JLabel("Version : " +this.appversion);
		JLabel descriptionLabel = new JLabel("Application de gestion de BANK boy here we go");
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(login);

		// STYLE
		// Font
		Font font = new Font("Tahoma", Font.PLAIN, 26);
		titreLabel.setFont(font);
		devsLabel.setFont(font);
		versionLabel.setFont(font);
		descriptionLabel.setFont(font);
		// Color
		versionLabel.setForeground(Color.RED);
		
		// COMPOSITION
		add(titreLabel);
		add(devsLabel);
		add(versionLabel);
		add(descriptionLabel);
		add(loginButton);
	}
}
