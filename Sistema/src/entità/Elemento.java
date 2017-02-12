package entit�;

public abstract class Elemento {

	private static int elementiCreati;
	private int id;
	
	static {
		
		elementiCreati = 0;
	}
	
	protected Elemento() {
		
		elementiCreati++;
		setId(elementiCreati);
	}
	
	public int getId() {
		
		return id;
	}
	
	private void setId(int x) {
		
		id = x;
	}
	
}
