package gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PannelloGestisciRelazioni extends JPanel {
	
	public PannelloGestisciRelazioni() {
		
		super();
		
		setLayout(new BorderLayout());
		
		TabellaRelazioni tRel = new TabellaRelazioni();
		FormGestisciRelazioni fRel = new FormGestisciRelazioni(tRel);
		
		add(tRel, BorderLayout.CENTER);
		add(fRel, BorderLayout.SOUTH);
	}
}
