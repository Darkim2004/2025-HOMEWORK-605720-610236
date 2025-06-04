package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FabbricaDiComandiRiflessivaTest {

    private FabbricaDiComandiRiflessiva fabbrica;

    @BeforeEach
    public void setUp() {
    	fabbrica = new FabbricaDiComandiRiflessiva();
    }

    @Test
    public void testComandoConParametro() throws Exception {
        Comando comando = fabbrica.costruisciComando("vai nord");
        assertEquals("vai", comando.getNome());
        assertEquals("nord", comando.getParametro());
    }
    
    @Test
    public void testComandoConParametro2() throws Exception {
        Comando comando = fabbrica.costruisciComando("prendi sasso");
        assertEquals("prendi", comando.getNome());
        assertEquals("sasso", comando.getParametro());
    }

    @Test
    public void testComandoSenzaParametro() throws Exception {
    	Comando comando = fabbrica.costruisciComando("aiuto");
        assertEquals("aiuto", comando.getNome());
        assertNull(comando.getParametro());
    }

    /*@Test
    public void testComandoNonValido() throws Exception {
    	Comando comando = fabbrica.costruisciComando("vola su");
        assertEquals("Non valido", comando.getNome());
        assertNull(comando.getParametro());
    }

    @Test
    public void testStringaVuota() throws Exception {
    	Comando comando = fabbrica.costruisciComando("");
        assertEquals("Non valido", comando.getNome());
        assertNull(comando.getParametro());
    }*/

    @Test
    public void testSoloNomeComando() throws Exception {
    	Comando comando = fabbrica.costruisciComando("fine");
        assertEquals("fine", comando.getNome());
        assertNull(comando.getParametro());
    }
}

