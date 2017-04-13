package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;

import entita.Personale;
import entita.Spazio;
import entita.Strumentazione;

/**
 * Finestra della GUI per visualizzare gli elementi del database.
 */
@SuppressWarnings("serial")
public class PannelloVisualizzaDati extends PannelloGestisciDati {

	private TabellaDati tPersonale;
	private TabellaDati tStrumentazione;
	private TabellaDati tSpazio;

	/**
	 * Crea la finestra e inizializza tutti i suoi componenti.
	 */
	public PannelloVisualizzaDati() {
		
		super();

		tPersonale = new TabellaDati(Personale.class);
		tStrumentazione = new TabellaDati(Strumentazione.class);
		tSpazio = new TabellaDati(Spazio.class);
	}
	
	/**
	 * Ricarica tutte le tabelle di visualizzazione degli elementi.
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void aggiorna() throws ClassNotFoundException, IOException {
		
		tPersonale.caricaPannelloDati();
		tStrumentazione.caricaPannelloDati();
		tSpazio.caricaPannelloDati();
	}

	/**
	 * Ridefinisce il comportamento dei radio buttons contenuti nella superclass PannelloGestioneDati.
	 * In base al radio button selezionato mostra la tabella corretta.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Personale")) {
			
			this.remove(tStrumentazione);
			this.remove(tSpazio);
			this.add(tPersonale, BorderLayout.CENTER);
			this.paintAll(this.getGraphics());
		}
		
		if (e.getActionCommand().equals("Strumentazione")) {
			
			this.remove(tPersonale);
			this.remove(tSpazio);
			this.add(tStrumentazione, BorderLayout.CENTER);
			this.paintAll(this.getGraphics());
		}
		
		if (e.getActionCommand().equals("Spazio")) {
			
			this.remove(tPersonale);
			this.remove(tStrumentazione);
			this.add(tSpazio, BorderLayout.CENTER);
			this.paintAll(this.getGraphics());
		}
		
	}
}
