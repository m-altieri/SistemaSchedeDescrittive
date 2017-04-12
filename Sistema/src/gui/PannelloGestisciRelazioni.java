package gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

/**
 * Pannello della GUI per gestire le relazioni tra gli elementi di Personale e Strumentazioni.
 * In particolare gestisce la relazione molti a molti che esiste tra essi -- è possibile assegnare più strumentazioni a ogni
 * personale e più personali a ogni strumentazione.
 */
@SuppressWarnings("serial")
public class PannelloGestisciRelazioni extends JPanel {
	
	/**
	 * Crea il pannello e inizializza tutte le sue componenti.
	 */
	public PannelloGestisciRelazioni() {
		
		super();
		
		setLayout(new BorderLayout());
		
		TabellaRelazioni tRel = new TabellaRelazioni();
		FormGestisciRelazioni fRel = new FormGestisciRelazioni(tRel);
		
		add(tRel, BorderLayout.CENTER);
		add(fRel, BorderLayout.SOUTH);
	}
}
