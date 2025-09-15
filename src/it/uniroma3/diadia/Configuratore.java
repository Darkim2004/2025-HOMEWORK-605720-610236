package it.uniroma3.diadia;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Configuratore {

    private static final String DIADIA_PROPERTIES = "/diadia.properties";
    private static final String PESO_MAX = "pesoMax";
    private static final String CFU = "cfu";
    private static Properties prop = null;
    
    public static int getCFU() {
        if (prop == null)
            carica();
        return Integer.parseInt(prop.getProperty(CFU));
    }
    
    public static int getPesoMax() {
        if (prop == null)
            carica();
        return Integer.parseInt(prop.getProperty(PESO_MAX));
    }

    private static void carica() {
        prop = new Properties();
        try (InputStream in = Configuratore.class.getResourceAsStream(DIADIA_PROPERTIES)) {
            if (in == null) {
                throw new RuntimeException("File di configurazione non trovato: " + DIADIA_PROPERTIES);
            }
            prop.load(in);
        } catch (IOException e) {
            throw new RuntimeException("Errore nel caricamento delle properties", e);
        }
    }
}
