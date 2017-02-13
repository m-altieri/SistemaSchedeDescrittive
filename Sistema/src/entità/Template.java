package entità;

import java.util.HashMap;

/**
 * Layout della scheda descrittiva.
 * Contiene il testo statico (cioè il testo fisso a prescindere dagli elementi contenuti),
 * e un'indicazione di quali informazioni visualizzare, ovvero quali attributi di ogni entità.
 * @author PC
 *
 */
public class Template {

	private String testoStatico;
	private HashMap<String, Boolean> infoDaVisualizzare;
	
	public Template() {
		
		this("", new HashMap<String, Boolean>());
	}
	
	public Template(String testoStatico) {
		
		this(testoStatico, new HashMap<String, Boolean>());
	}
	
	public Template(String testoStatico, HashMap<String, Boolean> infoDaVisualizzare) {
		
		setTestoStatico(testoStatico);
		setInfoDaVisualizzare(infoDaVisualizzare);
	}

	public String getTestoStatico() {
		
		return testoStatico;
	}

	public void setTestoStatico(String testoStatico) {
		
		this.testoStatico = testoStatico;
	}

	public HashMap<String, Boolean> getInfoDaVisualizzare() {
		
		return infoDaVisualizzare;
	}

	public void setInfoDaVisualizzare(HashMap<String, Boolean> infoDaVisualizzare) {
		
		this.infoDaVisualizzare = infoDaVisualizzare;
	}
	
}
