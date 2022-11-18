<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.Consulta"%>
<%@page import="models.Paciente"%>
<%@page import="models.Medico"%>
<%@page import="dao.MedicoDAO"%>
<%@page import="dao.PacienteDAO"%>
<%@page import="dao.ConsultaDAO"%>

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
        <%@include file="components/ehMedico.jsp" %>
        <jsp:include page = "components/menu.jsp" />
        
        <div class="card-title">
                <div class="card-body" style="padding: 10%; text-align: center;">
                    <h2 class="card-title" style="margin-top: 20px; margin-bottom: 90px; padding-bottom: 5%; text-align: center">Clínica Tais Bruno - Consultas</h2>
        <div class="container">
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <!-- CONTAINER NAVBAR -->
            <div class="container">
                <a class="navbar-brand" href="#"><b>Consultas</b></a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <a href="LoginController?action=logout" class="btn btn-outline-light my-2 my-sm-0"><i class="fas fa-sign-out-alt"></i> Logout </a>
            </div>
        </nav>
 
            <!-- UPDATE + DELETE -->
            <div class="table-responsive">
                <% 
                ConsultaDAO consultaDAO = new ConsultaDAO();
                PacienteDAO pacienteDAO = new PacienteDAO();
                ArrayList<Consulta> consultas = consultaDAO.getByMedico(medico.getId());
                %>
                
                <table class="table table-borderless table-hover table-sm">
                    <caption>Lista de Consulta - Total: <%= consultas.size() %></caption>
                    <thead class="thead-light">
                        <tr>
                            <th scope="col"> Data </th>
                            <th scope="col"> Descrição </th>
                            <th scope="col"> Realizada </th>
                            <th scope="col"> Paciente </th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (int index = 0; index < consultas.size(); index++) {
                                Consulta consulta = consultas.get(index);
                                
                                String linkUpdate = "EditarConsultaController?action=update&id=" + consulta.getId();
                                String linkRealizar = "RealizarConsultaController?action=update&id=" + consulta.getId();
                        %>
                            <tr>
                                <td><%= consulta.getData() %></td>
                                <td><%= consulta.getDescricao() %></td>
                                <td><%= consulta.getRealizada() %></td>
                                <td><%= pacienteDAO.get(consulta.getIdPaciente()).getNome() %></td>
                                
                                <td class="d-flex flex-row justify-content-center align-items-center p-2">
                                    <a href="<%= linkRealizar %>" class="btn btn-info m-1"> Realizar Consulta<i class="fas fa-pen"></i></a>
                                    <% if (consulta.getRealizada().equals("S")) {
                                    %> 
                                        <a href="<%= linkUpdate %>" class="btn btn-info m-1"> Editar Consulta<i class="fas fa-pen"></i></a>
                                    <% 
                                    } else {
                                    %> 
                                        <a href="<%= linkUpdate %>" class="btn btn-info m-1" style="display:none"> Editar Consulta<i class="fas fa-pen"></i></a>
                                    <%
                                     }
                                    %>

                                </td>
                            </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
        </div>
        </div>
        <script src="bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>



