package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Especialidade;
import dao.EspecialidadeDAO;
import dao.MedicoDAO;
import dao.ConsultaDAO;
import dao.ExameDAO;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import models.Consulta;
import models.Exames;
import models.Medico;

@WebServlet(name = "EspecialidadeController", urlPatterns = {"/EspecialidadeController"})
public class EspecialidadeController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
            MedicoDAO medicoDAO = new MedicoDAO();
            ConsultaDAO consultaDAO = new ConsultaDAO();
            ExameDAO exameDAO = new ExameDAO();
            Especialidade especialidade = new Especialidade();
            ArrayList<Especialidade> especialidades;
            int especialidadeId;
            
            String action = (String) request.getParameter("action");
            
            switch (action) {
                
                case "get":
                    especialidadeId = Integer.parseInt(request.getParameter("id"));
                    especialidade = especialidadeDAO.get(especialidadeId);
                    
                    request.setAttribute("especialidade", especialidade);
                    RequestDispatcher get = getServletContext().getRequestDispatcher("/visualizarEspecialidade.jsp");
                    get.forward(request, response);
                    
                    break;
                    
                case "delete":
                    especialidadeId = Integer.parseInt(request.getParameter("id"));
                    ArrayList<Medico> medicos = medicoDAO.getByEspecialidade(especialidadeId);
                    for (Medico medicoo : medicos) {
                        ArrayList<Consulta> consultas = consultaDAO.getByMedico(medicoo.getId());
                        for (Consulta consultaa : consultas) {
                            ArrayList<Exames> exames = exameDAO.getByConsulta(consultaa.getId());
                            for (Exames examee : exames) {
                                exameDAO.delete(examee.getId());
                            }
                            consultaDAO.delete(consultaa.getId());
                    }
                        medicoDAO.delete(medicoo.getId());
                    }
                    especialidadeDAO.delete(especialidadeId);
                
                    especialidades = especialidadeDAO.getAll();
                
                    request.setAttribute("especialidades", especialidades);
                    RequestDispatcher delete = getServletContext().getRequestDispatcher("/cadastraEspecialidades.jsp");
                    delete.forward(request, response);
                
                break;
            }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
            request.setCharacterEncoding("UTF-8");

            String descricao = request.getParameter("descricao");

            Especialidade especialidade = new Especialidade(descricao);

            try {
                especialidadeDAO.insert(especialidade);
            } catch (Exception e) {
                throw new RuntimeException("Falha no cadastro da Especialidade");
            } finally {
                ArrayList<Especialidade> especialidades;
                especialidades = especialidadeDAO.getAll();

            if (especialidade != null) {
                request.setAttribute("especialidades", especialidades);
                RequestDispatcher list = getServletContext().getRequestDispatcher("/AreaAdministrador.jsp");
                list.forward(request, response);

            } else {
                request.setAttribute("msgError", "Erro");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/formEspecialidade.jsp");
                rd.forward(request, response);
            }
            } 
    }
}


