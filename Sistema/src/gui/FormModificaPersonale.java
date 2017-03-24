package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import database.Database;
import entità.Personale;

public class FormModificaPersonale extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JComboBox<Integer> cmbId;
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
	
	public FormModificaPersonale(Visualizzatore visualizzatore) {
		
		super();
		
		this.visualizzatore = visualizzatore;
		
		this.setLayout(new GridLayout(1, 8));
		
		fontCampi = new Font("Arial", Font.PLAIN, 16);
		cmbId = new JComboBox<Integer>();
		txtNome = new CampoCredenziale("Nome");
		txtNome.setPreferredSize(new Dimension(0, ALTEZZA_CAMPI)); 
		//mettendo 0 alla lunghezza, si adatterà comunque secondo il GridLayout
		//basta impostare l'altezza di un solo campo e tutti gli altri si allineeranno a esso automaticamente
		txtNome.setFont(fontCampi);
		txtCognome = new CampoCredenziale("Cognome");
		txtCognome.setFont(fontCampi);
		txtEmail = new CampoCredenziale("Email");
		txtEmail.setFont(fontCampi);
		txtTelefono = new CampoCredenziale("Telefono");
		txtTelefono.setFont(fontCampi);
		txtResidenza = new CampoCredenziale("Residenza");
		txtResidenza.setFont(fontCampi);
		txtMansione = new CampoCredenziale("Mansione");
		txtMansione.setFont(fontCampi);
		txtCittaNascita = new CampoCredenziale("Città di nascita");
		txtCittaNascita.setFont(fontCampi);
		cmbSpazio = new JComboBox<String>();
		cmbSpazio.setEditable(true);
		cmbSpazio.setSelectedItem(new String("Spazio occupato"));
		cmbSpazio.setFont(fontCampi);
		
		popolaId();
		popolaSpazi();
		
		cmdConferma = new JButton("Conferma");
		
		cmdConferma.addActionListener(this);
		cmdConferma.setActionCommand("Conferma");
		
		cmbId.setEditable(true);
		cmbId.setSelectedItem(new String("ID"));
		
		this.add(cmbId);
		this.add(txtNome);
		this.add(txtCognome);
		this.add(txtEmail);
		this.add(txtTelefono);
		this.add(txtResidenza);
		this.add(txtMansione);
		this.add(txtCittaNascita);
		this.add(cmbSpazio);
		this.add(cmdConferma);
	}
	
	private void popolaId() {
		
		try {
			Database dbElementi = new Database();
			String query = "SELECT id FROM Personale";
			ResultSet rs = dbElementi.eseguiQueryRitorno(query);
			
			while (rs.next()) {
				int id = rs.getInt(1);
				cmbId.addItem(id);
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
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
		
		if (e.getActionCommand().equals("Conferma")) {
		
			Personale p = new Personale();
			p.setNome(txtNome.getText());
			p.setCognome(txtCognome.getText());
			p.setEmail(txtEmail.getText());
			p.setTelefono(txtTelefono.getText());
			p.setResidenza(txtResidenza.getText());
			p.setMansione(txtMansione.getText());
			p.setCittaNascita(txtCittaNascita.getText());
			
			int spazio = 0;
			if (cmbSpazio.getSelectedItem().equals("Spazio occupato")) {
				spazio = 0;
			} else {
				spazio = Integer.parseInt(cmbSpazio.getSelectedItem().toString().substring(0, cmbSpazio.getSelectedItem().toString().indexOf(" ")));
				System.out.println(spazio);
			}
			
			p.setSpazio(spazio);
			
			try {
				Database db = new Database();
				
				String queryElimina = "DELETE FROM Personale WHERE id = " + Integer.parseInt(cmbId.getSelectedItem().toString());
				db.eseguiQuery(queryElimina);
				p.crea(Integer.parseInt(cmbId.getSelectedItem().toString()));
				
			} catch (ClassNotFoundException | SQLException | IOException e2) {
				e2.printStackTrace();
			}
			
			try {
				visualizzatore.caricaPannelloDati(Personale.class);
			} catch (ClassNotFoundException | IOException e1) {
				e1.printStackTrace();
			}
		
		}
	}
}
