package gui;

import java.io.IOException;
import java.sql.SQLException;

import database.Database;
import entità.Personale;

public class Index {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		
		Login fl = new Login();
		fl.setVisible(true);
		
//		Credenziali init = new Credenziali("jdbc:sqlserver://serversistema.database.windows.net:1433", "marcomassiema@serversistema", "marco1massi2ema3!");
//		init.salva(new File("Credenziali_DBElementi.bin"));
		
		//TODO creare il DBUtility da azure e qui fare l'inizializzazione di Credenziali_DBUtility.bin
		Database elementi = new Database();
		elementi.eseguiQuery("IF NOT EXISTS (SELECT * FROM DBElementi.information_schema.tables WHERE table_name = 'Personale') "
				+ "BEGIN "
				+ "CREATE TABLE Personale(id INT IDENTITY(1,1) PRIMARY KEY,"
				+ "nome VARCHAR(30) NOT NULL,"
				+ "cognome VARCHAR(30) NOT NULL,"
				+ "email VARCHAR(50) NOT NULL,"
				+ "telefono VARCHAR(20) NOT NULL,"
				+ "residenza VARCHAR(50) NOT NULL,"
				+ "mansione VARCHAR(40) NOT NULL,"
				+ "cittaNascita VARCHAR(50) NOT NULL) "
				+ "END");
		
	}
}
