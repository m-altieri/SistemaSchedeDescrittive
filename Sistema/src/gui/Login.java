package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import database.Database;

public class Login extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CampoCredenziale username;
	private JPasswordField password;
	private JButton cmdLogin;
	private JButton cmdRegistrati;
	
	public Login() {
		
		super();

		final int LARGHEZZA_FINESTRA = 800;
		final int ALTEZZA_FINESTRA = 600;
		final int POSIZIONE_FINESTRA_X = 500;
		final int POSIZIONE_FINESTRA_Y = 200;
		final int LARGHEZZA_CAMPO_PASSWORD = 400;
		final int ALTEZZA_CAMPO_PASSWORD = 40;
		final int LARGHEZZA_PULSANTE_LOGIN = 200;
		final int ALTEZZA_PULSANTE_LOGIN = 50;
		final int LARGHEZZA_PULSANTE_REGISTRATI = 300;
		final int ALTEZZA_PULSANTE_REGISTRATI = 50;
		
		this.setResizable(false);
		this.setIconImage(new ImageIcon("icon.png").getImage());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setTitle("Login");
		this.setSize(new Dimension(LARGHEZZA_FINESTRA, ALTEZZA_FINESTRA));
		this.setLocation(POSIZIONE_FINESTRA_X, POSIZIONE_FINESTRA_Y);
		this.getContentPane().setLayout(new BorderLayout());
		
		username = new CampoCredenziale("Username");
		
		final String defaultPasswordText = "Password";
		password = new JPasswordField(defaultPasswordText);
		password.setPreferredSize(new Dimension(LARGHEZZA_CAMPO_PASSWORD, ALTEZZA_CAMPO_PASSWORD));
		password.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				String pass = "";
				for (int i = 0; i < password.getPassword().length; i++) {
					pass += password.getPassword()[i];
				}
				if (pass.equals(defaultPasswordText))
					password.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {}
			
		});
		
		cmdLogin = new JButton("Login");
		cmdRegistrati = new JButton("Non hai un account? Registrati");
		cmdLogin.setPreferredSize(new Dimension(LARGHEZZA_PULSANTE_LOGIN, ALTEZZA_PULSANTE_LOGIN));
		cmdRegistrati.setPreferredSize(new Dimension(LARGHEZZA_PULSANTE_REGISTRATI, ALTEZZA_PULSANTE_REGISTRATI));
		cmdRegistrati.setActionCommand("Registrati");
		
		cmdLogin.addActionListener(this);
		cmdRegistrati.addActionListener(this);
		
		JPanel credenziali = new JPanel();
		credenziali.setLayout(new FlowLayout());
		JPanel pulsanti = new JPanel();
		pulsanti.setLayout(new FlowLayout());
		
		JPanel panelUsername = new JPanel();
		panelUsername.setLayout(new FlowLayout());
		JPanel panelPassword = new JPanel();
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

		if (e.getActionCommand().equals(cmdRegistrati.getActionCommand())) {
			
			Registrazione fr = new Registrazione();
			fr.setVisible(true);
			this.dispose();
		}
		
		if (e.getActionCommand().equals(cmdLogin.getActionCommand())) {
			
			cmdLogin.setEnabled(false);
			cmdRegistrati.setEnabled(false);
			String pass = "";
			char[] passchar = password.getPassword();
			
			for (int i = 0; i < passchar.length; i++) {
				pass += passchar;
			}
			
			boolean isAdmin = false;
			try {
				Database dbUtility = new Database(true);
				String query = "SELECT * FROM Utente WHERE nomeUtente = '" + username.get() + "' AND password = '" + pass + "'";
				ResultSet match = dbUtility.eseguiQueryRitorno(query);
				boolean esiste = match.next();
				
				final int COLONNA_ADMIN = 4;

				if (esiste) {
					isAdmin = match.getBoolean(COLONNA_ADMIN);
					
					Client c = new Client(username.get(), isAdmin);
					c.setVisible(true);
					this.dispose();
				} else {
					JOptionPane.showMessageDialog(this, "Username o password errati", "Errore", JOptionPane.ERROR_MESSAGE);
				}
				
			} catch (SQLException | ClassNotFoundException | IOException f) {
				JOptionPane.showMessageDialog(null, "Errore di connessione al server", "Errore", JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}
	
	
}
