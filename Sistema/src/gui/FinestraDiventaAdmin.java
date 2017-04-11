package gui;

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
import javax.swing.JOptionPane;

import database.Database;

/**
 * Finestra della GUI per diventare admin. E' richiesto un codice segreto aziendale di 8 cifre.
 * Essere admin garantisce l'accesso ad ulteriori funzioni.
 */
public class FinestraDiventaAdmin extends JFrame {

	private static final long serialVersionUID = 1L;
	private CampoCredenziale codice;
	private String user;
	
	/**
	 * Crea la finestra e inizializza tutti i suoi componenti.
	 * Cliccando sul pulsante di conferma viene estratto dal database il codice admin attuale, e viene 
	 * confrontato con quello passato tramite il campo di testo.
	 * @param user L'username dell'utente attualmente loggato.
	 */
	public FinestraDiventaAdmin(String user) {
		
		super();

		final int LARGHEZZA_FINESTRA = 600;
		final int ALTEZZA_FINESTRA = 150;
		final int POSIZIONE_FINESTRA_X = 600;
		final int POSIZIONE_FINESTRA_Y = 300;
		final int LARGHEZZA_CAMPO_CODICE = 200;
		final int LARGHEZZA_PULSANTE_CONFERMA = 150;
		final int ALTEZZA_PULSANTE_CONFERMA = 40;
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setTitle("Diventa admin");
		setResizable(false);
		this.setIconImage(new ImageIcon("icon.png").getImage());
		setSize(LARGHEZZA_FINESTRA, ALTEZZA_FINESTRA);
		setLocation(POSIZIONE_FINESTRA_X, POSIZIONE_FINESTRA_Y);
		setLayout(new FlowLayout());
		
		this.user = user;
		
		codice = new CampoCredenziale("Codice");
		codice.setLunghezza(LARGHEZZA_CAMPO_CODICE);
		JButton conferma = new JButton("Conferma");
		conferma.setPreferredSize(new Dimension(LARGHEZZA_PULSANTE_CONFERMA, ALTEZZA_PULSANTE_CONFERMA));
		
		conferma.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					if (codice.getText().length() != 8) {
						throw new CodiceNonValidoException(codice.getText());
					}
					
					String codiceAdmin = ottieniCodiceAdmin();

					if (codice.getText().equals(codiceAdmin)) {
						try {
							Database dbUtility = new Database(true);
							String query = "UPDATE Utente SET amministratore = 1 WHERE nomeUtente = '" + FinestraDiventaAdmin.this.user + "'";
							dbUtility.eseguiQuery(query);
							JOptionPane.showMessageDialog(null, "Ora sei un amministratore, effettua nuovamente il login per accedere alle funzionalità avanzate", "Successo", JOptionPane.INFORMATION_MESSAGE);
						
						} catch (ClassNotFoundException | IOException | SQLException f) {
							JOptionPane.showMessageDialog(null, "Errore di connessione con il server", "Errore", JOptionPane.ERROR_MESSAGE);
							} finally {
							dispose();
						}
					} else {
						throw new CodiceNonValidoException(codice.getText());
					}
				} catch (CodiceNonValidoException f) {;}
			}
		});
		
		add(new JLabel("Codice Admin"));
		add(codice);
		add(conferma);
	}
	
	private String ottieniCodiceAdmin() {
		
		Database dbUtility = null;
		String cod = "";
		ResultSet res = null;
		try {
			dbUtility = new Database(true);
			String query = "SELECT * FROM CodiceAdmin";
			res = dbUtility.eseguiQueryRitorno(query);
			res.next();
			cod = res.getString(1);
		} catch (ClassNotFoundException | IOException | SQLException e) {
			JOptionPane.showMessageDialog(null, "Impossibile ottenere il codice dal database", "Errore", JOptionPane.ERROR_MESSAGE);
		} finally {
			if (res != null) {
				try {
					res.close();
				} catch (SQLException e) {;}
			}
		}
		
		return cod;
	}
}
