package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.ConsultaDAO;
import dao.ExameDAO;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import models.Exames;
import models.Consulta;

@WebServlet(name = "ExameController", urlPatterns = {"/ExameController"})
public class ExameController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ExameDAO exameDAO = new ExameDAO();
        ArrayList<Exames> exames;
        Exames exame = new Exames();
        int exameId;
        
        String action = (String) request.getParameter("action");
        
        switch (action) {
            case "get":
                exameId = Integer.parseInt(request.getParameter("idconsulta"));
                exames = exameDAO.getByConsulta(exameId);
                request.setAttribute("exames", exames);
                RequestDispatcher get = getServletContext().getRequestDispatcher("/exames.jsp");
                get.forward(request, response);
                
                break;
                
            case "all":
                exames = exameDAO.getAll();
                request.setAttribute("exames", exames);
                RequestDispatcher list = getServletContext().getRequestDispatcher("/formEditarConsulta.jsp");
                list.forward(request, response);
                
                break;
                
            case "insert":
                exame.setId(0);
                exame.setIdConsulta(0);
                exame.setIdTipoExame(0);
                
                request.setAttribute("exame", exame);
                RequestDispatcher insert = getServletContext().getRequestDispatcher("/formRealizarConsulta.jsp");
                insert.forward(request, response);
                
                break;
                
            case "update":
                exameId = Integer.parseInt(request.getParameter("id"));
                exame = exameDAO.get(exameId);
                
                request.setAttribute("exame", exame);
                RequestDispatcher update = getServletContext().getRequestDispatcher("/formRealizarConsulta.jsp");
                update.forward(request, response);
                
                break;
                
        }       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ExameDAO exameDAO = new ExameDAO();
        Exames exame = new Exames();
        request.setCharacterEncoding("UTF-8");
           
            exame.setId(Integer.parseInt(request.getParameter("id")));
            exame.setIdConsulta(Integer.parseInt(request.getParameter("idconsulta")));
            exame.setIdTipoExame(Integer.parseInt(request.getParameter("idtipoexame")));
            exameDAO.update(exame);
            
            ArrayList<Exames> exames;
            ConsultaDAO consultaDAO = new ConsultaDAO();
            Consulta consulta = consultaDAO.get(Integer.parseInt(request.getParameter("idconsulta")));
            exames = exameDAO.getByConsulta(consulta.getId());
            
            request.setAttribute("exames", exames);
            RequestDispatcher list = getServletContext().getRequestDispatcher("/AreaMedico.jsp");
            list.forward(request, response);
            
        }
    }

