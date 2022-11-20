<%@page import="models.Administrador"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- BOOTSTRAP -->
        <link rel="stylesheet" href="bootstrap/bootstrap.min.css">
        <title>Área do Administrador</title>
    </head>
<body>        
    <jsp:include page = "components/menu.jsp" />
    <%@include file="components/ehAdministrador.jsp" %>
    
        <h2 style="text-align: center; margin-top: 80px;">Área do Administrador</h2>
        <a class="nav-link" href="LoginController?action=logout" style="text-decoration: underline; text-align: center">Logout</a>
    <script src="bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
