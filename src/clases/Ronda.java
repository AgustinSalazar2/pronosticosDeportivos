package clases;

import java.util.List;
import java.util.ArrayList;

public class Ronda {
    private String nro;
    private List<Partido> partidos;
    private List<Pronostico> pronosticos;

    public Ronda(String nro, List<Partido> partidos, List<Pronostico> pronosticos) {
        this.nro = nro;
        this.partidos = partidos;
        this.pronosticos = pronosticos;
    }

    public int puntos() {
        //Definir que hace el método 😅
        int puntos = 0;

        for (Pronostico pronostico : pronosticos) {
            puntos = puntos + pronostico.puntos();
        }
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
