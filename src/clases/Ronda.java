package clases;

import java.util.List;
import java.util.ArrayList;

public class Ronda {
    private int nro;
    private List<Partido> partidos;
    private List<Pronostico> pronosticos;
    private List<Participante> participantes;
    public Ronda(int nro, List<Partido> partidos, List<Pronostico> pronosticos, List<Participante> participantes) {
        this.nro = nro;
        this.partidos = partidos;
        this.pronosticos = pronosticos;
        this.participantes = participantes;
    }
    public List<Participante> puntos() {
        //Iteramos el arreglo de participantes y de pronosticos y ejecutamos el metodo puntos() del objeto pronostico
        // para obtener los puntos de acuerdo al acierto o no del resultado y por ultimo mediante el metodo
        // sumarPuntosAc() del objeto participante se suma estos puntos a los puntos que ya tenia previamente (se acumula).
        for (Participante apostador : participantes) {
            String apostNomb = apostador.getNombre();
            //System.out.println("nombre particip: "+apostNomb);

            //La variable aciertos determinara la cantidad de aciertos del participante en la ronda
            int aciertos = 0;

            for (Pronostico pronostico : pronosticos) {
                String nombre = pronostico.getParticipante();
                //System.out.println("nombre pronostico"+nombre);

                if (apostNomb.equals(nombre)){
                    int puntos = pronostico.puntos();
                    //System.out.println("punto de pronostico: "+puntos);
                    if (puntos != 0) {
                        aciertos = aciertos + 1;
                    }
                    apostador.sumarPuntosAc(puntos);
                }
            }
            //System.out.println("cant de partidos: "+partidos.size());
            //En el caso de que los aciertos sean igual o mayor a la cantidad de partidos de la ronda
            // se le suman puntos extra
            if (aciertos >= partidos.size()) {
                //Puntos extra por acertar a todos los pronosticos de la ronda (3 puntos extra)
                apostador.sumarPuntosAc(3);
                //apostador.acertoTodalaRonda();
            }
            //System.out.println("Punto acumulado: "+apostador.getPuntosAc());
        }
        //Por ultimo retorna una lista de los participantes, con los puntos obtenidos en esa ronda
        return participantes;
        //return puntos;
    }

    //Este metodo muestra por pantalla los puntos obtenidos por cada participante en esta ronda:
    public void puntosPorRonda() {
        System.out.println("\nRonda n°: "+nro+"\n");
        for(Participante participant : participantes) {
            System.out.println(participant.getNombre()+", puntos acumulados: "+participant.getPuntosAc()+"\n");
            //System.out.println("\n");
        }
        System.out.println("****************************");
    }
    public List<Participante> getParticipantes() {
        return participantes;
    }
    public int getNro() {
        return nro;
    }
    public void setNro(int nro) {
        this.nro = nro;
    }
    public List<Partido> getPartidos() {
        return partidos;
    }
    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }
    public List<Pronostico> getPronosticos() {
        return pronosticos;
    }
}
