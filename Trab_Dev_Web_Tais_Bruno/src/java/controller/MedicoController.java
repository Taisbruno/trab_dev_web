package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Medico;
import dao.MedicoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import utils.CPF;

@WebServlet(name = "MedicoController", urlPatterns = {"/MedicoController"})
public class MedicoController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        MedicoDAO medicoDAO = new MedicoDAO();
        ArrayList<Medico> medicos;
        Medico medico = new Medico();
        int medicoId;
        
        String action = (String) request.getParameter("action");
        
        switch (action) {
            case "all":
                medicos = medicoDAO.getAll();
                request.setAttribute("medicos", medicos);
                RequestDispatcher list = getServletContext().getRequestDispatcher("/formEditarConsulta.jsp");
                RequestDispatcher listt = getServletContext().getRequestDispatcher("/formRealizarConsulta.jsp");
                list.forward(request, response);
                listt.forward(request, response);
                
                break;
            case "insert":
                medico.setId(0);
                medico.setNome("");
                medico.setCpf("");
                medico.setSenha("");
                
                request.setAttribute("medico", medico);
                RequestDispatcher insert = getServletContext().getRequestDispatcher("/insertUsuario.jsp");
                insert.forward(request, response);
                
                break;
            case "update":
                medicoId = Integer.parseInt(request.getParameter("id"));
                medico = medicoDAO.get(medicoId);
                
                request.setAttribute("medico", medico);
                RequestDispatcher update = getServletContext().getRequestDispatcher("/updateMedico.jsp");
                update.forward(request, response);
                
                break;
        }       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        MedicoDAO medicoDAO = new MedicoDAO();
        request.setCharacterEncoding("UTF-8");
        String message = "";
        
        try {
            Medico medico = new Medico();
            if (request.getParameter("nome").equals("")) {
                message = "'Nome' is empty";
                request.setAttribute("error", 1);
            }
            if (request.getParameter("cpf").equals("") || !CPF.IsCPF(request.getParameter("cpf"))) {
                message = "'CPF' is empty or is invalid";
                request.setAttribute("error", 1);
            }
            if (request.getParameter("senha").equals("")) {
                message = "'Senha' is empty";
                request.setAttribute("error", 1);
            }
            if (request.getParameter("suspenso").equals("")) {
                message = "'Suspenso' is empty";
                request.setAttribute("error", 1);
            }
            
            if (message.equals("")) {
                medico.setId(Integer.parseInt(request.getParameter("id")));
                medico.setNome(request.getParameter("nome"));
                medico.setCpf(request.getParameter("cpf"));
                medico.setSenha(request.getParameter("senha"));

            }
            
            request.setAttribute("message", message);
            
        } catch(NumberFormatException e) {
            message = "Error: " + e.getMessage();
            System.out.println(message);
            
            request.setAttribute("message", message);
            request.setAttribute("error", 1);
            
        } finally {
            ArrayList<Medico> medicos;
            medicos = medicoDAO.getAll();
            
            request.setAttribute("medicos", medicos);
            RequestDispatcher list = getServletContext().getRequestDispatcher("/AreaMedico.jsp");
            list.forward(request, response);
        }
    }
}