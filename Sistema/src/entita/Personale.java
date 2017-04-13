package entita;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import database.Database;

/**
 * Entità personale. Può essere contenuto nelle schede descrittive.
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
	
	/**
	 * Inizializza il personale con tutti i campi vuoti.
	 * Usare questo costruttore con l'accortezza di settare successivamente tutti i campi.
	 */
	public Personale() {
		
		this("", "", "", "", "", "", "");
	}
	
	/**
	 * Inizializza il personale riempiendo tutti i campi tranne l'id dello spazio occupato, che è opzionale.
	 * Eventualmente settarlo a parte in un secondo momento.
	 * @param nome Il nome.
	 * @param cognome Il cognome.
	 * @param email L'indirizzo email.
	 * @param telefono Il numero di telefono.
	 * @param residenza La città di residenza.
	 * @param mansione La mansione nell'azienda.
	 * @param cittaNascita La città di nascita.
	 */
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
	
	/**
	 * Aggiunge questo personale al database.
	 */
	@Override
	public void crea() {
		
		Database dbElementi = null;
		String queryInserimento = null;
		PreparedStatement ps = null;
		
		try {

			dbElementi = new Database();
			
			if (idSpazio != 0) {
				queryInserimento = "INSERT INTO Personale(nome, cognome, email, telefono, residenza, mansione, cittaNascita, spazio) "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			} else {
				queryInserimento = "INSERT INTO Personale(nome, cognome, email, telefono, residenza, mansione, cittaNascita) "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
			}
			ps = dbElementi.preparaQuery(queryInserimento);
			ps.setString(1, nome);
			ps.setString(2, cognome);
			ps.setString(3, email);
			ps.setString(4, telefono);
			ps.setString(5, residenza);
			ps.setString(6, mansione);
			ps.setString(7, cittaNascita);
			
			if (idSpazio != 0) {
				ps.setInt(8, idSpazio);
			}
			
			dbElementi.eseguiQueryPreparata(ps);
		
		} catch (ClassNotFoundException | SQLException | IOException e1) {
		
			JOptionPane.showMessageDialog(null, "Errore nella creazione del personale", "Errore", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Aggiunge questo personale al database specificando l'id che avrà.
	 * Se non si è sicuri, utilizzare il costruttore senza parametri.
	 * Questo costruttore è stato aggiunto poter realizzare la funzione di modifica.
	 * @param id L'id con cui verrà inserito nel database.
	 */
//	public void crea(int id) {
//
//		Database dbElementi = null;
//		String queryInserimento = null;
//		
//		try {
//			
//			dbElementi = new Database();
//
//			if (idSpazio != 0) {
//
//				queryInserimento = "SET IDENTITY_INSERT Personale ON"
//						+ " INSERT INTO Personale(id, nome, cognome, email, telefono, residenza, mansione, cittaNascita, spazio) VALUES ( " + 
//				id + ", " +
//				"'" + nome + "', " +
//				"'" + cognome + "', " +
//				"'" + email + "', " +
//				"'" + telefono + "', " +
//				"'" + residenza + "', " +
//				"'" + mansione + "', " +
//				"'" + cittaNascita + "', " +
//				idSpazio + ")"
//						+ " SET IDENTITY_INSERT Personale OFF";
//			
//			} else {
//				
//				queryInserimento = "SET IDENTITY_INSERT Personale ON"
//						+ " INSERT INTO Personale(id, nome, cognome, email, telefono, residenza, mansione, cittaNascita) VALUES ( " + 
//				id + ", " +
//				"'" + nome + "', " +
//				"'" + cognome + "', " +
//				"'" + email + "', " +
//				"'" + telefono + "', " +
//				"'" + residenza + "', " +
//				"'" + mansione + "', " +
//				"'" + cittaNascita + "')"
//						+ " SET IDENTITY_INSERT Personale OFF";
//			}
//			
//			dbElementi.eseguiQuery(queryInserimento);
//		
//		} catch (ClassNotFoundException | SQLException | IOException e1) {
//		
//			JOptionPane.showMessageDialog(null, "Errore nella creazione del personale", "Errore", JOptionPane.ERROR_MESSAGE);
//		}
//	}

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
	 * Metodo get del cognome.
	 * @return Il cognome.
	 */
	public String getCognome() {
		
		return cognome;
	}

	/**
	 * Metodo set del cognome.
	 * @param cognome Il cognome da settare.
	 */
	public void setCognome(String cognome) {
		
		this.cognome = cognome;
	}

	/**
	 * Metodo get dell'email.
	 * @return L'indirizzo email.
	 */
	public String getEmail() {
		
		return email;
	}

	/**
	 * Metodo set dell'email.
	 * @param email L'indirizzo email da settare.
	 */
	public void setEmail(String email) {
		
		this.email = email;
	}

	/**
	 * Metodo get del telefono.
	 * @return Il numero di telefono.
	 */
	public String getTelefono() {
		
		return telefono;
	}

	/**
	 * Metodo set del telefono.
	 * @param telefono Il numero di telefono da settare.
	 */
	public void setTelefono(String telefono) {
		
		this.telefono = telefono;
	}

	/**
	 * Metodo get della residenza.
	 * @return La città di residenza.
	 */
	public String getResidenza() {
		
		return residenza;
	}

	/**
	 * Metodo set della residenza.
	 * @param residenza La città di residenza da settare.
	 */
	public void setResidenza(String residenza) {
		
		this.residenza = residenza;
	}

	/**
	 * Metodo get della mansione.
	 * @return La mansione.
	 */
	public String getMansione() {
		
		return mansione;
	}

	/**
	 * Metodo set della mansione.
	 * @param mansione La mansione da settare.
	 */
	public void setMansione(String mansione) {
		
		this.mansione = mansione;
	}

	/**
	 * Metodo get della città di nascita.
	 * @return La città di nascita.
	 */
	public String getCittaNascita() {
		
		return cittaNascita;
	}

	/**
	 * Metodo set della città di nascita.
	 * @param cittaNascita La cittò di nascita da settare.
	 */
	public void setCittaNascita(String cittaNascita) {
		
		this.cittaNascita = cittaNascita;
	}
	
	/**
	 * Metodo set dello spazio occupato da questo personale.
	 * @param id L'id dello spazio occupato.
	 */
	public void setSpazio(int id) {
		
		this.idSpazio = id;
	}
		
}
