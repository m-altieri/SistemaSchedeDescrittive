package database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Modella le credenziali utilizzate per connettersi ai database.
 * Ogni oggetto credenziale è costituito da un url, un nome, una password e il database a cui si intende accedere. 
 */
@SuppressWarnings("serial")
public class Credenziali implements Serializable {

	private String url;
	private String nome;
	private String password;
	private String database;
	
	/**
	 * Imposta i parametri a quelli passati come argomento.
	 * @param url L'url del database.
	 * @param nome Il nome utente.
	 * @param password La password utente.
	 * @param database Il nome del database.
	 */
	public Credenziali(String url, String nome, String password, String database) {
		
		this.url = url;
		this.nome = nome;
		this.password = password;
		this.database = database;
	}
	
	/**
	 * Salva queste credenziali in locale sul file system.
	 * @param file Il file su cui salvare le credenziali.
	 * @throws IOException
	 */
	public void salva(File file) {
		
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try {
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
		} catch (IOException e) {
			;
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException f) {;}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException g) {;}
			}
		}
	}

	/**
	 * Metodo get dell'url.
	 * @return L'url.
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Metodo set dell'url.
	 * @param url L'url da settare.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Metodo get del nome.
	 * @return Il nome.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Metodo set del nome.
	 * @param nome Il nome da settare.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Metodo get della password.
	 * @return La password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Metodo set della password.
	 * @param password La password da settare.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Metodo get del database.
	 * @return Il nome del database.
	 */
	public String getDatabase() {
		return database;
	}
	
	/**
	 * Metodo set del database.
	 * @param database Il nome del database da settare.
	 */
	public void setDatabase(String database) {
		this.database = database;
	}
	
	/**
	 * Controlla che le credenziali non siano vuote,
	 * @return true se le credenziali non sono vuote, false altrimenti.
	 */
	public boolean isValid() {
		
		return !(url.isEmpty() || nome.isEmpty() || password.isEmpty() || database.isEmpty());
	}
	
}
