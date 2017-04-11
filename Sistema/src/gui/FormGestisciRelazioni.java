package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import database.Database;

@SuppressWarnings("serial")
public class FormGestisciRelazioni extends JPanel {
	
	private CampoCredenziale idPersonale;
	private CampoCredenziale idStrumentazione;
	private final int ALTEZZA_CAMPI = 40;

	public FormGestisciRelazioni(Visualizzatore v) {
		
		super();
		
		final String regexQualsiasiNumero = "\\d+";
		final int righeRadioButtons = 1;
		final int tipiElementi = 3;
		setLayout(new GridLayout(righeRadioButtons, tipiElementi));
		
		final int sizeFontCampi = 16;
		Font fontCampi = new Font("Arial", Font.PLAIN, sizeFontCampi);

		idPersonale = new CampoCredenziale("ID Personale");
		idPersonale.setPreferredSize(new Dimension(0, ALTEZZA_CAMPI));
		idStrumentazione = new CampoCredenziale("ID Strumentazione");
		
		idPersonale.setFont(fontCampi);
		idStrumentazione.setFont(fontCampi);
		
		JButton cmdAggiungi = new JButton("Aggiungi");
		cmdAggiungi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int risposta = JOptionPane.showConfirmDialog(null, "Sicuro?", "Conferma", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (risposta == JOptionPane.CLOSED_OPTION || risposta == JOptionPane.NO_OPTION) {
					return;
				}

				String idPersonale = FormGestisciRelazioni.this.idPersonale.get();
				String idStrumentazione = FormGestisciRelazioni.this.idStrumentazione.get();
				
				boolean inputValido = true;
				inputValido &= idPersonale.matches(regexQualsiasiNumero);
				inputValido &= idStrumentazione.matches(regexQualsiasiNumero);
				if (inputValido) {
					try {
						Database dbElementi = new Database();
						String queryInserisci = "INSERT INTO Utilizzo (idPersonale, idStrumentazione) VALUES (" + idPersonale + ", " + idStrumentazione + ")";
						dbElementi.eseguiQuery(queryInserisci);
					} catch (ClassNotFoundException | SQLException | IOException e1) {
						try {
							throw new InputInvalidoException(null);
						} catch (InputInvalidoException e2) {;}
					}
				}
				
				try {
					v.caricaPannelloDati();
				} catch (ClassNotFoundException | IOException e1) {;}
			}
		});
		
		JButton cmdElimina = new JButton("Elimina");
		cmdElimina.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String idPersonale = FormGestisciRelazioni.this.idPersonale.get();
				String idStrumentazione = FormGestisciRelazioni.this.idStrumentazione.get();
				
				boolean inputValido = true;
				inputValido &= idPersonale.matches(regexQualsiasiNumero);
				inputValido &= idStrumentazione.matches(regexQualsiasiNumero);
				if (inputValido) {
					try {
						Database dbElementi = new Database();
						String queryElimina = "DELETE FROM Utilizzo WHERE idPersonale = " + idPersonale + " AND idStrumentazione = " + idStrumentazione;
						dbElementi.eseguiQuery(queryElimina);
					} catch (ClassNotFoundException | SQLException | IOException e1) {;}
				}
				
				try {
					v.caricaPannelloDati();
				} catch (ClassNotFoundException | IOException e1) {;}
			}
			
		});
		
		add(idPersonale);
		add(idStrumentazione);
		add(cmdAggiungi);
		add(cmdElimina);
	}
}
