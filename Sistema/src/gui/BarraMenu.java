package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class BarraMenu extends JMenuBar implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame finestra;
	private JMenu dati;
	private JMenu amministrazione;
	private JMenu aiuto;
	private JMenu account;
	private JMenuItem modifica;
	private JMenuItem elimina;
	private JMenuItem inserisci;
	private JMenuItem importa;
	private JMenuItem visualizza;
	private JMenuItem produciSchede;
	private JMenuItem cambiaCodiceAmministratore;
	private JMenuItem cambiaCredenzialiDatabase;
	private JMenuItem manualeUtente;
	private JMenuItem cambiaPassword;
	private JMenuItem logout;
	private JMenuItem diventaAmministratore;
	private Font font;
	
	private PannelloInserisciDati pid;
	private PannelloModificaDati pmd;
	private PannelloVisualizzaDati pvd;
	private PannelloEliminaDati ped;
	
	public BarraMenu(JFrame finestra) {
		
		super();
		
		this.finestra = finestra;
		
		this.font = new Font("Arial", Font.PLAIN, 16);
		this.setPreferredSize(new Dimension(500,50));
		
		this.pid = new PannelloInserisciDati();
		this.pmd = new PannelloModificaDati();
		this.pvd = new PannelloVisualizzaDati();
		this.ped = new PannelloEliminaDati();
		
		this.dati = new JMenu("Dati");
		this.amministrazione = new JMenu("Amministrazione");
		this.aiuto = new JMenu("Aiuto");
		this.account = new JMenu("Account");
		this.modifica = new JMenuItem("Modifica dati");
		this.elimina = new JMenuItem("Elimina dati");
		this.inserisci = new JMenuItem("Inserisci dati");
		this.importa = new JMenuItem("Importa dati");
		this.visualizza = new JMenuItem("Visualizza dati");
		this.produciSchede = new JMenuItem("Produci schede");
		this.cambiaCodiceAmministratore = new JMenuItem("Cambia codice amministratore...");
		this.cambiaCredenzialiDatabase = new JMenuItem("Cambia credenziali database...");
		this.manualeUtente = new JMenuItem("Manuale utente");
		this.cambiaPassword = new JMenuItem("Cambia password...");
		this.logout = new JMenuItem("Logout");
		this.diventaAmministratore = new JMenuItem("Diventa amministratore...");
		
		this.dati.setFont(font);
		this.amministrazione.setFont(font);
		this.aiuto.setFont(font);
		this.account.setFont(font);
		this.modifica.setFont(font);
		this.elimina.setFont(font);
		this.inserisci.setFont(font);
		this.importa.setFont(font);
		this.visualizza.setFont(font);
		this.produciSchede.setFont(font);
		this.cambiaCodiceAmministratore.setFont(font);
		this.cambiaCredenzialiDatabase.setFont(font);
		this.manualeUtente.setFont(font);
		this.cambiaPassword.setFont(font);
		this.logout.setFont(font);
		this.diventaAmministratore.setFont(font);
		
		modifica.addActionListener(this);
		elimina.addActionListener(this);
		inserisci.addActionListener(this);
		importa.addActionListener(this);
		visualizza.addActionListener(this);
		produciSchede.addActionListener(this);
		cambiaCodiceAmministratore.addActionListener(this);
		cambiaCredenzialiDatabase.addActionListener(this);
		manualeUtente.addActionListener(this);
		cambiaPassword.addActionListener(this);
		logout.addActionListener(this);
		diventaAmministratore.addActionListener(this);
		
		dati.add(inserisci);
		dati.add(modifica);
		dati.add(elimina);
		dati.add(visualizza);
		dati.add(importa);
		
		amministrazione.add(produciSchede);
		amministrazione.add(cambiaCodiceAmministratore);
		amministrazione.add(cambiaCredenzialiDatabase);
		
		aiuto.add(manualeUtente);
		
		account.add(cambiaPassword);
		account.add(logout);
		account.add(diventaAmministratore);
		
		this.add(dati);
		this.add(amministrazione);
		this.add(aiuto);
		this.add(account);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();
		
		if (command.equals("Inserisci dati")) {
			try {
				finestra.remove(pmd);
				finestra.remove(pvd);
				finestra.remove(ped);
			} catch (Exception f) {
				
			}
			finestra.add(pid, BorderLayout.CENTER);
			finestra.paintAll(finestra.getGraphics());
		}
		
		if (command.equals("Modifica dati")) {
			try {
				finestra.remove(pid);
				finestra.remove(ped);
				finestra.remove(pvd);
			} catch (Exception f) {
				
			}
			finestra.add(pmd, BorderLayout.CENTER);
			finestra.paintAll(finestra.getGraphics());
		}
		
		if (command.equals("Visualizza dati")) {
			try {
				finestra.remove(pmd);
				finestra.remove(pid);
				finestra.remove(ped);
			} catch (Exception f) {
				
			}
			try {
				pvd.aggiorna();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			finestra.add(pvd, BorderLayout.CENTER);
			finestra.paintAll(finestra.getGraphics());
		}
		
		if (command.equals("Elimina dati")) {
			try {
				finestra.remove(pid);
				finestra.remove(pmd);
				finestra.remove(pvd);
			} catch (Exception f) {
				
			}
//			ped = new PannelloEliminaDati();
			finestra.add(ped, BorderLayout.CENTER);
			finestra.paintAll(finestra.getGraphics());
		}
		
		if (command.equals("Importa dati")) {
			
		}
		
		if (command.equals("Produci schede")) {
			
		}
		
		if (command.equals("Cambia codice amministratore...")) {
			
		}
		
		if (command.equals("Cambia credenziali database...")) {
			
		}
		
		if (command.equals("Manuale utente")) {
			
		}
		
		if (command.equals("Cambia password...")) {
			
		}
		
		if (command.equals("Logout")) {
			
		}
		
		if (command.equals("Diventa amministratore...")) {
			
		}
	}
	
}
