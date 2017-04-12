package gui;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * Finestra della GUI principale, da cui si può accedere a tutte le funzioni del sistema.
 * E' leggermente diversa in base se l'utente è un amministratore o meno.
 */
@SuppressWarnings("serial")
public class Client extends JFrame {

	private String user;
	private boolean admin;
	
	/**
	 * Crea la finestra e inizializza tutti i suoi componenti.
	 * @param user L'username dell'utente loggato.
	 * @param admin Flag che indica se l'utente loggato è un amministratore.
	 */
	public Client(String user, boolean admin) {
		
		super();
		
		setIconImage(new ImageIcon("icon.png").getImage());
		setTitle("Sistema Schede Descrittive");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setExtendedState(Frame.MAXIMIZED_BOTH);
		getContentPane().setLayout(new BorderLayout());
		
		this.user = user;
		this.admin = admin;
		
		BarraMenu menu = new BarraMenu(this);
		setJMenuBar(menu);
	}
	
	/**
	 * Metodo get dell'username.
	 * @return L'username dell'utente loggato.
	 */
	String getUser() {
		return user;
	}
	
	/**
	 * Metodo get del flag admin.
	 * @return true se l'utente loggato è un amministratore, false altrimenti.
	 */
	boolean getAdmin() {
		return admin;
	}
}
