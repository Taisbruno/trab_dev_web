package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Medico;
import dao.MedicoDAO;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "MedicoController", urlPatterns = {"/MedicoController"})
public class MedicoController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            MedicoDAO medicoDAO = new MedicoDAO();
           
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        MedicoDAO medicoDAO = new MedicoDAO();
        request.setCharacterEncoding("UTF-8");
        
        String nome = request.getParameter("nome");
        int crm = Integer.parseInt(request.getParameter("crm"));
        String estadocrm = request.getParameter("estadocrm");
        String cpf = request.getParameter("cpf");
        String senha = request.getParameter("senha");
        String autorizado = request.getParameter("autorizado");
        int idespecialidade = Integer.parseInt(request.getParameter("idespecialidade"));
        Medico medico = new Medico(nome, crm, estadocrm, cpf, senha, autorizado, idespecialidade);
        
        try {
            medicoDAO.insert(medico);
        } catch (Exception e) {
            throw new RuntimeException("Falha no cadastro do médico");
        } finally {
            ArrayList<Medico> medicos;
            medicos = medicoDAO.getAll();
            
        if (medico != null) {
            request.setAttribute("medicos", medicos);
            RequestDispatcher list = getServletContext().getRequestDispatcher("/AreaAdministrador.jsp");
            list.forward(request, response);

        } else {
            request.setAttribute("msgError", "Erro");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/formMedico.jsp");
            rd.forward(request, response);
        }
        }        
    }
}


