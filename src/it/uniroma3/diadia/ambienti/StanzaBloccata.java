package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccata extends Stanza{
	
	private String direzioneBloccata;
	private Attrezzo attrezzoSbloccante;
	
	public StanzaBloccata(String nome, String direzione, Attrezzo attrezzo){
		super(nome);
		this.direzioneBloccata = direzione;
		this.attrezzoSbloccante = attrezzo;
	}
	
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if(!super.hasAttrezzo(attrezzoSbloccante.getNome()) && direzione.equals(this.direzioneBloccata)) {
			return this;
		}
		else {
			return super.getStanzaAdiacente(direzione);
		}
	}
	
	@Override
	public String getDescrizione() {
		return (super.toString()+"\nLa stanza nella direzione"+ this.direzioneBloccata +"è bloccata.\n"
				+ "Forse qualcosa potrebbe aprirla...");
	}
}
