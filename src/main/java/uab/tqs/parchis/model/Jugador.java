package uab.tqs.parchis.model;

public class Jugador {
    private String nombre;
    private String color;
    private Ficha[] fichas;
    private boolean turno;

    public Jugador(String nombre, String color) {
        if (nombre == null || nombre.isEmpty()) throw new IllegalArgumentException("El nombre no puede ser nulo o estar vacio");
        if (color == null || color.isEmpty()) throw new IllegalArgumentException("El color no puede ser nulo o estar vacio");
        
        this.nombre = nombre;
        this.color = color;
        this.fichas = new Ficha[4]; // Inicialización fichas
        for (int i = 0; i < 4; i++) {
            fichas[i] = new Ficha(); 
        }
    }

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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    public void setTurno(boolean turno) {
        this.turno = turno;
    }
}
