package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import database.Database;
import entità.Personale;
import entità.Spazio;
import entità.Strumentazione;

public class PannelloEliminaDati extends PannelloGestioneDati {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VisualizzaTabella tPersonale;
	private VisualizzaTabella tStrumentazione;
	private VisualizzaTabella tSpazio;
	private JPanel form;
	private CampoCredenziale id;
	private JButton cmdConferma;
	
	public PannelloEliminaDati() {
		
		super();
		
		tPersonale = new VisualizzaTabella(Personale.class);
		tStrumentazione = new VisualizzaTabella(Strumentazione.class);
		tSpazio = new VisualizzaTabella(Spazio.class);
		
		form = new JPanel();
		id = new CampoCredenziale("ID");
		cmdConferma = new JButton("Elimina");
		cmdConferma.setActionCommand("cmdConferma");
		cmdConferma.addActionListener(this);
		form.setLayout(new FlowLayout());
		form.add(id);
		form.add(cmdConferma);
		
		this.add(form, BorderLayout.SOUTH);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		

		if (e.getActionCommand().equals("Personale")) {
			
			this.remove(tStrumentazione);
			this.remove(tSpazio);
			this.remove(tPersonale);
			
			tPersonale = new VisualizzaTabella(Personale.class);
			this.add(tPersonale, BorderLayout.CENTER);
			this.paintAll(this.getGraphics());
		}
		
		if (e.getActionCommand().equals("Strumentazione")) {
			
			this.remove(tPersonale);
			this.remove(tSpazio);
			this.remove(tStrumentazione);
			
			tStrumentazione = new VisualizzaTabella(Strumentazione.class);
			this.add(tStrumentazione, BorderLayout.CENTER);
			this.paintAll(this.getGraphics());
		}
		
		if (e.getActionCommand().equals("Spazio")) {
			
			this.remove(tPersonale);
			this.remove(tStrumentazione);
			this.remove(tSpazio);

			tSpazio = new VisualizzaTabella(Spazio.class);
			this.add(tSpazio, BorderLayout.CENTER);
			this.paintAll(this.getGraphics());
		}
		
		if (e.getActionCommand().equals("cmdConferma")) {
			
			try { 
									
				if (id.getText().equals("") || id.getText().equals("ID")) {
	
					throw new InputInvalidoException(this);
				}
				
				int risposta = JOptionPane.showConfirmDialog(this, "Sicuro? Eventuali dati referenziati verranno rimossi", "Conferma", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (risposta == JOptionPane.CLOSED_OPTION || risposta == JOptionPane.NO_OPTION) {
					return;
				}
				
				Database dbElementi = null;
				String query = null;
				
				String tabella = null;
				if (super.rdPersonale.isSelected())
					tabella = "Personale";
				else if (super.rdStrumentazione.isSelected())
					tabella = "Strumentazione";
				else
					tabella = "Spazio";
				
				try {
					dbElementi = new Database();
					query = "DELETE FROM " + tabella + " WHERE id = " + Integer.parseInt(id.get());
					System.out.println(query);
					dbElementi.eseguiQuery(query);
				} catch (Exception f) {
					
				}
				
				try {
					if (tabella.equals("Personale"))
						tPersonale.caricaPannelloDati();
					else if (tabella.equals("Strumentazione"))
						tStrumentazione.caricaPannelloDati();
					else
						tSpazio.caricaPannelloDati();
				} catch (ClassNotFoundException | IOException e1) {
					
					e1.printStackTrace();
				}
			} catch (InputInvalidoException f) {
				
			}
		}
	}

}
