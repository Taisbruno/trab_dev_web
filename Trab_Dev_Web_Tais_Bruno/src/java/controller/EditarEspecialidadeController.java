package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.EspecialidadeDAO;
import models.Especialidade;

@WebServlet(name = "EditarEspecialidadeController", urlPatterns = {"/EditarEspecialidadeController"})
public class EditarEspecialidadeController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
            Especialidade especialidade = new Especialidade();
            int especialidadeId;
            
            String action = (String) request.getParameter("action");
            
            switch (action) {
                
                case "update":
                    especialidadeId = Integer.parseInt(request.getParameter("id"));
                    especialidade = especialidadeDAO.get(especialidadeId);
                    
                    request.setAttribute("especialidade", especialidade);
                    RequestDispatcher update = getServletContext().getRequestDispatcher("/formEditarEspecialidade.jsp");
                    update.forward(request, response);       
                    
                    break;
                    
            }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
            Especialidade especialidade = new Especialidade();
            request.setCharacterEncoding("UTF-8");
           
            especialidade.setId(Integer.parseInt(request.getParameter("id")));
            especialidade.setDescricao(request.getParameter("descricao"));
            especialidadeDAO.update(especialidade);
            
            request.setAttribute("especialidade", especialidade);
            RequestDispatcher list = getServletContext().getRequestDispatcher("/cadastraEspecialidades.jsp");
            list.forward(request, response);

        }
    }
