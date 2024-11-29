package uab.tqs.parchis.model;

import java.util.Random;

/**
 * Clase que representa un dado en el juego de Parchís.
 * 
 * Contrato general:
 * - `tirar()` siempre devuelve un número entero entre 1 y 6 (incluidos).
 * - `getResultado()` siempre devuelve el último valor generado por el dado.
 * - El dado debe mantener su consistencia interna: el valor retornado por `getResultado()` 
 *   coincide con el valor generado por el último llamado a `tirar()`.
 */
public class Dado {

    private Random random;
    private int resultado;

    /**
     * Constructor del dado.
     * 
     * Precondiciones:
     * - Ninguna.
     * 
     * Postcondiciones:
     * - El dado se inicializa con un valor por defecto (`resultado = 1`).
     */
    public Dado() {
        random = new Random();
        resultado = 1;
    }

    /**
     * Genera un nuevo valor para el dado.
     * 
     * Precondiciones:
     * - Ninguna.
     * 
     * Postcondiciones:
     * - Devuelve un número entero entre 1 y 6 (incluidos).
     * - El valor devuelto se almacena en `resultado`.
     * - Subsecuentes llamadas a `getResultado()` devolverán este valor.
     * 
     * @return El valor generado por el dado (1-6).
     */
    public int tirar() {
        resultado = random.nextInt(6) + 1;
        return resultado;
    }

     /**
     * Obtiene el último valor generado por el dado.
     * 
     * Precondiciones:
     * - Se debe haber llamado al menos una vez a `tirar()`, o se devolverá el valor inicial (1).
     * 
     * Postcondiciones:
     * - Devuelve el último valor almacenado en `resultado`.
     * - El valor devuelto está garantizado que será un entero entre 1 y 6.
     * 
     * @return El último resultado del dado.
     */
    public int getResultado() {
        return resultado;
    }
}