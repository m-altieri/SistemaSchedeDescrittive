package entità;

import java.util.HashMap;

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
