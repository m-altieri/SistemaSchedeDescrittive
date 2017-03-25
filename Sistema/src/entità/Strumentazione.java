package entità;

import java.io.IOException;
import java.sql.SQLException;
import database.Database;

/**
 * Entità strumentazione. Può essere contenuto nelle schede descrittive.
 * @author PC
 *
 */
public class Strumentazione extends Elemento {

	private String nome;
	private String modello;
	private String marca;
	private String tipologia;
	private int annoAcquisto;
	
	private int idSpazio;
	
	public Strumentazione() {
		
		this("", "", "", "", 0);
	}
	
	public Strumentazione(String nome, String modello, String marca, String tipologia, int annoAcquisto) {
		
		super();
		
		this.nome = nome;
		this.modello = modello;
		this.marca = marca;
		this.tipologia = tipologia;
		this.annoAcquisto = annoAcquisto;
	}
	
	public String getNome() {
	
		return nome;
	}
	
	public void setNome(String nome) {
		
		this.nome = nome;
	}
	
	public String getModello() {
		
		return modello;
	}
	
	public void setModello(String modello) {
	
		this.modello = modello;
	}
	
	public String getMarca() {
	
		return marca;
	}
	
	public void setMarca(String marca) {
	
		this.marca = marca;
	}
	
	public String getTipologia() {
	
		return tipologia;
	}
	
	public void setTipologia(String tipologia) {
	
		this.tipologia = tipologia;
	}
	
	public int getAnnoAcquisto() {
	
		return annoAcquisto;
	}
	
	public void setAnnoAcquisto(int annoAcquisto) {
	
		this.annoAcquisto = annoAcquisto;
	}
	
	public void setSpazio(int id) {
		
		this.idSpazio = id;
	}

	@Override
	public void crea() {

		try {
			Database dbElementi = new Database();
			String queryInserimento = "";
			
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
			e1.printStackTrace();
		}
	}
	
	public void crea(int id) {

		try {
			Database dbElementi = new Database();
			String queryInserimento = "";
			
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
			e1.printStackTrace();
		}
	
	}
	
}
