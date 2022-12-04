<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="models.Administrador"%>
<%
    Administrador administrador = (Administrador) session.getAttribute("administrador");
    
    if (administrador == null) {
        response.sendRedirect("LoginController?action=loginadmin");
    }
%>
