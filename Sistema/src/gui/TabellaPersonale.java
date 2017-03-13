package gui;

import java.awt.GridLayout;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import database.Database;

public class TabellaPersonale extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<String> colonne;
	private final int RIGHE_TABELLA = 20;
	
	public TabellaPersonale() {
		
		super();
		
		colonne = ottieniColonne();
		this.setLayout(new GridLayout(RIGHE_TABELLA, colonne.size()));
		
		System.out.println(colonne);
		
		for (int i = 0; i++ < 100; this.add(new JLabel(Integer.toString(i)))); //DEBUG
		
	}
	
	private ArrayList<String> ottieniColonne() {
		
		ArrayList<String> colonne = new ArrayList<String>();
		
		try {
			Database db = new Database();
//			db.usa(); //se lo metti dà errore "use statement is not supported to switch between databases"
			ResultSet rs = db.eseguiQueryRitorno("SELECT * FROM DBElementi.information_schema.columns WHERE table_name = 'Personale'");
			while (rs.next()) {
				colonne.add(rs.getString(1));
			}
			rs.close();
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return colonne;
	}
}
