<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Inicio - Gestión de Empleados y Cargos</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1 class="mt-5">Bienvenido al Sistema de Gestión</h1>
    <p class="lead">Seleccione una opción para continuar:</p>

    <div class="list-group">
        <!-- Enlace a la lista de cargos -->
        <a href="cargos?action=list" class="list-group-item list-group-item-action">
            <h5 class="mb-1">Ver Lista de Cargos</h5>
            <p class="mb-1">Administre los cargos disponibles en la empresa.</p>
        </a>

        <!-- Enlace a la lista de empleados -->
        <a href="empleados?action=list" class="list-group-item list-group-item-action">
            <h5 class="mb-1">Ver Lista de Empleados</h5>
            <p class="mb-1">Administre los empleados registrados en la empresa.</p>
        </a>
    </div>
</div>
</body>
</html>