package DAO;




import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Cargos;
import databaserrhh.databaserrhh;

public class CargoDAO {

    // Método para listar todos los cargos
    public List<Cargos> getAllCargos() {
        List<Cargos> cargos = new ArrayList<>();
        String sql = "SELECT * FROM cargos";

        try (Connection conn = databaserrhh.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cargos cargo = new Cargos();
                cargo.setIdCargo(rs.getInt("idcargo"));
                cargo.setCargo(rs.getString("cargo"));
                cargo.setDescripcionCargo(rs.getString("descripcioncargo"));
                cargo.setJefatura(rs.getBoolean("jefatura"));
                cargos.add(cargo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cargos;
    }

    // Método para agregar un nuevo cargo
    public boolean addCargo(Cargos cargo) {
        String sql = "INSERT INTO cargos (cargo, descripcioncargo, jefatura) VALUES (?, ?, ?)";

        try (Connection conn = databaserrhh.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cargo.getCargo());
            stmt.setString(2, cargo.getDescripcionCargo());
            stmt.setBoolean(3, cargo.isJefatura());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para actualizar un cargo existente
    public boolean updateCargo(Cargos cargo) {
        String sql = "UPDATE cargos SET cargo = ?, descripcioncargo = ?, jefatura = ? WHERE idcargo = ?";

        try (Connection conn = databaserrhh.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cargo.getCargo());
            stmt.setString(2, cargo.getDescripcionCargo());
            stmt.setBoolean(3, cargo.isJefatura());
            stmt.setInt(4, cargo.getIdCargo());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para eliminar un cargo
    public boolean deleteCargo(int idCargo) {
        String sql = "DELETE FROM cargos WHERE idcargo = ?";

        try (Connection conn = databaserrhh.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCargo);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener un cargo por su ID
    public Cargos getCargoById(int idCargo) {
        Cargos cargo = null;
        String sql = "SELECT * FROM cargos WHERE idcargo = ?";

        try (Connection conn = databaserrhh.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCargo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cargo = new Cargos();
                cargo.setIdCargo(rs.getInt("idcargo"));
                cargo.setCargo(rs.getString("cargo"));
                cargo.setDescripcionCargo(rs.getString("descripcioncargo"));
                cargo.setJefatura(rs.getBoolean("jefatura"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cargo;
    }
}
