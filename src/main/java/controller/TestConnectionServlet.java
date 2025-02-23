package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import databaserrhh.databaserrhh;

@WebServlet("/testConnection")
public class TestConnectionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try (Connection conn = databaserrhh.getConnection()) {
            out.println("<h1>¡Conexión exitosa a la base de datos!</h1>");
        } catch (Exception e) {
            out.println("<h1>Error en la conexión: " + e.getMessage() + "</h1>");
        }
    }
}
