package database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Modella le credenziali utilizzate per connettersi al server e ai database.
 * @author PC
 *
 */
public class Credenziali implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String url;
	private String nome;
	private String password;
	private String database;
	
	public Credenziali(String url, String nome, String password, String database) {
		
		this.url = url;
		this.nome = nome;
		this.password = password;
		this.database = database;
	}
	
	public void salva(File file) throws IOException {
		
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(this);
		
		oos.close();
		fos.close();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getDatabase() {
		return database;
	}
	
	public void setDatabase(String database) {
		this.database = database;
	}
	
	public boolean isValid() {
		
		return !url.equals("") && !nome.equals("") && !password.equals("") && !database.equals("");
	}
	
}
