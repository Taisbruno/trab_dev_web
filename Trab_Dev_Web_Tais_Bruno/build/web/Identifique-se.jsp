<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- BOOTSTRAP -->
        <link rel="stylesheet" href="bootstrap/bootstrap.min.css">
    <title> Identifique-se </title>
    </head>

<body>        
     <jsp:include page = "components/menu.jsp" />
     
        <div class="card-title">
                <div class="card-body" style="padding: 10%; text-align: center;">
                    <h3 class="card-title" style="margin-top: 30px; margin-bottom: 40px; padding-bottom: 5%; text-align: center"> Selecione seu perfil </h3>
                        <a class="btn btn-primary" href="login.jsp">Administrador</a>
                        <a class="btn btn-primary" href="login.jsp">Médico</a>
                        <a class="btn btn-primary" href="formPaciente.jsp">Paciente</a>
                </div>
        </div>
            
        <script src="bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>