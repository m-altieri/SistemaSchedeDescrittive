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
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import database.Database;

public class Registrazione extends JFrame implements ActionListener {

	/**
	 * 
	 */
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
		password = new JPasswordField("Password");
		password.setPreferredSize(new Dimension(500, 40));
		password.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {

				String pass = "";
				for (int i = 0; i < password.getPassword().length; i++) {
					pass += password.getPassword()[i];
				}
				if (pass.equals("Password"))
					password.setText("");
			}

			@Override
			public void focusLost(FocusEvent arg0) {}
			
		});
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
		
		if (e.getActionCommand().equals("Registrati")) {
			String nome = username.get();
			String pass = "";
			
			for (int i = 0; i < password.getPassword().length; i++) {
				pass += password.getPassword()[i];
			}
			
			try {
				
				if (alreadyExists(nome, pass))
					throw new UtenteGiàEsistenteException(nome);
				
				String codice = codiceAmministratore.get();
				boolean codiceValido = isCodeValid(codice);
				
				try {
					
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
					
				} catch (SQLException | ClassNotFoundException | IOException f) {
					f.printStackTrace();
				}
				
			} catch (UtenteGiàEsistenteException | ClassNotFoundException | IOException | SQLException g) {
				
			}
		}
	}
	
	private boolean alreadyExists(String user, String pass) throws ClassNotFoundException, IOException, SQLException {

		Database dbUtility = new Database(true);
		String queryEsiste = "SELECT * FROM Utente WHERE nomeUtente = '" + username.get() + "' AND password = '" + pass + "'";
		ResultSet match = dbUtility.eseguiQueryRitorno(queryEsiste);
		
		return match.next();
	}
	
	private boolean isCodeValid(String codice) {
		
		String cod = "";
		
		try {
			Database dbUtility = new Database(true);
			String query = "SELECT * FROM CodiceAdmin";
			
			ResultSet res = dbUtility.eseguiQueryRitorno(query);
			res.next();
			cod = res.getString(1);
			
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
		
		return cod.equals(codice);
	}
}
