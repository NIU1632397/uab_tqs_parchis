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
    
    @Test
    void testPairwiseMover() {
        // Caso 1: home = true, fin = false, pasos = 0
        ficha.setHome(true);
        ficha.setFin(false);
        ficha.setPos(0);
        ficha.mover(0);
        assertEquals(0, ficha.getPos(), "La ficha no debería moverse si está en casa.");

        // Caso 2: home = true, fin = true, pasos = 5
        ficha.setHome(true);
        ficha.setFin(true);
        ficha.setPos(0);
        ficha.mover(5);
        assertEquals(0, ficha.getPos(), "La ficha no debería moverse si está en casa y en la meta.");

        // Caso 3: home = false, fin = false, pasos = 70
        ficha.setHome(false);
        ficha.setFin(false);
        ficha.setPos(0);
        ficha.mover(70);
        assertEquals(2, ficha.getPos(), "La posición debe ajustarse circularmente si supera el tablero.");

        // Caso 4: home = false, fin = true, pasos = 0
        ficha.setHome(false);
        ficha.setFin(true);
        ficha.setPos(10);
        ficha.mover(0);
        assertEquals(10, ficha.getPos(), "La posición no debe cambiar si la ficha está en la meta.");

        // Caso 5: home = false, fin = false, pasos = 5
        ficha.setHome(false);
        ficha.setFin(false);
        ficha.setPos(0);
        ficha.mover(5);
        assertEquals(5, ficha.getPos(), "La ficha debería moverse correctamente si no está en casa ni en la meta.");
    }
}