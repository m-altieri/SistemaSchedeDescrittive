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
		password = new JPasswordField("Password");
		password.setPreferredSize(new Dimension(400, 40));
		password.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				String pass = "";
				for (int i = 0; i < password.getPassword().length; i++)
					pass += password.getPassword()[i];
				
				if (pass.equals("Password"))
					password.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {}
			
		});
		
		cmdLogin = new JButton("Login");
		cmdRegistrati = new JButton("Non hai un account? Registrati");
		cmdLogin.setPreferredSize(new Dimension(200,50));
		cmdLogin.setActionCommand("Login");
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
			
			String pass = "";
			for (int i = 0; i < password.getPassword().length; i++) {
				pass += password.getPassword()[i];
			}
			
			boolean isAdmin = false;
			try {
				Database dbUtility = new Database(true);
				String query = "SELECT * FROM Utente WHERE nomeUtente = '" + username.get() + "' AND password = '" + pass + "'";
				ResultSet match = dbUtility.eseguiQueryRitorno(query);
				boolean esiste = match.next();
				
				if (esiste) {
					isAdmin = match.getBoolean(4);
					
					Client c = new Client(username.get(), isAdmin);
					c.setVisible(true);
					this.dispose();
				} else {
					JOptionPane.showMessageDialog(this, "Username o password errati", "Errore", JOptionPane.ERROR_MESSAGE);
				}
				
			} catch (SQLException | ClassNotFoundException | IOException f) {
				f.printStackTrace();
			}
			
		}
	}
	
	
}
