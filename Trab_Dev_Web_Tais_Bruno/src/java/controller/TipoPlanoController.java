package controller;

import dao.ConsultaDAO;
import dao.ExameDAO;
import dao.PacienteDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.TipoPlano;
import dao.TipoPlanoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import models.Consulta;
import models.Exames;
import models.Paciente;

@WebServlet(name = "TipoPlanoController", urlPatterns = {"/TipoPlanoController"})
public class TipoPlanoController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            TipoPlanoDAO tipoplanoDAO = new TipoPlanoDAO();
            TipoPlano tipoplano = new TipoPlano();
            PacienteDAO pacienteDAO = new PacienteDAO();
            ConsultaDAO consultaDAO = new ConsultaDAO();
            ExameDAO exameDAO = new ExameDAO();
            ArrayList<TipoPlano> tipoplanos;
            int tipoplanoId;
            
            String action = (String) request.getParameter("action");
            
            switch (action) {
                
                case "get":
                    tipoplanoId = Integer.parseInt(request.getParameter("id"));
                    tipoplano = tipoplanoDAO.get(tipoplanoId);
                    
                    request.setAttribute("tipoplano", tipoplano);
                    RequestDispatcher get = getServletContext().getRequestDispatcher("/visualizarTipoPlano.jsp");   
                    get.forward(request, response);
                    
                    break;
                    
                case "delete":
                    tipoplanoId = Integer.parseInt(request.getParameter("id"));
                    ArrayList<Paciente> pacientes = pacienteDAO.getByTipoPlano(tipoplanoId);
                    for (Paciente pacientee : pacientes) {
                        ArrayList<Consulta> consultas = consultaDAO.getByPaciente(pacientee.getId());
                        for (Consulta consultaa : consultas) {
                            ArrayList<Exames> exames = exameDAO.getByConsulta(consultaa.getId());
                            for (Exames examee : exames) {
                                exameDAO.delete(examee.getId());
                            }
                            consultaDAO.delete(consultaa.getId());
                        }
                        pacienteDAO.delete(pacientee.getId());
                    }
                    tipoplanoDAO.delete(tipoplanoId);
                
                    tipoplanos = tipoplanoDAO.getAll();
                
                    request.setAttribute("tipoplanos", tipoplanos);
                    RequestDispatcher delete = getServletContext().getRequestDispatcher("/cadastraTipoPlano.jsp");
                    delete.forward(request, response);
                    
                    break;
                    
                case "update":
                    tipoplanoId = Integer.parseInt(request.getParameter("id"));
                    tipoplano = tipoplanoDAO.get(tipoplanoId);
                    
                    request.setAttribute("tipoplano", tipoplano);
                    RequestDispatcher update = getServletContext().getRequestDispatcher("/formEditarTipoPlano.jsp");
                    update.forward(request, response);       
                    
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TipoPlanoController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            TipoPlanoDAO tipoplanoDAO = new TipoPlanoDAO();
            request.setCharacterEncoding("UTF-8");
            String message = "";
        
            String action = (String) request.getParameter("action");
            
            switch (action) {
                
                case "insert":
                    try {
                        TipoPlano tipoplano = new TipoPlano();
                    if (request.getParameter("descricao").equals("")) {
                        message = "'Descrição' is empty";
                        request.setAttribute("error", 1);
                    }
                    if (message.equals("")) {
                        tipoplano.setDescricao(request.getParameter("descricao"));

                        if (tipoplanoDAO.insert(tipoplano)) {
                            request.setAttribute("error", 0);
                        } else {
                            message = "Não Efetivado";
                            request.setAttribute("error", 1);
                        }
                        } else {
                            message = "É obrigatório o preenchimento de todos os campos / Dados inseridos inválidos";
                            System.out.println(message);

                            request.setAttribute("message", message);
                            request.setAttribute("error", 1);
                            RequestDispatcher error = getServletContext().getRequestDispatcher("/formTipoPlano.jsp");
                            error.forward(request, response);
                        }
                        request.setAttribute("message", message);
                            
                        } catch (Exception e) {
                            message = "É obrigatório o preenchimento de todos os campos / Dados inseridos inválidos";
                            System.out.println(message);

                            request.setAttribute("message", message);
                            request.setAttribute("error", 1);
                            RequestDispatcher error = getServletContext().getRequestDispatcher("/formTipoPlano.jsp");
                            error.forward(request, response);
                        } finally {
                            ArrayList<TipoPlano> tipoplanos;
                            tipoplanos = tipoplanoDAO.getAll();

                            request.setAttribute("tipoplanos", tipoplanos);
                            RequestDispatcher list = getServletContext().getRequestDispatcher("/cadastraTipoPlano.jsp");
                            list.forward(request, response);
                        } 
                    
                        break;
                    
                case "update": 
                    try {
                        TipoPlano tipoplano = new TipoPlano();
                        if (request.getParameter("descricao").equals("")) {
                            message = "'Descrição' is empty";
                            request.setAttribute("error", 1);
                        }
                    
                        if (message.equals("")) {
                            tipoplano.setId(Integer.parseInt(request.getParameter("id")));
                            tipoplano.setDescricao(request.getParameter("descricao"));
                            
                            if (tipoplanoDAO.update(tipoplano)) {
                                request.setAttribute("error", 0);
                            } else {
                                message = "Não Efetivado";
                                request.setAttribute("error", 1);
                        }
                        } else {
                            message = "É obrigatório o preenchimento de todos os campos / Dados inseridos inválidos";
                            System.out.println(message);

                            request.setAttribute("message", message);
                            request.setAttribute("error", 1);
                            RequestDispatcher error = getServletContext().getRequestDispatcher("/formEditarTipoPlano.jsp");
                            error.forward(request, response);
                        }
                        request.setAttribute("message", message);
                        
                        } catch (Exception e) {
                            request.setAttribute("message", message);
                            request.setAttribute("error", 1);
                        } finally {
                            ArrayList<TipoPlano> tipoplanos;
                            tipoplanos = tipoplanoDAO.getAll();
                            request.setAttribute("tipoplanos", tipoplanos);
                            RequestDispatcher list = getServletContext().getRequestDispatcher("/cadastraTipoPlano.jsp");
                            list.forward(request, response);
                        }
                        break;
            }
        }
}


