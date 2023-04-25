package clases;

public class Participante {
    private String nombre;
    private int puntosAc = 0;
    private boolean aciertoEnTodaLaRonda = false;

    public Participante(String nombre) {
        this.nombre = nombre;
        //this.puntosAc = puntosAc;
    }

    //Este metodo recibe como parametro puntos y los suma a la propiedad puntosAc.
    public void sumarPuntosAc(int puntosAc) {
        this.puntosAc += puntosAc;
    }
    public void acertoTodalaRonda(){
        aciertoEnTodaLaRonda = true;
    }
    public boolean isAciertoEnTodaLaRonda() {
        return aciertoEnTodaLaRonda;
    }
    public String getNombre() {
        return nombre;
    }
    public int getPuntosAc() {
        return puntosAc;
    }
}
