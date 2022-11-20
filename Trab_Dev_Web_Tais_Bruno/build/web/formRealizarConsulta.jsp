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
<%@page import="models.TipoExame"%>
<%@page import="dao.TipoExameDAO"%>
<%@page import="models.Exames"%>
<%@page import="dao.ExameDAO"%>

<%
    Consulta consulta = (Consulta) request.getAttribute("consultasMedico");
    TipoExameDAO tipoexameDAO = new TipoExameDAO();
    ArrayList<TipoExame> tipoexames = tipoexameDAO.getAll();
%>

<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="bootstrap/bootstrap.min.css">
        <title> Realização da Consulta </title>
    </head>
<body>        
    <jsp:include page = "components/menu.jsp" />
    <%@include file="components/ehMedico.jsp" %>
    
    <div class="rounded border border-dark p-4 m-5" style="padding: 10px; text-align: center">
        <h4 class="card-title" style="margin-top: 40px; margin-bottom: 40px">Realizar Consulta:</h4>
        <a class="btn btn-outline-dark my-2 my-sm-0" style="margin-bottom: 50px; text-align: center" href="realizarConsulta.jsp"><b>Voltar</b></a>   
        <form method="POST" action="RealizarConsultaController">   
            <br>
            <br>
        <input type="hidden" style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control" name="id" id="id" value="<%= consulta.getId() %>">
        <input type="hidden" style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control" name="idmedico" id="idmedico" value="<%= consulta.getIdMedico() %>">
        <input type="hidden" style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control" name="idpaciente" id="idpaciente" value="<%= consulta.getIdPaciente() %>">
        <input type="hidden" style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control" name="data" id="data" value="<%= consulta.getData() %>" >
        <div class="form-group">
            <label for="descricao"> <b>Descrição</b> </label>
            <input style="width:300px; height: 60px; display: block; margin-right: auto; margin-left: auto; text-align: center" type="text" class="form-control" name="descricao" id="descricao" autocomplete="off" placeholder="Digite a descrição" value="<%= consulta.getDescricao()%>" required>
        </div>
        <br>
        <div class="form-group">
            <label for="exame"><b> Exames </b></label>
            <select style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control" id="idtipoexame" name="idtipoexame" multiple> 
                <option value="" hidden></option>
                <%
                    for (int indexxx = 0; indexxx < tipoexames.size(); indexxx++) {
                        TipoExame tipoexame = tipoexames.get(indexxx);           
                %>
                <option value="<%= tipoexame.getId() %>"><%= tipoexame.getDescricao() %></option>
                <%
                    }
                %>
            </select>
        </div>
        <br>
        <div class="form-group">
            <label for="realizada"><b> Consulta Realizada? </b></label>
            <select style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-select" id="realizada" name="realizada" required>                
                <option value="N" disabled> Não </option>
                <option value="S" selected> Sim </option> 
            </select>
        </div>
            
        <button type="submit" class="btn btn-dark" style="margin-top: 50px; margin-bottom: 40px"><i class="fas fa-save"></i> Enviar </button>
     </form>
    
    </div>
    <script src="bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>



