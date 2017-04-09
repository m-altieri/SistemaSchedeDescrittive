package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Finestra della GUI che mostra il manuale utente.
 * Contiene sia informazioni generali sul sistema, sia una guida su come utilizzarlo e altri dettagli sul progetto.
 */
public class ManualeUtente extends JFrame {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Crea la finestra e inizializza tutti i suoi componenti.
	 */
	public ManualeUtente() {
		
		super();

		final int LARGHEZZA_FINESTRA = 530;
		final int ALTEZZA_FINESTRA = 800;
		final int POSIZIONE_FINESTRA_X = 500;
		final int POSIZIONE_FINESTRA_Y = 100;
		final int LARGHEZZA_PANNELLO = 500;
		final int ALTEZZA_PANNELLO = 2600;
		final int LARGHEZZA_SCROLLFRAME = 500;
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
		
		JTextArea text = new JTextArea();
		text.setBackground(new Color(238, 238, 238));
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		text.setFont(new Font("Arial", Font.PLAIN, 18));
		text.setSize(new Dimension(LARGHEZZA_SCROLLFRAME, ALTEZZA_SCROLLFRAME));
		
		Scanner s = null;
		try {
			s = new Scanner(new File("files/ManualeUtente.txt"));
			
			while (s.hasNextLine()) {
				text.setText(text.getText() + s.nextLine() + "\n");
			}
			
		} catch (FileNotFoundException e) {
			
			JOptionPane.showMessageDialog(null, "Impossibile trovare il file manuale.txt, assicurarsi che sia presente nella cartella files", "Errore", JOptionPane.ERROR_MESSAGE);
		} finally {
			s.close();
		}
		
		text.setEditable(false);
		manuale.add(text);
	}
}
