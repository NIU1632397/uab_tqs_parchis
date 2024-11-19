package uab.tqs.parchis.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TableroTest {

    private Tablero tablero;

    @BeforeEach
    void setUp() {
        tablero = new Tablero();
    }

    @Test
    void testTablero() {
        assertEquals(68, tablero.getTablero().size(), "El tablero deberia ser de 68 casillas");
        assertEquals(8, tablero.getTableroFinal().size(), "El final del tablero deberia ser de 8 casillas")
    }

    @Test
    void testCasillas() {
        assertEquals(0, tablero.getCasilla(0).getNumero(), "La casilla deberia ser la 0, casilla casa");
        assertEquals(1, tablero.getCasilla(1).getNumero(), "La casilla deberia ser la 1, casilla normal");
        assertEquals(5, tablero.getCasilla(5).getNumero(), "La casilla deberia ser la 5, casilla segura");
    }

}
