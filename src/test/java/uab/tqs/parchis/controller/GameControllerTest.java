package uab.tqs.parchis.controller;
import uab.tqs.parchis.model.Ficha;
import uab.tqs.parchis.model.Game;
import uab.tqs.parchis.model.Jugador;
import uab.tqs.parchis.view.GameView;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class GameControllerTest {
    private Game game;
    private GameView game_view;
    private GameController game_controller;

    @BeforeEach
    void setUp() {
        game = new Game();
        game_view = mock(GameView.class);
        game_controller = new GameController(game, game_view);
    }

    @Test
    void testIniciarJuego() {
        String[] jugadores = {"Jugador 1", "Jugador 2"};
        String[] colores = {"Azul", "Amarillo"};

        game_controller.iniciarJuego(jugadores, colores);

        assertEquals("Jugador 1", game.getJugadores().get(0).getNombre());
        assertEquals("Azul", game.getJugadores().get(0).getColor());

        verify(game_view).mostrarMensaje("Juego inicializado correctamente.");
    }

    void testIniciarJuegoMax() {
        // Más de 4 jugadores: caso inválido
        String[] jugadores = {"Jugador 1", "Jugador 2", "Jugador 3", "Jugador 4", "Jugador 5"};
        String[] colores = {"Azul", "Amarillo", "Verde", "Rojo", "Violeta"};
    
        game_controller.iniciarJuego(jugadores, colores);
    
        // Verificar que no se agregan jugadores y se muestra un error
        assertEquals(0, game.getJugadores().size(), "No se deben inicializar jugadores si exceden el límite.");
        verify(game_view).mostrarError("Debe haber entre 2 y 4 jugadores con colores únicos.");
    }

    @Test
    void testLanzarDado() {
        String[] jugadores = {"Jugador 1", "Jugador 2"};
        String[] colores = {"Azul", "Amarillo"};

        game_controller.iniciarJuego(jugadores, colores);

        int tirada = game_controller.lanzarDado();
    }

    @Test
    void testAvanzarTurno() {
        String[] jugadores = {"Jugador 1", "Jugador 2"};
        String[] colores = {"Azul", "Amarillo"};

        game_controller.iniciarJuego(jugadores, colores);

        game_controller.avanzarTurno();

        assertEquals("Jugador 2", game.getJugadorActual().getNombre());

        verify(game_view).mostrarMensaje("Turno avanzado. Ahora es el turno de: Jugador 2");
    }

    @Test
    void testMovimientoInicial() {
        String[] jugadores = {"Jugador 1"};
        String[] colores = {"Amarillo"};

        game_controller.iniciarJuego(jugadores, colores);

        Jugador jugador_actual = game.getJugadorActual();
        Ficha ficha = jugador_actual.getFichas()[0];

        game_controller.movimientoInicial(ficha);

        assertEquals(5, ficha.getPos(), "La ficha debe estar en la posición 5 tras el movimiento inicial.");
        assertFalse(ficha.isHome(), "La ficha ya no debe estar en casa.");
    }

    @Test
    void testMoverFicha() {
        String[] jugadores = {"Jugador 1"};
        String[] colores = {"Azul"};

        game_controller.iniciarJuego(jugadores, colores);

        Jugador jugador_actual = game.getJugadorActual();
        Ficha ficha = jugador_actual.getFichas()[0];

        game_controller.movimientoInicial(ficha);
        game_controller.moverFicha(ficha, 10);

        assertEquals(32, ficha.getPos(), "La ficha debe avanzar correctamente 10 posiciones desde el inicio suyo.");
    }

    @Test
    void testMoverFichaBloqueo() {
        String[] jugadores = {"Jugador 1", "Jugador 2"};
        String[] colores = {"Azul", "Amarillo"};

        game_controller.iniciarJuego(jugadores, colores);

        Jugador Jugador1 = game.getJugadorActual();
        Ficha ficha0 = Jugador1.getFichas()[0];
        Ficha ficha1 = Jugador1.getFichas()[1];

        game_controller.movimientoInicial(ficha0);
        game_controller.movimientoInicial(ficha1);
        
        game_controller.avanzarTurno();

        Jugador Jugador2 = game.getJugadorActual();
        Ficha ficha2 = Jugador2.getFichas()[2];

        game_controller.movimientoInicial(ficha2);

        game_controller.moverFicha(ficha2, 20);

        assertEquals(21, ficha2.getPos(), "La ficha debe avanzar correctamente hasta el bloqueo.");
    }

    @Test
    void testFinalInicioTablero() {
        String[] jugadores = {"Jugador 1"};
        String[] colores = {"Verde"};

        game_controller.iniciarJuego(jugadores, colores);

        Jugador jugador_actual = game.getJugadorActual();
        Ficha ficha = jugador_actual.getFichas()[0];

        game_controller.movimientoInicial(ficha);
        game_controller.moverFicha(ficha, 20);

        assertEquals(8, ficha.getPos(), "La ficha debe avanzar correctamente hasta la posicion 8.");
    }

    @Test
    void testMoverFichaFinal() {
        String[] jugadores = {"Jugador 1"};
        String[] colores = {"Verde"};

        game_controller.iniciarJuego(jugadores, colores);

        Jugador jugador_actual = game.getJugadorActual();
        Ficha ficha = jugador_actual.getFichas()[0];

        game_controller.movimientoInicial(ficha);
        game_controller.moverFicha(ficha, 66);

        assertTrue(ficha.getEstaFinal());
    }
}
