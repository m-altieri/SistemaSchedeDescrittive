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

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import entità.Personale;
import entità.Spazio;

public class FormInserisciSpazio extends JPanel implements FocusListener, MouseListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField txtNome;
	private JTextField txtUbicazione;
	private JTextField txtNumeroFinestre;
	private JTextField txtNumeroPorte;
	private JTextField txtGrandezza;
	private JButton cmdConferma;
	
	private Visualizzatore visualizzatore;
	
	private final int ALTEZZA_CAMPI = 40;
	
	private Font fontCampi;

	public FormInserisciSpazio(Visualizzatore visualizzatore) {
		
		super();
		
		this.visualizzatore = visualizzatore;
		
		this.setLayout(new GridLayout(1, 8));
		
		fontCampi = new Font("Arial", Font.PLAIN, 16);
		txtNome = new JTextField();
		txtNome.setPreferredSize(new Dimension(0, ALTEZZA_CAMPI)); 
		//mettendo 0 alla lunghezza, si adatterà comunque secondo il GridLayout
		//basta impostare l'altezza di un solo campo e tutti gli altri si allineeranno a esso automaticamente
		txtNome.setFont(fontCampi);
		txtUbicazione = new JTextField();
		txtUbicazione.setFont(fontCampi);
		txtNumeroFinestre = new JTextField();
		txtNumeroFinestre.setFont(fontCampi);
		txtNumeroPorte = new JTextField();
		txtNumeroPorte.setFont(fontCampi);
		txtGrandezza = new JTextField();
		txtGrandezza.setFont(fontCampi);
		
		cmdConferma = new JButton("Conferma");
		
		cmdConferma.addActionListener(this);
		cmdConferma.setActionCommand("Conferma");
		
		txtNome.setText("Nome");
		txtUbicazione.setText("Ubicazione");
		txtNumeroFinestre.setText("Numero Finestre");
		txtNumeroPorte.setText("Numero Porte");
		txtGrandezza.setText("Grandezza");
		txtNome.addMouseListener(this);
		txtNome.addFocusListener(this);
		txtUbicazione.addMouseListener(this);
		txtUbicazione.addFocusListener(this);
		txtNumeroFinestre.addMouseListener(this);
		txtNumeroFinestre.addFocusListener(this);
		txtNumeroPorte.addMouseListener(this);
		txtNumeroPorte.addFocusListener(this);
		txtGrandezza.addMouseListener(this);
		txtGrandezza.addFocusListener(this);
		txtNome.setName("txtNome");
		txtUbicazione.setName("txtUbicazione");
		txtNumeroFinestre.setName("txtNumeroFinestre");
		txtNumeroPorte.setName("txtNumeroPorte");
		txtGrandezza.setName("txtGrandezza");
		
		this.add(txtNome);
		this.add(txtUbicazione);
		this.add(txtNumeroFinestre);
		this.add(txtNumeroPorte);
		this.add(txtGrandezza);
		this.add(cmdConferma);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Conferma")) {
			
			Spazio s = new Spazio();
			s.setNome(txtNome.getText());
			s.setUbicazione(txtUbicazione.getText());
			s.setNumeroFinestre(Integer.parseInt(txtNumeroFinestre.getText()));
			s.setNumeroPorte(Integer.parseInt(txtNumeroPorte.getText()));
			s.setGrandezza(Integer.parseInt(txtGrandezza.getText()));
			
			s.crea();
		
			try {
				visualizzatore.caricaPannelloDati(Spazio.class);
			} catch (ClassNotFoundException | IOException e1) {
				
				e1.printStackTrace();
			}
		
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		switch (e.getComponent().getName()) {
		case "txtNome":
			txtNome.setText(""); break;
		case "txtUbicazione":
			txtUbicazione.setText(""); break;
		case "txtNumeroFinestre":
			txtNumeroFinestre.setText(""); break;
		case "txtNumeroPorte":
			txtNumeroPorte.setText(""); break;
		case "txtGrandezza":
			txtGrandezza.setText(""); break;
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

	@Override
	public void focusGained(FocusEvent e) {
		
		String nome = e.getComponent().getName();
		
		if (nome.equals("txtNome") && txtNome.getText().equals("Nome")) {
			txtNome.setText("");
		}
		if (nome.equals("txtUbicazione") && txtUbicazione.getText().equals("Ubicazione")) {
			txtUbicazione.setText("");
		}
		if (nome.equals("txtNumeroFinestre") && txtNumeroFinestre.getText().equals("Numero Finestre")) {
			txtNumeroFinestre.setText("");
		}
		if (nome.equals("txtNumeroPorte") && txtNumeroPorte.getText().equals("Numero Porte")) {
			txtNumeroPorte.setText("");
		}
		if (nome.equals("txtGrandezza") && txtGrandezza.getText().equals("Grandezza")) {
			txtGrandezza.setText("");
		}
	}

	@Override
	public void focusLost(FocusEvent arg0) {}
}
