package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("    <title> Login </title>\n");
      out.write("    </head>\n");
      out.write("\n");
      out.write("<body>        \n");
      out.write("    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "components/menu.jsp", out, false);
      out.write("\n");
      out.write("    \n");
      out.write("    <div class=\"rounded border border-dark p-4 m-5\" style=\"padding: 10px; text-align: center\">\n");
      out.write("    <h3 class=\"card-title\" style=\"margin-top: 80px\">Login</h3>\n");
      out.write("    <form method=\"POST\" action=\"LoginController\" style=\"text-align: center; margin-top: 30px\">                   \n");
      out.write("        <input type=\"hidden\" class=\"form-control\" name=\"id\" id=\"id\">\n");
      out.write("        \n");
      out.write("        <!-- CPF -->\n");
      out.write("        <div class=\"form-group\">\n");
      out.write("            <label for=\"cpf\" style=\"margin-top: 5px\"><b> CPF </b></label>\n");
      out.write("            <input type=\"text\" style=\"margin-top: 5px\" class=\"form-control cpf\" name=\"cpf\" id=\"cpf\" autocomplete=\"off\" placeholder=\"Digite seu CPF\" required>\n");
      out.write("        </div>\n");
      out.write("        <!-- SENHA -->\n");
      out.write("        <div class=\"form-group\">\n");
      out.write("            <label for=\"senha\" style=\"margin-top: 10px\"><b> Senha </b></label>\n");
      out.write("            <input type=\"password\" style=\"margin-top: 10px\" class=\"form-control\" name=\"senha\" id=\"senha\" autocomplete=\"off\" placeholder=\"Digite sua senha\" required>\n");
      out.write("        </div>\n");
      out.write("        <br>\n");
      out.write("        <!-- BOTÃO -->\n");
      out.write("        <button type=\"submit\" class=\"btn btn-dark\"><i class=\"fas fa-save\"></i> Salvar </button>\n");
      out.write("    </form>\n");
      out.write("    </div>\n");
      out.write("    <script src=\"bootstrap/bootstrap.bundle.min.js\"></script>\n");
      out.write("</body>\n");
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
