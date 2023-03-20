package clases;

public enum ResultadoEnum {
    GANADOR("ganador"),
    PERDEDOR("perdedor"),
    EMPATE("empate");
    private final String resultado;
    ResultadoEnum(String resultado) {
        this.resultado = resultado;
    }
    public String[] result() {
        String[] resultados = new String[3];
        resultados[0] = GANADOR.resultado;
        resultados[1] = PERDEDOR.resultado;
        resultados[2] = EMPATE.resultado;
        return resultados;
    }
}
