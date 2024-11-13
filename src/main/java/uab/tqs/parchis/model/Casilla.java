package uab.tqs.parchis.model;

import java.util.List;

public interface Casilla {
    int getNumero();
    boolean getSeguro();
    List<Ficha> getFichas();
    void agregarFicha(Ficha ficha);
    void quitarFicha(Ficha ficha);
}
