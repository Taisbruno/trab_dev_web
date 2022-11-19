<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="models.Paciente"%>
<%@page import="dao.PacienteDAO"%>
<%@page import="dao.MedicoDAO"%>
<%@page import="dao.ConsultaDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.Medico"%>
<%@page import="models.Consulta"%>
<%@page import="models.Especialidade"%>

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
            <select class="form-select d-block w-100 form-control" id="idmedico" name="idmedico" required>
                    <option value="">Nome do Médico</option>
                        
                        <%
                        
                        MedicoDAO medicoDAO = new MedicoDAO();
                        ArrayList<Medico> listamedicos = (ArrayList<Medico>) request.getAttribute("listamedicos");
                        
                        for (Medico medico : listamedicos) {
                            out.println("<option value= '"+ medico.getId()+ "'>");
                            out.println(medicoDAO.get(medico.getId()).getNome());

                            out.println("</option>");        
                        %>

            </select>
                        <%
                            }
                        %>
                    
            <input style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" type="search"  class="form-control" name="medico" id="medico" autocomplete="off" placeholder="Digite o nome do médico" required>
        
        <br> 
        <div class="form-group">
            <label for="paciente"> Nome do Paciente </label>
            <input style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" type="text" class="form-control" name="paciente" id="paciente" autocomplete="off" placeholder="Digite o nome do paciente" required>
        </div>
        <br>
        <div class="form-group">
            <label for="data"> Selecione a data </label>
            <input style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" type="date" class="form-control" name="data" id="data" value="" required>
        </div>
        <br>
        <div class="form-group">
            <label for="descricao"> Descrição </label>
            <input style="width:300px; height: 50px; display: block; margin-right: auto; margin-left: auto; text-align: center" type="text" class="form-control" name="descricao" id="descricao" autocomplete="off" placeholder="Digite a descrição" required>
        </div>
        <br>
        <div class="form-group">
        <label for="realizada"> Consulta Realizada? </label>
        <input type="checkbox" style="vertical-align: -2px; margin-left: 2px" id="realizada" name="realizada">
        </div>
        <!-- BOTÃO -->
        <button type="submit" class="btn btn-dark" style="margin-top: 30px; margin-bottom: 40px"><i class="fas fa-save"></i> Enviar </button>
     </form>
    
    </div>
    <script src="bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>

