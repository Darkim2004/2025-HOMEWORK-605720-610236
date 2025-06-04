package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Labirinto {

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;

	private Labirinto(String nomeFile) throws FileNotFoundException, FormatoFileNonValidoException {
		CaricatoreLabirinto c;
		c = new CaricatoreLabirinto(nomeFile);
		c.carica();
		this.stanzaIniziale = c.getStanzaIniziale();
		this.stanzaVincente = c.getStanzaVincente();
	}
	
	private Labirinto() {}
	
	public static LabirintoBuilder newBuilder(String labirinto) throws FileNotFoundException, FormatoFileNonValidoException {
		return new LabirintoBuilder(labirinto);
	}

	/**
	 * Crea tutte le stanze e le porte di collegamento
	 */
	/*private void creaStanze() {

		/* crea gli attrezzi */
	/*Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);

		/* crea stanze del labirinto */
	/*Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");

		/* collega le stanze */
	/*atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		biblioteca.impostaStanzaAdiacente("sud", atrio);

		/* pone gli attrezzi nelle stanze */
	/*aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);

		// il gioco comincia nell'atrio
		stanzaIniziale = atrio;
		stanzaVincente = biblioteca;
	}*/

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}

	public void setStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;
	}
	
	// Nested class LabirintoBuilder

	public static class LabirintoBuilder {
		private Labirinto labirinto;
		private Stanza ultimaStanza;
		private HashMap<String, Stanza> nome2stanza;

		public LabirintoBuilder(String labirinto) throws FileNotFoundException, FormatoFileNonValidoException {
			this.labirinto = new Labirinto(labirinto);
			this.nome2stanza = new HashMap<>();
		}
		
		public LabirintoBuilder() throws FileNotFoundException, FormatoFileNonValidoException {
			labirinto = new Labirinto();
			this.nome2stanza = new HashMap<>();
		}

		public LabirintoBuilder addStanzaIniziale(String nome) {
			Stanza stanzaIniziale = new Stanza(nome);
			labirinto.setStanzaIniziale(stanzaIniziale);
			ultimaStanza = stanzaIniziale;
			nome2stanza.put(nome, stanzaIniziale);
			return this;
		}

		public LabirintoBuilder addStanzaVincente(String nome) {
			Stanza stanzaVincente = new Stanza(nome);
			labirinto.setStanzaVincente(stanzaVincente);
			ultimaStanza = stanzaVincente;
			nome2stanza.put(nome, stanzaVincente);
			return this;
		}

		public LabirintoBuilder addStanza(String nome) {
			Stanza stanza = new Stanza(nome);
			ultimaStanza = stanza;
			nome2stanza.put(nome, stanza);
			return this;
		}

		public LabirintoBuilder addAttrezzo(String nome, int peso) {
			if(ultimaStanza != null) {
				ultimaStanza.addAttrezzo(new Attrezzo(nome, peso));
			}
			return this;
		}

		public LabirintoBuilder addAdiacenza(String da, String a, String direzione) {
			Stanza stanza1 = nome2stanza.get(da);
			Stanza stanza2 = nome2stanza.get(a);
			if(stanza1 != null && stanza2 != null) {
				stanza1.impostaStanzaAdiacente(Direzione.valueOf(direzione), stanza2);
			}
			return this;
		}

		public LabirintoBuilder addStanzaBuia(String nome, String attrezzo) {
			Stanza stanzaBuia = new StanzaBuia(nome, attrezzo);
			ultimaStanza = stanzaBuia;
			nome2stanza.put(nome, stanzaBuia);
			return this;
		}

		public LabirintoBuilder addStanzaBloccata(String nome, Direzione direzione, String nomeAttrezzo) {
			Stanza stanzaBloccata = new StanzaBloccata(nome, direzione, nomeAttrezzo);
			ultimaStanza = stanzaBloccata;
			nome2stanza.put(nome, stanzaBloccata);
			return this;
		}

		public LabirintoBuilder addStanzaMagica(String nome, int soglia) {
			Stanza stanzaMagica = new StanzaMagica(nome, soglia);
			ultimaStanza = stanzaMagica;
			nome2stanza.put(nome, stanzaMagica);
			return this;
		}

		public Labirinto getLabirinto() {
			return this.labirinto;
		}

		public Map<String, Stanza> getListaStanze() {
			return this.nome2stanza;
		}
	}
}
