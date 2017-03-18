package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormModificaPersonale extends JPanel {

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
		txtNome.setName("txtNome");
		txtCognome.setName("txtCognome");
		txtEmail.setName("txtEmail");
		txtTelefono.setName("txtTelefono");
		txtResidenza.setName("txtResidenza");
		txtMansione.setName("txtMansione");
		txtCittaNascita.setName("txtCittaNascita");
		
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

}
