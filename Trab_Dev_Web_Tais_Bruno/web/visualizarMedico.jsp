<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="models.Medico"%>
<%@page import="dao.MedicoDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.EspecialidadeDAO"%>
<%@page import="models.Especialidade"%>

<%  
    EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
    
    Medico medico = (Medico) request.getAttribute("medico");
    MedicoDAO medicoDAO = new MedicoDAO();
%>

<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- BOOTSTRAP -->
        <link rel="stylesheet" href="bootstrap/bootstrap.min.css">
        <title> Visualização do Médico </title>
    </head>
<body>        
    <jsp:include page = "components/menu.jsp" />
    <%@include file="components/ehAdministrador.jsp" %>
    
    <div class="rounded border border-dark p-4 m-5" style="padding: 10px; text-align: center">
        <h4 class="card-title" style="margin-top: 40px; margin-bottom: 40px">Visualizar Médico</h4>
        <a class="btn btn-outline-dark my-2 my-sm-0" style="margin-bottom: 50px; text-align: center" href="cadastraMedicos.jsp"><b>Voltar</b></a>  
        <div class="form-group">
            <br>
            <br>
            <label for="nome"><b> Nome do Médico</b> </label>
            <label style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control"><%= medico.getNome()%></label>
        </div>
        <br> 
        <div class="form-group">
        <label for="crm"><b> CRM </b> </label>
        <label style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control"><%= medico.getCrm()%></label>
        </div>
        <br>
        <div class="form-group">
        <label for="cpf"><b> Estado do CRM </b> </label>
        <label style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control"><%= medico.getEstadoCrm()%></label>
        </div>
        <br>
        <div class="form-group">
        <label for="cpf"><b> CPF </b></label>
        <label style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control"><%= medico.getCpf()%></label>
        </div>
        <br>
        <div class="form-group">
            <label for="senha"><b> Senha </b></label>
            <label style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control"><%= medico.getSenha() %></label>
        </div>
        <br>
        <div class="form-group">
            <label for="autorizado"><b> Autorizado </b></label>    
            <label style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control"><%= medico.getAutorizado() %></label>
        </div>
        <br>
        <div class="form-group">
            <label for="especialidade"><b> Especialidade </b></label>
            <label style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control"><%= especialidadeDAO.get(medico.getIdEspecialidade()).getDescricao() %></label>
        </div>
        <br>
        
    </div>
    <script src="bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>

