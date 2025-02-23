<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="DAO.TipoContratacionDAO, model.TipoContratacion, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestión de Tipos de Contratación</title>
    <style>
        table {
            width: 50%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
    </style>
</head>
<body>
<h1>Gestión de Tipos de Contratación</h1>

<!-- Formulario para agregar un nuevo tipo de contratación -->
<h2>Agregar Tipo de Contratación</h2>
<form action="tipoContratacion.jsp" method="post">
    Tipo de Contratación: <input type="text" name="tipoContratacion" required>
    <input type="submit" value="Agregar">
</form>

<%
    // Lógica para agregar un nuevo tipo de contratación
    if ("POST".equalsIgnoreCase(request.getMethod())) {
        String tipoContratacion = request.getParameter("tipoContratacion");

        if (tipoContratacion != null && !tipoContratacion.isEmpty()) {
            TipoContratacion nuevoTipo = new TipoContratacion();
            nuevoTipo.setTipoContratacion(tipoContratacion);

            TipoContratacionDAO dao = new TipoContratacionDAO();
            try {
                boolean success = dao.insertarTipoContratacion(nuevoTipo);
%>
<p style="color:<%= success ? "green" : "red" %>;">
    <%= success ? "Tipo de contratación agregado con éxito!" : "Error al agregar el tipo de contratación." %>
</p>
<%
} catch (Exception e) {
%>
<p style="color:red;">Error: <%= e.getMessage() %></p>
<%
            }
        }
    }
%>

<!-- Lista de tipos de contratación -->
<h2>Lista de Tipos de Contratación</h2>
<table>
    <tr>
        <th>ID</th>
        <th>Tipo de Contratación</th>
        <th>Acciones</th>
    </tr>
    <%
        TipoContratacionDAO dao = new TipoContratacionDAO();
        try {
            List<TipoContratacion> tipos = dao.getAllTipoContrataciones();
            for (TipoContratacion tipo : tipos) {
    %>
    <tr>
        <td><%= tipo.getIdTipoContratacion() %></td>
        <td><%= tipo.getTipoContratacion() %></td>
        <td>
            <form action="eliminarTipoContratacion.jsp" method="post" style="display:inline;">
                <input type="hidden" name="idTipoContratacion" value="<%= tipo.getIdTipoContratacion() %>">
                <input type="submit" value="Eliminar">
            </form>
        </td>
    </tr>
    <%
        }
    } catch (Exception e) {
    %>
    <p style="color:red;">Error al cargar la lista: <%= e.getMessage() %></p>
    <%
        }
    %>
</table>
</body>
</html>
