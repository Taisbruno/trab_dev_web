<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- BOOTSTRAP -->
        <link rel="stylesheet" href="bootstrap/bootstrap.min.css">

    <title> Login </title>
    </head>

<body>        
    <jsp:include page = "components/menu.jsp" />
    
    <div class="rounded border border-dark p-4 m-5" style="padding: 10px; text-align: center">
    <h3 class="card-title" style="margin-top: 40px">Login</h3>
    <form method="POST" action="LoginController" style="text-align: center; margin-top: 30px">                   
        <input style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" type="hidden" class="form-control" name="id" id="id">
        
        <!-- CPF -->
        <div class="form-group">
            <label for="cpf" style="margin-top: 5px"><b> CPF </b></label>
            <input style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" type="text" style="margin-top: 5px" class="form-control cpf" name="cpf" id="cpf" autocomplete="off" placeholder="Digite o CPF" required>
        </div>
        <br>
        <!-- SENHA -->
        <div class="form-group">
            <label for="senha" style="margin-top: 10px"><b> Senha </b></label>
            <input style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" type="password" style="margin-top: 10px" class="form-control" name="senha" id="senha" autocomplete="off" placeholder="Digite a senha" required>
        </div>
        <br>
        <!-- BOTÃO -->
        <button type="submit" class="btn btn-dark" style="margin-top: 30px; margin-bottom: 40px"><i class="fas fa-save"></i> Enviar </button>
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