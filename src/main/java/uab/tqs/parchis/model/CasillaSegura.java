package uab.tqs.parchis.model;

import java.util.List;
import java.util.ArrayList;

/**
 * Representa una casilla segura en un tablero de Parchís, donde las fichas están protegidas.
 * 
 * Invariante:
 * - El número de fichas en la casilla (`fichas.size()`) nunca debe ser negativo.
 * - El número de la casilla (`numero`) debe ser positivo y menor o igual a 68.
 * - `bloqueado` debe ser `true` si y solo si hay exactamente 2 fichas en la casilla.
 */
public class CasillaSegura implements Casilla {
    private int numero;
    private boolean bloqueado;
    private List<Ficha> fichas;
    /**
     * Constructor de la casilla segura.
     * 
     * Precondiciones:
     * - `num` debe ser un número positivo.
     * - `num` no debe ser mayor a 68.
     * 
     * Postcondiciones:
     * - `numero` se inicializa con el valor de `num`.
     * - `bloqueado` se inicializa como `false`.
     * - `fichas` se inicializa como una lista vacía.
     * 
     * @param num Número de la casilla.
     */
    public CasillaSegura(int num) {
        if (num <= 0) {
            throw new IllegalArgumentException("El número de la casilla debe ser positivo.");
        }
        if (num > 68) {
            throw new IllegalArgumentException("El número de la casilla no puede superar 68.");
        }
        this.numero = num;
        this.bloqueado = false;
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
     * - Devuelve el estado actual del bloqueo.
     * 
     * @return Estado de bloqueo de la casilla.
     */
    @Override
    public boolean getBloqueo() {
        return this.bloqueado;
    }

    /**
     * Indica si la casilla es segura.
     * 
     * Precondiciones:
     * - Ninguna.
     * 
     * Postcondiciones:
     * - Devuelve siempre `true`, ya que esta casilla es segura.
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
     * Agrega una ficha a la casilla.
     * 
     * Precondiciones:
     * - `ficha` no debe ser `null`.
     * 
     * Postcondiciones:
     * - La ficha se agrega a la lista `fichas`.
     * - Si hay exactamente 2 fichas en la casilla, se establece `bloqueado` como `true`.
     * 
     * @param ficha Ficha a agregar.
     */
    @Override
    public void agregarFicha(Ficha ficha) {
        if (ficha == null) {
            throw new IllegalArgumentException("La ficha no puede ser nula.");
        }
        this.fichas.add(ficha);

        // Actualizar el estado de bloqueo
        if (this.fichas.size() == 2) {
            this.bloqueado = true;
        }
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
     * - Si hay menos de 2 fichas en la casilla, se establece `bloqueado` como `false`.
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

        // Actualizar el estado de bloqueo
        if (this.fichas.size() < 2) {
            this.bloqueado = false;
        }
    }
}
