package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import database.Database;

/**
 * Finestra della GUI per registrarsi al sistema.
 * E' possibile inserire un codice facoltativo di 8 cifre aziendale, che permette di registrarsi in qualità di amministratori.
 * Gli amministratori hanno delle funzionalità avanzate a disposizione.
 * Al momento della registrazione il sistema controlla se sul server è già presente un utente con lo stesso nome e in tal caso nega la registrazione.
 */
public class Registrazione extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private CampoCredenziale username;
	private JPasswordField password;
	private CampoCredenziale codiceAmministratore;
	private JButton cmdRegistrati;
	private JButton cmdIndietro;
	private JPanel credenziali;
	private JPanel pulsanti;
	private JPanel panelUsername;
	private JPanel panelPassword;
	private JPanel panelAdmin;
	
	/**
	 * Crea la finestra e inizializza tutti i suoi componenti.
	 */
	public Registrazione() {
		
		super();
		
		this.setResizable(false);
		this.setIconImage(new ImageIcon("icon.png").getImage());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setTitle("Registrazione");
		this.setSize(new Dimension(800, 600));
		this.setLocation(500, 200);
		this.getContentPane().setLayout(new BorderLayout());
		
		username = new CampoCredenziale("Username");
		username.setLunghezza(500);
		password = new JPasswordField();
		password.setPreferredSize(new Dimension(500, 40));
		
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
		panelPassword.add(new JLabel("Password (almeno 8 caratteri)"));
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

	/**
	 * Gestisce le azioni eseguite al clic dei pulsanti.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals("Indietro")) {
			this.dispose();
			Login fl = new Login();
			fl.setVisible(true);
		}
		
		if (e.getActionCommand().equals("Registrati")) {
			String nome = username.get();
			String pass = "";
			
			char[] passChar = password.getPassword();
			for (int i = 0; i < passChar.length; i++) {
				pass = pass + password.getPassword()[i];
			}
			
			try {
				// controllo sintattico su username e password
				boolean inputValido = controllaInput(nome, pass);
				if (!inputValido)
					throw new InputInvalidoException(null);
				
				if (alreadyExists(nome))
					throw new UtenteGiaEsistenteException(nome);
				
				String codice = codiceAmministratore.get();
				boolean codiceValido = isCodeValid(codice);

				Database dbUtility = new Database(true);
				String query = "";
				
				if (codiceValido) {
					query = "INSERT INTO Utente(nomeUtente, password, amministratore) "
						+ "VALUES ( " + 
				"'" + nome + "', " +
				"'" + pass + "', " +
				"1)";
				} else {
					query = "INSERT INTO Utente(nomeUtente, password) "
							+ "VALUES ( " + 
					"'" + nome + "', " +
					"'" + pass + "')";
				}
				
				dbUtility.eseguiQuery(query);
			
				Login l = new Login();
				l.setVisible(true);
				dispose();

			} catch (Exception g) {;}
		}
	}
	
	private boolean controllaInput(String user, String pass) {
		
		boolean isValid = true;
		
		if (username.get().isEmpty() || username.get().equals(username.getHint()) || username.get().contains(" "))
			isValid = false;
		if (pass.length() < 8 || pass.contains(" "))
			isValid = false;		
		if (codiceAmministratore.get().equals(codiceAmministratore.getHint()))
			isValid = false;
		if (codiceAmministratore.get().length() != 8)
			isValid = false;

		return isValid;
	}

	/**
	 * Metodo privato.
	 * Controlla sul server se un certo utente esiste già.
	 * @param user L'username dell'utente da controllare.
	 * @param pass La password dell'utente da controllare.
	 * @return true se l'utente in input esiste già, false altrimenti.
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 */
	private boolean alreadyExists(String user) throws ClassNotFoundException, IOException, SQLException {

		Database dbUtility = new Database(true);
		String queryEsiste = "SELECT NomeUtente FROM Utente WHERE nomeUtente = '" + username.get() + "'";
		ResultSet match = dbUtility.eseguiQueryRitorno(queryEsiste);
		
		match.next();
		return match.getString(1).equals(user);
	}
	
	/**
	 * Metodo privato.
	 * Controlla se il codice admin passato in input è il codice esatto, nonché quello presente sul server.
	 * @param codice Il codice inserito in input.
	 * @return true se il codice è quello corretto, false altrimenti.
	 */
	private boolean isCodeValid(String codice) {
		
		String cod = "";
		
		try {
			Database dbUtility = new Database(true);
			String query = "SELECT * FROM CodiceAdmin";
			
			ResultSet res = dbUtility.eseguiQueryRitorno(query);
			res.next();
			cod = res.getString(1);
			
		} catch (ClassNotFoundException | IOException | SQLException e) {;}
		
		return cod.equals(codice);
	}
}
