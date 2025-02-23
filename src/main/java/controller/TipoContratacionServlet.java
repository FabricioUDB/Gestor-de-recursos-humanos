package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.TipoContratacionDAO;
import model.TipoContratacion;

@WebServlet("/tipocontratacion")
public class TipoContratacionServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("list".equals(action)) {
            listTipoContrataciones(request, response);
        } else if ("insert".equals(action)) {
            insertTipoContratacion(request, response);
        } else if ("delete".equals(action)) {
            deleteTipoContratacion(request, response);
        }
    }

    private void listTipoContrataciones(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        TipoContratacionDAO tipoContratacionDAO = new TipoContratacionDAO();
        List<TipoContratacion> tipoContrataciones = tipoContratacionDAO.getAllTipoContrataciones();

        request.setAttribute("tiposContratacion", tipoContrataciones);
        request.getRequestDispatcher("tipoContratacion.jsp").forward(request, response);
    }

    private void insertTipoContratacion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tipoContratacion = request.getParameter("tipoContratacion");
        TipoContratacion nuevoTipo = new TipoContratacion();
        nuevoTipo.setTipoContratacion(tipoContratacion);

        TipoContratacionDAO tipoContratacionDAO = new TipoContratacionDAO();
        tipoContratacionDAO.insertarTipoContratacion(nuevoTipo);

        response.sendRedirect("tipocontratacion?action=list");
    }

    private void deleteTipoContratacion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idTipoContratacion = Integer.parseInt(request.getParameter("idTipoContratacion"));

        TipoContratacionDAO tipoContratacionDAO = new TipoContratacionDAO();
        tipoContratacionDAO.eliminarTipoContratacion(idTipoContratacion);

        response.sendRedirect("tipocontratacion?action=list");
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
