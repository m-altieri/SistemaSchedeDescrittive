package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class BarraMenu extends JMenuBar implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Client finestra;
	
	private PannelloInserisciDati pid;
	private PannelloModificaDati pmd;
	private PannelloVisualizzaDati pvd;
	private PannelloEliminaDati ped;
	private PannelloProduciSchede pps;
	
	public BarraMenu(Client finestra) {
		
		super();
		
		this.finestra = finestra;

		final int GRANDEZZA_FONT = 16;
		final int LARGHEZZA = 500;
		final int ALTEZZA = 50;
		
		Font fontMenu = new Font("Arial", Font.PLAIN, GRANDEZZA_FONT);
		setPreferredSize(new Dimension(LARGHEZZA, ALTEZZA));
		
		pid = new PannelloInserisciDati();
		pmd = new PannelloModificaDati();
		pvd = new PannelloVisualizzaDati();
		ped = new PannelloEliminaDati();
		pps = new PannelloProduciSchede();

		JMenu dati = new JMenu("Dati");
		JMenu amministrazione = new JMenu("Amministrazione");
		JMenu aiuto = new JMenu("Aiuto");
		JMenu account = new JMenu("Account");
		JMenuItem modifica = new JMenuItem("Modifica dati");
		JMenuItem elimina = new JMenuItem("Elimina dati");
		JMenuItem inserisci = new JMenuItem("Inserisci dati");
		JMenuItem visualizza = new JMenuItem("Visualizza dati");
		JMenuItem produciSchede = new JMenuItem("Produci schede");
		JMenuItem cambiaCodiceAmministratore = new JMenuItem("Cambia codice amministratore...");
		JMenuItem cambiaCredenzialiDatabase = new JMenuItem("Cambia credenziali database...");
		JMenuItem manualeUtente = new JMenuItem("Manuale utente...");
		JMenuItem cambiaPassword = new JMenuItem("Cambia password...");
		JMenuItem logout = new JMenuItem("Logout");
		JMenuItem diventaAmministratore = new JMenuItem("Diventa amministratore...");
		
		dati.setFont(fontMenu);
		amministrazione.setFont(fontMenu);
		aiuto.setFont(fontMenu);
		account.setFont(fontMenu);
		modifica.setFont(fontMenu);
		elimina.setFont(fontMenu);
		inserisci.setFont(fontMenu);
		visualizza.setFont(fontMenu);
		produciSchede.setFont(fontMenu);
		cambiaCodiceAmministratore.setFont(fontMenu);
		cambiaCredenzialiDatabase.setFont(fontMenu);
		manualeUtente.setFont(fontMenu);
		cambiaPassword.setFont(fontMenu);
		logout.setFont(fontMenu);
		diventaAmministratore.setFont(fontMenu);
		
		modifica.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				finestra.remove(pid);
				finestra.remove(ped);
				finestra.remove(pvd);
				finestra.setTitle("Sistema schede descrittive - Modifica dati");
				pmd = new PannelloModificaDati();
				finestra.add(pmd, BorderLayout.CENTER);
				finestra.paintAll(finestra.getGraphics());
			}
			
		});
		elimina.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				finestra.remove(pmd);
				finestra.remove(pvd);
				finestra.remove(pid);
				finestra.setTitle("Sistema schede descrittive - Eliminazione dati");
				ped = new PannelloEliminaDati();
				finestra.add(ped, BorderLayout.CENTER);
				finestra.paintAll(finestra.getGraphics());
			}
			
		});
		inserisci.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				finestra.remove(pmd);
				finestra.remove(pvd);
				finestra.remove(ped);
				finestra.setTitle("Sistema schede descrittive - Inserimento dati");
				pid = new PannelloInserisciDati();
				finestra.add(pid, BorderLayout.CENTER);
				finestra.paintAll(finestra.getGraphics());
			}
			
		});
		visualizza.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				finestra.remove(pmd);
				finestra.remove(pid);
				finestra.remove(ped);
				finestra.setTitle("Sistema schede descrittive - Visualizzazione dati");
				pvd = new PannelloVisualizzaDati();
				finestra.add(pvd, BorderLayout.CENTER);
				finestra.paintAll(finestra.getGraphics());
			}
			
		});
		produciSchede.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				finestra.remove(pid);
				finestra.remove(pmd);
				finestra.remove(pvd);
				finestra.setTitle("Sistema schede descrittive - Produzione schede");
				pps = new PannelloProduciSchede();
				finestra.add(pps, BorderLayout.CENTER);
				finestra.paintAll(finestra.getGraphics());
			}
			
		});
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
		
		amministrazione.add(produciSchede);
		amministrazione.add(cambiaCodiceAmministratore);
		amministrazione.add(cambiaCredenzialiDatabase);
		
		aiuto.add(manualeUtente);
		
		account.add(cambiaPassword);
		account.add(logout);
		account.add(diventaAmministratore);
		
		if (!finestra.getAdmin())
			amministrazione.setEnabled(false);
		
		add(dati);
		add(amministrazione);
		add(aiuto);
		add(account);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();
		
		switch (command) {
//		case "Inserisci dati":
//			
//			finestra.remove(pmd);
//			finestra.remove(pvd);
//			finestra.remove(ped);
//			finestra.setTitle("Sistema schede descrittive - Inserimento dati");
//			pid = new PannelloInserisciDati();
//			finestra.add(pid, BorderLayout.CENTER);
//			finestra.paintAll(finestra.getGraphics());
//			break;
//			
//		case "Modifica dati":
//
//			finestra.remove(pid);
//			finestra.remove(ped);
//			finestra.remove(pvd);
//			finestra.setTitle("Sistema schede descrittive - Modifica dati");
//			pmd = new PannelloModificaDati();
//			finestra.add(pmd, BorderLayout.CENTER);
//			finestra.paintAll(finestra.getGraphics());
//			break;
//			
//		case "Elimina dati":
//		
//			finestra.remove(pmd);
//			finestra.remove(pvd);
//			finestra.remove(pid);
//			finestra.setTitle("Sistema schede descrittive - Eliminazione dati");
//			ped = new PannelloEliminaDati();
//			finestra.add(ped, BorderLayout.CENTER);
//			finestra.paintAll(finestra.getGraphics());
//			break;
//			
//		case "Visualizza dati":
//			
//			finestra.remove(pmd);
//			finestra.remove(pid);
//			finestra.remove(ped);
//			finestra.setTitle("Sistema schede descrittive - Visualizzazione dati");
//			pvd = new PannelloVisualizzaDati();
//			finestra.add(pvd, BorderLayout.CENTER);
//			finestra.paintAll(finestra.getGraphics());
//			break;
//			
//		case "Produci schede":
//		
//			finestra.remove(pid);
//			finestra.remove(pmd);
//			finestra.remove(pvd);
//			finestra.setTitle("Sistema schede descrittive - Produzione schede");
//			pps = new PannelloProduciSchede();
//			finestra.add(pps, BorderLayout.CENTER);
//			finestra.paintAll(finestra.getGraphics());
//			break;
//			
		case "Cambia codice amministratore...":
			
			FinestraCambiaCodice fcc = new FinestraCambiaCodice();
			fcc.setVisible(true);
			break;
		case "Cambia credenziali database...":
			
			break;
		case "Manuale utente...":
			
			ManualeUtente mu = new ManualeUtente();
			mu.setVisible(true);
			break;
		case "Cambia password...":
			
			FinestraCambiaPassword cp = new FinestraCambiaPassword(finestra.getUser());
			cp.setVisible(true);
			break;
		case "Logout":
			
			finestra.dispose();
			Login l = new Login();
			l.setVisible(true);
			break;
		case "Diventa amministratore...":
			
			FinestraDiventaAdmin fva = new FinestraDiventaAdmin(finestra.getUser());
			fva.setVisible(true);
			break;
		default:
			break;
		}
	}
	
}
