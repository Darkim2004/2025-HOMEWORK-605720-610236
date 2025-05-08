package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FabbricaDiComandiFisarmonicaTest {

    private FabbricaDiComandiFisarmonica fabbrica;

    @BeforeEach
    public void setUp() {
        fabbrica = new FabbricaDiComandiFisarmonica();
    }

    @Test
    public void testComandoConParametro() {
        Comando comando = fabbrica.costruisciComando("vai nord");
        assertEquals("vai", comando.getNome());
        assertEquals("nord", comando.getParametro());
    }

    @Test
    public void testComandoSenzaParametro() {
        Comando comando = fabbrica.costruisciComando("aiuto");
        assertEquals("aiuto", comando.getNome());
        assertNull(comando.getParametro());
    }

    @Test
    public void testComandoNonValido() {
        Comando comando = fabbrica.costruisciComando("vola su");
        assertEquals("Non valido", comando.getNome());
        assertNull(comando.getParametro());
    }

    @Test
    public void testStringaVuota() {
        Comando comando = fabbrica.costruisciComando("");
        assertEquals("Non valido", comando.getNome());
        assertNull(comando.getParametro());
    }

    @Test
    public void testSoloNomeComando() {
        Comando comando = fabbrica.costruisciComando("fine");
        assertEquals("fine", comando.getNome());
        assertNull(comando.getParametro());
    }
}

