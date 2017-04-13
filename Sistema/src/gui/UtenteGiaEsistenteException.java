package gui;

import javax.swing.JOptionPane;

/**
 * Eccezione lanciata nel caso in cui si tenti di registrati un utente con lo stesso username di un altro già esistente.
 */
@SuppressWarnings("serial")
public class UtenteGiaEsistenteException extends Exception {

	/**
	 * Crea l'eccezione.
	 * @param nome Username inserito.
	 */
	public UtenteGiaEsistenteException(String nome) {

		JOptionPane.showMessageDialog(null, "L'utente " + nome + " esiste gia'", "Errore", JOptionPane.ERROR_MESSAGE);
	}
}
