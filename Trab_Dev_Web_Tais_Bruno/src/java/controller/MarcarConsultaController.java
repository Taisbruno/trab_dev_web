package controller;

import dao.ConsultaDAO;
import dao.MedicoDAO;
import dao.PacienteDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Medico;
import models.Paciente;
import models.Especialidade;
import models.Consulta;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import utils.Date;


@WebServlet(urlPatterns = {"/MarcarConsultaController"})
public class MarcarConsultaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        MedicoDAO medicoDAO = new MedicoDAO();
        ArrayList<Medico> listamedicos = medicoDAO.getAll();
        request.setAttribute("listamedicos", listamedicos);
        RequestDispatcher rd = request.getRequestDispatcher("/formConsulta.jsp");
        rd.forward(request, response);
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
        String dataInteira = data + " " + hora;
        System.out.println(dataInteira);
        System.out.println(descricao);
        System.out.println(realizada);
        System.out.println(idmedico);
        System.out.println(idpaciente);
        Consulta consulta = new Consulta(dataInteira, descricao, realizada, Integer.parseInt(idmedico), Integer.parseInt(idpaciente));
        ConsultaDAO consultaDAO = new ConsultaDAO();
        
        try {
            consultaDAO.insert(consulta);
        } catch (Exception ex) {
            throw new RuntimeException("Falha");
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