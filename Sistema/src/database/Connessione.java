package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Permette di connettersi a un database.
 * Per utilizzare questa classe è necessario essere in possesso delle credenziali del database.
 */
public class Connessione {

	private Connection conn;
	private String connectionString;
	
	/**
	 * Stabilisce la connessione al database.
	 * @param credenziali Le credenziali per accedere al database.
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Connessione(Credenziali credenziali) throws SQLException, ClassNotFoundException {
				
		final String puntoEVirgola = ";";
		
		connectionString = "";
		connectionString += credenziali.getUrl() + puntoEVirgola;
		connectionString += "database=" + credenziali.getDatabase() + puntoEVirgola;
		connectionString += "user=" + credenziali.getNome() + puntoEVirgola;
		connectionString += "password=" + credenziali.getPassword() + puntoEVirgola;
		connectionString += "encrypt=true;";
		connectionString += "trustServerCertificate=false;";
		connectionString += "hostNameInCertificate=*.database.windows.net;";
		connectionString += "loginTimeout=30;";

		connetti();
	}
	
	/**
	 * Restituisce l'oggetto contenente la connessione stabilita.
	 * @return L'oggetto Connection.
	 */
	public Connection getConnessione() {
		
		return conn;
	}
	
	/**
	 * Metodo privato.
	 * Viene invocato automaticamente dal costruttore.
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private void connetti() throws SQLException, ClassNotFoundException {
				
		this.conn = DriverManager.getConnection(connectionString);
	}
	
	
}
