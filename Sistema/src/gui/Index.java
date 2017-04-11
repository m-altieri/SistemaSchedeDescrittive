package gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import database.Credenziali;

/**
 * Classe di supporto utilizzata per l'inizializzazione del sistema e l'avvio dell'interfaccia grafica.
 */
public class Index {
	
	/**
	 * Metodo main del sistema.
	 * Crea un oggetto di tipo Login (la finestra iniziale) e inizilizza i file binari contenenti le credenziali per i database.
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		inizializza();

		Login fl = new Login();
		fl.setVisible(true);		
	}
	
	/**
	 * Effettua le operazioni iniziali necessarie al corretto funzionamento del programma, ad es. creazione dei file di credenziali.
	 */
	private static void inizializza() {
		
		final String URL_SERVER = "jdbc:sqlserver://serversistema.database.windows.net:1433";
		final String USER = "marcomassiema@serversistema";
		final String PASS = "marco1massi2ema3!";
		final String NOME_DBELEMENTI = "DBElementi";
		final String NOME_DBUTILITY = "DBUtility";
		final String FILE_CREDENZIALI_DBELEMENTI = "Credenziali_DBElementi.bin";
		final String FILE_CREDENZIALI_DBUTILITY = "Credenziali_DBUtility.bin";
		final String FILE_TESTO_STATICO = "TestoStatico.txt";
		final String PATH_SCHEDE = "schede";
		
		// Salva credenziali DBElementi
		{
			Credenziali elementi = new Credenziali(URL_SERVER, USER, PASS, NOME_DBELEMENTI);
			File f = new File(FILE_CREDENZIALI_DBELEMENTI);
			if (!f.isFile()) {
				elementi.salva(new File("Credenziali_DBElementi.bin"));
			}
		}
		
		// Salva credenziali DBUtility
		{
			Credenziali utility = new Credenziali(URL_SERVER, USER, PASS, NOME_DBUTILITY);
			File f = new File(FILE_CREDENZIALI_DBUTILITY);
			if (!f.isFile()) {
				utility.salva(new File("Credenziali_DBUtility.bin"));
			}
		}
	
		// Crea directory schede
		{
			File f = new File(PATH_SCHEDE);
			if (!f.exists()) {
				f.mkdir();
			}
		}
		
		// Crea file testo statico
		{
			File f = new File(FILE_TESTO_STATICO);
			PrintWriter pw = null;
			if (!f.isFile()) {
				try {
					pw = new PrintWriter(f);
				} catch (FileNotFoundException e) {
					;
				} finally {
					if (pw != null) {
						pw.close();
					}
				}
			}
		}
	}
}
