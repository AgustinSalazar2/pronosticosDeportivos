package clases;

public class Pronostico {
    private Partido partido;
    private Equipo equipo;
    private ResultadoEnum resultado;
    private String participante;
    private int nroRonda;
    private int puntosPorAcierto;
    public Pronostico(Partido partido, Equipo equipo, ResultadoEnum resultado, String participante, int nroRonda, int puntosPorAcierto) {
        this.partido = partido;
        this.equipo = equipo;
        this.resultado = resultado;
        this.participante = participante;
        this.nroRonda = nroRonda;
        this.puntosPorAcierto = puntosPorAcierto;
    }
    public int puntos() {
        //Se utiliza el método resultado, del objeto partido, para conocer el resultado del partido
        // y se compara con el valor de la propiedad resultado que se recibe como parámetro al instanciar el objeto
        // si coinciden retorna los puntos determinados al inicio del programa (puntosPorAcierto), de lo contrario retorna 0
        if (partido.resultado(equipo) == resultado) {
            return puntosPorAcierto;
//        } else if ((resultado == ResultadoEnum.EMPATE) && (partido.resultado(equipo) == ResultadoEnum.EMPATE)) {
//            return 1;
        } else {
            return 0;
        }
    }
    public String getParticipante() {
        return participante;
    }
    public int getNroRonda() {
        return nroRonda;
    }
    public Partido getPartido() {
        return partido;
    }
    public Equipo getEquipo() {
        return equipo;
    }
    public ResultadoEnum getResultado() {
        return resultado;
    }
}
