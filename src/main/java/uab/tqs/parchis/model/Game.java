package uab.tqs.parchis.model;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Tablero tablero;
    private List<Jugador> jugadores;
    private Dado dado;
    private int turnoActual;

    public Game() {
        this.tablero = new Tablero();
        this.jugadores = new ArrayList<>();
        this.dado = new Dado();
        this.turnoActual = 0; // Empieza con el primer jugador
    }

    public void agregarJugador(String nombre, String color) {
        if (jugadores.size() >= 4) {
            throw new IllegalStateException("El máximo número de jugadores es 4.");
        }
        jugadores.add(new Jugador(nombre, color));
    }
}