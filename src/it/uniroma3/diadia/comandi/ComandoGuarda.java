package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando {
	
	public void esegui(Partita partita, IO io) {
		io.mostraMessaggio("Sei nella stanza "+ partita.getStanzaCorrente().getDescrizione());
		io.mostraMessaggio(partita.getGiocatore().getDescrizione());
	}
	
	public void setParametro(String parametro) {
		
	}

	@Override
	public String getNome() {
		return "guarda";
	}

	@Override
	public String getParametro() {
		return null;
	}
}
