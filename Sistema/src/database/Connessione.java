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
	private String connectionString;
	
	public Connessione(Credenziali credenziali) throws SQLException, ClassNotFoundException {
				
		connectionString = "";
		connectionString += credenziali.getUrl() + ";";
		connectionString += "database=DBElementi;";
		connectionString += "user=" + credenziali.getNome() + ";";
		connectionString += "password=" + credenziali.getPassword() + ";";
		connectionString += "encrypt=true;";
		connectionString += "trustServerCertificate=false;";
		connectionString += "hostNameInCertificate=*.database.windows.net;";
		connectionString += "loginTimeout=30;";

		System.out.println(connectionString);
		connetti();
	}
	
	public Connection getConnessione() {
		
		return conn;
	}
	
	private void connetti() throws SQLException, ClassNotFoundException {
		
//		ClassLoader.getSystemClassLoader().loadClass("com.mysql.jdbc.Driver");
		
		this.conn = DriverManager.getConnection(connectionString);
	}
	
	
}
