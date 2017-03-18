package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;

import entità.Personale;
import entità.Spazio;
import entità.Strumentazione;

public class PannelloVisualizzaDati extends PannelloGestioneDati {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VisualizzaTabella tPersonale;
	private VisualizzaTabella tStrumentazione;
	private VisualizzaTabella tSpazio;

	public PannelloVisualizzaDati() {
		
		super();

		tPersonale = new VisualizzaTabella(Personale.class);
		tStrumentazione = new VisualizzaTabella(Strumentazione.class);
		tSpazio = new VisualizzaTabella(Spazio.class);
	}
	
	public void aggiorna() throws ClassNotFoundException, IOException {
		
		tPersonale.caricaPannelloDati(Personale.class);
		tStrumentazione.caricaPannelloDati(Strumentazione.class);
		tSpazio.caricaPannelloDati(Spazio.class);
	}

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
