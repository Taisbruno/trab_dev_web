package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Paciente;
import models.Medico;
import models.Administrador;
import dao.PacienteDAO;
import dao.AdministradorDAO;
import dao.MedicoDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String action = (String) request.getParameter("action");
        
        switch (action) {
            case "loginpaciente":
                if (session.getAttribute("paciente") != null) {
                    Paciente paciente = (Paciente) session.getAttribute("paciente");
                    session.setAttribute("paciente", paciente);
                    RequestDispatcher loginpaciente = getServletContext().getRequestDispatcher("/AreaPaciente.jsp");
                    loginpaciente.forward(request, response);
                    
                }
                RequestDispatcher loginpaciente = getServletContext().getRequestDispatcher("/loginPaciente.jsp");
                loginpaciente.forward(request, response);
                
                break;
            
            case "loginmedico":
                if (session.getAttribute("medico") != null) {
                    Medico medico = (Medico) session.getAttribute("medico");
                    session.setAttribute("medico", medico);
                    RequestDispatcher loginmedico = getServletContext().getRequestDispatcher("/AreaMedico.jsp");
                    loginmedico.forward(request, response);
                    
                } 
                RequestDispatcher loginmedico = getServletContext().getRequestDispatcher("/loginMedico.jsp");
                loginmedico.forward(request, response);
                
                break;
                
            case "loginadmin":
                if (session.getAttribute("administrador") != null) {
                    Administrador administrador = (Administrador) session.getAttribute("administrador");
                    session.setAttribute("administrador", administrador);
                    RequestDispatcher loginadmin = getServletContext().getRequestDispatcher("/AreaAdministrador.jsp");
                    loginadmin.forward(request, response);
                }
                    
                RequestDispatcher loginadmin = getServletContext().getRequestDispatcher("/loginAdmin.jsp");
                loginadmin.forward(request, response);
                
                break;
                
            case "logout":
                session.invalidate();
                RequestDispatcher logout = getServletContext().getRequestDispatcher("/index.jsp");
                logout.forward(request, response);
                
                break;
                
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String message = "";
        
        String action = (String) request.getParameter("action");

        switch (action) {
            case "loginpaciente":
                try {
                    PacienteDAO pacienteDAO = null;
            try {
                pacienteDAO = new PacienteDAO();
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
                    String cpf = request.getParameter("cpf");
                    String senha = request.getParameter("senha");
                    
                    Paciente paciente = pacienteDAO.login(cpf, senha);
                    
                    if (paciente.getCpf() != null && paciente.getAutorizado().equals("S")) {
                        HttpSession session = request.getSession();
                        session.setAttribute("paciente", paciente);
                        RequestDispatcher login = getServletContext().getRequestDispatcher("/AreaPaciente.jsp");
                        login.forward(request, response);
                    
                    } else {
                        message = "Paciente Não Encontrado ou Não Autorizado ou Credenciais Inseridas Inválidas";
                        request.setAttribute("error", 1);  
                        request.setAttribute("message", message);
                        RequestDispatcher loginFailed = getServletContext().getRequestDispatcher("/login.jsp");
                        loginFailed.forward(request, response);
                    
                    }               
                } catch(IOException | NumberFormatException | ServletException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            
            case "loginmedico":
                try {
                    MedicoDAO medicoDAO = new MedicoDAO();
                    String cpf = request.getParameter("cpf");
                    String senha = request.getParameter("senha");
                    
                    Medico medico = medicoDAO.login(cpf, senha);
                    
                    if (medico.getCpf() != null && medico.getAutorizado().equals("S")) {
                        HttpSession session = request.getSession();
                        session.setAttribute("medico", medico);
                        RequestDispatcher login = getServletContext().getRequestDispatcher("/AreaMedico.jsp");
                        login.forward(request, response);
                    
                    } else {
                        message = "Médico Não Encontrado ou Não Autorizado ou Credenciais Inseridas Inválidas";
                        request.setAttribute("error", 1);  
                        request.setAttribute("message", message);
                        RequestDispatcher loginFailed = getServletContext().getRequestDispatcher("/login.jsp");
                        loginFailed.forward(request, response);
                    }
                } catch(IOException | NumberFormatException | ServletException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            
                break;
            
            case "loginadmin": 
                try {
                    AdministradorDAO administradorDAO = new AdministradorDAO();
                    String cpf = request.getParameter("cpf");
                    String senha = request.getParameter("senha");
                    
                    Administrador administrador = administradorDAO.login(cpf, senha);
                    
                    if (administrador.getCpf() != null) {
                        HttpSession session = request.getSession();
                        session.setAttribute("administrador", administrador);
                        RequestDispatcher login = getServletContext().getRequestDispatcher("/AreaAdministrador.jsp");
                        login.forward(request, response);
                    
                    } else {
                        message = "Administrador Não Encontrado ou Não Autorizado ou Credenciais Inseridas Inválidas";
                        request.setAttribute("error", 1);  
                        request.setAttribute("message", message);
                        RequestDispatcher loginFailed = getServletContext().getRequestDispatcher("/login.jsp");
                        loginFailed.forward(request, response);
                    
                    }
                } catch(IOException | NumberFormatException | ServletException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                
                break; 
        }
    }
}