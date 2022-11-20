<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- BOOTSTRAP -->
        <link rel="stylesheet" href="bootstrap/bootstrap.min.css">
        <title>Área do Paciente</title>
    </head>
<body>        
    <jsp:include page = "components/menu.jsp" />
    <%@include file="components/ehPaciente.jsp" %>
    
        <h2 style="text-align: center; margin-top: 80px;">Área do Paciente</h2>
        <a class="nav-link" href="LoginController?action=logout" style="text-decoration: underline; text-align: center">Logout</a>
    <br>    
    <div class="rounded border border-dark p-4 m-5" style="padding: 10px; text-align: center">
    <br>
    <a class="nav-link" href="formConsulta.jsp" style="text-decoration: underline"><b>Marcar Consulta</b></a>
    <br>
    <a class="nav-link" href="listaConsultasPaciente.jsp" style="text-decoration: underline"><b>Lista de Consultas</b></a>
    <br>
    </div>
    <script src="bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>