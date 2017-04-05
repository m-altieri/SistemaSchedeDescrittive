package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;

import database.Database;

/**
 * Pannello contenente le relazioni tra gli elementi di Personale e Strumentazione.
 * Questa classe NON si occupa dell'inserimento, modifica, o eliminazione delle relazioni ma solo della loro visualizzazione.
 * @see FormGestisciRelazioni
 */
public class TabellaRelazioni extends JPanel implements Visualizzatore {

	private static final long serialVersionUID = 1L;
	private JPanel pannelloColonne;
	private JPanel pannelloDati;
	private Font fontColonne;
	private Font fontDati;
	
	public TabellaRelazioni() {
		
		super();
		
		setLayout(new BorderLayout());
		
		pannelloColonne = new JPanel();
		pannelloDati = new JPanel();
		fontColonne = new Font("Arial", Font.BOLD, 20);
		fontDati = new Font("Arial", Font.PLAIN, 14);
		
		pannelloColonne.setLayout(new GridLayout(1, 2));
		pannelloDati.setLayout(new GridLayout(0, 2));
		
		JLabel p = new JLabel("PERSONALE");
		p.setFont(fontColonne);
		pannelloColonne.add(p);
		JLabel s = new JLabel("STRUMENTAZIONI");
		s.setFont(fontColonne);
		pannelloColonne.add(s);
		
		try {
			caricaPannelloDati();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		this.add(pannelloColonne, BorderLayout.NORTH);
		this.add(pannelloDati, BorderLayout.CENTER);
	}
	
	@Override
	public void caricaPannelloDati() throws ClassNotFoundException, IOException {

		pannelloDati.removeAll();
		
		Database elementi = null;
		ResultSet rs = null;
		
		try {
			elementi = new Database();
			String query = "SELECT * FROM Utilizzo";
			rs = elementi.eseguiQueryRitorno(query);
			
			while (rs.next()) {
				String idPers = rs.getString(1);
				String idStru = rs.getString(2);
				
				String queryOttieniNomePers = "SELECT Nome, Cognome FROM Personale WHERE id = " + idPers;
				String queryOttieniNomeStru = "SELECT Nome FROM Strumentazione WHERE id = " + idStru;
				ResultSet nomePers = elementi.eseguiQueryRitorno(queryOttieniNomePers);
				nomePers.next();
				idPers += " - " + nomePers.getString(1) + " " + nomePers.getString(2);
				ResultSet nomeStru = elementi.eseguiQueryRitorno(queryOttieniNomeStru);
				nomeStru.next();
				idStru += " - " + nomeStru.getString(1);
				
				JLabel lblPers = new JLabel(idPers);
				JLabel lblStru = new JLabel(idStru);
				lblPers.setFont(fontDati);
				lblStru.setFont(fontDati);
				pannelloDati.add(lblPers);
				pannelloDati.add(lblStru);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.paintAll(this.getGraphics());
	}
}
