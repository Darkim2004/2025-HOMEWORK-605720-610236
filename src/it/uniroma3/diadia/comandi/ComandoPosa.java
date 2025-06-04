package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa extends AbstractComando {
	
	private String nomeAttrezzo;

	@Override
	public void esegui(Partita partita, IO io) {
		
		if(nomeAttrezzo==null) {
			io.mostraMessaggio("Quale attrezzo vuoi posare ?");
			return;
		}
		else {
			if(partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo) != null) { // Se trovo l'attrezzo
				Attrezzo attrezzoPosato = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
				partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo); // Rimuovo dalla borsa
				if(partita.getStanzaCorrente().addAttrezzo(attrezzoPosato) == false) { // Aggiungo alla stanza e controllo se piena
					io.mostraMessaggio("Stanza piena!");
					partita.getGiocatore().getBorsa().addAttrezzo(attrezzoPosato); // Rimetti l'oggetto al suo posto
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

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}

	@Override
	public String getNome() {
		return "posa";
	}

	@Override
	public String getParametro() {
		return nomeAttrezzo;
	}

}
