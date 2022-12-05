package controller;

import dao.ConsultaDAO;
import dao.ExameDAO;
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
import models.Exames;
import models.Medico;
import utils.Date;

@WebServlet(name = "ConsultaController", urlPatterns = {"/ConsultaController"})
public class ConsultaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            ConsultaDAO consultaDAO = new ConsultaDAO();
            Consulta consulta = new Consulta();
            int consultaId;

            String action = (String) request.getParameter("action");

            switch (action) {

                case "get":
                    consultaId = Integer.parseInt(request.getParameter("id"));
                    consulta = consultaDAO.get(consultaId);

                    request.setAttribute("consultasMedico", consulta);
                    RequestDispatcher get = getServletContext().getRequestDispatcher("/visualizarConsulta.jsp");
                    get.forward(request, response);

                    break;
                    
                case "update":
                    consultaId = Integer.parseInt(request.getParameter("id"));
                    consulta = consultaDAO.get(consultaId);

                    request.setAttribute("consultasMedico", consulta);
                    RequestDispatcher update = getServletContext().getRequestDispatcher("/formEditarConsulta.jsp");
                    update.forward(request, response);

                    break;
                    
                case "realizar":
                    consultaId = Integer.parseInt(request.getParameter("id"));
                    consulta = consultaDAO.get(consultaId);

                    request.setAttribute("consultasMedico", consulta);
                    RequestDispatcher realizar = getServletContext().getRequestDispatcher("/formRealizarConsulta.jsp");
                    realizar.forward(request, response);

                    break;
                    
                case "getlistamedico":
                    consultaId = Integer.parseInt(request.getParameter("id"));
                    consulta = consultaDAO.get(consultaId);
                
                    request.setAttribute("consultasMedico", consulta);
                    RequestDispatcher getlistamedico = getServletContext().getRequestDispatcher("/listaExamesListaMedico.jsp");
                    getlistamedico.forward(request, response);
                
                    break;
                
                case "getlistapaciente":
                    consultaId = Integer.parseInt(request.getParameter("id"));
                    consulta = consultaDAO.get(consultaId);

                    request.setAttribute("consultasMedico", consulta);
                    RequestDispatcher getlistapaciente = getServletContext().getRequestDispatcher("/listaExamesListaPaciente.jsp");
                    getlistapaciente.forward(request, response);

                    break;
                    
                case "getlistamedico_admin":
                    consultaId = Integer.parseInt(request.getParameter("id"));
                    consulta = consultaDAO.get(consultaId);
                
                    request.setAttribute("consultasMedico", consulta);
                    RequestDispatcher getlistamedico_admin = getServletContext().getRequestDispatcher("/listaExamesListaMedico_Admin.jsp");
                    getlistamedico_admin.forward(request, response);
                
                    break;
        } 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
            ConsultaDAO consultaDAO = new ConsultaDAO();
            MedicoDAO medicoDAO = new MedicoDAO();
            request.setCharacterEncoding("UTF-8");
            String message = "";
            
            String action = (String) request.getParameter("action");

            switch (action) {
                
                case "marcar":
                    try {
                        Consulta consulta = new Consulta();
                        if (request.getParameter("data").equals("")) {
                            message = "'Data' is empty";
                            request.setAttribute("error", 1);
                        }
                        if (request.getParameter("hora").equals("")) {
                            message = "'Hora' is empty";
                            request.setAttribute("error", 1);
                        }
                        if (request.getParameter("descricao").equals("")) {
                            message = "'Descricao' is empty";
                            request.setAttribute("error", 1);
                        }
                        if (request.getParameter("realizada").equals("")) {
                            message = "'Realizada' is empty";
                            request.setAttribute("error", 1);
                        }
                        if (request.getParameter("idmedico").equals("")) {
                            message = "'IdMedico' is empty";
                            request.setAttribute("error", 1);
                        }
                        if (request.getParameter("idpaciente").equals("")) {
                            message = "'IdPaciente' is empty";
                            request.setAttribute("error", 1);
                        }
                    
                        if (message.equals("")) {
                            String data = request.getParameter("data");
                            String hora = request.getParameter("hora");
                            String dataCompleta = data + " " + hora;
                            String dataformated = Date.format(dataCompleta, "brazilonlyDate");
                        
                            consulta.setData(dataCompleta);
                            consulta.setDescricao(request.getParameter("descricao"));
                            consulta.setRealizada(request.getParameter("realizada"));
                            consulta.setIdMedico(Integer.parseInt(request.getParameter("idmedico")));
                            consulta.setIdPaciente(Integer.parseInt(request.getParameter("idpaciente")));
                        
                            ArrayList<Consulta> consultas = consultaDAO.getAll();
            
                            for (Consulta consultaa: consultas) {
                                if (dataformated.equals(consultaa.getData().split(" ")[0]) && (Integer.parseInt(request.getParameter("idmedico")) == consultaa.getIdMedico())) {
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
                            request.setAttribute("error", 0);
                            HttpSession session = request.getSession();
                            session.setAttribute("consulta", consulta);
                            RequestDispatcher done = request.getRequestDispatcher("/AreaPaciente.jsp");
                            done.forward(request, response);
                        } 
                        } else {
                            message = "É obrigatório o preenchimento de todos os campos / Dados inseridos inválidos";
                            System.out.println(message);

                            request.setAttribute("message", message);
                            request.setAttribute("error", 1);
                            RequestDispatcher error = getServletContext().getRequestDispatcher("/formConsulta.jsp");
                            error.forward(request, response);
                        }
                        request.setAttribute("message", message);
                        
                        } catch (Exception e) {
                            message = "É obrigatório o preenchimento de todos os campos / Dados inseridos inválidos";
                            System.out.println(message);

                            request.setAttribute("message", message);
                            request.setAttribute("error", 1);
                            RequestDispatcher error = getServletContext().getRequestDispatcher("/formConsulta.jsp");
                            error.forward(request, response);
                        } finally {
                            ArrayList<Consulta> consultasmedico;
                            Medico medico = medicoDAO.get(Integer.parseInt(request.getParameter("idmedico")));
                            consultasmedico = consultaDAO.getByMedico(medico.getId());
                            
                            request.setAttribute("consultasMedico", consultasmedico);
                            RequestDispatcher list = getServletContext().getRequestDispatcher("/AreaPaciente.jsp");
                            list.forward(request, response);
                        }
                        break;
                    
                case "update":
                        try {
                            Consulta consulta = new Consulta();
                            if (request.getParameter("data").equals("")) {
                                message = "'Data' is empty";
                                request.setAttribute("error", 1);
                            }
                            if (request.getParameter("realizada").equals("")) {
                                message = "'Realizada' is empty";
                                request.setAttribute("error", 1);
                            }
                            if (request.getParameter("idmedico").equals("")) {
                                message = "'IdMedico' is empty";
                                request.setAttribute("error", 1);
                            }
                            if (request.getParameter("idpaciente").equals("")) {
                                message = "'IdPaciente' is empty";
                                request.setAttribute("error", 1);
                            }
                        
                        if (message.equals("")) {
                            consulta.setId(Integer.parseInt(request.getParameter("id")));
                            consulta.setData(Date.format(request.getParameter("data"), "backToDatabase"));
                            consulta.setDescricao(request.getParameter("descricao"));
                            consulta.setRealizada(request.getParameter("realizada"));
                            consulta.setIdMedico(Integer.parseInt(request.getParameter("idmedico")));
                            consulta.setIdPaciente(Integer.parseInt(request.getParameter("idpaciente")));
                            
                        if (consultaDAO.update(consulta)) {
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
                            RequestDispatcher error = getServletContext().getRequestDispatcher("/formEditarConsulta.jsp");
                            error.forward(request, response);
                        } 
                        request.setAttribute("message", message);
                        
                    } catch (Exception e) {
                        request.setAttribute("message", message);
                        request.setAttribute("error", 1); 
                    } finally {
                        ArrayList<Consulta> consultasmedico;
                        Medico medico = medicoDAO.get(Integer.parseInt(request.getParameter("idmedico")));
                        consultasmedico = consultaDAO.getByMedico(medico.getId());

                        request.setAttribute("consultasMedico", consultasmedico);
                        RequestDispatcher list = getServletContext().getRequestDispatcher("/realizarConsulta.jsp");
                        list.forward(request, response);
                    }
                    break;
                    
                case "realizar":
                        ExameDAO exameDAO = new ExameDAO();
                        Exames exame = new Exames();
                        Consulta consulta = new Consulta();

                        consulta.setId(Integer.parseInt(request.getParameter("id")));
                        consulta.setData(Date.format(request.getParameter("data"), "backToDatabase"));
                        consulta.setDescricao(request.getParameter("descricao"));
                        consulta.setRealizada(request.getParameter("realizada"));
                        consulta.setIdMedico(Integer.parseInt(request.getParameter("idmedico")));
                        consulta.setIdPaciente(Integer.parseInt(request.getParameter("idpaciente")));
                        consultaDAO.update(consulta); 

                        try {
                            String[] exames = request.getParameterValues("idtipoexame");
                            String id = request.getParameter("id");

                        if (exames.length > 0) {
                            for (int j = 0; j < exames.length; j++) {
                                exame.setIdTipoExame(Integer.parseInt(exames[j]));
                                exame.setIdConsulta(Integer.parseInt(id));
                                exameDAO.insert(exame);
                            }
                        }
                        } catch(NumberFormatException e) {
                            message = "Error: " + e.getMessage();
                            System.out.println(message);

                        request.setAttribute("message", message);
                        request.setAttribute("error", 1);

                        } finally {
                            ArrayList<Consulta> consultasmedico;
                            Medico medico = medicoDAO.get(Integer.parseInt(request.getParameter("idmedico")));
                            consultasmedico = consultaDAO.getByMedico(medico.getId());

                            request.setAttribute("consultasMedico", consultasmedico);
                            RequestDispatcher list = getServletContext().getRequestDispatcher("/AreaMedico.jsp");
                            list.forward(request, response);
                        }
                        break;
            }   
    }
}