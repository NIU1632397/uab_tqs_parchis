package uab.tqs.parchis.model;

public class Jugador {
    private String nombre;
    private String color;
    private Ficha[] fichas;

    public Jugador(String nombre, String color) {
        this.nombre = nombre;
        this.color = color;
        this.fichas = new Ficha[4]; // Inicialización fichas
        for (int i = 0; i < 4; i++) {
            fichas[i] = new Ficha(); 
        }
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
}
