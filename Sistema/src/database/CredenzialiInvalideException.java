package database;

/**
 * Eccezzione invocata quando le credenziali passate per accedere al server o a un database non sono corrette;
 * ad esempio viene lanciata quando si passa un oggetto di tipo Credenziali appena creato, in quanto
 * è inizializzato a ("", "", "").
 * @author PC
 *
 */
@SuppressWarnings("serial")
public class CredenzialiInvalideException extends Exception {


}
