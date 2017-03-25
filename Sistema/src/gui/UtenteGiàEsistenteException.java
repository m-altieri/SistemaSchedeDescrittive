package gui;

import javax.swing.JOptionPane;

public class UtenteGi‡EsistenteException extends Exception {

	public UtenteGi‡EsistenteException(String nome) {
		
		JOptionPane.showMessageDialog(null, "L'utente " + nome + " esiste gi‡", "Errore", JOptionPane.ERROR_MESSAGE);
	}
}
