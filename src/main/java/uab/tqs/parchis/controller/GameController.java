package uab.tqs.parchis.controller;

import uab.tqs.parchis.model.Game;

public class GameController {
    private Game game;
    // private GameView game_view;

    public GameController(Game game) {
        this.game = game;
        // this.game_view = game_view:
    }

    public void iniciarJuego(String[] jugadores, String[] colores) {
        if (jugadores.length != colores.length || jugadores.length > 4) {
            // MOSTRAR ERROR EN LA VIEW
            return;
        }

        for (int i = 0; i < jugadores.length; i++) {
            this.game.agregarJugador(jugadores[i], colores[i]);
        }
    }

    public int lanzarDado() {
        int valor_dado = game.lanzarDado();
        return valor_dado;
    }

    public void avanzarTurno() {
        if (game.esFinDelJuego()) {
            // MOSTRAR MENSAJE EN LA VIEW DE QUE EL JUEGO HA ACABADO
            return;
        }

        game.avanzarTurno();
    }
}
