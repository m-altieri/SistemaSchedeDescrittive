package gui;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ManualeUtente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ManualeUtente() {
		
		super();

		final int LARGHEZZA_FINESTRA = 1000;
		final int ALTEZZA_FINESTRA = 800;
		final int POSIZIONE_FINESTRA_X = 500;
		final int POSIZIONE_FINESTRA_Y = 100;
		final int LARGHEZZA_PANNELLO = 800;
		final int ALTEZZA_PANNELLO = 1600;
		final int LARGHEZZA_SCROLLFRAME = 800;
		final int ALTEZZA_SCROLLFRAME = 800;

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setTitle("Manuale utente");
		setResizable(false);
		setIconImage(new ImageIcon("icon.png").getImage());
		setLocation(POSIZIONE_FINESTRA_X, POSIZIONE_FINESTRA_Y);
		setSize(LARGHEZZA_FINESTRA, ALTEZZA_FINESTRA);
		
		JPanel manuale = new JPanel();
		manuale.setPreferredSize(new Dimension(LARGHEZZA_PANNELLO, ALTEZZA_PANNELLO));
		JScrollPane scrollFrame = new JScrollPane(manuale);
		manuale.setAutoscrolls(true);
		scrollFrame.setPreferredSize(new Dimension(LARGHEZZA_SCROLLFRAME, ALTEZZA_SCROLLFRAME));
		add(scrollFrame);
	}
}
