package gui;

import javax.swing.JOptionPane;

public class UtenteGi‡EsistenteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UtenteGi‡EsistenteException(String nome) {
		
		JOptionPane.showMessageDialog(null, "L'utente " + nome + " esiste gi‡", "Errore", JOptionPane.ERROR_MESSAGE);
	}
}
