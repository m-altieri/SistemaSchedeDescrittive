package entità;

/**
 * Rappresenta una scheda descrittiva.
 * @author PC
 *
 */
public class Scheda {

	private static int schedeCreate;
	private int id;
	private Template template;
	
	static {
		
		schedeCreate = 0;
	}
	
	public Scheda() {
		
		this(new Template());
	}
	
	public Scheda(Template t) {
		
		schedeCreate++;
		setId(schedeCreate);
		setTemplate(t);
	}

	public int getId() {
		
		return id;
	}

	private void setId(int id) {
		
		this.id = id;
	}

	public Template getTemplate() {
		
		return template;
	}

	public void setTemplate(Template template) {
		
		this.template = template;
	}
	
}
