package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import entità.Personale;
import entità.Spazio;
import entità.Strumentazione;

public class PannelloModificaDati extends PannelloGestioneDati {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VisualizzaTabella tPersonale;
	private VisualizzaTabella tStrumentazione;
	private VisualizzaTabella tSpazio;
	private FormModificaPersonale fPersonale;
	private FormModificaStrumentazione fStrumentazione;
	private FormModificaSpazio fSpazio;

	public PannelloModificaDati() {
		
		super();
		
		tPersonale = new VisualizzaTabella(Personale.class);
		tStrumentazione = new VisualizzaTabella(Strumentazione.class);
		tSpazio = new VisualizzaTabella(Spazio.class);
		fPersonale = new FormModificaPersonale(tPersonale);
		fStrumentazione = new FormModificaStrumentazione(tStrumentazione);
		fSpazio = new FormModificaSpazio(tSpazio);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals("Personale")) {
			
			this.remove(tStrumentazione);
			this.remove(tSpazio);
			
			this.add(tPersonale, BorderLayout.CENTER);
			this.add(fPersonale, BorderLayout.SOUTH);
			this.paintAll(this.getGraphics());
		}
		
		if (e.getActionCommand().equals("Strumentazione")) {
			
			this.remove(tPersonale);
			this.remove(tSpazio);
			
			this.add(tStrumentazione, BorderLayout.CENTER);
			this.add(fStrumentazione, BorderLayout.SOUTH);
			this.paintAll(this.getGraphics());
		}
		
		if (e.getActionCommand().equals("Spazio")) {
			
			this.remove(tPersonale);
			this.remove(tStrumentazione);
			
			this.add(tSpazio, BorderLayout.CENTER);
			this.add(fSpazio, BorderLayout.SOUTH);
			this.paintAll(this.getGraphics());
		}
		
		
	}
}
