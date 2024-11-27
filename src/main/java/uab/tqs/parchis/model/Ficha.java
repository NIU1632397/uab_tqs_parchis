package uab.tqs.parchis.model;

public class Ficha {
    private int pos;
    private boolean home;
    private boolean fin;
    private String nombre;
    private boolean estaFinal;
    private String color;

    /**
     * Constructor por defecto: la ficha inicia en casa (home = true) y fuera de la meta (fin = false).
     */
    public Ficha() {
        this.pos = 0;
        this.home = true; 
        this.fin = false;
        this.nombre = "";
        this.estaFinal = false;
        this.color = "";
    }

    public Ficha(String nombre, String color) {
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
