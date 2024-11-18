package uab.tqs.parchis.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CasillaTest  {

    private CasillaCasa casilla_casa;

    @BeforeEach
    void setUp() {
        casilla_casa = new CasillaCasa(0);
    }

    @Test
    void testNumero() {
        assertEquals(0, casilla_casa.getNumero(), "El número de casilla deberia ser 0");
    }

    @Test
    void testSeguro() {
        assertTrue(casilla_casa.getSeguro(), "La casilla deberia ser segura");
    }

    @Test
    void testAgregarQuitar() {
        Ficha ficha = new Ficha();

        casilla_casa.agregarFicha(ficha);

        assertFalse(casilla_casa.getFichas().isEmpty(), "Deberia haber alguna ficha en la casilla");

        casilla_casa.quitarFicha(ficha);

        assertTrue(casilla_casa.getFichas().isEmpty(), "no deberia haber ninguna ficha en la casilla");
    }
}
