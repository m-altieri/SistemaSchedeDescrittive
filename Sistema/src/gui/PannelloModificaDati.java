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
	private FormModificaDati fPersonale;
	private FormModificaDati fStrumentazione;
	private FormModificaDati fSpazio;

	public PannelloModificaDati() {
		
		super();
		
		tPersonale = new VisualizzaTabella(Personale.class);
		tStrumentazione = new VisualizzaTabella(Strumentazione.class);
		tSpazio = new VisualizzaTabella(Spazio.class);
		fPersonale = new FormModificaDati(Personale.class, tPersonale);
		fStrumentazione = new FormModificaDati(Strumentazione.class, tStrumentazione);
		fSpazio = new FormModificaDati(Spazio.class, tSpazio);
	}

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
