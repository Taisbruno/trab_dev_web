<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- BOOTSTRAP -->
        <link rel="stylesheet" href="bootstrap/bootstrap.min.css">
        
        <title> Cadastrar Tipo de Exame </title>
    </head>
<body>      
    <jsp:include page = "components/menu.jsp" />
    
    <div class="rounded border border-dark p-4 m-5" style="padding: 10px; text-align: center">
    <h4 class="card-title" style="margin-top: 40px">Cadastrar tipo do Exame:</h4>
    <br>
    <form method="POST" action="TipoExameController">                   
        <br>
        <div class="form-group" style="margin-top:20px">
            <label for="tipoexame">Tipo do Exame</label>
            <input style="margin-top: 5px; width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" type="text"  class="form-control" name="tipoexame" id="tipoexame" placeholder="Digite o tipo do exame" required>
        </div>
        <br>
        <!-- BOTÃO -->
        <button type="submit" class="btn btn-dark" style="margin-top: 30px; margin-bottom: 30px"><i class="fas fa-save"></i> Enviar </button>
    </form>
    </div>
    <script src="bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>


