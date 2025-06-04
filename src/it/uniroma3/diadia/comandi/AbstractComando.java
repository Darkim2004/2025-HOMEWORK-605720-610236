package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public abstract class AbstractComando implements Comando{
	String parametro;

	@Override
	public abstract void esegui(Partita partita, IO io);

	@Override
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	@Override
	public String getNome() {
		return "AbstractComando";
	}

	@Override
	public String getParametro() {
		return this.parametro;
	}
	
}
