package uab.tqs.parchis.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CasillaTest  {

    private CasillaCasa casilla_casa;
    private CasillaNormal casilla_normal;
    private CasillaSegura casilla_segura;
    private CasillaFinal casilla_final;


    @BeforeEach
    void setUp() {
        // TEST CASILLA CASA
        casilla_casa = new CasillaCasa(0);

        // TEST CASILLA NORMAL
        casilla_normal = new CasillaNormal(2);

        // TEST CASILLA SEGURA
        casilla_segura = new CasillaSegura(5);

        // TEST CASILLA FINAL
        casilla_final = mock(CasillaFinal.class);
        when(casilla_final.getNumero()).thenReturn(4);
        when(casilla_final.getSeguro()).thenReturn(true);
    }

    @Test
    void testNumero() {
        // TEST CASILLA CASA
        assertEquals(0, casilla_casa.getNumero(), "El número de casilla deberia ser 0");

        // TEST CASILLA NORMAL
        assertEquals(2, casilla_normal.getNumero(), "El número de casilla deberia ser 2");

        // TEST CASILLA SEGURA
        assertEquals(5, casilla_segura.getNumero(), "El número de casilla deberia ser 5");

        // TEST CASILLA FINAL
        assertEquals(4, casilla_final.getNumero(), "El número de casilla deberia ser 4");
    }

    @Test
    void testSeguro() {
        // TEST CASILLA CASA
        assertTrue(casilla_casa.getSeguro(), "La casilla deberia ser segura");

        // TEST CASILLA NORMAL
        assertFalse(casilla_normal.getSeguro(), "La casilla no deberia ser segura");

        // TEST CASILLA SEGURA
        assertTrue(casilla_segura.getSeguro(), "La casilla deberia ser segura");

        // TEST CASILLA FINAL
        assertTrue(casilla_final.getSeguro(), "La casilla deberia ser segura");
    }

    @Test
    void testAgregarQuitar() {
        // POSIBLE MOCK OBJECT AQUÍ
        Ficha ficha = new Ficha();

        // TEST CASILLA CASA
        casilla_casa.agregarFicha(ficha);

        assertFalse(casilla_casa.getFichas().isEmpty(), "Deberia haber alguna ficha en la casilla");

        casilla_casa.quitarFicha(ficha);

        assertTrue(casilla_casa.getFichas().isEmpty(), "No deberia haber ninguna ficha en la casilla");

        // TEST CASILLA NORMAL
        casilla_normal.agregarFicha(ficha);

        assertFalse(casilla_normal.getFichas().isEmpty(), "Deberia haber alguna ficha en la casilla");

        casilla_normal.quitarFicha(ficha);

        assertTrue(casilla_normal.getFichas().isEmpty(), "No deberia haber ninguna ficha en la casilla");

        // TEST CASILLA SEGURA
        casilla_segura.agregarFicha(ficha);

        assertFalse(casilla_segura.getFichas().isEmpty(), "Deberia haber alguna ficha en la casilla");

        casilla_segura.quitarFicha(ficha);

        assertTrue(casilla_segura.getFichas().isEmpty(), "No deberia haber ninguna ficha en la casilla");

        // TEST CASILLA FINAL
        casilla_final.agregarFicha(ficha);

        assertFalse(casilla_final.getFichas().isEmpty(), "Deberia haber alguna ficha en la casilla");

        casilla_final.quitarFicha(ficha);

        assertTrue(casilla_final.getFichas().isEmpty(), "No deberia haber ninguna ficha en la casilla");
    }

    @Test
    void testBloqueo() {
        Ficha ficha1 = new Ficha();
        Ficha ficha2 = new Ficha();
        
        // TESTS CASILLA NORMAL
        casilla_normal.agregarFicha(ficha1);
        casilla_normal.agregarFicha(ficha2);

        assertTrue(casilla_normal.getBloqueo(), "La casilla deberia estar bloqueada");

        casilla_normal.quitarFicha(ficha2);

        assertFalse(casilla_normal.getBloqueo(), "La casilla deberia estar desbloqueada");

        // TEST CASILLA SEGURA
        casilla_segura.agregarFicha(ficha1);
        casilla_segura.agregarFicha(ficha2);

        assertTrue(casilla_segura.getBloqueo(), "La casilla deberia estar bloqueada");

        casilla_segura.quitarFicha(ficha2);

        assertFalse(casilla_segura.getBloqueo(), "La casilla deberia estar desbloqueada");
    }
}
