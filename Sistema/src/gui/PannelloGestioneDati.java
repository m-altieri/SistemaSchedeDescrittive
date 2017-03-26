package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public abstract class PannelloGestioneDati extends JPanel implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JRadioButton rdPersonale;
	protected JRadioButton rdStrumentazione;
	protected JRadioButton rdSpazio;

	public PannelloGestioneDati() {
		
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
		
		this.add(radioButtons, BorderLayout.NORTH);

	}

}
