<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- BOOTSTRAP -->
        <link rel="stylesheet" href="bootstrap/bootstrap.min.css">
        
        <title> Cadastro - Médico </title>
    </head>
<body>
    <jsp:include page = "components/menu.jsp" />
    
    <div class="rounded border border-dark p-4 m-5" style="padding: 10px; text-align: center; justify-content: center">
    <h4 class="card-title" style="margin-top: 40px; margin-bottom: 20px">Cadastro - Médico:</h4>
    <br>
    <form method="POST" action="MedicoController">                   
        
        <div class="form-group">
            <label for="nome"><b> Nome </b></label>
            <input style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" type="text" class="form-control" name="nome" id="nome" autocomplete="off" placeholder="Digite o nome" required>
        </div>
        <br>
        <div class="form-group">
            <label for="crm"><b> CRM </b></label>
            <input style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" type="int" class="form-control" name="crm" id="crm" autocomplete="off" placeholder="Digite o CRM" required>
        </div>
        <br>
        <div class="form-group">
            <label for="estadocrm"><b> Estado do CRM </b></label>
            <select class="form-select" id="estadocrm" style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" required>
            <option value="" disabled selected hidden></option>
            <option value="AC">Acre</option>
            <option value="AL">Alagoas</option>
            <option value="AP">Amapá</option>
            <option value="AM">Amazonas</option>
            <option value="BA">Bahia</option>
            <option value="CE">Ceará</option>
            <option value="DF">Distrito Federal</option>
            <option value="ES">Espírito Santo</option>
            <option value="GO">Goiás</option>
            <option value="MA">Maranhão</option>
            <option value="MT">Mato Grosso</option>
            <option value="MS">Mato Grosso do Sul</option>
            <option value="MG">Minas Gerais</option>
            <option value="PA">Pará</option>
            <option value="PB">Paraíba</option>
            <option value="PR">Paraná</option>
            <option value="PE">Pernambuco</option>
            <option value="PI">Piauí</option>
            <option value="RJ">Rio de Janeiro</option>
            <option value="RN">Rio Grande do Norte</option>
            <option value="RS">Rio Grande do Sul</option>
            <option value="RO">Rondônia</option>
            <option value="RR">Roraima</option>
            <option value="SC">Santa Catarina</option>
            <option value="SP">São Paulo</option>
            <option value="SE">Sergipe</option>
            <option value="TO">Tocantins</option>
            <option value="EX">Estrangeiro</option>
        </select>
        </div>
        <br>
        <div class="form-group">
            <label for="especialidade"><b> Especialidade </b></label>
            <select class="form-select" id="especialidade" style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" required>
            <option value="" disabled selected hidden></option>
            <option value="cardio">Cardiologia</option>
            <option value="neuro">Neurologia</option>
            <option value="gastro">Gastrologista</option>
            <option value="pneumo">Pneumologia</option>
            </select>
        </div>
        <br>
        <div class="form-group">
            <label for="cpf"><b> CPF </b></label>
            <input style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" type="int" class="form-control cpf" name="cpf" id="cpf" autocomplete="off" placeholder="Digite o CPF" required>
        </div>
        <br>
        <div class="form-group">
            <label for="senha"><b> Senha </b></label>
            <input style="width:300px; display: block; margin-right: auto; margin-left: auto; text-align: center" type="password" class="form-control" name="senha" id="senha" autocomplete="off" placeholder="Digite a senha" required>
        </div>
        <br>
        <div class="form-group">
        <label for="autorizado"> Autorizado </label>
        <input type="checkbox" style="vertical-align: -2px; margin-left: 2px" id="autorizado" name="autorizado" checked>
        </div>
        <br>
        <!-- BOTÃO -->
        <button type="submit" class="btn btn-dark" style="margin-top: 20px; margin-bottom: 30px"><i class="fas fa-save"></i> Salvar </button>
    </form>
    </div>
    <script src="bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>
