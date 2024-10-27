package uab.tqs.parchis.model;

public class Ficha {
    private int pos;
    private boolean home;
    private boolean fin;

    public Ficha() {
        this.pos = 0;
        this.home = true; // La ficha empieza en casa
        this.fin = false;
    }

    public void mover(int pasos) {
        if (!fin) {
            pos += pasos;
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
}
