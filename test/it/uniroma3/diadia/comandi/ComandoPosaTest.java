package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosaTest {
	// Fixture
	private IO io;
	private Partita partita;
	private Comando comandoPosa;
	private Attrezzo attrezzo;
	private Labirinto labirinto;
	
	@BeforeEach
	public void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		labirinto = new LabirintoBuilder()
				.addStanzaIniziale("stanza")
				.getLabirinto();
		attrezzo = new Attrezzo("Trapano", 0);
		Scanner scanner = new Scanner(System.in);
		io = new IOConsole(scanner);
		partita = new Partita(labirinto);
		comandoPosa = new ComandoPosa();
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
	}
	
	//Test esegui
	/*@Test
	public void testEseguiNull() {
		comandoPosa.esegui(partita, io);
		assertEquals(attrezzo, partita.getGiocatore().getBorsa().getAttrezzo("Trapano"));
		assertNull(partita.getStanzaCorrente().getAttrezzo("Trapano"));
	}*/
	
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
