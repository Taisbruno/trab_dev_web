package controller;

import dao.ConsultaDAO;
import dao.ExameDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Paciente;
import dao.PacienteDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import models.Consulta;
import models.Exames;

@WebServlet(name = "PacienteController", urlPatterns = {"/PacienteController"})
public class PacienteController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            PacienteDAO pacienteDAO = new PacienteDAO();
            Paciente paciente = new Paciente();
            ConsultaDAO consultaDAO = new ConsultaDAO();
            ExameDAO exameDAO = new ExameDAO();
            ArrayList<Paciente> pacientes;
            int pacienteId;
            
            String action = (String) request.getParameter("action");
            
            switch (action) {
                
                case "get":
                    pacienteId = Integer.parseInt(request.getParameter("id"));
                    paciente = pacienteDAO.get(pacienteId);
                    
                    request.setAttribute("paciente", paciente);
                    RequestDispatcher get = getServletContext().getRequestDispatcher("/visualizarPaciente.jsp");       
                    get.forward(request, response);
                    
                    break;
                    
                case "delete":
                    pacienteId = Integer.parseInt(request.getParameter("id"));
                    ArrayList<Consulta> consultas = consultaDAO.getByPaciente(pacienteId);
                        for (Consulta consultaa : consultas) {
                            ArrayList<Exames> exames = exameDAO.getByConsulta(consultaa.getId());
                            for (Exames examee : exames) {
                                exameDAO.delete(examee.getId());
                            }
                            consultaDAO.delete(consultaa.getId());
                        }
                        pacienteDAO.delete(pacienteId);

                        pacientes = pacienteDAO.getAll();

                        request.setAttribute("pacientes", pacientes);
                        RequestDispatcher delete = getServletContext().getRequestDispatcher("/cadastraPacientes.jsp");
                        delete.forward(request, response);
                
                    break;
                    
                case "update":
                    pacienteId = Integer.parseInt(request.getParameter("id"));
                    paciente = pacienteDAO.get(pacienteId);
                    
                    request.setAttribute("paciente", paciente);
                    RequestDispatcher update = getServletContext().getRequestDispatcher("/formEditarPaciente.jsp");
                    update.forward(request, response);       
                    
                    break;
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(PacienteController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            PacienteDAO pacienteDAO = null;
            String message = "";
            
            try {
                pacienteDAO = new PacienteDAO();
            } catch (SQLException ex) {
                Logger.getLogger(PacienteController.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.setCharacterEncoding("UTF-8");
        
            String action = (String) request.getParameter("action");
        
            switch (action) {
                case "insert":
                    try {
                        Paciente paciente = new Paciente();
                        if (request.getParameter("nome").equals("")) {
                            message = "'Nome' is empty";
                            request.setAttribute("error", 1);
                        }
                        if (request.getParameter("cpf").equals("") || request.getParameter("cpf").equals(pacienteDAO.get(request.getParameter("cpf")).getCpf())) {
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
                        if (request.getParameter("idtipoplano").equals("")) {
                            message = "'TipoPlano' is empty";
                            request.setAttribute("error", 1);
                        }

                        if (message.equals("")) {
                            paciente.setNome(request.getParameter("nome"));
                            paciente.setCpf(request.getParameter("cpf"));
                            paciente.setSenha(request.getParameter("senha"));
                            paciente.setAutorizado(request.getParameter("autorizado"));
                            paciente.setIdTipoPlano(Integer.parseInt(request.getParameter("idtipoplano")));
                        
                        if (pacienteDAO.insert(paciente)) {
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
                            RequestDispatcher error = getServletContext().getRequestDispatcher("/formPaciente.jsp");
                            error.forward(request, response);
                        }
                        request.setAttribute("message", message);
            
                    } catch (Exception e) {
                        message = "É obrigatório o preenchimento de todos os campos / Dados inseridos inválidos / CPF já cadastrado no banco de dados";
                        System.out.println(message);

                        request.setAttribute("message", message);
                        request.setAttribute("error", 1);
                        RequestDispatcher error = getServletContext().getRequestDispatcher("/formPaciente.jsp");
                        error.forward(request, response);
                    } finally {
                        ArrayList<Paciente> pacientes;
                        pacientes = pacienteDAO.getAll();
                            
                        request.setAttribute("pacientes", pacientes);
                        RequestDispatcher list = getServletContext().getRequestDispatcher("/AreaPaciente.jsp");
                        list.forward(request, response);
                        }
                    break;
                    
                case "insert_admin":
                    try {
                        Paciente paciente = new Paciente();
                        if (request.getParameter("nome").equals("")) {
                            message = "'Nome' is empty";
                            request.setAttribute("error", 1);
                        }
                       if (request.getParameter("cpf").equals("") || request.getParameter("cpf").equals(pacienteDAO.get(request.getParameter("cpf")).getCpf())) {
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
                        if (request.getParameter("idtipoplano").equals("")) {
                            message = "'TipoPlano' is empty";
                            request.setAttribute("error", 1);
                        }

                        if (message.equals("")) {
                            paciente.setNome(request.getParameter("nome"));
                            paciente.setCpf(request.getParameter("cpf"));
                            paciente.setSenha(request.getParameter("senha"));
                            paciente.setAutorizado(request.getParameter("autorizado"));
                            paciente.setIdTipoPlano(Integer.parseInt(request.getParameter("idtipoplano")));
                        
                        if (pacienteDAO.insert(paciente)) {
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
                            RequestDispatcher error = getServletContext().getRequestDispatcher("/formInsertPaciente_Admin.jsp");
                            error.forward(request, response);
                        }
                        request.setAttribute("message", message);
            
                    } catch (Exception e) {
                        message = "É obrigatório o preenchimento de todos os campos / Dados inseridos inválidos / CPF já cadastrado no banco de dados";
                        System.out.println(message);

                        request.setAttribute("message", message);
                        request.setAttribute("error", 1);
                        RequestDispatcher error = getServletContext().getRequestDispatcher("/formInsertPaciente_Admin.jsp");
                        error.forward(request, response);
                    } finally {
                        ArrayList<Paciente> pacientes;
                        pacientes = pacienteDAO.getAll();
                            
                        request.setAttribute("pacientes", pacientes);
                        RequestDispatcher list = getServletContext().getRequestDispatcher("/cadastraPacientes.jsp");
                        list.forward(request, response);
                    }
                    break;
                    
                case "update":
                    try {
                        Paciente paciente = new Paciente();
                        if (request.getParameter("nome").equals("")) {
                            message = "'Nome' is empty";
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
                        if (request.getParameter("idtipoplano").equals("")) {
                            message = "'TipoPlano' is empty";
                            request.setAttribute("error", 1);
                        }

                        if (message.equals("")) {
                            paciente.setId(Integer.parseInt(request.getParameter("id")));
                            paciente.setNome(request.getParameter("nome"));
                            paciente.setCpf(request.getParameter("cpf"));
                            paciente.setSenha(request.getParameter("senha"));
                            paciente.setAutorizado(request.getParameter("autorizado"));
                            paciente.setIdTipoPlano(Integer.parseInt(request.getParameter("idtipoplano")));
                            
                            if (pacienteDAO.update(paciente)) {
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
                            RequestDispatcher error = getServletContext().getRequestDispatcher("/formEditarPaciente.jsp");
                            error.forward(request, response);
                        } 
                        request.setAttribute("message", message);
                        
                    } catch (Exception e) {
                        request.setAttribute("message", message);
                        request.setAttribute("error", 1); 
                    } finally {
                        ArrayList<Paciente> pacientes;
                        pacientes = pacienteDAO.getAll();
                        request.setAttribute("pacientes", pacientes);
                        RequestDispatcher list = getServletContext().getRequestDispatcher("/cadastraPacientes.jsp");
                        list.forward(request, response);
                    }
                    break;
            } 
    }
}