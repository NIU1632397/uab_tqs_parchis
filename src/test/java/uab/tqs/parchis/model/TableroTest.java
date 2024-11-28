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
        assertEquals(69, tablero.getTablero().size(), "El tablero deberia ser de 68 casillas + 1 casilla para casa");
        assertEquals(8, tablero.getTableroFinal().size(), "El final del tablero deberia ser de 8 casillas");

        assertThrows(IndexOutOfBoundsException.class, () -> tablero.getCasillaTablero(70));
    }

    @Test
    void testCasillas() {
        // Casilla CASA
        assertEquals(0, tablero.getCasillaTablero(0).getNumero(), "La casilla deberia ser la 0, casilla casa");

        // Casilla NORMAL
        assertEquals(1, tablero.getCasillaTablero(1).getNumero(), "La casilla deberia ser la 1, casilla normal");
        assertFalse(tablero.getCasillaTablero(1).getSeguro(), "La casilla deberia ser no segura");

        // Casilla SEGURA
        assertEquals(5, tablero.getCasillaTablero(5).getNumero(), "La casilla deberia ser la 5, casilla segura");
    }

}
