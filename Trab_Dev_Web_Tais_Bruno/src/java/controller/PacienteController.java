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
            throw new RuntimeException("Falha na inserção do paciente");
        } finally {
            ArrayList<Paciente> pacientes;
            pacientes = pacienteDAO.getAll();
            
        if (paciente != null) {
            request.setAttribute("pacientes", pacientes);
            RequestDispatcher list = getServletContext().getRequestDispatcher("/AreaPaciente.jsp");
            list.forward(request, response);

        } else {
            request.setAttribute("msgError", "Erro");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/formPaciente.jsp");
            rd.forward(request, response);
        }
        }        
    }
}


