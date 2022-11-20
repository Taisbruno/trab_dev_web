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
    PacienteDAO pacienteDAO = new PacienteDAO();
    ExameDAO exameDAO = new ExameDAO();
    ArrayList<Exames> exames = exameDAO.getByConsulta(consulta.getId());
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
        <h4 class="card-title" style="margin-top: 40px; margin-bottom: 40px">Visualizar Consulta:</h4>
        <a class="btn btn-outline-dark my-2 my-sm-0" style="margin-bottom: 50px; text-align: center" href="realizarConsulta.jsp"><b>Voltar</b></a>  
        <div class="form-group">
            <br>
            <br>
            <label for="nomemedico"><b> Nome do Médico</b> </label>
            <label style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control"><%= medico.getNome()%></label>
        </div>
        <br> 
        <div class="form-group">
        <label for="especialidade"><b> Especialidade</b> </label>
        <label style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control"><%= medico.getNomeEspecialidade(medico.getIdEspecialidade())%></label>
        </div>
        <br>
        <div class="form-group">
            <label for="nomepaciente"><b> Nome do Paciente </b></label>
            <label style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control"><%= pacienteDAO.get(consulta.getIdPaciente()).getNome() %></label>
        </div>
        <br>
        <div class="form-group">
            <label for="data"><b>Data:</b></label>    
            <label style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control"><%= consulta.getData()%></label>
        </div>
        <br>
        <div class="form-group">
            <label for="descricao"><b>Descrição</b></label>
            <label style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control"><%= consulta.getDescricao()%></label>
        </div>
        <br>
        <div class="form-group">
            <label for="realizada"><b> Consulta Realizada? </b></label>
            <label style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control"><%= consulta.getRealizada()%></label>
        </div>
        <br>
        <label for="exames"><b> Exames </b></label>
        <% for (Exames exame : exames) {
        %>
            <label style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control"><%= exame.getDescricaoTipoExame()%></label>
        <% 
            }
        %>
        <br>
    </div>
    <script src="bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>



