package entit�;

import java.util.HashMap;

/**
 * Layout della scheda descrittiva.
 * Contiene il testo statico (cio� il testo fisso a prescindere dagli elementi contenuti),
 * e un'indicazione di quali informazioni visualizzare, ovvero quali attributi di ogni entit�.
 */
public class Template {

	private String testoStatico;
	private HashMap<String, Boolean> attributi;
	
	public Template() {
		
		this("", new HashMap<String, Boolean>());
	}
	
	public Template(String testoStatico) {
		
		this(testoStatico, new HashMap<String, Boolean>());
	}
	
	public Template(String testoStatico, HashMap<String, Boolean> attributi) {
		
		this.testoStatico = testoStatico;
		this.attributi = attributi;
	}

	public String getTestoStatico() {
		
		return testoStatico;
	}

	public void setTestoStatico(String testoStatico) {
		
		this.testoStatico = testoStatico;
	}

	public HashMap<String, Boolean> getAttributi() {
		
		return attributi;
	}

	public void setAttributi(HashMap<String, Boolean> attributi) {
		
		this.attributi = attributi;
	}
	
}
