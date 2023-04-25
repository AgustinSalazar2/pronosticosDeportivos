package clases;

public class Partido {
    private Equipo equipo1;
    private Equipo equipo2;
    private int golesEquipo1;
    private int golesEquipo2;
    private int nroRonda;
    public Partido(Equipo equipo1, Equipo equipo2, int golesEquipo1, int golesEquipo2, int nroRonda) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.golesEquipo1 = golesEquipo1;
        this.golesEquipo2 = golesEquipo2;
        this.nroRonda = nroRonda;
    }
    public ResultadoEnum resultado(Equipo equipo) {
        //Si el equipo pasado por parámetro coincide con equipo1 y dicho equipo tiene más goles retorna GANADOR.
        if (equipo.equals(equipo1) && golesEquipo1 > golesEquipo2) {
            return ResultadoEnum.GANADOR;
        }
        //Sino si el equipo pasado por parámetro coincide con equipo2 y dicho equipo tiene más goles retorna GANADOR.
        else if (equipo.equals(equipo2) && golesEquipo2 > golesEquipo1) {
            return ResultadoEnum.GANADOR;
        }
        //Sino si los goles del equipo1 son iguales a los goles del equipo2 retorna EMPATE
        else if (golesEquipo1 == golesEquipo2) {
            return ResultadoEnum.EMPATE;
        }
        //En cualquier otro caso retorna PERDEDOR
        else {
            return ResultadoEnum.PERDEDOR;
        }
    }
    public Equipo getEquipo1() {
        return equipo1;
    }
    public Equipo getEquipo2() {
        return equipo2;
    }
    public int getGolesEquipo1() {
        return golesEquipo1;
    }
    public int getGolesEquipo2() {
        return golesEquipo2;
    }
    public int getNroRonda() {
        return nroRonda;
    }
}
