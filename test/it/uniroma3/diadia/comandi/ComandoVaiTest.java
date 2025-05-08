package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoVaiTest {
	//Fixture
	private IO io;
	private Partita partita;
	private String direzione;
	private ComandoVai comandoVai;
	
	//Setup
	@BeforeEach
	public void setUp() {
		io = new IOConsole();
		partita = new Partita();
		comandoVai = new ComandoVai(direzione);
	}
	
	// Test esegui
	@Test
	public void testEseguiNull() {
		direzione = null;
		comandoVai.setParametro(direzione);
		comandoVai.esegui(partita, io);
		assertEquals("Atrio", partita.getStanzaCorrente().getNome());
	}
	
	@Test
	public void testEseguiDirezioneInesistente() {
		direzione = "giu";
		comandoVai.setParametro(direzione);
		comandoVai.esegui(partita, io);
		assertEquals("Atrio", partita.getStanzaCorrente().getNome());
	}
	
	@Test
	public void testEseguiDirezioneNonValida() {
		direzione = "sud";
		comandoVai.setParametro(direzione);
		comandoVai.esegui(partita, io);
		comandoVai.esegui(partita, io);
		assertEquals("Aula N10", partita.getStanzaCorrente().getNome());
	}
	
	@Test
	public void testEseguiDirezioneEsistente() {
		direzione = "sud";
		comandoVai.setParametro(direzione);
		comandoVai.esegui(partita, io);
		assertEquals("Aula N10", partita.getStanzaCorrente().getNome());
	}
}
