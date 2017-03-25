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

public class FinestraDiventaAdmin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CampoCredenziale codice;
	private JButton conferma;
	private String user;
	
	public FinestraDiventaAdmin(String user) {
		
		super();
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setTitle("Diventa admin");
		setResizable(false);
		this.setIconImage(new ImageIcon("icon.png").getImage());
		setSize(600, 150);
		setLocation(600, 300);
		setLayout(new FlowLayout());
		
		this.user = user;
		
		codice = new CampoCredenziale("Codice");
		codice.setLunghezza(200);
		conferma = new JButton("Conferma");
		conferma.setPreferredSize(new Dimension(150, 40));
		
		conferma.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					if (codice.getText().length() != 8)
						throw new CodiceNonValidoException(codice.getText());
					
					String codiceAdmin = ottieniCodiceAdmin();
					///
					System.out.println(codiceAdmin);
					System.out.println(codice.getText());
					///
					if (codice.getText().equals(codiceAdmin)) {
						try {
							Database dbUtility = new Database(true);
							String query = "UPDATE Utente SET amministratore = 1 WHERE nomeUtente = '" + FinestraDiventaAdmin.this.user + "'";
							dbUtility.eseguiQuery(query);
							JOptionPane.showMessageDialog(null, "Ora sei un amministratore, effettua nuovamente il login per accedere alle funzionalità avanzate", "Successo", JOptionPane.INFORMATION_MESSAGE);
						
						} catch (ClassNotFoundException | IOException | SQLException f) {} 
						
						finally {
							dispose();
						}
					} else {
						throw new CodiceNonValidoException(codice.getText());
					}
				} catch (CodiceNonValidoException f) {}
			}
		});
		
		add(new JLabel("Codice Admin"));
		add(codice);
		add(conferma);
	}
	
	private String ottieniCodiceAdmin() {
		
		Database dbUtility;
		String codice = "";
		try {
			dbUtility = new Database(true);
			String query = "SELECT * FROM CodiceAdmin";
			ResultSet res = dbUtility.eseguiQueryRitorno(query);
			res.next();
			codice = res.getString(1);
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
		
		return codice;
	}
}
