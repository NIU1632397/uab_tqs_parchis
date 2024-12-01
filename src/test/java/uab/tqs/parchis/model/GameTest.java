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

    @Test
    void testInicializacionModelo() {
        assertNotNull(game.getTablero(), "El tablero debería inicializarse.");
        assertNotNull(game.getJugadores(), "La lista de jugadores debería inicializarse.");
        assertEquals(0, game.getJugadores().size(), "La lista de jugadores debería estar vacía al inicio.");
        assertNotNull(game.getDado(), "El dado debería inicializarse.");
    }

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

    @Test
    void testAgregarJugadorExcedeLimite() {
        game.agregarJugador("Jugador 1", "amarillo");
        game.agregarJugador("Jugador 2", "azul");
        game.agregarJugador("Jugador 3", "rojo");
        game.agregarJugador("Jugador 4", "verde");

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            game.agregarJugador("Jugador 5", "morado");
        });

        assertEquals("El máximo número de jugadores es 4.", exception.getMessage());
    }

    @Test
    void testAvanzarTurno() {
        game.agregarJugador("Jugador 1", "amarillo");
        game.agregarJugador("Jugador 2", "azul");

        Jugador jugadorInicial = game.getJugadorActual();
        assertEquals("Jugador 1", jugadorInicial.getNombre(), "El primer turno debe ser del primer jugador.");

        game.avanzarTurno();
        Jugador siguienteJugador = game.getJugadorActual();
        assertEquals("Jugador 2", siguienteJugador.getNombre(), "El turno debería avanzar al segundo jugador.");

        game.avanzarTurno();
        Jugador jugadorReiniciado = game.getJugadorActual();
        assertEquals("Jugador 1", jugadorReiniciado.getNombre(), "El turno debería regresar al primer jugador.");
    }
    
    @Test
    void testEsFinDelJuego() {
        game.agregarJugador("Jugador 1", "amarillo");
        game.agregarJugador("Jugador 2", "azul");

        // Al inicio, nadie debería haber ganado
        assertFalse(game.esFinDelJuego(), "El juego no debería terminar al inicio.");

        // Simular que el primer jugador tiene todas las fichas en la meta
        for (Ficha ficha : game.getJugadores().get(0).getFichas()) {
            ficha.setFin(true);
        }

        assertTrue(game.esFinDelJuego(), "El juego debería terminar si un jugador tiene 4 fichas en la meta.");
    }

    @Test
    void testObtenerGanador() {
        game.agregarJugador("Jugador 1", "amarillo");
        game.agregarJugador("Jugador 2", "azul");

        // Al inicio, no debería haber un ganador
        assertNull(game.obtenerGanador(), "No debería haber un ganador al inicio.");

        // Simular que el primer jugador gana
        for (Ficha ficha : game.getJugadores().get(0).getFichas()) {
            ficha.setFin(true);
        }

        Jugador ganador = game.obtenerGanador();
        assertNotNull(ganador, "Debería haber un ganador.");
        assertEquals("Jugador 1", ganador.getNombre(), "El ganador debería ser el primer jugador.");
    }
}