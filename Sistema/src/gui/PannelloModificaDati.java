package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

public class PannelloModificaDati extends PannelloGestioneDati {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PannelloModificaDati() {
		
		super();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals("Personale")) {
			
			this.add(new FormModificaPersonale(), BorderLayout.SOUTH);
		}
		
		if (e.getActionCommand().equals("Strumentazione")) {
			
			this.add(new FormModificaStrumentazione(), BorderLayout.SOUTH);
		}
		
		if (e.getActionCommand().equals("Spazio")) {
			
			this.add(new FormModificaSpazio(), BorderLayout.SOUTH);
		}
		
	}
}
