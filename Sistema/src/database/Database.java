package database;

import java.io.File;
import java.io.FileInputStream;
/**
 * Rappresenta un database.
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe usata per gestire i database ed eseguire le query.
 */
public class Database {

	private Credenziali credenziali;
	private Connessione connessione;
	private File fileCredenziali;
	private String nome;
	final String PERCORSO_CREDENZIALI_DBELEMENTI = "Credenziali_DBElementi.bin";

	/**
	 * Costruttore principale della classe.
	 * Permette di modellare il database DBElementi.
	 * Per modellare DBUtility usare new Database(true).
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public Database() throws ClassNotFoundException, SQLException, IOException {

		this(false);
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
		
		final String PERCORSO_CREDENZIALI_DBUTILITY = "Credenziali_DBUtility.bin";

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
	
	/**
	 * Metodo get del nome.
	 * @return Il nome.
	 */
	public String getNome() {
		
		return nome;
	}
	
	/**
	 * Estrai l'oggetto credenziali corrispondente al file binario passato come parametro.
	 * @param f Il file da cui estrarre le credenziali.
	 * @return L'oggetto Credenziali.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private Credenziali ottieniCredenziali(File f) throws IOException, ClassNotFoundException {
		
		Credenziali c = null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try {
			fis = new FileInputStream(f);
			ois = new ObjectInputStream(fis);
			
			Object o = ois.readObject();
			if (o instanceof Credenziali) {
				c = (Credenziali) o;
			}
			
		} catch (IOException e) {
			;
		} finally {
			if (fis != null) {
				fis.close();
			}
			if (ois != null) {
				ois.close();
			}
		}

		return c;
	}
	
	/**
	 * Si connette al database corrispondente a questo oggetto, utilizzando per accedere le credenziali passate come parametro.
	 * @param c Le credenziali per accedere.
	 * @return L'oggetto Connessione, nel caso di buon fine dell'operazione.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private Connessione ottieniConnessione(Credenziali c) throws ClassNotFoundException, SQLException {
		
		return new Connessione(c);
	}
	
	/**
	 * Metodo get dell'oggetto connessione.
	 * @return L'oggetto Connessione.
	 */
	public Connessione getConnessione() {
		
		return connessione;
	}
	
	/**
	 * Metodo get dell'oggetto credenziali.
	 * @return L'oggetto Credenziali.
	 */
	public Credenziali getCredenziali() {
		
		return credenziali;
	}
	
	/**
	 * Esegue la query, passata in input come parametro, su questo database.
	 * Utilizzare questo metodo solo per query che non producono un output (come inserimento, eliminazione, ecc).
	 * Per poter invocare questo metodo, questo oggetto deve già avere una connessione stabilita.
	 * @param q La query da eseguire.
	 * @throws SQLException
	 */
	public void eseguiQuery(String q) throws SQLException {
		
		java.sql.Statement stmt = connessione.getConnessione().createStatement();
		stmt.execute(q);
		stmt.close();
	}
	
	/**
	 * Esegue la query, passata in input come parametro, su questo database.
	 * Utilizzare questo metodo per query che producono un output (come la selezione).
	 * Per poter invocare questo metodo, questo oggetto deve già avere una connessione stabilita.
	 * @param q La query da eseguire
	 * @return
	 * @throws SQLException
	 */
	public ResultSet eseguiQueryRitorno(String q) throws SQLException {
		
		java.sql.Statement stmt = connessione.getConnessione().createStatement();
		ResultSet rs = stmt.executeQuery(q);
		return rs;
	}

	/**
	 * Restituisce un oggetto PreparedStatement inizializzato con la query passata come argomento.
	 * La query da passare può contenere i placemarker '?' per le query dinamiche.
	 * @param q La query da preparare, può contenere i placemarker '?'.
	 * @return Oggetto PreparedStatement.
	 * @throws SQLException
	 */
	public PreparedStatement preparaQuery(String q) throws SQLException {
		
		java.sql.PreparedStatement pstmt = connessione.getConnessione().prepareStatement(q);
		return pstmt;
	}
	
	/**
	 * Esegue la query preparata nel caso di query che NON prevedono un result set di ritorno.
	 * Prima di eseguire questo metodo sostituire gli eventuali placemarker '?' con i corretti valori, utilizzando
	 * i metodi di PreparedStatement.
	 * @param ps La query preparata.
	 * @throws SQLException
	 */
	public void eseguiQueryPreparata(PreparedStatement ps) throws SQLException {
		
		ps.execute();
	}
	
	/**
	 * Esegue la query preparata nel caso di query che PREVEDONO un result set di ritorno.
	 * Prima di eseguire questo metodo sostituire gli eventuali placemarker '?' con i corretti valori, utilizzando
	 * i metodi di PreparedStatement
	 * @param ps La query preparata.
	 * @return L'oggetto ResultSet contenente il result set di ritorno.
	 * @throws SQLException
	 */
	public ResultSet eseguiQueryPreparataRitorno(PreparedStatement ps) throws SQLException {
		
		return ps.executeQuery();
	}
	
}
