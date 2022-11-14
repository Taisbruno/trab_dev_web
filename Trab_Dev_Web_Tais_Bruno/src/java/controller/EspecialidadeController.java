package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Especialidade;
import dao.EspecialidadeDAO;

@WebServlet(name = "EspecialidadeController", urlPatterns = {"/EspecialidadeController"})
public class EspecialidadeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
        ArrayList<Especialidade> especialidades;
        Especialidade especialidade = new Especialidade();
        int especialidadeId;
        
        String action = (String) request.getParameter("action");
        
        switch (action) {
            case "all":
                especialidades = especialidadeDAO.getAll();
                request.setAttribute("especialidades", especialidades);
                RequestDispatcher list = getServletContext().getRequestDispatcher("/formEditarConsulta.jsp");
                list.forward(request, response);
                
                break;
            case "insert":
                especialidade.setId(0);
                especialidade.setDescricao("");
                
                request.setAttribute("especialidade", especialidade);
                RequestDispatcher insert = getServletContext().getRequestDispatcher("/formEditarConsulta.jsp");
                insert.forward(request, response);
                
                break;
            case "update":
                especialidadeId = Integer.parseInt(request.getParameter("id"));
                especialidade = especialidadeDAO.get(especialidadeId);
                
                request.setAttribute("especialidade", especialidade);
                RequestDispatcher update = getServletContext().getRequestDispatcher("/formEditarConsulta.jsp");
                update.forward(request, response);
                
                break;
            case "delete":
                especialidadeId = Integer.parseInt(request.getParameter("id"));
                especialidadeDAO.delete(especialidadeId);
                
                especialidades = especialidadeDAO.getAll();
                
                request.setAttribute("especialidades", especialidades);
                RequestDispatcher delete = getServletContext().getRequestDispatcher("/formEditarConsulta.jsp");
                delete.forward(request, response);
                
                break;
        }       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
        request.setCharacterEncoding("UTF-8");
        String message = "";
        
        try {
            Especialidade especialidade = new Especialidade();
            if (request.getParameter("descricao").equals("")) {
                message = "'Descricao' is empty";
                request.setAttribute("error", 1);
            }
            
            if (message.equals("")) {
                especialidade.setId(Integer.parseInt(request.getParameter("id")));
                especialidade.setDescricao(request.getParameter("descricao"));

                if (especialidade.getId() == 0) {
                    if (especialidadeDAO.insert(especialidade)) {
                        message = "Nova Especialidade!";
                        request.setAttribute("error", 0);
                    } else {
                        message = "Não Efetivado";
                        request.setAttribute("error", 1);
                    }
                } else {
                    if (especialidadeDAO.update(especialidade)) {
                        message = "Especialidade Modificada!";
                        request.setAttribute("error", 0);
                    } else {
                        message = "Não Efetivado";
                        request.setAttribute("error", 1);
                    }
                }
            }
            
            request.setAttribute("message", message);
            
        } catch(NumberFormatException e) {
            message = "Error: " + e.getMessage();
            System.out.println(message);
            
            request.setAttribute("message", message);
            request.setAttribute("error", 1);
            
        } finally {
            ArrayList<Especialidade> especialidades;
            especialidades = especialidadeDAO.getAll();
            request.setAttribute("especialidades", especialidades);
            RequestDispatcher list = getServletContext().getRequestDispatcher("/formEditarConsulta.jsp");
            list.forward(request, response);
        }
    }
}