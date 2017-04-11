package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

/**
 * Estende la classe base del campo di testo, e la arricchisce di dettagli grafici utili al sistema, 
 * come un hint (testo che appare inizialmente e che svanisce quando la barra acquisisce il focus), e metodi per
 * regolare la grandezza.
 */
@SuppressWarnings("serial")
public class CampoCredenziale extends JTextField {

	private String hint = "";
	final int ALTEZZA = 40;

	/**
	 * Restituisce il testo della barra.
	 * @return Il testo della barra.
	 */
	public String get() {
		
		return this.getText();
	}
	
	private void initText(String x) {
		
		setText(x);
	}
	
	/**
	 * Inizializza la barra con le dimensioni di default e imposta sia il testo che l'hint come il parametro passato.
	 * @param x
	 */
	public CampoCredenziale(String x) {
		
		super();

		final int LARGHEZZA = 400;
		
		setPreferredSize(new Dimension(LARGHEZZA, ALTEZZA));
		setLayout(new FlowLayout());
		setForeground(Color.GRAY);
		
		initText(x);
		hint = x; 
		
		addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				if (getText().equals(hint)) {
					initText("");
					setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {

				if (getText().isEmpty()) {
					initText(hint);
					setForeground(Color.GRAY);
				}
			}
			
		});

	}
	
	/**
	 * Imposta la lunghezza della barra.
	 * @param x La lunghezza da impostare.
	 */
	void setLunghezza(int x) {
		
		setPreferredSize(new Dimension(x, ALTEZZA));
	}
	
	/**
	 * Imposta l'hint della barra (il testo grigio opaco che svanisce quando la barra acquisisce il focus).
	 * @param x L'hint da impostare.
	 */
	void setHint(String x) {
		
		setText(x);
		hint = x;
	}
	
	/**
	 * Resituisce l'hint.
	 * @return L'hint.
	 */
	String getHint() {
		
		return hint;
	}
}
