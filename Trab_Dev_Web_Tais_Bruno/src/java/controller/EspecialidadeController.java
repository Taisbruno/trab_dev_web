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
                
                case "update":
                    especialidadeId = Integer.parseInt(request.getParameter("id"));
                    especialidade = especialidadeDAO.get(especialidadeId);
                    
                    request.setAttribute("especialidade", especialidade);
                    RequestDispatcher update = getServletContext().getRequestDispatcher("/formEditarEspecialidade.jsp");
                    update.forward(request, response);       
                    
                    break;
            }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
            request.setCharacterEncoding("UTF-8");
            String message = "";
            
            String action = (String) request.getParameter("action");
            
            switch (action) {
                
                case "insert":
                    try {
                        Especialidade especialidade = new Especialidade();
                        if (request.getParameter("descricao").equals("")) {
                            message = "'Descrição' is empty";
                            request.setAttribute("error", 1);
                        }
                        if (message.equals("")) {
                            especialidade.setDescricao(request.getParameter("descricao"));
                            
                            if (especialidadeDAO.insert(especialidade)) {
                                request.setAttribute("error", 0);
                            } else {
                                message = "Não Efetivado";
                                request.setAttribute("error", 1);
                            }
                            } else {
                            message = "É obrigatório o preenchimento de todos os campos / Dados inseridos inválidos";
                            System.out.println(message);

                            request.setAttribute("message", message);
                            request.setAttribute("error", 1);
                            RequestDispatcher error = getServletContext().getRequestDispatcher("/formEspecialidade.jsp");
                            error.forward(request, response);
                            }
                            request.setAttribute("message", message);
                        
                            } catch (Exception e) {
                                message = "É obrigatório o preenchimento de todos os campos / Dados inseridos inválidos";
                                System.out.println(message);

                                request.setAttribute("message", message);
                                request.setAttribute("error", 1);
                                RequestDispatcher error = getServletContext().getRequestDispatcher("/formEspecialidade.jsp");
                                error.forward(request, response);
                  
                                } finally {
                                    ArrayList<Especialidade> especialidades;
                                    especialidades = especialidadeDAO.getAll();
                                    request.setAttribute("especialidades", especialidades);
                                    RequestDispatcher list = getServletContext().getRequestDispatcher("/cadastraEspecialidades.jsp");
                                    list.forward(request, response);

                                } 
                                break;
                        
                case "update":
                    try {
                        Especialidade especialidade = new Especialidade();
                        if (request.getParameter("descricao").equals("")) {
                            message = "'Descrição' is empty";
                            request.setAttribute("error", 1);
                        }
                    
                        if (message.equals("")) {
                            especialidade.setId(Integer.parseInt(request.getParameter("id")));
                            especialidade.setDescricao(request.getParameter("descricao"));
                        
                            if (especialidadeDAO.update(especialidade)) {
                                request.setAttribute("error", 0);
                            } else {
                                message = "Não Efetivado";
                                request.setAttribute("error", 1);
                            }
                            } else {
                            message = "É obrigatório o preenchimento de todos os campos / Dados inseridos inválidos";
                            System.out.println(message);

                            request.setAttribute("message", message);
                            request.setAttribute("error", 1);
                            RequestDispatcher error = getServletContext().getRequestDispatcher("/formEditarEspecialidade.jsp");
                            error.forward(request, response);
                            }
                            request.setAttribute("message", message);
                            
                            } catch (Exception e) {
                                request.setAttribute("message", message);
                                request.setAttribute("error", 1);
                            } finally {
                                ArrayList<Especialidade> especialidades;
                                especialidades = especialidadeDAO.getAll();
                                request.setAttribute("especialidades", especialidades);
                                RequestDispatcher list = getServletContext().getRequestDispatcher("/cadastraEspecialidades.jsp");
                                list.forward(request, response);
                            }
                            break;
            }
    }
}


