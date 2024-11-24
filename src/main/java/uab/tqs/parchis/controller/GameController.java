package uab.tqs.parchis.controller;

import uab.tqs.parchis.model.Game;
import uab.tqs.parchis.view.GameView;

public class GameController {
    private Game game;
    private GameView game_view;

    public GameController(Game game, GameView game_view) {
        this.game = game;
        this.game_view = game_view;
    }

    public void iniciarJuego(String[] jugadores, String[] colores) {
        if (jugadores.length != colores.length || jugadores.length > 4) {
            game_view.mostrarError("Debe haber entre 2 y 4 jugadores con colores únicos.");
            return;
        }

        for (int i = 0; i < jugadores.length; i++) {
            this.game.agregarJugador(jugadores[i], colores[i]);
        }

        game_view.mostrarMensaje("Juego inicializado correctamente.");
        actualizarVista();
    }

    public int lanzarDado() {
        int valor_dado = game.lanzarDado();
        return valor_dado;
    }

    public void avanzarTurno() {
        if (game.esFinDelJuego()) {
            game_view.mostrarMensaje("El juego ha terminado. El ganador es: " + game.obtenerGanador().getNombre());
            return;
        }

        game.avanzarTurno();
        game_view.mostrarMensaje("Turno avanzado. Ahora es el turno de: " + game.getJugadorActual().getNombre());
        actualizarVista();
    }

    public void actualizarVista() {
        game_view.mostrarTablero(game.getTablero());
    }
}
