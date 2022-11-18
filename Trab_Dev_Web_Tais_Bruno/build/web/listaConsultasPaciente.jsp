<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.Consulta"%>
<%@page import="models.Paciente"%>
<%@page import="models.Medico"%>
<%@page import="dao.MedicoDAO"%>
<%@page import="dao.PacienteDAO"%>
<%@page import="dao.ConsultaDAO"%>
<%@page import="dao.EspecialidadeDAO"%>

<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- BOOTSTRAP -->
        <link rel="stylesheet" href="bootstrap/bootstrap.min.css">

        <!-- TITLE -->
        <title> Clínica Tais Bruno - Lista de Consultas </title>
    </head>
    <body>
        <%@include file="components/ehPaciente.jsp" %>
        <jsp:include page = "components/menu.jsp" />
        
        <div class="card-title">
                <div class="card-body" style="padding: 10%; text-align: center;">
                    <h2 class="card-title" style="margin-top: 20px; padding-bottom: 2%; text-align: center">Clínica Tais Bruno - Consultas</h2>
                    <a class="nav-link" style="margin-bottom: 50px; text-decoration: underline; text-align: center" href="AreaPaciente.jsp">Voltar</a>
        <div class="container">
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container">
                <a class="navbar-brand" href="#"><b>Consultas</b></a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <a href="LoginController?action=logout" class="btn btn-outline-light my-2 my-sm-0"><i class="fas fa-sign-out-alt"></i> Logout </a>
            </div>
        </nav>
        
        <div class="container">
            <%  
                EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
                MedicoDAO medicoDAO = new MedicoDAO();
                ConsultaDAO consultaDAO = new ConsultaDAO();
                ArrayList<Consulta> consultas = consultaDAO.getByPaciente(paciente.getId());
            %>
            
            <div class="table-responsive">
                <table class="table table-borderless table-hover table-sm">
                    <caption>Lista de Consultas - Total: <%= consultas.size() %></caption>
                    <thead class="thead-light">
                        <tr>
                            <th scope="col"> Data </th>
                            <th scope="col"> Descrição </th>
                            <th scope="col"> Realizada </th>
                            <th scope="col"> Médico </th>
                            <th scope="col"> Especialidade </th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (int index = 0; index < consultas.size(); index++) {
                                Consulta consulta = consultas.get(index);
                                
                        %>
                            <tr>
                                <td class="data"><%= consulta.getData() %></td>
                                <td><%= consulta.getDescricao() %></td>
                                <td><%= consulta.getRealizada() %></td>
                                <td><%= medicoDAO.get(consulta.getIdMedico()).getNome()%></td>
                                <td><%= especialidadeDAO.get((medicoDAO.get(consulta.getIdMedico()).getIdEspecialidade())).getDescricao()%></td>
                                <td class="d-flex flex-row justify-content-center align-items-center p-2">
                                </td>
                            </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
            
        <script src="bootstrap/bootstrap.bundle.min.js"></script>
        </div>
        </div>
        </div>
</body>
</html>

