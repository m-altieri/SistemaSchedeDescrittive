package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import database.Database;

public class FinestraCambiaCodice extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CampoCredenziale codice;
	
	public FinestraCambiaCodice() {
		
		super();

		final int LARGHEZZA_FINESTRA = 600;
		final int ALTEZZA_FINESTRA = 150;
		final int POSIZIONE_FINESTRA_X = 600;
		final int POSIZIONE_FINESTRA_Y = 300;
		final int LARGHEZZA_CAMPO_CODICE = 200;
		final int LARGHEZZA_PULSANTE_CONFERMA = 150;
		final int ALTEZZA_PULSANTE_CONFERMA = 40;
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setTitle("Cambia codice admin");
		setResizable(false);
		this.setIconImage(new ImageIcon("icon.png").getImage());
		setSize(LARGHEZZA_FINESTRA, ALTEZZA_FINESTRA);
		setLocation(POSIZIONE_FINESTRA_X, POSIZIONE_FINESTRA_Y);
		setLayout(new FlowLayout());
		
		codice = new CampoCredenziale("Codice");
		codice.setLunghezza(LARGHEZZA_CAMPO_CODICE);
		JButton conferma = new JButton("Conferma");
		conferma.setPreferredSize(new Dimension(LARGHEZZA_PULSANTE_CONFERMA, ALTEZZA_PULSANTE_CONFERMA));
		
		conferma.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					Database dbUtility = new Database(true);
					String query = "INSERT INTO CodiceAdmin (codice) VALUES ('" + codice.get() + "')";
					dbUtility.eseguiQuery(query);
					dispose();
					JOptionPane.showMessageDialog(null, "Nuovo codice: " + codice.get(), "Successo", JOptionPane.INFORMATION_MESSAGE);
				} catch (ClassNotFoundException | IOException | SQLException f) {
					JOptionPane.showMessageDialog(null, "Errore di collegamento con il server", "Errore", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		
		add(new JLabel("Nuovo codice"));
		add(codice);
		add(conferma);
	}
}
