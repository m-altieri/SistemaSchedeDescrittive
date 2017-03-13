package gui;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormInserisciPersonale extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;
	
	private JTextField txtNome;
	private JTextField txtCognome;
	private JTextField txtEmail;
	private JTextField txtTelefono;
	private JTextField txtResidenza;
	private JTextField txtMansione;
	private JTextField txtCittaNascita;
	private JButton cmdConferma;
	
	public FormInserisciPersonale() {
		
		super();
		
		this.setLayout(new GridLayout(1, 8));
		
		txtNome = new JTextField();
		txtCognome = new JTextField();
		txtEmail = new JTextField();
		txtTelefono = new JTextField();
		txtResidenza = new JTextField();
		txtMansione = new JTextField();
		txtCittaNascita = new JTextField();
		cmdConferma = new JButton("Conferma");
		
		txtNome.setText("Nome");
		txtCognome.setText("Cognome");
		txtEmail.setText("Email");
		txtTelefono.setText("Residenza");
		txtResidenza.setText("Residenza");
		txtMansione.setText("Mansione");
		txtCittaNascita.setText("Città di nascita");
		txtNome.addMouseListener(this);
		txtCognome.addMouseListener(this);
		txtEmail.addMouseListener(this);
		txtTelefono.addMouseListener(this);
		txtResidenza.addMouseListener(this);
		txtMansione.addMouseListener(this);
		txtCittaNascita.addMouseListener(this);
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
		this.add(cmdConferma);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

		switch (arg0.getComponent().getName()) {
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
