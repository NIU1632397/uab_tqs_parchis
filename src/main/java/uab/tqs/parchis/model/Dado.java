package uab.tqs.parchis.model;

import java.util.Random;

public class Dado {

    private Random random;
    private int resultado;

    public Dado() {
        random = new Random();
        resultado = 1;
    }

    public int tirar() {
        resultado = random.nextInt(6) + 1;
        return resultado;
    }

    public int getResultado() {
        return resultado;
    }
}