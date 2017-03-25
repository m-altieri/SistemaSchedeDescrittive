package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import database.Database;

public class FinestraCambiaPassword extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPasswordField pass;
	private JButton conferma;
	private String user;
	
	public FinestraCambiaPassword(String user) {
		
		super();
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setTitle("Cambia password");
		setResizable(false);
		this.setIconImage(new ImageIcon("icon.png").getImage());
		setSize(600, 150);
		setLocation(600, 300);
		setLayout(new FlowLayout());
		
		this.user = user;
		
		pass = new JPasswordField("Password");
		pass.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {

				String password = "";
				for (int i = 0; i < pass.getPassword().length; i++) {
					password += pass.getPassword()[i];
				}
				if (password.equals("Password"))
					pass.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {}
			
		});
		pass.setPreferredSize(new Dimension(200, 40));
		
		conferma = new JButton("Conferma");
		conferma.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					Database dbUtility = new Database(true);
					String password = "";
					
					for (int i = 0; i < pass.getPassword().length; i++)
						password += pass.getPassword()[i];

					String query = "UPDATE Utente SET password = '" + password + "' WHERE nomeUtente = '" + FinestraCambiaPassword.this.user + "'";
					dbUtility.eseguiQuery(query);
					
					dispose();
					JOptionPane.showMessageDialog(null, "Password cambiata con successo", "Successo", JOptionPane.INFORMATION_MESSAGE);
				} catch (ClassNotFoundException | IOException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		conferma.setPreferredSize(new Dimension(150, 40));
		
		add(new JLabel("Nuova password"));
		add(pass);
		add(conferma);
	}
}
