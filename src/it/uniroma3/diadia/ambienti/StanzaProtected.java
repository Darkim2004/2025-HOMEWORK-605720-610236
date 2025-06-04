package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
 */

public class StanzaProtected {

	static final protected int NUMERO_MASSIMO_DIREZIONI = 4;
	static final protected int NUMERO_MASSIMO_ATTREZZI = 10;

	protected String nome;
	protected HashSet<Attrezzo> attrezzi;
	protected int numeroAttrezzi;
	protected HashMap<String, Stanza> stanzeAdiacenti; // La direzione è la chiave, la stanza è l'oggetto
	protected int numeroStanzeAdiacenti;
	protected ArrayList<String> direzioni;

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * @param nome il nome della stanza
	 */
	public StanzaProtected(String nome) {
		this.nome = nome;
		this.stanzeAdiacenti = new HashMap<String, Stanza>();
		this.numeroStanzeAdiacenti = 0;
		this.attrezzi = new HashSet<Attrezzo>();
		this.numeroAttrezzi = 0;
		this.direzioni = new ArrayList<String>();
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
	 */
	public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
		if (!stanzeAdiacenti.containsKey(direzione)) {	// Se non ho nessuna stanza in quella direzione
			if (numeroStanzeAdiacenti < 4) {
				stanzeAdiacenti.put(direzione, stanza);
				numeroStanzeAdiacenti++;
				this.direzioni.add(direzione);
			}
		}
		else { // Se ho già una stanza in quella direzione
			if(stanzeAdiacenti.size() < NUMERO_MASSIMO_DIREZIONI) {
				stanzeAdiacenti.put(direzione, stanza); // Aggiorna la stanza associata a una direzione esistente
			}
		}
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(String direzione) {
		return stanzeAdiacenti.get(direzione);
	}

	/**
	 * Restituisce la nome della stanza.
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * @return la collezione di attrezzi nella stanza.
	 */
	public HashSet<Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}

	/**
	 * Restituisce il numero di attrezzi presenti nella stanza.
	 * @return Il numero di attrezzi nella stanza.
	 */
	public int getnumeroAttrezzi() {
		return this.numeroAttrezzi;
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (attrezzo != null && this.numeroAttrezzi < NUMERO_MASSIMO_ATTREZZI) {
			this.attrezzi.add(attrezzo);
			this.numeroAttrezzi++;
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza,
	 * stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite: ");
		for (String direzione : this.direzioni)
			if (direzione!=null)
				risultato.append(" " + direzione);
		risultato.append("\nAttrezzi nella stanza: ");
		for (Attrezzo attrezzo : this.attrezzi) {
			if(attrezzo != null) {
				risultato.append(attrezzo.toString()+" "); //AGGIUNTO FILTRO NULL PER EVITARE CASINI 
			}
		}
		return risultato.toString();
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		boolean trovato;
		trovato = false;
		for (Attrezzo attrezzo : this.attrezzi) {
			if (attrezzo != null && attrezzo.getNome().equals(nomeAttrezzo)) // Aggiunto controllo per null
				trovato = true;
		}
		return trovato;
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
	 * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo attrezzoCercato;
		attrezzoCercato = null;
		for (Attrezzo attrezzo : this.attrezzi) {
			if ( attrezzo != null && attrezzo.getNome().equals(nomeAttrezzo)) { // Aggiunto un controllo per il null
				attrezzoCercato = attrezzo;
				break;
			}
		}
		return attrezzoCercato;
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		return attrezzi.remove(attrezzo);
	}

	/**
	 * Ritorna un array con le direzioni delle stanze adiacenti occupate
	 * @return array di String con le direzioni occupate
	 */
	public String[] getDirezioni() {
		/*String[] direzioni = new String[this.numeroStanzeAdiacenti];
		for(int i=0; i<this.numeroStanzeAdiacenti; i++)
			direzioni[i] = this.direzioni[i];
		return direzioni;*/
		return direzioni.toArray(new String[0]);
	}

}