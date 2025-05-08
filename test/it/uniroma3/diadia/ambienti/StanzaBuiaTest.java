package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {
	
	private StanzaBuia stanza;
	private Attrezzo attrezzo;
	
	@BeforeEach
	public void setUp() {
		stanza = new StanzaBuia("Sgabuzzino", "Lanterna");
		attrezzo = new Attrezzo("Lanterna", 1);
	}
	
	@Test
	public void testGetDescrizioneNoAttrezzoLuce() {
		Attrezzo attrezzo2 = new Attrezzo("Spada", 1);
		stanza.addAttrezzo(attrezzo2);
		assertEquals("Qui c'è buio pesto", stanza.getDescrizione());
	}
	
	@Test
	public void testGetDescrizioneNoAttrezzi() {
		assertEquals("Qui c'è buio pesto", stanza.getDescrizione());
	}
	
	@Test
	public void testGetDescrizioneSiAttrezzoLuce() {
		stanza.addAttrezzo(attrezzo);
		assertNotEquals("Qui c'è buio pesto", stanza.getDescrizione());
	}
}
