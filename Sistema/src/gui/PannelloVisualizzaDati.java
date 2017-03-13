package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

public class PannelloVisualizzaDati extends PannelloGestioneDati {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PannelloVisualizzaDati() {
		
		super();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Personale")) {
			
			this.add(new TabellaPersonale(), BorderLayout.CENTER);
			this.paintAll(this.getGraphics());
		}
		
		if (e.getActionCommand().equals("Strumentazione")) {
			
			this.add(new TabellaStrumentazione(), BorderLayout.CENTER);
		}
		
		if (e.getActionCommand().equals("Spazio")) {
			
			this.add(new TabellaSpazio(), BorderLayout.CENTER);
		}
		
	}
}
