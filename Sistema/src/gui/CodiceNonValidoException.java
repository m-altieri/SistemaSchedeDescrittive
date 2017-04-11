package gui;

import javax.swing.JOptionPane;

/**
 * Eccezione lanciata quando il codice admin inserito non � esatto.
 */
@SuppressWarnings("serial")
public class CodiceNonValidoException extends Exception {

	/**
	 * Lancia l'eccezione senza specificare il codice inserito.
	 */
	public CodiceNonValidoException() {
		
		this("");
	}

	/**
	 * Lancia l'eccezione specificando il codice inserito. Il messaggio sar� pi� significativo per l'utente.
	 * @param codice Il codice inserito.
	 */
	public CodiceNonValidoException(String codice) {
		
		super();
		
		JOptionPane.showMessageDialog(null, "Il codice " + codice + " non � valido, assicurarsi che contenga esattamente 8 caratteri", "Errore", JOptionPane.ERROR_MESSAGE);
	}
}
