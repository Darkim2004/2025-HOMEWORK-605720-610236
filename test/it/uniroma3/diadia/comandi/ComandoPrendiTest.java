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

public class ComandoPrendiTest {

	// Fixture
	private IO io;
	private Partita partita;
	private Comando comandoPrendi;
	private Attrezzo attrezzo;
	private LabirintoBuilder labirintoBuilder;
	private Labirinto labirinto;

	@BeforeEach
	public void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		labirintoBuilder = new LabirintoBuilder();
		labirinto = labirintoBuilder
				.addStanzaIniziale("stanza")
				.addAttrezzo("Trapano", 0)
				.getLabirinto();
		attrezzo = new Attrezzo("Trapano", 0);
		Scanner scanner = new Scanner(System.in);
		io = new IOConsole(scanner);
		partita = new Partita(labirinto);
		comandoPrendi = new ComandoPrendi();
	}

	//Test esegui
	/*@Test
    public void testEseguiParametroNull() {
        comandoPrendi.esegui(partita, io);
        assertEquals(attrezzo, partita.getStanzaCorrente().getAttrezzo("Trapano"));
        assertNull(partita.getGiocatore().getBorsa().getAttrezzo("Trapano"));
    }*/

	@Test
	public void testEseguiAttrezzoNonPresente() {
		comandoPrendi.setParametro("Martello");
		comandoPrendi.esegui(partita, io);
		assertNull(partita.getGiocatore().getBorsa().getAttrezzo("Martello"));
		assertNull(partita.getGiocatore().getBorsa().getAttrezzo("Trapano"));
	}

	@Test
	public void testEseguiAttrezzoPresente() {
		comandoPrendi.setParametro("Trapano");
		comandoPrendi.esegui(partita, io);
		assertEquals(attrezzo, partita.getGiocatore().getBorsa().getAttrezzo("Trapano"));
		assertNull(partita.getStanzaCorrente().getAttrezzo("Trapano"));
	}
}
