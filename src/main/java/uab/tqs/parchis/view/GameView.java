package uab.tqs.parchis.view;

import uab.tqs.parchis.model.Tablero;

public class GameView {

    public void mostrarMensaje(String mensaje) {
        System.out.println("[Mensaje]: " + mensaje);
    }

    public void mostrarError(String mensaje) {
        System.out.println("[Error]: " + mensaje);
    }

    public void mostrarTablero(Tablero tablero) {
        System.out.println("[Tablero actualizado]");
        System.out.println(tablero.toString());
    }
}
