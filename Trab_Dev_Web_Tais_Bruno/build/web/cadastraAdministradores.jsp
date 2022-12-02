<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.ArrayList"%>
<%@page import="dao.AdministradorDAO"%>
<%@page import="models.Administrador"%>

<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="bootstrap/bootstrap.min.css">

        <title> Clínica Tais Bruno - Cadastra Administradores </title>
    </head>
    <body>
        <%@include file="components/ehAdministrador.jsp" %>
        <jsp:include page = "components/menu.jsp" />
        
        <div class="card-title">
                <div class="card-body" style="padding: 10%; text-align: center;">
                    <h2 class="card-title" style="margin-top: 10px; padding-bottom: 2%; text-align: center">Cadastra Administradores</h2>
                    <a class="btn btn-outline-dark my-2 my-sm-0" style="margin-bottom: 50px; text-align: center" href="AreaAdministrador.jsp"><b>Voltar</b></a>
                    <br>
                    <br>
                    <br>
                    <br>
                    <br>
                    <br>
                    <a href="formAdministrador.jsp" class="btn btn-outline-dark my-2 my-sm-0"><b>Inserir Administrador</b></a>
        <div class="container">
            <nav class="navbar navbar-expand-lg navbar-dark bg-secondary" style="margin-top: 20px; margin-bottom: 10px">
            <div class="container">
                <a class="navbar-brand" href="#"><b>Administradores</b></a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <a href="LoginController?action=logout" class="btn btn-outline-light my-2 my-sm-0"><i class="fas fa-sign-out-alt"></i> Logout </a>
            </div>
        </nav>
 
            <div class="table-responsive">
                <% 
                    AdministradorDAO administradorDAO = new AdministradorDAO();
                    ArrayList<Administrador> administradores = administradorDAO.getAll();
                %>
                
                <table class="table table-borderless table-hover table-sm">
                    <caption>Lista de Administradores - Total: <%= administradores.size() %></caption>
                    <thead class="thead-light">
                        <tr>
                            <th scope="col"> Nome </th>
                            <th scope="col"> CPF </th>
                            <th scope="col"> Senha </th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (int index = 0; index < administradores.size(); index++) {
                                Administrador administradorr = administradores.get(index);
                                
                                String linkUpdate = "AdministradorController?action=update&id=" + administradorr.getId();
                                String linkDelete = "AdministradorController?action=delete&id=" + administradorr.getId();
                                String linkVisualizar = "AdministradorController?action=get&id=" + administradorr.getId();
                        %>
                            <tr>
                                <td><%= administradorr.getNome() %></td>
                                <td><%= administradorr.getCpf() %></td>
                                <td><%= administradorr.getSenha() %></td>

                                <td class="d-flex flex-row justify-content-center align-items-center p-2">

                                    <a href="<%= linkVisualizar %>" class="btn btn-info" style="margin-right:10px;"> Visualizar <i class="fas fa-pen"></i></a>

                                    <a href="<%= linkUpdate %>" class="btn btn-info" style="margin-right:10px;"> Editar <i class="fas fa-pen"></i></a>

                                    <a href="<%= linkDelete %>" class="btn btn-info" style="margin-right:10px;"> Excluir <i class="fas fa-pen"></i></a>          

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

