package uab.tqs.parchis.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa el tablero del juego de Parchís.
 * 
 * Invariantes:
 * - El tablero principal (`tablero`) contiene exactamente 69 casillas (1 casa y 68 normales/seguras).
 * - El tablero final (`tablero_final`) contiene exactamente 8 casillas finales.
 */
public class Tablero {
    private List<Casilla> tablero;
    private List<Casilla> tablero_final;

    /**
     * Constructor del Tablero. 
     * 
     * Precondiciones:
     * - Ninguna.
     * 
     * Postcondiciones:
     * - `tablero` es inicializado con 69 casillas: 1 casa y 68 normales/seguras.
     * - `tablero_final` es inicializado con 8 casillas finales.
     */
    public Tablero() {
        this.tablero = new ArrayList<>();
        inicializarTablero();

        this.tablero_final = new ArrayList<>();
        inicializarTableroFinal();
    }

    /**
     * Inicializa el tablero principal con las casillas necesarias.
     * 
     * Precondiciones:
     * - `tablero` debe estar inicializado como una lista vacía.
     * 
     * Postcondiciones:
     * - `tablero` contiene 69 casillas:
     *     - Una casilla de tipo `CasillaCasa` en la posición 0.
     *     - 68 casillas normales y seguras desde la posición 1 hasta la 68.
     */
    private void inicializarTablero() {
        if (this.tablero == null) {
            throw new IllegalStateException("La lista tablero no está inicializada.");
        }

        // Añadir casilla casa
        this.tablero.add(new CasillaCasa(0));

        // Añadir las 68 casillas normales/seguras
        for (int i = 1; i <= 68; i++) {
            if (esCasillaSegura(i)) {
                this.tablero.add(new CasillaSegura(i));
            } else {
                this.tablero.add(new CasillaNormal(i));
            }
        }

        // Postcondición: Validar que el tablero tiene exactamente 69 casillas.
        if (this.tablero.size() != 69) {
            throw new IllegalStateException("El tablero principal no contiene 69 casillas.");
        }
    }

    /**
     * Inicializa el tablero final con las 8 casillas finales.
     * 
     * Precondiciones:
     * - `tablero_final` debe estar inicializado como una lista vacía.
     * 
     * Postcondiciones:
     * - `tablero_final` contiene exactamente 8 casillas finales numeradas del 1 al 8.
     */
    private void inicializarTableroFinal() {
        if (this.tablero_final == null) {
            throw new IllegalStateException("La lista tablero_final no está inicializada.");
        }

        // Añadir las 8 casillas finales
        for (int i = 1; i <= 8; i++) {
            this.tablero_final.add(new CasillaFinal(i));
        }

        // Postcondición: Validar que tablero_final tiene exactamente 8 casillas.
        if (this.tablero_final.size() != 8) {
            throw new IllegalStateException("El tablero final no contiene 8 casillas.");
        }
    }

    /**
     * Verifica si una casilla es segura según su número.
     * 
     * Precondiciones:
     * - `numero` debe estar entre 1 y 68.
     * 
     * Postcondiciones:
     * - Devuelve true si el número corresponde a una casilla segura.
     * - Devuelve false en caso contrario.
     * 
     * @param numero Número de la casilla.
     * @return `true` si la casilla es segura, `false` en caso contrario.
     */
    private boolean esCasillaSegura(int numero) {
        if (numero < 1 || numero > 68) {
            throw new IllegalArgumentException("El número de casilla debe estar entre 1 y 68.");
        }

        int[] casillasSeguras = {5, 12, 17, 24, 29, 36, 41, 48};
        for (int segura : casillasSeguras) {
            if (numero == segura) {
                return true;
            }
        }
        return false;
    }

    /**
     * Devuelve todas las casillas del tablero.
     * 
     * Precondiciones:
     * - Ninguna.
     * 
     * Postcondiciones:
     * - Devuelve la lista completa de casillas del tablero principal.
     * 
     * @return Lista de casillas del tablero.
     */
    public List<Casilla> getTablero() {
        return this.tablero;
    }

    /**
     * Devuelve todas las casillas del tablero final.
     * 
     * Precondiciones:
     * - Ninguna.
     * 
     * Postcondiciones:
     * - Devuelve la lista completa de casillas del tablero final.
     * 
     * @return Lista de casillas del tablero final.
     */
    public List<Casilla> getTableroFinal() {
        return this.tablero_final;
    }

    /**
     * Obtiene una casilla del tablero principal por su número.
     * 
     * Precondiciones:
     * - `numero` debe estar entre 0 y 68.
     * 
     * Postcondiciones:
     * - Devuelve la casilla correspondiente si existe.
     * - Lanza una excepción si el número es inválido.
     * 
     * @param numero Número de la casilla.
     * @return La casilla correspondiente.
     */
    public Casilla getCasilla(int numero) {
        if (numero < 0 || numero >= tablero.size()) {
            throw new IndexOutOfBoundsException("El número de casilla está fuera de rango.");
        }

        return this.tablero.get(numero);
    }
}
