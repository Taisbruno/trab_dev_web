package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Medico;
import dao.MedicoDAO;

@WebServlet(name = "VisualizarMedicoController", urlPatterns = {"/VisualizarMedicoController"})
public class VisualizarMedicoController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            MedicoDAO medicoDAO = new MedicoDAO();
            Medico medico = new Medico();
            int medicoId;
            
            String action = (String) request.getParameter("action");
            
            switch (action) {
                
                case "get":
                    medicoId = Integer.parseInt(request.getParameter("id"));
                    medico = medicoDAO.get(medicoId);
                    
                    request.setAttribute("medico", medico);
                    RequestDispatcher update = getServletContext().getRequestDispatcher("/visualizarMedico.jsp");       
                    update.forward(request, response);
                    
                    break;
            }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        }
    }
