package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import database.Database;

/**
 * Finestra della GUI per cambiare la password dell'account attualmente loggato.
 */
public class FinestraCambiaPassword extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPasswordField pass;
	private String user;
	
	/**
	 * Crea la finestra e inizializza tutti i suoi componenti.
	 * Facendo clic sul pulsanti di conferma viene sovrascritto il campo password sul database, 
	 * nella tupla corrispondente all'utente passato come parametro.
	 * @param user L'username dell'utente attualmente loggato.
	 */
	public FinestraCambiaPassword(String user) {
		
		super();

		final int LARGHEZZA_FINESTRA = 600;
		final int ALTEZZA_FINESTRA = 150;
		final int POSIZIONE_FINESTRA_X = 600;
		final int POSIZIONE_FINESTRA_Y = 300;
		final int LARGHEZZA_CAMPO_PASS = 200;
		final int ALTEZZA_CAMPO_PASS = 40;
		final int LARGHEZZA_PULSANTE_CONFERMA = 150;
		final int ALTEZZA_PULSANTE_CONFERMA = 40;
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setTitle("Cambia password");
		setResizable(false);
		this.setIconImage(new ImageIcon("icon.png").getImage());
		setSize(LARGHEZZA_FINESTRA, ALTEZZA_FINESTRA);
		setLocation(POSIZIONE_FINESTRA_X, POSIZIONE_FINESTRA_Y);
		setLayout(new FlowLayout());
		
		this.user = user;
		
		final String passwordText = "Password";
		pass = new JPasswordField(passwordText);
		pass.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {

				String password = "";
				char[] passchar = pass.getPassword();
				
				for (int i = 0; i < passchar.length; i++) {
					password += passchar[i];
				}
				if (password.equals(passwordText))
					pass.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {}
			
		});
		pass.setPreferredSize(new Dimension(LARGHEZZA_CAMPO_PASS, ALTEZZA_CAMPO_PASS));
		
		JButton conferma = new JButton("Conferma");
		conferma.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					Database dbUtility = new Database(true);
					String password = "";
					
					for (int i = 0; i < pass.getPassword().length; i++) {
						password += pass.getPassword()[i];
					}

					boolean inputValido = true;
					if (password.length() < 8 || password.contains(" "))
						inputValido = false;
					if (!inputValido)
						throw new InputInvalidoException(null);
					
					String query = "UPDATE Utente SET password = '" + password + "' WHERE nomeUtente = '" + FinestraCambiaPassword.this.user + "'";
					dbUtility.eseguiQuery(query);
					
					dispose();
					JOptionPane.showMessageDialog(null, "Password cambiata con successo", "Successo", JOptionPane.INFORMATION_MESSAGE);
				} catch (ClassNotFoundException | IOException | SQLException e1) {
					JOptionPane.showMessageDialog(null, "Errore di connessione al server", "Errore", JOptionPane.ERROR_MESSAGE);
				} catch (InputInvalidoException f) {;}
			}
		});
		conferma.setPreferredSize(new Dimension(LARGHEZZA_PULSANTE_CONFERMA, ALTEZZA_PULSANTE_CONFERMA));
		
		add(new JLabel("Nuova password (almeno 8 caratteri)"));
		add(pass);
		add(conferma);
	}
}
