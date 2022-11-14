<%@page import="java.util.ArrayList"%>
<%@page import="models.Consulta"%>
<%@page import="models.Paciente"%>
<%@page import="models.Medico"%>
<%@page import="dao.MedicoDAO"%>
<%@page import="dao.PacienteDAO"%>
<%@page import="dao.ConsultaDAO"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- BOOTSTRAP -->
        <link rel="stylesheet" href="bootstrap/bootstrap.min.css">

        <!-- TITLE -->
        <title> Clínica Tais Bruno - Marcar Consulta </title>

    </head>
    <body>
        <%@include file="components/ehPaciente.jsp" %>
        <jsp:include page = "components/menu.jsp" />
        
        <div class="card-title">
                <div class="card-body" style="padding: 10%; text-align: center;">
                    <h2 class="card-title" style="margin-top: 20px; margin-bottom: 90px; padding-bottom: 5%; text-align: center">Clínica Tais Bruno - Consultas</h2>
                <div class="container">
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            
            <div class="container">
                <a class="navbar-brand" href="#"><b>Marcação de Consulta</b></a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item mx-3 active">
                            <a class="nav-link" href="ListaConsultaPacienteController?action=get&id=<%= String.valueOf(paciente.getId())%>"><i class="fas fa-money-bill-wave"></i> Consultas <span class="sr-only">(current)</span> </a>
                        </li>
                    </ul>
                </div>
                <a href="LoginController?action=logout" class="btn btn-outline-light my-2 my-sm-0"><i class="fas fa-sign-out-alt"></i> Logout </a>
            </div>
        </nav>
        </div>
                        
        <div class="container">

            <div class="d-flex flex-row justify-content-between align-items-center m-5">
                <div class="col-sm-12 col-md-6 col-lg-4 col-xl-3 d-flex justify-content-start align-items-center p-2">
                    <h3><b> Marcar Consulta </b></h3>
                </div>

                <div class="col-sm-12 col-md-6 col-lg-4 col-xl-3 d-flex justify-content-end align-items-center p-2">
                    <a href="ListaConsultaPacienteController?action=get&id=<%= String.valueOf(paciente.getId())%>" class="btn btn-primary"><i class="fas fa-list"></i> Listar Consultas </a>
                </div>
            </div> 

            <jsp:include page="components/formConsulta.jsp" />
        </div>
        </div>
        </div>

    </body>
</html>
