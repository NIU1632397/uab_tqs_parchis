package uab.tqs.parchis.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameTest { 

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    /**
     * Test de inicialización del modelo. 
     * Caja negra: Verifica que el modelo se inicializa con el estado esperado.
     */
    @Test
    void testInicializacionModelo() {
        assertNotNull(game.getTablero(), "El tablero debería inicializarse.");
        assertNotNull(game.getJugadores(), "La lista de jugadores debería inicializarse.");
        assertEquals(0, game.getJugadores().size(), "La lista de jugadores debería estar vacía al inicio.");
        assertNotNull(game.getDado(), "El dado debería inicializarse.");
    }

    /**
     * Test para agregar jugadores válidos.
     * Caja negra: Agregar hasta 4 jugadores correctamente.
     */
    @Test
    void testAgregarJugadoresValidos() {
        game.agregarJugador("Jugador 1", "amarillo");
        game.agregarJugador("Jugador 2", "azul");
        game.agregarJugador("Jugador 3", "rojo");
        game.agregarJugador("Jugador 4", "verde");

        assertEquals(4, game.getJugadores().size(), "Debería haber 4 jugadores en la lista.");
        assertEquals("Jugador 1", game.getJugadores().get(0).getNombre());
        assertEquals("amarillo", game.getJugadores().get(0).getColor());
    }
}