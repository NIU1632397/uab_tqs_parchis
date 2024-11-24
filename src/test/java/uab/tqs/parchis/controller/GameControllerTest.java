package uab.tqs.parchis.controller;
import uab.tqs.parchis.model.Game;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class GameControllerTest {
    private Game game;
    // DESCOMENTAR CUANDO SE IMPLEMENTE LA CLASE
    // private GameView game_view;
    private GameController game_controller;

    @BeforeEach
    void setUp() {
        game = new Game();
        // game_view = mock(GameView.class);
        game_controller = new GameController(game);
    }

    @Test
    void testIniciarJuego() {
        String[] jugadores = {"Jugador 1", "Jugador 2"};
        String[] colores = {"Azul", "Amarillo"};

        game_controller.iniciarJuego(jugadores, colores);

        assertEquals("Jugador 1", game.getJugadores().get(0).getNombre());
        assertEquals("Azul", game.getJugadores().get(0).getColor());

        // verify(game_view).mostrarEstadoJuego("Juego inicializado correctamente.");
    }

    @Test
    void testLanzarDado() {
        String[] jugadores = {"Jugador 1", "Jugador 2"};
        String[] colores = {"Azul", "Amarillo"};

        game_controller.iniciarJuego(jugadores, colores);

        int tirada = game_controller.lanzarDado();

        assertTrue((tirada >= 0 && tirada <= 6), "El numero deberia estar entre 0 y 6");
    }

    @Test
    void testAvanzarTurno() {
        String[] jugadores = {"Jugador 1", "Jugador 2"};
        String[] colores = {"Azul", "Amarillo"};

        game_controller.iniciarJuego(jugadores, colores);

        game_controller.avanzarTurno();

        assertEquals("Jugador 2", game.getJugadorActual().getNombre());

        // verify(game_view).mostrarMensaje("Turno avanzado. Ahora es el turno de: Jugador 2");
    }
}
