package uab.tqs.parchis.model;

public class Jugador {
    private String nombre;
    private String color;
    private Ficha[] fichas;
    private boolean turno;

    /**
     * Constructor principal para crear un jugador.
     * @param nombre Nombre del jugador, no nulo ni vacío.
     * @param color Color del jugador, no nulo ni vacío.
     * @throws IllegalArgumentException Si el nombre o color son nulos o vacíos.
     */
    public Jugador(String nombre, String color) {
        if (nombre == null || nombre.isEmpty()) throw new IllegalArgumentException("El nombre no puede ser nulo o estar vacio.");
        if (color == null || color.isEmpty()) throw new IllegalArgumentException("El color no puede ser nulo o estar vacio.");
        
        this.nombre = nombre;
        this.color = color;
        this.fichas = new Ficha[4]; // Inicialización fichas
        for (int i = 0; i < 4; i++) {
            fichas[i] = new Ficha(color + i); 
        }
    }

    /**
     * Constructor adicional que permite definir el estado de turno inicial del jugador.
     * @param nombre Nombre del jugador, no nulo ni vacío.
     * @param color Color del jugador, no nulo ni vacío.
     * @param turno Estado inicial del turno.
     */
    public Jugador(String nombre, String color, boolean turno) {
        this(nombre, color);  // Reutiliza el constructor principal
        this.turno = turno;
    }


    public String getNombre() {
        return nombre;
    }

    public String getColor() {
        return color;
    }

    public Ficha[] getFichas() {
        return fichas;
    }

    public boolean isTurno() {
        return turno;
    }
    
    /**
     * Cambia el estado de turno del jugador.
     * Precondición: El valor de turno debe diferir del estado actual.
     * Postcondición: El turno del jugador debe ser igual al nuevo valor de turno.
     * @param turno El nuevo estado de turno.
     */
    public void setTurno(boolean turno) {
        if (this.turno != turno) {
            this.turno = turno;
        }
    }

    /**
     * Mueve una ficha específica del jugador.
     * Precondición: El índice de ficha debe estar entre 0 y 3, y el jugador debe estar en su turno.
     * Postcondición: La posición de la ficha debe cambiar si no está en home ni en fin.
     * @param indice El índice de la ficha a mover (0-3).
     * @param pasos Número de pasos a mover.
     * @throws IllegalArgumentException Si el índice es inválido o los pasos son negativos.
     * @throws IllegalStateException Si el jugador no tiene el turno.
     */
    public void moverFicha(int indice, int pasos) {
        if (indice < 0 || indice >= fichas.length) {
            throw new IllegalArgumentException("Índice de ficha fuera de rango.");
        }
        if (pasos < 0) {
            throw new IllegalArgumentException("El número de pasos no puede ser negativo.");
        }
        if (!turno) {
            throw new IllegalStateException("No es el turno del jugador.");
        }

        fichas[indice].mover(pasos);
    }

    /**
     * Método para contar cuántas fichas han llegado a la meta (fin).
     * Invariante: el número de fichas en fin debe estar entre 0 y 4.
     * @return El número de fichas en fin.
     */
    public int contarFichasEnFin() {
        int count = 0;
        for (Ficha ficha : fichas) {
            if (ficha.isFin()) {
                count++;
            }
        }
        assert count >= 0 && count <= 4 : "El número de fichas en fin debe estar entre 0 y 4.";
        return count;
    }
}
