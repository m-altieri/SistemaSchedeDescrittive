package entita;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import database.Database;

/**
 * Rappresenta una scheda descrittiva.
 * @author PC
 *
 */
public class Scheda {

	private static int schedeCreate;
	private String titolo;
	private int id;
	private Template template;
	private List<Elemento> elementi;
	private String tipo;
	private List<String> vincoli;
	
	static {
		
		schedeCreate = 0;
	}
	
	public Scheda(String c, Template t, String s) {
		
		schedeCreate++;
		id = schedeCreate;
		tipo = c;
		titolo = s;
		template = t;
		elementi = new ArrayList<Elemento>();
		vincoli = new ArrayList<String>();
	}

	public int getId() {
		
		return id;
	}

	public Template getTemplate() {
		
		return template;
	}

	public void setTemplate(Template template) {
		
		this.template = template;
	}
	
	public void aggiungiElemento(Elemento x) {
		
		elementi.add(x);
	}
	
	public void rimuoviElemento(Elemento x) {
		
		elementi.remove(x);
	}
	
	public void rimuoviElemento(int x) {
		
		elementi.remove(x);
	}
	
	public void aggiungiVincolo(String x) {
		
		vincoli.add(x);
	}
	
	public void rimuoviVincolo(String x) {
		
		vincoli.remove(x);
	}
	
	public void rimuoviVincolo(int x) {
		
		vincoli.remove(x);
	}
	
	public void setTitolo(String t) {
		
		titolo = t;
	}
	
	public String getTitolo() {
		
		return titolo;
	}
	
	public String getTipo() {
		
		return tipo;
	}
	
	public void produci() {
		
		File f = null;
		PrintWriter pw = null;
		
		try {
			String percorso = "schede/" + titolo + "_" + getTime() + ".txt";
			
			f = new File(percorso);
			if (f.isFile()) {
				throw new TitoloSchedaInvalidoException();
			}
	
			pw = new PrintWriter(f);
			// stampa cose iniziali
			pw.println("Scheda descrittiva " + tipo);
			pw.println("Titolo: " + titolo);
			pw.println(template.getTestoStatico());
			
			// in attr ho esattamente gli attributi da visualizzare
			List<String> attr = new ArrayList<String>();
			attr.addAll(template.getAttributi().keySet());
			
			// stampa colonne
			int attrSize = attr.size();
			for (int i = 0; i < attrSize; i++) {
				pw.printf("%-40s", attr.get(i));
			}
			pw.println();
	
			
			// stampa elementi
			String queryElementi = ottieniQueryElementi((ArrayList<String>) attr, (ArrayList<String>) vincoli);
			try {
				Database elementi = new Database();
				ResultSet rs = elementi.eseguiQueryRitorno(queryElementi);
				while (rs.next()) {
					for (int i = 1; i < attrSize + 1; i++) {
						pw.printf("%-40s", rs.getString(i));
					}
					pw.println();
				}

			} catch (ClassNotFoundException | SQLException | IOException e) {;}
			
			pw.flush();
				
		} catch (FileNotFoundException | TitoloSchedaInvalidoException e) {;}
			
	}
	
	private String ottieniQueryElementi(ArrayList<String> attr, ArrayList<String> vincoliAttr) {

		String query = "SELECT ";
		int attrSize = attr.size();
		for (int i = 0; i < attrSize; i++) {
			query = query + attr.get(i);
			if (attr.size() - i > 1)
				query = query + ", ";
		}
		query += " FROM " + tipo;
		
		if (!vincoliAttr.isEmpty()) {
			query += " WHERE ";
			int nVincoli = vincoliAttr.size();
			for (int i = 0; i < nVincoli; i++) {
				query = query + vincoliAttr.get(i);
				if (vincoliAttr.size() - i > 1)
					query = query + " AND ";
			}
		}
		
		return query;
	}

	private String getTime() {

		String time = LocalDateTime.now().toString();
		
		time = time.substring(0, time.indexOf('.'));
		time = time.replace(':', '_');

		return time;
	}
}
