package database;

/**
 * Rappresenta un database.
 */
import java.io.IOException;
import java.sql.SQLException;

public class Database extends Entit‡SQL {

	private String nome;

	public Database(String nome, Credenziali credenziali) throws ClassNotFoundException, SQLException, IOException {
		
		super(credenziali);
		
		this.nome = nome;
	}
	
	public String getNome() {
		
		return nome;
	}
	
	public void setNome(String nome) {
		
		this.nome = nome;
	}

}
