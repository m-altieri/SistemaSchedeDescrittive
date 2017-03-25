package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import javax.swing.JComboBox;

import database.Database;
import entità.Elemento;
import entità.Personale;
import entità.Spazio;
import entità.Strumentazione;

public class FormModificaDati extends FormInserisciDati {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JComboBox<Integer> cmbId;
	private String className;
	
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
						if (cmbSpazio != null) {
							cmbSpazio.setSelectedItem(rs.getString(campi.size() + 2));
						}
					} catch (SQLException | ClassNotFoundException | IOException f) {
						
					}
				}
			}
			
		});
		
		popolaId();
		add(cmbId);
	}
	
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
	
	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals("Conferma")) {
			try {
				for (Iterator<CampoCredenziale> iterator = campi.iterator(); iterator.hasNext();) {
					CampoCredenziale campoCredenziale = (CampoCredenziale) iterator.next();
					if (campoCredenziale.getText().equals("") || campoCredenziale.getText().equals(campoCredenziale.getHint()))
						throw new InputInvalidoException(null);
				}
				
				try {
					Database db = new Database();
					
					String queryElimina = "DELETE FROM " + className + " WHERE id = " + Integer.parseInt(cmbId.getSelectedItem().toString());
					db.eseguiQuery(queryElimina);
					
				} catch (ClassNotFoundException | SQLException | IOException e2) {
					e2.printStackTrace();
				}
				
				int spazio = 0;
				if (!className.equals(Spazio.class.getSimpleName()) && !cmbSpazio.getSelectedItem().equals("")) {
					if (!cmbSpazio.getSelectedItem().equals("Spazio occupato")) {
						spazio = Integer.parseInt(cmbSpazio.getSelectedItem().toString().substring(0, cmbSpazio.getSelectedItem().toString().indexOf(" ")));
						System.out.println(spazio);
					}
				}
								
				if (className.equals(Personale.class.getSimpleName())) {
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
					Strumentazione s = new Strumentazione();
					s.setNome(txtNome.getText());
					s.setModello(txtModello.getText());
					s.setMarca(txtMarca.getText());
					s.setTipologia(txtTipologia.getText());
					s.setAnnoAcquisto(Integer.parseInt(txtAnnoAcquisto.getText()));
					s.setSpazio(spazio);
					s.crea(Integer.parseInt(cmbId.getSelectedItem().toString()));
				} else {
					Spazio s = new Spazio();
					s.setNome(txtNome.getText());
					s.setUbicazione(txtUbicazione.getText());
					s.setNumeroFinestre(Integer.parseInt(txtNumeroFinestre.getText()));
					s.setNumeroPorte(Integer.parseInt(txtNumeroPorte.getText()));
					s.setGrandezza(Integer.parseInt(txtGrandezza.getText()));
					s.crea(Integer.parseInt(cmbId.getSelectedItem().toString()));
				}
				
				
				
				try {
					visualizzatore.caricaPannelloDati((Class<? extends Elemento>) Class.forName("entità." + className));
				} catch (ClassNotFoundException | IOException e1) {				
					e1.printStackTrace();
				}
		
			} catch (InputInvalidoException f) {
				
			}
		}
		
	}
		
}
