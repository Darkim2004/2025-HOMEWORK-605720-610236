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
}
