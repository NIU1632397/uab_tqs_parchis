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

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public Dado getDado() {
        return dado;
    }

    public Jugador getJugadorActual() {
        return jugadores.get(turnoActual);
    }

    public void avanzarTurno() {
        turnoActual = (turnoActual + 1) % jugadores.size();
    }

    public boolean esFinDelJuego() {
        return jugadores.stream().anyMatch(jugador -> jugador.contarFichasEnFin() == 4);
    }

    public Jugador obtenerGanador() {
        return jugadores.stream()
                .filter(jugador -> jugador.contarFichasEnFin() == 4)
                .findFirst()
                .orElse(null);
    }
}