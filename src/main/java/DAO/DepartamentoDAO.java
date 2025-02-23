package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Departamento;
import databaserrhh.databaserrhh;

public class DepartamentoDAO {

    // Método para agregar un departamento
    public void agregarDepartamento(Departamento departamento) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = databaserrhh.getConnection();
            String sql = "INSERT INTO Departamento (nombre, descripcion) VALUES (?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, departamento.getNombre());
            stmt.setString(2, departamento.getDescripcion());
            stmt.executeUpdate();
        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }

    // Método para obtener todos los departamentos
    public List<Departamento> obtenerDepartamentos() throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Departamento> departamentos = new ArrayList<>();

        try {
            conn = databaserrhh.getConnection();
            String sql = "SELECT nombre, descripcion FROM Departamento";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Departamento dept = new Departamento();
                dept.setNombre(rs.getString("nombre"));
                dept.setDescripcion(rs.getString("descripcion"));
                departamentos.add(dept);
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }

        return departamentos;
    }
}
