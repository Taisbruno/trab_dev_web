package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Especialidade;
import dao.EspecialidadeDAO;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "EspecialidadeController", urlPatterns = {"/EspecialidadeController"})
public class EspecialidadeController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
            Especialidade especialidade = new Especialidade();
            int especialidadeId;
            
            String action = (String) request.getParameter("action");
            
            switch (action) {
                
                case "get":
                    especialidadeId = Integer.parseInt(request.getParameter("id"));
                    especialidade = especialidadeDAO.get(especialidadeId);
                    
                    request.setAttribute("especialidade", especialidade);
                    RequestDispatcher get = getServletContext().getRequestDispatcher("/visualizarEspecialidade.jsp");
                    get.forward(request, response);
                    
                    break;
            }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
        request.setCharacterEncoding("UTF-8");
        
        String descricao = request.getParameter("descricao");
        
        Especialidade especialidade = new Especialidade(descricao);
        
        try {
            especialidadeDAO.insert(especialidade);
        } catch (Exception e) {
            throw new RuntimeException("Falha no cadastro da Especialidade");
        } finally {
            ArrayList<Especialidade> especialidades;
            especialidades = especialidadeDAO.getAll();
            
        if (especialidade != null) {
            request.setAttribute("especialidades", especialidades);
            RequestDispatcher list = getServletContext().getRequestDispatcher("/AreaAdministrador.jsp");
            list.forward(request, response);

        } else {
            request.setAttribute("msgError", "Erro");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/formEspecialidade.jsp");
            rd.forward(request, response);
        }
        }        
    }
}


