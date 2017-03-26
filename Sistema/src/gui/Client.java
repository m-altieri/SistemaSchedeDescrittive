package gui;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Client extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String user;
	private boolean admin;
	
	public Client(String user, boolean admin) {
		
		super();
		
		setIconImage(new ImageIcon("icon.png").getImage());
		setTitle("Sistema schede descrittive");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setExtendedState(JFrame.MAXIMIZED_BOTH);
		getContentPane().setLayout(new BorderLayout());
		
		this.user = user;
		this.admin = admin;
		
		BarraMenu menu = new BarraMenu(this);
		setJMenuBar(menu);
	}
	
	String getUser() {
		return user;
	}
	
	boolean getAdmin() {
		return admin;
	}
}
