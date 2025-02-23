<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="DAO.TipoContratacionDAO" %>
<%
    // Obtener el ID del tipo de contratación a eliminar
    String idParam = request.getParameter("idTipoContratacion");
    if (idParam != null && !idParam.isEmpty()) {
        int idTipoContratacion = Integer.parseInt(idParam);

        // Eliminar el tipo de contratación
        TipoContratacionDAO dao = new TipoContratacionDAO();
        try {
            boolean success = dao.eliminarTipoContratacion(idTipoContratacion);
            if (success) {
                response.sendRedirect("tipoContratacion.jsp?mensaje=Tipo+de+contratacion+eliminado+con+exito");
            } else {
                response.sendRedirect("tipoContratacion.jsp?mensaje=Error+al+eliminar+el+tipo+de+contratacion");
            }
        } catch (Exception e) {
            response.sendRedirect("tipoContratacion.jsp?mensaje=Error: " + e.getMessage());
        }
    } else {
        response.sendRedirect("tipoContratacion.jsp?mensaje=ID+no+valido");
    }
%>