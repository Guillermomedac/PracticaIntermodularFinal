
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Conectorbd {

    private static final String URL = "jdbc:mysql://localhost:3306/practica_intermodular";
    private static final String USUARIO = "root";
    private static final String CONTRASEÑA = "";

    // Método para obtener la conexión a la base de datos
    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
    }

    // Método para obtener el nombre de un planeta por su ID
    public static String getNombrePlaneta(int id) throws SQLException {
        String nombre = "";
        String query = "SELECT nombre FROM Planeta WHERE id = " + id;

        try (Connection conexion = obtenerConexion(); Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                nombre = rs.getString("nombre");
            }
        }

        return nombre;
    }

    public static double getRadioPlaneta(int id) throws SQLException {
        double radio = 0.0;
        String query = "SELECT radio FROM Planeta WHERE id = " + id;

        try (Connection conexion = obtenerConexion(); Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                radio = rs.getDouble("radio");
            }
        }

        return radio;
    }

    public static double getdistancia_solPlaneta(int id) throws SQLException {
        double distancia_sol = 0.0;
        String query = "SELECT distancia_sol FROM Planeta WHERE id = " + id;

        try (Connection conexion = obtenerConexion(); Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                distancia_sol = rs.getDouble("distancia_sol");
            }
        }

        return distancia_sol;
    }

    public static double getperiodo_orbitaPlaneta(int id) throws SQLException {
        double periodo_orbita = 0.0;
        String query = "SELECT periodo_orbita FROM Planeta WHERE id = " + id;

        try (Connection conexion = obtenerConexion(); Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                periodo_orbita = rs.getDouble("periodo_orbita");
            }
        }

        return periodo_orbita;
    }

    public static int getTemperaturaPlaneta(int id) throws SQLException {
        int temperatura = 0;
        String query = "SELECT temperatura FROM Planeta WHERE id = " + id;

        try (Connection conexion = obtenerConexion(); Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                temperatura = rs.getInt("temperatura");
            }
        }

        return temperatura;
    }

    public static String gettipo_planeta(int id) throws SQLException {
        String tipo_planeta = "";
        String query = "SELECT tipo_planeta FROM Planeta WHERE id = " + id;

        try (Connection conexion = obtenerConexion(); Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                tipo_planeta = rs.getString("tipo_planeta");
            }
        }

        return tipo_planeta;
    }

    public static int getnumero_satelites(int id) throws SQLException {
        int numero_satelites = 0;
        String query = "SELECT numero_satelites FROM Planeta WHERE id = " + id;

        try (Connection conexion = obtenerConexion(); Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                numero_satelites = rs.getInt("numero_satelites");
            }
        }

        return numero_satelites;
    }

    public static String getfecha_creacion(int id) throws SQLException {
        String fecha_creacion = "";
        String query = "SELECT fecha_creacion FROM Planeta WHERE id = " + id;

        try (Connection conexion = obtenerConexion(); Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                fecha_creacion = rs.getString("fecha_creacion");
            }
        }

        return fecha_creacion;
    }

    /*----------------------------------------------------------------------------------------------------*/
    public static String[] getSatelitesPorPlaneta(String nombrePlaneta) throws SQLException {
        List<String> satelites = new ArrayList<>();
        String query = "SELECT Satelite.nombre "
                + "FROM Satelite "
                + "JOIN Planeta ON Satelite.planeta = Planeta.nombre "
                + "WHERE Planeta.nombre = '" + nombrePlaneta + "'";

        try (Connection conexion = obtenerConexion(); Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                satelites.add(rs.getString("nombre"));
            }
        }

        // Verificar si la lista de satélites está vacía
        if (satelites.isEmpty()) {
            // Si está vacía, imprimir un mensaje y devolver un arreglo vacío
            System.out.println("No se encontraron satélites para el planeta " + nombrePlaneta);
            return new String[0];
        } else {
            // Si no está vacía, imprimir el primer satélite (para depuración) y devolver la lista como arreglo
            System.out.println("Primer satélite encontrado: " + satelites.get(0));
            return satelites.toArray(new String[satelites.size()]);
        }
    }
    public static String getPlanetaSatelite(String nombreSatelite) throws SQLException {
        String nombrePlaneta = null;
        String query = "SELECT planeta FROM Satelite WHERE nombre = '" + nombreSatelite + "'";

        try (Connection conexion = obtenerConexion();
             Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                nombrePlaneta = rs.getString("planeta");
            }
        }

        return nombrePlaneta;
    }
    public static double getsateliteradio(String nombreSatelite) throws SQLException {
        double radio = 0;
        String query = "SELECT radio FROM Satelite WHERE nombre = '" + nombreSatelite + "'";

        try (Connection conexion = obtenerConexion();
             Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                radio = rs.getDouble("radio");
            }
        }

        return radio;
    }
    public static double getSateliteDistanciaPlaneta(String nombreSatelite) throws SQLException {
    double distanciaPlaneta = 0;
    String query = "SELECT distancia_planeta FROM Satelite WHERE nombre = '" + nombreSatelite + "'";

    try (Connection conexion = obtenerConexion();
         Statement stmt = conexion.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {
        if (rs.next()) {
            distanciaPlaneta = rs.getDouble("distancia_planeta");
        }
    }

    return distanciaPlaneta;
}

public static double getSatelitePeriodoOrbital(String nombreSatelite) throws SQLException {
    double periodoOrbital = 0;
    String query = "SELECT periodo_orbital FROM Satelite WHERE nombre = '" + nombreSatelite + "'";

    try (Connection conexion = obtenerConexion();
         Statement stmt = conexion.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {
        if (rs.next()) {
            periodoOrbital = rs.getDouble("periodo_orbital");
        }
    }

    return periodoOrbital;
}

public static int getSateliteTemperatura(String nombreSatelite) throws SQLException {
    int temperatura = 0;
    String query = "SELECT temperatura FROM Satelite WHERE nombre = '" + nombreSatelite + "'";

    try (Connection conexion = obtenerConexion();
         Statement stmt = conexion.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {
        if (rs.next()) {
            temperatura = rs.getInt("temperatura");
        }
    }

    return temperatura;
}

public static String getSateliteTipo(String nombreSatelite) throws SQLException {
    String tipo = "";
    String query = "SELECT tipo FROM Satelite WHERE nombre = '" + nombreSatelite + "'";

    try (Connection conexion = obtenerConexion();
         Statement stmt = conexion.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {
        if (rs.next()) {
            tipo = rs.getString("tipo");
        }
    }

    return tipo;
}

public static String getSateliteFechaCreacion(String nombreSatelite) throws SQLException {
    String fechaCreacion = "";
    String query = "SELECT fecha_creacion FROM Satelite WHERE nombre = '" + nombreSatelite + "'";

    try (Connection conexion = obtenerConexion();
         Statement stmt = conexion.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {
        if (rs.next()) {
            fechaCreacion = rs.getString("fecha_creacion");
        }
    }

    return fechaCreacion;
}

public static int getsateliteid(String nombreSatelite) throws SQLException {
    int id = 0;
    String query = "SELECT id FROM Satelite WHERE nombre = '" + nombreSatelite + "'";

    try (Connection conexion = obtenerConexion();
         Statement stmt = conexion.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {
        if (rs.next()) {
            id = rs.getInt("id");
        }
    }

    return id;
}

}
