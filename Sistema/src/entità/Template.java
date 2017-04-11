package entità;

import java.util.LinkedHashMap;

/**
 * Layout della scheda descrittiva.
 * Contiene il testo statico (cioè il testo fisso a prescindere dagli elementi contenuti),
 * e un'indicazione di quali informazioni visualizzare, ovvero quali attributi di ogni entità.
 */
public class Template {

	private String testoStatico;
	private LinkedHashMap<String, Boolean> attributi;
	
	public Template() {
		
		this("", new LinkedHashMap<String, Boolean>());
	}
	
	public Template(String testoStatico) {
		
		this(testoStatico, new LinkedHashMap<String, Boolean>());
	}
	
	public Template(String testoStatico, LinkedHashMap<String, Boolean> attributi) {
		
		this.testoStatico = testoStatico;
		this.attributi = attributi;
	}

	public String getTestoStatico() {
		
		return testoStatico;
	}

	public void setTestoStatico(String testoStatico) {
		
		this.testoStatico = testoStatico;
	}

	public LinkedHashMap<String, Boolean> getAttributi() {
		
		return attributi;
	}

	public void setAttributi(LinkedHashMap<String, Boolean> attributi) {
		
		this.attributi = attributi;
	}
	
}
