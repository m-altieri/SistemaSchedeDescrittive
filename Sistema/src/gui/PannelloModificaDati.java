package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import entita.Personale;
import entita.Spazio;
import entita.Strumentazione;

/**
 * Finestra della GUI per modificare elementi già presenti nel database.
 */
public class PannelloModificaDati extends PannelloGestisciDati {

	private static final long serialVersionUID = 1L;

	private TabellaDati tPersonale;
	private TabellaDati tStrumentazione;
	private TabellaDati tSpazio;
	private FormModificaDati fPersonale;
	private FormModificaDati fStrumentazione;
	private FormModificaDati fSpazio;

	/**
	 * Crea la finestra e inizializza tutti i suoi componenti.
	 */
	public PannelloModificaDati() {
		
		super();
		
		tPersonale = new TabellaDati(Personale.class);
		tStrumentazione = new TabellaDati(Strumentazione.class);
		tSpazio = new TabellaDati(Spazio.class);
		fPersonale = new FormModificaDati(Personale.class, tPersonale);
		fStrumentazione = new FormModificaDati(Strumentazione.class, tStrumentazione);
		fSpazio = new FormModificaDati(Spazio.class, tSpazio);
	}

	/**
	 * Ridefinisce il comportamento dei radio buttons contenuti nella superclass PannelloGestioneDati.
	 * In base al radio button selezionato mostra la tabella e la form corretta.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		rimuoviPannelli();
		
		switch (e.getActionCommand()) {
		case "Personale":
			this.add(tPersonale, BorderLayout.CENTER);
			this.add(fPersonale, BorderLayout.SOUTH);
			break;
		case "Strumentazione":
			this.add(tStrumentazione, BorderLayout.CENTER);
			this.add(fStrumentazione, BorderLayout.SOUTH);
			break;
		case "Spazio":
			this.add(tSpazio, BorderLayout.CENTER);
			this.add(fSpazio, BorderLayout.SOUTH);
			break;
		default:
			break;
		}
		
		this.paintAll(this.getGraphics());
	}
	
	private void rimuoviPannelli() {
		
		remove(tPersonale);
		remove(fPersonale);
		remove(tStrumentazione);
		remove(fStrumentazione);
		remove(tSpazio);
		remove(fSpazio);
	}
}
