package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Registrazione extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CampoCredenziale username;
	private CampoCredenziale password;
	private CampoCredenziale codiceAmministratore;
	private JButton cmdRegistrati;
	private JButton cmdIndietro;
	private JPanel credenziali;
	private JPanel pulsanti;
	private JPanel panelUsername;
	private JPanel panelPassword;
	private JPanel panelAdmin;
	
	public Registrazione() {
		
		super();
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setTitle("Registrazione");
		this.setSize(new Dimension(800, 600));
		this.setLocation(500, 200);
		this.getContentPane().setLayout(new BorderLayout());
		
		username = new CampoCredenziale("Username");
		username.setLunghezza(500);
		password = new CampoCredenziale("Password");
		password.setLunghezza(500);
		codiceAmministratore = new CampoCredenziale("Codice amministratore");
		codiceAmministratore.setHint("Lascia vuoto se non vuoi registrarti come amministratore.");
		codiceAmministratore.setLunghezza(500);
		cmdRegistrati = new JButton("Registrati");
		cmdRegistrati.setActionCommand("Registrati");
		cmdIndietro = new JButton("Indietro");
		cmdIndietro.setActionCommand("Indietro");
		cmdRegistrati.setPreferredSize(new Dimension(200, 50));
		cmdIndietro.setPreferredSize(new Dimension(200, 50));
		
		cmdRegistrati.addActionListener(this);
		cmdIndietro.addActionListener(this);
		
		credenziali = new JPanel();
		credenziali.setLayout(new FlowLayout());
		pulsanti = new JPanel();
		pulsanti.setLayout(new FlowLayout());
		
		panelUsername = new JPanel();
		panelUsername.setLayout(new FlowLayout());
		panelPassword = new JPanel();
		panelPassword.setLayout(new FlowLayout());
		panelAdmin = new JPanel();
		panelAdmin.setLayout(new FlowLayout());
		
		panelUsername.add(new JLabel("Username"));
		panelUsername.add(username);
		panelPassword.add(new JLabel("Password"));
		panelPassword.add(password);
		panelAdmin.add(new JLabel("Codice admin"));
		panelAdmin.add(codiceAmministratore);
		
		credenziali.add(panelUsername);
		credenziali.add(panelPassword);
		credenziali.add(panelAdmin);
		
		this.add(credenziali, BorderLayout.CENTER);
		
		pulsanti.add(cmdRegistrati);
		pulsanti.add(cmdIndietro);
		
		this.add(pulsanti, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals("Indietro")) {
			this.dispose();
			Login fl = new Login();
			fl.setVisible(true);
		}
	}
}
