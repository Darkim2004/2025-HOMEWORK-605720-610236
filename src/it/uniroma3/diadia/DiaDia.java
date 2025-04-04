package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};

	private Partita partita;
	private IOConsole io;

	public DiaDia() {
		this.partita = new Partita();
		this.io = new IOConsole();
	}

	public void gioca() {
		String istruzione; 

		io.mostraMessaggio(MESSAGGIO_BENVENUTO);		
		do		
			istruzione = io.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);

		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if (comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		else io.mostraMessaggio("Comando sconosciuto");
		if (this.partita.vinta()) {
			io.mostraMessaggio("Hai vinto!");
			return true;
		} else
			return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			io.mostraMessaggio(elencoComandi[i]+" ");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null) {
			io.mostraMessaggio("Dove vuoi andare ?");
			return;
		}
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			io.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(cfu-1); //NON CONTEGGIAVA BENE I CFU CON CFU--
		}
		io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		io.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}
	
	/**
	 * Comando "prendi".
	 */
	private void prendi(String nomeAttrezzo) {
		if(nomeAttrezzo==null) {
			io.mostraMessaggio("Quale attrezzo vuoi prendere ?");
			return;
		}
		else {
			// Se trovo l'attrezzo
			if(this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo) != null) {
				Attrezzo attrezzoPreso = this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
				this.partita.getStanzaCorrente().removeAttrezzo(attrezzoPreso); // Lo rimuovo da stanza
				if(this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzoPreso) == false) { // Lo aggiungo alla borsa e controllo lo abbia fatto
					io.mostraMessaggio("Borsa piena!");
					this.partita.getStanzaCorrente().addAttrezzo(attrezzoPreso); // Rimetti l'oggetto al suo posto
				}
				else {
					io.mostraMessaggio("L'oggetto " + nomeAttrezzo + " è ora nella tua borsa");
				}
			}
			else {
				io.mostraMessaggio("Oggetto non trovato");
			}
		}
		
	}
	
	/**
	 * Comando "posa".
	 */
	private void posa(String nomeAttrezzo) {
		if(nomeAttrezzo==null) {
			io.mostraMessaggio("Quale attrezzo vuoi posare ?");
			return;
		}
		else {
			// Se trovo l'attrezzo
			if(this.partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo) != null) {
				Attrezzo attrezzoPosato = this.partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
				this.partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo); // Rimuovo dalla borsa
				if(this.partita.getStanzaCorrente().addAttrezzo(attrezzoPosato) == false) { // Aggiungo alla stanza e controllo se piena
					io.mostraMessaggio("Stanza piena!");
					this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzoPosato); // Rimetti l'oggetto al suo posto
				}
				else {
					io.mostraMessaggio("Hai lasciato l'oggetto " + nomeAttrezzo);
				}
			}
			else {
				io.mostraMessaggio("Oggetto non trovato");
			}
		}
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
}