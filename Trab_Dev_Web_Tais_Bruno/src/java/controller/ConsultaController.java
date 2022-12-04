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
                        String data = request.getParameter("data");
                        String hora = request.getParameter("hora");
                        String descricao = request.getParameter("descricao");
                        String realizada = request.getParameter("realizada");
                        String idmedico = request.getParameter("idmedico");
                        String idpaciente = request.getParameter("idpaciente");
                        String dataCompleta = data + " " + hora;
            
                        String dataformated = Date.format(dataCompleta, "brazilonlyDate");
            
                        Consulta consulta = new Consulta(dataCompleta, descricao, realizada, Integer.parseInt(idmedico), Integer.parseInt(idpaciente));
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
                    break;
                    
                case "update":
                        Consulta consultaa = new Consulta();

                        consultaa.setId(Integer.parseInt(request.getParameter("id")));
                        consultaa.setData(Date.format(request.getParameter("data"), "backToDatabase"));
                        consultaa.setDescricao(request.getParameter("descricao"));
                        consultaa.setRealizada(request.getParameter("realizada"));
                        consultaa.setIdMedico(Integer.parseInt(request.getParameter("idmedico")));
                        consultaa.setIdPaciente(Integer.parseInt(request.getParameter("idpaciente")));
                        consultaDAO.update(consultaa);

                        ArrayList<Consulta> consultasmedico;
                        Medico medico = medicoDAO.get(Integer.parseInt(request.getParameter("idmedico")));
                        consultasmedico = consultaDAO.getByMedico(medico.getId());

                        request.setAttribute("consultasMedico", consultasmedico);
                        RequestDispatcher list = getServletContext().getRequestDispatcher("/realizarConsulta.jsp");
                        list.forward(request, response);

                    break;
                    
                case "realizar":
                        ExameDAO exameDAO = new ExameDAO();
                        Exames exame = new Exames();
                        Consulta consultaaa = new Consulta();

                        consultaaa.setId(Integer.parseInt(request.getParameter("id")));
                        consultaaa.setData(Date.format(request.getParameter("data"), "backToDatabase"));
                        consultaaa.setDescricao(request.getParameter("descricao"));
                        consultaaa.setRealizada(request.getParameter("realizada"));
                        consultaaa.setIdMedico(Integer.parseInt(request.getParameter("idmedico")));
                        consultaaa.setIdPaciente(Integer.parseInt(request.getParameter("idpaciente")));
                        consultaDAO.update(consultaaa); 

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

                            ArrayList<Consulta> consultasmedicoo;
                            Medico medicoo = medicoDAO.get(Integer.parseInt(request.getParameter("idmedico")));
                            consultasmedicoo = consultaDAO.getByMedico(medicoo.getId());

                            request.setAttribute("consultasMedico", consultasmedicoo);
                            RequestDispatcher listt = getServletContext().getRequestDispatcher("/realizarConsulta.jsp");
                            listt.forward(request, response);
                        }
                    break;
    
            }   
    }
}