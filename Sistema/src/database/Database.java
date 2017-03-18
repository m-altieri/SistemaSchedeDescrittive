package database;

import java.io.File;
import java.io.FileInputStream;
/**
 * Rappresenta un database.
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {

	private Credenziali credenziali;
	private Connessione connessione;
	private File fileCredenziali;
	private final String PERCORSO_CREDENZIALI_DBELEMENTI = "Credenziali_DBElementi.bin";
	private final String PERCORSO_CREDENZIALI_DBUTILITY = "Credenziali_DBUtility.bin";
	private String nome;
	
	/**
	 * Costruttore principale della classe.
	 * Permette di modellare il database DBElementi.
	 * Per modellare DBUtility usare new Database(true).
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public Database() throws ClassNotFoundException, SQLException, IOException {
		
		fileCredenziali = new File(PERCORSO_CREDENZIALI_DBELEMENTI);
		
		credenziali = ottieniCredenziali(fileCredenziali);
		connessione = ottieniConnessione(credenziali);
		nome = "DBElementi";
	}
	
	/**
	 * Usare questo costruttore se si intende modellare il database DBUtility.
	 * Per farlo, invocare il costruttore passando true.
	 * Invocare questo costruttore e passare false, equivale a invocare new Database().
	 * @param isDBUtility true se si intende modellare DBUtility, false se si intende modellare DBElementi.
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 */
	public Database(boolean isDBUtility) throws ClassNotFoundException, IOException, SQLException {
		
		if (isDBUtility) {
			fileCredenziali = new File(PERCORSO_CREDENZIALI_DBUTILITY);
			nome = "DBUtility";
		} else {
			fileCredenziali = new File(PERCORSO_CREDENZIALI_DBELEMENTI);
			nome = "DBElementi";
		}
		
		credenziali = ottieniCredenziali(fileCredenziali);
		connessione = ottieniConnessione(credenziali);
	}
	
	public String getNome() {
		
		return nome;
	}
	
	private Credenziali ottieniCredenziali(File fileCredenziali) throws IOException, ClassNotFoundException {
		
		Credenziali c = null;
		FileInputStream fis = new FileInputStream(fileCredenziali);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		c = (Credenziali) ois.readObject();
		
		fis.close();
		ois.close();
		return c;
	}
	
	private Connessione ottieniConnessione(Credenziali credenziali) throws ClassNotFoundException, SQLException {
		
		return new Connessione(credenziali);
	}
	
	public Connessione getConnessione() {
		
		return connessione;
	}
	
	public Credenziali getCredenziali() {
		
		return credenziali;
	}
	
	public void usa() throws SQLException {
		
		java.sql.Statement stmt = connessione.getConnessione().createStatement();
		stmt.execute("USE " + this.credenziali.getNome());
		stmt.close();
	}
	
	public void eseguiQuery(String q) throws SQLException {
		
		java.sql.Statement stmt = connessione.getConnessione().createStatement();
		stmt.execute(q);
		stmt.close();
	}
	
	public ResultSet eseguiQueryRitorno(String q) throws SQLException {
		
		java.sql.Statement stmt = connessione.getConnessione().createStatement();
		return stmt.executeQuery(q);
	}
}
