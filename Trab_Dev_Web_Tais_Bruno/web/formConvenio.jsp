<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- BOOTSTRAP -->
        <link rel="stylesheet" href="bootstrap/bootstrap.min.css">
        
        <title> Cadastrar o Convênio </title>
    </head>
<body>      
    <jsp:include page = "components/menu.jsp" />
    
    <div class="rounded border border-dark p-4 m-5" style="padding: 10px; text-align: center">
    <h4 class="card-title" style="margin-top: 40px">Cadastrar o Convênio:</h4>
    <br>
    <form method="POST" action="ConvenioController">                   
        <br>
        <div class="form-group" style="margin-top:20px">
            <label for="convenio"> Convênio </label>
            <input style="margin-top: 5px; width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" type="text" class="form-control" name="convenio" id="convenio" placeholder="Digite o nome do convênio" required>
        </div>
        <br>
        
        <button type="submit" class="btn btn-dark" style="margin-top: 20px; margin-bottom: 30px"><i class="fas fa-save"></i> Enviar </button>
    </form>
    </div>
    <script src="bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>
