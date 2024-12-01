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
        assertThrows(IndexOutOfBoundsException.class, () -> tablero.getCasillaTablero(-1));
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

    @Test
    void testCasillasSeguras() {
        int[] seguras = {5, 12, 17, 22, 29, 34, 39, 46, 51, 56, 63, 68};
        for (int numero : seguras) {
            assertTrue(tablero.getCasillaTablero(numero).getSeguro(), "La casella " + numero + " ha de ser segura.");
        }
    }

    @Test
    void testLoopTestingInicializarTablero() {
        for (int i = 0; i < 69; i++) {
            assertNotNull(tablero.getCasillaTablero(i), "La casella " + i + " ha d'existir al tauler principal.");
        }
        for (int i = 1; i <= 8; i++) {
            assertNotNull(tablero.getCasillaTableroFinal(i - 1), "La casella " + i + " ha d'existir al tauler final.");
        }
    }
}
