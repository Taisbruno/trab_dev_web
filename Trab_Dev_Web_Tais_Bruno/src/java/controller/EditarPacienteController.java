package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.PacienteDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Paciente;

@WebServlet(name = "EditarPacienteController", urlPatterns = {"/EditarPacienteController"})
public class EditarPacienteController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            PacienteDAO pacienteDAO = new PacienteDAO();
            Paciente paciente = new Paciente();
            int pacienteId;
            
            String action = (String) request.getParameter("action");
            
            switch (action) {
                
                case "update":
                    pacienteId = Integer.parseInt(request.getParameter("id"));
                    paciente = pacienteDAO.get(pacienteId);
                    
                    request.setAttribute("paciente", paciente);
                    RequestDispatcher update = getServletContext().getRequestDispatcher("/formEditarPaciente.jsp");
                    update.forward(request, response);       
                    
                    break;
                    
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditarPacienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            PacienteDAO pacienteDAO = new PacienteDAO();
            Paciente paciente = new Paciente();
            request.setCharacterEncoding("UTF-8");
           
            paciente.setId(Integer.parseInt(request.getParameter("id")));
            paciente.setNome(request.getParameter("nome"));
            paciente.setCpf(request.getParameter("cpf"));
            paciente.setSenha(request.getParameter("senha"));
            paciente.setAutorizado(request.getParameter("autorizado"));
            paciente.setIdTipoPlano(Integer.parseInt(request.getParameter("idtipoplano")));
            pacienteDAO.update(paciente);
            
            request.setAttribute("paciente", paciente);
            RequestDispatcher list = getServletContext().getRequestDispatcher("/cadastraPacientes.jsp");
            list.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(EditarPacienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        }
    }
