package entit�;

import javax.swing.JOptionPane;

public class TitoloSchedaInvalidoException extends Exception {

	private static final long serialVersionUID = 1L;

	public TitoloSchedaInvalidoException() {
		
		super();
		
		JOptionPane.showMessageDialog(null, "Impossibile creare la scheda. Questo nome esiste gi�.", "Errore", JOptionPane.ERROR_MESSAGE);
	}
}
