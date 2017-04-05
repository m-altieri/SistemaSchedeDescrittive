package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import entità.Personale;
import entità.Spazio;
import entità.Strumentazione;

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
		
		if (e.getActionCommand().equals("Personale")) {
			
			this.remove(tStrumentazione);
			this.remove(tSpazio);
			this.remove(fStrumentazione);
			this.remove(fSpazio);
			
			this.add(tPersonale, BorderLayout.CENTER);
			this.add(fPersonale, BorderLayout.SOUTH);
			this.paintAll(this.getGraphics());
		}
		
		if (e.getActionCommand().equals("Strumentazione")) {
			
			this.remove(tPersonale);
			this.remove(tSpazio);
			this.remove(fPersonale);
			this.remove(fSpazio);
			
			this.add(tStrumentazione, BorderLayout.CENTER);
			this.add(fStrumentazione, BorderLayout.SOUTH);
			this.paintAll(this.getGraphics());
		}
		
		if (e.getActionCommand().equals("Spazio")) {
			
			this.remove(tPersonale);
			this.remove(tStrumentazione);
			this.remove(fPersonale);
			this.remove(fStrumentazione);
			
			this.add(tSpazio, BorderLayout.CENTER);
			this.add(fSpazio, BorderLayout.SOUTH);
			this.paintAll(this.getGraphics());
		}
		
	}
}
