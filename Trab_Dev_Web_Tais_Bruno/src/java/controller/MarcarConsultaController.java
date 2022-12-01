package controller;

import dao.ConsultaDAO;
import dao.MedicoDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Consulta;
import utils.Date;


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
        
            String message = "";
        
            String data = request.getParameter("data");
            String hora = request.getParameter("hora");
            String descricao = request.getParameter("descricao");
            String realizada = request.getParameter("realizada");
            String idmedico = request.getParameter("idmedico");
            String idpaciente = request.getParameter("idpaciente");
            String dataCompleta = data + " " + hora;
            
            String dataformated = Date.format(dataCompleta, "brazilonlyDate");
            
            Consulta consulta = new Consulta(dataCompleta, descricao, realizada, Integer.parseInt(idmedico), Integer.parseInt(idpaciente));
            ConsultaDAO consultaDAO = new ConsultaDAO();
            ArrayList<Consulta> consultas = consultaDAO.getAll();
            
            for (Consulta consultaa: consultas) {
                    if (dataformated.equals(consultaa.getData().split(" ")[0]) && (Integer.parseInt(idmedico) == consultaa.getIdMedico())) {
                        message = "Não é possível marcar consulta com o médico desejado na data informada. Escolha uma outra data que esteja disponível para marcação";
                        request.setAttribute("error", 1);  
                        request.setAttribute("message", message);
                        RequestDispatcher nope = request.getRequestDispatcher("/formConsulta.jsp");
                        nope.forward(request, response);
                        break;
                    } 
                }
            
            if (message.equals("")) {
                consultaDAO.insert(consulta);
                HttpSession session = request.getSession();
                session.setAttribute("consulta", consulta);
                RequestDispatcher done = request.getRequestDispatcher("/AreaPaciente.jsp");
                done.forward(request, response);
            }
    }

}