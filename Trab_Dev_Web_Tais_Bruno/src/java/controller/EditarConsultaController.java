package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Consulta;
import dao.ConsultaDAO;
import dao.MedicoDAO;
import models.Medico;
import utils.Date;

@WebServlet(name = "EditarConsultaController", urlPatterns = {"/EditarConsultaController"})
public class EditarConsultaController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ConsultaDAO consultaDAO = new ConsultaDAO();
        Consulta consulta = new Consulta();
        int consultaId;
        
        String action = (String) request.getParameter("action");
        
        switch (action) {
    
            case "update":
                consultaId = Integer.parseInt(request.getParameter("id"));
                consulta = consultaDAO.get(consultaId);
                
                request.setAttribute("consultasMedico", consulta);
                RequestDispatcher update = getServletContext().getRequestDispatcher("/formEditarConsulta.jsp");
                update.forward(request, response);
                
                break;
                
        }       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            ConsultaDAO consultaDAO = new ConsultaDAO();
            Consulta consulta = new Consulta();
            request.setCharacterEncoding("UTF-8");
           
            consulta.setId(Integer.parseInt(request.getParameter("id")));
            consulta.setData(Date.format(request.getParameter("data"), "backToDatabase"));
            consulta.setDescricao(request.getParameter("descricao"));
            consulta.setRealizada(request.getParameter("realizada"));
            consulta.setIdMedico(Integer.parseInt(request.getParameter("idmedico")));
            consulta.setIdPaciente(Integer.parseInt(request.getParameter("idpaciente")));
            consultaDAO.update(consulta);
            
            ArrayList<Consulta> consultasmedico;
            MedicoDAO medicoDAO = new MedicoDAO();
            Medico medico = medicoDAO.get(Integer.parseInt(request.getParameter("idmedico")));
            consultasmedico = consultaDAO.getByMedico(medico.getId());
            
            request.setAttribute("consultasMedico", consultasmedico);
            RequestDispatcher list = getServletContext().getRequestDispatcher("/AreaMedico.jsp");
            list.forward(request, response);
            
        }
    }
