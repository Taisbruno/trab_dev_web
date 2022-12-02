<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="models.Medico"%>
<%@page import="dao.MedicoDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.Especialidade"%>
<%@page import="dao.EspecialidadeDAO"%>

<%  
    Medico medico = (Medico) request.getAttribute("medico");
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
        <title> Alteração do Médico </title>
    </head>
<body>        
    <jsp:include page = "components/menu.jsp" />
    <%@include file="components/ehAdministrador.jsp" %>
    
    <div class="rounded border border-dark p-4 m-5" style="padding: 10px; text-align: center">
        <h4 class="card-title" style="margin-top: 40px; margin-bottom: 50px;">Editar Médico</h4>
        <a class="btn btn-outline-dark my-2 my-sm-0" style="margin-bottom: 50px; text-align: center" href="cadastraMedicos.jsp"><b>Voltar</b></a>  
     <form method="POST" action="MedicoController?action=update">   
        <input type="hidden" style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control" name="id" id="id" value="<%= medico.getId() %>">
        <div class="form-group">
            <br>
            <br>
            <label for="nome"><b>Nome do Médico</b></label>
            <input type="text" style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control" id="nome" name="nome" value="<%= medico.getNome() %>">
            <br>
            <label for="nome"><b>CRM</b></label>
            <input type="text" style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control" id="crm" name="crm" value="<%= medico.getCrm() %>">
            <br>
            <label for="estadocrm"><b>Estado do CRM</b></label>
            <select style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center;" class="form-select" id="estadocrm" name="estadocrm" required>
            <option value="AC">Acre</option>
            <option value="AL">Alagoas</option>
            <option value="AP">Amapá</option>
            <option value="AM">Amazonas</option>
            <option value="BA">Bahia</option>
            <option value="CE">Ceará</option>
            <option value="DF">Distrito Federal</option>
            <option value="ES">Espírito Santo</option>
            <option value="GO">Goiás</option>
            <option value="MA">Maranhão</option>
            <option value="MT">Mato Grosso</option>
            <option value="MS">Mato Grosso do Sul</option>
            <option value="MG">Minas Gerais</option>
            <option value="PA">Pará</option>
            <option value="PB">Paraíba</option>
            <option value="PR">Paraná</option>
            <option value="PE">Pernambuco</option>
            <option value="PI">Piauí</option>
            <option value="RJ">Rio de Janeiro</option>
            <option value="RN">Rio Grande do Norte</option>
            <option value="RS">Rio Grande do Sul</option>
            <option value="RO">Rondônia</option>
            <option value="RR">Roraima</option>
            <option value="SC">Santa Catarina</option>
            <option value="SP">São Paulo</option>
            <option value="SE">Sergipe</option>
            <option value="TO">Tocantins</option>
            <option value="EX">Estrangeiro</option>
            </select>
            <br>
            <label for="cpf"><b>CPF</b></label>
            <input type="text" style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control" name="cpf" id="cpf" value="<%= medico.getCpf() %>">
             <br>
             <label for="senha"><b>Senha</b></label>
            <input type="text" style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-control" name="senha" id="senha" value="<%= medico.getSenha() %>" >
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
        <label for="idespecialidade"><b>Especialidade</b></label>
        <select style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" class="form-select" name="idespecialidade" id="idespecialidade"> 
            <%
                for (int index = 0; index < especialidades.size(); index++) {
                    Especialidade especialidade = especialidades.get(index);           
            %>
            <option value="<%= especialidade.getId() %>"><%= especialidade.getDescricao() %></option>
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