package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import database.Database;
import entità.Personale;
import entità.Spazio;
import entità.Strumentazione;

/**
 * Finestra della GUI per eliminare elementi dal database.
 */
public class PannelloEliminaDati extends PannelloGestisciDati {

	private static final long serialVersionUID = 1L;
	private TabellaDati tPersonale;
	private TabellaDati tStrumentazione;
	private TabellaDati tSpazio;
	private JPanel form;
	private CampoCredenziale id;
	private JButton cmdConferma;
	
	/**
	 * Crea la finestra e inizializza tutti i suoi componenti.
	 */
	public PannelloEliminaDati() {
		
		super();
		
		tPersonale = new TabellaDati(Personale.class);
		tStrumentazione = new TabellaDati(Strumentazione.class);
		tSpazio = new TabellaDati(Spazio.class);
		
		form = new JPanel();
		id = new CampoCredenziale("ID");
		cmdConferma = new JButton("Elimina");
		cmdConferma.setActionCommand("Conferma");
		cmdConferma.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					if (id.getText().equals("") || id.getText().equals("ID")) {
						throw new InputInvalidoException(null);
					}
					
					int risposta = JOptionPane.showConfirmDialog(null, "Sicuro? Eventuali dati referenziati verranno rimossi", "Conferma", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if (!(risposta == JOptionPane.CLOSED_OPTION || risposta == JOptionPane.NO_OPTION)) {
						eliminaElemento(id.getText());
					}
					
				} catch (/*InputInvalidoException*/ Exception e1) {e1.printStackTrace();}
			}
			
		});
		form.setLayout(new FlowLayout());
		form.add(id);
		form.add(cmdConferma);
		
		this.add(form, BorderLayout.SOUTH);
	}
	/**
	 * Ridefinisce il comportamento dei radio buttons contenuti nella superclass PannelloGestioneDati.
	 * In base al radio button selezionato mostra la tabella corretta.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		rimuoviPannelli();

		switch (e.getActionCommand()) {
		case "Personale":
			tPersonale = new TabellaDati(Personale.class);
			add(tPersonale, BorderLayout.CENTER);
			break;
		case "Strumentazione":
			tStrumentazione = new TabellaDati(Strumentazione.class);
			add(tStrumentazione, BorderLayout.CENTER);
			break;
		case "Spazio":
			tSpazio = new TabellaDati(Spazio.class);
			add(tSpazio, BorderLayout.CENTER);
			break;
		default:
			break;
		}
		
		this.paintAll(this.getGraphics());
	}
	
	private void rimuoviPannelli() {
		
		remove(tPersonale);
		remove(tStrumentazione);
		remove(tSpazio);
	}
	
	private void eliminaElemento(String id) {

		Database dbElementi = null;
		String query = null;
		
		String tabella = null;
		if (super.rdPersonale.isSelected())
			tabella = "Personale";
		else if (super.rdStrumentazione.isSelected())
			tabella = "Strumentazione";
		else
			tabella = "Spazio";
		
		try {
			dbElementi = new Database();
			query = "DELETE FROM " + tabella + " WHERE id = " + Integer.parseInt(id);
			dbElementi.eseguiQuery(query);
		} catch (Exception f) {
			JOptionPane.showMessageDialog(null, "Elemento presente in una relazione", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		
		try {
			if (tabella.equals("Personale"))
				tPersonale.caricaPannelloDati();
			else if (tabella.equals("Strumentazione"))
				tStrumentazione.caricaPannelloDati();
			else
				tSpazio.caricaPannelloDati();
		} catch (ClassNotFoundException | IOException e) {}
		
		this.paintAll(getGraphics());
	}

}
