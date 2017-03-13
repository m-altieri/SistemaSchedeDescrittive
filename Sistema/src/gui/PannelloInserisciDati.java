package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

public class PannelloInserisciDati extends PannelloGestioneDati {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TabellaPersonale tPersonale;
	private TabellaStrumentazione tStrumentazione;
	private TabellaSpazio tSpazio;
	private FormInserisciPersonale fPersonale;
	private FormInserisciStrumentazione fStrumentazione;
	private FormInserisciSpazio fSpazio;
	
	public PannelloInserisciDati() {
		
		super();
		
		tPersonale = new TabellaPersonale();
		tStrumentazione = new TabellaStrumentazione();
		tSpazio = new TabellaSpazio();
		fPersonale = new FormInserisciPersonale();
		fStrumentazione = new FormInserisciStrumentazione();
		fSpazio = new FormInserisciSpazio();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals("Personale")) {
			
			this.add(tPersonale, BorderLayout.CENTER);
			this.add(fPersonale, BorderLayout.SOUTH);
			this.paintAll(this.getGraphics());
		}
		
		if (e.getActionCommand().equals("Strumentazione")) {
			
			this.add(tStrumentazione, BorderLayout.CENTER);
			this.add(fStrumentazione, BorderLayout.SOUTH);
			this.paintAll(this.getGraphics());
		}
		
		if (e.getActionCommand().equals("Spazio")) {
			
			this.add(tSpazio, BorderLayout.CENTER);
			this.add(fSpazio, BorderLayout.SOUTH);
			this.paintAll(this.getGraphics());
		}
		
	}
}
