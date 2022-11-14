package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("        <title> Clínica Tais Bruno </title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "components/menu.jsp", out, false);
      out.write("\n");
      out.write("        \n");
      out.write("    <div class=\"rounded border border-dark p-4 m-5\" style=\"padding: 10px; text-align: center\">        \n");
      out.write("    <h3 class=\"card-title\" style=\"margin-top: 80px\">Bem-vindo a Clínica Tais Bruno!</h3> \n");
      out.write("    </div>\n");
      out.write("        <section>\n");
      out.write("            <p style=\"text-align:center\"><img src=\"img/clinica.jpg\" alt=\"clinica\" style=\"padding: 10px; width: 900px\"></p>\n");
      out.write("                <div class=\"thumb\" style=\"height: 40px\"></div>\n");
      out.write("                <div class=\"info\" style=\"justify-content: center; text-align: center\">\n");
      out.write("                    <div>\n");
      out.write("                        <h3 class=\"card-title\" style=\"margin-top: 60px\"> Especialidades:</h3> \n");
      out.write("                    </div>\n");
      out.write("                    <br>\n");
      out.write("                    <p>Cardiologia</p>\n");
      out.write("                    <p>Neurologia</p>\n");
      out.write("                    <p>Gastrologista</p>\n");
      out.write("                    <p>Pneumologia</p>\n");
      out.write("                </div>\n");
      out.write("        </section>\n");
      out.write("        <section>\n");
      out.write("                <div class=\"thumb\"></div>\n");
      out.write("                <div class=\"info\" style=\"justify-content: center; text-align: center\">\n");
      out.write("                    <div>\n");
      out.write("                        <h3 class=\"card-title\" style=\"margin-top: 60px\"> Convênios aceitos:</h3> \n");
      out.write("                    </div>\n");
      out.write("                    <br>\n");
      out.write("                    <p>SulAmerica</p>\n");
      out.write("                    <p>Unimed</p>\n");
      out.write("                    <p>Amil</p>\n");
      out.write("                    <p>Particular</p>\n");
      out.write("                </div>\n");
      out.write("        </section>\n");
      out.write("            <footer class=\"mastfoot mt-auto\" style=\"text-align: center\">\n");
      out.write("                <div class=\"container\" style=\"margin-top: 80px\">\n");
      out.write("                    <span class=\"text-muted\">Desenvolvido por <b>Tais Bruno</b><br/>Universidade Federal Fluminense</span>\n");
      out.write("                </div>\n");
      out.write("            </footer>\n");
      out.write("        <!-- JavaScript Bundle with Popper -->\n");
      out.write("        <script src=\"bootstrap/bootstrap.bundle.min.js\"></script>\n");
      out.write("    </body>\n");
      out.write("</html>");
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
