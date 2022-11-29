package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.AdministradorDAO;
import models.Administrador;

@WebServlet(name = "EditarAdministradorController", urlPatterns = {"/EditarAdministradorController"})
public class EditarAdministradorController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            AdministradorDAO administradorDAO = new AdministradorDAO();
            Administrador administrador = new Administrador();
            int administradorId;
            
            String action = (String) request.getParameter("action");
            
            switch (action) {
                
                case "update":
                    administradorId = Integer.parseInt(request.getParameter("id"));
                    administrador = administradorDAO.get(administradorId);
                    
                    request.setAttribute("administrador", administrador);
                    RequestDispatcher update = getServletContext().getRequestDispatcher("/formEditarAdministrador.jsp");
                    update.forward(request, response);       
                    
                    break;
                    
            }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            AdministradorDAO administradorDAO = new AdministradorDAO();
            Administrador administrador = new Administrador();
            request.setCharacterEncoding("UTF-8");
           
            administrador.setId(Integer.parseInt(request.getParameter("id")));
            administrador.setNome(request.getParameter("nome"));
            administrador.setCpf(request.getParameter("cpf"));
            administrador.setSenha(request.getParameter("senha"));
            administradorDAO.update(administrador);
            
            request.setAttribute("administrador", administrador);
            RequestDispatcher list = getServletContext().getRequestDispatcher("/cadastraAdministradores.jsp");
            list.forward(request, response);
            
        }
    }
