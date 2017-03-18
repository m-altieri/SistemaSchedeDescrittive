package entità;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Database;

/**
 * Entità spazio. Può essere contenuto nelle schede descrittive.
 * @author PC
 *
 */
public class Spazio extends Elemento {

	private String nome;
	private String ubicazione;
	private int numeroFinestre;
	private int numeroPorte;
	private double grandezza;
	
	public Spazio() {
		
		this("", "", 0, 0, 0);
	}
	
	public Spazio(String nome, String ubicazione, int numeroFinestre, int numeroPorte, double grandezza) {
		
		super();
		
		this.nome = nome;
		this.ubicazione = ubicazione;
		this.numeroFinestre = numeroFinestre;
		this.numeroPorte = numeroPorte;
		this.grandezza = grandezza;
	}

	public String getNome() {
		
		return nome;
	}

	public void setNome(String nome) {
		
		this.nome = nome;
	}

	public String getUbicazione() {
		
		return ubicazione;
	}

	public void setUbicazione(String ubicazione) {
		
		this.ubicazione = ubicazione;
	}

	public int getNumeroFinestre() {
		
		return numeroFinestre;
	}

	public void setNumeroFinestre(int numeroFinestre) {
		
		this.numeroFinestre = numeroFinestre;
	}

	public int getNumeroPorte() {
		
		return numeroPorte;
	}

	public void setNumeroPorte(int numeroPorte) {
		
		this.numeroPorte = numeroPorte;
	}

	public double getGrandezza() {
		
		return grandezza;
	}

	public void setGrandezza(double grandezza) {
		
		this.grandezza = grandezza;
	}

	@Override
	public void crea() {

		try {
			Database dbElementi = new Database();
			String queryInserimento = "";
			
			queryInserimento = "INSERT INTO Spazio(nome, ubicazione, numeroFinestre, numeroPorte, grandezza) "
					+ "VALUES ( " + 
			"'" + nome + "', " +
			"'" + ubicazione + "', " +
			numeroFinestre + ", " +
			numeroPorte + ", " +
			grandezza + ")";
			
			dbElementi.eseguiQuery(queryInserimento);
		} catch (ClassNotFoundException | SQLException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
}
