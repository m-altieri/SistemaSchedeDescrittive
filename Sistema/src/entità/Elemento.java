package entità;

/**
 * Identifica gli oggetti contenuti in una scheda.
 * Può essere un personale, una strumentazione o uno spazio.
 */
public abstract class Elemento {

	/**
	 * Ogni elemento ha un id globale, che gli viene assegnato al momento della istanziazione. Personale, strumentazioni e spazi condividono tutti lo stesso contatore.
	 */
	private static int elementiCreati;
	private int id;
	
	static {
		
		elementiCreati = 0;
	}
	
	/**
	 * Aggiunge questo elemento al database.
	 */
	public abstract void crea();
	
	/**
	 * Inizializza l'id del nuovo elemento.
	 */
	protected Elemento() {
		
		elementiCreati++;
		setId(elementiCreati);
	}
	
	/**
	 * Metodo get per l'id.
	 * @return L'id dell'elemento.
	 */
	public int getId() {
		
		return id;
	}
	
	/**
	 * Metodo set per l'id.
	 * @param x L'id da settare.
	 */
	private void setId(int x) {
		
		id = x;
	}
	
}
