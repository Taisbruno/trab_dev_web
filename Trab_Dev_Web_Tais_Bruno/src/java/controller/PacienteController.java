package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Paciente;
import dao.PacienteDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import utils.CPF;

@WebServlet(name = "PacienteController", urlPatterns = {"/PacienteController"})
public class PacienteController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            PacienteDAO pacienteDAO = new PacienteDAO();
            ArrayList<Paciente> pacientes;
            Paciente paciente = new Paciente();
            int pacienteId;
            
            String action = (String) request.getParameter("action");
            
            switch (action) {
                case "all":
                    pacientes = pacienteDAO.getAll();
                    request.setAttribute("pacientes", pacientes);
                    RequestDispatcher list = getServletContext().getRequestDispatcher("/formEditarConsulta.jsp");
                    list.forward(request, response);
                    
                    break;
                case "insert":
                    paciente.setId(0);
                    paciente.setNome("");
                    paciente.setCpf("");
                    paciente.setSenha("");
                    paciente.setAutorizado("");
                    paciente.setIdTipoPlano(0);
                    
                    request.setAttribute("paciente", paciente);
                    RequestDispatcher insert = getServletContext().getRequestDispatcher("/formPaciente.jsp");
                    insert.forward(request, response);
                    
                    break;
                case "update":
                    pacienteId = Integer.parseInt(request.getParameter("id"));
                    paciente = pacienteDAO.get(pacienteId);
                    
                    request.setAttribute("paciente", paciente);
                    RequestDispatcher update = getServletContext().getRequestDispatcher("/updateUsuario.jsp");
                    update.forward(request, response);
                    
                    break;   
            }
        } catch (SQLException ex) {
            Logger.getLogger(PacienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PacienteDAO pacienteDAO = null;
        try {
            pacienteDAO = new PacienteDAO();
        } catch (SQLException ex) {
            Logger.getLogger(PacienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setCharacterEncoding("UTF-8");
        
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String senha = request.getParameter("senha");
        String autorizado = request.getParameter("autorizado");
        String idtipoplano = request.getParameter("idtipoplano");
        Paciente paciente = new Paciente(nome, cpf, senha, autorizado, Integer.parseInt(idtipoplano));
        
        try {
            pacienteDAO.insert(paciente);
        } catch (Exception e) {
            throw new RuntimeException("Falha");
        } finally {
        
        ArrayList<Paciente> pacientes;
        pacientes = pacienteDAO.getAll();
            
        request.setAttribute("pacientes", pacientes);
        RequestDispatcher list = getServletContext().getRequestDispatcher("/formEditarConsulta.jsp");
        list.forward(request, response);
        }

        if (paciente != null) {
            HttpSession session = request.getSession();
            session.setAttribute("paciente", paciente);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/AreaPaciente.jsp");
            rd.forward(request, response);
            return;

        } else {
            request.setAttribute("msgError", "Erro");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/formPaciente.jsp");
            rd.forward(request, response);
            return;
        }
          
    }
}


