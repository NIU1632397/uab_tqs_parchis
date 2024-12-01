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
        ficha.mover(5); 
        assertEquals(5, ficha.getPos(), "La ficha debería haberse movido a la posición 5");
    }

    @Test
    void testNoMoverCuandoEstaEnFin() {
        ficha.setFin(true);  
        ficha.mover(5);
        assertEquals(0, ficha.getPos(), "La ficha no debería moverse si ha llegado a la meta");
    }

    @Test
    void testMoverCeroPasos() {
        ficha.mover(0);
        assertEquals(0, ficha.getPos(), "La posición no debería cambiar si los pasos son 0");
    }

    @Test
    void testMoverAlPrincipio() {
        ficha.mover(1);
        assertEquals(1, ficha.getPos(), "La posición no debería cambiar si los pasos son 0");
    }

    @Test
    void testMoverPasosNegativosLanzaExcepcion() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ficha.mover(-1);
        });
        assertEquals("El número de pasos debe ser no negativo.", exception.getMessage());
    }

    @Test
    void testMoverPasosNegativosLanzaExcepcionLimite() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ficha.mover(-1);
        });
        assertEquals("El número de pasos debe ser no negativo.", exception.getMessage());
    }

    @Test
    void testMoverCercaDelFinal() {
        ficha.setPos(67); 
        ficha.mover(2);   
        assertEquals(1, ficha.getPos(), "La ficha debería volver al inicio del tablero después de pasar la posición 68.");
    }

    @Test
    void testMoverMasDe68Pasos() {
        ficha.setPos(68);  
        ficha.mover(5);
        assertEquals(5, ficha.getPos(), "La ficha debería volver a la posición 5 después de pasar la posición 68.");
    }

    @Test
    void testNoMoverCuandoEstaEnMeta() {
        ficha.setFin(true); 
        ficha.mover(5);     
        assertEquals(0, ficha.getPos(), "La ficha no debería moverse si ya ha llegado a la meta.");
    }
}