package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Superclasse astratta dei vari pannelli della GUI del menù Dati.
 * Contiene le caratteristiche comuni a quelle classi, come i radio buttons per scegliere il tipo di elemento.
 */
public abstract class PannelloGestisciDati extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	protected JRadioButton rdPersonale;
	protected JRadioButton rdStrumentazione;
	protected JRadioButton rdSpazio;

	/**
	 * Crea la finestra e inizializza tutti i suoi componenti.
	 * Non viene definito il comportamento dei radio buttons che dovrà quindi essere definito nelle sottoclassi.
	 */
	protected PannelloGestisciDati() {
		
		super();
		
		this.setLayout(new BorderLayout());

		final int GRANDEZZA_FONT_RADIOBUTTONS = 20;
		Font fontRadioButtons = new Font("Arial", Font.PLAIN, GRANDEZZA_FONT_RADIOBUTTONS);
		
		JPanel radioButtons = new JPanel();
		radioButtons.setLayout(new FlowLayout());
		rdPersonale = new JRadioButton("Personale");
		rdStrumentazione = new JRadioButton("Strumentazione");
		rdSpazio = new JRadioButton("Spazio");
		rdPersonale.setFont(fontRadioButtons);
		rdStrumentazione.setFont(fontRadioButtons);
		rdSpazio.setFont(fontRadioButtons);
		ButtonGroup buttons = new ButtonGroup();
		buttons.add(rdPersonale);
		buttons.add(rdStrumentazione);
		buttons.add(rdSpazio);
		
		radioButtons.add(rdPersonale);
		radioButtons.add(rdStrumentazione);
		radioButtons.add(rdSpazio);
		
		rdPersonale.addActionListener(this);
		rdStrumentazione.addActionListener(this);
		rdSpazio.addActionListener(this);
		
		add(radioButtons, BorderLayout.NORTH);
	}

}
