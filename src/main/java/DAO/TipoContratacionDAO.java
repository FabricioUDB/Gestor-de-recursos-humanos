package DAO;

import model.TipoContratacion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import databaserrhh.databaserrhh;

public class TipoContratacionDAO {

    // Obtener todos los tipos de contratación
    public List<TipoContratacion> getAllTipoContrataciones() {
        List<TipoContratacion> tipoContrataciones = new ArrayList<>();
        String query = "SELECT * FROM tipodecontrataciones";

        try (Connection conn = databaserrhh.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                TipoContratacion tipo = new TipoContratacion(
                        rs.getInt("idTipoContratacion"),
                        rs.getString("tipoContratacion")
                );
                tipoContrataciones.add(tipo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de la excepción lanzada por getConnection()
        }
        return tipoContrataciones;
    }

    // Insertar un nuevo tipo de contratación
    public boolean insertarTipoContratacion(TipoContratacion tipoContratacion) {
        String query = "INSERT INTO tipodecontrataciones (tipoContratacion) VALUES (?)";

        try (Connection conn = databaserrhh.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, tipoContratacion.getTipoContratacion());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de la excepción lanzada por getConnection()
        }
        return false;
    }

    // Eliminar un tipo de contratación
    public boolean eliminarTipoContratacion(int idTipoContratacion) {
        String query = "DELETE FROM tipodecontrataciones WHERE idTipoContratacion = ?";

        try (Connection conn = databaserrhh.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, idTipoContratacion);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de la excepción lanzada por getConnection()
        }
        return false;
    }
}