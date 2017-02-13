package database;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Rappresenta il server.
 * @author PC
 *
 */
public class Server extends Entit‡SQL {

	private ArrayList<Database> databases;
	
	public Server(Credenziali credenziali) throws ClassNotFoundException, SQLException, IOException {
		
		super(credenziali);
		
		popolaDatabases();
	}

	public ArrayList<Database> getDatabases() {
		
		return databases;
	}

	public void setDatabases(ArrayList<Database> databases) {
		
		this.databases = databases;
	}

	//AGGIUSTARE IL FATTO CHE NON PASSA CREDENZIALI
	private void popolaDatabases() throws SQLException, ClassNotFoundException, IOException {
		
		Query q = new Query(this, "SHOW DATABASES;");
		ResultSet rs = q.eseguiResult();
		while (rs.next()) {
			
			aggiungiDatabase(new Database(rs.getString(1), new Credenziali()));
		}
	}

	public void aggiungiDatabase(Database db) {
		
		databases.add(db);
	}
	
}
