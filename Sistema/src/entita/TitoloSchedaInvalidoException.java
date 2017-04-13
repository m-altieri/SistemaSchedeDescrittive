package entita;

import javax.swing.JOptionPane;

/**
 * Eccezione lanciata durante la produzione delle schede, quando il nome inserito non è valido.
 * Se viene inserito il nome di una scheda già esistente, normalmente il sistema darà alla nuova scheda un nome
 * leggermente diverso a seconda della data e dell'ora; tuttavia se nonostante ciò i nomi sono ancora uguali,
 * questa eccezione verrà sollevata.
 */
@SuppressWarnings("serial")
public class TitoloSchedaInvalidoException extends Exception {

	/**
	 * Lancia l'eccezione. Viene mostrata una finestra di dialogo.
	 */
	public TitoloSchedaInvalidoException() {
		
		super();
		
		JOptionPane.showMessageDialog(null, "Impossibile creare la scheda.", "Errore", JOptionPane.ERROR_MESSAGE);
	}
}
