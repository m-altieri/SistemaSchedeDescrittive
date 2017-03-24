package gui;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class InputInvalidoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InputInvalidoException(JComponent parent) {
		
		super();
		
		JOptionPane.showMessageDialog(parent, "ID non valido", "Errore", JOptionPane.ERROR_MESSAGE);
	}
}
