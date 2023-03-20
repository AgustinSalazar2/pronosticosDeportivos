import clases.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Definimmos las variables necesarias
        BufferedReader lector;
        String linea;

        String rutaPartido = "D:\\Users\\Agustin\\Escritorio\\ProyectoJava\\src\\resultados.csv";
        String rutaPronostico = "D:\\Users\\Agustin\\Escritorio\\ProyectoJava\\src\\pronosticos.csv";

        //Se crea un nuevo arreglo de partidos y pronosticos
        List<Partido> partidos = new ArrayList<Partido>();
        List<Pronostico> pronosticos = new ArrayList<Pronostico>();

        try {
            lector = new BufferedReader(new FileReader(rutaPartido));
            while ((linea = lector.readLine()) != null) {
                //Se crea un array de string por cada linea
                String[] lineaArray = linea.split(";");

                //System.out.println(lineaArray[0]+" "+lineaArray[1]+" "+lineaArray[2]+" "+lineaArray[3]);
                //Se crean las variables y se le asignan los datos de cada posicion del array de strings
                Equipo equipo1 = new Equipo(lineaArray[0],"equipo1");
                Equipo equipo2 = new Equipo(lineaArray[3], "equipo2");
                int golesEquipo1 = Integer.parseInt(lineaArray[1]);
                int golesEquipo2 = Integer.parseInt(lineaArray[2]);

                //Se instancia un nuevo objeto de la clase partido con los parametros requeridos por su constructor
                Partido partido = new Partido(equipo1, equipo2, golesEquipo1, golesEquipo2);

                //Se agrega cada partido en el arreglo partidos
                partidos.add(partido);
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo partidos");
        }

        try {
            lector = new BufferedReader(new FileReader(rutaPronostico));
            //Se define un contador
            int cont = 0;
            while ((linea = lector.readLine()) != null) {
                //Se define un a variable resultado
                ResultadoEnum resultado = null;
                String[] lineaPronostico = linea.split(";");

                //Si la "x" se encuentra en la posicion 1 del arreglo se le asigna "ganador" a la variable resultado
                if (lineaPronostico[1].equals("x")) {
                    resultado = ResultadoEnum.GANADOR;
                }
                //Si la "x" se encuentra en la posicion 2 del arreglo se le asigna "empate" a la variable resultado
                else if (lineaPronostico[2].equals("x")) {
                    resultado = ResultadoEnum.EMPATE;
                }
                //Si la "x" se encuentra en la posicion 3 del arreglo se le asigna "perdedor" a la variable resultado
                else if (lineaPronostico[3].equals("x")) {
                    resultado = ResultadoEnum.PERDEDOR;
                }else {
                    System.out.println("No se encontró un posible resultado del partido");
                }
                //System.out.println(lineaPronostico[0]+" "+lineaPronostico[1]+" "+lineaPronostico[2]+" "+lineaPronostico[3]+" "+lineaPronostico[4]);

                //Se instancia un nuevo pronostico con los parametros requeridos por su constructor
                Pronostico pronostico = new Pronostico(partidos.get(cont), partidos.get(cont).getEquipo1(), resultado);

                //Se agrega cada pronostico en el arreglo pronosticos
                pronosticos.add(pronostico);

                //Se aumenta el contador en 1
                cont++;
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo pronosticos");
        }

        //Se instancia una nueva ronda de la clase Ronda y se le pasan los arreglos partidos y pronosticos
        Ronda ronda = new Ronda("1", partidos, pronosticos);

        //Se ejecuta el metodo puntos() del objeto ronda el cual devuelve el acumulado de puntos obtenidos por ronda
        //Se asigna dicho valor en una variable para luego mostrarla por consola

        int puntos = ronda.puntos();

        System.out.println("Puntaje = "+puntos);

    }
}