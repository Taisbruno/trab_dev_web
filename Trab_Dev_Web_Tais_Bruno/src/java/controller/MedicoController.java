package controller;

import dao.ConsultaDAO;
import dao.ExameDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Medico;
import dao.MedicoDAO;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import models.Consulta;
import models.Exames;

@WebServlet(name = "MedicoController", urlPatterns = {"/MedicoController"})
public class MedicoController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            MedicoDAO medicoDAO = new MedicoDAO();
            Medico medico = new Medico();
            ConsultaDAO consultaDAO = new ConsultaDAO();
            ExameDAO exameDAO = new ExameDAO();
            ArrayList<Medico> medicos;
            int medicoId;
            
            String action = (String) request.getParameter("action");
            
            switch (action) {
                
                case "get":
                    medicoId = Integer.parseInt(request.getParameter("id"));
                    medico = medicoDAO.get(medicoId);
                    
                    request.setAttribute("medico", medico);
                    RequestDispatcher get = getServletContext().getRequestDispatcher("/visualizarMedico.jsp");       
                    get.forward(request, response);
                    
                    break;
                    
                case "getAll":
                    medicoId = Integer.parseInt(request.getParameter("id"));
                    medico = medicoDAO.get(medicoId);
                    
                    request.setAttribute("medico", medico);
                    RequestDispatcher view = getServletContext().getRequestDispatcher("/visualizarConsultaMedico_Admin.jsp");       
                    view.forward(request, response);
                    
                    break;
                    
                case "delete":
                    medicoId = Integer.parseInt(request.getParameter("id"));
                    ArrayList<Consulta> consultas = consultaDAO.getByMedico(medicoId);
                        for (Consulta consultaa : consultas) {
                            ArrayList<Exames> exames = exameDAO.getByConsulta(consultaa.getId());
                            for (Exames examee : exames) {
                                exameDAO.delete(examee.getId());
                            }
                            consultaDAO.delete(consultaa.getId());
                    }
                    medicoDAO.delete(medicoId);
                
                    medicos = medicoDAO.getAll();
                
                    request.setAttribute("medicos", medicos);
                    RequestDispatcher delete = getServletContext().getRequestDispatcher("/cadastraMedicos.jsp");
                    delete.forward(request, response);
                
                    break;
                    
                case "update":
                    medicoId = Integer.parseInt(request.getParameter("id"));
                    medico = medicoDAO.get(medicoId);
                    
                    request.setAttribute("medico", medico);
                    RequestDispatcher update = getServletContext().getRequestDispatcher("/formEditarMedico.jsp");
                    update.forward(request, response);       
                    
                    break;
            }
           
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            MedicoDAO medicoDAO = new MedicoDAO();
            request.setCharacterEncoding("UTF-8");
            String message = "";
            
            String action = (String) request.getParameter("action");
            
            switch (action) {
                
                case "insert":
                    try {
                        Medico medico = new Medico();
                        if (request.getParameter("nome").equals("")) {
                            message = "'Nome' is empty";
                            request.setAttribute("error", 1);
                        }
                        if (request.getParameter("crm").equals("")) {
                            message = "'Crm' is empty";
                            request.setAttribute("error", 1);
                        }
                        if (request.getParameter("estadocrm").equals("")) {
                            message = "'EstadoCrm' is empty";
                            request.setAttribute("error", 1);
                        }
                        if (request.getParameter("cpf").equals("") || request.getParameter("cpf").equals(medicoDAO.get(request.getParameter("cpf")).getCpf())) {
                            message = "'Cpf' is empty or already exists";
                            request.setAttribute("error", 1);
                        }
                        if (request.getParameter("senha").equals("")) {
                            message = "'Senha' is empty";
                            request.setAttribute("error", 1);
                        }
                        if (request.getParameter("autorizado").equals("")) {
                            message = "'Autorizado' is empty";
                            request.setAttribute("error", 1);
                        }
                        if (request.getParameter("idespecialidade").equals("")) {
                            message = "'IdEspecialidade' is empty";
                            request.setAttribute("error", 1);
                        }
                    
                    if (message.equals("")) {
                        medico.setNome(request.getParameter("nome"));
                        medico.setCrm(Integer.parseInt(request.getParameter("crm")));
                        medico.setEstadoCrm(request.getParameter("estadocrm"));
                        medico.setCpf(request.getParameter("cpf"));
                        medico.setSenha(request.getParameter("senha"));
                        medico.setAutorizado(request.getParameter("autorizado"));
                        medico.setIdEspecialidade(Integer.parseInt(request.getParameter("idespecialidade")));

                        if (medicoDAO.insert(medico)) {
                            request.setAttribute("error", 0);
                        } else {
                            message = "Não Efetivado";
                            request.setAttribute("error", 1);
                        }
                        } else {
                            message = "É obrigatório o preenchimento de todos os campos / Dados inseridos inválidos / CPF já cadastrado no banco de dados";
                            System.out.println(message);

                            request.setAttribute("message", message);
                            request.setAttribute("error", 1);
                            RequestDispatcher error = getServletContext().getRequestDispatcher("/formMedico.jsp");
                            error.forward(request, response);
                        }
                        request.setAttribute("message", message);
            
                    } catch (Exception e) {
                        message = "É obrigatório o preenchimento de todos os campos / Dados inseridos inválidos / CPF já cadastrado no banco de dados";
                        System.out.println(message);

                        request.setAttribute("message", message);
                        request.setAttribute("error", 1);
                        RequestDispatcher error = getServletContext().getRequestDispatcher("/formMedico.jsp");
                        error.forward(request, response);
                    } finally {
                            ArrayList<Medico> medicos;
                            medicos = medicoDAO.getAll();
                            
                            request.setAttribute("medicos", medicos);
                            RequestDispatcher list = getServletContext().getRequestDispatcher("/cadastraMedicos.jsp");
                            list.forward(request, response);
                        }
                        break;
                    
                case "update":
                    try {
                        Medico medico = new Medico();
                        if (request.getParameter("nome").equals("")) {
                            message = "'Nome' is empty";
                            request.setAttribute("error", 1);
                        }
                        if (request.getParameter("crm").equals("")) {
                            message = "'Crm' is empty";
                            request.setAttribute("error", 1);
                        }
                        if (request.getParameter("estadocrm").equals("")) {
                            message = "'EstadoCrm' is empty";
                            request.setAttribute("error", 1);
                        }
                        if (request.getParameter("cpf").equals("")) {
                            message = "'Cpf' is empty";
                            request.setAttribute("error", 1);
                        }
                        if (request.getParameter("senha").equals("")) {
                            message = "'Senha' is empty";
                            request.setAttribute("error", 1);
                        }
                        if (request.getParameter("autorizado").equals("")) {
                            message = "'Autorizado' is empty";
                            request.setAttribute("error", 1);
                        }
                        if (request.getParameter("idespecialidade").equals("")) {
                            message = "'IdEspecialidade' is empty";
                            request.setAttribute("error", 1);
                        }
                    
                    if (message.equals("")) {
                        medico.setId(Integer.parseInt(request.getParameter("id")));
                        medico.setNome(request.getParameter("nome"));
                        medico.setCrm(Integer.parseInt(request.getParameter("crm")));
                        medico.setEstadoCrm(request.getParameter("estadocrm"));
                        medico.setCpf(request.getParameter("cpf"));
                        medico.setSenha(request.getParameter("senha"));
                        medico.setAutorizado(request.getParameter("autorizado"));
                        medico.setIdEspecialidade(Integer.parseInt(request.getParameter("idespecialidade")));
                        
                     if (medicoDAO.update(medico)) {
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
                            RequestDispatcher error = getServletContext().getRequestDispatcher("/formEditarMedico.jsp");
                            error.forward(request, response);
                        } 
                        request.setAttribute("message", message);
                        
                    } catch (Exception e) {
                        request.setAttribute("message", message);
                        request.setAttribute("error", 1); 
                    } finally {
                        ArrayList<Medico> medicos;
                        medicos = medicoDAO.getAll();
                        request.setAttribute("medicos", medicos);
                        RequestDispatcher list = getServletContext().getRequestDispatcher("/cadastraMedicos.jsp");
                        list.forward(request, response);
                    }
                    break;
            }
    }
}