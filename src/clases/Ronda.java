package clases;

import java.util.List;
import java.util.ArrayList;

public class Ronda {
    private String nro;
    private List<Partido> partidos;

    public Ronda(String nro, List<Partido> partidos) {
        this.nro = nro;
        this.partidos = partidos;
    }

    public int puntos() {
        //Definir que hace el método 😅
        int puntos = 0;
        return puntos;
    }

    public String getNro() {
        return nro;
    }

    public void setNro(String nro) {
        this.nro = nro;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }
}
