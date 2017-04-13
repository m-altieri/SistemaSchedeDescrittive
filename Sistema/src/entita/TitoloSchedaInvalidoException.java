package entita;

import javax.swing.JOptionPane;

/**
 * Eccezione lanciata durante la produzione delle schede, quando il nome inserito non � valido.
 * Se viene inserito il nome di una scheda gi� esistente, normalmente il sistema dar� alla nuova scheda un nome
 * leggermente diverso a seconda della data e dell'ora; tuttavia se nonostante ci� i nomi sono ancora uguali,
 * questa eccezione verr� sollevata.
 */
public class TitoloSchedaInvalidoException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Lancia l'eccezione. Viene mostrata una finestra di dialogo.
	 */
	public TitoloSchedaInvalidoException() {
		
		super();
		
		JOptionPane.showMessageDialog(null, "Impossibile creare la scheda.", "Errore", JOptionPane.ERROR_MESSAGE);
	}
}
