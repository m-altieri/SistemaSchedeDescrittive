package database;

import java.sql.*;

public class Query {

	private String query;
	
	public Query() {
		
		this("");
	}
	
	public Query(String query) {
		
		this.query = query;
	}
	
	public void esegui() throws ClassNotFoundException {
		
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection conn;
		Statement stmt;
		conn = DriverManager.getConnection(URL, NOME, PASS);
		stmt = conn.createStatement();
		stmt.executeQuery(this.query);
	}
	
	public String getQuery() {
		
		return query;
	}
	
	public void setQuery(String query) {
		
		this.query = query;
	}
	
}
