package clases;

public class ResultadoEnum {
    public String ganador = "GANADOR";
    public String perdedor = "PERDEDOR";
    public String empate = "EMPATE";

    public ResultadoEnum() {
    }

    public String getGanador() {
        return ganador;
    }

    public void setGanador(String ganador) {
        this.ganador = ganador;
    }

    public String getPerdedor() {
        return perdedor;
    }

    public void setPerdedor(String perdedor) {
        this.perdedor = perdedor;
    }

    public String getEmpate() {
        return empate;
    }

    public void setEmpate(String empate) {
        this.empate = empate;
    }
}
