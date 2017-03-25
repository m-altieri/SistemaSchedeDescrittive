package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import database.Database;

public class FinestraCambiaCodice extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CampoCredenziale codice;
	private JButton conferma;
	
	public FinestraCambiaCodice() {
		
		super();
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setTitle("Cambia codice admin");
		setResizable(false);
		this.setIconImage(new ImageIcon("icon.png").getImage());
		setSize(600, 150);
		setLocation(600, 300);
		setLayout(new FlowLayout());
		
		codice = new CampoCredenziale("Codice");
		codice.setLunghezza(200);
		conferma = new JButton("Conferma");
		conferma.setPreferredSize(new Dimension(150, 40));
		
		conferma.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					Database dbUtility = new Database(true);
					String query = "INSERT INTO CodiceAdmin (codice) VALUES ('" + codice.get() + "')";
					dbUtility.eseguiQuery(query);
					dispose();
					JOptionPane.showMessageDialog(null, "Nuovo codice: " + codice.get(), "Successo", JOptionPane.INFORMATION_MESSAGE);
				} catch (ClassNotFoundException | IOException | SQLException f) {
					f.printStackTrace();
				}
			}
			
		});
		
		add(new JLabel("Nuovo codice"));
		add(codice);
		add(conferma);
	}
}
