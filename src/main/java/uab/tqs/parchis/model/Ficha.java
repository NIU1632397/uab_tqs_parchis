package uab.tqs.parchis.model;

/**
 * Clase que representa una ficha en el juego de Parchís.
 * 
 * Contrato general:
 * - Una ficha comienza en casa (`home = true`) y fuera de la meta (`fin = false`).
 * - La posición de la ficha (`pos`) siempre es un número no negativo.
 * - La ficha puede moverse solo si no está en `home` ni en `fin`.
 * - Si la posición de la ficha supera 68 al moverse, vuelve al inicio del tablero (posición ajustada circularmente).
 */
public class Ficha {
    private int pos;
    private boolean home;
    private boolean fin;
    private String nombre;
    private boolean estaFinal;
    private String color;

    /**
     * Constructor por defecto: inicializa la ficha con valores predeterminados.
     * 
     * Precondiciones:
     * - Ninguna.
     * 
     * Postcondiciones:
     * - `pos` se inicializa en `0`.
     * - `home` es `true`.
     * - `fin` es `false`.
     * - `nombre` es una cadena vacía.
     * - `estaFinal` es `false`.
     * - `color` es una cadena vacía.
     */
    public Ficha() {
        this.pos = 0;
        this.home = true; 
        this.fin = false;
        this.nombre = "";
        this.estaFinal = false;
        this.color = "";
    }

    /**
     * Constructor que permite especificar el nombre y color de la ficha.
     * 
     * Precondiciones:
     * - `nombre` no debe ser `null`.
     * - `color` no debe ser `null`.
     * 
     * Postcondiciones:
     * - `pos` se inicializa en `0`.
     * - `home` es `true`.
     * - `fin` es `false`.
     * - `nombre` se inicializa con el valor proporcionado.
     * - `estaFinal` es `false`.
     * - `color` se inicializa con el valor proporcionado.
     * 
     * @param nombre Nombre de la ficha.
     * @param color Color de la ficha.
     */
    public Ficha(String nombre, String color) {
        if (nombre == null || color == null) {
            throw new IllegalArgumentException("El nombre y el color no pueden ser nulos.");
        }

        this.pos = 0;
        this.home = true; 
        this.fin = false;
        this.nombre = nombre;
        this.estaFinal = false;
        this.color = color;
    }

    /**
     * Mueve la ficha un número de pasos si no está en casa o en la meta.
     *
     * Precondiciones:
     * - pasos >= 0, ya que un número negativo puede considerarse un error de entrada en este caso.
     *
     * Postcondiciones:
     * - Si `home` es `true`, la posición no debe cambiar.
     * - Si `fin` es `true`, la posición no debe cambiar.
     * - Si `home` y `fin` son `false`, la posición debe incrementar en `pasos`.
     *
     * @param pasos Número de pasos a mover la ficha.
     */
    public void mover(int pasos) {
        if (pasos < 0) {
            throw new IllegalArgumentException("El número de pasos debe ser no negativo.");
        }

        if (!fin) {
            pos += pasos;
            if (pos > 68) {
                pos = pos-68;
            }
        }
    }

    // Getters
    public int getPos() {
        return pos;
    }

    public boolean isHome() {
        return home;
    }

    public boolean isFin() {
        return fin;
    }

    public String getName() {
        return nombre;
    }

    public boolean getEstaFinal() {
        return estaFinal;
    }

    public String getColor() {
        return color;
    }

    // Setters
    public void setPos(int pos) {
        this.pos = pos;
    }

    public void setHome(boolean home) {
        this.home = home;
    }

    public void setFin(boolean fin) {
        this.fin = fin;
    }

    public void setEstaFinal(boolean estaFinal) {
        this.estaFinal = estaFinal;
    }
}
