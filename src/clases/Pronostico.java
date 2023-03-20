package clases;

public class Pronostico {
    private Partido partido;
    private Equipo equipo;
    private ResultadoEnum resultado;

    public Pronostico(Partido partido, Equipo equipo, ResultadoEnum resultado) {
        this.partido = partido;
        this.equipo = equipo;
        this.resultado = resultado;
    }

    public int puntos() {
        //Se utiliza el método resultado, del objeto partido, para conocer el resultado del partido
        //y se compara con el valor de la propiedad resultado que se recibe como parámetro al instanciar el objeto
        // si coinciden retorna 1 punto, de lo contrario retorna 0
        if (partido.resultado(equipo) == resultado) {
            return 1;
//        } else if ((resultado == ResultadoEnum.EMPATE) && (partido.resultado(equipo) == ResultadoEnum.EMPATE)) {
//            return 1;
        } else {
            return 0;
        }
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public ResultadoEnum getResultado() {
        return resultado;
    }

    public void setResultado(ResultadoEnum resultado) {
        this.resultado = resultado;
    }
}
