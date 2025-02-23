<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Departamento, DAO.DepartamentoDAO, java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Agregar y Listar Departamentos</title>
</head>
<body>
<h1>Agregar y Listar Departamentos</h1>

<!-- Mensaje de éxito o error -->
<% if (request.getAttribute("mensaje") != null) { %>
<p style='color:<%= request.getAttribute("color") != null ? request.getAttribute("color") : "black" %>;'>
    <%= request.getAttribute("mensaje") %>
</p>
<% } %>

<!-- Formulario para agregar un nuevo departamento -->
<h2>Agregar Departamento</h2>
<form action="agregarDepartamento" method="post">
    Nombre: <input type="text" name="nombre" required><br><br>
    Descripción: <textarea name="descripcion" required></textarea><br><br>
    <input type="submit" value="Agregar">
</form>

<!-- Lista de departamentos -->
<h2>Lista de Departamentos</h2>
<table border="1">
    <tr>
        <th>Nombre</th>
        <th>Descripción</th>
    </tr>
    <%
        DepartamentoDAO dao = new DepartamentoDAO();
        List<Departamento> departamentos = dao.obtenerDepartamentos();
        for (Departamento dept : departamentos) {
    %>
    <tr>
        <td><%= dept.getNombre() %></td>
        <td><%= dept.getDescripcion() %></td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>