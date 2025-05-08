package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza{
	String attrezzoLuce;

	public StanzaBuia(String nome, String nomeAttrezzo) {
		super(nome);
		this.attrezzoLuce = nomeAttrezzo;
	}
	
	@Override
	public String getDescrizione() {
		if(super.hasAttrezzo(attrezzoLuce)) {
			return super.toString();
		}
		else {
			 return "Qui c'è buio pesto";
		}
	}
	
}
