<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- BOOTSTRAP -->
        <link rel="stylesheet" href="bootstrap/bootstrap.min.css">
        
        <title> Marcação de Exame </title>
    </head>
<body>      
    <jsp:include page = "components/menu.jsp" />
    
    <div class="rounded border border-dark p-4 m-5" style="padding: 10px; text-align: center">
    <h4 class="card-title" style="margin-top: 40px">Marcar Exame:</h4>
    <form method="POST" action="ExameController">                   
        <br>
        <div class="form-group">
            <label for="exame" style="margin-top: 30px"> Selecione o Exame </label>
            <select class="form-select" style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" required>

            <option value="" disabled selected hidden></option>
            <option value="coracao">Coração</option>
            <option value="pulmao">Pulmão</option>
            <option value="abdome">Abdome</option>
            <option value="cranio">Crânio</option>

        </select>
        </div>
        <br>        
        <div class="form-group">
            <label for="consulta"> Selecione a Consulta </label>
            <input style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" type="search" class="form-control" name="consulta" id="consulta" required>
        </div>
        <br>
        
        <button type="submit" class="btn btn-dark" style="margin-top: 20px; margin-bottom: 40px"><i class="fas fa-save"></i> Enviar </button>
    </form>
    </div>
    <script src="bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>

