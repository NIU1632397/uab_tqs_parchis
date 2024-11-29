package uab.tqs.parchis.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa el estado y la lógica del juego de Parchís.
 * 
 * Contrato general:
 * - El juego tiene un máximo de 4 jugadores.
 * - El tablero (`tablero`), los jugadores (`jugadores`) y el dado (`dado`) siempre están inicializados.
 * - El turno avanza de manera circular entre los jugadores.
 * - El juego finaliza cuando un jugador tiene las 4 fichas en la meta.
 * - `lanzarDado()` siempre retorna un valor entre 1 y 6.
 */
public class Game {
    private Tablero tablero;
    private List<Jugador> jugadores;
    private Dado dado;
    private int turnoActual;

     /**
     * Constructor del juego.
     * 
     * Precondiciones:
     * - Ninguna.
     * 
     * Postcondiciones:
     * - `tablero` se inicializa correctamente.
     * - `jugadores` se inicializa como una lista vacía.
     * - `dado` se inicializa correctamente.
     * - El turno actual (`turnoActual`) se establece en 0.
     */
    public Game() {
        this.tablero = new Tablero();
        this.jugadores = new ArrayList<>();
        this.dado = new Dado();
        this.turnoActual = 0; // Empieza con el primer jugador
    }

    /**
     * Agrega un nuevo jugador al juego.
     * 
     * Precondiciones:
     * - El número de jugadores actuales debe ser menor que 4.
     * 
     * Postcondiciones:
     * - El nuevo jugador se agrega a la lista de jugadores.
     * - Lanza una excepción si ya hay 4 jugadores.
     * 
     * @param nombre Nombre del jugador.
     * @param color Color del jugador.
     * @throws IllegalStateException Si ya hay 4 jugadores en la lista.
     */
    public void agregarJugador(String nombre, String color) {
        if (jugadores.size() >= 4) {
            throw new IllegalStateException("El máximo número de jugadores es 4.");
        }
        jugadores.add(new Jugador(nombre, color));
    }

    /**
     * Devuelve la lista de jugadores.
     * 
     * Precondiciones:
     * - Ninguna.
     * 
     * Postcondiciones:
     * - Devuelve una lista no nula de jugadores.
     * 
     * @return Lista de jugadores.
     */
    public List<Jugador> getJugadores() {
        return jugadores;
    }

    /**
     * Devuelve el tablero del juego.
     * 
     * Precondiciones:
     * - Ninguna.
     * 
     * Postcondiciones:
     * - Devuelve una instancia de `Tablero`.
     * 
     * @return Tablero del juego.
     */
    public Tablero getTablero() {
        return tablero;
    }

    /**
     * Devuelve el dado del juego.
     * 
     * Precondiciones:
     * - Ninguna.
     * 
     * Postcondiciones:
     * - Devuelve una instancia de `Dado`.
     * 
     * @return Dado del juego.
     */
    public Dado getDado() {
        return dado;
    }

    /**
     * Devuelve al jugador que tiene el turno actual.
     * 
     * Precondiciones:
     * - La lista de jugadores no debe estar vacía.
     * 
     * Postcondiciones:
     * - Devuelve una instancia de `Jugador` correspondiente al turno actual.
     * 
     * @return Jugador con el turno actual.
     */
    public Jugador getJugadorActual() {
         /**
        if (jugadores.isEmpty()) {
            throw new IllegalStateException("Debe haber al menos un jugador en el juego para consultar el jugador actual.");
        }  
        **/

        return jugadores.get(turnoActual);
    }

    /**
     * Avanza el turno al siguiente jugador.
     * 
     * Precondiciones:
     * - Debe haber al menos un jugador en el juego.
     * 
     * Postcondiciones:
     * - El turno actual avanza de manera circular entre los jugadores.
     */
    public void avanzarTurno() {
        /**
        if (jugadores.isEmpty()) {
            throw new IllegalStateException("Debe haber al menos un jugador en el juego para consultar el jugador actual.");
        }  
        **/ 

        turnoActual = (turnoActual + 1) % jugadores.size();
    }

    /**
     * Lanza el dado del juego.
     * 
     * Precondiciones:
     * - Ninguna.
     * 
     * Postcondiciones:
     * - Retorna un valor entero entre 1 y 6.
     * - El valor se almacena en el dado asociado al juego.
     * 
     * @return El valor del dado (1-6).
     */
    public int lanzarDado() {
        int tirada = dado.tirar();
        return tirada;
    }

    /**
     * Determina si el juego ha finalizado.
     * 
     * Precondiciones:
     * - Debe haber al menos un jugador en el juego.
     * 
     * Postcondiciones:
     * - Devuelve `true` si algún jugador tiene 4 fichas en la meta.
     * - Devuelve `false` en caso contrario.
     * 
     * @return `true` si el juego ha terminado, `false` en caso contrario.
     */
    public boolean esFinDelJuego() {
        /**
        if (jugadores.isEmpty()) {
            throw new IllegalStateException("Debe haber al menos un jugador en el juego para consultar el jugador actual.");
        }  
        **/

        return jugadores.stream().anyMatch(jugador -> jugador.contarFichasEnFin() == 4);
    }

     /**
     * Devuelve el jugador ganador, si lo hay.
     * 
     * Precondiciones:
     * - Debe haber al menos un jugador en el juego.
     * 
     * Postcondiciones:
     * - Devuelve el jugador que tiene 4 fichas en la meta.
     * - Devuelve `null` si no hay ningún ganador.
     * 
     * @return Jugador ganador o `null`.
     */
    public Jugador obtenerGanador() {
        /**
        if (jugadores.isEmpty()) {
            throw new IllegalStateException("Debe haber al menos un jugador en el juego para consultar el jugador actual.");
        }  
        **/

        return jugadores.stream()
                .filter(jugador -> jugador.contarFichasEnFin() == 4)
                .findFirst()
                .orElse(null);
    }
}