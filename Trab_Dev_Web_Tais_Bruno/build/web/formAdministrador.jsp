<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- BOOTSTRAP -->
        <link rel="stylesheet" href="bootstrap/bootstrap.min.css">
        <title> Cadastro - Administrador </title>
    </head>

<body>
    <jsp:include page = "components/menu.jsp" />
    
    <div class="rounded border border-dark p-4 m-5" style="padding: 10px; text-align: center; justify-content: center">
    <h4 class="card-title" style="margin-top: 40px; margin-bottom: 20px">Cadastro - Administrador:</h4>
    <br>
    <form method="POST" action="AdministradorController">                   
        
        <div class="form-group">
            <label for="nome"><b> Nome </b></label>
            <input style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" type="text" class="form-control" name="nome" id="nome" autocomplete="off" placeholder="Digite o nome" required>
        </div>
        <br>
        <div class="form-group">
            <label for="cpf"><b> CPF </b></label>
            <input style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" type="text" class="form-control cpf" name="cpf" id="cpf" autocomplete="off" placeholder="Digite o CPF" required>
        </div>
        <br>
        <div class="form-group">
            <label for="senha"><b> Senha </b></label>
            <input style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" type="password" class="form-control" name="senha" id="senha" autocomplete="off" placeholder="Digite a senha" required>
        </div>
        <br>
        <!-- BOTÃO -->
        <button type="submit" class="btn btn-dark" style="margin-top: 30px; margin-bottom: 30px"><i class="fas fa-save"></i> Salvar </button>
    </form>
    </div>
    <script src="bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>