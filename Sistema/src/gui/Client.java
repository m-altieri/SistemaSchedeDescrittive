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
	
	private String user;
	private boolean admin;
	
	public Client(String user, boolean admin) {
		
		super();
		
		this.setIconImage(new ImageIcon("icon.png").getImage());
		this.setTitle("Sistema schede descrittive");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.getContentPane().setLayout(new BorderLayout());
		
		this.user = user;
		this.admin = admin;
		
		menu = new BarraMenu(this);
		this.setJMenuBar(menu);
	}
	
	String getUser() {
		return user;
	}
	
	boolean getAdmin() {
		return admin;
	}
}
