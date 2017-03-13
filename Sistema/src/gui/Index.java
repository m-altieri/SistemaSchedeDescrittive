package gui;

import java.io.IOException;

public class Index {
	
	public static void main(String[] args) throws IOException {
		
		Login fl = new Login();
		fl.setVisible(true);
		
//		Credenziali init = new Credenziali("jdbc:sqlserver://serversistema.database.windows.net:1433", "marcomassiema@serversistema", "marco1massi2ema3!");
//		init.salva(new File("Credenziali_DBElementi.bin"));
		
		//TODO creare il DBUtility da azure e qui fare l'inizializzazione di Credenziali_DBUtility.bin
	}
}
