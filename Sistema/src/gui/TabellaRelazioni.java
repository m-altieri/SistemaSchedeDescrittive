package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.sql.PreparedStatement;
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
@SuppressWarnings("serial")
public class TabellaRelazioni extends JPanel implements Visualizzatore {

	private JPanel pannelloDati;
	private Font fontDati;
	
	public TabellaRelazioni() {
		
		super();
		
		setLayout(new BorderLayout());
		
		JPanel pannelloColonne = new JPanel();
		pannelloDati = new JPanel();
		final int sizeFontColonne = 20;
		Font fontColonne = new Font("Arial", Font.BOLD, sizeFontColonne);
		final int sizeFontDati = 14;
		fontDati = new Font("Arial", Font.PLAIN, sizeFontDati);
		
		final int righeAttributi = 1;
		final int colonneAttributi = 2;
		pannelloColonne.setLayout(new GridLayout(righeAttributi, colonneAttributi));
		final int righeDati = 0; // 0 perchè in GridLayout 0 significa quante ne sono necessarie
		final int colonneDati = 2;
		pannelloDati.setLayout(new GridLayout(righeDati, colonneDati));
		
		JLabel p = new JLabel("PERSONALE");
		p.setFont(fontColonne);
		pannelloColonne.add(p);
		JLabel s = new JLabel("STRUMENTAZIONI");
		s.setFont(fontColonne);
		pannelloColonne.add(s);
		
		caricamentoDati();
		
		this.add(pannelloColonne, BorderLayout.NORTH);
		this.add(pannelloDati, BorderLayout.CENTER);
	}
	
	private void caricamentoDati() {
		
		try {
			caricaPannelloDati();
		} catch (ClassNotFoundException | IOException e) {;}
	}
	
	/**
	 * Carica il pannello con tutte le relazioni tra gli elementi di Personale e Strumentazioni.
	 * Per ogni elementi viene caricato l'id, inoltre per il personale viene caricato anche il nome e il cognome, e 
	 * per le strumentazioni solo il nome.
	 */
	@Override
	public void caricaPannelloDati() throws ClassNotFoundException, IOException {

		pannelloDati.removeAll();
		
		Database elementi = null;
		ResultSet rs = null;
		ResultSet nomePers = null;
		ResultSet nomeStru = null;
		
		try {
			elementi = new Database();
			String query = "SELECT * FROM Utilizzo";
			rs = elementi.eseguiQueryRitorno(query);
			
			while (rs.next()) {
				final int colonnaIdPers = 1;
				final int colonnaIdStru = 2;
				String idPers = rs.getString(colonnaIdPers);
				String idStru = rs.getString(colonnaIdStru);
				
				String queryOttieniNomePers = "SELECT Nome, Cognome FROM Personale WHERE id = ?";
				PreparedStatement psPers = elementi.preparaQuery(queryOttieniNomePers);
				psPers.setString(1, idPers);
				
				String queryOttieniNomeStru = "SELECT Nome FROM Strumentazione WHERE id = ?";
				PreparedStatement psStru = elementi.preparaQuery(queryOttieniNomeStru);
				psStru.setString(1, idStru);
				
				final int colonnaNomePers = 1;
				final int colonnaCognomePers = 2;
				final int colonnaNomeStru = 1;
				nomePers = elementi.eseguiQueryPreparataRitorno(psPers);
				nomePers.next();
				idPers = idPers.concat(" - " + nomePers.getString(colonnaNomePers) + " " + nomePers.getString(colonnaCognomePers));
				nomeStru = elementi.eseguiQueryPreparataRitorno(psStru);
				nomeStru.next();
				idStru = idStru.concat(" - " + nomeStru.getString(colonnaNomeStru));
				
				JLabel lblPers = new JLabel(idPers);
				JLabel lblStru = new JLabel(idStru);
				lblPers.setFont(fontDati);
				lblStru.setFont(fontDati);
				pannelloDati.add(lblPers);
				pannelloDati.add(lblStru);
			}
		} catch (SQLException e) {
			;
		} finally {
			chiudiResultSets(rs, nomePers, nomeStru);
		}
		
		this.paintAll(this.getGraphics());
	}
	
	/**
	 * Metodo di servizio strettamente collegato a caricaPannelloDati().
	 * Chiude le risorse ResultSet passate come argomento.
	 * @param resultSets
	 * @see public void caricaPannelloDati() throws ClassNotFoundException, IOException
	 */
	private void chiudiResultSets(ResultSet... resultSets) {
		
		try {
			for (ResultSet rs : resultSets) {
				if (rs != null) {
					rs.close();
				}
			}
		} catch (SQLException e) {;}

	}
}
