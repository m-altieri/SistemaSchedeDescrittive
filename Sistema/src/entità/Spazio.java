package entità;

import java.util.ArrayList;

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
	
	private ArrayList<Personale> occupatori;
	private ArrayList<Strumentazione> strumentazioni;
	
	public Spazio() {
		
		this("", "", 0, 0, 0);
	}
	
	public Spazio(String nome, String ubicazione, int numeroFinestre, int numeroPorte, double grandezza) {
		
		super();
		
		setNome(nome);
		setUbicazione(ubicazione);
		setNumeroFinestre(numeroFinestre);
		setNumeroPorte(numeroPorte);
		setGrandezza(grandezza);
		
		setOccupatori(new ArrayList<Personale>());
		setStrumentazioni(new ArrayList<Strumentazione>());
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

	public ArrayList<Personale> getOccupatori() {
		
		return occupatori;
	}

	public void setOccupatori(ArrayList<Personale> occupatori) {
		
		this.occupatori = occupatori;
	}

	public ArrayList<Strumentazione> getStrumentazioni() {
		
		return strumentazioni;
	}

	public void setStrumentazioni(ArrayList<Strumentazione> strumentazioni) {
		
		this.strumentazioni = strumentazioni;
	}
	
}
