<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="models.Paciente"%>
<%@page import="dao.PacienteDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.TipoPlano"%>
<%@page import="dao.TipoPlanoDAO"%>

<%  
    Paciente paciente = (Paciente) request.getAttribute("paciente");
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
        <title> Alteração de Paciente </title>
    </head>
<body>        
    <jsp:include page = "components/menu.jsp" />
    <%@include file="components/ehAdministrador.jsp" %>
    
    <div class="rounded border border-dark p-4 m-5" style="padding: 10px; text-align: center">
        <h4 class="card-title" style="margin-top: 40px; margin-bottom: 50px;">Editar Paciente</h4>
        <a class="btn btn-outline-dark my-2 my-sm-0" style="margin-bottom: 50px; text-align: center" href="cadastraPacientes.jsp"><b>Voltar</b></a>  
     <form method="POST" action="PacienteController?action=update"> 
        <input type="hidden" style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control" name="id" id="id" value="<%= paciente.getId() %>">
        <div class="form-group">
            <br>
            <br>
            <label for="nome"><b>Nome do Paciente</b></label>
            <input type="text" style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control" id="nome" name="nome" value="<%= paciente.getNome() %>">
             <br>
             <label for="cpf"><b>CPF</b></label>
            <input type="text" style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control" name="cpf" id="cpf" value="<%= paciente.getCpf() %>">
             <br>
             <label for="senha"><b>Senha</b></label>
            <input type="text" style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control" name="senha" id="senha" value="<%= paciente.getSenha() %>" >
        </div>
        <br>
        <div class="form-group">
            <label for="autorizado"><b>Autorizado?</b></label>
        <select style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center;" class="form-select" id="autorizado" name="autorizado" required>
        <option value="S"> Sim </option>     
        <option value="N"> Não </option>
        </select>
        <br>
        </div>
        <label for="idtipoplano"><b>Tipo de Plano</b></label>
        <select style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-select" id="idtipoplano" name="idtipoplano"> 
            <%
                for (int index = 0; index < tipoplanos.size(); index++) {
                    TipoPlano tipoplano = tipoplanos.get(index);           
            %>
            <option value="<%= tipoplano.getId() %>"><%= tipoplano.getDescricao() %></option>
            <%
                }
            %>
        </select>
        <br>
        <button type="submit" class="btn btn-dark" style="margin-top: 40px; margin-bottom: 40px"><i class="fas fa-save"></i> Enviar </button>
     </form>
    </div>
    <script src="bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>



