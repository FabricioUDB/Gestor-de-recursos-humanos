package controller;



import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Departamento;
import DAO.DepartamentoDAO;

@WebServlet("/Departamento")
public class DepartamentoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Maneja solicitudes POST
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");

        Departamento departamento = new Departamento();
        departamento.setNombre(nombre);
        departamento.setDescripcion(descripcion);

        DepartamentoDAO dao = new DepartamentoDAO();
        try {
            dao.agregarDepartamento(departamento);
            request.setAttribute("mensaje", "Departamento agregado con éxito!");
        } catch (Exception e) {
            request.setAttribute("mensaje", "Error: " + e.getMessage());
        }

        // Redirige de vuelta al JSP
        request.getRequestDispatcher("departamento.jsp").forward(request, response);
    }

    // Maneja solicitudes GET
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirige al formulario JSP
        request.getRequestDispatcher("departamento.jsp").forward(request, response);
    }
}