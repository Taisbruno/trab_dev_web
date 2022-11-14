package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Consulta;
import dao.ConsultaDAO;
import dao.PacienteDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Paciente;
import models.Medico;
import utils.Date;

@WebServlet(name = "ListaConsultaPacienteController", urlPatterns = {"/ListaConsultaPacienteController"})
public class ListaConsultaPacienteController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ConsultaDAO consultaDAO = new ConsultaDAO();
        ArrayList<Consulta> consultas;
        Consulta consulta = new Consulta();
        int consultaId;
        
        String action = (String) request.getParameter("action");
        
        switch (action) {
            case "get":
                consultaId = Integer.parseInt(request.getParameter("id"));
                consultas = consultaDAO.getByPaciente(consultaId);
                request.setAttribute("consultas", consultas);
                RequestDispatcher get = getServletContext().getRequestDispatcher("/listaConsultasPaciente.jsp");
                get.forward(request, response);
                
                break;
                
            case "all":
                consultas = consultaDAO.getAll();
                request.setAttribute("consultas", consultas);
                RequestDispatcher list = getServletContext().getRequestDispatcher("/listaConsultasPaciente.jsp");
                list.forward(request, response);
                
                break;
                
            case "insert":
                consulta.setId(0);
                consulta.setData("");
                consulta.setDescricao("");
                consulta.setRealizada("");
                consulta.setIdMedico(0);
                consulta.setIdPaciente(0);
                
                request.setAttribute("consulta", consulta);
                RequestDispatcher insert = getServletContext().getRequestDispatcher("/formConsulta.jsp");
                insert.forward(request, response);
                
                break;
        }       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ConsultaDAO consultaDAO = new ConsultaDAO();
        request.setCharacterEncoding("UTF-8");
        String message = "";
        
        try {
            Consulta consulta = new Consulta();
            if (request.getParameter("data") == null) {
                message = "'Data' is empty";
                request.setAttribute("error", 1);
            }
            if (request.getParameter("descricao") == null) {
                message = "'Descricao' is empty";
                request.setAttribute("error", 1);
            }
            if (request.getParameter("realizada").equals("")) {
                message = "'Realizada' is empty";
                request.setAttribute("error", 1);
            }
            if (request.getParameter("idpaciente").equals("")) {
                message = "'IdPaciente' is empty";
                request.setAttribute("error", 1);
            }
            if (request.getParameter("idmedico").equals("")) {
                message = "'IdMedico' is empty";
                request.setAttribute("error", 1);
            }
            
            if (message.equals("")) {
                consulta.setId(Integer.parseInt(request.getParameter("id")));
                consulta.setData(Date.format(request.getParameter("data"), "database"));
                consulta.setDescricao(request.getParameter("descricao"));
                consulta.setRealizada(request.getParameter("realizada"));
                consulta.setIdMedico(Integer.parseInt(request.getParameter("idmedico")));
                consulta.setIdPaciente(Integer.parseInt(request.getParameter("idpaciente")));

                if (consulta.getId() == 0) {
                    if (consultaDAO.insert(consulta)) {
                        message = "Nova Consulta!";
                        request.setAttribute("error", 0);
                    } else {
                        message = "Não Efetivado";
                        request.setAttribute("error", 1);
                    }
                } 
            }
            
            request.setAttribute("message", message);
            
        } catch(NumberFormatException e) {
            message = "Error: " + e.getMessage();
            System.out.println(message);
            
            request.setAttribute("message", message);
            request.setAttribute("error", 1);
            
        } finally {
            ArrayList<Consulta> consultas;
            
            PacienteDAO pacienteDAO = null;
            try {
                pacienteDAO = new PacienteDAO();
            } catch (SQLException ex) {
                Logger.getLogger(ListaConsultaPacienteController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Paciente paciente = pacienteDAO.get(Integer.parseInt(request.getParameter("id")));
            consultas = consultaDAO.getByPaciente(paciente.getId());
            
            request.setAttribute("consultas", consultas);
            RequestDispatcher list = getServletContext().getRequestDispatcher("/listaConsultasPaciente.jsp");
            list.forward(request, response);
            
        }
    }
}
