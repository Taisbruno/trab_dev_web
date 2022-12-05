<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="models.Administrador"%>
<%@page import="dao.AdministradorDAO"%>

<%  
    Administrador administradorrr = (Administrador) request.getAttribute("administrador");
%>

<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- BOOTSTRAP -->
        <link rel="stylesheet" href="bootstrap/bootstrap.min.css">
        <title> Alteração do Administrador </title>
    </head>
<body>        
    <jsp:include page = "components/menu.jsp" />
    <%@include file="components/ehAdministrador.jsp" %>
    
    <div class="rounded border border-dark p-4 m-5" style="padding: 10px; text-align: center">
        <h4 class="card-title" style="margin-top: 40px; margin-bottom: 50px;">Editar Administrador</h4>
        <a class="btn btn-outline-dark my-2 my-sm-0" style="margin-bottom: 50px; text-align: center" href="cadastraAdministradores.jsp"><b>Voltar</b></a>  
     <form method="POST" action="AdministradorController?action=update">   
        <input type="hidden" style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control" name="id" id="id" value="<%= administradorrr.getId() %>">
        <div class="form-group">
            <br>
            <br>
            <label for="nome"><b>Nome do Administrador</b></label>
            <input type="text" style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control" id="nome" name="nome" value="<%= administradorrr.getNome() %>" required>
            <br>
            <label for="cpf"><b>CPF</b></label>
            <input type="text" style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control" name="cpf" id="cpf" value="<%= administradorrr.getCpf() %>" required>
             <br>
             <label for="senha"><b>Senha</b></label>
            <input type="text" style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control" name="senha" id="senha" value="<%= administradorrr.getSenha() %>" required>
        </div>
        <br>
        <button type="submit" class="btn btn-dark" style="margin-top: 40px; margin-bottom: 40px"><i class="fas fa-save"></i> Enviar </button>
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