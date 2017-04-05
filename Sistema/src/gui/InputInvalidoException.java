package gui;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 * Eccezione lanciata quando i valori contenuti nei campi di testo per l'inserimento di un nuovo elemento o la 
 * modifica di uno esistente non sono validi.
 * Viene manifestata all'utente come una finestrella di errore.
 */
public class InputInvalidoException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Lancia l'eccezione.
	 * @param parent La finestra su cui mostrare la finestrella di errore.
	 */
	public InputInvalidoException(JComponent parent) {

		super();;
		
		JOptionPane.showMessageDialog(parent, "Input non valido", "Errore", JOptionPane.ERROR_MESSAGE);

	}
}
