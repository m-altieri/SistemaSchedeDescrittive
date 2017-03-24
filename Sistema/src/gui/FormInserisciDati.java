package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import database.Database;
import entit�.Elemento;
import entit�.Personale;
import entit�.Spazio;
import entit�.Strumentazione;

public class FormInserisciDati extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private String className;
	
	private ArrayList<CampoCredenziale> campi;
	
	private CampoCredenziale txtNome;
	private CampoCredenziale txtCognome;
	private CampoCredenziale txtEmail;
	private CampoCredenziale txtTelefono;
	private CampoCredenziale txtResidenza;
	private CampoCredenziale txtMansione;
	private CampoCredenziale txtCittaNascita;
	
	private CampoCredenziale txtModello;
	private CampoCredenziale txtMarca;
	private CampoCredenziale txtTipologia;
	private CampoCredenziale txtAnnoAcquisto;
	
	private CampoCredenziale txtUbicazione;
	private CampoCredenziale txtNumeroFinestre;
	private CampoCredenziale txtNumeroPorte;
	private CampoCredenziale txtGrandezza;
	
	private JComboBox<String> cmbSpazio;
	private JButton cmdConferma;
	
	private Visualizzatore visualizzatore;
	
	private final int ALTEZZA_CAMPI = 40;
	
	private Font fontCampi;
	
	public FormInserisciDati(Class<? extends Elemento> c, Visualizzatore visualizzatore) {
		
		super();
		
		className = c.getSimpleName();
		
		this.visualizzatore = visualizzatore;
		
		this.setLayout(new GridLayout(1, 8));
		fontCampi = new Font("Arial", Font.PLAIN, 16);
		
		campi = new ArrayList<CampoCredenziale>();
		
		txtNome = new CampoCredenziale("Nome");
		txtNome.setPreferredSize(new Dimension(0, ALTEZZA_CAMPI));
		txtCognome = new CampoCredenziale("Cognome");
		txtEmail = new CampoCredenziale("Email");
		txtTelefono = new CampoCredenziale("Telefono");
		txtResidenza = new CampoCredenziale("Residenza");
		txtMansione = new CampoCredenziale("Mansione");
		txtCittaNascita = new CampoCredenziale("Citt� di nascita");
		txtModello = new CampoCredenziale("Modello");
		txtMarca = new CampoCredenziale("Marca");
		txtTipologia = new CampoCredenziale("Tipologia");
		txtAnnoAcquisto = new CampoCredenziale("Anno acquisto");
		txtUbicazione = new CampoCredenziale("Ubicazione");
		txtUbicazione.setFont(fontCampi);
		txtNumeroFinestre = new CampoCredenziale("Numero finestre");
		txtNumeroFinestre.setFont(fontCampi);
		txtNumeroPorte = new CampoCredenziale("Numero porte");
		txtNumeroPorte.setFont(fontCampi);
		txtGrandezza = new CampoCredenziale("Grandezza");
		txtGrandezza.setFont(fontCampi);

		if (className.equals("Personale")) {
			campi.add(txtNome);
			campi.add(txtCognome);
			campi.add(txtEmail);
			campi.add(txtTelefono);
			campi.add(txtResidenza);
			campi.add(txtMansione);
			campi.add(txtCittaNascita);
		} else if (className.equals("Strumentazione")) {
			campi.add(txtNome);
			campi.add(txtModello);
			campi.add(txtMarca);
			campi.add(txtTipologia);
			campi.add(txtAnnoAcquisto);
		} else {
			campi.add(txtNome);
			campi.add(txtUbicazione);
			campi.add(txtNumeroFinestre);
			campi.add(txtNumeroPorte);
			campi.add(txtGrandezza);
		}
		
		for (Iterator<CampoCredenziale> iterator = campi.iterator(); iterator.hasNext();) {
			CampoCredenziale campoCredenziale = (CampoCredenziale) iterator.next();
			campoCredenziale.setFont(fontCampi);
			add(campoCredenziale);
		}
		
		if (!c.getSimpleName().equals("Spazio")) {
			cmbSpazio = new JComboBox<String>();
			cmbSpazio.setEditable(true);
			cmbSpazio.setSelectedItem(new String("Spazio occupato"));
			cmbSpazio.setFont(fontCampi);
			
			add(cmbSpazio);
			popolaSpazi();
		}
		
		cmdConferma = new JButton("Conferma");
		
		cmdConferma.addActionListener(this);
		cmdConferma.setActionCommand("Conferma");
		add(cmdConferma);
	}
	
	private void popolaSpazi() {
		
		try {
			Database dbElementi = new Database();
			String query = "SELECT id, nome FROM Spazio";
			ResultSet rs = dbElementi.eseguiQueryRitorno(query);
			
			while (rs.next()) {
				int id = rs.getInt(1);
				String nome = rs.getString(2);
				cmbSpazio.addItem(Integer.toString(id) + " - " + nome);
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			if (e.getActionCommand().equals("Conferma")) {
				
				for (Iterator<CampoCredenziale> iterator = campi.iterator(); iterator.hasNext();) {
					CampoCredenziale campoCredenziale = (CampoCredenziale) iterator.next();
					if (campoCredenziale.getText().equals("") || campoCredenziale.getText().equals(campoCredenziale.getHint()))
						throw new InputInvalidoException(null);
				}
				
				int spazio = 0;
				if (!className.equals(Spazio.class.getSimpleName())) {
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
					p.crea();
				} else if (className.equals(Strumentazione.class.getSimpleName())) {
					Strumentazione s = new Strumentazione();
					s.setNome(txtNome.getText());
					s.setModello(txtModello.getText());
					s.setMarca(txtMarca.getText());
					s.setTipologia(txtTipologia.getText());
					s.setAnnoAcquisto(Integer.parseInt(txtAnnoAcquisto.getText()));
					s.setSpazio(spazio);
					s.crea();
				} else {
					Spazio s = new Spazio();
					s.setNome(txtNome.getText());
					s.setUbicazione(txtUbicazione.getText());
					s.setNumeroFinestre(Integer.parseInt(txtNumeroFinestre.getText()));
					s.setNumeroPorte(Integer.parseInt(txtNumeroPorte.getText()));
					s.setGrandezza(Integer.parseInt(txtGrandezza.getText()));
					s.crea();
				}
				
				try {
					visualizzatore.caricaPannelloDati((Class<? extends Elemento>) Class.forName("entit�." + className));
				} catch (ClassNotFoundException | IOException e1) {				
					e1.printStackTrace();
				}
			
			}
		} catch (InputInvalidoException f) {
			
		}
	}

}
