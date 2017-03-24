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
import javax.swing.JPanel;

import database.Database;
import entità.Personale;

public class FormInserisciPersonale extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<CampoCredenziale> campi;
	private CampoCredenziale txtNome;
	private CampoCredenziale txtCognome;
	private CampoCredenziale txtEmail;
	private CampoCredenziale txtTelefono;
	private CampoCredenziale txtResidenza;
	private CampoCredenziale txtMansione;
	private CampoCredenziale txtCittaNascita;
	private JComboBox<String> cmbSpazio;
	private JButton cmdConferma;
	
	private Visualizzatore visualizzatore;
	
	private final int ALTEZZA_CAMPI = 40;
	
	private Font fontCampi;
	
	public FormInserisciPersonale(Visualizzatore visualizzatore) {
		
		super();
		
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
		txtCittaNascita = new CampoCredenziale("Città di nascita");

		campi.add(txtNome);
		campi.add(txtCognome);
		campi.add(txtEmail);
		campi.add(txtTelefono);
		campi.add(txtResidenza);
		campi.add(txtMansione);
		campi.add(txtCittaNascita);
		
		for (Iterator<CampoCredenziale> iterator = campi.iterator(); iterator.hasNext();) {
			CampoCredenziale campoCredenziale = (CampoCredenziale) iterator.next();
			campoCredenziale.setFont(fontCampi);
			add(campoCredenziale);
		}
		cmbSpazio = new JComboBox<String>();
		cmbSpazio.setEditable(true);
		cmbSpazio.setSelectedItem(new String("Spazio occupato"));
		cmbSpazio.setFont(fontCampi);
		
		add(cmbSpazio);
		popolaSpazi();
		
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
			
			if (txtNome.getText().equals(txtNome.getHint()) ||
					txtCognome.getText().equals(txtCognome.getHint()) ||
					txtEmail.getText().equals(txtEmail.getHint()) ||
					txtTelefono.getText().equals(txtTelefono.getHint()) ||
					txtResidenza.getText().equals(txtResidenza.getHint()) ||
					txtMansione.getText().equals(txtMansione.getHint()) ||
					txtCittaNascita.getText().equals(txtCittaNascita.getHint()) ||
					cmbSpazio.getSelectedItem().equals("Spazio occupato")) {
				throw new InputInvalidoException(null);
			}
			
			if (e.getActionCommand().equals("Conferma")) {
				
				Personale p = new Personale();
				p.setNome(txtNome.get()); 
				p.setCognome(txtCognome.get());
				p.setEmail(txtEmail.get());
				p.setTelefono(txtTelefono.get());
				p.setResidenza(txtResidenza.get());
				p.setMansione(txtMansione.get());
				p.setCittaNascita(txtCittaNascita.get());
				
				int spazio = 0;
				if (cmbSpazio.getSelectedItem().equals("Spazio occupato")) {
					spazio = 0;
				} else {
					spazio = Integer.parseInt(cmbSpazio.getSelectedItem().toString().substring(0, cmbSpazio.getSelectedItem().toString().indexOf(" ")));
					System.out.println(spazio);
				}
				
				p.setSpazio(spazio);
				p.crea();
			
				try {
					visualizzatore.caricaPannelloDati(Personale.class);
				} catch (ClassNotFoundException | IOException e1) {				
					e1.printStackTrace();
				}
			
			}
		} catch (InputInvalidoException f) {
			
		}
	}

}
