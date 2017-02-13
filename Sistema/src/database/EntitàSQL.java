package database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;

public abstract class Entit‡SQL {

	private Credenziali credenziali;
	private File fileCredenziali;
	private Connessione conn;
	
	protected Entit‡SQL(Credenziali credenziali) throws ClassNotFoundException, SQLException, IOException {
		
		credenziali = ottieniCredenziali();
		fileCredenziali = new File(credenziali.getUrl() + ".bin");
		
		if (credenziali.isValid())
			salvaCredenziali();
		
		connetti();
	}

	public Credenziali getCredenziali() {
		
		return credenziali;
	}

	public void setCredenziali(Credenziali credenziali) throws IOException {
		
		this.credenziali = credenziali;
	}
	
	
	public void setConnessione(Connessione conn) {
		
		this.conn = conn;
	}
	
	public Connessione getConnessione() {
		
		return conn;
	}
	
	
	private void salvaCredenziali() throws IOException {
		
		FileOutputStream fos = new FileOutputStream(fileCredenziali);
		ObjectOutputStream ous = new ObjectOutputStream(fos);
		
		ous.writeObject(credenziali);
		
		ous.close();
		fos.close();
	}
	
	public Credenziali ottieniCredenziali() throws IOException, ClassNotFoundException {
		
		FileInputStream fis = new FileInputStream(fileCredenziali);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		credenziali = (Credenziali) ois.readObject();
		
		ois.close();
		fis.close();
		
		return credenziali;	
	}
	
	public void connetti() throws ClassNotFoundException, SQLException {
		
		conn = new Connessione(credenziali);
	}
}
