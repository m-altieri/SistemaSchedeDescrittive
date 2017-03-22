package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Login extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CampoCredenziale username;
	private CampoCredenziale password;
	private JButton cmdLogin;
	private JButton cmdRegistrati;
	private JPanel credenziali;
	private JPanel panelUsername;
	private JPanel panelPassword;
	private JPanel pulsanti;
	
	public Login() {
		
		super();
		
		this.setResizable(false);
		this.setIconImage(new ImageIcon("icon.png").getImage());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setTitle("Login");
		this.setSize(new Dimension(800, 600));
		this.setLocation(500, 200);
		this.getContentPane().setLayout(new BorderLayout());
		
		username = new CampoCredenziale("Username");
		username.setLunghezza(500);
		password = new CampoCredenziale("Password", true);
		password.setLunghezza(500);
		
		cmdLogin = new JButton("Login");
		cmdRegistrati = new JButton("Non hai un account? Registrati");
		cmdLogin.setPreferredSize(new Dimension(200,50));
		cmdRegistrati.setPreferredSize(new Dimension(300,50));
		cmdRegistrati.setActionCommand("Registrati");
		
		cmdLogin.addActionListener(this);
		cmdRegistrati.addActionListener(this);
		
		credenziali = new JPanel();
		credenziali.setLayout(new FlowLayout());
		pulsanti = new JPanel();
		pulsanti.setLayout(new FlowLayout());
		
		panelUsername = new JPanel();
		panelUsername.setLayout(new FlowLayout());
		panelPassword = new JPanel();
		panelPassword.setLayout(new FlowLayout());
		
		panelUsername.add(new JLabel("Username"));
		panelUsername.add(username);
		panelPassword.add(new JLabel("Password"));
		panelPassword.add(password);
		
		credenziali.add(panelUsername);
		credenziali.add(panelPassword);
		
		this.add(credenziali, BorderLayout.CENTER);
		
		pulsanti.add(cmdLogin);
		pulsanti.add(cmdRegistrati);
		
		this.add(pulsanti, BorderLayout.SOUTH);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Registrati")) {
			
			Registrazione fr = new Registrazione();
			fr.setVisible(true);
			this.dispose();
		}
		
		if (e.getActionCommand().equals("Login")) {
			
			this.cmdLogin.setEnabled(false);
			this.cmdRegistrati.setEnabled(false);
			this.paintAll(getGraphics());
			Client c = new Client();
			c.setVisible(true);
			this.dispose();
		}
	}
	
	
}
