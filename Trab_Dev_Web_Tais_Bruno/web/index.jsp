<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- BOOTSTRAP -->
        <link rel="stylesheet" href="bootstrap/bootstrap.min.css">

        <title> Clínica Tais Bruno </title>
    </head>
    <body>
        <jsp:include page = "components/menu.jsp" />
        
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
                    <p>Cardiologia</p>
                    <p>Neurologia</p>
                    <p>Gastrologista</p>
                    <p>Pneumologia</p>
                </div>
        </section>
        <section>
                <div class="thumb"></div>
                <div class="info" style="justify-content: center; text-align: center">
                    <div>
                        <h3 class="card-title" style="margin-top: 60px"> Convênios aceitos:</h3> 
                    </div>
                    <br>
                    <p>SulAmerica</p>
                    <p>Unimed</p>
                    <p>Amil</p>
                    <p>Particular</p>
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