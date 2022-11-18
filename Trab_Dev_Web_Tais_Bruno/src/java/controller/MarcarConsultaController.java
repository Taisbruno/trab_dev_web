package controller;

import dao.ConsultaDAO;
import dao.MedicoDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Consulta;


@WebServlet(urlPatterns = {"/MarcarConsultaController"})
public class MarcarConsultaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        MedicoDAO medicoDAO = new MedicoDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String data = request.getParameter("data");
        String hora = request.getParameter("hora");
        String descricao = request.getParameter("descricao");
        String realizada = request.getParameter("realizada");
        String idmedico = request.getParameter("idmedico");
        String idpaciente = request.getParameter("idpaciente");
        String dataCompleta = data + " " + hora;
        Consulta consulta = new Consulta(dataCompleta, descricao, realizada, Integer.parseInt(idmedico), Integer.parseInt(idpaciente));
        ConsultaDAO consultaDAO = new ConsultaDAO();
        
        try {
            consultaDAO.insert(consulta);
        } catch (Exception ex) {
            throw new RuntimeException("Falha na marcação da consulta");
        }
        
        if (consulta != null) {
            HttpSession session = request.getSession();
            session.setAttribute("consulta", consulta);
            RequestDispatcher rd = request.getRequestDispatcher("/AreaPaciente.jsp");
            rd.forward(request, response);

        } else {
            request.setAttribute("msgError", "Erro.");
            RequestDispatcher rd = request.getRequestDispatcher("/formConsulta.jsp");
            rd.forward(request, response);
        }
    }

}