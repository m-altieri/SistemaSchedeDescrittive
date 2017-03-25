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
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setTitle("Manuale utente");
		setResizable(false);
		this.setIconImage(new ImageIcon("icon.png").getImage());
		setLocation(500, 100);
		setSize(1000, 800);
		
		JPanel manuale = new JPanel();
		manuale.setPreferredSize(new Dimension(800, 1600));
		JScrollPane scrollFrame = new JScrollPane(manuale);
		manuale.setAutoscrolls(true);
		scrollFrame.setPreferredSize(new Dimension(800, 800));
		this.add(scrollFrame);
	}
}
