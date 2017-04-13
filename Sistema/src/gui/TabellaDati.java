package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import database.Database;
import entita.Elemento;

/**
 * Costituisce la tabella contenente tutti gli elementi di un certo tipo per permettere la visualizzazione all'utente.
 */
public class TabellaDati extends JPanel implements Visualizzatore {

	private static final long serialVersionUID = 1L;
	private JPanel pannelloColonne;
	private JPanel pannelloDati;
	private ArrayList<String> colonne;
	private Font fontColonne;
	private Font fontDati;
	private Class<? extends Elemento> tipo;
	
	/**
	 * Crea la struttura della tabella e inserisce le colonne e i dati.
	 * @param tipo La classe corrispondente al database da cui estrarre i dati (Personale, Strumentazione o Spazio)
	 */
	public TabellaDati(Class<? extends Elemento> tipo) {
		
		super();
		
		this.tipo = tipo;
		
		pannelloColonne = new JPanel();
		pannelloDati = new JPanel();
		fontColonne = new Font("Arial", Font.BOLD, 20);
		fontDati = new Font("Arial", Font.PLAIN, 14);
		
		try {
			colonne = ottieniColonne();
		} catch (SQLException e) {;}
		this.setLayout(new BorderLayout());
		
		pannelloColonne.setLayout(new GridLayout(1, colonne.size()));
		pannelloDati.setLayout(new GridLayout(0, colonne.size()));
		
		int nColonne = colonne.size();
		for (int i = 0; i < nColonne; i++) {
			String c = colonne.get(i);
			c = c.toUpperCase();
			JLabel colonna = new JLabel(c);
			colonna.setFont(fontColonne);
			pannelloColonne.add(colonna);
		}

		try {
			caricaPannelloDati();
		} catch (ClassNotFoundException | IOException e) {;}
		
		this.add(pannelloColonne, BorderLayout.NORTH);
		this.add(pannelloDati, BorderLayout.CENTER);
	}
	
	/**
	 * Estrae l'insieme delle colonne dal database del personale, strumentazioni, o spazi a seconda dell'oggetto su cui viene invocato.
	 * @return Colonne del database.
	 * @throws SQLException
	 */
	private ArrayList<String> ottieniColonne() throws SQLException {
		
		ArrayList<String> colonne = new ArrayList<String>();
		Database db = null;
		try {
			db = new Database();
		} catch (ClassNotFoundException | IOException e) {;}
		
		ResultSet rs = null;
		try {
			rs = db.eseguiQueryRitorno("SELECT column_name FROM information_schema.columns WHERE table_name = '" + tipo.getSimpleName() + "'");
			while (rs.next()) {
				colonne.add(rs.getString(1));
			}
			
		} catch (SQLException e) {
			;
		} finally {
			rs.close();
		}
		return colonne;
	}
	
	/**
	 * NON usare questo metodo in modo pubblico.
	 * E' reso pubblico solo per consentire alla classe di essere conforme all'interfaccia Visualizzatore che implementa.
	 */
	@Override
	public void caricaPannelloDati() throws ClassNotFoundException, IOException {
		
		pannelloDati.removeAll();
		
		ArrayList<String> riga = null;
		Database elementi = null;
		ResultSet rs = null;
		
		try {
			elementi = new Database();
			String query = "SELECT * FROM " + tipo.getSimpleName() + " ORDER BY id";
			rs = elementi.eseguiQueryRitorno(query);
			while (rs.next()) {
				riga = new ArrayList<String>();
				int nColonne = colonne.size();
				for (int i = 0; i < nColonne; i++) {
					riga.add(rs.getString(i + 1));
				}
				
				int rigaSize = riga.size();
				for (int i = 0; i < rigaSize; i++) {
					JLabel dato = new JLabel(riga.get(i));
					dato.setFont(fontDati);
					pannelloDati.add(dato);
				}
			}
		} catch (SQLException e) {;}
		
		this.paintAll(this.getGraphics());
	}

}
