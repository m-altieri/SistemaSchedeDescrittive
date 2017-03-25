package gui;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import database.Credenziali;

public class Index {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		
		Login fl = new Login();
		fl.setVisible(true);
		
		Credenziali elementi = new Credenziali("jdbc:sqlserver://serversistema.database.windows.net:1433", "marcomassiema@serversistema", "marco1massi2ema3!", "DBElementi");
		elementi.salva(new File("Credenziali_DBElementi.bin"));
		Credenziali utility = new Credenziali("jdbc:sqlserver://serversistema.database.windows.net:1433", "marcomassiema@serversistema", "marco1massi2ema3!", "DBUtility");
		utility.salva(new File("Credenziali_DBUtility.bin"));
	}
}
