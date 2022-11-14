<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="models.Paciente"%>
<%
    Paciente paciente = (Paciente) session.getAttribute("paciente");
    
    if (paciente == null) {
        response.sendRedirect("LoginController?action=login");
    }
%>