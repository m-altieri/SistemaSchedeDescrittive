package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import entita.Scheda;
import entita.Template;

/**
 * 
 */
public class PannelloProduciSchede extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextArea txtTestoStatico;
	private ArrayList<JCheckBox> attributi;
	private LinkedHashMap<String, Boolean> info;
	private JTextField txtNome;
	private JComboBox<String> cmbTipo;
	private JList<String> lstVincoli;
	private DefaultListModel<String> listModel;
	private JPanel pannelloAttributi;
	
	public PannelloProduciSchede() {
		
		super();
		
		setLayout(new BorderLayout());
		
		JPanel panel = new JPanel(new GridLayout(0, 2));
		panel.setPreferredSize(new Dimension(0, 400));
		Font font = new Font("Arial", Font.PLAIN, 18);
		info = new LinkedHashMap<String, Boolean>();
		
		JPanel north = new JPanel(new FlowLayout());
		north.setPreferredSize(new Dimension(0, 300));
		
		JPanel pannelloNome = new JPanel(new FlowLayout());
		JLabel lblNome = new JLabel("Nome scheda");
		lblNome.setFont(font);
		pannelloNome.add(lblNome);
		txtNome = new JTextField();
		txtNome.setFont(font);
		txtNome.setPreferredSize(new Dimension(500, 50));
		pannelloNome.add(txtNome);
		north.add(pannelloNome);
		
		JPanel pannelloTipo = new JPanel(new FlowLayout());
		JLabel lblTipo = new JLabel("Tipo scheda");
		lblTipo.setFont(font);
		pannelloTipo.add(lblTipo);
		cmbTipo = new JComboBox<String>();
		cmbTipo.setFont(font);
		cmbTipo.setPreferredSize(new Dimension(500, 50));
		cmbTipo.addItem("Personale");
		cmbTipo.addItem("Strumentazione");
		cmbTipo.addItem("Spazio");
		cmbTipo.addItem("Utilizzo");
		cmbTipo.setEditable(true);
		cmbTipo.setSelectedItem("");
		pannelloTipo.add(cmbTipo);
		north.add(pannelloTipo);
		add(north, BorderLayout.NORTH);
		
		JPanel attrvinc = new JPanel(new GridLayout(1, 2));
		attributi = new ArrayList<JCheckBox>();
		pannelloAttributi = new JPanel(new GridLayout(0, 1));
		attrvinc.add(pannelloAttributi);
		
		JPanel pannelloVincoli = new JPanel(new FlowLayout());
		pannelloVincoli.setVisible(false);
		JButton cmdAggiungi = new JButton("Aggiungi vincolo");
		cmdAggiungi.setPreferredSize(new Dimension(200, 50));
		cmdAggiungi.setFont(font);
		pannelloVincoli.add(cmdAggiungi);
		JButton cmdRimuovi = new JButton("Rimuovi vincolo");
		cmdRimuovi.setPreferredSize(new Dimension(200, 50));
		cmdRimuovi.setFont(font);
		pannelloVincoli.add(cmdRimuovi);
		listModel = new DefaultListModel<String>();
		lstVincoli = new JList<String>(listModel);
		lstVincoli.setFont(font);
		JScrollPane scroll = new JScrollPane(lstVincoli);
		pannelloVincoli.add(scroll);
		attrvinc.add(pannelloVincoli);
		panel.add(attrvinc);
		
		JPanel pannelloTestoStatico = new JPanel(new FlowLayout());
		JLabel lblTestoStatico = new JLabel("Testo statico");
		lblTestoStatico.setFont(font);
		pannelloTestoStatico.add(lblTestoStatico);
		txtTestoStatico = new JTextArea();
		txtTestoStatico.setText(ottieniTestoStatico());
		txtTestoStatico.setPreferredSize(new Dimension(500, 200));
		txtTestoStatico.setLineWrap(true);
		txtTestoStatico.setWrapStyleWord(true);
		txtTestoStatico.setBorder(txtNome.getBorder());
		txtTestoStatico.setEditable(false);
		pannelloTestoStatico.add(txtTestoStatico);
		JButton cmdCambia = new JButton("Cambia...");
		cmdCambia.setFont(font);
		cmdCambia.setPreferredSize(new Dimension(200, 50));
		pannelloTestoStatico.add(cmdCambia);
		panel.add(pannelloTestoStatico);
		
		add(panel, BorderLayout.CENTER);
		
		JPanel pannelloProduci = new JPanel(new FlowLayout());
		pannelloProduci.setPreferredSize(new Dimension(0, 150));
		JButton cmdProduci = new JButton("Produci scheda");
		cmdProduci.setFont(font);
		cmdProduci.setPreferredSize(new Dimension(500, 80));
		cmdProduci.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				PannelloProduciSchede.this.produciScheda();	
			}
		});
		
		pannelloProduci.add(cmdProduci);
		add(pannelloProduci, BorderLayout.SOUTH);
		
		cmbTipo.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				boolean selezionato = e.getStateChange() == ItemEvent.SELECTED;
				
				if (selezionato) {
					
					@SuppressWarnings("unchecked")
					String tipo = (String) ((JComboBox<String>) e.getSource()).getSelectedItem();
					
					PannelloProduciSchede.this.azzeraAttributi();
					
					switch (tipo) {
					case "Personale":
						attributi.add(new JCheckBox("Nome"));
						attributi.add(new JCheckBox("Cognome"));
						attributi.add(new JCheckBox("Email"));
						attributi.add(new JCheckBox("Telefono"));
						attributi.add(new JCheckBox("Residenza"));
						attributi.add(new JCheckBox("Mansione"));
						attributi.add(new JCheckBox("CittaNascita"));
						break;
					case "Strumentazione":
						attributi.add(new JCheckBox("Nome"));
						attributi.add(new JCheckBox("Modello"));
						attributi.add(new JCheckBox("Marca"));
						attributi.add(new JCheckBox("Tipologia"));
						attributi.add(new JCheckBox("AnnoAcquisto"));
						break;
					case "Spazio":
						attributi.add(new JCheckBox("Nome"));
						attributi.add(new JCheckBox("Ubicazione"));
						attributi.add(new JCheckBox("NumeroFinestre"));
						attributi.add(new JCheckBox("NumeroPorte"));
						attributi.add(new JCheckBox("Grandezza"));
						break;
					case "Utilizzo":
						attributi.add(new JCheckBox("IdPersonale"));
						attributi.add(new JCheckBox("IdStrumentazione"));
						break;
					default:
						try {
							throw new InputInvalidoException(null);
						} catch (InputInvalidoException e1) {;}
						break;
					}
					
					for (JCheckBox c : attributi) {
						c.setFont(font);
						c.setPreferredSize(new Dimension(200, 100));
						c.addActionListener(PannelloProduciSchede.this);
						pannelloAttributi.add(c);
					}
					PannelloProduciSchede.this.paintAll(PannelloProduciSchede.this.getGraphics());
				}
				
				pannelloVincoli.setVisible(true);
			}
			
		});
		
		cmdCambia.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JFrame d = new JFrame();
				d.setLocation(1000, 500);
				d.setLayout(new BorderLayout());
				d.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				d.setTitle("Cambia testo statico");
				
				JTextArea txtTestoStatico = new JTextArea();
				txtTestoStatico.setLineWrap(true);
				txtTestoStatico.setWrapStyleWord(true);
				txtTestoStatico.setFont(font);
				txtTestoStatico.setText(PannelloProduciSchede.this.txtTestoStatico.getText());
				
				JScrollPane scroll = new JScrollPane(txtTestoStatico);
				scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
				scroll.setPreferredSize(new Dimension(500, 200));
				d.add(scroll, BorderLayout.CENTER);
				
				JButton cmdConferma = new JButton("Conferma");
				cmdConferma.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						File f = null;
						PrintWriter pw = null;
						String percorso = "TestoStatico.txt";
						
						f = new File(percorso);
						try {
							pw = new PrintWriter(f);
							pw.println(txtTestoStatico.getText());
							pw.flush();
							pw.close();
						} catch (FileNotFoundException g) {;}
						
						PannelloProduciSchede.this.txtTestoStatico.setText(txtTestoStatico.getText());
						d.dispose();
					}		
				});
				cmdConferma.setPreferredSize(new Dimension(300, 50));
				d.add(cmdConferma, BorderLayout.SOUTH);
				d.pack();
				d.setVisible(true);
			}
		});
		
		cmdAggiungi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				JFrame f = new JFrame();
				f.setLocationRelativeTo(null);
				f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				f.setTitle("Aggiungi vincolo");
				f.setSize(new Dimension(800, 300));
				f.setLayout(new BorderLayout());
				JPanel vincolo = new JPanel(new FlowLayout());
				JComboBox<String> cmbAttributo = new JComboBox<String>();
				cmbAttributo.setFont(font);
				cmbAttributo.setPreferredSize(new Dimension(200, 40));
				JComboBox<String> cmbOperatore = new JComboBox<String>();
				cmbOperatore.setPreferredSize(new Dimension(100, 40));
				cmbOperatore.setFont(font);
				JTextField txtValore = new JTextField();
				txtValore.setPreferredSize(new Dimension(200, 40));
				txtValore.setFont(font);
				cmbAttributo.setEditable(false);
				for (JCheckBox c : attributi) {
					cmbAttributo.addItem(c.getText());
				}
				cmbOperatore.addItem("=");
				cmbOperatore.addItem("<");
				cmbOperatore.addItem(">");
				cmbOperatore.addItem("!=");
				cmbOperatore.addItem("Simile");
				JTextArea legenda = new JTextArea("Se il valore da inserire è una stringa, scriverlo tra apici ('). \nSe si usa l'operatore \"Simile\", si può usare il simbolo % per rappresentare "
						+ "un qualsiasi insieme di caratteri e il simbolo _ per rappresentare un singolo catattere. "
						+ "Es: i nomi che iniziano con la lettera R: Nome Simile 'R%'");
				legenda.setPreferredSize(new Dimension(f.getWidth() - 50, f.getHeight()));
				legenda.setLineWrap(true);
				legenda.setFont(font);
				legenda.setBackground(f.getBackground());
				vincolo.add(cmbAttributo);
				vincolo.add(cmbOperatore);
				vincolo.add(txtValore);
				vincolo.add(legenda);
				f.add(vincolo, BorderLayout.CENTER);
				JButton cmdConferma = new JButton("Conferma");
				cmdConferma.setPreferredSize(new Dimension(300, 50));
				cmdConferma.setFont(font);
				cmdConferma.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// AGGIUSTARE
						PannelloProduciSchede.this.listModel.addElement(cmbAttributo.getSelectedItem() + " " + cmbOperatore.getSelectedItem() + " " + txtValore.getText());
						f.dispose();
					}
					
				});
				f.add(cmdConferma, BorderLayout.SOUTH);
				f.setVisible(true);
			}
		});
		
		cmdRimuovi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (!lstVincoli.isSelectionEmpty())
					listModel.remove(lstVincoli.getSelectedIndex());
			}
		});
	}
	
	private void azzeraAttributi() {
		
		for (JCheckBox c : attributi) {
			pannelloAttributi.remove(c);
		}
		attributi.clear();
		info.clear();
		listModel.clear();	
	}

	private void produciScheda() {

		try {
			boolean inputErrato = txtNome.getText().isEmpty() || cmbTipo.getSelectedItem().toString().isEmpty() || info.isEmpty();
			if (!inputErrato) {
				Template t = new Template();
				t.setTestoStatico(txtTestoStatico.getText());
				t.setAttributi(info);
				Scheda s = new Scheda(cmbTipo.getSelectedItem().toString(), t, txtNome.getText());
				
				int listModelSize = listModel.size();
				for (int i = 0; i < listModelSize; i++) {
					String vincolo = listModel.getElementAt(i);
					vincolo = vincolo.replace("Simile", "LIKE");
					s.aggiungiVincolo(vincolo);
				}
				
				s.produci();
				JOptionPane.showMessageDialog(null, "Scheda prodotta. Controllare nella cartella schede.", "Successo", JOptionPane.INFORMATION_MESSAGE);
			} else {
				throw new InputInvalidoException(null);
			}
		} catch (InputInvalidoException f) {;}	
	}

	private String ottieniTestoStatico() {
		
		String testo = "";
		File file = null;
		Scanner s = null;
		
		file = new File("TestoStatico.txt");
		try {
			s = new Scanner(file);
			while (s.hasNextLine())
				testo = testo + s.nextLine() + "\n";
		} catch (FileNotFoundException e) {;}
		
		return testo;
	}

	// Eseguito quando si clicca sulle checkbox
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JCheckBox source = (JCheckBox) (e.getSource());
		if (source.isSelected())
			info.put(source.getText(), true);
		else
			info.remove(source.getText());
	}
}
