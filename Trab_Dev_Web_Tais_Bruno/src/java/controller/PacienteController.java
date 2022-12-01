package controller;

import dao.ConsultaDAO;
import dao.ExameDAO;
import java.io.IOException;
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
import models.Consulta;
import models.Exames;

@WebServlet(name = "PacienteController", urlPatterns = {"/PacienteController"})
public class PacienteController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            PacienteDAO pacienteDAO = new PacienteDAO();
            Paciente paciente = new Paciente();
            ConsultaDAO consultaDAO = new ConsultaDAO();
            ExameDAO exameDAO = new ExameDAO();
            ArrayList<Paciente> pacientes;
            int pacienteId;
            
            String action = (String) request.getParameter("action");
            
            switch (action) {
                
                case "get":
                    pacienteId = Integer.parseInt(request.getParameter("id"));
                    paciente = pacienteDAO.get(pacienteId);
                    
                    request.setAttribute("paciente", paciente);
                    RequestDispatcher update = getServletContext().getRequestDispatcher("/visualizarPaciente.jsp");       
                    update.forward(request, response);
                    
                    break;
                    
                case "delete":
                    pacienteId = Integer.parseInt(request.getParameter("id"));
                    ArrayList<Consulta> consultas = consultaDAO.getByPaciente(pacienteId);
                        for (Consulta consultaa : consultas) {
                            ArrayList<Exames> exames = exameDAO.getByConsulta(consultaa.getId());
                            for (Exames examee : exames) {
                                exameDAO.delete(examee.getId());
                            }
                            consultaDAO.delete(consultaa.getId());
                        }
                        pacienteDAO.delete(pacienteId);

                        pacientes = pacienteDAO.getAll();

                        request.setAttribute("pacientes", pacientes);
                        RequestDispatcher delete = getServletContext().getRequestDispatcher("/cadastraPacientes.jsp");
                        delete.forward(request, response);
                
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
            throw new RuntimeException("Falha no cadastro do paciente");
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