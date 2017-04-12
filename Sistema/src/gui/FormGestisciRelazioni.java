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

/**
 * Componente di PannelloGestisciRelazioni che permette l'aggiunta di nuove relazioni e l'eliminazione di quelle già esistenti.
 * @see PannelloGestisciRelazioni
 */
@SuppressWarnings("serial")
public class FormGestisciRelazioni extends JPanel implements ActionListener {
	
	private CampoCredenziale idPersonale;
	private CampoCredenziale idStrumentazione;
	private final int ALTEZZA_CAMPI = 40;
	private Visualizzatore v;
	private final String regexQualsiasiNumero = "\\d+";
	private final int righeRadioButtons = 1;

	/**
	 * Crea il form e inizializza tutte le sue componenti.
	 * @param v La tabella relazioni su cui visualizzare i dati esistenti.
	 */
	public FormGestisciRelazioni(Visualizzatore v) {
		
		super();

		this.v = v;
		
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
		cmdAggiungi.setActionCommand("Aggiungi");
		cmdAggiungi.addActionListener(this);
		
		JButton cmdElimina = new JButton("Elimina");
		cmdElimina.setActionCommand("Elimina");
		cmdElimina.addActionListener(this);
		
		add(idPersonale);
		add(idStrumentazione);
		add(cmdAggiungi);
		add(cmdElimina);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals("Aggiungi")) {
			
			int risposta = JOptionPane.showConfirmDialog(null, "Sicuro?", "Conferma", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if (!(risposta == JOptionPane.CLOSED_OPTION || risposta == JOptionPane.NO_OPTION)) {

				String idPersonale = FormGestisciRelazioni.this.idPersonale.get();
				String idStrumentazione = FormGestisciRelazioni.this.idStrumentazione.get();
				
				aggiungiRelazione(idPersonale, idStrumentazione);
			}
		}
		else if (e.getActionCommand().equals("Elimina")) {
			
			int risposta = JOptionPane.showConfirmDialog(null, "Sicuro?", "Conferma", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if (!(risposta == JOptionPane.CLOSED_OPTION || risposta == JOptionPane.NO_OPTION)) {

				String idPersonale = FormGestisciRelazioni.this.idPersonale.get();
				String idStrumentazione = FormGestisciRelazioni.this.idStrumentazione.get();
				
				eliminaRelazione(idPersonale, idStrumentazione);
			}
		}
	}
	
	private void aggiungiRelazione(String idPers, String idStru) {
	
		boolean inputValido = true;
		inputValido &= idPers.matches(regexQualsiasiNumero);
		inputValido &= idStru.matches(regexQualsiasiNumero);
		if (inputValido) {
			try {
				Database dbElementi = new Database();
				String queryInserisci = "INSERT INTO Utilizzo (idPersonale, idStrumentazione) VALUES (" + idPers + ", " + idStru + ")";
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
	
	private void eliminaRelazione(String idPers, String idStru) {
		
		boolean inputValido = true;
		inputValido &= idPers.matches(regexQualsiasiNumero);
		inputValido &= idStru.matches(regexQualsiasiNumero);
		if (inputValido) {
			try {
				Database dbElementi = new Database();
				String queryElimina = "DELETE FROM Utilizzo WHERE idPersonale = " + idPers + " AND idStrumentazione = " + idStru;
				dbElementi.eseguiQuery(queryElimina);
			} catch (ClassNotFoundException | SQLException | IOException e1) {;}
		}
		
		try {
			v.caricaPannelloDati();
		} catch (ClassNotFoundException | IOException e1) {;}
	}
}
