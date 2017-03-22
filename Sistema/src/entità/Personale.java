package entità;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Database;

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
	
	private int idSpazio;
	
	public Personale() {
		
		this("", "", "", "", "", "", "");
	}
	
	public Personale(String nome, String cognome, String email, String telefono, String residenza, String mansione,
			String cittaNascita) {
		
		super();
		
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.telefono = telefono;
		this.residenza = residenza;
		this.mansione = mansione;
		this.cittaNascita = cittaNascita;
	}
	
	@Override
	public void crea() {
		
		try {
			Database dbElementi = new Database();
			String queryInserimento = "";
			
			if (idSpazio != 0) {
				queryInserimento = "INSERT INTO Personale(nome, cognome, email, telefono, residenza, mansione, cittaNascita, spazio) "
						+ "VALUES ( " + 
				"'" + nome + "', " +
				"'" + cognome + "', " +
				"'" + email + "', " +
				"'" + telefono + "', " +
				"'" + residenza + "', " +
				"'" + mansione + "', " +
				"'" + cittaNascita + "', " +
				idSpazio + ")";
			} else {
				queryInserimento = "INSERT INTO Personale(nome, cognome, email, telefono, residenza, mansione, cittaNascita) "
						+ "VALUES ( " + 
				"'" + nome + "', " +
				"'" + cognome + "', " +
				"'" + email + "', " +
				"'" + telefono + "', " +
				"'" + residenza + "', " +
				"'" + mansione + "', " +
				"'" + cittaNascita + "')";
			}
			
			dbElementi.eseguiQuery(queryInserimento);
		} catch (ClassNotFoundException | SQLException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void crea(int id) {

		try {
			Database dbElementi = new Database();
			String queryInserimento = "";
			
			if (idSpazio != 0) {
				queryInserimento = "SET IDENTITY_INSERT Personale ON"
						+ " INSERT INTO Personale(id, nome, cognome, email, telefono, residenza, mansione, cittaNascita) VALUES ( " + 
				id + ", " +
				"'" + nome + "', " +
				"'" + cognome + "', " +
				"'" + email + "', " +
				"'" + telefono + "', " +
				"'" + residenza + "', " +
				"'" + mansione + "', " +
				"'" + cittaNascita + "', " +
				idSpazio + ")"
						+ " SET IDENTITY_INSERT Personale OFF";
			} else {
				queryInserimento = "SET IDENTITY_INSERT Personale ON"
						+ " INSERT INTO Personale(id, nome, cognome, email, telefono, residenza, mansione, cittaNascita) VALUES ( " + 
				id + ", " +
				"'" + nome + "', " +
				"'" + cognome + "', " +
				"'" + email + "', " +
				"'" + telefono + "', " +
				"'" + residenza + "', " +
				"'" + mansione + "', " +
				"'" + cittaNascita + "')"
						+ " SET IDENTITY_INSERT Personale OFF";
			}
			
			dbElementi.eseguiQuery(queryInserimento);
		} catch (ClassNotFoundException | SQLException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
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
	
	public void setSpazio(int id) {
		
		this.idSpazio = id;
	}
		
}
