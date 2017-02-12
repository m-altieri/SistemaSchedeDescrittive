package database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Server {

	private CredenzialiServer credenziali;
	private ArrayList<Database> databases;
	private File fileCredenziali;
	
	public Server(CredenzialiServer credenziali) throws CredenzialiInvalideException, IOException {
		
		this(credenziali, new ArrayList<Database>());
	}

	public Server(CredenzialiServer credenziali, ArrayList<Database> databases) throws CredenzialiInvalideException, IOException {
		
		setCredenziali(credenziali);
		setDatabases(databases);
		
		if (credenziali.isValid())
			salvaCredenziali();
	}

	public CredenzialiServer getCredenziali() {
		
		return credenziali;
	}

	public void setCredenziali(CredenzialiServer credenziali) throws IOException {
		
		this.credenziali = credenziali;
	}

	public ArrayList<Database> getDatabases() {
		
		return databases;
	}

	public void setDatabases(ArrayList<Database> databases) {
		
		this.databases = databases;
	}
	
	private void salvaCredenziali() throws IOException {
		
		FileOutputStream fos = new FileOutputStream(fileCredenziali);
		ObjectOutputStream ous = new ObjectOutputStream(fos);
		ous.writeObject(credenziali);
		ous.close();
		fos.close();
	}
	
	public void connetti() {
		
		
	}
	
	private void popolaDatabases() {
		
		
	}
	
}
