package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import entita.Personale;
import entita.Spazio;
import entita.Strumentazione;

/**
 * Finestra della GUI per inserire nuovi elementi nel database.
 */
public class PannelloInserisciDati extends PannelloGestisciDati {

	private static final long serialVersionUID = 1L;
	private TabellaDati tPersonale;
	private TabellaDati tStrumentazione;
	private TabellaDati tSpazio;
	private FormInserisciDati fPersonale;
	private FormInserisciDati fStrumentazione;
	private FormInserisciDati fSpazio;
	
	/**
	 * Crea la finestra e inizializza tutti i suoi componenti.
	 */
	public PannelloInserisciDati() {
		
		super();
		
		tPersonale = new TabellaDati(Personale.class);
		tStrumentazione = new TabellaDati(Strumentazione.class);
		tSpazio = new TabellaDati(Spazio.class);
		fPersonale = new FormInserisciDati(Personale.class, tPersonale);
		fStrumentazione = new FormInserisciDati(Strumentazione.class, tStrumentazione);
		fSpazio = new FormInserisciDati(Spazio.class, tSpazio);
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
