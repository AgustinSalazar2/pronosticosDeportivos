package clases;

import java.util.ArrayList;
import java.util.List;

public class Fase {
    private int nroFase;
    private List<Ronda> rondas;

    public Fase(int nroFase, List<Ronda> rondas) {
        this.nroFase = nroFase;
        this.rondas = rondas;
    }
    public void puntosParticipantesPorFase(){
        List<Participante> participanteAcum = new ArrayList<Participante>();
        Participante mariana = new Participante("Mariana");
        Participante pedro = new Participante("Pedro");
        participanteAcum.add(mariana);
        participanteAcum.add(pedro);

        //Se recorren las rondas y por cada participante y los puntos acumulados en dicha ronda
        // se van acumulando estos puntos con los obtenidos en el resto de rondas que tiene la fase
        // esto permite obtener los puntos acumulados por cada participante en dicha fase (conjunto de rondas).

        for (Ronda ronda : rondas) {
            int numRonda = ronda.getNro();
            List<Participante> apostadores = ronda.puntos();
            //List<Participante> apostadores = ronda.getParticipantes();

            for (Participante participAcum : participanteAcum) {
                String nombre = participAcum.getNombre();

                for (Participante apostador : apostadores) {
                    //System.out.println(apostador.getNombre()+", "+apostador.getPuntosAc());
                    String nombreApost = apostador.getNombre();
                    if (nombreApost.equals(nombre)) {
                        int puntos = apostador.getPuntosAc();
                        participAcum.sumarPuntosAc(puntos);
                    }
                }
            }
        }
        //return participanteAcum;

        //Muestra por pantalla el nombre del participante y los puntos acumulados.
        System.out.println("\nPuntos acumulados por fase:\n");
        for (Participante apostador : participanteAcum) {
            System.out.println(apostador.getNombre() + ", Puntos acumulados: " + apostador.getPuntosAc());
        }
        //System.out.println("****************");
    }

    public void puntosPorRonda() {
        for (Ronda ronda : rondas) {
            ronda.puntosPorRonda();
        }
    }
    public int getNroFase() {
        return nroFase;
    }
    public List<Ronda> getRondas() {
        return rondas;
    }
}
