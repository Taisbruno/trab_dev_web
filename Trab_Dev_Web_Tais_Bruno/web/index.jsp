<%@page import="models.TipoPlano"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.Especialidade"%>
<%@page import="dao.TipoPlanoDAO"%>
<%@page import="dao.EspecialidadeDAO"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="bootstrap/bootstrap.min.css">

        <title> Clínica Tais Bruno </title>
    </head>
    <body>
        <jsp:include page = "components/menu.jsp" />
        
        <%
            EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
            TipoPlanoDAO tipoplanoDAO = new TipoPlanoDAO();
            ArrayList<Especialidade> especialidades = especialidadeDAO.getAll();
            ArrayList<TipoPlano> tipoplanos = tipoplanoDAO.getAll();
        %>
        
            <div class="rounded border border-dark p-4 m-5" style="padding: 10px; text-align: center">        
                <h3 class="card-title" style="margin-top: 80px">Bem-vindo a Clínica Tais Bruno!</h3> 
            </div>
            <section>
            <p style="text-align:center"><img src="img/clinica.jpg" alt="clinica" style="padding: 10px; width: 900px"></p>
                <div class="thumb" style="height: 40px"></div>
                <div class="info" style="justify-content: center; text-align: center">
                    <div>
                        <h3 class="card-title" style="margin-top: 60px"> Especialidades:</h3> 
                    </div>
                    <br>
                    <%
                        for (int index = 0; index < especialidades.size(); index++) {
                                Especialidade especialidade = especialidades.get(index);
                    %>
                    <p><%= especialidade.getDescricao()%></p>
                    <%
                        }
                    %>
                </div>
            </section>
            <section>
                <div class="thumb"></div>
                <div class="info" style="justify-content: center; text-align: center">
                    <div>
                        <h3 class="card-title" style="margin-top: 60px"> Planos aceitos:</h3> 
                    </div>
                    <br>
                    <%
                        for (int indexx = 0; indexx < tipoplanos.size(); indexx++) {
                               TipoPlano tipoplano = tipoplanos.get(indexx);
                    %>
                    <p><%= tipoplano.getDescricao()%></p>
                    <%
                        }
                    %>
                </div>
            </section>
            <footer class="mastfoot mt-auto" style="text-align: center">
                <div class="container" style="margin-top: 80px">
                    <span class="text-muted">Desenvolvido por <b>Tais Bruno</b><br/>Universidade Federal Fluminense</span>
                </div>
            </footer>
        <script src="bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>