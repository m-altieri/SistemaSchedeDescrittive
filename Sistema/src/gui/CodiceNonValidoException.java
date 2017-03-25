package gui;

import javax.swing.JOptionPane;

public class CodiceNonValidoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CodiceNonValidoException(String codice) {
		
		JOptionPane.showMessageDialog(null, "Il codice " + codice + " non è valido, assicurarsi che contenga esattamente 8 caratteri", "Errore", JOptionPane.ERROR_MESSAGE);
	}
}
