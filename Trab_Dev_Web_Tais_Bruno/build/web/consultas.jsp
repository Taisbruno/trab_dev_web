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
        <title> Clínica Tais Bruno - Consultas </title>
    </head>
    <body>
        <jsp:include page = "components/menu.jsp" />
        
        <div class="card-title">
                <div class="card-body" style="padding: 10%; text-align: center;">
                    <h2 class="card-title" style="margin-top: 20px; margin-bottom: 90px; padding-bottom: 5%; text-align: center">Clínica Tais Bruno - Consultas</h2>
                    <h5 class="card-title" style="padding-bottom: 4%; text-align: center">Deseja marcar uma consulta?</h5>
                        <a class="btn btn-primary" href="formConsulta.jsp">Marcar Consulta</a>
                    <h5 class="card-title" style="padding-bottom: 4%; text-align: center">Lista de Consultas: </h5>
                    <select style="vertical-align: -2px; margin-left: 2px" class="form-control" id="autorizado"  name="autorizado" required>
                    <option value="S" selected> Sim </option>           
                    <option value="N" disabled> Não </option>
                    </select>-->
                </div>
            
        <!-- JavaScript Bundle with Popper -->
        <script src="bootstrap/bootstrap.bundle.min.js"></script>
        </div>
    </body>
</html>
