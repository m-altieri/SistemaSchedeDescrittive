package entità;

import java.util.ArrayList;

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
	
	private ArrayList<Personale> utilizzatori;
	private Spazio spazio;
	
	public Strumentazione() {
		
		this("", "", "", "", 0);
	}
	
	public Strumentazione(String nome, String modello, String marca, String tipologia, int annoAcquisto) {
		
		super();
		
		setNome(nome);
		setModello(modello);
		setMarca(marca);
		setTipologia(tipologia);
		setAnnoAcquisto(annoAcquisto);
		
		setUtilizzatori(new ArrayList<Personale>());
		setSpazio(new Spazio());
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
	
	public ArrayList<Personale> getUtilizzatori() {
	
		return utilizzatori;
	}
	
	public void setUtilizzatori(ArrayList<Personale> utilizzatori) {
	
		this.utilizzatori = utilizzatori;
	}
	
	public Spazio getSpazio() {
	
		return spazio;
	}
	
	public void setSpazio(Spazio ubicazione) {
	
		this.spazio = ubicazione;
	}
	
}
