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

<%
    MedicoDAO medicoDAO = new MedicoDAO();
    ArrayList<Medico> medicos = medicoDAO.getAll();
%>

<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- BOOTSTRAP -->
        <link rel="stylesheet" href="bootstrap/bootstrap.min.css">
        <title> Marcação de Consulta </title>
    </head>
<body>        
    <jsp:include page = "components/menu.jsp" />
    <%@include file="components/ehPaciente.jsp" %>
    
    <div class="rounded border border-dark p-4 m-5" style="padding: 10px; text-align: center">
        <h4 class="card-title" style="margin-top: 40px; margin-bottom: 40px">Marcar Consulta:</h4>
        <a class="btn btn-outline-dark my-2 my-sm-0" style="margin-bottom: 50px; text-align: center" href="AreaPaciente.jsp"><b>Voltar</b></a>
        <br>
     <form method="POST" action="ConsultaController?action=marcar">
        <input style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" type="hidden" class="form-control" name="idconsulta" id="idconsulta" >
            
        <div class="form-group">
            <br>
            <br>
            <label for="nomemedico"><b> Médico e Especialidade </b></label>
            <select style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-select" id="idmedico" name="idmedico" required>
                <option value="" disabled selected hidden></option>
                <%
                    for (int index = 0; index < medicos.size(); index++) {
                        Medico medico = medicos.get(index);
                %>
                <%
                    if (medico.getAutorizado().equals("S")) {
                %>    
                <option value="<%= medico.getId() %>"><%= medico.getNome() + " - " + medico.getNomeEspecialidade(medico.getIdEspecialidade()) %></option>
                <%
                    }
                %>
                <%
                    }
                %>

            </select>
        <br> 
            <input style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control" name="idpaciente" id="idpaciente" type="hidden" value="<%= paciente.getId()%>">

        <div class="form-group">
            <label for="data"><b>Selecione a data</b></label>         
            <input style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" type="date" class="form-control" name="data" id="data" required>
        </div>
        <br>
        <div class="form-group">
            <label for="hora"><b> Selecione o horário</b> </label>         
            <input style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" type="time" class="form-control" name="hora" id="hora" required>
        </div>
        <br>
            <input style="width:300px; height: 50px; display: block; margin-right: auto; margin-left: auto; text-align: center" type="hidden" class="form-control" name="descricao" id="descricao" value = " ">
        <div class="form-group">
        <input style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" type="hidden" class="form-control" name="realizada" value = "N" id="realizada" >
        </div>
        
        <button type="submit" class="btn btn-dark" style="margin-top: 30px; margin-bottom: 40px"><i class="fas fa-save"></i> Enviar </button>
     </div>
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

