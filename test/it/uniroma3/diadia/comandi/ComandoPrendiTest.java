package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendiTest {

    // Fixture
	private IO io;
    private Partita partita;
    private ComandoPrendi comandoPrendi;
    private Attrezzo attrezzo;

    @BeforeEach
    public void setUp() {
    	io = new IOConsole();
        partita = new Partita();
        comandoPrendi = new ComandoPrendi(null);
        attrezzo = new Attrezzo("Trapano", 1);
        partita.getStanzaCorrente().addAttrezzo(attrezzo);
    }
    
  //Test esegui
    @Test
    public void testEseguiParametroNull() {
        comandoPrendi.esegui(partita, io);
        assertEquals(attrezzo, partita.getStanzaCorrente().getAttrezzo("Trapano"));
        assertNull(partita.getGiocatore().getBorsa().getAttrezzo("Trapano"));
    }

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
