import clases.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class PronosticosDeportivos {
    public static void main(String[] args) {

        BufferedReader lector;
        String linea;
        String rutaArchivo = "D:\\Users\\Agustin\\Escritorio\\ProyectoJava\\src\\resultados.csv";
        List<Partido> partidos = new ArrayList<Partido>();
        try {
            lector = new BufferedReader(new FileReader(rutaArchivo));
            while ((linea = lector.readLine()) != null) {
                //Se imprime cada línea.
                //Las columnas de cada línea estarán delimitadas por una ,(coma) o un ;(punto y coma)
                //System.out.println(linea);
                String[] lineaArray = linea.split(";");
                //System.out.println(lineaArray[0]+" "+lineaArray[1]+" "+lineaArray[2]+" "+lineaArray[3]);
                Equipo equipo1 = new Equipo(lineaArray[0],"equipo1");
                Equipo equipo2 = new Equipo(lineaArray[3], "equipo2");
                int golesEquipo1 = Integer.parseInt(lineaArray[1]);
                int golesEquipo2 = Integer.parseInt(lineaArray[2]);
                Partido partido = new Partido(equipo1, equipo2, golesEquipo1, golesEquipo2);
                partidos.add(partido);
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo partidos");
        }
    }
}