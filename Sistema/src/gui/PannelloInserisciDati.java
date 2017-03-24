package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import entità.Personale;
import entità.Spazio;
import entità.Strumentazione;

public class PannelloInserisciDati extends PannelloGestioneDati {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VisualizzaTabella tPersonale;
	private VisualizzaTabella tStrumentazione;
	private VisualizzaTabella tSpazio;
	private FormInserisciPersonale fPersonale;
	private FormInserisciStrumentazione fStrumentazione;
	private FormInserisciSpazio fSpazio;
	
	public PannelloInserisciDati() {
		
		super();
		
		tPersonale = new VisualizzaTabella(Personale.class);
		tStrumentazione = new VisualizzaTabella(Strumentazione.class);
		tSpazio = new VisualizzaTabella(Spazio.class);
		fPersonale = new FormInserisciPersonale(tPersonale);
		fStrumentazione = new FormInserisciStrumentazione(tStrumentazione);
		fSpazio = new FormInserisciSpazio(tSpazio);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals("Personale")) {
			
			this.remove(tStrumentazione);
			this.remove(tSpazio);
			this.remove(fStrumentazione);
			this.remove(fSpazio);
			
//			tPersonale = new VisualizzaTabella(Personale.class);
//			fPersonale = new FormInserisciPersonale(tPersonale);
			this.add(tPersonale, BorderLayout.CENTER);
			this.add(fPersonale, BorderLayout.SOUTH);
			this.paintAll(this.getGraphics());
		}
		
		if (e.getActionCommand().equals("Strumentazione")) {
			
			this.remove(tPersonale);
			this.remove(tSpazio);
			this.remove(fPersonale);
			this.remove(fSpazio);
			
//			tStrumentazione = new VisualizzaTabella(Strumentazione.class);
//			fStrumentazione = new FormInserisciStrumentazione(tStrumentazione);
			this.add(tStrumentazione, BorderLayout.CENTER);
			this.add(fStrumentazione, BorderLayout.SOUTH);
			this.paintAll(this.getGraphics());
		}
		
		if (e.getActionCommand().equals("Spazio")) {
			
			this.remove(tPersonale);
			this.remove(tStrumentazione);
			this.remove(fPersonale);
			this.remove(fStrumentazione);
			
//			tSpazio = new VisualizzaTabella(Spazio.class);
//			fSpazio = new FormInserisciSpazio(tSpazio);
			this.add(tSpazio, BorderLayout.CENTER);
			this.add(fSpazio, BorderLayout.SOUTH);
			this.paintAll(this.getGraphics());
		}
		
	}
}
