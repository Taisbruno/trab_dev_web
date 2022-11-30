package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.TipoPlano;
import dao.TipoPlanoDAO;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "TipoPlanoController", urlPatterns = {"/TipoPlanoController"})
public class TipoPlanoController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            TipoPlanoDAO tipoplanoDAO = new TipoPlanoDAO();
            TipoPlano tipoplano = new TipoPlano();
            int tipoplanoId;
            
            String action = (String) request.getParameter("action");
            
            switch (action) {
                
                case "get":
                    tipoplanoId = Integer.parseInt(request.getParameter("id"));
                    tipoplano = tipoplanoDAO.get(tipoplanoId);
                    
                    request.setAttribute("tipoplano", tipoplano);
                    RequestDispatcher get = getServletContext().getRequestDispatcher("/visualizarTipoPlano.jsp");   
                    get.forward(request, response);
                    
                    break;
            }
            
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        TipoPlanoDAO tipoplanoDAO = new TipoPlanoDAO();
        request.setCharacterEncoding("UTF-8");
        
        String descricao = request.getParameter("descricao");
        
        TipoPlano tipoplano = new TipoPlano(descricao);
        
        try {
            tipoplanoDAO.insert(tipoplano);
        } catch (Exception e) {
            throw new RuntimeException("Falha no cadastro do Tipo do Plano");
        } finally {
            ArrayList<TipoPlano> tipoplanos;
            tipoplanos = tipoplanoDAO.getAll();
            
        if (tipoplano != null) {
            request.setAttribute("tipoplanos", tipoplanos);
            RequestDispatcher list = getServletContext().getRequestDispatcher("/AreaAdministrador.jsp");
            list.forward(request, response);

        } else {
            request.setAttribute("msgError", "Erro");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/formTipoPlano.jsp");
            rd.forward(request, response);
        }
        }        
    }
}


