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
}
