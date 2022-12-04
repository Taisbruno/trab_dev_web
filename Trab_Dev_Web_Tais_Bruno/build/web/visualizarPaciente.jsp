<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="models.Paciente"%>
<%@page import="dao.PacienteDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.TipoPlanoDAO"%>
<%@page import="models.TipoPlano"%>

<%  
    TipoPlanoDAO tipoplanoDAO = new TipoPlanoDAO();
    
    Paciente paciente = (Paciente) request.getAttribute("paciente");
    PacienteDAO pacienteDAO = new PacienteDAO();
%>

<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- BOOTSTRAP -->
        <link rel="stylesheet" href="bootstrap/bootstrap.min.css">
        <title> Visualização do Paciente </title>
    </head>
<body>        
    <jsp:include page = "components/menu.jsp" />
    <%@include file="components/ehAdministrador.jsp" %>
    
    <div class="rounded border border-dark p-4 m-5" style="padding: 10px; text-align: center">
        <h4 class="card-title" style="margin-top: 40px; margin-bottom: 40px">Visualizar Paciente</h4>
        <a class="btn btn-outline-dark my-2 my-sm-0" style="margin-bottom: 50px; text-align: center" href="cadastraPacientes.jsp"><b>Voltar</b></a>  
        <div class="form-group">
            <br>
            <br>
            <label for="nomepaciente"><b> Nome do Paciente</b> </label>
            <label style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control"><%= paciente.getNome()%></label>
        </div>
        <br> 
        <div class="form-group">
        <label for="cpf"><b> CPF </b> </label>
        <label style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control"><%= paciente.getCpf()%></label>
        </div>
        <br>
        <div class="form-group">
            <label for="senha"><b> Senha </b></label>
            <label style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control"><%= paciente.getSenha() %></label>
        </div>
        <br>
        <div class="form-group">
            <label for="autorizado"><b> Autorizado </b></label>    
            <label style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control"><%= paciente.getAutorizado() %></label>
        </div>
        <br>
        <div class="form-group">
            <label for="tipoplano"><b> Tipo de Plano </b></label>
            <label style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control"><%= tipoplanoDAO.get(paciente.getIdTipoPlano()).getDescricao() %></label>
        </div>
        <br>
    </div>
    <script src="bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>
