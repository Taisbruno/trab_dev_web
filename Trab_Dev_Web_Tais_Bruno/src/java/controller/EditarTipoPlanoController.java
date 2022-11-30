package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.TipoPlanoDAO;
import models.TipoPlano;

@WebServlet(name = "EditarTipoPlanoController", urlPatterns = {"/EditarTipoPlanoController"})
public class EditarTipoPlanoController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            TipoPlanoDAO tipoplanoDAO = new TipoPlanoDAO();
            TipoPlano tipoplano = new TipoPlano();
            int tipoplanoId;
            
            String action = (String) request.getParameter("action");
            
            switch (action) {
                
                case "update":
                    tipoplanoId = Integer.parseInt(request.getParameter("id"));
                    tipoplano = tipoplanoDAO.get(tipoplanoId);
                    
                    request.setAttribute("tipoplano", tipoplano);
                    RequestDispatcher update = getServletContext().getRequestDispatcher("/formEditarTipoPlano.jsp");
                    update.forward(request, response);       
                    
                    break;
                    
            }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            TipoPlanoDAO tipoplanoDAO = new TipoPlanoDAO();
            TipoPlano tipoplano = new TipoPlano();
            request.setCharacterEncoding("UTF-8");
           
            tipoplano.setId(Integer.parseInt(request.getParameter("id")));
            tipoplano.setDescricao(request.getParameter("descricao"));
            tipoplanoDAO.update(tipoplano);
            
            request.setAttribute("tipoplano", tipoplano);
            RequestDispatcher list = getServletContext().getRequestDispatcher("/cadastraTipoPlano.jsp");
            list.forward(request, response);

        }
    }
