package uab.tqs.parchis.model;

import java.util.List;
import java.util.ArrayList;

/**
 * Representa una casilla normal en un tablero de Parchís.
 * 
 * Invariante:
 * - El número de fichas en la casilla (fichas.size()) nunca debe ser negativo.
 * - La propiedad `bloqueado` es true si y solo si hay exactamente 2 fichas en la casilla.
 * - El número de la casilla (`numero`) debe ser positivo y menor o igual a 68.
 */
public class CasillaNormal implements Casilla {
    private int numero;
    private boolean bloqueado;
    private List<Ficha> fichas;

    /**
     * Constructor de la casilla.
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
    public CasillaNormal(int num) {
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
     * - Devuelve el estado actual de la propiedad `bloqueado`.
     * 
     * @return `true` si la casilla está bloqueada, `false` en caso contrario.
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
     * - Devuelve siempre `false`, ya que esta casilla no es segura.
     * 
     * @return `false`.
     */
    @Override
    public boolean getSeguro() {
        return false;
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
     * Devuelve una representación en texto de una casilla normal.
     * 
     * Precondiciones:
     * - Ninguna.
     * 
     * Postcondiciones:
     * - Devuelve un texto con el número de la casilla indicando que es una casilla normal.
     * 
     * @return Representación textual de la casilla normal.
     */
    @Override
    public String toString() {
        return "CasillaNormal (número: " + numero + ")";
    }

    /**
     * Agrega una ficha a la casilla.
     * 
     * Precondiciones:
     * - `ficha` no debe ser `null`.
     * 
     * Postcondiciones:
     * - La ficha se agrega a la lista `fichas`.
     * - Si el número de fichas en la casilla es exactamente 2, se establece `bloqueado = true`.
     * - Si hay más de 2 fichas, el estado de `bloqueado` permanece inalterado.
     * 
     * @param ficha Ficha a agregar.
     */
    @Override
    public void agregarFicha(Ficha ficha) {
        if (ficha == null) {
            throw new IllegalArgumentException("La ficha no puede ser nula.");
        }
        this.fichas.add(ficha);

        // Verificar bloqueo
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
     * - Si el número de fichas en la casilla es menor a 2, se establece `bloqueado = false`.
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

        // Verificar desbloqueo
        if (this.fichas.size() < 2) {
            this.bloqueado = false;
        }
    }
}
