package entità;

import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import database.Database;

/**
 * Entità strumentazione. Può essere contenuto nelle schede descrittive.
 */
public class Strumentazione extends Elemento {

	private String nome;
	private String modello;
	private String marca;
	private String tipologia;
	private int annoAcquisto;
	
	private int idSpazio;
	
	/**
	 * Inizializza la strumentazione con tutti i campi vuoti.
	 * Usare questo costruttore con l'accortezza di settare successivamente tutti i campi.
	 */
	public Strumentazione() {
		
		this("", "", "", "", 0);
	}

	/**
	 * Inizializza la strumentazione riempiendo tutti i campi tranne l'id dello spazio occupato, che è opzionale.
	 * Eventualmente settarlo a parte in un secondo momento.
	 * @param nome Il nome.
	 * @param modello Il modello.
	 * @param marca La marca.
	 * @param tipologia La tipologia.
	 * @param annoAcquisto L'anno di acquisto.
	 */
	public Strumentazione(String nome, String modello, String marca, String tipologia, int annoAcquisto) {
		
		super();
		
		this.nome = nome;
		this.modello = modello;
		this.marca = marca;
		this.tipologia = tipologia;
		this.annoAcquisto = annoAcquisto;
	}
	
	/**
	 * Metodo get del nome.
	 * @return Il nome.
	 */
	public String getNome() {
	
		return nome;
	}
	
	/**
	 * Metodo set del nome.
	 * @param nome Il nome da settare.
	 */
	public void setNome(String nome) {
		
		this.nome = nome;
	}
	
	/**
	 * Metodo get del modello.
	 * @return Il modello.
	 */
	public String getModello() {
		
		return modello;
	}
	
	/**
	 * Metodo set del modello.
	 * @param modello Il modello da settare.
	 */
	public void setModello(String modello) {
	
		this.modello = modello;
	}
	
	/**
	 * Metodo get della marca.
	 * @return La marca.
	 */
	public String getMarca() {
	
		return marca;
	}
	
	/**
	 * Metodo set della marca.
	 * @param marca La marca da settare.
	 */
	public void setMarca(String marca) {
	
		this.marca = marca;
	}
	
	/**
	 * Metodo get della tipologia.
	 * @return La tipologia.
	 */
	public String getTipologia() {
	
		return tipologia;
	}
	
	/**
	 * Metodo set della tipologia.
	 * @param tipologia La tipologia da settare.
	 */
	public void setTipologia(String tipologia) {
	
		this.tipologia = tipologia;
	}
	
	/**
	 * Metodo get dell'anno di acquisto.
	 * @return L'anno di acquisto.
	 */
	public int getAnnoAcquisto() {
	
		return annoAcquisto;
	}
	
	/**
	 * Metodo set dell'anno di acquisto.
	 * @param annoAcquisto L'anno di acquisto da settare.
	 */
	public void setAnnoAcquisto(int annoAcquisto) {
	
		this.annoAcquisto = annoAcquisto;
	}
	
	/**
	 * Metodo set dello spazio occupato da questa strumentazione.
	 * @param id L'id dello spazio occupato.
	 */
	public void setSpazio(int id) {
		
		this.idSpazio = id;
	}

	/**
	 * Aggiunge questa strumentazione al database.
	 */
	@Override
	public void crea() {

		Database dbElementi = null;
		String queryInserimento = null;
		
		try {
			
			dbElementi = new Database();
			
			if (idSpazio != 0) {
				
				queryInserimento = "INSERT INTO Strumentazione(nome, modello, marca, tipologia, annoAcquisto, spazio) "
						+ "VALUES ( " + 
				"'" + nome + "', " +
				"'" + modello + "', " +
				"'" + marca + "', " +
				"'" + tipologia + "', " +
				annoAcquisto + ", " +
				idSpazio + ")";
				
			} else {
				
				queryInserimento = "INSERT INTO Strumentazione(nome, modello, marca, tipologia, annoAcquisto) "
						+ "VALUES ( " + 
				"'" + nome + "', " +
				"'" + modello + "', " +
				"'" + marca + "', " +
				"'" + tipologia + "', " +
				annoAcquisto + ")";
			}
			
			dbElementi.eseguiQuery(queryInserimento);
			
		} catch (ClassNotFoundException | SQLException | IOException e1) {
			
			JOptionPane.showMessageDialog(null, "Errore nella creazione della strumentazione", "Errore", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Aggiunge questa strumentazione al database specificando l'id che avrà.
	 * Se non si è sicuri, utilizzare il costruttore senza parametri.
	 * Questo costruttore è stato aggiunto poter realizzare la funzione di modifica.
	 * @param id L'id con cui verrà inserito nel database.
	 */
	public void crea(int id) {

		Database dbElementi = null;
		String queryInserimento = null;
		
		try {
			
			dbElementi = new Database();
			
			if (idSpazio != 0) {
				
				queryInserimento = "INSERT INTO Strumentazione(id, nome, modello, marca, tipologia, annoAcquisto, spazio) "
						+ "VALUES ( " + 
				id + ", " +
				"'" + nome + "', " +
				"'" + modello + "', " +
				"'" + marca + "', " +
				"'" + tipologia + "', " +
				annoAcquisto + ", " +
				idSpazio + ")";
				
			} else {
				
				queryInserimento = "INSERT INTO Strumentazione(id, nome, modello, marca, tipologia, annoAcquisto) "
						+ "VALUES ( " + 
				id + ", " +
				"'" + nome + "', " +
				"'" + modello + "', " +
				"'" + marca + "', " +
				"'" + tipologia + "', " +
				annoAcquisto + ")";
			}
			
			queryInserimento = "SET IDENTITY_INSERT Strumentazione ON " + queryInserimento + " SET IDENTITY_INSERT Strumentazione OFF";
			
			dbElementi.eseguiQuery(queryInserimento);
			
		} catch (ClassNotFoundException | SQLException | IOException e1) {
			
			JOptionPane.showMessageDialog(null, "Errore nella creazione della strumentazione", "Errore", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
