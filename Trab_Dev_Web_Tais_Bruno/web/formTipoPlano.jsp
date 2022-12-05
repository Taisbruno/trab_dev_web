<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- BOOTSTRAP -->
        <link rel="stylesheet" href="bootstrap/bootstrap.min.css">
        
        <title> Cadastrar Tipo do Plano </title>
    </head>
<body>      
    <jsp:include page = "components/menu.jsp" />
    <%@include file="components/ehAdministrador.jsp" %>
    
    <div class="rounded border border-dark p-4 m-5" style="padding: 10px; text-align: center">
    <h4 class="card-title" style="margin-top: 40px">Cadastrar Tipo do Plano:</h4>
    <br>
    <a class="btn btn-outline-dark my-2 my-sm-0" style="margin-bottom: 60px; text-align: center" href="cadastraTipoPlano.jsp"><b>Voltar</b></a>
    <form method="POST" action="TipoPlanoController?action=insert">                   
        <br>
        <br>
        <div class="form-group" style="margin-top:20px">
            <label for="tipoplano"><b> Tipo do Plano</b> </label>
            <input style="margin-top: 5px; width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" type="text" class="form-control" name="descricao" id="descricao" placeholder="Digite o nome do tipo do plano" required>
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
