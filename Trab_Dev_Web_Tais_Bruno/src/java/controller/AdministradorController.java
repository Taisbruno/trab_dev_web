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
            ArrayList<Administrador> administradores;
            int administradorId;
            
            String action = (String) request.getParameter("action");
            
            switch (action) {
                
                case "get":
                    administradorId = Integer.parseInt(request.getParameter("id"));
                    administrador = administradorDAO.get(administradorId);
                    
                    request.setAttribute("administrador", administrador);
                    RequestDispatcher get = getServletContext().getRequestDispatcher("/visualizarAdministrador.jsp");       
                    get.forward(request, response);
                    
                    break;
                    
                case "delete":
                    administradorId = Integer.parseInt(request.getParameter("id"));
                    administradorDAO.delete(administradorId);
                
                    administradores = administradorDAO.getAll();
                
                    request.setAttribute("administradores", administradores);
                    RequestDispatcher delete = getServletContext().getRequestDispatcher("/cadastraAdministradores.jsp");
                    delete.forward(request, response);
                
                    break;
                
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
            request.setCharacterEncoding("UTF-8");
            String message = "";
            
            String action = (String) request.getParameter("action");
            
            switch (action) {
                
                case "insert":
                    String nome = request.getParameter("nome");
                    String cpf = request.getParameter("cpf");
                    String senha = request.getParameter("senha");
                    Administrador administrador = new Administrador(nome, cpf, senha);
                    
                    request.setAttribute("message", message);

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
                        request.setAttribute("error", 1);
                        request.setAttribute("message", message);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/formAdministrador.jsp");
                        rd.forward(request, response);
                        }
                    }
                    break;
                    
                case "update":
                        Administrador administradorr = new Administrador();
                    
                        administradorr.setId(Integer.parseInt(request.getParameter("id")));
                        administradorr.setNome(request.getParameter("nome"));
                        administradorr.setCpf(request.getParameter("cpf"));
                        administradorr.setSenha(request.getParameter("senha"));
                        administradorDAO.update(administradorr);
            
                        request.setAttribute("administrador", administradorr);
                        RequestDispatcher list = getServletContext().getRequestDispatcher("/cadastraAdministradores.jsp");
                        list.forward(request, response);

                    break;
            }
    }
}


