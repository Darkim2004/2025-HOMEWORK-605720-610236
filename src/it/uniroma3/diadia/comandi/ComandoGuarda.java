package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends AbstractComando {
	
	public void esegui(Partita partita, IO io) {
		io.mostraMessaggio("Sei nella stanza "+ partita.getStanzaCorrente().getDescrizione());
		io.mostraMessaggio(partita.getGiocatore().getDescrizione());
	}

	@Override
	public String getNome() {
		return "guarda";
	}
}
