package entita;

import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

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
	
	/**
	 * Inizializza lo spazio con tutti i campi vuoti.
	 * Usare questo costruttore con l'accortezza di settare successivamente tutti i campi.
	 */
	public Spazio() {
		
		this("", "", 0, 0, 0);
	}
	
	/**
	 * Inizializza lo spazio riempiendo tutti i campi.
	 * @param nome Il nome.
	 * @param ubicazione L'ubicazione.
	 * @param numeroFinestre Il numero di finestre.
	 * @param numeroPorte Il numero di porte.
	 * @param grandezza La grandezza.
	 */
	public Spazio(String nome, String ubicazione, int numeroFinestre, int numeroPorte, float grandezza) {
		
		super();
		
		this.nome = nome;
		this.ubicazione = ubicazione;
		this.numeroFinestre = numeroFinestre;
		this.numeroPorte = numeroPorte;
		this.grandezza = grandezza;
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
	 * Metodo get dell'ubicazione.
	 * @return L'ubicazione.
	 */
	public String getUbicazione() {
		
		return ubicazione;
	}

	/**
	 * Metodo set dell'ubicazione.
	 * @param ubicazione L'ubicazione da settare.
	 */
	public void setUbicazione(String ubicazione) {
		
		this.ubicazione = ubicazione;
	}

	/**
	 * Metodo get del numero di finestre.
	 * @return Il numero di finestre.
	 */
	public int getNumeroFinestre() {
		
		return numeroFinestre;
	}

	/**
	 * Metodo set del numero di finestre.
	 * @param numeroFinestre Il numero di finestre da settare.
	 */
	public void setNumeroFinestre(int numeroFinestre) {
		
		this.numeroFinestre = numeroFinestre;
	}

	/**
	 * Metodo get del numero di porte.
	 * @return Il numero di porte.
	 */
	public int getNumeroPorte() {
		
		return numeroPorte;
	}

	/**
	 * Metodo set del numero di porte.
	 * @param numeroPorte Il numero di porte da settare.
	 */
	public void setNumeroPorte(int numeroPorte) {
		
		this.numeroPorte = numeroPorte;
	}

	/**
	 * Metodo get della grandezza.
	 * @return La grandezza.
	 */
	public double getGrandezza() {
		
		return grandezza;
	}

	/**
	 * Metodo set della grandezza.
	 * @param d La grandezza da settare.
	 */
	public void setGrandezza(double d) {
		
		this.grandezza = d;
	}

	/**
	 * Aggiunge questo spazio al database.
	 */
	@Override
	public void crea() {

		Database dbElementi = null;
		String queryInserimento = null;
		
		try {
			
			dbElementi = new Database();

			queryInserimento = "INSERT INTO Spazio(nome, ubicazione, numeroFinestre, numeroPorte, grandezza) "
					+ "VALUES ( " + 
			"'" + nome + "', " +
			"'" + ubicazione + "', " +
			numeroFinestre + ", " +
			numeroPorte + ", " +
			grandezza + ")";
			
			dbElementi.eseguiQuery(queryInserimento);
			
		} catch (ClassNotFoundException | SQLException | IOException e1) {
			
			JOptionPane.showMessageDialog(null, "Errore nella creazione dello spazio", "Errore", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Aggiunge questo personale al database specificando l'id che avrà.
	 * Se non si è sicuri, utilizzare il costruttore senza parametri.
	 * Questo costruttore è stato aggiunto poter realizzare la funzione di modifica.
	 * @param id L'id con cui verrà inserito nel database.
	 */
	public void crea(int id) {
			
		Database dbElementi = null;
		String queryInserimento = null;
		
		try {
		
			dbElementi = new Database();
			
			queryInserimento = "INSERT INTO Spazio(id, nome, ubicazione, numeroFinestre, numeroPorte, grandezza) "
					+ "VALUES ( " + 
			id + ", " +
			"'" + nome + "', " +
			"'" + ubicazione + "', " +
			numeroFinestre + ", " +
			numeroPorte + ", " +
			grandezza + ")";
					
			queryInserimento = "SET IDENTITY_INSERT Spazio ON " + queryInserimento + " SET IDENTITY_INSERT Spazio OFF";
			dbElementi.eseguiQuery(queryInserimento);
		
		} catch (ClassNotFoundException | SQLException | IOException e1) {
			
			JOptionPane.showMessageDialog(null, "Errore nella creazione dello spazio", "Errore", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
