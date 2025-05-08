package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando {
	private String nomeAttrezzo;
	
	public ComandoPrendi(String parametro) {
		this.nomeAttrezzo = parametro;
	}
	
	@Override
	public void esegui(Partita partita, IO io) {
		if(nomeAttrezzo == null) {
			io.mostraMessaggio("Cosa vuoi prendere? Devi specificare un attrezzo");
			return;
		}
		
		if(partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo) != null) {
			Attrezzo attrezzoPreso = partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
			partita.getStanzaCorrente().removeAttrezzo(attrezzoPreso); // Lo rimuovo da stanza
			if(partita.getGiocatore().getBorsa().addAttrezzo(attrezzoPreso) == false) { // Lo aggiungo alla borsa e controllo lo abbia fatto
				io.mostraMessaggio("Borsa piena!");
				partita.getStanzaCorrente().addAttrezzo(attrezzoPreso); // Rimetti l'oggetto al suo posto
			}
			else {
				io.mostraMessaggio("L'oggetto " + nomeAttrezzo + " è ora nella tua borsa");
			}
		}
		else {
			io.mostraMessaggio("Oggetto non trovato");
		}
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}

	@Override
	public String getNome() {
		return "prendi";
	}

	@Override
	public String getParametro() {
		return nomeAttrezzo;
	}

}
