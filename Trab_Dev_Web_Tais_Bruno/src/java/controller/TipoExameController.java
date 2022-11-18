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
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Exames;
import models.Consulta;

@WebServlet(name = "TipoExameController", urlPatterns = {"/TipoExameController"})
public class TipoExameController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        TipoExameDAO tipoexameDAO = new TipoExameDAO();
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            ExameDAO exameDAO = new ExameDAO();
            Exames exame = new Exames();
            request.setCharacterEncoding("UTF-8");
            
        }
    }

