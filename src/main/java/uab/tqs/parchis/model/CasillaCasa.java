package uab.tqs.parchis.model;

import java.util.List;
import java.util.ArrayList;

public class CasillaCasa implements Casilla {
    private int numero;
    private List<Ficha> fichas;

    public CasillaCasa(int num) {
        this.numero = num;
        this.fichas = new ArrayList<>();
    }

    @Override
    public int getNumero() {
        return this.numero;
    }

    @Override
    public boolean getSeguro() {
        return true;
    }

    @Override
    public List<Ficha> getFichas() {
        return this.fichas;
    }
    
    @Override
    public void agregarFicha(Ficha ficha) {
        fichas.add(ficha);
    }

    @Override
    public void quitarFicha(Ficha ficha) {
        this.fichas.remove(ficha);
    }
}
