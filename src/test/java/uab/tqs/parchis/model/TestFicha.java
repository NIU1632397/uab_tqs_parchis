package uab.tqs.parchis.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FichaTest {

    private Ficha ficha;

    @BeforeEach
    void setUp() {
        ficha = new Ficha();
        ficha.setHome(false); // Simula que ya ha salido de casa
        ficha.setFin(false); // Simula que no ha llegado al final
        ficha.setPos(0);      // Simula posicion inical
    }

    @Test
    void testMoverCuandoNoEstaEnFin() {
        ficha.mover(5); // Mueve 5 posiciones
        assertEquals(5, ficha.getPos(), "La ficha debería haberse movido a la posición 5");
    }

    @Test
    void testNoMoverCuandoEstaEnHome() {
        ficha.setHome(true);  // La ficha aún está en casa
        ficha.mover(5);
        assertEquals(0, ficha.getPos(), "La ficha no debería moverse si está en casa");
    }

    @Test
    void testNoMoverCuandoEstaEnFin() {
        ficha.setFin(true);  // La ficha ha llegado a la meta
        ficha.mover(5);
        assertEquals(0, ficha.getPos(), "La ficha no debería moverse si ha llegado a la meta");
    }

    @Test
    void testMoverCeroPasos() {
        ficha.mover(0);
        assertEquals(0, ficha.getPos(), "La posición no debería cambiar si los pasos son 0");
    }

    @Test
    void testMoverPasosNegativosLanzaExcepcion() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ficha.mover(-3);
        });
        assertEquals("El número de pasos debe ser no negativo.", exception.getMessage());
    }

}
