package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.Configuratore;

public class Giocatore {
	static final private int CFU_INIZIALI = Configuratore.getCFU();
	
	private int cfu;
	private Borsa borsa;
	
	public Giocatore() {
		this.borsa = new Borsa();
		this.cfu = CFU_INIZIALI;
	}
	
	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;	
	}
	
	public Borsa getBorsa() {
		return this.borsa;
	}
	
	public String getDescrizione() {
		return this.toString();
	}
	
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append("Ti rimangono: " + this.cfu + " cfu\n");
		risultato.append(this.getBorsa().getDescrizione());
		return risultato.toString(); 
	}
}
