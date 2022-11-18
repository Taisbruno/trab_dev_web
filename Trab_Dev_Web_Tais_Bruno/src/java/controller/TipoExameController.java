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
import dao.TipoExameDAO;
import dao.ExameDAO;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import models.Exames;
import models.TipoExame;
import models.Consulta;

@WebServlet(name = "TipoExameController", urlPatterns = {"/TipoExameController"})
public class TipoExameController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        TipoExameDAO tipoexameDAO = new TipoExameDAO();
        ArrayList<TipoExame> tipoexames;
        TipoExame tipoexame = new TipoExame();
        int tipoexameId;
        
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

