package gui;

import java.io.IOException;

/**
 * Il metodi definiti in questa interfaccia vengono invocati dalle classi di inserimento, modifica e eliminazione dati 
 * nel momento di aggiornare i dati mostrati all'utente.
 * La classe che implementa questi metodi è VisualizzaTabella, che è parametrica e può essere esattamente di 3 tipi.
 */
public interface Visualizzatore {

	/**
	 * Aggiorna la tabella contenente gli elementi di quel tipo presenti in database.
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void caricaPannelloDati() throws ClassNotFoundException, IOException;
}
