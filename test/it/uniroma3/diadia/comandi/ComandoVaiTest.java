package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;
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

public class ComandoVaiTest {
	//Fixture
	private IO io;
	private Partita partita;
	private String direzione;
	private ComandoVai comandoVai;
	private LabirintoBuilder labirintoBuilder;
	private Labirinto labirinto;
	
	//Setup
	@BeforeEach
	public void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		labirintoBuilder = new LabirintoBuilder();
		labirinto = labirintoBuilder
				.addStanzaIniziale("Atrio")
				.addStanza("Aula N10")
				.addAdiacenza("Atrio", "Aula N10", "sud")
				.getLabirinto();
		Scanner scanner = new Scanner(System.in);
		io = new IOConsole(scanner);
		partita = new Partita(labirinto);
		comandoVai = new ComandoVai();
	}
	
	// Test esegui
	@Test
	public void testEseguiNull() {
		direzione = null;
		comandoVai.setParametro(direzione);
		comandoVai.esegui(partita, io);
		assertEquals("Atrio", partita.getStanzaCorrente().getNome());
	}
	
	/*@Test
	public void testEseguiDirezioneInesistente() {
		direzione = "giu";
		comandoVai.setParametro(direzione);
		assertThrowsExactly(comandoVai.esegui(partita, io), java.lang.IllegalArgumentException);
	}*/
	
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
