package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Empleados;
import databaserrhh.databaserrhh;

@WebServlet("/empleados")
public class EmpleadosServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("list".equals(action)) {
            listEmpleados(request, response);
        } else if ("add".equals(action)) {
            addEmpleado(request, response);
        }
    }

    private void listEmpleados(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Empleados> empleados = new ArrayList<>();
        try (Connection conn = databaserrhh.getConnection()) {
            String sql = "SELECT * FROM Empleados";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Empleados emp = new Empleados();
                emp.setIdEmpleado(rs.getInt("idEmpleado"));
                emp.setnumerodui(rs.getString("numerodui"));
                emp.setNombrePersona(rs.getString("nombrePersona"));
                emp.setUsuario(rs.getString("usuario"));
                emp.setNumeroTelefono(rs.getString("numeroTelefono"));
                emp.setCorreoInstitucional(rs.getString("correoInstitucional"));
                emp.setFechaNacimiento(rs.getDate("fechaNacimiento"));
                empleados.add(emp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("empleados", empleados);
        request.getRequestDispatcher("empleados.jsp").forward(request, response);
    }

    private void addEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Implementar la lógica para agregar un empleado
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}