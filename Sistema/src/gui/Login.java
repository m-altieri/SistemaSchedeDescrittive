package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.sql.PreparedStatement;
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

/**
 * Finestra della GUI per loggare al sistema. E' presente un pulsante per collegarsi alla finestra di registrazione.
 * Al momento del login, il sistema memorizza l'username e la password inserite in input, e manda una richiesta al database 
 * contenente gli utenti registrati per verificare se è presente un utente con tali dati; in caso affermativo, 
 * viene mostrata la schermata successiva, ovvero quella principale della GUI (Client), e viene tenuta traccia 
 * se l'utente in questione è un amministratore o meno, in caso negativo viene negato l'accesso.
 */
@SuppressWarnings("serial")
public class Login extends JFrame implements ActionListener, KeyListener {

	private CampoCredenziale username;
	private JPasswordField password;
	private JButton cmdLogin;
	private JButton cmdRegistrati;
	
	/**
	 * Crea la finestra e tutti i suoi componenti e li inizializza.
	 */
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
		
		password = new JPasswordField();
		password.setPreferredSize(new Dimension(LARGHEZZA_CAMPO_PASSWORD, ALTEZZA_CAMPO_PASSWORD));
		
		username.addKeyListener(this);
		password.addKeyListener(this);
		
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

	/**
	 * Gestisce le azioni compiute al clic dei pulsanti.
	 */
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
			update(getGraphics());
			
			String pass = "";
			char[] passchar = password.getPassword();
			
			for (int i = 0; i < passchar.length; i++) {
				pass = pass.concat(String.valueOf(passchar[i]));
			}
			
			boolean isAdmin = false;
			ResultSet match = null;
			try {
				
				Database dbUtility = new Database(true);
				String query = "SELECT * FROM Utente WHERE nomeUtente = ? AND password = ?";
				PreparedStatement ps = dbUtility.preparaQuery(query);
				ps.setString(1, username.get());
				ps.setString(2, pass);
				match = dbUtility.eseguiQueryPreparataRitorno(ps);
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
			} finally {
				if (match != null) {
					try {
						match.close();
					} catch (SQLException e1) {;}
				}
				cmdLogin.setEnabled(true);
				cmdRegistrati.setEnabled(true);
				update(getGraphics());
			}
			
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			cmdLogin.doClick();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent e) {}
	
}
