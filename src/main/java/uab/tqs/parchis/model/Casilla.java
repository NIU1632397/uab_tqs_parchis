package uab.tqs.parchis.model;

import java.util.List;

/**
 * Interfaz que define el comportamiento de una casilla en un tablero de Parchís.
 * 
 * Contrato general:
 * - `getNumero()` siempre devuelve un número positivo.
 * - La lista de fichas nunca debe contener valores nulos.
 * - Los métodos de modificación (como `agregarFicha` y `quitarFicha`) deben mantener la coherencia interna
 *   de los estados dependientes, como `bloqueado` y `seguro`.
 */
public interface Casilla {

    /**
     * Devuelve el número de la casilla.
     * 
     * Precondiciones:
     * - Ninguna.
     * 
     * Postcondiciones:
     * - Devuelve un número positivo que identifica la casilla.
     * 
     * @return Número de la casilla.
     */
    int getNumero();

    /**
     * Indica si la casilla está bloqueada.
     * 
     * Precondiciones:
     * - Ninguna.
     * 
     * Postcondiciones:
     * - Devuelve `true` si la casilla está bloqueada, `false` en caso contrario.
     * 
     * @return Estado de bloqueo de la casilla.
     */
    boolean getBloqueo();

    /**
     * Indica si la casilla es segura.
     * 
     * Precondiciones:
     * - Ninguna.
     * 
     * Postcondiciones:
     * - Devuelve `true` si la casilla es segura, `false` en caso contrario.
     * 
     * @return `true` si la casilla es segura, `false` de lo contrario.
     */
    boolean getSeguro();

    /**
     * Devuelve la lista de fichas en la casilla.
     * 
     * Precondiciones:
     * - Ninguna.
     * 
     * Postcondiciones:
     * - Devuelve una lista no nula que contiene las fichas actualmente en la casilla.
     * 
     * @return Lista de fichas en la casilla.
     */
    List<Ficha> getFichas();

    /**
     * Agrega una ficha a la casilla.
     * 
     * Precondiciones:
     * - `ficha` no debe ser `null`.
     * 
     * Postcondiciones:
     * - La ficha se agrega a la lista de fichas en la casilla.
     * - La implementación concreta puede actualizar otros estados como `bloqueado`.
     * 
     * @param ficha Ficha a agregar.
     */
    void agregarFicha(Ficha ficha);

    /**
     * Quita una ficha de la casilla.
     * 
     * Precondiciones:
     * - `ficha` no debe ser `null`.
     * - `ficha` debe estar presente en la lista de fichas en la casilla.
     * 
     * Postcondiciones:
     * - La ficha se elimina de la lista de fichas en la casilla.
     * - La implementación concreta puede actualizar otros estados como `bloqueado`.
     * 
     * @param ficha Ficha a quitar.
     */
    void quitarFicha(Ficha ficha);
}
