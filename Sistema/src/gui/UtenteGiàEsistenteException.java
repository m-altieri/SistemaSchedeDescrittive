package gui;

import javax.swing.JOptionPane;

/**
 * Eccezione lanciata nel caso in cui si tenti di registrati un utente con lo stesso username di un altro gi‡ esistente.
 */
public class UtenteGi‡EsistenteException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Crea l'eccezione.
	 * @param nome Username inserito.
	 */
	public UtenteGi‡EsistenteException(String nome) {
		
		JOptionPane.showMessageDialog(null, "L'utente " + nome + " esiste gi‡", "Errore", JOptionPane.ERROR_MESSAGE);
	}
}
