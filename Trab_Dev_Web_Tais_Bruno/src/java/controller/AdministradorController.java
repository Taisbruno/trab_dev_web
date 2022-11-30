package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Administrador;
import dao.AdministradorDAO;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "AdministradorController", urlPatterns = {"/AdministradorController"})
public class AdministradorController extends HttpServlet {
    
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
        
        AdministradorDAO administradorDAO = new AdministradorDAO();
        request.setCharacterEncoding("UTF-8");
        
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String senha = request.getParameter("senha");
        Administrador administrador = new Administrador(nome, cpf, senha);
        
        try {
            administradorDAO.insert(administrador);
        } catch (Exception e) {
            throw new RuntimeException("Falha no cadastro do administrador");
        } finally {
            ArrayList<Administrador> administradores;
            administradores = administradorDAO.getAll();
            
        if (administrador != null) {
            request.setAttribute("administradores", administradores);
            RequestDispatcher list = getServletContext().getRequestDispatcher("/AreaAdministrador.jsp");
            list.forward(request, response);

        } else {
            request.setAttribute("msgError", "Erro");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/formAdministrador.jsp");
            rd.forward(request, response);
        }
        }        
    }
}


