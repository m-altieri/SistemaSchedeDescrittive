package entità;

import java.util.ArrayList;

/**
 * Entità personale. Può essere contenuto nelle schede descrittive.
 * @author PC
 *
 */
public class Personale extends Elemento {

	private String nome;
	private String cognome;
	private String email;
	private String telefono;
	private String residenza;
	private String mansione;
	private String cittaNascita;
	
	private ArrayList<Strumentazione> strumentazioni;
	private Spazio spazio;
	
	public Personale() {
		
		this("", "", "", "", "", "", "");
	}
	
	public Personale(String nome, String cognome, String email, String telefono, String residenza, String mansione,
			String cittaNascita) {
		
		super();
		
		setNome(nome);
		setCognome(cognome);
		setEmail(email);
		setTelefono(telefono);
		setResidenza(residenza);
		setMansione(mansione);
		setCittaNascita(cittaNascita);
		
		setStrumentazioni(new ArrayList<Strumentazione>());
		setSpazio(new Spazio());
	}

	public String getNome() {
		
		return nome;
	}

	public void setNome(String nome) {
		
		this.nome = nome;
	}

	public String getCognome() {
		
		return cognome;
	}

	public void setCognome(String cognome) {
		
		this.cognome = cognome;
	}

	public String getEmail() {
		
		return email;
	}

	public void setEmail(String email) {
		
		this.email = email;
	}

	public String getTelefono() {
		
		return telefono;
	}

	public void setTelefono(String telefono) {
		
		this.telefono = telefono;
	}

	public String getResidenza() {
		
		return residenza;
	}

	public void setResidenza(String residenza) {
		
		this.residenza = residenza;
	}

	public String getMansione() {
		
		return mansione;
	}

	public void setMansione(String mansione) {
		
		this.mansione = mansione;
	}

	public String getCittaNascita() {
		
		return cittaNascita;
	}

	public void setCittaNascita(String cittaNascita) {
		
		this.cittaNascita = cittaNascita;
	}

	public ArrayList<Strumentazione> getStrumentazioni() {
		
		return strumentazioni;
	}

	public void setStrumentazioni(ArrayList<Strumentazione> strumentazioniUsate) {
		
		this.strumentazioni = strumentazioniUsate;
	}

	public Spazio getSpazio() {
		
		return spazio;
	}

	public void setSpazio(Spazio spazioOccupato) {
		
		this.spazio = spazioOccupato;
	}
		
}
