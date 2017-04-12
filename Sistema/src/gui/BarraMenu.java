package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Barra dei menù presente nella finestra principale della GUI.
 * Permette di selezionare le funzioni del sistema.
 */
@SuppressWarnings("serial")
public class BarraMenu extends JMenuBar implements ActionListener {

	private PannelloInserisciDati pid;
	private PannelloModificaDati pmd;
	private PannelloVisualizzaDati pvd;
	private PannelloEliminaDati ped;
	private PannelloGestisciRelazioni pgr;
	private PannelloProduciSchede pps;
	
	private Client finestra;
	
	/**
	 * Crea la barra e tutti i suoi item.
	 * Inoltre, stabilisce le azioni da compiere quando si clicca sui vari item.
	 * Ogni item fa apparire un nuovo componente della GUI, che può essere una finestra popup o un pannello che 
	 * va a posizionarsi sul client principale.
	 * @param finestra L'oggetto Client.
	 */
	public BarraMenu(Client finestra) {
		
		super();
		
		final int GRANDEZZA_FONT = 16;
		final int LARGHEZZA = 500;
		final int ALTEZZA = 50;
		
		this.finestra = finestra;
		Font fontMenu = new Font("Arial", Font.PLAIN, GRANDEZZA_FONT);
		setPreferredSize(new Dimension(LARGHEZZA, ALTEZZA));
		
		pid = new PannelloInserisciDati();
		pmd = new PannelloModificaDati();
		pvd = new PannelloVisualizzaDati();
		ped = new PannelloEliminaDati();
		pgr = new PannelloGestisciRelazioni();
		pps = new PannelloProduciSchede();

		JMenu dati = new JMenu("Dati");
		JMenu amministrazione = new JMenu("Amministrazione");
		JMenu aiuto = new JMenu("Aiuto");
		JMenu account = new JMenu("Account");
		JMenuItem modifica = new JMenuItem("Modifica dati");
		JMenuItem elimina = new JMenuItem("Elimina dati");
		JMenuItem inserisci = new JMenuItem("Inserisci dati");
		JMenuItem visualizza = new JMenuItem("Visualizza dati");
		JMenuItem relazioni = new JMenuItem("Gestisci relazioni");
		JMenuItem produciSchede = new JMenuItem("Produci schede");
		JMenuItem cambiaCodiceAmministratore = new JMenuItem("Cambia codice amministratore...");
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
		relazioni.setFont(fontMenu);
		produciSchede.setFont(fontMenu);
		cambiaCodiceAmministratore.setFont(fontMenu);
		manualeUtente.setFont(fontMenu);
		cambiaPassword.setFont(fontMenu);
		logout.setFont(fontMenu);
		diventaAmministratore.setFont(fontMenu);
		
		inserisci.setActionCommand("Inserimento dati");
		inserisci.addActionListener(this);
		modifica.setActionCommand("Modifica dati");
		modifica.addActionListener(this);
		elimina.setActionCommand("Eliminazione dati");
		elimina.addActionListener(this);
		visualizza.setActionCommand("Visualizzazione dati");
		visualizza.addActionListener(this);
		relazioni.setActionCommand("Gestione relazioni");
		relazioni.addActionListener(this);
		produciSchede.setActionCommand("Produzione schede");
		produciSchede.addActionListener(this);
		
		modifica.addActionListener(this);
		elimina.addActionListener(this);
		inserisci.addActionListener(this);
		visualizza.addActionListener(this);
		relazioni.addActionListener(this);
		produciSchede.addActionListener(this);
		
		cambiaCodiceAmministratore.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				FinestraCambiaCodice fcc = new FinestraCambiaCodice();
				fcc.setVisible(true);
			}
			
		});
		manualeUtente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ManualeUtente mu = new ManualeUtente();
				mu.setVisible(true);
			}
			
		});
		cambiaPassword.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				FinestraCambiaPassword cp = new FinestraCambiaPassword(finestra.getUser());
				cp.setVisible(true);
			}
			
		});
		logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				finestra.dispose();
				Login l = new Login();
				l.setVisible(true);
			}
			
		});
		diventaAmministratore.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				FinestraDiventaAdmin fda = new FinestraDiventaAdmin(finestra.getUser());
				fda.setVisible(true);
			}
			
		});
		
		dati.add(inserisci);
		dati.add(modifica);
		dati.add(elimina);
		dati.add(visualizza);
		dati.add(relazioni);
		
		amministrazione.add(produciSchede);
		amministrazione.add(cambiaCodiceAmministratore);
		
		aiuto.add(manualeUtente);
		
		account.add(cambiaPassword);
		account.add(logout);
		account.add(diventaAmministratore);
		
		if (!finestra.getAdmin()) {
			amministrazione.setEnabled(false);
		}
		
		add(dati);
		add(amministrazione);
		add(aiuto);
		add(account);
	}
	
	/**
	 * Rimuove il pannello attualmente impostato sul Client, qualunque esso sia.
	 */
	private void rimuoviPannelli() {
		
		finestra.remove(pid);
		finestra.remove(pmd);
		finestra.remove(ped);
		finestra.remove(pvd);
		finestra.remove(pgr);
		finestra.remove(pps);
	}

	/**
	 * Questo metodo è dedicato ai pulsanti inserisci, modifica, elimina, visualizza, gestisci relazioni, e produci schede.
	 * Per le altre funzioni l'action listener è all'interno del costruttore.
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		rimuoviPannelli();
		finestra.setTitle("Sistema Schede Descrittive - " + e.getActionCommand());
		
		switch (e.getActionCommand()) {
		case "Inserimento dati":
			pid = new PannelloInserisciDati();
			finestra.add(pid, BorderLayout.CENTER);
			break;
		case "Modifica dati":
			pmd = new PannelloModificaDati();
			finestra.add(pmd, BorderLayout.CENTER);
			break;
		case "Eliminazione dati":
			ped = new PannelloEliminaDati();
			finestra.add(ped, BorderLayout.CENTER);
			break;
		case "Visualizzazione dati":
			pvd = new PannelloVisualizzaDati();
			finestra.add(pvd, BorderLayout.CENTER);
			break;
		case "Gestione relazioni":
			pgr = new PannelloGestisciRelazioni();
			finestra.add(pgr, BorderLayout.CENTER);
			break;
		case "Produzione schede":
			pps = new PannelloProduciSchede();
			finestra.add(pps, BorderLayout.CENTER);
			break;
		default:
			break;
		}
		
		finestra.paintAll(finestra.getGraphics());
	}

}
