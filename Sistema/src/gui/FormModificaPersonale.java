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

public class FormModificaPersonale extends JPanel implements ActionListener, FocusListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JComboBox<Integer> cmbId;
	private JTextField txtNome;
	private JTextField txtCognome;
	private JTextField txtEmail;
	private JTextField txtTelefono;
	private JTextField txtResidenza;
	private JTextField txtMansione;
	private JTextField txtCittaNascita;
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
		txtNome = new JTextField();
		txtNome.setPreferredSize(new Dimension(0, ALTEZZA_CAMPI)); 
		//mettendo 0 alla lunghezza, si adatterà comunque secondo il GridLayout
		//basta impostare l'altezza di un solo campo e tutti gli altri si allineeranno a esso automaticamente
		txtNome.setFont(fontCampi);
		txtCognome = new JTextField();
		txtCognome.setFont(fontCampi);
		txtEmail = new JTextField();
		txtEmail.setFont(fontCampi);
		txtTelefono = new JTextField();
		txtTelefono.setFont(fontCampi);
		txtResidenza = new JTextField();
		txtResidenza.setFont(fontCampi);
		txtMansione = new JTextField();
		txtMansione.setFont(fontCampi);
		txtCittaNascita = new JTextField();
		txtCittaNascita.setFont(fontCampi);
		cmbSpazio = new JComboBox<String>();
		cmbSpazio.setEditable(true);
		cmbSpazio.setSelectedItem(new String("Spazio occupato"));
		cmbSpazio.addFocusListener(this);
		cmbSpazio.setFont(fontCampi);
		popolaSpazi();
		
		cmdConferma = new JButton("Conferma");
		
		cmdConferma.addActionListener(this);
		cmdConferma.setActionCommand("Conferma");
		
		cmbId.setEditable(true);
		cmbId.setSelectedItem(new String("ID"));
		cmbId.addMouseListener(this);
		cmbId.addFocusListener(this);
		txtNome.setText("Nome");
		txtCognome.setText("Cognome");
		txtEmail.setText("Email");
		txtTelefono.setText("Telefono");
		txtResidenza.setText("Residenza");
		txtMansione.setText("Mansione");
		txtCittaNascita.setText("Città di nascita");
		txtNome.addMouseListener(this);
		txtNome.addFocusListener(this);
		txtCognome.addMouseListener(this);
		txtCognome.addFocusListener(this);
		txtEmail.addMouseListener(this);
		txtEmail.addFocusListener(this);
		txtTelefono.addMouseListener(this);
		txtTelefono.addFocusListener(this);
		txtResidenza.addMouseListener(this);
		txtResidenza.addFocusListener(this);
		txtMansione.addMouseListener(this);
		txtMansione.addFocusListener(this);
		txtCittaNascita.addMouseListener(this);
		txtCittaNascita.addFocusListener(this);
		cmbId.setName("cmbId");
		txtNome.setName("txtNome");
		txtCognome.setName("txtCognome");
		txtEmail.setName("txtEmail");
		txtTelefono.setName("txtTelefono");
		txtResidenza.setName("txtResidenza");
		txtMansione.setName("txtMansione");
		txtCittaNascita.setName("txtCittaNascita");
		
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
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

	@Override
	public void mouseClicked(MouseEvent e) {

		switch (e.getComponent().getName()) {
		case "cmbId":
			cmbId.setSelectedItem(new String("")); break;
		case "txtNome":
			txtNome.setText(""); break;
		case "txtCognome":
			txtCognome.setText(""); break;
		case "txtEmail":
			txtEmail.setText(""); break;
		case "txtTelefono":
			txtTelefono.setText(""); break;
		case "txtResidenza":
			txtResidenza.setText(""); break;
		case "txtMansione":
			txtMansione.setText(""); break;
		case "txtCittaNascita":
			txtCittaNascita.setText(""); break;
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

}
