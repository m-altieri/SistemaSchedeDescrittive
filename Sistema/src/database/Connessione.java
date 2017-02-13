package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Modella la connessione al server o a un database.
 * Invocare getConnessione() per utilizzare la connessione.
 * @author PC
 *
 */
public class Connessione {

	private Connection conn;
	private Credenziali credenziali;
	
	public Connessione(Credenziali credenziali) throws SQLException, ClassNotFoundException {
		
		this.credenziali = credenziali;
		
		connetti();
	}
	
	public Connection getConnessione() {
		
		return conn;
	}
	
	private void connetti() throws SQLException, ClassNotFoundException {
		
		ClassLoader.getSystemClassLoader().loadClass("com.mysql.jdbc.Driver");
		
		this.conn = DriverManager.getConnection(credenziali.getUrl(), credenziali.getNome(), credenziali.getPassword());
	}
	
	
}
