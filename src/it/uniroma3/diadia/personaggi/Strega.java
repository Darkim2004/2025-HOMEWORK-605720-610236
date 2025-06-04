package it.uniroma3.diadia.personaggi;

import java.util.List;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio{
	private static final String MESSAGGIO_BENEVOLO = "Che bel garbo, che cortesia, " +
	        "ti premio con un po' di magia! ";
	private static final String MESSAGGIO_IRRITATA = "Maleducato, che delusione... " +
	        "per te soltanto polvere e illusione! ";
	private static final String MESSAGGIO_RISATA = "HAHAHAHAHAHAHA";

	public Strega(String nome, String presentaz) {
		super(nome, presentaz);
	}

	@Override
	public String agisci(Partita partita) {
		List<Stanza> listaStanzaAdiacenti = partita.getStanzaCorrente().getListStanzeAdiacenti();
		
		Stanza stanzaMaxOggetti = listaStanzaAdiacenti.get(0);
		Stanza stanzaMinOggetti = listaStanzaAdiacenti.get(0);
		
		for(Stanza stanza : listaStanzaAdiacenti) {
			if(stanza.getNumeroAttrezzi() > stanzaMaxOggetti.getNumeroAttrezzi()) {
				stanzaMaxOggetti = stanza;
			}
			if(stanza.getNumeroAttrezzi() < stanzaMinOggetti.getNumeroAttrezzi()) {
				stanzaMinOggetti = stanza;
			}
		}
		
		if(this.haSalutato()) {
			partita.setStanzaCorrente(stanzaMaxOggetti);
			return MESSAGGIO_BENEVOLO;
		}
		partita.setStanzaCorrente(stanzaMinOggetti);
		return MESSAGGIO_IRRITATA;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		return MESSAGGIO_RISATA;
	}

}
