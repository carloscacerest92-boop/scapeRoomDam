package temploInca.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestorMySQL {

    private static final String URL = "jdbc:mysql://localhost:3306/escape_room_db";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "root";

    public void guardarResultado(String nombreJugador, int energiaSobrante) {
        String sql = "INSERT INTO ranking (nombre, energia_restante) VALUES (?, ?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(URL, USUARIO, PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, nombreJugador);
                pstmt.setInt(2, energiaSobrante);
                pstmt.executeUpdate();

                System.out.println("[BASE DE DATOS] Tu victoria ha sido registrada en el mural de los dioses.");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("[ERROR DRIVER] Java no encuentra el archivo .jar al ejecutar: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("[ERROR BD] No se pudo conectar con el mural: " + e.getMessage());
        }
    }

    // metodo que lee los datos para el ranking
    public List<String[]> obtenerRanking() {
        List<String[]> listaRanking = new ArrayList<>();
        String sql = "SELECT nombre, energia_restante, fecha FROM ranking ORDER BY energia_restante DESC LIMIT 10";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(URL, USUARIO, PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String energia = String.valueOf(rs.getInt("energia_restante"));
                    String fecha = rs.getString("fecha");

                    // Guardamos la fila como un array de textos
                    listaRanking.add(new String[]{nombre, energia, fecha});
                }
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("[ERROR BD] No se pudo obtener el ranking: " + e.getMessage());
        }

        return listaRanking;
    }
}