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
import entità.Strumentazione;

public class FormInserisciStrumentazione extends JPanel implements FocusListener, MouseListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNome;
	private JTextField txtModello;
	private JTextField txtMarca;
	private JTextField txtTipologia;
	private JTextField txtAnnoAcquisto;
	private JComboBox<String> cmbSpazio;
	private JButton cmdConferma;
	
	private Visualizzatore visualizzatore;
	
	private final int ALTEZZA_CAMPI = 40;
	
	private Font fontCampi;

	public FormInserisciStrumentazione(Visualizzatore visualizzatore) {
		
		super();
		
		this.visualizzatore = visualizzatore;
		
		this.setLayout(new GridLayout(1, 8));
		
		fontCampi = new Font("Arial", Font.PLAIN, 16);
		txtNome = new JTextField();
		txtNome.setPreferredSize(new Dimension(0, ALTEZZA_CAMPI)); 
		//mettendo 0 alla lunghezza, si adatterà comunque secondo il GridLayout
		//basta impostare l'altezza di un solo campo e tutti gli altri si allineeranno a esso automaticamente
		txtNome.setFont(fontCampi);
		txtModello = new JTextField();
		txtModello.setFont(fontCampi);
		txtMarca = new JTextField();
		txtMarca.setFont(fontCampi);
		txtTipologia = new JTextField();
		txtTipologia.setFont(fontCampi);
		txtAnnoAcquisto = new JTextField();
		txtAnnoAcquisto.setFont(fontCampi);
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
		txtModello.setText("Modello");
		txtMarca.setText("Marca");
		txtTipologia.setText("Tipologia");
		txtAnnoAcquisto.setText("Anno acquisto");
		txtNome.addMouseListener(this);
		txtNome.addFocusListener(this);
		txtModello.addMouseListener(this);
		txtModello.addFocusListener(this);
		txtMarca.addMouseListener(this);
		txtMarca.addFocusListener(this);
		txtTipologia.addMouseListener(this);
		txtTipologia.addFocusListener(this);
		txtAnnoAcquisto.addMouseListener(this);
		txtAnnoAcquisto.addFocusListener(this);
		txtNome.setName("txtNome");
		txtModello.setName("txtModello");
		txtMarca.setName("txtMarca");
		txtTipologia.setName("txtTipologia");
		txtAnnoAcquisto.setName("txtAnnoAcquisto");
		
		this.add(txtNome);
		this.add(txtModello);
		this.add(txtMarca);
		this.add(txtTipologia);
		this.add(txtAnnoAcquisto);
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
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Conferma")) {
			
			Strumentazione s = new Strumentazione();
			s.setNome(txtNome.getText());
			s.setModello(txtModello.getText());
			s.setMarca(txtMarca.getText());
			s.setTipologia(txtTipologia.getText());
			s.setAnnoAcquisto(Integer.parseInt(txtAnnoAcquisto.getText()));
			
			int spazio = 0;
			if (cmbSpazio.getSelectedItem().equals("Spazio occupato")) {
				spazio = 0;
			} else {
				spazio = Integer.parseInt(cmbSpazio.getSelectedItem().toString().substring(0, cmbSpazio.getSelectedItem().toString().indexOf(" ")));
				System.out.println(spazio);
			}
			
			s.setSpazio(spazio);
			s.crea();
		
			try {
				visualizzatore.caricaPannelloDati(Strumentazione.class);
			} catch (ClassNotFoundException | IOException e1) {
				
				e1.printStackTrace();
			}
		
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

		switch (arg0.getComponent().getName()) {
		case "txtNome":
			txtNome.setText(""); break;
		case "txtModello":
			txtModello.setText(""); break;
		case "txtMarca":
			txtMarca.setText(""); break;
		case "txtTipologia":
			txtTipologia.setText(""); break;
		case "txtANnoAcquisto":
			txtAnnoAcquisto.setText(""); break;
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
		if (nome.equals("txtModello") && txtModello.getText().equals("Modello")) {
			txtModello.setText("");
		}
		if (nome.equals("txtMarca") && txtMarca.getText().equals("Marca")) {
			txtMarca.setText("");
		}
		if (nome.equals("txtTipologia") && txtTipologia.getText().equals("Tipologia")) {
			txtTipologia.setText("");
		}
		if (nome.equals("txtAnnoAcquisto") && txtAnnoAcquisto.getText().equals("Anno acquisto")) {
			txtAnnoAcquisto.setText("");
		}
		if (nome.equals("cmbSpazio") && cmbSpazio.getSelectedItem().equals("Spazio occupato")) {
			cmbSpazio.setSelectedItem(new String(""));
		}
	}

	@Override
	public void focusLost(FocusEvent arg0) {}
}
