package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio{
	private static final String MESSAGGIO_PACIFICO = "Annuso, scodinzolo, e non ti mordo, " +
	        "hai il mio osso... sei nel giusto accordo!";
	private static final String MESSAGGIO_AGGRESSIVO = "Grrrr! Non fiuto ciò che voglio, " +
	        "un morso ti do, e via dal mio orgoglio!";
	private static final String CIBO_PREFERITO = "osso";
	private Attrezzo attrezzoDelCane;

	public Cane(String nome, String presentaz, Attrezzo attrezzoDelCane) {
		super(nome, presentaz);
		this.attrezzoDelCane = attrezzoDelCane;
	}

	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		return MESSAGGIO_AGGRESSIVO;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if(attrezzo.getNome().equals(CIBO_PREFERITO)) {
			partita.getStanzaCorrente().addAttrezzo(attrezzoDelCane);
			return MESSAGGIO_PACIFICO;
		}
		return this.agisci(partita);
	}

}
