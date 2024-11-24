package uab.tqs.parchis.model;

import java.util.List;
import java.util.ArrayList;

/**
 * Representa una casilla final en un tablero de Parchís, donde las fichas alcanzan la meta.
 * 
 * Invariante:
 * - El número de fichas en la casilla (`fichas.size()`) nunca debe ser negativo.
 * - El número de la casilla (`numero`) debe ser positivo y menor o igual a 68.
 */
public class CasillaFinal implements Casilla {
    private int numero;
    private List<Ficha> fichas;

    /**
     * Constructor de la casilla final.
     * 
     * Precondiciones:
     * - `num` debe ser un número positivo.
     * - `num` no debe ser mayor a 68.
     * 
     * Postcondiciones:
     * - `numero` se inicializa con el valor de `num`.
     * - `fichas` se inicializa como una lista vacía.
     * 
     * @param num Número de la casilla.
     */
    public CasillaFinal(int num) {
        if (num <= 0) {
            throw new IllegalArgumentException("El número de la casilla debe ser positivo.");
        }
        if (num > 68) {
            throw new IllegalArgumentException("El número de la casilla no puede superar 68.");
        }
        this.numero = num;
        this.fichas = new ArrayList<>();
    }

    /**
     * Devuelve el número de la casilla.
     * 
     * Precondiciones:
     * - Ninguna.
     * 
     * Postcondiciones:
     * - El número de la casilla se devuelve sin modificaciones.
     * 
     * @return Número de la casilla.
     */
    @Override
    public int getNumero() {
        return this.numero;
    }

    /**
     * Indica si la casilla está bloqueada.
     * 
     * Precondiciones:
     * - Ninguna.
     * 
     * Postcondiciones:
     * - Devuelve siempre `false`, ya que las casillas finales no pueden estar bloqueadas.
     * 
     * @return `false`.
     */
    @Override
    public boolean getBloqueo() {
        return false;
    }

    /**
     * Indica si la casilla es segura.
     * 
     * Precondiciones:
     * - Ninguna.
     * 
     * Postcondiciones:
     * - Devuelve siempre `true`, ya que las casillas finales son seguras.
     * 
     * @return `true`.
     */
    @Override
    public boolean getSeguro() {
        return true;
    }

    /**
     * Devuelve la lista de fichas en la casilla.
     * 
     * Precondiciones:
     * - Ninguna.
     * 
     * Postcondiciones:
     * - Devuelve la lista actual de fichas sin modificarla.
     * 
     * @return Lista de fichas en la casilla.
     */
    @Override
    public List<Ficha> getFichas() {
        return this.fichas;
    }

    /**
     * Devuelve una representación en texto de una casilla final.
     * 
     * Precondiciones:
     * - Ninguna.
     * 
     * Postcondiciones:
     * - Devuelve un texto con el número de la casilla indicando que es una casilla final.
     * 
     * @return Representación textual de la casilla final.
     */
    @Override
    public String toString() {
        StringBuilder mensaje = new StringBuilder("CasillaFinal: (número: " + numero + "), Fichas: ");
        for (Ficha ficha : this.fichas) {
            mensaje.append(ficha.getName() + ", ");
        }
        return mensaje.toString();
    }

    /**
     * Agrega una ficha a la casilla.
     * 
     * Precondiciones:
     * - `ficha` no debe ser `null`.
     * 
     * Postcondiciones:
     * - La ficha se agrega a la lista `fichas`.
     * 
     * @param ficha Ficha a agregar.
     */
    @Override
    public void agregarFicha(Ficha ficha) {
        if (ficha == null) {
            throw new IllegalArgumentException("La ficha no puede ser nula.");
        }
        this.fichas.add(ficha);
    }

    /**
     * Quita una ficha de la casilla.
     * 
     * Precondiciones:
     * - `ficha` no debe ser `null`.
     * - `ficha` debe estar presente en la lista `fichas`.
     * 
     * Postcondiciones:
     * - La ficha se elimina de la lista `fichas`.
     * 
     * @param ficha Ficha a quitar.
     */
    @Override
    public void quitarFicha(Ficha ficha) {
        if (ficha == null) {
            throw new IllegalArgumentException("La ficha no puede ser nula.");
        }
        if (!this.fichas.contains(ficha)) {
            throw new IllegalStateException("La ficha no está presente en la casilla.");
        }
        this.fichas.remove(ficha);
    }
}
