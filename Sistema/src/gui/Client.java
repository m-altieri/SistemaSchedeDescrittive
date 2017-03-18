package gui;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Client extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BarraMenu menu;
	
	public Client() {
		
		super();
		
		this.setIconImage(new ImageIcon("icon.png").getImage());
		this.setTitle("Sistema schede descrittive");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.getContentPane().setLayout(new BorderLayout());
		
		menu = new BarraMenu(this);
		this.setJMenuBar(menu);
	}
}
