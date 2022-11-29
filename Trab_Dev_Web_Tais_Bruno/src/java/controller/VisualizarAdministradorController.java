package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Administrador;
import dao.AdministradorDAO;

@WebServlet(name = "VisualizarAdministradorController", urlPatterns = {"/VisualizarAdministradorController"})
public class VisualizarAdministradorController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            AdministradorDAO administradorDAO = new AdministradorDAO();
            Administrador administrador = new Administrador();
            int administradorId;
            
            String action = (String) request.getParameter("action");
            
            switch (action) {
                
                case "get":
                    administradorId = Integer.parseInt(request.getParameter("id"));
                    administrador = administradorDAO.get(administradorId);
                    
                    request.setAttribute("administrador", administrador);
                    RequestDispatcher update = getServletContext().getRequestDispatcher("/visualizarAdministrador.jsp");       
                    update.forward(request, response);
                    
                    break;
            }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        }
    }
