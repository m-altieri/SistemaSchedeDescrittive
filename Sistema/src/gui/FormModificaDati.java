package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import database.Database;
import entita.Elemento;
import entita.Personale;
import entita.Strumentazione;

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
						int nCampi = campi.size();
						for (int i = 0; i < nCampi; i++) {
							campi.get(i).setText(rs.getString(i + 2)); // + 2 perchè la prima colonna è l'id, quindi partiamo dalla seconda
						}
						if (cmbSpazio != null && rs.getString(campi.size() + 2) != null) {
							cmbSpazio.setSelectedItem(rs.getString(campi.size() + 2));
						} else if (cmbSpazio != null)
							cmbSpazio.setSelectedItem("");
					} catch (SQLException | ClassNotFoundException | IOException f) {;}
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
		} catch (ClassNotFoundException | SQLException | IOException e) {;}
	}
	
	/**
	 * Esegue tutte le operazioni di basso livello per inserire nel database il nuovo elemento.
	 * Inoltre aggiorna la tabella per mostrare il nuovo elemento modificato.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
			
		try {
			// Controllo sui dati inseriti
			boolean inputValido = controllaInput();
			if (inputValido) {
				
				modificaElemento();
				try {
					visualizzatore.caricaPannelloDati();
				} catch (ClassNotFoundException | IOException e1) {;}
				
			} else
				throw new InputInvalidoException(null);
				
		} catch (InputInvalidoException f) {;}		
	}
	
	private int estraiSpazio() {
		
		int idSpazio = 0;
		if (cmbSpazio != null) {
			String spazio = cmbSpazio.getSelectedItem().toString();
		
			if (spazio.contains("-"))
				idSpazio = Integer.parseInt(spazio.substring(0, spazio.indexOf(" ")));
			else if (!spazio.isEmpty())
				idSpazio = Integer.parseInt(spazio);
		}
		return idSpazio;
	}

	private void modificaElemento() {
		
		int idSpazio = estraiSpazio();
		
		Database db = null;
		String queryModifica = null;
		
		try {
			
			db = new Database();
			
			if (className.equals(Personale.class.getSimpleName())) {

					if (idSpazio != 0)
						queryModifica = "UPDATE " + className + " SET nome = '" + txtNome.get() + "', cognome = '" + txtCognome.get() +
							"', email = '" + txtEmail.get() + "', telefono = '" + txtTelefono.get() + "', residenza = '" +
							txtResidenza.get() + "', mansione = '" + txtMansione.get() + "', cittaNascita = '" + txtCittaNascita.get() +
							"', spazio = " + idSpazio + " WHERE id = " + Integer.parseInt(cmbId.getSelectedItem().toString());
					else
						queryModifica = "UPDATE " + className + " SET nome = '" + txtNome.get() + "', cognome = '" + txtCognome.get() +
						"', email = '" + txtEmail.get() + "', telefono = '" + txtTelefono.get() + "', residenza = '" +
						txtResidenza.get() + "', mansione = '" + txtMansione.get() + "', cittaNascita = '" + txtCittaNascita.get() +
						"', spazio = null WHERE id = " + Integer.parseInt(cmbId.getSelectedItem().toString());
								
			} else if (className.equals(Strumentazione.class.getSimpleName())) {

					if (idSpazio != 0)
						queryModifica = "UPDATE " + className + " SET nome = '" + txtNome.get() + "', modello = '" + txtModello.get() +
							"', marca = '" + txtMarca.get() + "', tipologia = '" + txtTipologia.get() + "', annoAcquisto = " +
							txtAnnoAcquisto.get() + ", spazio = " + idSpazio + " WHERE id = " + Integer.parseInt(cmbId.getSelectedItem().toString());
					else
						queryModifica = "UPDATE " + className + " SET nome = '" + txtNome.get() + "', modello = '" + txtModello.get() +
								"', marca = '" + txtMarca.get() + "', tipologia = '" + txtTipologia.get() + "', annoAcquisto = " +
								txtAnnoAcquisto.get() + ", spazio = null WHERE id = " + Integer.parseInt(cmbId.getSelectedItem().toString());
							
			} else {
		
					queryModifica = "UPDATE " + className + " SET nome = '" + txtNome.get() + "', ubicazione = '" + txtUbicazione.get() +
							"', numeroFinestre = " + txtNumeroFinestre.get() + ", numeroPorte = " + txtNumeroPorte.get() + 
							", grandezza = " + txtGrandezza.get() + " WHERE id = " + Integer.parseInt(cmbId.getSelectedItem().toString());				
			}
			
			db.eseguiQuery(queryModifica);

		} catch (ClassNotFoundException | SQLException | IOException e2) {
			JOptionPane.showMessageDialog(null, "Errore durante la modifica del personale", "Errore", JOptionPane.ERROR_MESSAGE);
		}
	}

}
