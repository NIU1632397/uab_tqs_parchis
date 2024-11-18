package uab.tqs.parchis.model;

import java.util.List;
import java.util.ArrayList;

public class CasillaCasa implements Casilla {
    private int numero;
    private boolean bloqueado;
    private List<Ficha> fichas;

    public CasillaCasa(int num) {
        this.numero = num;
        this.bloqueado = false;
        this.fichas = new ArrayList<>();
    }

    @Override
    public int getNumero() {
        return this.numero;
    }

    @Override
    public boolean getBloqueo() {
        return this.bloqueado;
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
        this.fichas.add(ficha);
        
        if (this.fichas.size() == 2) {
            this.bloqueado = true;
        }
    }

    @Override
    public void quitarFicha(Ficha ficha) {
        this.fichas.remove(ficha);

        if (this.fichas.size() < 2) {
            this.bloqueado = false;
        }
    }
}
