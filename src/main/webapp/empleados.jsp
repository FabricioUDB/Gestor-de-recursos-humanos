<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Empleados</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<!-- Botón para ir a la página cargos.jsp -->
<a href="/GestionEmpleados/cargos?action=list" class="btn btn-secondary">Cargos</a>
<a href="/GestionEmpleados/tipocontratacion?action=list" class="btn btn-secondary">tipocontratacion</a>


<div class="container">
    <h2>Lista de Empleados</h2>
    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>numerodui</th>
            <th>Nombre</th>
            <th>Usuario</th>
            <th>Teléfono</th>
            <th>Correo</th>
            <th>Fecha de Nacimiento</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="emp" items="${empleados}">
            <tr>
                <td>${emp.idEmpleado}</td>
                <td>${emp.numerodui}</td>
                <td>${emp.nombrePersona}</td>
                <td>${emp.usuario}</td>
                <td>${emp.numeroTelefono}</td>
                <td>${emp.correoInstitucional}</td>
                <td>${emp.fechaNacimiento}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div class="container">
    <h2>Lista de Cargos</h2>
    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Cargo</th>
            <th>Descripción</th>
            <th>Jefatura</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="cargo" items="${cargos}">
            <tr>
                <td>${cargo.idCargo}</td>
                <td>${cargo.cargo}</td>
                <td>${cargo.descripcionCargo}</td>
                <td>${cargo.jefatura ? 'Sí' : 'No'}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- Formulario para agregar un nuevo cargo -->
    <h3>Agregar Nuevo Cargo</h3>
    <form action="cargos" method="post">
        <input type="hidden" name="action" value="add">
        <div class="form-group">
            <label for="cargo">Cargo:</label>
            <input type="text" class="form-control" id="cargo" name="cargo" required>
        </div>
        <div class="form-group">
            <label for="descripcion">Descripción:</label>
            <input type="text" class="form-control" id="descripcion" name="descripcion" required>
        </div>
        <div class="form-group">
            <label for="jefatura">Jefatura:</label>
            <select class="form-control" id="jefatura" name="jefatura" required>
                <option value="true">Sí</option>
                <option value="false">No</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Agregar Cargo</button>
    </form>
</div>
</body>
</html>