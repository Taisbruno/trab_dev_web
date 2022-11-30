<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="models.TipoPlano"%>
<%@page import="java.util.ArrayList"%>

<%  
    TipoPlano tipoplano = (TipoPlano) request.getAttribute("tipoplano");
%>

<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- BOOTSTRAP -->
        <link rel="stylesheet" href="bootstrap/bootstrap.min.css">
        <title> Visualização do Tipo do Plano </title>
    </head>
<body>        
    <jsp:include page = "components/menu.jsp" />
    <%@include file="components/ehAdministrador.jsp" %>
    
    <div class="rounded border border-dark p-4 m-5" style="padding: 10px; text-align: center">
        <h4 class="card-title" style="margin-top: 40px; margin-bottom: 40px">Visualizar Tipo do Plano</h4>
        <a class="btn btn-outline-dark my-2 my-sm-0" style="margin-bottom: 50px; text-align: center" href="cadastraTipoPlano.jsp"><b>Voltar</b></a>  
        <div class="form-group">
            <br>
            <br>
            <label for="descrição"><b> Descrição </b> </label>
            <label style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control"><%= tipoplano.getDescricao()%></label>
        </div>
        <br> 
    </div>
    <script src="bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>
