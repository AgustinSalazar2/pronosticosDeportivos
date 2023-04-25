import clases.*;

import java.sql.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    //Constantes de la base de datos
    public static final String DB_NAME = "pronosticos_Deportivos.db";
    //Ruta para la coneccion a la base de datos (ruta donde se guardara la base de datos...cambiar si es necesario para realizar pruebas)
    public static final String CONNECTION_STRING = "jdbc:sqlite:D:\\Users\\Agustin\\Escritorio\\proyectoFinal\\pronosticosDeportivos\\" + DB_NAME;
    public static final String TABLE_PRONOSTICOS = "pronosticos";
    public static final String COLUMN_PRONOSTICO_ID = "id_pronostico";
    public static final String COLUMN_EQUIPO1 = "equipo1";
    public static final String COLUMN_GANA1 = "gana1";
    public static final String COLUMN_EMPATA = "empata";
    public static final String COLUMN_GANA2 = "gana2";
    public static final String COLUMN_EQUIPO2 = "equipo2";
    public static final String COLUMN_PARTICIPANTE = "participante";
    public static final String COLUMN_RONDA = "ronda";
    public static final String COLUMN_NRO_PARTIDO = "nro_partido";

    public static void main(String[] args) {
        try {
            //Conexión a travez del driver JDBC
            Class.forName("org.sqlite.JDBC");

            Connection conn = DriverManager.getConnection(CONNECTION_STRING);

            Statement statement = conn.createStatement();

            //Importante!! Descomentar para realizar pruebas

            //Elimino si existe una tabla PRONOSTICOS y creo una nueva:

            /*
            statement.execute("DROP TABLE IF EXISTS " + TABLE_PRONOSTICOS);
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_PRONOSTICOS +
                    " (" + COLUMN_PRONOSTICO_ID + " integer PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_EQUIPO1 + " text, " +
                    COLUMN_GANA1 + " text, " +
                    COLUMN_EMPATA + " text," +
                    COLUMN_GANA2 + " text," +
                    COLUMN_EQUIPO2 + " text," +
                    COLUMN_PARTICIPANTE + " text," +
                    COLUMN_NRO_PARTIDO + " integer," +
                    COLUMN_RONDA + " integer" +
                    ")");
             */

            //Inserto registros de los pronosticos:

            /*
            insertPronostico(statement, "Argentina", "x", "", "", "Arabia Saudita", "Mariana", 1, 1);
            insertPronostico(statement, "Polonia", "", "x", "", "Mexico", "Mariana", 2, 1);
            insertPronostico(statement, "Argentina", "x", "", "", "Mexico", "Mariana", 3, 2);
            insertPronostico(statement, "Arabia Saudita", "", "", "x", "Polonia", "Mariana", 4, 2);
            insertPronostico(statement, "Argentina", "x", "", "", "Arabia Saudita", "Pedro", 1, 1);
            insertPronostico(statement, "Polonia", "", "", "x", "Mexico", "Pedro", 2, 1);
            insertPronostico(statement, "Argentina", "x", "", "", "Mexico", "Pedro", 3, 2);
            insertPronostico(statement, "Arabia Saudita", "", "x", "", "Polonia", "Pedro", 4, 2);
           */

            //System.out.println("\nConexión establecida\n");

            //Solícito traer todas las columnas de la tabla PRONOSTICOS:
            ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_PRONOSTICOS);

            //Ruta del archivo de resultados (cambiar si es necesario para realizar las pruebas).
            String rutaArchivoResultados = "D:\\Users\\Agustin\\Escritorio\\proyectoFinal\\pronosticosDeportivos\\src\\resultados.csv";

            //Se determina la cantidad de rondas (esta informacion se puede leer desde un archivo tambien)
            int cantRondas = 2;

            //En esta variable se determina la cantidad de puntos que se va a asignar por cada acierto en el pronostico (ganador, perdedor, empate)
            int puntosPorAcierto = 1;

            //Se crea una lista que almacene objetos partidos, otro para pronosticos y otro para rondas:
            List<Ronda> listaDeRonda = new ArrayList<Ronda>();
            List<Partido> partidos = new ArrayList<Partido>();
            List<Pronostico> pronosticos = new ArrayList<Pronostico>();

            //Se utilizan los metodos declarados mas abajo:

            //El metodo leerArchivoResultados() recibe como parametros la ruta del archivo de resultados y una lista de objetos de tipo Partido
            // y retorna una lista de partidos.
            partidos = leerArchivoResultados(rutaArchivoResultados, partidos);

            //El metodo cargarListaPronosticos() recibe como parametros el resultados obtenido en la consulta a la BD (tabla pronosticos)
            // una lista de objetos de tipo Partido otra lista de objetos tipo Pronostico y la variable puntosPorAcierto,
            // y retorna una lista de pronosticos.
            pronosticos = cargarListaPronosticos(results, pronosticos, partidos, puntosPorAcierto);

            //El metodo ordenarPorRondas() recibe como parametros una lista de objetos tipo Partido, una lista de objetos tipo Pronostico
            // una lista de objetos tipo Ronda y la variable cantRondas (cantidad de rondas por fase). Retorna una lista de rondas.
            listaDeRonda = ordenarPorRondas(partidos, pronosticos, listaDeRonda, cantRondas);

            //Cerrando conexiones a la BD
            results.close();
            statement.close();
            conn.close();

            //Se instancia un objeto de la clase Fase y se utiliza su metodo puntosParticipantesPorFase()
            // el cual devuelve los puntos acumulados por cada participante en todas las rondas de esa fase:
            Fase fase = new Fase(1, listaDeRonda);

            fase.puntosParticipantesPorFase();

            System.out.println("\n****************************");
            //En este caso lo que se hace es utilizar el metodo puntosPorRonda() del objeto fase el cual muestra
            // los puntos que lleva acumulado cada participante en cada ronda de la fase.
            fase.puntosPorRonda();

        } catch (SQLException e) {
            System.out.println("Algo va mal: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Controlador no encontrado: " + e.getMessage());
        }
    }

    //Metodo utilizados:
    private static List<Ronda> ordenarPorRondas(List<Partido> partidos, List<Pronostico> pronosticos, List<Ronda> listaDeRonda, int cantRondas) {
        //Este metodo recorre la lista de partidos y la lista de pronosticos y los agrupa en un objeto del tipo Ronda
        // de acuerdo a la ronda que corresponden cada uno y por ultimo retorna una lista de estas rondas.
        for (int i = 1; i <= cantRondas; i++) {
            List<Partido> partidoss = new ArrayList<Partido>();
            List<Pronostico> prediccion = new ArrayList<Pronostico>();
            //System.out.println(partidos.size());
            for (Partido result : partidos) {
                //System.out.println(result.getEquipo1().getNombre()+"---"+result.getEquipo2().getNombre());
                if (result.getNroRonda() == i) {
                    partidoss.add(result);
                }
            }
            for (Pronostico preddic : pronosticos) {
                //System.out.println(preddic.getParticipante());
                if (preddic.getNroRonda() == i) {
                    prediccion.add(preddic);
                }
            }

            List<Participante> participantes = new ArrayList<Participante>();
            Participante mariana = new Participante("Mariana");
            Participante pedro = new Participante("Pedro");
            participantes.add(mariana);
            participantes.add(pedro);

            Ronda ronda = new Ronda(i, partidoss, prediccion, participantes);
            listaDeRonda.add(ronda);
        }
        return listaDeRonda;
    }

    private static List<Partido> leerArchivoResultados(String rutaPartido, List<Partido> partidos) {
        //Esta funcion lo que hace es leer el archivo CSV que contiene los resultados de los partidos y
        // va creando objetos de la clase Partido con sus respectivos equipos y goles de cada uno y la ronda a la que corresponde
        // por ultimo retorna una lista de estos partidos.
        BufferedReader lector;
        String linea;
        try {
            lector = new BufferedReader(new FileReader(rutaPartido));
            while ((linea = lector.readLine()) != null) {
                //Se crea un array de string por cada linea
                String[] lineaArray = linea.split(";");
                int nroRonda = Integer.parseInt(lineaArray[4]);
                //Se crean las variables y se le asignan los datos de cada posicion del array de strings
                Equipo equipo1 = new Equipo(lineaArray[0], "equipo1");
                Equipo equipo2 = new Equipo(lineaArray[3], "equipo2");
                int golesEquipo1 = Integer.parseInt(lineaArray[1]);
                int golesEquipo2 = Integer.parseInt(lineaArray[2]);

                //Se instancia un nuevo objeto de la clase partido con los parametros requeridos por su constructor
                Partido partido = new Partido(equipo1, equipo2, golesEquipo1, golesEquipo2, nroRonda);

                //Se agrega cada partido en el arreglo partidos
                partidos.add(partido);
            }

        } catch (Exception e) {
            System.out.println("Error al leer el archivo partidos");
        }
        return partidos;
    }

    private static List<Pronostico> cargarListaPronosticos(ResultSet tablaPronosticos, List<Pronostico> pronosticos, List<Partido> partidos, int puntosPorAcierto) throws SQLException {
        //System.out.println("\nconsulta\n");
        //Esta funcion toma el resultado obtenido de la consulta de la tabla pronosticos de la BD y va creando
        // pronosticos de a la informacion obtenida, tambien evalua los resultados de acuerdo al partido al que corresponde
        // y a lo pronosticado por el participante (esto de acuerdo a la posicion de la "x").
        // Retorna una lista de los pronosticos.
        while (tablaPronosticos.next()) {
            //Se define un a variable resultado
            ResultadoEnum resultado = null;
            //String[] lineaPronostico = linea.split(";");
            int rondaNro = tablaPronosticos.getInt(COLUMN_RONDA);
            int posPartido = tablaPronosticos.getInt(COLUMN_NRO_PARTIDO) - 1;

            //Para determinar ganador, perdedor o empate se toma como referencia siempre al equipo1

            //Si la "x" se encuentra en la columna "gana1" se le asigna "ganador" a la variable resultado
            if (tablaPronosticos.getString(COLUMN_GANA1).equals("x")) {
                resultado = ResultadoEnum.GANADOR;
            }
            //Si la "x" se encuentra en la columna "empate" se le asigna "empate" a la variable resultado
            else if (tablaPronosticos.getString(COLUMN_EMPATA).equals("x")) {
                resultado = ResultadoEnum.EMPATE;
            }
            //Si la "x" se encuentra en la la columna "gana2" se le asigna "perdedor" a la variable resultado
            else if (tablaPronosticos.getString(COLUMN_GANA2).equals("x")) {
                resultado = ResultadoEnum.PERDEDOR;
            } else {
                System.out.println("No se encontró un posible resultado del partido");
            }

            String participante = tablaPronosticos.getString(COLUMN_PARTICIPANTE);

            //Se instancia un nuevo pronostico con los parametros requeridos por su constructor
            Pronostico pronostico = new Pronostico(partidos.get(posPartido), partidos.get(posPartido).getEquipo1(), resultado, participante, rondaNro, puntosPorAcierto);

            //Se agrega cada pronostico en el arreglo pronosticos
            pronosticos.add(pronostico);
        }

        return pronosticos;
    }

    private static void insertPronostico(Statement statement, String equipo1, String gana1, String empate, String gana2, String equipo2, String participante, int nro_partido, int ronda) throws SQLException {
        //Esta funcion inserta registros en la tabla "pronosticos".

        statement.execute("INSERT INTO " + TABLE_PRONOSTICOS +
                " (" + COLUMN_EQUIPO1 + ", " +
                COLUMN_GANA1 + ", " +
                COLUMN_EMPATA + ", " +
                COLUMN_GANA2 + ", " +
                COLUMN_EQUIPO2 + ", " +
                COLUMN_PARTICIPANTE + ", " +
                COLUMN_NRO_PARTIDO + ", " +
                COLUMN_RONDA +
                " ) " +
                "VALUES('" + equipo1 + "', '" + gana1 + "', '" + empate + "', '" + gana2 + "', '" + equipo2 + "', '" + participante + "', " + nro_partido + ", " + ronda + ")");
    }

}