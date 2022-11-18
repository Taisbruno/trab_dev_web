package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Especialidade;
import dao.EspecialidadeDAO;

@WebServlet(name = "EspecialidadeController", urlPatterns = {"/EspecialidadeController"})
public class EspecialidadeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
        
    }       

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
            request.setCharacterEncoding("UTF-8");
        
    }
}