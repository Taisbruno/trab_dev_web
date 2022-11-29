<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- BOOTSTRAP -->
        <link rel="stylesheet" href="bootstrap/bootstrap.min.css">

    <title> Cadastro - Paciente </title>
    </head>

<body>        
    <jsp:include page = "components/menu.jsp" />
    <%@include file="components/ehAdministrador.jsp" %>
    
    <div class="rounded border border-dark p-4 m-5" style="padding: 10px; text-align: center">
    <h4 class="card-title" style="margin-top: 40px; margin-bottom: 20px">Cadastro - Paciente:</h4>  
    <br>
    <a class="btn btn-outline-dark my-2 my-sm-0" style="margin-bottom: 60px; text-align: center" href="cadastraPacientes.jsp"><b>Voltar</b></a>
    <form method="POST" action="PacienteController">
        <input style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" type="hidden" class="form-control" name="idpaciente" id="idpaciente" >
        
        <div class="form-group">
            <label for="nome"><b> Nome </b></label>
            <input style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" type="text" class="form-control" name="nome" id="nome" autocomplete="off" placeholder="Digite o nome" required>
        </div>
        <br>
        <!-- CPF -->
        <div class="form-group">
            <label for="cpf"><b> CPF </b></label>
            <input style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" type="text" class="form-control cpf" name="cpf" id="cpf" autocomplete="off" placeholder="Digite o CPF" required>
        </div>
        <br>
        <!-- SENHA -->
        <div class="form-group">
            <label for="senha"><b> Senha </b></label>
            <input style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" type="password" class="form-control" name="senha" id="senha" autocomplete="off" placeholder="Digite a senha" required>
        </div>
        <br>
        <div class="form-group">
            <label for="idtipoplano"><b> Plano </b></label>
            <select class="form-select" id="idtipoplano" style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" name="idtipoplano" required>
            <option value="" disabled selected hidden></option>
            <option value=1>Unimed</option>
            <option value=2>Amil</option>
            <option value=3>SulAmerica</option>
            <option value=4>Particular</option>
            </select>
        </div>
        <br>
        <div class="form-group">
        <label for="autorizado"><b> Autorizado? </b></label>
        <select style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-select" id="autorizado"  name="autorizado" required>
        <option value="S"> Sim </option>    
        <option value="N"> Não </option>
        </select>
        </div>
        <button type="submit" class="btn btn-dark" style="margin-top: 30px; margin-bottom: 30px"><i class="fas fa-save"></i> Salvar </button>
    </form>
    </div>
    <script src="bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>