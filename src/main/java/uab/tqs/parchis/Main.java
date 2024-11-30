package uab.tqs.parchis;

import uab.tqs.parchis.controller.GameController;
import uab.tqs.parchis.model.Game;
import uab.tqs.parchis.view.GameView;

public class Main {
    public static void main(String[] args) {
        GameView gameView = new GameView();
        Game game = new Game();
        GameController gameController = new GameController(game, gameView); // Correcto

        String[] jugadores = {"Jugador 1", "Jugador 2", "Jugador 3", "Jugador 4"};
        String[] colores = {"Amarillo", "Azul", "Rojo", "Verde"};

        gameController.iniciarJuego(jugadores, colores); // Cambiado a gameController

        while (!game.esFinDelJuego()) { // Esto también tiene un error lógico: debería ser !game.esFinDelJuego()
            gameController.empezarTurno();
        }
    }
}
