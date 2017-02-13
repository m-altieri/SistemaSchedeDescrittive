package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Crea, gestisce, ed esegue oggetti query rivolti al server o a un database.
 * @author PC
 *
 */
public class Query {

	private String query; 
	private Statement stmt;
	private Server server;
	private Database db;
	
	public Query(String query) throws SQLException {
		
		this(null, query);
	}
	
	public Query(Server server, String query) throws SQLException {

		this(server, null, query);
	}
	
	public Query(Server server, Database db, String query) throws SQLException {
		
		this.server = server;
		this.db = db;
		this.query = query;
	}
	
	private Statement preparaQuery() throws SQLException {
		
		return server.getConnessione().getConnessione().createStatement();
	}
	
	private void chiudi() throws SQLException {
		
		stmt.close();
	}
	
	private void usaDatabase() throws SQLException {
		
		preparaQuery();
		stmt.execute("USE DATABASE " + db.getNome());
		chiudi();
	}
	
	public ResultSet eseguiResult() throws ClassNotFoundException, SQLException {
		
		if (db != null)
			usaDatabase();
		
		preparaQuery();
		ResultSet rs = stmt.executeQuery(query);
		rs.close();
		chiudi();
		return rs;
	}
	
	public void esegui() throws SQLException {
		
		if (db != null)
			usaDatabase();
		
		preparaQuery();
		stmt.execute(query);
		chiudi();
	}
	
	public String getQuery() {
		
		return query;
	}
	
	public void setQuery(String query) {
		
		this.query = query;
	}
	
}
