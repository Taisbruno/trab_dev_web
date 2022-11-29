package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.MedicoDAO;
import models.Medico;

@WebServlet(name = "EditarMedicoController", urlPatterns = {"/EditarMedicoController"})
public class EditarMedicoController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            MedicoDAO medicoDAO = new MedicoDAO();
            Medico medico = new Medico();
            int medicoId;
            
            String action = (String) request.getParameter("action");
            
            switch (action) {
                
                case "update":
                    medicoId = Integer.parseInt(request.getParameter("id"));
                    medico = medicoDAO.get(medicoId);
                    
                    request.setAttribute("medico", medico);
                    RequestDispatcher update = getServletContext().getRequestDispatcher("/formEditarMedico.jsp");
                    update.forward(request, response);       
                    
                    break;
                    
            }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            MedicoDAO medicoDAO = new MedicoDAO();
            Medico medico = new Medico();
            request.setCharacterEncoding("UTF-8");
           
            medico.setId(Integer.parseInt(request.getParameter("id")));
            medico.setNome(request.getParameter("nome"));
            medico.setCrm(Integer.parseInt(request.getParameter("crm")));
            medico.setEstadoCrm(request.getParameter("estadocrm"));
            medico.setCpf(request.getParameter("cpf"));
            medico.setSenha(request.getParameter("senha"));
            medico.setAutorizado(request.getParameter("autorizado"));
            medico.setIdEspecialidade(Integer.parseInt(request.getParameter("idespecialidade")));
            medicoDAO.update(medico);
            
            request.setAttribute("medico", medico);
            RequestDispatcher list = getServletContext().getRequestDispatcher("/cadastraMedicos.jsp");
            list.forward(request, response);
            
        }
    }
