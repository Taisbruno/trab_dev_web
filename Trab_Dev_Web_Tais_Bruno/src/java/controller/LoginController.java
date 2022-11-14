package controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import utils.CPF;

@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String action = (String) request.getParameter("action");
        
        switch (action) {
            case "login":
                if (session.getAttribute("paciente") != null) {
                    Paciente paciente = (Paciente) session.getAttribute("paciente");
                    session.setAttribute("paciente", paciente);
                    RequestDispatcher login = getServletContext().getRequestDispatcher("/AreaPaciente.jsp");
                    login.forward(request, response);
                    
                } else if (session.getAttribute("administrador") != null) {
                    Administrador administrador = (Administrador) session.getAttribute("administrador");
                    session.setAttribute("administrador", administrador);
                    RequestDispatcher login = getServletContext().getRequestDispatcher("/AreaAdministrador.jsp");
                    login.forward(request, response);
                    
                } else if (session.getAttribute("medico") != null) {
                    Medico medico = (Medico) session.getAttribute("medico");
                    session.setAttribute("medico", medico);
                    RequestDispatcher login = getServletContext().getRequestDispatcher("/AreaMedico.jsp");
                    login.forward(request, response);
                    
                } 
                RequestDispatcher login = getServletContext().getRequestDispatcher("/login.jsp");
                login.forward(request, response);
                
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
        
        try {
            PacienteDAO pacienteDAO = new PacienteDAO();
            AdministradorDAO administradorDAO = new AdministradorDAO();
            MedicoDAO medicoDAO = new MedicoDAO();
            
            if (request.getParameter("cpf").equals("") || !CPF.IsCPF(request.getParameter("cpf"))) {
                message = "'CPF' is empty or is invalid";
                request.setAttribute("error", 1);
            }
            if (request.getParameter("senha").equals("")) {
                message = "'Senha' is empty";
                request.setAttribute("error", 1);
            }
            
            if (message.equals("")) {

                String cpf = request.getParameter("cpf");
                String senha = request.getParameter("senha");
                
                Paciente paciente = pacienteDAO.login(cpf, senha);
                Administrador administrador = administradorDAO.login(cpf, senha);
                Medico medico = medicoDAO.login(cpf, senha);
                
                if (paciente.getCpf() != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("paciente", paciente);
                    RequestDispatcher login = getServletContext().getRequestDispatcher("/AreaPaciente.jsp");
                    login.forward(request, response);
                    
                } else if (administrador.getCpf() != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("administrador", administrador);
                    RequestDispatcher login = getServletContext().getRequestDispatcher("/AreaAdministrador.jsp");
                    login.forward(request, response);
                    
                } else if (medico.getCpf() != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("medico", medico);
                    RequestDispatcher login = getServletContext().getRequestDispatcher("/AreaMedico.jsp");
                    login.forward(request, response);
                    
                } else {
                    message = "Paciente/Medico/Administrador Não Encontrado";
                    request.setAttribute("error", 1);  
                    request.setAttribute("message", message);
                    RequestDispatcher loginFailed = getServletContext().getRequestDispatcher("/login.jsp");
                    loginFailed.forward(request, response);
                    
                }
                
            }
            
        } catch(IOException | NumberFormatException | ServletException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}