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
						} else if (cmbSpazio != null)
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
			
			try {
				// Controllo sui dati inseriti
				controlloSintatticoInput();
					
			
				// Estrazione id spazio
				int idSpazio = 0;
				if (cmbSpazio != null) {
					String spazio = cmbSpazio.getSelectedItem().toString();
				
					if (spazio.contains("-"))
						idSpazio = Integer.parseInt(spazio.substring(0, spazio.indexOf(" ")));
					else if (!spazio.isEmpty())
						idSpazio = Integer.parseInt(spazio);
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
						if (Integer.parseInt(txtAnnoAcquisto.get()) < 1900 || Integer.parseInt(txtAnnoAcquisto.get()) > 2020)
							inputValido = false;
						if (!inputValido)
							throw new InputInvalidoException(null);
						
						Database db = null;
						String queryModifica = null;
						try {
							
							db = new Database();
							
							if (idSpazio != 0)
								queryModifica = "UPDATE " + className + " SET nome = '" + txtNome.get() + "', cognome = '" + txtCognome.get() +
									"', email = '" + txtEmail.get() + "', telefono = '" + txtTelefono.get() + "', residenza = '" +
									txtResidenza.get() + "', mansione = '" + txtMansione.get() + "', cittaNascita = '" + txtCittaNascita.get() +
									"', spazio = " + idSpazio + " WHERE id = " + Integer.parseInt(cmbId.getSelectedItem().toString());
							else
								queryModifica = "UPDATE " + className + " SET nome = '" + txtNome.get() + "', cognome = '" + txtCognome.get() +
								"', email = '" + txtEmail.get() + "', telefono = '" + txtTelefono.get() + "', residenza = '" +
								txtResidenza.get() + "', mansione = '" + txtMansione.get() + "', cittaNascita = '" + txtCittaNascita.get() +
								"' WHERE id = " + Integer.parseInt(cmbId.getSelectedItem().toString());
							db.eseguiQuery(queryModifica);
							
						} catch (ClassNotFoundException | SQLException | IOException e2) {
							e2.printStackTrace();
							JOptionPane.showMessageDialog(null, "Errore durante la modifica del personale", "Errore", JOptionPane.ERROR_MESSAGE);
						}
						
					} else if (className.equals(Strumentazione.class.getSimpleName())) {
						
						// controllo semantico sull'input
						boolean inputValido = true;
						
						if (Integer.parseInt(txtAnnoAcquisto.get()) < 1900 || Integer.parseInt(txtAnnoAcquisto.get()) > 2020)
							inputValido = false;
						
						if (!inputValido)
							throw new InputInvalidoException(null);
						
						Database db = null;
						String queryModifica = null;
						try {
							
							db = new Database();
							
							if (idSpazio != 0)
								queryModifica = "UPDATE " + className + " SET nome = '" + txtNome.get() + "', modello = '" + txtModello.get() +
									"', marca = '" + txtMarca.get() + "', tipologia = '" + txtTipologia.get() + "', annoAcquisto = " +
									txtAnnoAcquisto.get() + ", spazio = " + idSpazio + " WHERE id = " + Integer.parseInt(cmbId.getSelectedItem().toString());
							else
								queryModifica = "UPDATE " + className + " SET nome = '" + txtNome.get() + "', modello = '" + txtModello.get() +
										"', marca = '" + txtMarca.get() + "', tipologia = '" + txtTipologia.get() + "', annoAcquisto = " +
										txtAnnoAcquisto.get() + " WHERE id = " + Integer.parseInt(cmbId.getSelectedItem().toString());
							db.eseguiQuery(queryModifica);
							
						} catch (ClassNotFoundException | SQLException | IOException e2) {
							e2.printStackTrace();
							JOptionPane.showMessageDialog(null, "Errore durante la modifica della strumentazione", "Errore", JOptionPane.ERROR_MESSAGE);
						}
										
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
						
						Database db = null;
						String queryModifica = null;
						try {
							
							db = new Database();
							queryModifica = "UPDATE " + className + " SET nome = '" + txtNome.get() + "', ubicazione = '" + txtUbicazione.get() +
									"', numeroFinestre = " + txtNumeroFinestre.get() + ", numeroPorte = " + txtNumeroPorte.get() + 
									", grandezza = " + txtGrandezza.get() + " WHERE id = " + Integer.parseInt(cmbId.getSelectedItem().toString());
							db.eseguiQuery(queryModifica);
							
						} catch (ClassNotFoundException | SQLException | IOException e2) {
							e2.printStackTrace();
							JOptionPane.showMessageDialog(null, "Errore durante la modifica dello spazio", "Errore", JOptionPane.ERROR_MESSAGE);
						}
						
					}
					
					try {
						visualizzatore.caricaPannelloDati();
					} catch (ClassNotFoundException | IOException e1) {;}
				}
				
				catch (InputInvalidoException f) {;}
			
			} catch (InputInvalidoException f) {;}
			
	}

	/**
	 * Controlla che l'input inserito sia sintatticamente corretto, ovvero che non abbia spazi
	 * lasciati vuoti.
	 * @throws InputInvalidoException
	 */
	private void controlloSintatticoInput() throws InputInvalidoException {

		boolean inputErrato = false;
		
		for (Iterator<CampoCredenziale> iterator = campi.iterator(); iterator.hasNext();) {
			CampoCredenziale campoCredenziale = iterator.next();
			inputErrato |= campoCredenziale.getText().equals("") || campoCredenziale.getText().equals(campoCredenziale.getHint());
		}
		
		if (inputErrato)
			throw new InputInvalidoException(null);
	}
		
}
