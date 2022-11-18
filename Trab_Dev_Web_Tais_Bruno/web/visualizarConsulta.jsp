<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="models.Paciente"%>
<%@page import="dao.PacienteDAO"%>
<%@page import="dao.MedicoDAO"%>
<%@page import="dao.ConsultaDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.Medico"%>
<%@page import="models.Consulta"%>
<%@page import="models.Especialidade"%>
<%@page import="dao.EspecialidadeDAO"%>
<%@page import="dao.ExameDAO"%>
<%@page import="dao.TipoExameDAO"%>
<%@page import="models.TipoExame"%>
<%@page import="models.Exames"%>

<%  
    Consulta consulta = (Consulta) request.getAttribute("consultasMedico");
%>

<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- BOOTSTRAP -->
        <link rel="stylesheet" href="bootstrap/bootstrap.min.css">
        <title> Visualização da Consulta </title>
    </head>
<body>        
    <jsp:include page = "components/menu.jsp" />
    <%@include file="components/ehMedico.jsp" %>
    
    <div class="rounded border border-dark p-4 m-5" style="padding: 10px; text-align: center">
        <h4 class="card-title" style="margin-top: 40px">Visualizar Consulta:</h4>
        <a class="nav-link" style="margin-bottom: 50px; text-decoration: underline; text-align: center" href="realizarConsulta.jsp">Voltar</a>
        <div class="form-group">
            <label for="nomemedico"> Nome do Médico </label>
            <label style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control"><%= medico.getNome()%></label>
        </div>
        <br> 
        <div class="form-group">
            <label for="nomepaciente"> Nome do Paciente </label>
            <label style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control"><%= consulta.getIdPaciente()%></label>
        </div>
        <br>
        <div class="form-group">
            <label for="data"> Data: </label>    
            <label style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control"><%= consulta.getData()%></label>
        </div>
        <br>
        <div class="form-group">
            <label for="descricao"> Descrição </label>
            <label style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control"><%= consulta.getDescricao()%></label>
        </div>
        <br>
        <div class="form-group">
            <label for="exame"><b> Exames </b></label>
            <label style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control"><%= consulta.getExames()%></label>
        </div>
        <br>
        <div class="form-group">
            <label for="realizada"><b> Consulta Realizada? </b></label>
            <label style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control"><%= consulta.getRealizada()%></label>
        </div>
    </div>
    <script src="bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>



