package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.CargoDAO;
import model.Cargos;

@WebServlet("/cargos")
public class CargosServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("list".equals(action)) {
            listCargos(request, response);
        }
    }

    private void listCargos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CargoDAO cargoDAO = new CargoDAO();
        List<Cargos> cargos = cargoDAO.getAllCargos();

        request.setAttribute("cargos", cargos);
        request.getRequestDispatcher("empleados.jsp").forward(request, response);
    }

    private void addCargo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener los datos del formulario
        String cargo = request.getParameter("cargo");
        String descripcion = request.getParameter("descripcion");
        boolean jefatura = Boolean.parseBoolean(request.getParameter("jefatura"));

        // Crear un objeto Cargos con los datos
        Cargos nuevoCargo = new Cargos();
        nuevoCargo.setCargo(cargo);
        nuevoCargo.setDescripcionCargo(descripcion);
        nuevoCargo.setJefatura(jefatura);

        // Guardar el cargo en la base de datos
        CargoDAO cargoDAO = new CargoDAO();
        boolean resultado = cargoDAO.addCargo(nuevoCargo);

        // Redirigir a la lista de cargos después de agregar
        if (resultado) {
            response.sendRedirect("cargos?action=list");
        } else {
            request.setAttribute("error", "No se pudo agregar el cargo");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
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
