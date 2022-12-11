package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Administrador;
import dao.AdministradorDAO;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "AdministradorController", urlPatterns = {"/AdministradorController"})
public class AdministradorController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            AdministradorDAO administradorDAO = new AdministradorDAO();
            Administrador administrador = new Administrador();
            ArrayList<Administrador> administradores;
            int administradorId;
            
            String action = (String) request.getParameter("action");
            
            switch (action) {
                
                case "get":
                    administradorId = Integer.parseInt(request.getParameter("id"));
                    administrador = administradorDAO.get(administradorId);
                    
                    request.setAttribute("administrador", administrador);
                    RequestDispatcher get = getServletContext().getRequestDispatcher("/visualizarAdministrador.jsp");       
                    get.forward(request, response);
                    
                    break;
                    
                case "delete":
                    administradorId = Integer.parseInt(request.getParameter("id"));
                    administradorDAO.delete(administradorId);
                
                    administradores = administradorDAO.getAll();
                
                    request.setAttribute("administradores", administradores);
                    RequestDispatcher delete = getServletContext().getRequestDispatcher("/cadastraAdministradores.jsp");
                    delete.forward(request, response);
                
                    break;
                
                case "update":
                    administradorId = Integer.parseInt(request.getParameter("id"));
                    administrador = administradorDAO.get(administradorId);
                    
                    request.setAttribute("administrador", administrador);
                    RequestDispatcher update = getServletContext().getRequestDispatcher("/formEditarAdministrador.jsp");
                    update.forward(request, response);       
                    
                    break;
            }
           
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            AdministradorDAO administradorDAO = new AdministradorDAO();
            request.setCharacterEncoding("UTF-8");
            String message = "";
            
            String action = (String) request.getParameter("action");
            
            switch (action) {
                
                case "insert":
                    try {
                        Administrador administrador = new Administrador();
                        if (request.getParameter("nome").equals("")) {
                            message = "'Nome' is empty";
                            request.setAttribute("error", 1);
                        }
                        if (request.getParameter("cpf").equals("") || request.getParameter("cpf").equals(administradorDAO.get(request.getParameter("cpf")).getCpf())) {
                            message = "'Cpf' is empty or already exists";
                            request.setAttribute("error", 1);
                        }
                        if (request.getParameter("senha").equals("")) {
                            message = "'Senha' is empty";
                            request.setAttribute("error", 1);
                        }

                        if (message.equals("")) {
                            administrador.setNome(request.getParameter("nome"));
                            administrador.setCpf(request.getParameter("cpf"));
                            administrador.setSenha(request.getParameter("senha"));

                            if(administradorDAO.insert(administrador)) {
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
                            RequestDispatcher error = getServletContext().getRequestDispatcher("/formAdministrador.jsp");
                            error.forward(request, response);
                            }
                            request.setAttribute("message", message);
                            
                            } catch (Exception e) {
                                message = "É obrigatório o preenchimento de todos os campos / Dados inseridos inválidos / CPF já cadastrado no banco de dados";
                                System.out.println(message);

                                request.setAttribute("message", message);
                                request.setAttribute("error", 1);
                                RequestDispatcher error = getServletContext().getRequestDispatcher("/formAdministrador.jsp");
                                error.forward(request, response);
                            } finally {
                                ArrayList<Administrador> administradores;
                                administradores = administradorDAO.getAll();

                                request.setAttribute("administradores", administradores);
                                RequestDispatcher list = getServletContext().getRequestDispatcher("/cadastraAdministradores.jsp");
                                list.forward(request, response);
                            }
                            break;
                    
                case "update":
                    try {
                        Administrador administrador = new Administrador();
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

                        if (message.equals("")) {
                            administrador.setId(Integer.parseInt(request.getParameter("id")));
                            administrador.setNome(request.getParameter("nome"));
                            administrador.setCpf(request.getParameter("cpf"));
                            administrador.setSenha(request.getParameter("senha"));
                            
                        if (administradorDAO.update(administrador)) {
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
                            RequestDispatcher error = getServletContext().getRequestDispatcher("/formEditarAdministrador.jsp");
                            error.forward(request, response);
                        } 
                        request.setAttribute("message", message);
                        
                    } catch (Exception e) {
                        request.setAttribute("message", message);
                        request.setAttribute("error", 1); 
                    } finally {
                        ArrayList<Administrador> administradores;
                        administradores = administradorDAO.getAll();
                        request.setAttribute("administradores", administradores);
                        RequestDispatcher list = getServletContext().getRequestDispatcher("/cadastraAdministradores.jsp");
                        list.forward(request, response);
                    }
                    break;
            }
    }
}


