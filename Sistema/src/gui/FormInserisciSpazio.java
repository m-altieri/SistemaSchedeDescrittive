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

public class FormInserisciSpazio extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CampoCredenziale txtNome;
	private CampoCredenziale txtUbicazione;
	private CampoCredenziale txtNumeroFinestre;
	private CampoCredenziale txtNumeroPorte;
	private CampoCredenziale txtGrandezza;
	private JButton cmdConferma;
	
	private Visualizzatore visualizzatore;
	
	private final int ALTEZZA_CAMPI = 40;
	
	private Font fontCampi;

	public FormInserisciSpazio(Visualizzatore visualizzatore) {
		
		super();
		
		this.visualizzatore = visualizzatore;
		
		this.setLayout(new GridLayout(1, 8));
		
		fontCampi = new Font("Arial", Font.PLAIN, 16);
		txtNome = new CampoCredenziale("Nome");
		txtNome.setPreferredSize(new Dimension(0, ALTEZZA_CAMPI)); 
		//mettendo 0 alla lunghezza, si adatterà comunque secondo il GridLayout
		//basta impostare l'altezza di un solo campo e tutti gli altri si allineeranno a esso automaticamente
		txtNome.setFont(fontCampi);
		txtUbicazione = new CampoCredenziale("Ubicazione");
		txtUbicazione.setFont(fontCampi);
		txtNumeroFinestre = new CampoCredenziale("Numero finestre");
		txtNumeroFinestre.setFont(fontCampi);
		txtNumeroPorte = new CampoCredenziale("Numero porte");
		txtNumeroPorte.setFont(fontCampi);
		txtGrandezza = new CampoCredenziale("Grandezza");
		txtGrandezza.setFont(fontCampi);
		
		cmdConferma = new JButton("Conferma");
		
		cmdConferma.addActionListener(this);
		cmdConferma.setActionCommand("Conferma");
		
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
}
