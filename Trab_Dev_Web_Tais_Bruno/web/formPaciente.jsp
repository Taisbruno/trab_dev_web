<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.ArrayList"%>
<%@page import="models.TipoPlano"%>
<%@page import="dao.TipoPlanoDAO"%>

<%  
    TipoPlanoDAO tipoplanoDAO = new TipoPlanoDAO();
    ArrayList<TipoPlano> tipoplanos = tipoplanoDAO.getAll();
%>

<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- BOOTSTRAP -->
        <link rel="stylesheet" href="bootstrap/bootstrap.min.css">

    <title> Login - Paciente </title>
    </head>

<body>        
    <jsp:include page = "components/menu.jsp" />
    
    <div class="rounded border border-dark p-5 m-5" style="padding: 10px; text-align: center">
    <a class="btn btn-outline-dark my-2 my-sm-0" style="margin-bottom: 60px; text-align: center" href="index.jsp"><b>Voltar</b></a>
    <h4 class="card-title" style="margin-top: 50px">Já possui cadastro?</h4>  
    <br>
        <a class="nav-link" href="login.jsp" style="text-decoration: underline">Login</a>
    <br>
    <h4 class="card-title" style="margin-top: 40px; margin-bottom: 20px">Cadastro - Paciente:</h4>  
    <br>

    <form method="POST" action="PacienteController?action=insert">
        <input style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" type="hidden" class="form-control" name="idpaciente" id="idpaciente" >
        
        <div class="form-group">
            <label for="nome"><b> Nome </b></label>
            <input style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" type="text" class="form-control" name="nome" id="nome" autocomplete="off" placeholder="Digite o nome" required>
        </div>
        <br>
        <!-- CPF -->
        <div class="form-group">
            <label for="cpf"><b> CPF </b></label>
            <input style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" type="text" class="form-control" name="cpf" id="cpf" autocomplete="off" placeholder="Digite o CPF" required>
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
            <%
                for (int index = 0; index < tipoplanos.size(); index++) {
                    TipoPlano tipoplano = tipoplanos.get(index);           
            %>
            <option value="<%= tipoplano.getId() %>"><%= tipoplano.getDescricao() %></option>
            <%
                }
            %>
            </select>
        </div>
        <br>
        <div class="form-group">
        <input style="vertical-align: -2px; margin-left: 2px" type="hidden" class="form-control" name="autorizado" value="S" id="autorizado" >
        </div>
        
        <button type="submit" class="btn btn-dark" style="margin-top: 30px; margin-bottom: 30px"><i class="fas fa-save"></i> Salvar </button>
    </form>
    </div>
    <script src="bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>
