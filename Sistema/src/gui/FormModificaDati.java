package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import database.Database;
import entità.Elemento;
import entità.Personale;
import entità.Spazio;
import entità.Strumentazione;

/**
 * Sottoclasse di FormInserisciDati.
 * E' molto simile alla suddetta ma contiene un ulteriore campo combobox dal quale è possibile selezionare l'ID 
 * dell'elemento da modificare.
 * Serve per modificare elementi già presenti nel database.
 * Da usare insieme al pannello di modifica.
 */
public class FormModificaDati extends FormInserisciDati {

	private static final long serialVersionUID = 1L;
	
	private JComboBox<Integer> cmbId;
	private String className;
	
	/**
	 * Crea e inizializza la form. Può assumere 3 diverse forme a seconda del tipo che viene passato come parametro 
	 * nel costruttore.
	 * @param c La classe sottoclasse di Elemento di cui si vuole aggiungere un oggetto.
	 * @param visualizzatore La tabella da associare e in cui visualizzare i nuovi dati inseriti.
	 */
	public FormModificaDati(Class<? extends Elemento> c, Visualizzatore visualizzatore) {
		
		super(c, visualizzatore);

		className = c.getSimpleName();
		
		cmbId = new JComboBox<Integer>();
		cmbId.setEditable(true);
		cmbId.setSelectedItem("ID");
		
		cmbId.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				
				if (e.getStateChange() == ItemEvent.SELECTED) {
					try {
						Database db = new Database();
						String query = "SELECT * FROM " + className + " WHERE id = " + Integer.parseInt(cmbId.getSelectedItem().toString());
						
						ResultSet rs = db.eseguiQueryRitorno(query);
						rs.next();
						for (int i = 0; i < campi.size(); i++) {
							campi.get(i).setText(rs.getString(i + 2)); // + 2 perchè la prima colonna è l'id, quindi partiamo dalla seconda
						}
						if (cmbSpazio != null && rs.getString(campi.size() + 2) != null) {
							cmbSpazio.setSelectedItem(rs.getString(campi.size() + 2));
						} else
							cmbSpazio.setSelectedItem(new String(""));
					} catch (SQLException | ClassNotFoundException | IOException f) {
						f.printStackTrace();
					}
				}
			}
			
		});
		
		popolaId();
		add(cmbId);
	}
	
	/**
	 * Popola la combobox contenente gli ID degli elementi già creati del tipo selezionato, in modo da selezionare 
	 * quale modificare.
	 */
	private void popolaId() {
		
		try {
			Database dbElementi = new Database();
			String query = "SELECT id FROM " + className;
			ResultSet rs = dbElementi.eseguiQueryRitorno(query);
			
			while (rs.next()) {
				int id = rs.getInt(1);
				cmbId.addItem(id);
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Esegue tutte le operazioni di basso livello per inserire nel database il nuovo elemento.
	 * Inoltre aggiorna la tabella per mostrare il nuovo elemento creato.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals("Conferma")) {
			
			try {
				
				// Controllo sui dati inseriti
				for (Iterator<CampoCredenziale> iterator = campi.iterator(); iterator.hasNext();) {
					CampoCredenziale campoCredenziale = (CampoCredenziale) iterator.next();
					if (campoCredenziale.getText().equals("") || campoCredenziale.getText().equals(campoCredenziale.getHint()))
						throw new InputInvalidoException(null);
				}
				
				Database db = null;
				String queryElimina = null;
				try {
					
					db = new Database();
					queryElimina = "DELETE FROM " + className + " WHERE id = " + Integer.parseInt(cmbId.getSelectedItem().toString());
					db.eseguiQuery(queryElimina);
					
				} catch (ClassNotFoundException | SQLException | IOException e2) {

					JOptionPane.showMessageDialog(null, "Errore durante l'eliminazione", "Errore", JOptionPane.ERROR_MESSAGE);
				}
				
			} catch (Exception f) {;}
			
			int spazio = 0;
			if (!(className.equals(Spazio.class.getSimpleName()) || cmbSpazio.getSelectedItem().toString().isEmpty() || cmbSpazio.getSelectedItem().toString().equals("Spazio occupato"))) {
				
				if (cmbSpazio.getSelectedItem().toString().contains("-"))
					spazio = Integer.parseInt(cmbSpazio.getSelectedItem().toString().substring(0, cmbSpazio.getSelectedItem().toString().indexOf(" ")));
				else
					spazio = Integer.parseInt(cmbSpazio.getSelectedItem().toString());
			}
			
			try {
				
				if (className.equals(Personale.class.getSimpleName())) {
					
					// controllo semantico sull'input
					boolean inputValido = true;
					
					if (!txtNome.get().matches("([A-Z]|[a-z]|\\ù|\\à|\\è|\\ò|\\ì)+(\\s([A-z]|[a-z]|\\ù|\\à|\\ò|\\ì)+)?"))
						inputValido = false;
					if (!txtCognome.get().matches("([A-Z]|[a-z]|\\ù|\\à|\\è|\\ò|\\ì)+(\\s([A-z]|[a-z]|\\ù|\\à|\\ò|\\ì)+)?"))
						inputValido = false;
					if (!txtTelefono.get().matches("\\+?[0-9]+(( )[0-9]+)*"))
						inputValido = false;
					if (!txtEmail.get().matches("(\\w|\\d).+(\\w|\\d)@.+\\..+"))
						inputValido = false;
					
					if (!inputValido)
						throw new InputInvalidoException(null);
					
					Personale p = new Personale();
					p.setNome(txtNome.get()); 
					p.setCognome(txtCognome.get());
					p.setEmail(txtEmail.get());
					p.setTelefono(txtTelefono.get());
					p.setResidenza(txtResidenza.get());
					p.setMansione(txtMansione.get());
					p.setCittaNascita(txtCittaNascita.get());
					p.setSpazio(spazio);
					p.crea(Integer.parseInt(cmbId.getSelectedItem().toString()));
					
				} else if (className.equals(Strumentazione.class.getSimpleName())) {
					
					// controllo semantico sull'input
					boolean inputValido = true;
					
					if (!txtAnnoAcquisto.get().matches("\\d{4}"))
						inputValido = true;
					
					if (!inputValido)
						throw new InputInvalidoException(null);
					
					Strumentazione s = new Strumentazione();
					s.setNome(txtNome.getText());
					s.setModello(txtModello.getText());
					s.setMarca(txtMarca.getText());
					s.setTipologia(txtTipologia.getText());
					s.setAnnoAcquisto(Integer.parseInt(txtAnnoAcquisto.getText()));
					s.setSpazio(spazio);
					s.crea(Integer.parseInt(cmbId.getSelectedItem().toString()));
					
				} else {
					
					// controllo semantico dell'input
					boolean inputValido = true;
					
					if (!txtNumeroFinestre.get().matches("\\d+"))
						inputValido = false;
					if (!txtNumeroPorte.get().matches("\\d+"))
						inputValido = false;
					if (!txtGrandezza.get().matches("\\d+(\\.\\d+)?"))
						inputValido = false;
					
					if (!inputValido)
						throw new InputInvalidoException(null);
					
					Spazio s = new Spazio();
					s.setNome(txtNome.getText());
					s.setUbicazione(txtUbicazione.getText());
					s.setNumeroFinestre(Integer.parseInt(txtNumeroFinestre.getText()));
					s.setNumeroPorte(Integer.parseInt(txtNumeroPorte.getText()));
					s.setGrandezza(Integer.parseInt(txtGrandezza.getText()));
					s.crea(Integer.parseInt(cmbId.getSelectedItem().toString()));
				}
				
				try {
					visualizzatore.caricaPannelloDati();
				} catch (ClassNotFoundException | IOException e1) {;}
			}
			
			catch (InputInvalidoException f) {;}
		}
	}
		
}
