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
    Paciente paciente = (Paciente) session.getAttribute("paciente");
    MedicoDAO medicoDAO = new MedicoDAO();
    ArrayList<Medico> medicos = medicoDAO.getAll();
    EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
    ArrayList<Especialidade> especialidades = especialidadeDAO.getAll();
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
    
    <div class="rounded border border-dark p-4 m-5" style="padding: 10px; text-align: center">
        <h4 class="card-title" style="margin-top: 40px">Marcar Consulta:</h4>
        <br>
             
     <form method="POST" action="MarcarConsultaController">
        <input style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" type="hidden" class="form-control" name="idconsulta" id="idconsulta" >
            
        <div class="form-group">
            <label for="nomemedico"> Nome do Médico </label>
            <select style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-select" id="idmedico" name="idmedico" required>
                <option value="" disabled>Nome do Médico</option>
                <%
                    for (int index = 0; index < medicos.size(); index++) {
                        Medico medico = medicos.get(index);
                        Especialidade especialidade = especialidades.get(index);
                %>

                <option value="<%= medico.getId() %>"><%= medico.getNome() + " - " + especialidade.getDescricao() %></option>
                <%
                    }
                %>

            </select>
        <br> 
            <input style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control" name="idpaciente" id="idpaciente" type="hidden" value="<%= paciente.getId()%>">

        <div class="form-group">
            <label for="data"> Selecione a data </label>         
            <input style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" type="date" class="form-control" name="data" id="data" required>
        </div>
        <br>
        <div class="form-group">
            <label for="hora"> Selecione o horário </label>         
            <input style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" type="time" class="form-control" name="hora" id="hora" required>
        </div>
        <br>
            <input style="width:300px; height: 50px; display: block; margin-right: auto; margin-left: auto; text-align: center" type="hidden" class="form-control" name="descricao" id="descricao" value = " ">
        <div class="form-group">
        <input style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" type="hidden" class="form-control" name="realizada" value = "N" id="realizada" >
        </div>
        <!-- BOTÃO -->
        <button type="submit" class="btn btn-dark" style="margin-top: 30px; margin-bottom: 40px"><i class="fas fa-save"></i> Enviar </button>
     </div>
     </form>
    
    </div>
    <script src="bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>

