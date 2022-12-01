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
        
        String descricao = request.getParameter("descricao");
        
        TipoPlano tipoplano = new TipoPlano(descricao);
        
        try {
            tipoplanoDAO.insert(tipoplano);
        } catch (Exception e) {
            throw new RuntimeException("Falha no cadastro do Tipo do Plano");
        } finally {
            ArrayList<TipoPlano> tipoplanos;
            tipoplanos = tipoplanoDAO.getAll();
            
        if (tipoplano != null) {
            request.setAttribute("tipoplanos", tipoplanos);
            RequestDispatcher list = getServletContext().getRequestDispatcher("/AreaAdministrador.jsp");
            list.forward(request, response);

        } else {
            request.setAttribute("msgError", "Erro");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/formTipoPlano.jsp");
            rd.forward(request, response);
        }
        }        
    }
}


