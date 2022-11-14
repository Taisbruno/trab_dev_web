package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class formExame_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<html lang=\"pt-BR\">\n");
      out.write("    <head>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("\n");
      out.write("        <!-- BOOTSTRAP -->\n");
      out.write("        <link rel=\"stylesheet\" href=\"bootstrap/bootstrap.min.css\">\n");
      out.write("        \n");
      out.write("        <title> Marcação de Exames </title>\n");
      out.write("    </head>\n");
      out.write("<body>      \n");
      out.write("    <header class=\"masthead mb-auto\">\n");
      out.write("                <div class=\"card-body\">\n");
      out.write("                    <nav class=\"nav nav-masthead justify-content\" style=\"text-align: center; flex-wrap: nowrap; justify-content: center; margin-top: 10px\">\n");
      out.write("                        <a class=\"btn btn-primary\" style=\"margin-right: 30px\" href=\"index.jsp\">Início</a>\n");
      out.write("                        <a class=\"btn btn-primary\" style=\"margin-right: 30px\" href=\"exames.jsp\">Exames</a>\n");
      out.write("                        <a class=\"btn btn-primary\" style=\"margin-right: 30px\" href=\"consultas.jsp\">Consultas</a>\n");
      out.write("                        <a class=\"btn btn-primary\" style=\"margin-right: 30px\" href=\"sobre.jsp\">Sobre</a>\n");
      out.write("                        <a class=\"btn btn-primary\" style=\"margin-right: 30px\" href=\"Identifique-se.jsp\"><i class=\"fas fa-sign-in-alt\"></i>Entrar</a>\n");
      out.write("    </nav>\n");
      out.write("    </div>\n");
      out.write("    </header>\n");
      out.write("    <div class=\"rounded border border-dark p-4 m-5\">\n");
      out.write("    <h4 class=\"card-title\">Marcar exame:</h4>\n");
      out.write("    <form method=\"POST\" action=\"ExameController\">                   \n");
      out.write("        \n");
      out.write("        <div class=\"form-group\">\n");
      out.write("            <label for=\"exame\"> Selecione o Exame </label>\n");
      out.write("            <input type=\"search\"  class=\"form-control\" name=\"exame\" id=\"exame\" required>\n");
      out.write("        </div>\n");
      out.write("                \n");
      out.write("        <div class=\"form-group\">\n");
      out.write("            <label for=\"consulta\"> Selecione a Consulta </label>\n");
      out.write("            <input type=\"search\" class=\"form-control\" name=\"consulta\" id=\"consulta\" required>\n");
      out.write("        </div>\n");
      out.write("           \n");
      out.write("        <!-- BOTÃO -->\n");
      out.write("        <button type=\"submit\" class=\"btn btn-dark\"><i class=\"fas fa-save\"></i> Enviar </button>\n");
      out.write("    </form>\n");
      out.write("    </div>\n");
      out.write("    <script src=\"bootstrap/bootstrap.bundle.min.js\"></script>\n");
      out.write("</body>\n");
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
