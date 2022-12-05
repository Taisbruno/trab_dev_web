<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="models.Especialidade"%>

<%  
    Especialidade especialidade = (Especialidade) request.getAttribute("especialidade");
%>

<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="bootstrap/bootstrap.min.css">
        
        <title> Editar Especialidade </title>
    </head>
<body>      
    <jsp:include page = "components/menu.jsp" />
    <%@include file="components/ehAdministrador.jsp" %>
    
    <div class="rounded border border-dark p-4 m-5" style="padding: 10px; text-align: center">
        <h4 class="card-title" style="margin-top: 40px">Editar Especialidade:</h4>
        <br>
        <a class="btn btn-outline-dark my-2 my-sm-0" style="margin-bottom: 50px; text-align: center" href="cadastraEspecialidades.jsp"><b>Voltar</b></a>  
    <br>
    <form method="POST" action="EspecialidadeController?action=update">
        <input type="hidden" style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control" name="id" id="id" value="<%= especialidade.getId() %>">
        <br>
        <br>
        <div class="form-group" style="margin-top:20px">
            <label for="especialidade"><b> Especialidade </b></label>
            <input type="text" style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control" id="descricao" name="descricao" value="<%= especialidade.getDescricao() %>" required>
        </div>
        <br>
        <button type="submit" class="btn btn-dark" style="margin-top: 20px; margin-bottom: 30px"><i class="fas fa-save"></i> Enviar </button>
    </form>
    </div>
    <div class="container">
        <%                       
                if (request.getAttribute("message") != null) {
        %>
                    <div class="alert alert-danger m-5" role="alert">
                        <%= (String) request.getAttribute("message") %>
                    </div>
        <%
                }
        %>
    </div>
    <script src="bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>
