package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;
import models.Consulta;

public final class listaconsultas_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"pt-BR\">\n");
      out.write("    <head>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("\n");
      out.write("        <!-- BOOTSTRAP -->\n");
      out.write("        <link rel=\"stylesheet\" href=\"bootstrap/bootstrap.min.css\">\n");
      out.write("\n");
      out.write("        <!-- TITLE -->\n");
      out.write("        <title> Clínica Tais Bruno - Lista de Consultas </title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "components/menu.jsp", out, false);
      out.write("\n");
      out.write("        \n");
      out.write("        <div class=\"card-title\">\n");
      out.write("                <div class=\"card-body\" style=\"padding: 10%; text-align: center;\">\n");
      out.write("                    <h2 class=\"card-title\" style=\"margin-top: 20px; margin-bottom: 90px; padding-bottom: 5%; text-align: center\">Clínica Tais Bruno - Consultas</h2>\n");
      out.write("                    <div class=\"container\">\n");
      out.write("            <div class=\"mt-5\">\n");
      out.write("        <div class=\"table table-hover\">\n");
      out.write("        <table class=\"table\">\n");
      out.write("            <thead>\n");
      out.write("                <tr>\n");
      out.write("                    <th>\n");
      out.write("                        Data\n");
      out.write("                    </th>\n");
      out.write("                    <th>\n");
      out.write("                        Descricao\n");
      out.write("                    </th>\n");
      out.write("                    <th>\n");
      out.write("                        Realizada?\n");
      out.write("                    </th>\n");
      out.write("                    <th>\n");
      out.write("                        Medico\n");
      out.write("                    </th>\n");
      out.write("                </tr>\n");
      out.write("            </thead>\n");
      out.write("                ");

                    ArrayList<Consulta> listaconsultas = (ArrayList<Consulta>) request.getAttribute("listaconsultas");
                    if (listaconsultas.isEmpty()){
                        out.print("<h1>Sua lista de consultas está vazia! D:</h1>");
                    }
                    for (Consulta consulta : listaconsultas) {
                        int id = consulta.getId();
                        String data = consulta.getData();
                        String descricao = consulta.getDescricao();
                        String realizada = consulta.getRealizada();
                        int idmedico = consulta.getIdMedico();
                        int idpaciente = consulta.getIdPaciente();
                
      out.write("\n");
      out.write("            <tbody>\n");
      out.write("                    <tr>\n");
      out.write("                        <td>\n");
      out.write("                            ");
      out.print( data);
      out.write("\n");
      out.write("                        </td>\n");
      out.write("                        <td>\n");
      out.write("                            ");
      out.print( descricao);
      out.write("                         \n");
      out.write("                        </td>\n");
      out.write("                        <td>\n");
      out.write("                            ");
      out.print( realizada);
      out.write("\n");
      out.write("                        </td>\n");
      out.write("                        <td>\n");
      out.write("                            ");
      out.print( idmedico);
      out.write("\n");
      out.write("                        </td>\n");
      out.write("                        <td>\n");
      out.write("                            <a href=\"/ClinicaTais/\"><button style=\"background: #fff; border-radius: 6px; padding: 15px; cursor: pointer; color: #008AAF; border: none; font-size: 16px;\">Editar</button></a>\n");
      out.write("                        </td>\n");
      out.write("                    </tr>\n");
      out.write("            </tbody>\n");
      out.write("            ");
     }
      out.write("\n");
      out.write("        </table>\n");
      out.write("    </div>\n");
      out.write("                </div>\n");
      out.write("            \n");
      out.write("        <!-- JavaScript Bundle with Popper -->\n");
      out.write("        <script src=\"bootstrap/bootstrap.bundle.min.js\"></script>\n");
      out.write("        </div>\n");
      out.write("        </div>\n");
      out.write("        </div>\n");
      out.write("        </body>\n");
      out.write("</html>\n");
      out.write("\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
