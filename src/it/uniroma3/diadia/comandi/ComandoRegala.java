package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoRegala extends AbstractComando{
	private String attrezzoDaRegalare;

	@Override
	public void esegui(Partita partita, IO io) {
		if(this.attrezzoDaRegalare == null || !partita.getGiocatore().getBorsa().hasAttrezzo(attrezzoDaRegalare)) {
			io.mostraMessaggio("Non hai questo oggetto!");
			return;
		}
		if(partita.getStanzaCorrente().getPersonaggio() == null) {
			Comando posa = new ComandoPosa();
			posa.setParametro(attrezzoDaRegalare);
			posa.esegui(partita, io);
			return;
		}
		Attrezzo attrezzo = partita.getGiocatore().getBorsa().getAttrezzo(attrezzoDaRegalare);
		io.mostraMessaggio(partita.getStanzaCorrente().getPersonaggio().riceviRegalo(attrezzo, partita));
		partita.getGiocatore().getBorsa().removeAttrezzo(attrezzoDaRegalare);
		return;
	}
	
	@Override
	public String getNome() {
		return "regala";
	}

	@Override
	public void setParametro(String parametro) {
		this.attrezzoDaRegalare = parametro;
	}

	@Override
	public String getParametro() {
		return this.attrezzoDaRegalare;
	}
}
