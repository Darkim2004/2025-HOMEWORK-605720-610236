package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosaTest {
	// Fixture
	private IO io;
	private Partita partita;
	private ComandoPosa comandoPosa;
	private Attrezzo attrezzo;
	
	@BeforeEach
	public void setUp() {
		io = new IOConsole();
		partita = new Partita();
		comandoPosa = new ComandoPosa(null);
		attrezzo = new Attrezzo("Trapano", 1);
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
	}
	
	//Test esegui
	@Test
	public void testEseguiNull() {
		comandoPosa.esegui(partita, io);
		assertEquals(attrezzo, partita.getGiocatore().getBorsa().getAttrezzo("Trapano"));
		assertNull(partita.getStanzaCorrente().getAttrezzo("Trapano"));
	}
	
	@Test
	public void testEseguiAttrezzoInesistente() {
		comandoPosa.setParametro("Rastrello");
		comandoPosa.esegui(partita, io);
		assertEquals(attrezzo, partita.getGiocatore().getBorsa().getAttrezzo("Trapano"));
		assertNull(partita.getStanzaCorrente().getAttrezzo("Rastrello"));
	}
	
	@Test
	public void testEseguiOggettoEsistente() {
		comandoPosa.setParametro("Trapano");
		comandoPosa.esegui(partita, io);
		assertNull(partita.getGiocatore().getBorsa().getAttrezzo("Trapano"));
		assertEquals(attrezzo,partita.getStanzaCorrente().getAttrezzo("Trapano"));
	}
}
