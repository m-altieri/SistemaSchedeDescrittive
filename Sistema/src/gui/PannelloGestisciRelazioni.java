package gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class PannelloGestisciRelazioni extends JPanel {

	private static final long serialVersionUID = 1L;
	private TabellaRelazioni tRel;
	private FormGestisciRelazioni fRel;
	
	public PannelloGestisciRelazioni() {
		
		super();
		
		setLayout(new BorderLayout());
		
		tRel = new TabellaRelazioni();
		fRel = new FormGestisciRelazioni(tRel);
		
		add(tRel, BorderLayout.CENTER);
		add(fRel, BorderLayout.SOUTH);
	}
}
