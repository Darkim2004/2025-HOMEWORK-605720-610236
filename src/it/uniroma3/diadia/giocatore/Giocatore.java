package it.uniroma3.diadia.giocatore;


public class Giocatore {
	
	private int cfu;
	private Borsa borsa;
	
	public Giocatore() {
		this.borsa = new Borsa();
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
